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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lakshitha
 */
@Entity
@Table(name = "jobseeker_experience")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobseekerExperience.findAll", query = "SELECT j FROM JobseekerExperience j"),
    @NamedQuery(name = "JobseekerExperience.findById", query = "SELECT j FROM JobseekerExperience j WHERE j.id = :id"),
    @NamedQuery(name = "JobseekerExperience.findByTotalYears", query = "SELECT j FROM JobseekerExperience j WHERE j.totalYears = :totalYears")})
public class JobseekerExperience implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "ex_generator",
            table = "id_generator",
            pkColumnName = "gen_name",
            valueColumnName = "gen_value",
            pkColumnValue = "education",
            allocationSize = 1)
    @Id
    @GeneratedValue(generator = "ex_generator")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_years")
    private int totalYears;
    @JoinColumn(name = "jobseeker", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jobseeker jobseeker;

    public JobseekerExperience() {
    }

    public JobseekerExperience(Integer id) {
        this.id = id;
    }

    public JobseekerExperience(Integer id, int totalYears) {
        this.id = id;
        this.totalYears = totalYears;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTotalYears() {
        return totalYears;
    }

    public void setTotalYears(int totalYears) {
        this.totalYears = totalYears;
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
        if (!(object instanceof JobseekerExperience)) {
            return false;
        }
        JobseekerExperience other = (JobseekerExperience) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jobhunt.entity.JobseekerExperience[ id=" + id + " ]";
    }
    
}
