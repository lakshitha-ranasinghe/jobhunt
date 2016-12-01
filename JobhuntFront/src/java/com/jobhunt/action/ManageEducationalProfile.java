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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.HashMap;
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
public class ManageEducationalProfile extends ActionSupport {
    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();
    
    private List<JobseekerOl> jobseekerOl;
    private List<JobseekerAl> jobseekerAl; 
    private List<JobseekerUniversity> jobseekerUniversity;
    private JobseekerEducation jobseekerEducation;
    private Jobseeker jobseeker;
    private Boolean isUpdateSuccess;
    private final static String UPDATE = "update";
    private final static int FIRSTRECORD = 0;
    
    public JobseekerEducation getJobseekerEducation() {
        return jobseekerEducation;
    }

    public Boolean getIsUpdateSuccess() {
        return isUpdateSuccess;
    }

    public void setIsUpdateSuccess(Boolean isUpdateSuccess) {
        this.isUpdateSuccess = isUpdateSuccess;
    }

    public void setJobseekerEducation(JobseekerEducation jobseekerEducation) {
        this.jobseekerEducation = jobseekerEducation;
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
    
    public ManageEducationalProfile() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser)session.get("user");
        Integer userId = user.getId();

        Jobseeker jobseekerObject = jobSeekerManagement.findPersonalProfile(userId);
        
        Boolean isNewProfile = false;
        
        if(jobseekerObject!=null){
            jobseeker = jobseekerObject;
        }
        
        isNewProfile = jobseeker.getJobseekerAlCollection().isEmpty();
        
        List<Map<String,String>> alResults = new ArrayList<>();
        List<Map<String,String>> olResults = new ArrayList<>();
        List<Map<String,String>> universities = new ArrayList<>();
        Map<String, String> education = new HashMap<>();
        
        for(JobseekerAl al : jobseekerAl){
            Map<String, String> result = new HashMap<>();
            result.put("subject", al.getSubject());
            result.put("mark", al.getMark());
            alResults.add(result);
        }
        
        for(JobseekerOl ol : jobseekerOl){
            Map<String, String> result = new HashMap<>();
            result.put("subject", ol.getSubject());
            result.put("mark", ol.getMark());
            olResults.add(result);
        }
        
        for(JobseekerUniversity uni : jobseekerUniversity){
            Map<String, String> result = new HashMap<>();
            result.put("name", uni.getName());
            result.put("course", uni.getCourse());
            result.put("gpa", String.valueOf(uni.getGpa()));
            result.put("year", String.valueOf(uni.getCompletedYear()));
            universities.add(result);
        }
        
        education.put("olYear", String.valueOf(jobseekerEducation.getOlYear()));
        education.put("alYear", String.valueOf(jobseekerEducation.getAlYear()));
        education.put("olSchool", jobseekerEducation.getOlSchool());
        education.put("alSchool", jobseekerEducation.getAlSchool());
        
        if(isNewProfile){
            isUpdateSuccess = false;
            jobSeekerManagement.createEducationalProfile(alResults, olResults, universities, education, userId);
            isUpdateSuccess = true;
            return SUCCESS;
        }
        else{
            for(int i=0; i<jobseekerOl.size(); i++){ 
                List<JobseekerOl> oLs = (List)(jobseeker.getJobseekerOlCollection());
                JobseekerOl ol = (JobseekerOl)(oLs.get(i));
                olResults.get(i).put("id", String.valueOf(ol.getOlId()));
            }
            for(int i=0; i<jobseekerAl.size(); i++){
                List<JobseekerAl> aLs = (List)(jobseeker.getJobseekerAlCollection());
                JobseekerAl aL = (JobseekerAl)(aLs.get(i));
                alResults.get(i).put("id", String.valueOf(aL.getAlId()));
            }
            for(int i=0; i<jobseekerUniversity.size(); i++){
                List<JobseekerUniversity> u = (List)(jobseeker.getJobseekerUniversityCollection());
                JobseekerUniversity university = (JobseekerUniversity)(u.get(i));
                universities.get(i).put("id",String.valueOf(university.getUniversityId()));
            }
            
            List<JobseekerEducation> educations = (List)(jobseeker.getJobseekerEducationCollection());
            JobseekerEducation e = (JobseekerEducation)(educations.get(FIRSTRECORD));
            education.put("id",String.valueOf(e.getId()));
            
            isUpdateSuccess = false;
            jobSeekerManagement.updateEducationalProfile(alResults, olResults , universities, education, userId);
            isUpdateSuccess = true;
            return UPDATE;
        } 
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
