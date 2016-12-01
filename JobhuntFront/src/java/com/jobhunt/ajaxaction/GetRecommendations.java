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
public class GetRecommendations extends ActionSupport {

    private List<Vacancy> vacancies;
    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();
    private String preferredJob;
    private String preferredArea;
    private String expectedSalary;
    private List<Integer> appliedVacancyIds;

    public List<Integer> getAppliedVacancyIds() {
        return appliedVacancyIds;
    }

    public void setAppliedVacancyIds(List<Integer> appliedVacancyIds) {
        this.appliedVacancyIds = appliedVacancyIds;
    }

    public String getPreferredJob() {
        return preferredJob;
    }

    public void setPreferredJob(String preferredJob) {
        this.preferredJob = preferredJob;
    }

    public String getPreferredArea() {
        return preferredArea;
    }

    public void setPreferredArea(String preferredArea) {
        this.preferredArea = preferredArea;
    }

    public String getExpectedSalary() {
        return expectedSalary;
    }

    public void setExpectedSalary(String expectedSalary) {
        this.expectedSalary = expectedSalary;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public GetRecommendations() {
    }

    public String execute() throws Exception {
        String job = getNewJob(preferredJob);
        String area = getNewArea(preferredArea);
        String salary = getNewSalary(expectedSalary);

        appliedVacancyIds = new ArrayList<>();
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();
        
        Jobseeker jobseeker = jobSeekerManagement.findPersonalProfile(userId);
        List<VacancyApply> jobseekerAppliedVacancies = (List) jobseeker.getVacancyApplyCollection();
        for (VacancyApply vacancyApply : jobseekerAppliedVacancies) {
            appliedVacancyIds.add(vacancyApply.getVacancyId().getId());
        }

        vacancies = vacancyRemote.findRecommendedVacancies(job, area, salary);
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

    private JobseekerRemote lookupJobseekerManagementRemote() {
        try {
            Context c = new InitialContext();
            return (JobseekerRemote) c.lookup("java:global/JobhuntEJB/JobseekerBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private String getNewJob(String job) {
        if (job.equals("Not Specified")) {
            return "%";
        } else {
            return job;
        }
    }

    private String getNewSalary(String salary) {
        if (expectedSalary.equals("Not Specified")) {
            return "like '%'";
        } else {
            switch (expectedSalary) {
                case "10000 - 30000":
                    return "between 10000 AND 30000";
                case "30000 - 60000":
                    return "between 30000 AND 60000";
                case "60000 - 100000":
                    return "between 60000 AND 100000";
                case "100000 - 150000":
                    return "between 100000 AND 150000";
                case "More than 150000":
                    return "> 15000";
            }
            return null;
        }
    }

    private String getNewArea(String area) {
        if (area.equals("Not Specified")) {
            return "%";
        } else {
            return area;
        }
    }
}
