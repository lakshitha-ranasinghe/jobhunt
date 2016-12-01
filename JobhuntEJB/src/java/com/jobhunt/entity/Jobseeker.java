/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lakshitha
 */
@Entity
@Table(name = "jobseeker")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jobseeker.findAll", query = "SELECT j FROM Jobseeker j"),
    @NamedQuery(name = "Jobseeker.findById", query = "SELECT j FROM Jobseeker j WHERE j.id = :id"),
    @NamedQuery(name = "Jobseeker.findByFirstName", query = "SELECT j FROM Jobseeker j WHERE j.firstName = :firstName"),
    @NamedQuery(name = "Jobseeker.findByLastName", query = "SELECT j FROM Jobseeker j WHERE j.lastName = :lastName"),
    @NamedQuery(name = "Jobseeker.findByTitle", query = "SELECT j FROM Jobseeker j WHERE j.title = :title"),
    @NamedQuery(name = "Jobseeker.findByBirthday", query = "SELECT j FROM Jobseeker j WHERE j.birthday = :birthday"),
    @NamedQuery(name = "Jobseeker.findByAddress1", query = "SELECT j FROM Jobseeker j WHERE j.address1 = :address1"),
    @NamedQuery(name = "Jobseeker.findByAddress2", query = "SELECT j FROM Jobseeker j WHERE j.address2 = :address2"),
    @NamedQuery(name = "Jobseeker.findByAddress3", query = "SELECT j FROM Jobseeker j WHERE j.address3 = :address3"),
    @NamedQuery(name = "Jobseeker.findByEmail", query = "SELECT j FROM Jobseeker j WHERE j.email = :email"),
    @NamedQuery(name = "Jobseeker.findByTelephone", query = "SELECT j FROM Jobseeker j WHERE j.telephone = :telephone"),
    @NamedQuery(name = "Jobseeker.findByMobile", query = "SELECT j FROM Jobseeker j WHERE j.mobile = :mobile"),
    @NamedQuery(name = "Jobseeker.findByQualifiedField", query = "SELECT j FROM Jobseeker j WHERE j.qualifiedField = :qualifiedField"),
    @NamedQuery(name = "Jobseeker.findByLastJob", query = "SELECT j FROM Jobseeker j WHERE j.lastJob = :lastJob"),
    @NamedQuery(name = "Jobseeker.findByExpectedJob", query = "SELECT j FROM Jobseeker j WHERE j.expectedJob = :expectedJob")})
