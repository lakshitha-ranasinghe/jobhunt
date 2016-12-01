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
import com.jobhunt.entity.JobseekerExperience;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class JobseekerExperienceJpaController implements Serializable {

    public JobseekerExperienceJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(JobseekerExperience jobseekerExperience) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Jobseeker jobseeker = jobseekerExperience.getJobseeker();
            if (jobseeker != null) {
                jobseeker = em.getReference(jobseeker.getClass(), jobseeker.getId());
                jobseekerExperience.setJobseeker(jobseeker);
            }
            em.persist(jobseekerExperience);
            if (jobseeker != null) {
                jobseeker.getJobseekerExperienceCollection().add(jobseekerExperience);
                jobseeker = em.merge(jobseeker);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findJobseekerExperience(jobseekerExperience.getId()) != null) {
                throw new PreexistingEntityException("JobseekerExperience " + jobseekerExperience + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(JobseekerExperience jobseekerExperience) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            JobseekerExperience persistentJobseekerExperience = em.find(JobseekerExperience.class, jobseekerExperience.getId());
            Jobseeker jobseekerOld = persistentJobseekerExperience.getJobseeker();
            Jobseeker jobseekerNew = jobseekerExperience.getJobseeker();
            if (jobseekerNew != null) {
                jobseekerNew = em.getReference(jobseekerNew.getClass(), jobseekerNew.getId());
                jobseekerExperience.setJobseeker(jobseekerNew);
            }
            jobseekerExperience = em.merge(jobseekerExperience);
            if (jobseekerOld != null && !jobseekerOld.equals(jobseekerNew)) {
                jobseekerOld.getJobseekerExperienceCollection().remove(jobseekerExperience);
                jobseekerOld = em.merge(jobseekerOld);
            }
            if (jobseekerNew != null && !jobseekerNew.equals(jobseekerOld)) {
                jobseekerNew.getJobseekerExperienceCollection().add(jobseekerExperience);
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
                Integer id = jobseekerExperience.getId();
                if (findJobseekerExperience(id) == null) {
                    throw new NonexistentEntityException("The jobseekerExperience with id " + id + " no longer exists.");
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
            JobseekerExperience jobseekerExperience;
            try {
                jobseekerExperience = em.getReference(JobseekerExperience.class, id);
                jobseekerExperience.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jobseekerExperience with id " + id + " no longer exists.", enfe);
            }
            Jobseeker jobseeker = jobseekerExperience.getJobseeker();
            if (jobseeker != null) {
                jobseeker.getJobseekerExperienceCollection().remove(jobseekerExperience);
                jobseeker = em.merge(jobseeker);
            }
            em.remove(jobseekerExperience);
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

    public List<JobseekerExperience> findJobseekerExperienceEntities() {
        return findJobseekerExperienceEntities(true, -1, -1);
    }

    public List<JobseekerExperience> findJobseekerExperienceEntities(int maxResults, int firstResult) {
        return findJobseekerExperienceEntities(false, maxResults, firstResult);
    }

    private List<JobseekerExperience> findJobseekerExperienceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JobseekerExperience.class));
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

    public JobseekerExperience findJobseekerExperience(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(JobseekerExperience.class, id);
        } finally {
            em.close();
        }
    }

    public int getJobseekerExperienceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<JobseekerExperience> rt = cq.from(JobseekerExperience.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
