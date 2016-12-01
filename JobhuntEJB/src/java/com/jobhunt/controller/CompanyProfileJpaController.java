/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.controller;

import com.jobhunt.controller.exceptions.IllegalOrphanException;
import com.jobhunt.controller.exceptions.NonexistentEntityException;
import com.jobhunt.controller.exceptions.PreexistingEntityException;
import com.jobhunt.controller.exceptions.RollbackFailureException;
import com.jobhunt.entity.CompanyProfile;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jobhunt.entity.Vacancy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class CompanyProfileJpaController implements Serializable {

    public CompanyProfileJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CompanyProfile companyProfile) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companyProfile.getVacancyCollection() == null) {
            companyProfile.setVacancyCollection(new ArrayList<Vacancy>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Vacancy> attachedVacancyCollection = new ArrayList<Vacancy>();
            for (Vacancy vacancyCollectionVacancyToAttach : companyProfile.getVacancyCollection()) {
                vacancyCollectionVacancyToAttach = em.getReference(vacancyCollectionVacancyToAttach.getClass(), vacancyCollectionVacancyToAttach.getId());
                attachedVacancyCollection.add(vacancyCollectionVacancyToAttach);
            }
            companyProfile.setVacancyCollection(attachedVacancyCollection);
            em.persist(companyProfile);
            for (Vacancy vacancyCollectionVacancy : companyProfile.getVacancyCollection()) {
                CompanyProfile oldCompanyOfVacancyCollectionVacancy = vacancyCollectionVacancy.getCompany();
                vacancyCollectionVacancy.setCompany(companyProfile);
                vacancyCollectionVacancy = em.merge(vacancyCollectionVacancy);
                if (oldCompanyOfVacancyCollectionVacancy != null) {
                    oldCompanyOfVacancyCollectionVacancy.getVacancyCollection().remove(vacancyCollectionVacancy);
                    oldCompanyOfVacancyCollectionVacancy = em.merge(oldCompanyOfVacancyCollectionVacancy);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyProfile(companyProfile.getId()) != null) {
                throw new PreexistingEntityException("CompanyProfile " + companyProfile + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CompanyProfile companyProfile) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyProfile persistentCompanyProfile = em.find(CompanyProfile.class, companyProfile.getId());
            Collection<Vacancy> vacancyCollectionOld = persistentCompanyProfile.getVacancyCollection();
            Collection<Vacancy> vacancyCollectionNew = companyProfile.getVacancyCollection();
            List<String> illegalOrphanMessages = null;
            for (Vacancy vacancyCollectionOldVacancy : vacancyCollectionOld) {
                if (!vacancyCollectionNew.contains(vacancyCollectionOldVacancy)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vacancy " + vacancyCollectionOldVacancy + " since its company field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Vacancy> attachedVacancyCollectionNew = new ArrayList<Vacancy>();
            for (Vacancy vacancyCollectionNewVacancyToAttach : vacancyCollectionNew) {
                vacancyCollectionNewVacancyToAttach = em.getReference(vacancyCollectionNewVacancyToAttach.getClass(), vacancyCollectionNewVacancyToAttach.getId());
                attachedVacancyCollectionNew.add(vacancyCollectionNewVacancyToAttach);
            }
            vacancyCollectionNew = attachedVacancyCollectionNew;
            companyProfile.setVacancyCollection(vacancyCollectionNew);
            companyProfile = em.merge(companyProfile);
            for (Vacancy vacancyCollectionNewVacancy : vacancyCollectionNew) {
                if (!vacancyCollectionOld.contains(vacancyCollectionNewVacancy)) {
                    CompanyProfile oldCompanyOfVacancyCollectionNewVacancy = vacancyCollectionNewVacancy.getCompany();
                    vacancyCollectionNewVacancy.setCompany(companyProfile);
                    vacancyCollectionNewVacancy = em.merge(vacancyCollectionNewVacancy);
                    if (oldCompanyOfVacancyCollectionNewVacancy != null && !oldCompanyOfVacancyCollectionNewVacancy.equals(companyProfile)) {
                        oldCompanyOfVacancyCollectionNewVacancy.getVacancyCollection().remove(vacancyCollectionNewVacancy);
                        oldCompanyOfVacancyCollectionNewVacancy = em.merge(oldCompanyOfVacancyCollectionNewVacancy);
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
                Integer id = companyProfile.getId();
                if (findCompanyProfile(id) == null) {
                    throw new NonexistentEntityException("The companyProfile with id " + id + " no longer exists.");
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
            CompanyProfile companyProfile;
            try {
                companyProfile = em.getReference(CompanyProfile.class, id);
                companyProfile.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyProfile with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Vacancy> vacancyCollectionOrphanCheck = companyProfile.getVacancyCollection();
            for (Vacancy vacancyCollectionOrphanCheckVacancy : vacancyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This CompanyProfile (" + companyProfile + ") cannot be destroyed since the Vacancy " + vacancyCollectionOrphanCheckVacancy + " in its vacancyCollection field has a non-nullable company field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(companyProfile);
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

    public List<CompanyProfile> findCompanyProfileEntities() {
        return findCompanyProfileEntities(true, -1, -1);
    }

    public List<CompanyProfile> findCompanyProfileEntities(int maxResults, int firstResult) {
        return findCompanyProfileEntities(false, maxResults, firstResult);
    }

    private List<CompanyProfile> findCompanyProfileEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CompanyProfile.class));
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

    public CompanyProfile findCompanyProfile(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CompanyProfile.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyProfileCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CompanyProfile> rt = cq.from(CompanyProfile.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public CompanyProfile findCompanyProfileByName(String name){
        EntityManager em = getEntityManager();
        Query cq = em.createQuery("SELECT c FROM CompanyProfile c WHERE c.name = :name");
        cq.setParameter("name", name);
        try {
            CompanyProfile companyProfile = (CompanyProfile)cq.getSingleResult();
            return companyProfile;
        } catch (NoResultException e) {
            return null;
        }
    }
}
