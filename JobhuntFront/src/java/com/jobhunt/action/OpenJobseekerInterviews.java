/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.entity.VacancyApply;
import com.jobhunt.remote.JobseekerRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
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
public class OpenJobseekerInterviews extends ActionSupport {
    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();
    private List<Vacancy> approvedVacancies;
    private List<Vacancy> rejectedVacancies;
    private List<Vacancy> pendingVacancies;
    private Boolean isCompleteJobseeker;
    private Integer seekerId;

    public Integer getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(Integer seekerId) {
        this.seekerId = seekerId;
    }

    public Boolean getIsCompleteJobseeker() {
        return isCompleteJobseeker;
    }

    public void setIsCompleteJobseeker(Boolean isCompleteJobseeker) {
        this.isCompleteJobseeker = isCompleteJobseeker;
    }
    
    public List<Vacancy> getPendingVacancies() {
        return pendingVacancies;
    }

    public void setPendingVacancies(List<Vacancy> pendingVacancies) {
        this.pendingVacancies = pendingVacancies;
    }

    public List<Vacancy> getApprovedVacancies() {
        return approvedVacancies;
    }

    public void setApprovedVacancies(List<Vacancy> approvedVacancies) {
        this.approvedVacancies = approvedVacancies;
    }

    public List<Vacancy> getRejectedVacancies() {
        return rejectedVacancies;
    }

    public void setRejectedVacancies(List<Vacancy> rejectedVacancies) {
        this.rejectedVacancies = rejectedVacancies;
    }
    
    public OpenJobseekerInterviews() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser)session.get("user");
        Integer userId = user.getId();
        
        seekerId = userId;
        
        Jobseeker jobseekerObject = jobSeekerManagement.findPersonalProfile(userId);
        
        if (jobseekerObject == null) {
            isCompleteJobseeker = false;
            return ERROR;
        } else {
            if (jobseekerObject.getJobseekerOlCollection() == null || jobseekerObject.getJobseekerOlCollection().isEmpty()) {
                isCompleteJobseeker = false;
                return ERROR;
            } else {
                if (jobseekerObject.getJobseekerWorkedcompanyCollection() == null || jobseekerObject.getJobseekerWorkedcompanyCollection().isEmpty()) {
                    isCompleteJobseeker = false;
                    return ERROR;
                }
                else{
                    isCompleteJobseeker = true;
                }
            }
        }
        
        List<VacancyApply> appliedVacancies = (List)jobseekerObject.getVacancyApplyCollection();

        rejectedVacancies = new ArrayList<>();
        pendingVacancies = new ArrayList<>();
        approvedVacancies = new ArrayList<>();
        
        for(VacancyApply vacancyApply : appliedVacancies){
            int applyStatus = vacancyApply.getStatus();
            if(applyStatus==1){
                pendingVacancies.add(vacancyApply.getVacancyId());
            }
            else if(applyStatus==0){
                approvedVacancies.add(vacancyApply.getVacancyId());
            }
            else{
                rejectedVacancies.add(vacancyApply.getVacancyId());
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
}
