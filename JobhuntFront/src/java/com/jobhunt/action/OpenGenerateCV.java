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
import com.jobhunt.entity.JobseekerOther;
import com.jobhunt.entity.JobseekerUniversity;
import com.jobhunt.entity.JobseekerWorkedcompany;
import com.jobhunt.remote.JobseekerRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.DateFormat;
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
public class OpenGenerateCV extends ActionSupport {
    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();
    private Jobseeker jobseeker;
    private List<JobseekerAl> jobseekerAl;
    private List<JobseekerOl> jobseekerOl;
    private List<JobseekerUniversity> jobseekerUniversity;
    private List<JobseekerWorkedcompany> jobseekerWorkedCompanies;
    private List<JobseekerOther> jobseekerOther;
    private JobseekerEducation jobseekerEducation;
    private static final int FIRSTRECORD = 0;
    private Boolean isCompleteJobseeker;

    public Boolean getIsCompleteJobseeker() {
        return isCompleteJobseeker;
    }

    public void setIsCompleteJobseeker(Boolean isCompleteJobseeker) {
        this.isCompleteJobseeker = isCompleteJobseeker;
    }

    public List<JobseekerWorkedcompany> getJobseekerWorkedCompanies() {
        return jobseekerWorkedCompanies;
    }

    public void setJobseekerWorkedCompanies(List<JobseekerWorkedcompany> jobseekerWorkedCompanies) {
        this.jobseekerWorkedCompanies = jobseekerWorkedCompanies;
    }

    public List<JobseekerOther> getJobseekerOther() {
        return jobseekerOther;
    }

    public void setJobseekerOther(List<JobseekerOther> jobseekerOther) {
        this.jobseekerOther = jobseekerOther;
    }

    public JobseekerEducation getJobseekerEducation() {
        return jobseekerEducation;
    }

    public void setJobseekerEducation(JobseekerEducation jobseekerEducation) {
        this.jobseekerEducation = jobseekerEducation;
    }
    
    private String birthday;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<JobseekerAl> getJobseekerAl() {
        return jobseekerAl;
    }

    public void setJobseekerAl(List<JobseekerAl> jobseekerAl) {
        this.jobseekerAl = jobseekerAl;
    }

    public List<JobseekerOl> getJobseekerOl() {
        return jobseekerOl;
    }

    public void setJobseekerOl(List<JobseekerOl> jobseekerOl) {
        this.jobseekerOl = jobseekerOl;
    }

    public List<JobseekerUniversity> getJobseekerUniversity() {
        return jobseekerUniversity;
    }

    public void setJobseekerUniversity(List<JobseekerUniversity> jobseekerUniversity) {
        this.jobseekerUniversity = jobseekerUniversity;
    }

    private String nameWithInitials;

    public String getNameWithInitials() {
        return nameWithInitials;
    }

    public void setNameWithInitials(String nameWithInitials) {
        this.nameWithInitials = nameWithInitials;
    }
    
    public Jobseeker getJobseeker() {
        return jobseeker;
    }

    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
    }

    public OpenGenerateCV() {
    }
    
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser)session.get("user");
        Integer userId = user.getId();

        jobseeker = jobSeekerManagement.findPersonalProfile(userId);
        
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
                }
            }
        }
        
        nameWithInitials = jobseeker.getFirstName().charAt(0) + ". " + jobseeker.getLastName();
      
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        birthday = df.format(jobseeker.getBirthday());
        
        jobseekerOl = (List)jobseeker.getJobseekerOlCollection();
        jobseekerAl = (List)jobseeker.getJobseekerAlCollection();
        jobseekerUniversity = (List)jobseeker.getJobseekerUniversityCollection();
        jobseekerEducation = ((List<JobseekerEducation>)jobseeker.getJobseekerEducationCollection()).get(FIRSTRECORD);
        
        jobseekerWorkedCompanies = (List)jobseeker.getJobseekerWorkedcompanyCollection();
        jobseekerOther = (List)jobseeker.getJobseekerOtherCollection();
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
