/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.ajaxaction;

import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.JobseekerAl;
import com.jobhunt.entity.JobseekerOl;
import com.jobhunt.entity.JobseekerUniversity;
import com.jobhunt.entity.JobseekerWorkedcompany;
import com.jobhunt.remote.JobseekerRemote;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lakshitha
 */
public class GetJobseekerProfile extends ActionSupport {

    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();
    private Integer applicantId;
    private Jobseeker jobseeker;
    private String olResultSummery;
    private String alResultSummery;
    private String uniResultSummery;
    private String professionalSummery;

    public String getOlResultSummery() {
        return olResultSummery;
    }

    public String getProfessionalSummery() {
        return professionalSummery;
    }

    public void setProfessionalSummery(String professionalSummery) {
        this.professionalSummery = professionalSummery;
    }

    public String getUniResultSummery() {
        return uniResultSummery;
    }

    public void setUniResultSummery(String uniResultSummery) {
        this.uniResultSummery = uniResultSummery;
    }

    public void setOlResultSummery(String olResultSummery) {
        this.olResultSummery = olResultSummery;
    }

    public String getAlResultSummery() {
        return alResultSummery;
    }

    public void setAlResultSummery(String alResultSummery) {
        this.alResultSummery = alResultSummery;
    }

    public Jobseeker getJobseeker() {
        return jobseeker;
    }

    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public GetJobseekerProfile() {
    }

    public String execute() throws Exception {
        jobseeker = jobSeekerManagement.findPersonalProfile(applicantId);
        List<JobseekerOl> olResult = (List<JobseekerOl>) jobseeker.getJobseekerOlCollection();
        List<JobseekerAl> alResult = (List<JobseekerAl>) jobseeker.getJobseekerAlCollection();
        List<JobseekerUniversity> universityResult = (List<JobseekerUniversity>) jobseeker.getJobseekerUniversityCollection();
        List<JobseekerWorkedcompany> workedCompanies = (List<JobseekerWorkedcompany>) jobseeker.getJobseekerWorkedcompanyCollection();

        Map<String, Integer> olSummeryCount = new HashMap<>();
        olSummeryCount.put("A", 0);
        olSummeryCount.put("B", 0);
        olSummeryCount.put("C", 0);
        olSummeryCount.put("S", 0);
        olSummeryCount.put("F", 0);
        for (JobseekerOl ol : olResult) {
            switch (ol.getMark()) {
                case "A": {
                    int count = olSummeryCount.get("A");
                    olSummeryCount.put("A", ++count);
                    break;
                }
                case "B": {
                    int count = olSummeryCount.get("B");
                    olSummeryCount.put("B", ++count);
                    break;
                }
                case "C": {
                    int count = olSummeryCount.get("C");
                    olSummeryCount.put("C", ++count);
                    break;
                }
                case "S": {
                    int count = olSummeryCount.get("S");
                    olSummeryCount.put("S", ++count);
                    break;
                }
                case "F": {
                    int count = olSummeryCount.get("F");
                    olSummeryCount.put("F", ++count);
                    break;
                }
            }
        }
        olResultSummery = "";
        for (Entry<String, Integer> e : olSummeryCount.entrySet()) {
            String key = e.getKey();
            Integer value = e.getValue();
            if(value>0){
                olResultSummery = olResultSummery + " " + value + key + "(s)";
            }
        }

        Map<String, Integer> alSummeryCount = new HashMap<>();
        alSummeryCount.put("A", 0);
        alSummeryCount.put("B", 0);
        alSummeryCount.put("C", 0);
        alSummeryCount.put("S", 0);
        alSummeryCount.put("F", 0);
        for (JobseekerAl al : alResult) {
            switch (al.getMark()) {
                case "A": {
                    int count = alSummeryCount.get("A");
                    alSummeryCount.put("A", ++count);
                    break;
                }
                case "B": {
                    int count = alSummeryCount.get("B");
                    alSummeryCount.put("B", ++count);
                    break;
                }
                case "C": {
                    int count = alSummeryCount.get("C");
                    alSummeryCount.put("C", ++count);
                    break;
                }
                case "S": {
                    int count = alSummeryCount.get("S");
                    alSummeryCount.put("S", ++count);
                    break;
                }
                case "F": {
                    int count = alSummeryCount.get("F");
                    alSummeryCount.put("F", ++count);
                    break;
                }
            }
        }
        alResultSummery = "";
        for (Entry<String, Integer> e : alSummeryCount.entrySet()) {
            String key = e.getKey();
            Integer value = e.getValue();
            if(value>0){
                alResultSummery = alResultSummery + " " + value + key + "(s)";
            }
        }
        
        uniResultSummery = "";
        boolean isUniResultFound = false;
        for(JobseekerUniversity uni : universityResult){
            if(!uni.getName().equals("Not Specified")){
                isUniResultFound = true;
                uniResultSummery = uniResultSummery + "Completed " + uni.getCourse() + " at " + uni.getName() + "<br>";
            }
        }
        if(isUniResultFound==false){
            uniResultSummery = "Not Specified";
        }
        
        professionalSummery = "";
        boolean isExperienceFound = false;
        for(JobseekerWorkedcompany jobseekerWorkedcompany : workedCompanies){
            if(!jobseekerWorkedcompany.getName().equals("Not Specified")){
                isExperienceFound = true;
                professionalSummery = professionalSummery + "Worked as a " + jobseekerWorkedcompany.getDesignation() + " at " + jobseekerWorkedcompany.getName() + "<br>";
            }
        }
        
        if(isExperienceFound==false){
            professionalSummery = "Not Specified";
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
