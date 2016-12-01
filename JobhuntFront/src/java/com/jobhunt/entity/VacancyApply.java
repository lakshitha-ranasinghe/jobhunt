/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lakshitha
 */
@Entity
@Table(name = "vacancy_apply")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VacancyApply.findAll", query = "SELECT v FROM VacancyApply v"),
    @NamedQuery(name = "VacancyApply.findById", query = "SELECT v FROM VacancyApply v WHERE v.id = :id"),
    @NamedQuery(name = "VacancyApply.findByStatus", query = "SELECT v FROM VacancyApply v WHERE v.status = :status"),
    @NamedQuery(name = "VacancyApply.findByInterviewDate", query = "SELECT v FROM VacancyApply v WHERE v.interviewDate = :interviewDate")})
public class VacancyApply implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "interview_date")
    @Temporal(TemporalType.DATE)
    private Date interviewDate;
    @JoinColumn(name = "vacancy_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vacancy vacancyId;
    @JoinColumn(name = "jobseeker_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jobseeker jobseekerId;

    public VacancyApply() {
    }

    public VacancyApply(Integer id) {
        this.id = id;
    }

    public VacancyApply(Integer id, int status, Date interviewDate) {
        this.id = id;
        this.status = status;
        this.interviewDate = interviewDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Vacancy getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Vacancy vacancyId) {
        this.vacancyId = vacancyId;
    }

    public Jobseeker getJobseekerId() {
        return jobseekerId;
    }

    public void setJobseekerId(Jobseeker jobseekerId) {
        this.jobseekerId = jobseekerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VacancyApply)) {
            return false;
        }
        VacancyApply other = (VacancyApply) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jobhunt.entity.VacancyApply[ id=" + id + " ]";
    }
    
}
