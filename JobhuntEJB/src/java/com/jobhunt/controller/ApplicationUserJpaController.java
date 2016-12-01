/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.controller;

import com.jobhunt.controller.exceptions.NonexistentEntityException;
import com.jobhunt.controller.exceptions.RollbackFailureException;
import com.jobhunt.entity.ApplicationUser;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class ApplicationUserJpaController implements Serializable {

    public ApplicationUserJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ApplicationUser applicationUser) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(applicationUser);
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

    public void edit(ApplicationUser applicationUser) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            applicationUser = em.merge(applicationUser);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = applicationUser.getId();
                if (findApplicationUser(id) == null) {
                    throw new NonexistentEntityException("The applicationUser with id " + id + " no longer exists.");
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
            ApplicationUser applicationUser;
            try {
                applicationUser = em.getReference(ApplicationUser.class, id);
                applicationUser.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The applicationUser with id " + id + " no longer exists.", enfe);
            }
            em.remove(applicationUser);
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

    public List<ApplicationUser> findApplicationUserEntities() {
        return findApplicationUserEntities(true, -1, -1);
    }

    public List<ApplicationUser> findApplicationUserEntities(int maxResults, int firstResult) {
        return findApplicationUserEntities(false, maxResults, firstResult);
    }

    private List<ApplicationUser> findApplicationUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ApplicationUser.class));
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

    public ApplicationUser findApplicationUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ApplicationUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getApplicationUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ApplicationUser> rt = cq.from(ApplicationUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public ApplicationUser findApplicationUserByUsernamePassword(String username, String password) {
        EntityManager em = getEntityManager();
        Query cq = em.createQuery("SELECT a FROM ApplicationUser a WHERE a.username = :username AND a.password = :password");
        cq.setParameter("username", username);
        cq.setParameter("password", password);
        try {
            return (ApplicationUser) cq.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public Boolean findUsernameExistance(String username) {
        EntityManager em = getEntityManager();
        Query cq = em.createQuery("SELECT a FROM ApplicationUser a WHERE a.username = :username");
        cq.setParameter("username", username);
        try {
            Object user = cq.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public Boolean changePassword(Integer id, String password) {
        ApplicationUser applicationUser = new ApplicationUserJpaController(utx, emf).findApplicationUser(id);
        applicationUser.setPassword(password);
        try {
            new ApplicationUserJpaController(utx, emf).edit(applicationUser);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }
}
