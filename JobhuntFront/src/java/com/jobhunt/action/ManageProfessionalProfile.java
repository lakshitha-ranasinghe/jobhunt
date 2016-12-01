/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.JobseekerExperience;
import com.jobhunt.entity.JobseekerOther;
import com.jobhunt.entity.JobseekerWorkedcompany;
import com.jobhunt.remote.JobseekerRemote;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
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
public class ManageProfessionalProfile extends ActionSupport {

    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();

    private List<JobseekerOther> otherQualifications;
    private List<JobseekerWorkedcompany> workedCompanies;
    private JobseekerExperience experience;
    private Jobseeker jobseeker;
    private final static String UPDATE = "update";
    private final static int FIRSTRECORD = 0;
    private Boolean isUpdateSuccess;

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

    public List<JobseekerOther> getOtherQualifications() {
        return otherQualifications;
    }

    public void setOtherQualifications(List<JobseekerOther> otherQualifications) {
        this.otherQualifications = otherQualifications;
    }

    public List<JobseekerWorkedcompany> getWorkedCompanies() {
        return workedCompanies;
    }

    public void setWorkedCompanies(List<JobseekerWorkedcompany> workedCompanies) {
        this.workedCompanies = workedCompanies;
    }

    public JobseekerExperience getExperience() {
        return experience;
    }

    public void setExperience(JobseekerExperience experience) {
        this.experience = experience;
    }

    public ManageProfessionalProfile() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();

        Jobseeker seeker = jobSeekerManagement.findPersonalProfile(userId);
        if (seeker != null) {
            jobseeker = seeker;
        }

        List<Map<String, String>> remoteWorkedCompanies = new ArrayList<>();
        List<Map<String, String>> remoteOther = new ArrayList<>();
        List<Map<String, Date>> remoteWorkedDurations = new ArrayList<>();

        for (JobseekerWorkedcompany jobseekerWorkedcompany : workedCompanies) {
            Map<String, String> companies = new HashMap<>();
            Map<String, Date> durations = new HashMap<>();

            companies.put("name", jobseekerWorkedcompany.getName());
            companies.put("designation", jobseekerWorkedcompany.getDesignation());
            durations.put("start", jobseekerWorkedcompany.getStartDate());
            durations.put("end", jobseekerWorkedcompany.getEndDate());

            remoteWorkedCompanies.add(companies);
            remoteWorkedDurations.add(durations);
        }


        
        for (JobseekerOther jobseekerOther : otherQualifications) {
            Map<String, String> other = new HashMap<>();
            other.put("title", jobseekerOther.getTitle());
            other.put("publishedYear", String.valueOf(jobseekerOther.getPublishedYear()));
            other.put("details", jobseekerOther.getDetails());
            remoteOther.add(other);
        }
        
        int totalExperience = experience.getTotalYears();
        
        if (jobseeker.getJobseekerWorkedcompanyCollection().isEmpty()) {
            System.out.println("New");
            isUpdateSuccess = false;
            jobSeekerManagement.createProfessionalProfile(remoteWorkedCompanies, remoteOther, remoteWorkedDurations, userId, totalExperience);
            isUpdateSuccess = true;
            return SUCCESS;
        } else {
            for (int i = 0; i < workedCompanies.size(); i++) {
                List<JobseekerWorkedcompany> companies = (List) jobseeker.getJobseekerWorkedcompanyCollection();
                JobseekerWorkedcompany company = companies.get(i);
                remoteWorkedCompanies.get(i).put("id",String.valueOf(company.getId()));
            }
            for (int i = 0; i < otherQualifications.size(); i++) {
                List<JobseekerOther> others = (List) (jobseeker.getJobseekerOtherCollection());
                JobseekerOther other = (JobseekerOther) (others.get(i));
                remoteOther.get(i).put("id", String.valueOf(other.getId()));
            }
            List<JobseekerExperience> experiences = (List) (jobseeker.getJobseekerExperienceCollection());
            JobseekerExperience exp = (JobseekerExperience) (experiences.get(FIRSTRECORD));
            int expId = exp.getId();
            isUpdateSuccess = false;
            jobSeekerManagement.updateProfessionalProfile(remoteWorkedCompanies, remoteOther, remoteWorkedDurations, userId, totalExperience, expId);
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
