/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.ajaxaction;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.CompanyProfile;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.UserCache;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.entity.VacancyApply;
import com.jobhunt.entity.VacancyCache;
import com.jobhunt.remote.ApplicationUserRemote;
import com.jobhunt.remote.CompanyRemote;
import com.jobhunt.remote.JobseekerRemote;
import com.jobhunt.remote.VacancyRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lakshitha
 */
public class GetDashboardData extends ActionSupport {

    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();
    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    private final CompanyRemote companyRemote = lookupCompanyManagementRemote();
    private final ApplicationUserRemote applicationUserRemote = lookupApplicationUserManagementRemote();
    private Integer approved;
    private Integer rejected;
    private Integer pending;
    private String userName;
    private Integer newVacancySize;
    private Integer upcomingInterviewSize;

    private List<Vacancy> newVacancyList;
    private Map<String, String> vacancyWithDate;
    private Map<String, String> pendingApplicants;

    public Map<String, String> getPendingApplicants() {
        return pendingApplicants;
    }

    public void setPendingApplicants(Map<String, String> pendingApplicants) {
        this.pendingApplicants = pendingApplicants;
    }

    public Integer getUpcomingInterviewSize() {
        return upcomingInterviewSize;
    }

    public void setUpcomingInterviewSize(Integer upcomingInterviewSize) {
        this.upcomingInterviewSize = upcomingInterviewSize;
    }

    public Integer getNewVacancySize() {
        return newVacancySize;
    }

    public void setNewVacancySize(Integer newVacancySize) {
        this.newVacancySize = newVacancySize;
    }

    public List<Vacancy> getNewVacancyList() {
        return newVacancyList;
    }

    public void setNewVacancyList(List<Vacancy> newVacancyList) {
        this.newVacancyList = newVacancyList;
    }

    public Map<String, String> getVacancyWithDate() {
        return vacancyWithDate;
    }

    public void setVacancyWithDate(Map<String, String> vacancyWithDate) {
        this.vacancyWithDate = vacancyWithDate;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public Integer getRejected() {
        return rejected;
    }

    public void setRejected(Integer rejected) {
        this.rejected = rejected;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public GetDashboardData() {
    }

    public String getTopNavData() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();
        Jobseeker jobseeker = jobSeekerManagement.findPersonalProfile(userId);
        if (jobseeker != null) {
            userName = jobseeker.getFirstName();
        } else {
            userName = user.getName();
        }

        return SUCCESS;
    }

    public String companyNameForTopNav() {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();

        CompanyProfile companyProfile = companyRemote.findCompanyProfile(userId);
        if (companyProfile != null) {
            userName = companyProfile.getName();
        } else {
            userName = user.getName();
        }
        return SUCCESS;
    }

    public String approvalsAndRejectsForSeeker() {
        System.out.println("Finding Aprrovals and Rejects for Seeker Dashboard");
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();

        approved = 0;
        rejected = 0;
        pending = 0;

        List<VacancyApply> vacanciesApplied = vacancyRemote.findVacancyApplyByJobseeker(userId);
        if (vacanciesApplied == null) {
            return SUCCESS;
        }
        for (VacancyApply vacancyApply : vacanciesApplied) {
            if (vacancyApply.getStatus() == 0) {
                approved++;
            } else if (vacancyApply.getStatus() == 1) {
                pending++;
            } else {
                rejected++;
            }
        }

        return SUCCESS;
    }

    public String vacancyInformationForJobseeker() {
        List<VacancyCache> vacanyCacheData = vacancyRemote.findAllVacancyCache();
        Collections.sort(vacanyCacheData, (VacancyCache o1, VacancyCache o2) -> o2.getPostedDate().compareTo(o1.getPostedDate()));

        newVacancyList = new ArrayList<>();
        vacancyWithDate = new LinkedHashMap<>();
        newVacancySize = 0;

        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();

        UserCache userCache = applicationUserRemote.findUserCache(userId);
        for (VacancyCache vacancyCache : vacanyCacheData) {
            Vacancy vacancy = vacancyRemote.findVacany(vacancyCache.getId());
            if (userCache.getLastLogin().compareTo(vacancyCache.getPostedDate()) <= 0) {
                newVacancyList.add(vacancy);
                newVacancySize++;
            }
            long difference = new Date().getTime() - vacancyCache.getPostedDate().getTime();
            long days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            if (days == 0) {
                long hours = TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS);
                if (hours == 0) {
                    long minutes = TimeUnit.MINUTES.convert(difference, TimeUnit.MILLISECONDS);
                    vacancyWithDate.put(vacancy.getTitle() + "-" + vacancy.getId(), String.valueOf(minutes) + " minutes ago");
                } else {
                    vacancyWithDate.put(vacancy.getTitle() + "-" + vacancy.getId(), String.valueOf(hours) + " hours ago");
                }
            } else {
                vacancyWithDate.put(vacancy.getTitle() + "-" + vacancy.getId(), String.valueOf(days) + " days ago");
            }
        }
        return SUCCESS;
    }

