/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.ajaxaction;

import com.jobhunt.remote.VacancyRemote;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lakshitha
 */
public class DeleteVacancy extends ActionSupport {
    private int selectedVacancy;
    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    private String isDeleted;

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }    

    public int getSelectedVacancy() {
        return selectedVacancy;
    }

    public void setSelectedVacancy(int selectedVacancy) {
        this.selectedVacancy = selectedVacancy;
    }
    
    public DeleteVacancy() {
    }
    
    
    public String execute() throws Exception {
        Boolean deleted = vacancyRemote.deleteVacancy(selectedVacancy);
        setIsDeleted(String.valueOf(deleted));
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
