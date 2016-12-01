/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.CompanyProfile;
import com.jobhunt.remote.CompanyRemote;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lakshitha
 */
public class OpenCompanyDetails extends ActionSupport {
    private final CompanyRemote companyRemote = lookupCompanyManagementRemote();
    private List<CompanyProfile> companies;

    public List<CompanyProfile> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyProfile> companies) {
        this.companies = companies;
    }

    public OpenCompanyDetails() {
    }
    
    public String execute() throws Exception {
        companies = companyRemote.findAllCompanies();
        if(companies==null){
            companies = new ArrayList<>();
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
