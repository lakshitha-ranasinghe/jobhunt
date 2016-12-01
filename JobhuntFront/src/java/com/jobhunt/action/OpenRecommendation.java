/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.remote.JobseekerRemote;
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
public class OpenRecommendation extends ActionSupport {

    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();
    private String lastJob;
    private String interestedField;
    private String qualifiedField;
    private Boolean isCompleteJobseeker;

    public Boolean getIsCompleteJobseeker() {
        return isCompleteJobseeker;
    }

    public void setIsCompleteJobseeker(Boolean isCompleteJobseeker) {
        this.isCompleteJobseeker = isCompleteJobseeker;
    }

    public String getLastJob() {
        return lastJob;
    }

    public void setLastJob(String lastJob) {
        this.lastJob = lastJob;
    }

    public String getInterestedField() {
        return interestedField;
    }

    public void setInterestedField(String interestedField) {
        this.interestedField = interestedField;
    }

    public String getQualifiedField() {
        return qualifiedField;
    }

    public void setQualifiedField(String qualifiedField) {
        this.qualifiedField = qualifiedField;
    }

    public OpenRecommendation() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();
        Jobseeker jobseeker = jobSeekerManagement.findPersonalProfile(userId);
        if (jobseeker == null) {
            isCompleteJobseeker = false;
            return ERROR;
        } else {
            if (jobseeker.getJobseekerOlCollection() == null || jobseeker.getJobseekerOlCollection().isEmpty()) {
                isCompleteJobseeker = false;
                return ERROR;
            } else {
                if (jobseeker.getJobseekerWorkedcompanyCollection() == null || jobseeker.getJobseekerWorkedcompanyCollection().isEmpty()) {
                    isCompleteJobseeker = false;
                    return ERROR;
                } else {
                    isCompleteJobseeker = true;
                    return SUCCESS;
                }
            }
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
