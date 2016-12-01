/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.controller;

import com.jobhunt.controller.exceptions.IllegalOrphanException;
import com.jobhunt.controller.exceptions.NonexistentEntityException;
import com.jobhunt.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jobhunt.entity.CompanyProfile;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.entity.VacancyApply;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class VacancyJpaController implements Serializable {

    public VacancyJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vacancy vacancy) throws RollbackFailureException, Exception {
        if (vacancy.getVacancyApplyCollection() == null) {
            vacancy.setVacancyApplyCollection(new ArrayList<VacancyApply>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyProfile company = vacancy.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getId());
                vacancy.setCompany(company);
            }
            Collection<VacancyApply> attachedVacancyApplyCollection = new ArrayList<VacancyApply>();
            for (VacancyApply vacancyApplyCollectionVacancyApplyToAttach : vacancy.getVacancyApplyCollection()) {
                vacancyApplyCollectionVacancyApplyToAttach = em.getReference(vacancyApplyCollectionVacancyApplyToAttach.getClass(), vacancyApplyCollectionVacancyApplyToAttach.getId());
                attachedVacancyApplyCollection.add(vacancyApplyCollectionVacancyApplyToAttach);
            }
            vacancy.setVacancyApplyCollection(attachedVacancyApplyCollection);
            em.persist(vacancy);
            if (company != null) {
                company.getVacancyCollection().add(vacancy);
                company = em.merge(company);
            }
            for (VacancyApply vacancyApplyCollectionVacancyApply : vacancy.getVacancyApplyCollection()) {
                Vacancy oldVacancyIdOfVacancyApplyCollectionVacancyApply = vacancyApplyCollectionVacancyApply.getVacancyId();
                vacancyApplyCollectionVacancyApply.setVacancyId(vacancy);
                vacancyApplyCollectionVacancyApply = em.merge(vacancyApplyCollectionVacancyApply);
                if (oldVacancyIdOfVacancyApplyCollectionVacancyApply != null) {
                    oldVacancyIdOfVacancyApplyCollectionVacancyApply.getVacancyApplyCollection().remove(vacancyApplyCollectionVacancyApply);
                    oldVacancyIdOfVacancyApplyCollectionVacancyApply = em.merge(oldVacancyIdOfVacancyApplyCollectionVacancyApply);
                }
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

    public void edit(Vacancy vacancy) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Vacancy persistentVacancy = em.find(Vacancy.class, vacancy.getId());
            CompanyProfile companyOld = persistentVacancy.getCompany();
            CompanyProfile companyNew = vacancy.getCompany();
            Collection<VacancyApply> vacancyApplyCollectionOld = persistentVacancy.getVacancyApplyCollection();
            Collection<VacancyApply> vacancyApplyCollectionNew = vacancy.getVacancyApplyCollection();
            List<String> illegalOrphanMessages = null;
            for (VacancyApply vacancyApplyCollectionOldVacancyApply : vacancyApplyCollectionOld) {
                if (!vacancyApplyCollectionNew.contains(vacancyApplyCollectionOldVacancyApply)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VacancyApply " + vacancyApplyCollectionOldVacancyApply + " since its vacancyId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getId());
                vacancy.setCompany(companyNew);
            }
            Collection<VacancyApply> attachedVacancyApplyCollectionNew = new ArrayList<VacancyApply>();
            for (VacancyApply vacancyApplyCollectionNewVacancyApplyToAttach : vacancyApplyCollectionNew) {
                vacancyApplyCollectionNewVacancyApplyToAttach = em.getReference(vacancyApplyCollectionNewVacancyApplyToAttach.getClass(), vacancyApplyCollectionNewVacancyApplyToAttach.getId());
                attachedVacancyApplyCollectionNew.add(vacancyApplyCollectionNewVacancyApplyToAttach);
            }
            vacancyApplyCollectionNew = attachedVacancyApplyCollectionNew;
            vacancy.setVacancyApplyCollection(vacancyApplyCollectionNew);
            vacancy = em.merge(vacancy);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getVacancyCollection().remove(vacancy);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getVacancyCollection().add(vacancy);
                companyNew = em.merge(companyNew);
            }
            for (VacancyApply vacancyApplyCollectionNewVacancyApply : vacancyApplyCollectionNew) {
                if (!vacancyApplyCollectionOld.contains(vacancyApplyCollectionNewVacancyApply)) {
                    Vacancy oldVacancyIdOfVacancyApplyCollectionNewVacancyApply = vacancyApplyCollectionNewVacancyApply.getVacancyId();
                    vacancyApplyCollectionNewVacancyApply.setVacancyId(vacancy);
                    vacancyApplyCollectionNewVacancyApply = em.merge(vacancyApplyCollectionNewVacancyApply);
                    if (oldVacancyIdOfVacancyApplyCollectionNewVacancyApply != null && !oldVacancyIdOfVacancyApplyCollectionNewVacancyApply.equals(vacancy)) {
                        oldVacancyIdOfVacancyApplyCollectionNewVacancyApply.getVacancyApplyCollection().remove(vacancyApplyCollectionNewVacancyApply);
                        oldVacancyIdOfVacancyApplyCollectionNewVacancyApply = em.merge(oldVacancyIdOfVacancyApplyCollectionNewVacancyApply);
                    }
                }
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
                Integer id = vacancy.getId();
                if (findVacancy(id) == null) {
                    throw new NonexistentEntityException("The vacancy with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Vacancy vacancy;
            try {
                vacancy = em.getReference(Vacancy.class, id);
                vacancy.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vacancy with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<VacancyApply> vacancyApplyCollectionOrphanCheck = vacancy.getVacancyApplyCollection();
            for (VacancyApply vacancyApplyCollectionOrphanCheckVacancyApply : vacancyApplyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vacancy (" + vacancy + ") cannot be destroyed since the VacancyApply " + vacancyApplyCollectionOrphanCheckVacancyApply + " in its vacancyApplyCollection field has a non-nullable vacancyId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyProfile company = vacancy.getCompany();
            if (company != null) {
                company.getVacancyCollection().remove(vacancy);
                company = em.merge(company);
            }
            em.remove(vacancy);
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

    public List<Vacancy> findVacancyEntities() {
        return findVacancyEntities(true, -1, -1);
    }

    public List<Vacancy> findVacancyEntities(int maxResults, int firstResult) {
        return findVacancyEntities(false, maxResults, firstResult);
    }

    private List<Vacancy> findVacancyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vacancy.class));
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

    public Vacancy findVacancy(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vacancy.class, id);
        } finally {
            em.close();
        }
    }

    public int getVacancyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vacancy> rt = cq.from(Vacancy.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Vacancy> findVacanciesByJobseekerPreferences(String job, String salary, String area) {
        EntityManager em = getEntityManager();
        TypedQuery<Vacancy> cq = em.createQuery("SELECT v FROM Vacancy v WHERE v.title like :job AND v.branch like :area AND v.salary " + salary, Vacancy.class);
        cq.setParameter("job", job);
        cq.setParameter("area", area);
        return cq.getResultList();
    }
    
    public List<Vacancy> findVacanciesByCompanyId(Integer id) {
        CompanyProfile companyProfile = new CompanyProfileJpaController(utx, emf).findCompanyProfile(id);
        EntityManager em = getEntityManager();
        TypedQuery<Vacancy> cq = em.createQuery("SELECT v FROM Vacancy v WHERE v.company = :company",Vacancy.class);
        cq.setParameter("company", companyProfile);
        return cq.getResultList();
    }

}
