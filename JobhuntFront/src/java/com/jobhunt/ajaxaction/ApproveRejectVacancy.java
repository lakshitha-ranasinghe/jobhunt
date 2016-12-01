/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.ajaxaction;

import com.jobhunt.remote.VacancyRemote;
import com.opensymphony.xwork2.ActionSupport;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lakshitha
 */
public class ApproveRejectVacancy extends ActionSupport {

    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    private Integer applicantId;
    private Integer vacancyId;
    private Boolean isApproved;
    private Boolean isRejected;
    private String status;
    private static final Integer REJECTED = 2;
    private static final Integer APPROVED = 0;
    private static final Integer PENDING = 1;
    private String interviewDate;

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public Boolean getIsRejected() {
        return isRejected;
    }

    public void setIsRejected(Boolean isRejected) {
        this.isRejected = isRejected;
    }

    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }

    public ApproveRejectVacancy() {
    }

    @Override
    public String execute() throws Exception {
        Date interview = null;
        if (interviewDate != null) {
            String[] dateParts = interviewDate.trim().split("-");
            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(dateParts[0]), (Integer.parseInt(dateParts[1]) - 1), Integer.parseInt(dateParts[2]));
            interview = cal.getTime();
        }
        isApproved = false;
        isRejected = false;
        if (status.equals("reject")) {
            vacancyRemote.changeApplyVacancyStatus(applicantId, vacancyId, REJECTED, new Date());
            isRejected = true;
        } else if (status.equals("approve")) {
            vacancyRemote.changeApplyVacancyStatus(applicantId, vacancyId, APPROVED, interview);
            isApproved = true;
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
