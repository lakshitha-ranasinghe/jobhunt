/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.CompanyProfile;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.remote.CompanyRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lakshitha
 */
public class OpenUpdateVacancies extends ActionSupport {
    private final CompanyRemote companyRemote = lookupCompanyManagementRemote();
    private List<Vacancy> vacancies;
    private Boolean isProfileComplete;

    public Boolean getIsProfileComplete() {
        return isProfileComplete;
    }

    public void setIsProfileComplete(Boolean isProfileComplete) {
        this.isProfileComplete = isProfileComplete;
    }
    
    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }


    public OpenUpdateVacancies() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();
        
        isProfileComplete = true;
        
        CompanyProfile company = companyRemote.findCompanyProfile(userId);
        
        if(company==null){
            isProfileComplete = false;
            return ERROR;
        }
        
        List<Vacancy> companyVacancies = (List)company.getVacancyCollection();
        if(companyVacancies != null){
            setVacancies(companyVacancies);
            return SUCCESS;
        }
        else{
            System.out.println("No Vacancies Found");
            return ERROR;
        }
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
