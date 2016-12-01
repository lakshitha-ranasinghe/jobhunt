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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Table(name = "vacancy_cache")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VacancyCache.findAll", query = "SELECT v FROM VacancyCache v"),
    @NamedQuery(name = "VacancyCache.findById", query = "SELECT v FROM VacancyCache v WHERE v.id = :id"),
    @NamedQuery(name = "VacancyCache.findByPostedDate", query = "SELECT v FROM VacancyCache v WHERE v.postedDate = :postedDate"),
    @NamedQuery(name = "VacancyCache.findByPreference1", query = "SELECT v FROM VacancyCache v WHERE v.preference1 = :preference1"),
    @NamedQuery(name = "VacancyCache.findByPreference2", query = "SELECT v FROM VacancyCache v WHERE v.preference2 = :preference2")})
public class VacancyCache implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "posted_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;
    @Column(name = "preference1")
    private Integer preference1;
    @Size(max = 50)
    @Column(name = "preference2")
    private String preference2;

    public VacancyCache() {
    }

    public VacancyCache(Integer id) {
        this.id = id;
    }

    public VacancyCache(Integer id, Date postedDate) {
        this.id = id;
        this.postedDate = postedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Integer getPreference1() {
        return preference1;
    }

    public void setPreference1(Integer preference1) {
        this.preference1 = preference1;
    }

    public String getPreference2() {
        return preference2;
    }

    public void setPreference2(String preference2) {
        this.preference2 = preference2;
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
        if (!(object instanceof VacancyCache)) {
            return false;
        }
        VacancyCache other = (VacancyCache) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jobhunt.entity.VacancyCache[ id=" + id + " ]";
    }
    
}
