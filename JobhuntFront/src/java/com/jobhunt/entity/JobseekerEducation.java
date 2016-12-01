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
@Table(name = "jobseeker_education")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobseekerEducation.findAll", query = "SELECT j FROM JobseekerEducation j"),
    @NamedQuery(name = "JobseekerEducation.findById", query = "SELECT j FROM JobseekerEducation j WHERE j.id = :id"),
    @NamedQuery(name = "JobseekerEducation.findByOlYear", query = "SELECT j FROM JobseekerEducation j WHERE j.olYear = :olYear"),
    @NamedQuery(name = "JobseekerEducation.findByOlSchool", query = "SELECT j FROM JobseekerEducation j WHERE j.olSchool = :olSchool"),
    @NamedQuery(name = "JobseekerEducation.findByAlYear", query = "SELECT j FROM JobseekerEducation j WHERE j.alYear = :alYear"),
    @NamedQuery(name = "JobseekerEducation.findByAlSchool", query = "SELECT j FROM JobseekerEducation j WHERE j.alSchool = :alSchool")})
public class JobseekerEducation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ol_year")
    private int olYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ol_school")
    private String olSchool;
    @Basic(optional = false)
    @NotNull
    @Column(name = "al_year")
    private int alYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "al_school")
    private String alSchool;
    @JoinColumn(name = "jobseeker", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jobseeker jobseeker;

    public JobseekerEducation() {
    }

    public JobseekerEducation(Integer id) {
        this.id = id;
    }

    public JobseekerEducation(Integer id, int olYear, String olSchool, int alYear, String alSchool) {
        this.id = id;
        this.olYear = olYear;
        this.olSchool = olSchool;
        this.alYear = alYear;
        this.alSchool = alSchool;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getOlYear() {
        return olYear;
    }

    public void setOlYear(int olYear) {
        this.olYear = olYear;
    }

    public String getOlSchool() {
        return olSchool;
    }

    public void setOlSchool(String olSchool) {
        this.olSchool = olSchool;
    }

    public int getAlYear() {
        return alYear;
    }

    public void setAlYear(int alYear) {
        this.alYear = alYear;
    }

    public String getAlSchool() {
        return alSchool;
    }

    public void setAlSchool(String alSchool) {
        this.alSchool = alSchool;
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
        if (!(object instanceof JobseekerEducation)) {
            return false;
        }
        JobseekerEducation other = (JobseekerEducation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jobhunt.entity.JobseekerEducation[ id=" + id + " ]";
    }
    
}
