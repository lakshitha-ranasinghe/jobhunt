/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.controller;

import com.jobhunt.controller.exceptions.NonexistentEntityException;
import com.jobhunt.controller.exceptions.PreexistingEntityException;
import com.jobhunt.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.JobseekerOl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class JobseekerOlJpaController implements Serializable {

    public JobseekerOlJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(JobseekerOl jobseekerOl) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Jobseeker jobseekerEducation = jobseekerOl.getJobseekerEducation();
            if (jobseekerEducation != null) {
                jobseekerEducation = em.getReference(jobseekerEducation.getClass(), jobseekerEducation.getId());
                jobseekerOl.setJobseekerEducation(jobseekerEducation);
            }
            em.persist(jobseekerOl);
            if (jobseekerEducation != null) {
                jobseekerEducation.getJobseekerOlCollection().add(jobseekerOl);
                jobseekerEducation = em.merge(jobseekerEducation);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findJobseekerOl(jobseekerOl.getOlId()) != null) {
                throw new PreexistingEntityException("JobseekerOl " + jobseekerOl + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(JobseekerOl jobseekerOl) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            JobseekerOl persistentJobseekerOl = em.find(JobseekerOl.class, jobseekerOl.getOlId());
            Jobseeker jobseekerEducationOld = persistentJobseekerOl.getJobseekerEducation();
            Jobseeker jobseekerEducationNew = jobseekerOl.getJobseekerEducation();
            if (jobseekerEducationNew != null) {
                jobseekerEducationNew = em.getReference(jobseekerEducationNew.getClass(), jobseekerEducationNew.getId());
                jobseekerOl.setJobseekerEducation(jobseekerEducationNew);
            }
            jobseekerOl = em.merge(jobseekerOl);
            if (jobseekerEducationOld != null && !jobseekerEducationOld.equals(jobseekerEducationNew)) {
                jobseekerEducationOld.getJobseekerOlCollection().remove(jobseekerOl);
                jobseekerEducationOld = em.merge(jobseekerEducationOld);
            }
            if (jobseekerEducationNew != null && !jobseekerEducationNew.equals(jobseekerEducationOld)) {
                jobseekerEducationNew.getJobseekerOlCollection().add(jobseekerOl);
                jobseekerEducationNew = em.merge(jobseekerEducationNew);
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
                Integer id = jobseekerOl.getOlId();
                if (findJobseekerOl(id) == null) {
                    throw new NonexistentEntityException("The jobseekerOl with id " + id + " no longer exists.");
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
            JobseekerOl jobseekerOl;
            try {
                jobseekerOl = em.getReference(JobseekerOl.class, id);
                jobseekerOl.getOlId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jobseekerOl with id " + id + " no longer exists.", enfe);
            }
            Jobseeker jobseekerEducation = jobseekerOl.getJobseekerEducation();
            if (jobseekerEducation != null) {
                jobseekerEducation.getJobseekerOlCollection().remove(jobseekerOl);
                jobseekerEducation = em.merge(jobseekerEducation);
            }
            em.remove(jobseekerOl);
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

    public List<JobseekerOl> findJobseekerOlEntities() {
        return findJobseekerOlEntities(true, -1, -1);
    }

    public List<JobseekerOl> findJobseekerOlEntities(int maxResults, int firstResult) {
        return findJobseekerOlEntities(false, maxResults, firstResult);
    }

    private List<JobseekerOl> findJobseekerOlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JobseekerOl.class));
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

    public JobseekerOl findJobseekerOl(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(JobseekerOl.class, id);
        } finally {
            em.close();
        }
    }

    public int getJobseekerOlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<JobseekerOl> rt = cq.from(JobseekerOl.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
