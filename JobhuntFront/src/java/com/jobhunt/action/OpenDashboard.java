/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.UserCache;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.entity.VacancyCache;
import com.jobhunt.remote.ApplicationUserRemote;
import com.jobhunt.remote.VacancyRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
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
public class OpenDashboard extends ActionSupport {

    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    private final ApplicationUserRemote applicationUserRemote = lookupApplicationUserManagementRemote();
    private static final String SEEKER_SUCCESS = "seeker";
    private static final String COMPANY_SUCCESS = "company";
    private List<Vacancy> newVacancyList;
    private Map<Vacancy, String> vacancyWithDate;

    public Map<Vacancy, String> getVacancyWithDate() {
        return vacancyWithDate;
    }

    public void setVacancyWithDate(Map<Vacancy, String> vacancyWithDate) {
        this.vacancyWithDate = vacancyWithDate;
    }

    public List<Vacancy> getNewVacancyList() {
        return newVacancyList;
    }

    public void setNewVacancyList(List<Vacancy> newVacancyList) {
        this.newVacancyList = newVacancyList;
    }

    public OpenDashboard() {

    }

    public String seekerDashboard() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();

        UserCache userCache = applicationUserRemote.findUserCache(userId);
        List<VacancyCache> vacanyCacheData = vacancyRemote.findAllVacancyCache();

        newVacancyList = new ArrayList<>();
        vacancyWithDate = new TreeMap<>();

        for (VacancyCache vacancyCache : vacanyCacheData) {
            Vacancy vacancy = vacancyRemote.findVacany(vacancyCache.getId());
            if (userCache.getLastLogin().compareTo(vacancyCache.getPostedDate()) <= 0) {
                newVacancyList.add(vacancy);
            }
            long difference = new Date().getTime() - vacancyCache.getPostedDate().getTime();
            long days = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
            if (days == 0) {
                long hours = TimeUnit.HOURS.convert(difference, TimeUnit.MILLISECONDS);
                if (hours == 0) {
                    long minutes = TimeUnit.MINUTES.convert(difference, TimeUnit.MILLISECONDS);
                    vacancyWithDate.put(vacancy, String.valueOf(minutes) + " minutes ago");
                } else {
                    vacancyWithDate.put(vacancy, String.valueOf(hours) + " hours ago");
                }
            } else {
                vacancyWithDate.put(vacancy, String.valueOf(days) + " days ago");
            }
//                        }
        }

        return SEEKER_SUCCESS;
    }

    public String companyDashboard() throws Exception {
        return COMPANY_SUCCESS;
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

    private VacancyRemote lookupVacancyManagementRemote() {
        try {
            Context c = new InitialContext();
            return (VacancyRemote) c.lookup("java:global/JobhuntEJB/VacancyBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
