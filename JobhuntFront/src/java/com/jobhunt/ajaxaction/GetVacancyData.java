/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.ajaxaction;

import com.jobhunt.entity.Vacancy;
import com.jobhunt.entity.VacancyApply;
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
public class GetVacancyData extends ActionSupport {

    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    private int selectedVacancyId;
    private Vacancy vacancy;
    private VacancyApply vacancyApply;
    private String selectedVacancyApplyId;

    public VacancyApply getVacancyApply() {
        return vacancyApply;
    }

    public void setVacancyApply(VacancyApply vacancyApply) {
        this.vacancyApply = vacancyApply;
    }

    public String getSelectedVacancyApplyId() {
        return selectedVacancyApplyId;
    }

    public void setSelectedVacancyApplyId(String selectedVacancyApplyId) {
        this.selectedVacancyApplyId = selectedVacancyApplyId;
    }

    public int getSelectedVacancyId() {
        return selectedVacancyId;
    }

    public void setSelectedVacancyId(int selectedVacancyId) {
        this.selectedVacancyId = selectedVacancyId;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public GetVacancyData() {
    }

    public String vacancyDataForRecommendation() throws Exception {
        vacancy = vacancyRemote.findVacany(selectedVacancyId);
        return SUCCESS;
    }
    
    public String jobseekerInterviewVacancyData(){
        String[] applicationNumberParts = selectedVacancyApplyId.split("-");
        
        vacancyApply = vacancyRemote.findVacancyApplyByJobseekerAndVacancy(Integer.parseInt(applicationNumberParts[0]), Integer.parseInt(applicationNumberParts[1]));
        vacancy = vacancyApply.getVacancyId();
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
