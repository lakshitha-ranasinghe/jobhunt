/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lakshitha
 */
@Entity
@Table(name = "company_profile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyProfile.findAll", query = "SELECT c FROM CompanyProfile c"),
    @NamedQuery(name = "CompanyProfile.findById", query = "SELECT c FROM CompanyProfile c WHERE c.id = :id"),
    @NamedQuery(name = "CompanyProfile.findByName", query = "SELECT c FROM CompanyProfile c WHERE c.name = :name"),
    @NamedQuery(name = "CompanyProfile.findByType", query = "SELECT c FROM CompanyProfile c WHERE c.type = :type"),
    @NamedQuery(name = "CompanyProfile.findByEmail", query = "SELECT c FROM CompanyProfile c WHERE c.email = :email"),
    @NamedQuery(name = "CompanyProfile.findByTelephone", query = "SELECT c FROM CompanyProfile c WHERE c.telephone = :telephone"),
    @NamedQuery(name = "CompanyProfile.findByMobile", query = "SELECT c FROM CompanyProfile c WHERE c.mobile = :mobile"),
    @NamedQuery(name = "CompanyProfile.findByAddress1", query = "SELECT c FROM CompanyProfile c WHERE c.address1 = :address1"),
    @NamedQuery(name = "CompanyProfile.findByAddress2", query = "SELECT c FROM CompanyProfile c WHERE c.address2 = :address2"),
    @NamedQuery(name = "CompanyProfile.findByAddress3", query = "SELECT c FROM CompanyProfile c WHERE c.address3 = :address3"),
    @NamedQuery(name = "CompanyProfile.findByWebsite", query = "SELECT c FROM CompanyProfile c WHERE c.website = :website"),
    @NamedQuery(name = "CompanyProfile.findByJobType", query = "SELECT c FROM CompanyProfile c WHERE c.jobType = :jobType"),
    @NamedQuery(name = "CompanyProfile.findByDescription", query = "SELECT c FROM CompanyProfile c WHERE c.description = :description")})
public class CompanyProfile implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Vacancy> vacancyCollection;
    private static final long serialVersionUID = 1L;
    @Id
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
    @Size(min = 1, max = 100)
    @Column(name = "type")
    private String type;
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
    @Column(name = "address1")
    private String address1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address2")
    private String address2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "address3")
    private String address3;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "website")
    private String website;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "job_type")
    private String jobType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "description")
    private String description;

    public CompanyProfile() {
    }

    public CompanyProfile(Integer id) {
        this.id = id;
    }

    public CompanyProfile(Integer id, String name, String type, String email, int telephone, int mobile, String address1, String address2, String address3, String website, String jobType, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.email = email;
        this.telephone = telephone;
        this.mobile = mobile;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.website = website;
        this.jobType = jobType;
        this.description = description;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof CompanyProfile)) {
            return false;
        }
        CompanyProfile other = (CompanyProfile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CompanyProfile{" + "name=" + name + ", type=" + type + ", email=" + email + ", telephone=" + telephone + ", address1=" + address1 + ", address2=" + address2 + ", address3=" + address3 + ", website=" + website + '}';
    }

    @XmlTransient
    public Collection<Vacancy> getVacancyCollection() {
        return vacancyCollection;
    }

    public void setVacancyCollection(Collection<Vacancy> vacancyCollection) {
        this.vacancyCollection = vacancyCollection;
    }
    
}
