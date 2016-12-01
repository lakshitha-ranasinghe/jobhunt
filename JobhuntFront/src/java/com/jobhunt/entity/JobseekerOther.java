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
@Table(name = "jobseeker_other")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobseekerOther.findAll", query = "SELECT j FROM JobseekerOther j"),
    @NamedQuery(name = "JobseekerOther.findById", query = "SELECT j FROM JobseekerOther j WHERE j.id = :id"),
    @NamedQuery(name = "JobseekerOther.findByTitle", query = "SELECT j FROM JobseekerOther j WHERE j.title = :title"),
    @NamedQuery(name = "JobseekerOther.findByPublishedYear", query = "SELECT j FROM JobseekerOther j WHERE j.publishedYear = :publishedYear"),
    @NamedQuery(name = "JobseekerOther.findByDetails", query = "SELECT j FROM JobseekerOther j WHERE j.details = :details")})
public class JobseekerOther implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "published_year")
    private int publishedYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "details")
    private String details;
    @JoinColumn(name = "jobseeker", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jobseeker jobseeker;

    public JobseekerOther() {
    }

    public JobseekerOther(Integer id) {
        this.id = id;
    }

    public JobseekerOther(Integer id, String title, int publishedYear, String details) {
        this.id = id;
        this.title = title;
        this.publishedYear = publishedYear;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Jobseeker getJobseeker() {
        return jobseeker;
    }

    public void setJobseeker(Jobseeker jobseeker) {
        this.jobseeker = jobseeker;
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
        if (!(object instanceof JobseekerOther)) {
            return false;
        }
        JobseekerOther other = (JobseekerOther) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jobhunt.entity.JobseekerOther[ id=" + id + " ]";
    }
    
}
