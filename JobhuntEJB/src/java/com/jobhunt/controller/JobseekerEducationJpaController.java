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
import com.jobhunt.entity.JobseekerEducation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class JobseekerEducationJpaController implements Serializable {

    public JobseekerEducationJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(JobseekerEducation jobseekerEducation) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Jobseeker jobseeker = jobseekerEducation.getJobseeker();
            if (jobseeker != null) {
                jobseeker = em.getReference(jobseeker.getClass(), jobseeker.getId());
                jobseekerEducation.setJobseeker(jobseeker);
            }
            em.persist(jobseekerEducation);
            if (jobseeker != null) {
                jobseeker.getJobseekerEducationCollection().add(jobseekerEducation);
                jobseeker = em.merge(jobseeker);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findJobseekerEducation(jobseekerEducation.getId()) != null) {
                throw new PreexistingEntityException("JobseekerEducation " + jobseekerEducation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(JobseekerEducation jobseekerEducation) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            JobseekerEducation persistentJobseekerEducation = em.find(JobseekerEducation.class, jobseekerEducation.getId());
            Jobseeker jobseekerOld = persistentJobseekerEducation.getJobseeker();
            Jobseeker jobseekerNew = jobseekerEducation.getJobseeker();
            if (jobseekerNew != null) {
                jobseekerNew = em.getReference(jobseekerNew.getClass(), jobseekerNew.getId());
                jobseekerEducation.setJobseeker(jobseekerNew);
            }
            jobseekerEducation = em.merge(jobseekerEducation);
            if (jobseekerOld != null && !jobseekerOld.equals(jobseekerNew)) {
                jobseekerOld.getJobseekerEducationCollection().remove(jobseekerEducation);
                jobseekerOld = em.merge(jobseekerOld);
            }
            if (jobseekerNew != null && !jobseekerNew.equals(jobseekerOld)) {
                jobseekerNew.getJobseekerEducationCollection().add(jobseekerEducation);
                jobseekerNew = em.merge(jobseekerNew);
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
                Integer id = jobseekerEducation.getId();
                if (findJobseekerEducation(id) == null) {
                    throw new NonexistentEntityException("The jobseekerEducation with id " + id + " no longer exists.");
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
            JobseekerEducation jobseekerEducation;
            try {
                jobseekerEducation = em.getReference(JobseekerEducation.class, id);
                jobseekerEducation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jobseekerEducation with id " + id + " no longer exists.", enfe);
            }
            Jobseeker jobseeker = jobseekerEducation.getJobseeker();
            if (jobseeker != null) {
                jobseeker.getJobseekerEducationCollection().remove(jobseekerEducation);
                jobseeker = em.merge(jobseeker);
            }
            em.remove(jobseekerEducation);
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

    public List<JobseekerEducation> findJobseekerEducationEntities() {
        return findJobseekerEducationEntities(true, -1, -1);
    }

    public List<JobseekerEducation> findJobseekerEducationEntities(int maxResults, int firstResult) {
        return findJobseekerEducationEntities(false, maxResults, firstResult);
    }

    private List<JobseekerEducation> findJobseekerEducationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JobseekerEducation.class));
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

    public JobseekerEducation findJobseekerEducation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(JobseekerEducation.class, id);
        } finally {
            em.close();
        }
    }

    public int getJobseekerEducationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<JobseekerEducation> rt = cq.from(JobseekerEducation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
