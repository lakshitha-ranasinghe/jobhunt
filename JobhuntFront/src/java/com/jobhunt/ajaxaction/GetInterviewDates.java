/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.ajaxaction;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.entity.VacancyApply;
import com.jobhunt.remote.VacancyRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lakshitha
 */
public class GetInterviewDates extends ActionSupport {

    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    private Integer year;
    private Integer month;
    private Integer day;
    List<String> interviewsToday;
    Map<String,Date> allInterviewDates;
    Map<String,Date> allClosingDates;

    public Map<String, Date> getAllClosingDates() {
        return allClosingDates;
    }

    public void setAllClosingDates(Map<String, Date> allClosingDates) {
        this.allClosingDates = allClosingDates;
    }

    public Map<String, Date> getAllInterviewDates() {
        return allInterviewDates;
    }

    public void setAllInterviewDates(Map<String, Date> allInterviewDates) {
        this.allInterviewDates = allInterviewDates;
    }

    public List<String> getInterviewsToday() {
        return interviewsToday;
    }

    public void setInterviewsToday(List<String> interviewsToday) {
        this.interviewsToday = interviewsToday;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public GetInterviewDates() {
    }

    public String companyInterviews() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();

        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);
        Date today = cal.getTime();

        List<VacancyApply> vacancyApplys = vacancyRemote.findVacancyByDateAndID(today);
        Set<Vacancy> interviews = new HashSet<>();
        if (vacancyApplys != null) {
            for (VacancyApply vacancyApply : vacancyApplys) {
                if (vacancyApply.getVacancyId().getCompany().getId().equals(userId)) {
                    interviews.add(vacancyApply.getVacancyId());
                }
            }
        }
        interviewsToday = new ArrayList<>();
        for (Vacancy vacancy : interviews) {
            interviewsToday.add(vacancy.getTitle() + "-" + vacancy.getVacancyCount() + "-" + vacancy.getClosingDate() + "-" + vacancy.getVacancyApplyCollection().size());
        }
        return SUCCESS;
    }

    public String allInterviews() {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();
        List<VacancyApply> vacancyApplys = vacancyRemote.findAllVacancyApply();
        allInterviewDates = new HashMap<>();
        if (vacancyApplys != null) {
            for (VacancyApply vacancyApply : vacancyApplys) {
                if (vacancyApply.getVacancyId().getCompany().getId().equals(userId)) {
                    Vacancy vacancy = vacancyApply.getVacancyId();
                    allInterviewDates.put(vacancy.getId()+"-"+vacancy.getTitle()+" Interview", vacancyApply.getInterviewDate());
                }
            }
        }
        
        allClosingDates = new HashMap<>();
        List<Vacancy> vacancies = vacancyRemote.findVacancyByCompanyId(userId);
        for(Vacancy vacancy : vacancies){
            allClosingDates.put(vacancy.getId()+"-"+vacancy.getTitle()+" Closing Date", vacancy.getClosingDate());
        }

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
}