public class Jobseeker implements Serializable {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "jobseekerId")
    private Collection<VacancyApply> vacancyApplyCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "address1")
    private String address1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "address2")
    private String address2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "address3")
    private String address3;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telephone")
    private int telephone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mobile")
    private int mobile;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "qualified_field")
    private String qualifiedField;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "last_job")
    private String lastJob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "expected_job")
    private String expectedJob;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "jobseekerEducation")
    private Collection<JobseekerOl> jobseekerOlCollection;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "jobseeker")
    private Collection<JobseekerEducation> jobseekerEducationCollection;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "jobseekerEducation")
    private Collection<JobseekerAl> jobseekerAlCollection;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "jobseeker")
    private Collection<JobseekerWorkedcompany> jobseekerWorkedcompanyCollection;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "jobseekerEducation")
    private Collection<JobseekerUniversity> jobseekerUniversityCollection;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "jobseeker")
    private Collection<JobseekerOther> jobseekerOtherCollection;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "jobseeker")
    private Collection<JobseekerExperience> jobseekerExperienceCollection;

    public Jobseeker() {
    }

    public Jobseeker(Integer id) {
        this.id = id;
    }

    public Jobseeker(Integer id, String firstName, String lastName, String title, Date birthday, String address1, String address2, String address3, String email, int telephone, int mobile, String qualifiedField, String lastJob, String expectedJob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.birthday = birthday;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.email = email;
        this.telephone = telephone;
        this.mobile = mobile;
        this.qualifiedField = qualifiedField;
        this.lastJob = lastJob;
        this.expectedJob = expectedJob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getQualifiedField() {
        return qualifiedField;
    }

    public void setQualifiedField(String qualifiedField) {
        this.qualifiedField = qualifiedField;
    }

    public String getLastJob() {
        return lastJob;
    }

    public void setLastJob(String lastJob) {
        this.lastJob = lastJob;
    }

    public String getExpectedJob() {
        return expectedJob;
    }

    public void setExpectedJob(String expectedJob) {
        this.expectedJob = expectedJob;
    }

    @XmlTransient
    public Collection<JobseekerOl> getJobseekerOlCollection() {
        return jobseekerOlCollection;
    }

    public void setJobseekerOlCollection(Collection<JobseekerOl> jobseekerOlCollection) {
        this.jobseekerOlCollection = jobseekerOlCollection;
    }

    @XmlTransient
    public Collection<JobseekerEducation> getJobseekerEducationCollection() {
        return jobseekerEducationCollection;
    }

    public void setJobseekerEducationCollection(Collection<JobseekerEducation> jobseekerEducationCollection) {
        this.jobseekerEducationCollection = jobseekerEducationCollection;
    }

    @XmlTransient
    public Collection<JobseekerAl> getJobseekerAlCollection() {
        return jobseekerAlCollection;
    }

    public void setJobseekerAlCollection(Collection<JobseekerAl> jobseekerAlCollection) {
        this.jobseekerAlCollection = jobseekerAlCollection;
    }

    @XmlTransient
    public Collection<JobseekerWorkedcompany> getJobseekerWorkedcompanyCollection() {
        return jobseekerWorkedcompanyCollection;
    }

    public void setJobseekerWorkedcompanyCollection(Collection<JobseekerWorkedcompany> jobseekerWorkedcompanyCollection) {
        this.jobseekerWorkedcompanyCollection = jobseekerWorkedcompanyCollection;
    }

    @XmlTransient
    public Collection<JobseekerUniversity> getJobseekerUniversityCollection() {
        return jobseekerUniversityCollection;
    }

    public void setJobseekerUniversityCollection(Collection<JobseekerUniversity> jobseekerUniversityCollection) {
        this.jobseekerUniversityCollection = jobseekerUniversityCollection;
    }

    @XmlTransient
    public Collection<JobseekerOther> getJobseekerOtherCollection() {
        return jobseekerOtherCollection;
    }

    public void setJobseekerOtherCollection(Collection<JobseekerOther> jobseekerOtherCollection) {
        this.jobseekerOtherCollection = jobseekerOtherCollection;
    }

    @XmlTransient
    public Collection<JobseekerExperience> getJobseekerExperienceCollection() {
        return jobseekerExperienceCollection;
    }

    public void setJobseekerExperienceCollection(Collection<JobseekerExperience> jobseekerExperienceCollection) {
        this.jobseekerExperienceCollection = jobseekerExperienceCollection;
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
        if (!(object instanceof Jobseeker)) {
            return false;
        }
        Jobseeker other = (Jobseeker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Jobseeker{" + "vacancyApplyCollection=" + vacancyApplyCollection + ", id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title + ", birthday=" + birthday + ", address1=" + address1 + ", address2=" + address2 + ", address3=" + address3 + ", email=" + email + ", telephone=" + telephone + ", mobile=" + mobile + ", qualifiedField=" + qualifiedField + ", lastJob=" + lastJob + ", expectedJob=" + expectedJob + ", jobseekerOlCollection=" + jobseekerOlCollection + ", jobseekerEducationCollection=" + jobseekerEducationCollection + ", jobseekerAlCollection=" + jobseekerAlCollection + ", jobseekerWorkedcompanyCollection=" + jobseekerWorkedcompanyCollection + ", jobseekerUniversityCollection=" + jobseekerUniversityCollection + ", jobseekerOtherCollection=" + jobseekerOtherCollection + ", jobseekerExperienceCollection=" + jobseekerExperienceCollection + '}';
    }

    

    @XmlTransient
    public Collection<VacancyApply> getVacancyApplyCollection() {
        return vacancyApplyCollection;
    }

    public void setVacancyApplyCollection(Collection<VacancyApply> vacancyApplyCollection) {
        this.vacancyApplyCollection = vacancyApplyCollection;
    }
    
}
