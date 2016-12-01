/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.ajaxaction;

import com.jobhunt.remote.VacancyRemote;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lakshitha
 */
public class UpdateClosingDate extends ActionSupport {
    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    private String vacancyId;
    private String closingDate;
    private Boolean isUpdateSuccessful;

    public String getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(String vacancyId) {
        this.vacancyId = vacancyId;
    }

    public Boolean getIsUpdateSuccessful() {
        return isUpdateSuccessful;
    }

    public void setIsUpdateSuccessful(Boolean isUpdateSuccessful) {
        this.isUpdateSuccessful = isUpdateSuccessful;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }
    
    public UpdateClosingDate() {
    }
    
    @Override
    public String execute() throws Exception {
        Integer id = Integer.parseInt(vacancyId.trim());
        Date newClosingDate = null;
        if (closingDate != null) {
            String[] dateParts = closingDate.trim().split("-");
            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(dateParts[0]), (Integer.parseInt(dateParts[1]) - 1), Integer.parseInt(dateParts[2]));
            newClosingDate = cal.getTime();
        }
        isUpdateSuccessful = vacancyRemote.UpdateVacancyClosingDate(id, newClosingDate);
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
