/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.controller;

import com.jobhunt.controller.exceptions.NonexistentEntityException;
import com.jobhunt.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.VacancyApply;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class VacancyApplyJpaController implements Serializable {

    public VacancyApplyJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(VacancyApply vacancyApply) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Vacancy vacancyId = vacancyApply.getVacancyId();
            if (vacancyId != null) {
                vacancyId = em.getReference(vacancyId.getClass(), vacancyId.getId());
                vacancyApply.setVacancyId(vacancyId);
            }
            Jobseeker jobseekerId = vacancyApply.getJobseekerId();
            if (jobseekerId != null) {
                jobseekerId = em.getReference(jobseekerId.getClass(), jobseekerId.getId());
                vacancyApply.setJobseekerId(jobseekerId);
            }
            em.persist(vacancyApply);
            if (vacancyId != null) {
                vacancyId.getVacancyApplyCollection().add(vacancyApply);
                vacancyId = em.merge(vacancyId);
            }
            if (jobseekerId != null) {
                jobseekerId.getVacancyApplyCollection().add(vacancyApply);
                jobseekerId = em.merge(jobseekerId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(VacancyApply vacancyApply) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            VacancyApply persistentVacancyApply = em.find(VacancyApply.class, vacancyApply.getId());
            Vacancy vacancyIdOld = persistentVacancyApply.getVacancyId();
            Vacancy vacancyIdNew = vacancyApply.getVacancyId();
            Jobseeker jobseekerIdOld = persistentVacancyApply.getJobseekerId();
            Jobseeker jobseekerIdNew = vacancyApply.getJobseekerId();
            if (vacancyIdNew != null) {
                vacancyIdNew = em.getReference(vacancyIdNew.getClass(), vacancyIdNew.getId());
                vacancyApply.setVacancyId(vacancyIdNew);
            }
            if (jobseekerIdNew != null) {
                jobseekerIdNew = em.getReference(jobseekerIdNew.getClass(), jobseekerIdNew.getId());
                vacancyApply.setJobseekerId(jobseekerIdNew);
            }
            vacancyApply = em.merge(vacancyApply);
            if (vacancyIdOld != null && !vacancyIdOld.equals(vacancyIdNew)) {
                vacancyIdOld.getVacancyApplyCollection().remove(vacancyApply);
                vacancyIdOld = em.merge(vacancyIdOld);
            }
            if (vacancyIdNew != null && !vacancyIdNew.equals(vacancyIdOld)) {
                vacancyIdNew.getVacancyApplyCollection().add(vacancyApply);
                vacancyIdNew = em.merge(vacancyIdNew);
            }
            if (jobseekerIdOld != null && !jobseekerIdOld.equals(jobseekerIdNew)) {
                jobseekerIdOld.getVacancyApplyCollection().remove(vacancyApply);
                jobseekerIdOld = em.merge(jobseekerIdOld);
            }
            if (jobseekerIdNew != null && !jobseekerIdNew.equals(jobseekerIdOld)) {
                jobseekerIdNew.getVacancyApplyCollection().add(vacancyApply);
                jobseekerIdNew = em.merge(jobseekerIdNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vacancyApply.getId();
                if (findVacancyApply(id) == null) {
                    throw new NonexistentEntityException("The vacancyApply with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            VacancyApply vacancyApply;
            try {
                vacancyApply = em.getReference(VacancyApply.class, id);
                vacancyApply.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vacancyApply with id " + id + " no longer exists.", enfe);
            }
            Vacancy vacancyId = vacancyApply.getVacancyId();
            if (vacancyId != null) {
                vacancyId.getVacancyApplyCollection().remove(vacancyApply);
                vacancyId = em.merge(vacancyId);
            }
            Jobseeker jobseekerId = vacancyApply.getJobseekerId();
            if (jobseekerId != null) {
                jobseekerId.getVacancyApplyCollection().remove(vacancyApply);
                jobseekerId = em.merge(jobseekerId);
            }
            em.remove(vacancyApply);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<VacancyApply> findVacancyApplyEntities() {
        return findVacancyApplyEntities(true, -1, -1);
    }

    public List<VacancyApply> findVacancyApplyEntities(int maxResults, int firstResult) {
        return findVacancyApplyEntities(false, maxResults, firstResult);
    }

    private List<VacancyApply> findVacancyApplyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(VacancyApply.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public VacancyApply findVacancyApply(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(VacancyApply.class, id);
        } finally {
            em.close();
        }
    }

    public int getVacancyApplyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<VacancyApply> rt = cq.from(VacancyApply.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public VacancyApply findVacancyApplyBySeekerAndVacancy(Integer jobseekerId, Integer vacancyId) {
        EntityManager em = getEntityManager();
        Jobseeker jobseeker = new JobseekerJpaController(utx, emf).findJobseeker(jobseekerId);
        Vacancy vacancy = new VacancyJpaController(utx, emf).findVacancy(vacancyId);
        TypedQuery<VacancyApply> cq = em.createQuery("SELECT v FROM VacancyApply v WHERE v.vacancyId = :vacancyId AND v.jobseekerId = :jobseekerId", VacancyApply.class);
        cq.setParameter("vacancyId", vacancy);
        cq.setParameter("jobseekerId", jobseeker);
        return cq.getSingleResult();
    }
    
    public List<VacancyApply> findVacancyApplyByDateAndID(Date today){
        EntityManager em = getEntityManager();
        TypedQuery<VacancyApply> cq = em.createQuery("SELECT v FROM VacancyApply v WHERE v.interviewDate = :interviewDate", VacancyApply.class);
        cq.setParameter("interviewDate", today);
        try{
            return cq.getResultList();
        }
        catch(Exception ex){
            return null;
        }
    }
    
    public List<VacancyApply> findVacancyApplyByJobseeker(Integer id){
        EntityManager em = getEntityManager();
        Jobseeker jobseeker = new JobseekerJpaController(utx, emf).findJobseeker(id);
        if(jobseeker==null){
            return null;
        }
        TypedQuery<VacancyApply> cq = em.createQuery("SELECT v FROM VacancyApply v WHERE v.jobseekerId = :jobseekerId", VacancyApply.class);
        cq.setParameter("jobseekerId", jobseeker);
        try{
            return cq.getResultList();
        }
        catch(Exception ex){
            return null;
        }
    }
}
