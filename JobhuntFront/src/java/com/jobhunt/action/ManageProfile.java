/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.remote.JobseekerRemote;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
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
public class ManageProfile extends ActionSupport {
    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();
    private Boolean isUpdateSuccess;
    private static final String UPDATE = "update";
    private Jobseeker jobseeker;

    public Jobseeker getJobseeker() {
        return jobseeker;
    }

    public Boolean getIsUpdateSuccess() {
        return isUpdateSuccess;
    }

    public void setIsUpdateSuccess(Boolean isUpdateSuccess) {
        this.isUpdateSuccess = isUpdateSuccess;
    }
    
    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
    }

    public ManageProfile() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser)session.get("user");
        Integer userId = user.getId();
        jobseeker.setId(userId);
        
        Map<String,String> jobseekerPersonalData = new HashMap<>();
        jobseekerPersonalData.put("id", String.valueOf(userId));
        jobseekerPersonalData.put("firstName", jobseeker.getFirstName());
        jobseekerPersonalData.put("lastName", jobseeker.getLastName());
        jobseekerPersonalData.put("title", jobseeker.getTitle());
        jobseekerPersonalData.put("address1", jobseeker.getAddress1());
        jobseekerPersonalData.put("address2", jobseeker.getAddress2());
        jobseekerPersonalData.put("address3", jobseeker.getAddress3());
        jobseekerPersonalData.put("email", jobseeker.getEmail());
        jobseekerPersonalData.put("telephone", String.valueOf(jobseeker.getTelephone()));
        jobseekerPersonalData.put("mobile", String.valueOf(jobseeker.getMobile()));
        jobseekerPersonalData.put("qualifiedField", jobseeker.getQualifiedField());
        jobseekerPersonalData.put("lastJob", jobseeker.getLastJob());
        jobseekerPersonalData.put("expectedJob", jobseeker.getExpectedJob());
        
        Jobseeker jobseekerObject = jobSeekerManagement.findPersonalProfile(userId);
        if(jobseekerObject == null){
            isUpdateSuccess = false;
            jobSeekerManagement.createPersonalProfile(jobseekerPersonalData, jobseeker.getBirthday()); 
            isUpdateSuccess = true;
            return SUCCESS;
        }
        else{
            isUpdateSuccess = false;
            jobSeekerManagement.updatePersonalProfle(jobseekerPersonalData, userId, jobseeker.getBirthday());
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
