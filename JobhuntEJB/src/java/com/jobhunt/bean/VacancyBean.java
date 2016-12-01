/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.bean;

import com.jobhunt.controller.CompanyProfileJpaController;
import com.jobhunt.controller.JobseekerJpaController;
import com.jobhunt.controller.VacancyApplyJpaController;
import com.jobhunt.controller.VacancyCacheJpaController;
import com.jobhunt.controller.VacancyJpaController;
import com.jobhunt.controller.exceptions.NonexistentEntityException;
import com.jobhunt.controller.exceptions.RollbackFailureException;
import com.jobhunt.entity.CompanyProfile;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.entity.VacancyApply;
import com.jobhunt.entity.VacancyCache;
import com.jobhunt.remote.VacancyRemote;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class VacancyBean implements VacancyRemote{

    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;
    
    @Override
    public Integer addVacany(Map<String,String> vacancy, Integer id, Date closingDate) {
        System.out.println(id);
        CompanyProfile companyProfile = new CompanyProfileJpaController(utx, emf).findCompanyProfile(id);
        Vacancy vacancyPersist = new Vacancy();
        vacancyPersist.setBranch(vacancy.get("location"));
        vacancyPersist.setDescription(vacancy.get("description"));
        vacancyPersist.setPrerequisites(vacancy.get("prerequisites"));
        vacancyPersist.setTitle(vacancy.get("title"));
        vacancyPersist.setVacancyCount(Integer.parseInt(vacancy.get("count")));
        vacancyPersist.setClosingDate(closingDate);
        vacancyPersist.setSalary(Double.parseDouble(vacancy.get("salary")));
        vacancyPersist.setCompany(companyProfile);
        try {
            new VacancyJpaController(utx, emf).create(vacancyPersist);
        } catch (Exception ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vacancyPersist.getId();
    }

    @Override
    public List<Vacancy> findAllVacancies() {
        return new VacancyJpaController(utx, emf).findVacancyEntities();
    }

    @Override
    public List<Vacancy> findRecommendedVacancies(String job, String area, String salary) {
        return new VacancyJpaController(utx, emf).findVacanciesByJobseekerPreferences(job, salary, area);
    }

    @Override
    public Boolean deleteVacancy(Integer id) {
        try {
            new VacancyJpaController(utx, emf).destroy(id);
            return true;
        } catch (RollbackFailureException ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public Vacancy findVacany(Integer id) {
        return new VacancyJpaController(utx, emf).findVacancy(id);
    }

    @Override
    public Boolean applyVacancy(Integer seekerId, Integer vacancyId, Integer status) {   
        try {
            Jobseeker jobseeker = new JobseekerJpaController(utx, emf).findJobseeker(seekerId);
            Vacancy vacancy = new VacancyJpaController(utx, emf).findVacancy(vacancyId);
            
            VacancyApply vacancyApply = new VacancyApply();
            vacancyApply.setInterviewDate(vacancy.getClosingDate());
            vacancyApply.setJobseekerId(jobseeker);
            vacancyApply.setStatus(status);
            vacancyApply.setVacancyId(vacancy);

            new VacancyApplyJpaController(utx, emf).create(vacancyApply);
            return true;
        } catch (RollbackFailureException ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }   

    @Override
    public Boolean changeApplyVacancyStatus(Integer applicantId, Integer vacancyId, Integer status, Date interviewDate) {
        VacancyApply vacancyApply = new VacancyApplyJpaController(utx, emf).findVacancyApplyBySeekerAndVacancy(applicantId, vacancyId);
        vacancyApply.setStatus(status);
        vacancyApply.setInterviewDate(interviewDate);
        try {
            new VacancyApplyJpaController(utx, emf).edit(vacancyApply);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public void createVacancyCache(Integer id, Date postedDate) {
        VacancyCache vacancyCache = new VacancyCache();
        vacancyCache.setId(id);
        vacancyCache.setPostedDate(postedDate);
        try {
            new VacancyCacheJpaController(utx, emf).create(vacancyCache);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<VacancyCache> findAllVacancyCache() {
        return new VacancyCacheJpaController(utx, emf).findVacancyCacheEntities();
    }

    @Override
    public List<VacancyApply> findVacancyByDateAndID(Date today) {
        return new VacancyApplyJpaController(utx, emf).findVacancyApplyByDateAndID(today);
    }

    @Override
    public List<VacancyApply> findAllVacancyApply() {
        return new VacancyApplyJpaController(utx, emf).findVacancyApplyEntities();
    }

    @Override
    public VacancyApply findVacancyApplyById(Integer id) {
        return new VacancyApplyJpaController(utx, emf).findVacancyApply(id);
    }

    @Override
    public VacancyApply findVacancyApplyByJobseekerAndVacancy(Integer vacancyId, Integer seekerId) {
        return new VacancyApplyJpaController(utx, emf).findVacancyApplyBySeekerAndVacancy(seekerId, vacancyId);
    }

    @Override
    public List<VacancyApply> findVacancyApplyByJobseeker(Integer id) {
        return new VacancyApplyJpaController(utx, emf).findVacancyApplyByJobseeker(id);
    }

    @Override
    public List<Vacancy> findVacancyByCompanyId(Integer id) {
        return new VacancyJpaController(utx, emf).findVacanciesByCompanyId(id);
    }

    @Override
    public Boolean UpdateVacancyClosingDate(Integer id, Date closingDate) {
        Vacancy vacancy = new VacancyJpaController(utx, emf).findVacancy(id);
        vacancy.setClosingDate(closingDate);
        try {
            new VacancyJpaController(utx, emf).edit(vacancy);
            return true;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (RollbackFailureException ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(VacancyBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
