/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.JobseekerAl;
import com.jobhunt.entity.JobseekerEducation;
import com.jobhunt.entity.JobseekerOl;
import com.jobhunt.entity.JobseekerUniversity;
import com.jobhunt.remote.JobseekerRemote;
import static com.opensymphony.xwork2.Action.SUCCESS;
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
public class OpenEducationalProfile extends ActionSupport {
    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();
    
    private Jobseeker jobseeker;
    private List<String> olResults;
    private List<String> alResults;
    private List<JobseekerOl> jobseekerOl;
    private List<JobseekerAl> jobseekerAl; 
    private List<JobseekerUniversity> jobseekerUniversity;
    private JobseekerEducation jobseekerEducation;
    private Boolean isPersonalExist;
    private static final int FIRSTRESULT = 0;
    private static final String NEW = "new";

    public Boolean getIsPersonalExist() {
        return isPersonalExist;
    }

    public void setIsPersonalExist(Boolean isPersonalExist) {
        this.isPersonalExist = isPersonalExist;
    }

    public OpenEducationalProfile() {
    }

    public List<String> getOlResults() {
        return olResults;
    }

    public void setOlResults(List<String> olResults) {
        this.olResults = olResults;
    }

    public List<String> getAlResults() {
        return alResults;
    }

    public void setAlResults(List<String> alResults) {
        this.alResults = alResults;
    }
    
    public Jobseeker getJobseeker() {
        return jobseeker;
    }

    public List<JobseekerOl> getJobseekerOl() {
        return jobseekerOl;
    }

    public void setJobseekerOl(List<JobseekerOl> jobseekerOl) {
        this.jobseekerOl = jobseekerOl;
    }

    public List<JobseekerAl> getJobseekerAl() {
        return jobseekerAl;
    }

    public void setJobseekerAl(List<JobseekerAl> jobseekerAl) {
        this.jobseekerAl = jobseekerAl;
    }

    public List<JobseekerUniversity> getJobseekerUniversity() {
        return jobseekerUniversity;
    }

    public void setJobseekerUniversity(List<JobseekerUniversity> jobseekerUniversity) {
        this.jobseekerUniversity = jobseekerUniversity;
    }

    public JobseekerEducation getJobseekerEducation() {
        return jobseekerEducation;
    }

    public void setJobseekerEducation(JobseekerEducation jobseekerEducation) {
        this.jobseekerEducation = jobseekerEducation;
    }
    
    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser)session.get("user");
        Integer userId = user.getId();
        Jobseeker jobseekerObject = jobSeekerManagement.findPersonalProfile(userId);
        isPersonalExist = true;
        if(jobseekerObject!=null){
            jobseeker = jobseekerObject;         
        }
        else{
            isPersonalExist = false;
            return ERROR;
        }
        if(jobseeker.getJobseekerAlCollection().isEmpty()){ 
            return NEW;
        }
        jobseekerAl = (List)jobseeker.getJobseekerAlCollection();
        jobseekerOl = (List)jobseeker.getJobseekerOlCollection();
        jobseekerUniversity = (List)jobseeker.getJobseekerUniversityCollection();
        jobseekerEducation = (JobseekerEducation)((List)jobseeker.getJobseekerEducationCollection()).get(FIRSTRESULT); 
        
        alResults = new ArrayList<>();
        olResults = new ArrayList<>();
        for(JobseekerAl al : jobseekerAl){
            alResults.add(al.getMark());
        }
        for(JobseekerOl ol : jobseekerOl){
            olResults.add(ol.getMark());
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
