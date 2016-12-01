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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
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
@Table(name = "vacancy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacancy.findAll", query = "SELECT v FROM Vacancy v"),
    @NamedQuery(name = "Vacancy.findById", query = "SELECT v FROM Vacancy v WHERE v.id = :id"),
    @NamedQuery(name = "Vacancy.findByTitle", query = "SELECT v FROM Vacancy v WHERE v.title = :title"),
    @NamedQuery(name = "Vacancy.findByDescription", query = "SELECT v FROM Vacancy v WHERE v.description = :description"),
    @NamedQuery(name = "Vacancy.findByPrerequisites", query = "SELECT v FROM Vacancy v WHERE v.prerequisites = :prerequisites"),
    @NamedQuery(name = "Vacancy.findByBranch", query = "SELECT v FROM Vacancy v WHERE v.branch = :branch"),
    @NamedQuery(name = "Vacancy.findByClosingDate", query = "SELECT v FROM Vacancy v WHERE v.closingDate = :closingDate"),
    @NamedQuery(name = "Vacancy.findByVacancyCount", query = "SELECT v FROM Vacancy v WHERE v.vacancyCount = :vacancyCount"),
    @NamedQuery(name = "Vacancy.findBySalary", query = "SELECT v FROM Vacancy v WHERE v.salary = :salary")})
public class Vacancy implements Serializable {
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "vacancyId")
    private Collection<VacancyApply> vacancyApplyCollection;
    private static final long serialVersionUID = 1L;
    @TableGenerator(name = "vacancy_generator",
            table = "id_generator",
            pkColumnName = "gen_name",
            valueColumnName = "gen_value",
            pkColumnValue = "vacancy",
            allocationSize = 2)
    @Id
    @GeneratedValue(generator = "vacancy_generator")
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
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "prerequisites")
    private String prerequisites;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "branch")
    private String branch;
    @Basic(optional = false)
    @NotNull
    @Column(name = "closing_date")
    @Temporal(TemporalType.DATE)
    private Date closingDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vacancy_count")
    private int vacancyCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "salary")
    private double salary;
    @JoinColumn(name = "company", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CompanyProfile company;

    public Vacancy() {
    }

    public Vacancy(Integer id) {
        this.id = id;
    }

    public Vacancy(Integer id, String title, String description, String prerequisites, String branch, Date closingDate, int vacancyCount, double salary) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.prerequisites = prerequisites;
        this.branch = branch;
        this.closingDate = closingDate;
        this.vacancyCount = vacancyCount;
        this.salary = salary;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public int getVacancyCount() {
        return vacancyCount;
    }

    public void setVacancyCount(int vacancyCount) {
        this.vacancyCount = vacancyCount;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public CompanyProfile getCompany() {
        return company;
    }

    public void setCompany(CompanyProfile company) {
        this.company = company;
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
        if (!(object instanceof Vacancy)) {
            return false;
        }
        Vacancy other = (Vacancy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vacancy{" + "vacancyApplyCollection=" + vacancyApplyCollection + ", id=" + id + ", title=" + title + ", description=" + description + ", prerequisites=" + prerequisites + ", branch=" + branch + ", closingDate=" + closingDate + ", vacancyCount=" + vacancyCount + ", salary=" + salary + ", company=" + company + '}';
    }

    @XmlTransient
    public Collection<VacancyApply> getVacancyApplyCollection() {
        return vacancyApplyCollection;
    }

    public void setVacancyApplyCollection(Collection<VacancyApply> vacancyApplyCollection) {
        this.vacancyApplyCollection = vacancyApplyCollection;
    }
    
}
