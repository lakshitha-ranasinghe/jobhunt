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
import com.jobhunt.entity.JobseekerOther;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class JobseekerOtherJpaController implements Serializable {

    public JobseekerOtherJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(JobseekerOther jobseekerOther) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Jobseeker jobseeker = jobseekerOther.getJobseeker();
            if (jobseeker != null) {
                jobseeker = em.getReference(jobseeker.getClass(), jobseeker.getId());
                jobseekerOther.setJobseeker(jobseeker);
            }
            em.persist(jobseekerOther);
            if (jobseeker != null) {
                jobseeker.getJobseekerOtherCollection().add(jobseekerOther);
                jobseeker = em.merge(jobseeker);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findJobseekerOther(jobseekerOther.getId()) != null) {
                throw new PreexistingEntityException("JobseekerOther " + jobseekerOther + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(JobseekerOther jobseekerOther) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            JobseekerOther persistentJobseekerOther = em.find(JobseekerOther.class, jobseekerOther.getId());
            Jobseeker jobseekerOld = persistentJobseekerOther.getJobseeker();
            Jobseeker jobseekerNew = jobseekerOther.getJobseeker();
            if (jobseekerNew != null) {
                jobseekerNew = em.getReference(jobseekerNew.getClass(), jobseekerNew.getId());
                jobseekerOther.setJobseeker(jobseekerNew);
            }
            jobseekerOther = em.merge(jobseekerOther);
            if (jobseekerOld != null && !jobseekerOld.equals(jobseekerNew)) {
                jobseekerOld.getJobseekerOtherCollection().remove(jobseekerOther);
                jobseekerOld = em.merge(jobseekerOld);
            }
            if (jobseekerNew != null && !jobseekerNew.equals(jobseekerOld)) {
                jobseekerNew.getJobseekerOtherCollection().add(jobseekerOther);
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
                Integer id = jobseekerOther.getId();
                if (findJobseekerOther(id) == null) {
                    throw new NonexistentEntityException("The jobseekerOther with id " + id + " no longer exists.");
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
            JobseekerOther jobseekerOther;
            try {
                jobseekerOther = em.getReference(JobseekerOther.class, id);
                jobseekerOther.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jobseekerOther with id " + id + " no longer exists.", enfe);
            }
            Jobseeker jobseeker = jobseekerOther.getJobseeker();
            if (jobseeker != null) {
                jobseeker.getJobseekerOtherCollection().remove(jobseekerOther);
                jobseeker = em.merge(jobseeker);
            }
            em.remove(jobseekerOther);
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

    public List<JobseekerOther> findJobseekerOtherEntities() {
        return findJobseekerOtherEntities(true, -1, -1);
    }

    public List<JobseekerOther> findJobseekerOtherEntities(int maxResults, int firstResult) {
        return findJobseekerOtherEntities(false, maxResults, firstResult);
    }

    private List<JobseekerOther> findJobseekerOtherEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JobseekerOther.class));
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

    public JobseekerOther findJobseekerOther(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(JobseekerOther.class, id);
        } finally {
            em.close();
        }
    }

    public int getJobseekerOtherCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<JobseekerOther> rt = cq.from(JobseekerOther.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
