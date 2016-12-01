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
import com.jobhunt.entity.JobseekerAl;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class JobseekerAlJpaController implements Serializable {

    public JobseekerAlJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(JobseekerAl jobseekerAl) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Jobseeker jobseekerEducation = jobseekerAl.getJobseekerEducation();
            if (jobseekerEducation != null) {
                jobseekerEducation = em.getReference(jobseekerEducation.getClass(), jobseekerEducation.getId());
                jobseekerAl.setJobseekerEducation(jobseekerEducation);
            }
            em.persist(jobseekerAl);
            if (jobseekerEducation != null) {
                jobseekerEducation.getJobseekerAlCollection().add(jobseekerAl);
                jobseekerEducation = em.merge(jobseekerEducation);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findJobseekerAl(jobseekerAl.getAlId()) != null) {
                throw new PreexistingEntityException("JobseekerAl " + jobseekerAl + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(JobseekerAl jobseekerAl) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            JobseekerAl persistentJobseekerAl = em.find(JobseekerAl.class, jobseekerAl.getAlId());
            Jobseeker jobseekerEducationOld = persistentJobseekerAl.getJobseekerEducation();
            Jobseeker jobseekerEducationNew = jobseekerAl.getJobseekerEducation();
            if (jobseekerEducationNew != null) {
                jobseekerEducationNew = em.getReference(jobseekerEducationNew.getClass(), jobseekerEducationNew.getId());
                jobseekerAl.setJobseekerEducation(jobseekerEducationNew);
            }
            jobseekerAl = em.merge(jobseekerAl);
            if (jobseekerEducationOld != null && !jobseekerEducationOld.equals(jobseekerEducationNew)) {
                jobseekerEducationOld.getJobseekerAlCollection().remove(jobseekerAl);
                jobseekerEducationOld = em.merge(jobseekerEducationOld);
            }
            if (jobseekerEducationNew != null && !jobseekerEducationNew.equals(jobseekerEducationOld)) {
                jobseekerEducationNew.getJobseekerAlCollection().add(jobseekerAl);
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
                Integer id = jobseekerAl.getAlId();
                if (findJobseekerAl(id) == null) {
                    throw new NonexistentEntityException("The jobseekerAl with id " + id + " no longer exists.");
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
            JobseekerAl jobseekerAl;
            try {
                jobseekerAl = em.getReference(JobseekerAl.class, id);
                jobseekerAl.getAlId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jobseekerAl with id " + id + " no longer exists.", enfe);
            }
            Jobseeker jobseekerEducation = jobseekerAl.getJobseekerEducation();
            if (jobseekerEducation != null) {
                jobseekerEducation.getJobseekerAlCollection().remove(jobseekerAl);
                jobseekerEducation = em.merge(jobseekerEducation);
            }
            em.remove(jobseekerAl);
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

    public List<JobseekerAl> findJobseekerAlEntities() {
        return findJobseekerAlEntities(true, -1, -1);
    }

    public List<JobseekerAl> findJobseekerAlEntities(int maxResults, int firstResult) {
        return findJobseekerAlEntities(false, maxResults, firstResult);
    }

    private List<JobseekerAl> findJobseekerAlEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JobseekerAl.class));
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

    public JobseekerAl findJobseekerAl(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(JobseekerAl.class, id);
        } finally {
            em.close();
        }
    }

    public int getJobseekerAlCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<JobseekerAl> rt = cq.from(JobseekerAl.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
