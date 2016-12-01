/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.ajaxaction;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.entity.VacancyApply;
import com.jobhunt.remote.JobseekerRemote;
import com.jobhunt.remote.VacancyRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.DateFormat;
import java.util.ArrayList;
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
public class GetAutomaticRecommendations extends ActionSupport {

    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();
    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    private List<Vacancy> filteredVacancies;
    private List<Integer> appliedVacancyIds;

    public List<Integer> getAppliedVacancyIds() {
        return appliedVacancyIds;
    }

    public void setAppliedVacancyIds(List<Integer> appliedVacancyIds) {
        this.appliedVacancyIds = appliedVacancyIds;
    }
    
    public List<Vacancy> getFilteredVacancies() {
        return filteredVacancies;
    }

    public void setFilteredVacancies(List<Vacancy> filteredVacancies) {
        this.filteredVacancies = filteredVacancies;
    }

    public GetAutomaticRecommendations() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();

        Jobseeker jobseeker = jobSeekerManagement.findPersonalProfile(userId);
        List<Vacancy> vacancies = vacancyRemote.findAllVacancies();
        
        appliedVacancyIds = new ArrayList<>();
        
        List<VacancyApply> jobseekerAppliedVacancies = (List)jobseeker.getVacancyApplyCollection();
        for(VacancyApply vacancyApply : jobseekerAppliedVacancies){
            appliedVacancyIds.add(vacancyApply.getVacancyId().getId());
        }
        
        filteredVacancies = new ArrayList<>();
        for (Vacancy vacancy : vacancies) {
            if(jobseeker.getExpectedJob().equals(vacancy.getTitle())){
                filteredVacancies.add(vacancy);
            }
            else{
                
            }
        }
        
        return SUCCESS;
    }

    private JobseekerRemote lookupJobseekerManagementRemote() {
        try {
            Context c = new InitialContext();
            return (JobseekerRemote) c.lookup("java:global/JobhuntEJB/JobseekerBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
