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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lakshitha
 */
@Entity
@Table(name = "user_cache")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserCache.findAll", query = "SELECT u FROM UserCache u"),
    @NamedQuery(name = "UserCache.findById", query = "SELECT u FROM UserCache u WHERE u.id = :id"),
    @NamedQuery(name = "UserCache.findByLastLogin", query = "SELECT u FROM UserCache u WHERE u.lastLogin = :lastLogin"),
    @NamedQuery(name = "UserCache.findByPreference1", query = "SELECT u FROM UserCache u WHERE u.preference1 = :preference1"),
    @NamedQuery(name = "UserCache.findByPreference2", query = "SELECT u FROM UserCache u WHERE u.preference2 = :preference2"),
    @NamedQuery(name = "UserCache.findByPreference3", query = "SELECT u FROM UserCache u WHERE u.preference3 = :preference3")})
public class UserCache implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;
    @Basic(optional = false)
    @Column(name = "last_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "preference1")
    private Integer preference1;
    @Column(name = "preference2")
    private Integer preference2;
    @Column(name = "preference3")
    private Integer preference3;

    public UserCache() {
    }

    public UserCache(Integer id) {
        this.id = id;
    }

    public UserCache(Integer id, byte[] profilePicture, Date lastLogin) {
        this.id = id;
        this.profilePicture = profilePicture;
        this.lastLogin = lastLogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getPreference1() {
        return preference1;
    }

    public void setPreference1(Integer preference1) {
        this.preference1 = preference1;
    }

    public Integer getPreference2() {
        return preference2;
    }

    public void setPreference2(Integer preference2) {
        this.preference2 = preference2;
    }

    public Integer getPreference3() {
        return preference3;
    }

    public void setPreference3(Integer preference3) {
        this.preference3 = preference3;
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
        if (!(object instanceof UserCache)) {
            return false;
        }
        UserCache other = (UserCache) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jobhunt.entity.UserCache[ id=" + id + " ]";
    }
    
}
