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
import com.jobhunt.entity.JobseekerWorkedcompany;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class JobseekerWorkedcompanyJpaController implements Serializable {

    public JobseekerWorkedcompanyJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(JobseekerWorkedcompany jobseekerWorkedcompany) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Jobseeker jobseeker = jobseekerWorkedcompany.getJobseeker();
            if (jobseeker != null) {
                jobseeker = em.getReference(jobseeker.getClass(), jobseeker.getId());
                jobseekerWorkedcompany.setJobseeker(jobseeker);
            }
            em.persist(jobseekerWorkedcompany);
            if (jobseeker != null) {
                jobseeker.getJobseekerWorkedcompanyCollection().add(jobseekerWorkedcompany);
                jobseeker = em.merge(jobseeker);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findJobseekerWorkedcompany(jobseekerWorkedcompany.getId()) != null) {
                throw new PreexistingEntityException("JobseekerWorkedcompany " + jobseekerWorkedcompany + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(JobseekerWorkedcompany jobseekerWorkedcompany) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            JobseekerWorkedcompany persistentJobseekerWorkedcompany = em.find(JobseekerWorkedcompany.class, jobseekerWorkedcompany.getId());
            Jobseeker jobseekerOld = persistentJobseekerWorkedcompany.getJobseeker();
            Jobseeker jobseekerNew = jobseekerWorkedcompany.getJobseeker();
            if (jobseekerNew != null) {
                jobseekerNew = em.getReference(jobseekerNew.getClass(), jobseekerNew.getId());
                jobseekerWorkedcompany.setJobseeker(jobseekerNew);
            }
            jobseekerWorkedcompany = em.merge(jobseekerWorkedcompany);
            if (jobseekerOld != null && !jobseekerOld.equals(jobseekerNew)) {
                jobseekerOld.getJobseekerWorkedcompanyCollection().remove(jobseekerWorkedcompany);
                jobseekerOld = em.merge(jobseekerOld);
            }
            if (jobseekerNew != null && !jobseekerNew.equals(jobseekerOld)) {
                jobseekerNew.getJobseekerWorkedcompanyCollection().add(jobseekerWorkedcompany);
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
                Integer id = jobseekerWorkedcompany.getId();
                if (findJobseekerWorkedcompany(id) == null) {
                    throw new NonexistentEntityException("The jobseekerWorkedcompany with id " + id + " no longer exists.");
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
            JobseekerWorkedcompany jobseekerWorkedcompany;
            try {
                jobseekerWorkedcompany = em.getReference(JobseekerWorkedcompany.class, id);
                jobseekerWorkedcompany.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jobseekerWorkedcompany with id " + id + " no longer exists.", enfe);
            }
            Jobseeker jobseeker = jobseekerWorkedcompany.getJobseeker();
            if (jobseeker != null) {
                jobseeker.getJobseekerWorkedcompanyCollection().remove(jobseekerWorkedcompany);
                jobseeker = em.merge(jobseeker);
            }
            em.remove(jobseekerWorkedcompany);
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

    public List<JobseekerWorkedcompany> findJobseekerWorkedcompanyEntities() {
        return findJobseekerWorkedcompanyEntities(true, -1, -1);
    }

    public List<JobseekerWorkedcompany> findJobseekerWorkedcompanyEntities(int maxResults, int firstResult) {
        return findJobseekerWorkedcompanyEntities(false, maxResults, firstResult);
    }

    private List<JobseekerWorkedcompany> findJobseekerWorkedcompanyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JobseekerWorkedcompany.class));
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

    public JobseekerWorkedcompany findJobseekerWorkedcompany(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(JobseekerWorkedcompany.class, id);
        } finally {
            em.close();
        }
    }

    public int getJobseekerWorkedcompanyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<JobseekerWorkedcompany> rt = cq.from(JobseekerWorkedcompany.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
