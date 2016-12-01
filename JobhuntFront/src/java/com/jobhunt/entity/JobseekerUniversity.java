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
@Table(name = "jobseeker_university")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobseekerUniversity.findAll", query = "SELECT j FROM JobseekerUniversity j"),
    @NamedQuery(name = "JobseekerUniversity.findByUniversityId", query = "SELECT j FROM JobseekerUniversity j WHERE j.universityId = :universityId"),
    @NamedQuery(name = "JobseekerUniversity.findByName", query = "SELECT j FROM JobseekerUniversity j WHERE j.name = :name"),
    @NamedQuery(name = "JobseekerUniversity.findByCourse", query = "SELECT j FROM JobseekerUniversity j WHERE j.course = :course"),
    @NamedQuery(name = "JobseekerUniversity.findByCompletedYear", query = "SELECT j FROM JobseekerUniversity j WHERE j.completedYear = :completedYear"),
    @NamedQuery(name = "JobseekerUniversity.findByGpa", query = "SELECT j FROM JobseekerUniversity j WHERE j.gpa = :gpa")})
public class JobseekerUniversity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "university_id")
    private Integer universityId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "course")
    private String course;
    @Basic(optional = false)
    @NotNull
    @Column(name = "completed_year")
    private int completedYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gpa")
    private double gpa;
    @JoinColumn(name = "jobseeker_education", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jobseeker jobseekerEducation;

    public JobseekerUniversity() {
    }

    public JobseekerUniversity(Integer universityId) {
        this.universityId = universityId;
    }

    public JobseekerUniversity(Integer universityId, String name, String course, int completedYear, double gpa) {
        this.universityId = universityId;
        this.name = name;
        this.course = course;
        this.completedYear = completedYear;
        this.gpa = gpa;
    }

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getCompletedYear() {
        return completedYear;
    }

    public void setCompletedYear(int completedYear) {
        this.completedYear = completedYear;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
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
        hash += (universityId != null ? universityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobseekerUniversity)) {
            return false;
        }
        JobseekerUniversity other = (JobseekerUniversity) object;
        if ((this.universityId == null && other.universityId != null) || (this.universityId != null && !this.universityId.equals(other.universityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jobhunt.entity.JobseekerUniversity[ universityId=" + universityId + " ]";
    }
    
}