    public String upcomingDetailsForCompany() {
        System.out.println("Finding Aprrovals and Rejects for Company Dashboard");
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();

        List<VacancyApply> pendingVacancyApplies = new ArrayList<>();

        approved = 0;
        rejected = 0;
        pending = 0;
        upcomingInterviewSize = 0;

        List<Vacancy> vacancies = vacancyRemote.findVacancyByCompanyId(userId);
        for (Vacancy vacancy : vacancies) {
            if (vacancy.getClosingDate().compareTo(new Date()) >= 0) {
                upcomingInterviewSize++;
                List<VacancyApply> vacancyApplies = (List<VacancyApply>) vacancy.getVacancyApplyCollection();
                for (VacancyApply vacancyApply : vacancyApplies) {
                    if (vacancyApply.getStatus() == 0) {
                        approved++;
                    } else if (vacancyApply.getStatus() == 1) {
                        pendingVacancyApplies.add(vacancyApply);
                        pending++;
                    } else {
                        rejected++;
                    }
                }
            }
        }
        
        
        pendingApplicants = new LinkedHashMap<>();
        Collections.sort(pendingVacancyApplies, new Comparator<VacancyApply>() {

            public int compare(VacancyApply o1, VacancyApply o2) {
                return o2.getInterviewDate().compareTo(o1.getInterviewDate());
            }
        });
        
        System.out.println(pendingVacancyApplies);
        for (VacancyApply vacancyApply : pendingVacancyApplies) {
            Jobseeker jobseeker = vacancyApply.getJobseekerId();
            long difference = new Date().getTime() - vacancyApply.getInterviewDate().getTime();
            long days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            if (days == 0) {
                long hours = TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS);
                if (hours == 0) {
                    long minutes = TimeUnit.MINUTES.convert(difference, TimeUnit.MILLISECONDS);
                    pendingApplicants.put(jobseeker.getFirstName()+" "+ jobseeker.getLastName() + " requested for "+vacancyApply.getVacancyId().getTitle() + " position" + "-" + vacancyApply.getId(), String.valueOf(minutes) + " minutes ago");
                } else {
                    pendingApplicants.put(jobseeker.getFirstName()+" "+ jobseeker.getLastName() + " requested for "+vacancyApply.getVacancyId().getTitle() + " position" + "-" + vacancyApply.getId(), String.valueOf(hours) + " hours ago");
                }
            } else {
                pendingApplicants.put(jobseeker.getFirstName()+" "+ jobseeker.getLastName() + " requested for "+vacancyApply.getVacancyId().getTitle() + " position" + "-" + vacancyApply.getId(), String.valueOf(days) + " days ago");
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

    private CompanyRemote lookupCompanyManagementRemote() {
        try {
            Context c = new InitialContext();
            return (CompanyRemote) c.lookup("java:global/JobhuntEJB/CompanyBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ApplicationUserRemote lookupApplicationUserManagementRemote() {
        try {
            Context c = new InitialContext();
            return (ApplicationUserRemote) c.lookup("java:global/JobhuntEJB/ApplicationUserBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
