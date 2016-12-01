/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.ajaxaction;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.CompanyProfile;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.remote.CompanyRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
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
public class GetVacancyDataToUpdate extends ActionSupport {
    private final CompanyRemote companyRemote = lookupCompanyManagementRemote();
    private String selectedVacancy;
    private Vacancy vacancy;

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacany(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public String getSelectedVacancy() {
        return selectedVacancy;
    }

    public void setSelectedVacancy(String selectedVacancy) {
        this.selectedVacancy = selectedVacancy;
    }
    
    public GetVacancyDataToUpdate() {
    }
 
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();
        CompanyProfile company = companyRemote.findCompanyProfile(userId);
        
        int vacancyId  = Integer.parseInt(selectedVacancy.split(":")[0]);
        for(Vacancy currentVacancy : company.getVacancyCollection()){
            if(currentVacancy.getId().equals(vacancyId)){
                vacancy = currentVacancy;
            }
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
