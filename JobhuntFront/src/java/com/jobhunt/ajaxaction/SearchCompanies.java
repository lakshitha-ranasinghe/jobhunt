/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.ajaxaction;

import com.jobhunt.entity.CompanyProfile;
import com.jobhunt.remote.CompanyRemote;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lakshitha
 */
public class SearchCompanies extends ActionSupport {

    private final CompanyRemote companyRemote = lookupCompanyManagementRemote();
    private List<CompanyProfile> filteredCompanies;
    private String searchString;
    private String name;
    private CompanyProfile companyProfile;
    private int resultFound = 1;

    public int getResultFound() {
        return resultFound;
    }

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setResultFound(int resultFound) {
        this.resultFound = resultFound;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List<CompanyProfile> getFilteredCompanies() {
        return filteredCompanies;
    }

    public void setFilteredCompanies(List<CompanyProfile> filteredCompanies) {
        this.filteredCompanies = filteredCompanies;
    }

    public SearchCompanies() {
    }

    public String execute() throws Exception {
        List<CompanyProfile> companies = companyRemote.findAllCompanies();
        filteredCompanies = new ArrayList<>();
        String pattern = ".*\\b" + searchString + "\\b.*";
        for (CompanyProfile companyProfile : companies) {
            resultFound = 1;
            String line = companyProfile.toString() + companyProfile.getJobType();
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(line);
            if (m.find()) {
                filteredCompanies.add(companyProfile);
            }
        }
        return SUCCESS;
    }
    
    public String findCompanyDetails(){
        companyProfile = companyRemote.findCompanyByName(name.trim());
        if(companyProfile==null){
            companyProfile = new CompanyProfile();
        }
        return SUCCESS;
    }

    private CompanyRemote lookupCompanyManagementRemote() {
        try {
            Context c = new InitialContext();
            return (CompanyRemote) c.lookup("java:global/JobhuntEJB/CompanyBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
