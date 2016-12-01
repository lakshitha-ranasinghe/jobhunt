/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lakshitha
 */
@Entity
@Table(name = "jobseeker_ol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobseekerOl.findAll", query = "SELECT j FROM JobseekerOl j"),
    @NamedQuery(name = "JobseekerOl.findByOlId", query = "SELECT j FROM JobseekerOl j WHERE j.olId = :olId"),
    @NamedQuery(name = "JobseekerOl.findBySubject", query = "SELECT j FROM JobseekerOl j WHERE j.subject = :subject"),
    @NamedQuery(name = "JobseekerOl.findByMark", query = "SELECT j FROM JobseekerOl j WHERE j.mark = :mark")})
public class JobseekerOl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ol_id")
    private Integer olId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "subject")
    private String subject;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "mark")
    private String mark;
    @JoinColumn(name = "jobseeker_education", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jobseeker jobseekerEducation;

    public JobseekerOl() {
    }

    public JobseekerOl(Integer olId) {
        this.olId = olId;
    }

    public JobseekerOl(Integer olId, String subject, String mark) {
        this.olId = olId;
        this.subject = subject;
        this.mark = mark;
    }

    public Integer getOlId() {
        return olId;
    }

    public void setOlId(Integer olId) {
        this.olId = olId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Jobseeker getJobseekerEducation() {
        return jobseekerEducation;
    }

    public void setJobseekerEducation(Jobseeker jobseekerEducation) {
        this.jobseekerEducation = jobseekerEducation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (olId != null ? olId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobseekerOl)) {
            return false;
        }
        JobseekerOl other = (JobseekerOl) object;
        if ((this.olId == null && other.olId != null) || (this.olId != null && !this.olId.equals(other.olId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return mark;
    }
}
