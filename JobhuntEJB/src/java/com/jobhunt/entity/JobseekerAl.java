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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lakshitha
 */
@Entity
@Table(name = "jobseeker_al")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobseekerAl.findAll", query = "SELECT j FROM JobseekerAl j"),
    @NamedQuery(name = "JobseekerAl.findByAlId", query = "SELECT j FROM JobseekerAl j WHERE j.alId = :alId"),
    @NamedQuery(name = "JobseekerAl.findBySubject", query = "SELECT j FROM JobseekerAl j WHERE j.subject = :subject"),
    @NamedQuery(name = "JobseekerAl.findByMark", query = "SELECT j FROM JobseekerAl j WHERE j.mark = :mark")})
public class JobseekerAl implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "al_generator",
            table = "id_generator",
            pkColumnName = "gen_name",
            valueColumnName = "gen_value",
            pkColumnValue = "al",
            allocationSize = 1)
    @Id
    @GeneratedValue(generator = "al_generator")
    @Basic(optional = false)
    @NotNull
    @Column(name = "al_id")
    private Integer alId;
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

    public JobseekerAl() {
    }

    public JobseekerAl(Integer alId) {
        this.alId = alId;
    }

    public JobseekerAl(Integer alId, String subject, String mark) {
        this.alId = alId;
        this.subject = subject;
        this.mark = mark;
    }

    public Integer getAlId() {
        return alId;
    }

    public void setAlId(Integer alId) {
        this.alId = alId;
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
        hash += (alId != null ? alId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobseekerAl)) {
            return false;
        }
        JobseekerAl other = (JobseekerAl) object;
        if ((this.alId == null && other.alId != null) || (this.alId != null && !this.alId.equals(other.alId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jobhunt.entity.JobseekerAl[ alId=" + alId + " ]";
    }
    
}
