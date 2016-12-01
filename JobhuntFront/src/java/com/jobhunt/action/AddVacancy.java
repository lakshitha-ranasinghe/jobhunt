/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.remote.VacancyRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.HashMap;
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
public class AddVacancy extends ActionSupport {
  
    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    
    private Vacancy vacancy;

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
    
    public AddVacancy() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser)session.get("user");
        Integer userId = user.getId();

        Map<String, String> remoteVacancy = new HashMap<>();
        remoteVacancy.put("title", vacancy.getTitle());
        remoteVacancy.put("description", vacancy.getDescription());
        remoteVacancy.put("prerequisites", vacancy.getPrerequisites());
        remoteVacancy.put("location",vacancy.getBranch());
        remoteVacancy.put("salary", String.valueOf(vacancy.getSalary()));
        remoteVacancy.put("count", String.valueOf(vacancy.getVacancyCount()));
        Integer vacancyId = vacancyRemote.addVacany(remoteVacancy, userId, vacancy.getClosingDate());
        vacancyRemote.createVacancyCache(vacancyId, new Date());
        return SUCCESS;
    }
    
    private VacancyRemote lookupVacancyManagementRemote() {
        try {
            Context c = new InitialContext();
            return (VacancyRemote) c.lookup("java:global/JobhuntEJB/VacancyBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
