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
import com.jobhunt.entity.JobseekerUniversity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class JobseekerUniversityJpaController implements Serializable {

    public JobseekerUniversityJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(JobseekerUniversity jobseekerUniversity) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Jobseeker jobseekerEducation = jobseekerUniversity.getJobseekerEducation();
            if (jobseekerEducation != null) {
                jobseekerEducation = em.getReference(jobseekerEducation.getClass(), jobseekerEducation.getId());
                jobseekerUniversity.setJobseekerEducation(jobseekerEducation);
            }
            em.persist(jobseekerUniversity);
            if (jobseekerEducation != null) {
                jobseekerEducation.getJobseekerUniversityCollection().add(jobseekerUniversity);
                jobseekerEducation = em.merge(jobseekerEducation);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findJobseekerUniversity(jobseekerUniversity.getUniversityId()) != null) {
                throw new PreexistingEntityException("JobseekerUniversity " + jobseekerUniversity + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(JobseekerUniversity jobseekerUniversity) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            JobseekerUniversity persistentJobseekerUniversity = em.find(JobseekerUniversity.class, jobseekerUniversity.getUniversityId());
            Jobseeker jobseekerEducationOld = persistentJobseekerUniversity.getJobseekerEducation();
            Jobseeker jobseekerEducationNew = jobseekerUniversity.getJobseekerEducation();
            if (jobseekerEducationNew != null) {
                jobseekerEducationNew = em.getReference(jobseekerEducationNew.getClass(), jobseekerEducationNew.getId());
                jobseekerUniversity.setJobseekerEducation(jobseekerEducationNew);
            }
            jobseekerUniversity = em.merge(jobseekerUniversity);
            if (jobseekerEducationOld != null && !jobseekerEducationOld.equals(jobseekerEducationNew)) {
                jobseekerEducationOld.getJobseekerUniversityCollection().remove(jobseekerUniversity);
                jobseekerEducationOld = em.merge(jobseekerEducationOld);
            }
            if (jobseekerEducationNew != null && !jobseekerEducationNew.equals(jobseekerEducationOld)) {
                jobseekerEducationNew.getJobseekerUniversityCollection().add(jobseekerUniversity);
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
                Integer id = jobseekerUniversity.getUniversityId();
                if (findJobseekerUniversity(id) == null) {
                    throw new NonexistentEntityException("The jobseekerUniversity with id " + id + " no longer exists.");
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
            JobseekerUniversity jobseekerUniversity;
            try {
                jobseekerUniversity = em.getReference(JobseekerUniversity.class, id);
                jobseekerUniversity.getUniversityId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jobseekerUniversity with id " + id + " no longer exists.", enfe);
            }
            Jobseeker jobseekerEducation = jobseekerUniversity.getJobseekerEducation();
            if (jobseekerEducation != null) {
                jobseekerEducation.getJobseekerUniversityCollection().remove(jobseekerUniversity);
                jobseekerEducation = em.merge(jobseekerEducation);
            }
            em.remove(jobseekerUniversity);
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

    public List<JobseekerUniversity> findJobseekerUniversityEntities() {
        return findJobseekerUniversityEntities(true, -1, -1);
    }

    public List<JobseekerUniversity> findJobseekerUniversityEntities(int maxResults, int firstResult) {
        return findJobseekerUniversityEntities(false, maxResults, firstResult);
    }

    private List<JobseekerUniversity> findJobseekerUniversityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(JobseekerUniversity.class));
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

    public JobseekerUniversity findJobseekerUniversity(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(JobseekerUniversity.class, id);
        } finally {
            em.close();
        }
    }

    public int getJobseekerUniversityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<JobseekerUniversity> rt = cq.from(JobseekerUniversity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
