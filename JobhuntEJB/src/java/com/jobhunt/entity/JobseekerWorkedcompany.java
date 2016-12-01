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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lakshitha
 */
@Entity
@Table(name = "jobseeker_workedcompany")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobseekerWorkedcompany.findAll", query = "SELECT j FROM JobseekerWorkedcompany j"),
    @NamedQuery(name = "JobseekerWorkedcompany.findById", query = "SELECT j FROM JobseekerWorkedcompany j WHERE j.id = :id"),
    @NamedQuery(name = "JobseekerWorkedcompany.findByName", query = "SELECT j FROM JobseekerWorkedcompany j WHERE j.name = :name"),
    @NamedQuery(name = "JobseekerWorkedcompany.findByStartDate", query = "SELECT j FROM JobseekerWorkedcompany j WHERE j.startDate = :startDate"),
    @NamedQuery(name = "JobseekerWorkedcompany.findByEndDate", query = "SELECT j FROM JobseekerWorkedcompany j WHERE j.endDate = :endDate"),
    @NamedQuery(name = "JobseekerWorkedcompany.findByDesignation", query = "SELECT j FROM JobseekerWorkedcompany j WHERE j.designation = :designation")})
public class JobseekerWorkedcompany implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "workedcompany_generator",
            table = "id_generator",
            pkColumnName = "gen_name",
            valueColumnName = "gen_value",
            pkColumnValue = "companyworked",
            allocationSize = 1)
    @Id
    @GeneratedValue(generator = "workedcompany_generator")
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "designation")
    private String designation;
    @JoinColumn(name = "jobseeker", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Jobseeker jobseeker;

    public JobseekerWorkedcompany() {
    }

    public JobseekerWorkedcompany(Integer id) {
        this.id = id;
    }

    public JobseekerWorkedcompany(Integer id, String name, Date startDate, Date endDate, String designation) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.designation = designation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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
        if (!(object instanceof JobseekerWorkedcompany)) {
            return false;
        }
        JobseekerWorkedcompany other = (JobseekerWorkedcompany) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jobhunt.entity.JobseekerWorkedcompany[ id=" + id + " ]";
    }
    
}
