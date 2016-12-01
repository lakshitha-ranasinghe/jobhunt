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
import com.jobhunt.entity.Jobseeker;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jobhunt.entity.JobseekerOl;
import java.util.ArrayList;
import java.util.Collection;
import com.jobhunt.entity.JobseekerEducation;
import com.jobhunt.entity.JobseekerAl;
import com.jobhunt.entity.JobseekerWorkedcompany;
import com.jobhunt.entity.JobseekerUniversity;
import com.jobhunt.entity.JobseekerOther;
import com.jobhunt.entity.JobseekerExperience;
import com.jobhunt.entity.VacancyApply;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
public class JobseekerJpaController implements Serializable {

    public JobseekerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Jobseeker jobseeker) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (jobseeker.getJobseekerOlCollection() == null) {
            jobseeker.setJobseekerOlCollection(new ArrayList<JobseekerOl>());
        }
        if (jobseeker.getJobseekerEducationCollection() == null) {
            jobseeker.setJobseekerEducationCollection(new ArrayList<JobseekerEducation>());
        }
        if (jobseeker.getJobseekerAlCollection() == null) {
            jobseeker.setJobseekerAlCollection(new ArrayList<JobseekerAl>());
        }
        if (jobseeker.getJobseekerWorkedcompanyCollection() == null) {
            jobseeker.setJobseekerWorkedcompanyCollection(new ArrayList<JobseekerWorkedcompany>());
        }
        if (jobseeker.getJobseekerUniversityCollection() == null) {
            jobseeker.setJobseekerUniversityCollection(new ArrayList<JobseekerUniversity>());
        }
        if (jobseeker.getJobseekerOtherCollection() == null) {
            jobseeker.setJobseekerOtherCollection(new ArrayList<JobseekerOther>());
        }
        if (jobseeker.getJobseekerExperienceCollection() == null) {
            jobseeker.setJobseekerExperienceCollection(new ArrayList<JobseekerExperience>());
        }
        if (jobseeker.getVacancyApplyCollection() == null) {
            jobseeker.setVacancyApplyCollection(new ArrayList<VacancyApply>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<JobseekerOl> attachedJobseekerOlCollection = new ArrayList<JobseekerOl>();
            for (JobseekerOl jobseekerOlCollectionJobseekerOlToAttach : jobseeker.getJobseekerOlCollection()) {
                jobseekerOlCollectionJobseekerOlToAttach = em.getReference(jobseekerOlCollectionJobseekerOlToAttach.getClass(), jobseekerOlCollectionJobseekerOlToAttach.getOlId());
                attachedJobseekerOlCollection.add(jobseekerOlCollectionJobseekerOlToAttach);
            }
            jobseeker.setJobseekerOlCollection(attachedJobseekerOlCollection);
            Collection<JobseekerEducation> attachedJobseekerEducationCollection = new ArrayList<JobseekerEducation>();
            for (JobseekerEducation jobseekerEducationCollectionJobseekerEducationToAttach : jobseeker.getJobseekerEducationCollection()) {
                jobseekerEducationCollectionJobseekerEducationToAttach = em.getReference(jobseekerEducationCollectionJobseekerEducationToAttach.getClass(), jobseekerEducationCollectionJobseekerEducationToAttach.getId());
                attachedJobseekerEducationCollection.add(jobseekerEducationCollectionJobseekerEducationToAttach);
            }
            jobseeker.setJobseekerEducationCollection(attachedJobseekerEducationCollection);
            Collection<JobseekerAl> attachedJobseekerAlCollection = new ArrayList<JobseekerAl>();
            for (JobseekerAl jobseekerAlCollectionJobseekerAlToAttach : jobseeker.getJobseekerAlCollection()) {
                jobseekerAlCollectionJobseekerAlToAttach = em.getReference(jobseekerAlCollectionJobseekerAlToAttach.getClass(), jobseekerAlCollectionJobseekerAlToAttach.getAlId());
                attachedJobseekerAlCollection.add(jobseekerAlCollectionJobseekerAlToAttach);
            }
            jobseeker.setJobseekerAlCollection(attachedJobseekerAlCollection);
            Collection<JobseekerWorkedcompany> attachedJobseekerWorkedcompanyCollection = new ArrayList<JobseekerWorkedcompany>();
            for (JobseekerWorkedcompany jobseekerWorkedcompanyCollectionJobseekerWorkedcompanyToAttach : jobseeker.getJobseekerWorkedcompanyCollection()) {
                jobseekerWorkedcompanyCollectionJobseekerWorkedcompanyToAttach = em.getReference(jobseekerWorkedcompanyCollectionJobseekerWorkedcompanyToAttach.getClass(), jobseekerWorkedcompanyCollectionJobseekerWorkedcompanyToAttach.getId());
                attachedJobseekerWorkedcompanyCollection.add(jobseekerWorkedcompanyCollectionJobseekerWorkedcompanyToAttach);
            }
            jobseeker.setJobseekerWorkedcompanyCollection(attachedJobseekerWorkedcompanyCollection);
            Collection<JobseekerUniversity> attachedJobseekerUniversityCollection = new ArrayList<JobseekerUniversity>();
            for (JobseekerUniversity jobseekerUniversityCollectionJobseekerUniversityToAttach : jobseeker.getJobseekerUniversityCollection()) {
                jobseekerUniversityCollectionJobseekerUniversityToAttach = em.getReference(jobseekerUniversityCollectionJobseekerUniversityToAttach.getClass(), jobseekerUniversityCollectionJobseekerUniversityToAttach.getUniversityId());
                attachedJobseekerUniversityCollection.add(jobseekerUniversityCollectionJobseekerUniversityToAttach);
            }
            jobseeker.setJobseekerUniversityCollection(attachedJobseekerUniversityCollection);
            Collection<JobseekerOther> attachedJobseekerOtherCollection = new ArrayList<JobseekerOther>();
            for (JobseekerOther jobseekerOtherCollectionJobseekerOtherToAttach : jobseeker.getJobseekerOtherCollection()) {
                jobseekerOtherCollectionJobseekerOtherToAttach = em.getReference(jobseekerOtherCollectionJobseekerOtherToAttach.getClass(), jobseekerOtherCollectionJobseekerOtherToAttach.getId());
                attachedJobseekerOtherCollection.add(jobseekerOtherCollectionJobseekerOtherToAttach);
            }
            jobseeker.setJobseekerOtherCollection(attachedJobseekerOtherCollection);
            Collection<JobseekerExperience> attachedJobseekerExperienceCollection = new ArrayList<JobseekerExperience>();
            for (JobseekerExperience jobseekerExperienceCollectionJobseekerExperienceToAttach : jobseeker.getJobseekerExperienceCollection()) {
                jobseekerExperienceCollectionJobseekerExperienceToAttach = em.getReference(jobseekerExperienceCollectionJobseekerExperienceToAttach.getClass(), jobseekerExperienceCollectionJobseekerExperienceToAttach.getId());
                attachedJobseekerExperienceCollection.add(jobseekerExperienceCollectionJobseekerExperienceToAttach);
            }
            jobseeker.setJobseekerExperienceCollection(attachedJobseekerExperienceCollection);
            Collection<VacancyApply> attachedVacancyApplyCollection = new ArrayList<VacancyApply>();
            for (VacancyApply vacancyApplyCollectionVacancyApplyToAttach : jobseeker.getVacancyApplyCollection()) {
                vacancyApplyCollectionVacancyApplyToAttach = em.getReference(vacancyApplyCollectionVacancyApplyToAttach.getClass(), vacancyApplyCollectionVacancyApplyToAttach.getId());
                attachedVacancyApplyCollection.add(vacancyApplyCollectionVacancyApplyToAttach);
            }
            jobseeker.setVacancyApplyCollection(attachedVacancyApplyCollection);
            em.persist(jobseeker);
            for (JobseekerOl jobseekerOlCollectionJobseekerOl : jobseeker.getJobseekerOlCollection()) {
                Jobseeker oldJobseekerEducationOfJobseekerOlCollectionJobseekerOl = jobseekerOlCollectionJobseekerOl.getJobseekerEducation();
                jobseekerOlCollectionJobseekerOl.setJobseekerEducation(jobseeker);
                jobseekerOlCollectionJobseekerOl = em.merge(jobseekerOlCollectionJobseekerOl);
                if (oldJobseekerEducationOfJobseekerOlCollectionJobseekerOl != null) {
                    oldJobseekerEducationOfJobseekerOlCollectionJobseekerOl.getJobseekerOlCollection().remove(jobseekerOlCollectionJobseekerOl);
                    oldJobseekerEducationOfJobseekerOlCollectionJobseekerOl = em.merge(oldJobseekerEducationOfJobseekerOlCollectionJobseekerOl);
                }
            }
            for (JobseekerEducation jobseekerEducationCollectionJobseekerEducation : jobseeker.getJobseekerEducationCollection()) {
                Jobseeker oldJobseekerOfJobseekerEducationCollectionJobseekerEducation = jobseekerEducationCollectionJobseekerEducation.getJobseeker();
                jobseekerEducationCollectionJobseekerEducation.setJobseeker(jobseeker);
                jobseekerEducationCollectionJobseekerEducation = em.merge(jobseekerEducationCollectionJobseekerEducation);
                if (oldJobseekerOfJobseekerEducationCollectionJobseekerEducation != null) {
                    oldJobseekerOfJobseekerEducationCollectionJobseekerEducation.getJobseekerEducationCollection().remove(jobseekerEducationCollectionJobseekerEducation);
                    oldJobseekerOfJobseekerEducationCollectionJobseekerEducation = em.merge(oldJobseekerOfJobseekerEducationCollectionJobseekerEducation);
                }
            }
            for (JobseekerAl jobseekerAlCollectionJobseekerAl : jobseeker.getJobseekerAlCollection()) {
                Jobseeker oldJobseekerEducationOfJobseekerAlCollectionJobseekerAl = jobseekerAlCollectionJobseekerAl.getJobseekerEducation();
                jobseekerAlCollectionJobseekerAl.setJobseekerEducation(jobseeker);
                jobseekerAlCollectionJobseekerAl = em.merge(jobseekerAlCollectionJobseekerAl);
                if (oldJobseekerEducationOfJobseekerAlCollectionJobseekerAl != null) {
                    oldJobseekerEducationOfJobseekerAlCollectionJobseekerAl.getJobseekerAlCollection().remove(jobseekerAlCollectionJobseekerAl);
                    oldJobseekerEducationOfJobseekerAlCollectionJobseekerAl = em.merge(oldJobseekerEducationOfJobseekerAlCollectionJobseekerAl);
                }
            }
            for (JobseekerWorkedcompany jobseekerWorkedcompanyCollectionJobseekerWorkedcompany : jobseeker.getJobseekerWorkedcompanyCollection()) {
                Jobseeker oldJobseekerOfJobseekerWorkedcompanyCollectionJobseekerWorkedcompany = jobseekerWorkedcompanyCollectionJobseekerWorkedcompany.getJobseeker();
                jobseekerWorkedcompanyCollectionJobseekerWorkedcompany.setJobseeker(jobseeker);
                jobseekerWorkedcompanyCollectionJobseekerWorkedcompany = em.merge(jobseekerWorkedcompanyCollectionJobseekerWorkedcompany);
                if (oldJobseekerOfJobseekerWorkedcompanyCollectionJobseekerWorkedcompany != null) {
                    oldJobseekerOfJobseekerWorkedcompanyCollectionJobseekerWorkedcompany.getJobseekerWorkedcompanyCollection().remove(jobseekerWorkedcompanyCollectionJobseekerWorkedcompany);
                    oldJobseekerOfJobseekerWorkedcompanyCollectionJobseekerWorkedcompany = em.merge(oldJobseekerOfJobseekerWorkedcompanyCollectionJobseekerWorkedcompany);
                }
            }
            for (JobseekerUniversity jobseekerUniversityCollectionJobseekerUniversity : jobseeker.getJobseekerUniversityCollection()) {
                Jobseeker oldJobseekerEducationOfJobseekerUniversityCollectionJobseekerUniversity = jobseekerUniversityCollectionJobseekerUniversity.getJobseekerEducation();
                jobseekerUniversityCollectionJobseekerUniversity.setJobseekerEducation(jobseeker);
                jobseekerUniversityCollectionJobseekerUniversity = em.merge(jobseekerUniversityCollectionJobseekerUniversity);
                if (oldJobseekerEducationOfJobseekerUniversityCollectionJobseekerUniversity != null) {
                    oldJobseekerEducationOfJobseekerUniversityCollectionJobseekerUniversity.getJobseekerUniversityCollection().remove(jobseekerUniversityCollectionJobseekerUniversity);
                    oldJobseekerEducationOfJobseekerUniversityCollectionJobseekerUniversity = em.merge(oldJobseekerEducationOfJobseekerUniversityCollectionJobseekerUniversity);
                }
            }
            for (JobseekerOther jobseekerOtherCollectionJobseekerOther : jobseeker.getJobseekerOtherCollection()) {
                Jobseeker oldJobseekerOfJobseekerOtherCollectionJobseekerOther = jobseekerOtherCollectionJobseekerOther.getJobseeker();
                jobseekerOtherCollectionJobseekerOther.setJobseeker(jobseeker);
                jobseekerOtherCollectionJobseekerOther = em.merge(jobseekerOtherCollectionJobseekerOther);
                if (oldJobseekerOfJobseekerOtherCollectionJobseekerOther != null) {
                    oldJobseekerOfJobseekerOtherCollectionJobseekerOther.getJobseekerOtherCollection().remove(jobseekerOtherCollectionJobseekerOther);
                    oldJobseekerOfJobseekerOtherCollectionJobseekerOther = em.merge(oldJobseekerOfJobseekerOtherCollectionJobseekerOther);
                }
            }
            for (JobseekerExperience jobseekerExperienceCollectionJobseekerExperience : jobseeker.getJobseekerExperienceCollection()) {
                Jobseeker oldJobseekerOfJobseekerExperienceCollectionJobseekerExperience = jobseekerExperienceCollectionJobseekerExperience.getJobseeker();
                jobseekerExperienceCollectionJobseekerExperience.setJobseeker(jobseeker);
                jobseekerExperienceCollectionJobseekerExperience = em.merge(jobseekerExperienceCollectionJobseekerExperience);
                if (oldJobseekerOfJobseekerExperienceCollectionJobseekerExperience != null) {
                    oldJobseekerOfJobseekerExperienceCollectionJobseekerExperience.getJobseekerExperienceCollection().remove(jobseekerExperienceCollectionJobseekerExperience);
                    oldJobseekerOfJobseekerExperienceCollectionJobseekerExperience = em.merge(oldJobseekerOfJobseekerExperienceCollectionJobseekerExperience);
                }
            }
            for (VacancyApply vacancyApplyCollectionVacancyApply : jobseeker.getVacancyApplyCollection()) {
                Jobseeker oldJobseekerIdOfVacancyApplyCollectionVacancyApply = vacancyApplyCollectionVacancyApply.getJobseekerId();
                vacancyApplyCollectionVacancyApply.setJobseekerId(jobseeker);
                vacancyApplyCollectionVacancyApply = em.merge(vacancyApplyCollectionVacancyApply);
                if (oldJobseekerIdOfVacancyApplyCollectionVacancyApply != null) {
                    oldJobseekerIdOfVacancyApplyCollectionVacancyApply.getVacancyApplyCollection().remove(vacancyApplyCollectionVacancyApply);
                    oldJobseekerIdOfVacancyApplyCollectionVacancyApply = em.merge(oldJobseekerIdOfVacancyApplyCollectionVacancyApply);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findJobseeker(jobseeker.getId()) != null) {
                throw new PreexistingEntityException("Jobseeker " + jobseeker + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Jobseeker jobseeker) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Jobseeker persistentJobseeker = em.find(Jobseeker.class, jobseeker.getId());
            Collection<JobseekerOl> jobseekerOlCollectionOld = persistentJobseeker.getJobseekerOlCollection();
            Collection<JobseekerOl> jobseekerOlCollectionNew = jobseeker.getJobseekerOlCollection();
            Collection<JobseekerEducation> jobseekerEducationCollectionOld = persistentJobseeker.getJobseekerEducationCollection();
            Collection<JobseekerEducation> jobseekerEducationCollectionNew = jobseeker.getJobseekerEducationCollection();
            Collection<JobseekerAl> jobseekerAlCollectionOld = persistentJobseeker.getJobseekerAlCollection();
            Collection<JobseekerAl> jobseekerAlCollectionNew = jobseeker.getJobseekerAlCollection();
            Collection<JobseekerWorkedcompany> jobseekerWorkedcompanyCollectionOld = persistentJobseeker.getJobseekerWorkedcompanyCollection();
            Collection<JobseekerWorkedcompany> jobseekerWorkedcompanyCollectionNew = jobseeker.getJobseekerWorkedcompanyCollection();
            Collection<JobseekerUniversity> jobseekerUniversityCollectionOld = persistentJobseeker.getJobseekerUniversityCollection();
            Collection<JobseekerUniversity> jobseekerUniversityCollectionNew = jobseeker.getJobseekerUniversityCollection();
            Collection<JobseekerOther> jobseekerOtherCollectionOld = persistentJobseeker.getJobseekerOtherCollection();
            Collection<JobseekerOther> jobseekerOtherCollectionNew = jobseeker.getJobseekerOtherCollection();
            Collection<JobseekerExperience> jobseekerExperienceCollectionOld = persistentJobseeker.getJobseekerExperienceCollection();
            Collection<JobseekerExperience> jobseekerExperienceCollectionNew = jobseeker.getJobseekerExperienceCollection();
            Collection<VacancyApply> vacancyApplyCollectionOld = persistentJobseeker.getVacancyApplyCollection();
            Collection<VacancyApply> vacancyApplyCollectionNew = jobseeker.getVacancyApplyCollection();
            List<String> illegalOrphanMessages = null;
            for (JobseekerOl jobseekerOlCollectionOldJobseekerOl : jobseekerOlCollectionOld) {
                if (!jobseekerOlCollectionNew.contains(jobseekerOlCollectionOldJobseekerOl)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain JobseekerOl " + jobseekerOlCollectionOldJobseekerOl + " since its jobseekerEducation field is not nullable.");
                }
            }
            for (JobseekerEducation jobseekerEducationCollectionOldJobseekerEducation : jobseekerEducationCollectionOld) {
                if (!jobseekerEducationCollectionNew.contains(jobseekerEducationCollectionOldJobseekerEducation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain JobseekerEducation " + jobseekerEducationCollectionOldJobseekerEducation + " since its jobseeker field is not nullable.");
                }
            }
            for (JobseekerAl jobseekerAlCollectionOldJobseekerAl : jobseekerAlCollectionOld) {
                if (!jobseekerAlCollectionNew.contains(jobseekerAlCollectionOldJobseekerAl)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain JobseekerAl " + jobseekerAlCollectionOldJobseekerAl + " since its jobseekerEducation field is not nullable.");
                }
            }
            for (JobseekerWorkedcompany jobseekerWorkedcompanyCollectionOldJobseekerWorkedcompany : jobseekerWorkedcompanyCollectionOld) {
                if (!jobseekerWorkedcompanyCollectionNew.contains(jobseekerWorkedcompanyCollectionOldJobseekerWorkedcompany)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain JobseekerWorkedcompany " + jobseekerWorkedcompanyCollectionOldJobseekerWorkedcompany + " since its jobseeker field is not nullable.");
                }
            }
            for (JobseekerUniversity jobseekerUniversityCollectionOldJobseekerUniversity : jobseekerUniversityCollectionOld) {
                if (!jobseekerUniversityCollectionNew.contains(jobseekerUniversityCollectionOldJobseekerUniversity)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain JobseekerUniversity " + jobseekerUniversityCollectionOldJobseekerUniversity + " since its jobseekerEducation field is not nullable.");
                }
            }
            for (JobseekerOther jobseekerOtherCollectionOldJobseekerOther : jobseekerOtherCollectionOld) {
                if (!jobseekerOtherCollectionNew.contains(jobseekerOtherCollectionOldJobseekerOther)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain JobseekerOther " + jobseekerOtherCollectionOldJobseekerOther + " since its jobseeker field is not nullable.");
                }
            }
            for (JobseekerExperience jobseekerExperienceCollectionOldJobseekerExperience : jobseekerExperienceCollectionOld) {
                if (!jobseekerExperienceCollectionNew.contains(jobseekerExperienceCollectionOldJobseekerExperience)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain JobseekerExperience " + jobseekerExperienceCollectionOldJobseekerExperience + " since its jobseeker field is not nullable.");
                }
            }
            for (VacancyApply vacancyApplyCollectionOldVacancyApply : vacancyApplyCollectionOld) {
                if (!vacancyApplyCollectionNew.contains(vacancyApplyCollectionOldVacancyApply)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain VacancyApply " + vacancyApplyCollectionOldVacancyApply + " since its jobseekerId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<JobseekerOl> attachedJobseekerOlCollectionNew = new ArrayList<JobseekerOl>();
            for (JobseekerOl jobseekerOlCollectionNewJobseekerOlToAttach : jobseekerOlCollectionNew) {
                jobseekerOlCollectionNewJobseekerOlToAttach = em.getReference(jobseekerOlCollectionNewJobseekerOlToAttach.getClass(), jobseekerOlCollectionNewJobseekerOlToAttach.getOlId());
                attachedJobseekerOlCollectionNew.add(jobseekerOlCollectionNewJobseekerOlToAttach);
            }
            jobseekerOlCollectionNew = attachedJobseekerOlCollectionNew;
            jobseeker.setJobseekerOlCollection(jobseekerOlCollectionNew);
            Collection<JobseekerEducation> attachedJobseekerEducationCollectionNew = new ArrayList<JobseekerEducation>();
            for (JobseekerEducation jobseekerEducationCollectionNewJobseekerEducationToAttach : jobseekerEducationCollectionNew) {
                jobseekerEducationCollectionNewJobseekerEducationToAttach = em.getReference(jobseekerEducationCollectionNewJobseekerEducationToAttach.getClass(), jobseekerEducationCollectionNewJobseekerEducationToAttach.getId());
                attachedJobseekerEducationCollectionNew.add(jobseekerEducationCollectionNewJobseekerEducationToAttach);
            }
            jobseekerEducationCollectionNew = attachedJobseekerEducationCollectionNew;
            jobseeker.setJobseekerEducationCollection(jobseekerEducationCollectionNew);
            Collection<JobseekerAl> attachedJobseekerAlCollectionNew = new ArrayList<JobseekerAl>();
            for (JobseekerAl jobseekerAlCollectionNewJobseekerAlToAttach : jobseekerAlCollectionNew) {
                jobseekerAlCollectionNewJobseekerAlToAttach = em.getReference(jobseekerAlCollectionNewJobseekerAlToAttach.getClass(), jobseekerAlCollectionNewJobseekerAlToAttach.getAlId());
                attachedJobseekerAlCollectionNew.add(jobseekerAlCollectionNewJobseekerAlToAttach);
            }
            jobseekerAlCollectionNew = attachedJobseekerAlCollectionNew;
            jobseeker.setJobseekerAlCollection(jobseekerAlCollectionNew);
            Collection<JobseekerWorkedcompany> attachedJobseekerWorkedcompanyCollectionNew = new ArrayList<JobseekerWorkedcompany>();
            for (JobseekerWorkedcompany jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompanyToAttach : jobseekerWorkedcompanyCollectionNew) {
                jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompanyToAttach = em.getReference(jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompanyToAttach.getClass(), jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompanyToAttach.getId());
                attachedJobseekerWorkedcompanyCollectionNew.add(jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompanyToAttach);
            }
            jobseekerWorkedcompanyCollectionNew = attachedJobseekerWorkedcompanyCollectionNew;
            jobseeker.setJobseekerWorkedcompanyCollection(jobseekerWorkedcompanyCollectionNew);
            Collection<JobseekerUniversity> attachedJobseekerUniversityCollectionNew = new ArrayList<JobseekerUniversity>();
            for (JobseekerUniversity jobseekerUniversityCollectionNewJobseekerUniversityToAttach : jobseekerUniversityCollectionNew) {
                jobseekerUniversityCollectionNewJobseekerUniversityToAttach = em.getReference(jobseekerUniversityCollectionNewJobseekerUniversityToAttach.getClass(), jobseekerUniversityCollectionNewJobseekerUniversityToAttach.getUniversityId());
                attachedJobseekerUniversityCollectionNew.add(jobseekerUniversityCollectionNewJobseekerUniversityToAttach);
            }
            jobseekerUniversityCollectionNew = attachedJobseekerUniversityCollectionNew;
            jobseeker.setJobseekerUniversityCollection(jobseekerUniversityCollectionNew);
            Collection<JobseekerOther> attachedJobseekerOtherCollectionNew = new ArrayList<JobseekerOther>();
            for (JobseekerOther jobseekerOtherCollectionNewJobseekerOtherToAttach : jobseekerOtherCollectionNew) {
                jobseekerOtherCollectionNewJobseekerOtherToAttach = em.getReference(jobseekerOtherCollectionNewJobseekerOtherToAttach.getClass(), jobseekerOtherCollectionNewJobseekerOtherToAttach.getId());
                attachedJobseekerOtherCollectionNew.add(jobseekerOtherCollectionNewJobseekerOtherToAttach);
            }
            jobseekerOtherCollectionNew = attachedJobseekerOtherCollectionNew;
            jobseeker.setJobseekerOtherCollection(jobseekerOtherCollectionNew);
            Collection<JobseekerExperience> attachedJobseekerExperienceCollectionNew = new ArrayList<JobseekerExperience>();
            for (JobseekerExperience jobseekerExperienceCollectionNewJobseekerExperienceToAttach : jobseekerExperienceCollectionNew) {
                jobseekerExperienceCollectionNewJobseekerExperienceToAttach = em.getReference(jobseekerExperienceCollectionNewJobseekerExperienceToAttach.getClass(), jobseekerExperienceCollectionNewJobseekerExperienceToAttach.getId());
                attachedJobseekerExperienceCollectionNew.add(jobseekerExperienceCollectionNewJobseekerExperienceToAttach);
            }
            jobseekerExperienceCollectionNew = attachedJobseekerExperienceCollectionNew;
            jobseeker.setJobseekerExperienceCollection(jobseekerExperienceCollectionNew);
            Collection<VacancyApply> attachedVacancyApplyCollectionNew = new ArrayList<VacancyApply>();
            for (VacancyApply vacancyApplyCollectionNewVacancyApplyToAttach : vacancyApplyCollectionNew) {
                vacancyApplyCollectionNewVacancyApplyToAttach = em.getReference(vacancyApplyCollectionNewVacancyApplyToAttach.getClass(), vacancyApplyCollectionNewVacancyApplyToAttach.getId());
                attachedVacancyApplyCollectionNew.add(vacancyApplyCollectionNewVacancyApplyToAttach);
            }
            vacancyApplyCollectionNew = attachedVacancyApplyCollectionNew;
            jobseeker.setVacancyApplyCollection(vacancyApplyCollectionNew);
            jobseeker = em.merge(jobseeker);
            for (JobseekerOl jobseekerOlCollectionNewJobseekerOl : jobseekerOlCollectionNew) {
                if (!jobseekerOlCollectionOld.contains(jobseekerOlCollectionNewJobseekerOl)) {
                    Jobseeker oldJobseekerEducationOfJobseekerOlCollectionNewJobseekerOl = jobseekerOlCollectionNewJobseekerOl.getJobseekerEducation();
                    jobseekerOlCollectionNewJobseekerOl.setJobseekerEducation(jobseeker);
                    jobseekerOlCollectionNewJobseekerOl = em.merge(jobseekerOlCollectionNewJobseekerOl);
                    if (oldJobseekerEducationOfJobseekerOlCollectionNewJobseekerOl != null && !oldJobseekerEducationOfJobseekerOlCollectionNewJobseekerOl.equals(jobseeker)) {
                        oldJobseekerEducationOfJobseekerOlCollectionNewJobseekerOl.getJobseekerOlCollection().remove(jobseekerOlCollectionNewJobseekerOl);
                        oldJobseekerEducationOfJobseekerOlCollectionNewJobseekerOl = em.merge(oldJobseekerEducationOfJobseekerOlCollectionNewJobseekerOl);
                    }
                }
            }
            for (JobseekerEducation jobseekerEducationCollectionNewJobseekerEducation : jobseekerEducationCollectionNew) {
                if (!jobseekerEducationCollectionOld.contains(jobseekerEducationCollectionNewJobseekerEducation)) {
                    Jobseeker oldJobseekerOfJobseekerEducationCollectionNewJobseekerEducation = jobseekerEducationCollectionNewJobseekerEducation.getJobseeker();
                    jobseekerEducationCollectionNewJobseekerEducation.setJobseeker(jobseeker);
                    jobseekerEducationCollectionNewJobseekerEducation = em.merge(jobseekerEducationCollectionNewJobseekerEducation);
                    if (oldJobseekerOfJobseekerEducationCollectionNewJobseekerEducation != null && !oldJobseekerOfJobseekerEducationCollectionNewJobseekerEducation.equals(jobseeker)) {
                        oldJobseekerOfJobseekerEducationCollectionNewJobseekerEducation.getJobseekerEducationCollection().remove(jobseekerEducationCollectionNewJobseekerEducation);
                        oldJobseekerOfJobseekerEducationCollectionNewJobseekerEducation = em.merge(oldJobseekerOfJobseekerEducationCollectionNewJobseekerEducation);
                    }
                }
            }
            for (JobseekerAl jobseekerAlCollectionNewJobseekerAl : jobseekerAlCollectionNew) {
                if (!jobseekerAlCollectionOld.contains(jobseekerAlCollectionNewJobseekerAl)) {
                    Jobseeker oldJobseekerEducationOfJobseekerAlCollectionNewJobseekerAl = jobseekerAlCollectionNewJobseekerAl.getJobseekerEducation();
                    jobseekerAlCollectionNewJobseekerAl.setJobseekerEducation(jobseeker);
                    jobseekerAlCollectionNewJobseekerAl = em.merge(jobseekerAlCollectionNewJobseekerAl);
                    if (oldJobseekerEducationOfJobseekerAlCollectionNewJobseekerAl != null && !oldJobseekerEducationOfJobseekerAlCollectionNewJobseekerAl.equals(jobseeker)) {
                        oldJobseekerEducationOfJobseekerAlCollectionNewJobseekerAl.getJobseekerAlCollection().remove(jobseekerAlCollectionNewJobseekerAl);
                        oldJobseekerEducationOfJobseekerAlCollectionNewJobseekerAl = em.merge(oldJobseekerEducationOfJobseekerAlCollectionNewJobseekerAl);
                    }
                }
            }
            for (JobseekerWorkedcompany jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany : jobseekerWorkedcompanyCollectionNew) {
                if (!jobseekerWorkedcompanyCollectionOld.contains(jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany)) {
                    Jobseeker oldJobseekerOfJobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany = jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany.getJobseeker();
                    jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany.setJobseeker(jobseeker);
                    jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany = em.merge(jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany);
                    if (oldJobseekerOfJobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany != null && !oldJobseekerOfJobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany.equals(jobseeker)) {
                        oldJobseekerOfJobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany.getJobseekerWorkedcompanyCollection().remove(jobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany);
                        oldJobseekerOfJobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany = em.merge(oldJobseekerOfJobseekerWorkedcompanyCollectionNewJobseekerWorkedcompany);
                    }
                }
            }
            for (JobseekerUniversity jobseekerUniversityCollectionNewJobseekerUniversity : jobseekerUniversityCollectionNew) {
                if (!jobseekerUniversityCollectionOld.contains(jobseekerUniversityCollectionNewJobseekerUniversity)) {
                    Jobseeker oldJobseekerEducationOfJobseekerUniversityCollectionNewJobseekerUniversity = jobseekerUniversityCollectionNewJobseekerUniversity.getJobseekerEducation();
                    jobseekerUniversityCollectionNewJobseekerUniversity.setJobseekerEducation(jobseeker);
                    jobseekerUniversityCollectionNewJobseekerUniversity = em.merge(jobseekerUniversityCollectionNewJobseekerUniversity);
                    if (oldJobseekerEducationOfJobseekerUniversityCollectionNewJobseekerUniversity != null && !oldJobseekerEducationOfJobseekerUniversityCollectionNewJobseekerUniversity.equals(jobseeker)) {
                        oldJobseekerEducationOfJobseekerUniversityCollectionNewJobseekerUniversity.getJobseekerUniversityCollection().remove(jobseekerUniversityCollectionNewJobseekerUniversity);
                        oldJobseekerEducationOfJobseekerUniversityCollectionNewJobseekerUniversity = em.merge(oldJobseekerEducationOfJobseekerUniversityCollectionNewJobseekerUniversity);
                    }
                }
            }
            for (JobseekerOther jobseekerOtherCollectionNewJobseekerOther : jobseekerOtherCollectionNew) {
                if (!jobseekerOtherCollectionOld.contains(jobseekerOtherCollectionNewJobseekerOther)) {
                    Jobseeker oldJobseekerOfJobseekerOtherCollectionNewJobseekerOther = jobseekerOtherCollectionNewJobseekerOther.getJobseeker();
                    jobseekerOtherCollectionNewJobseekerOther.setJobseeker(jobseeker);
                    jobseekerOtherCollectionNewJobseekerOther = em.merge(jobseekerOtherCollectionNewJobseekerOther);
                    if (oldJobseekerOfJobseekerOtherCollectionNewJobseekerOther != null && !oldJobseekerOfJobseekerOtherCollectionNewJobseekerOther.equals(jobseeker)) {
                        oldJobseekerOfJobseekerOtherCollectionNewJobseekerOther.getJobseekerOtherCollection().remove(jobseekerOtherCollectionNewJobseekerOther);
                        oldJobseekerOfJobseekerOtherCollectionNewJobseekerOther = em.merge(oldJobseekerOfJobseekerOtherCollectionNewJobseekerOther);
                    }
                }
            }
            for (JobseekerExperience jobseekerExperienceCollectionNewJobseekerExperience : jobseekerExperienceCollectionNew) {
                if (!jobseekerExperienceCollectionOld.contains(jobseekerExperienceCollectionNewJobseekerExperience)) {
                    Jobseeker oldJobseekerOfJobseekerExperienceCollectionNewJobseekerExperience = jobseekerExperienceCollectionNewJobseekerExperience.getJobseeker();
                    jobseekerExperienceCollectionNewJobseekerExperience.setJobseeker(jobseeker);
                    jobseekerExperienceCollectionNewJobseekerExperience = em.merge(jobseekerExperienceCollectionNewJobseekerExperience);
                    if (oldJobseekerOfJobseekerExperienceCollectionNewJobseekerExperience != null && !oldJobseekerOfJobseekerExperienceCollectionNewJobseekerExperience.equals(jobseeker)) {
                        oldJobseekerOfJobseekerExperienceCollectionNewJobseekerExperience.getJobseekerExperienceCollection().remove(jobseekerExperienceCollectionNewJobseekerExperience);
                        oldJobseekerOfJobseekerExperienceCollectionNewJobseekerExperience = em.merge(oldJobseekerOfJobseekerExperienceCollectionNewJobseekerExperience);
                    }
                }
            }
            for (VacancyApply vacancyApplyCollectionNewVacancyApply : vacancyApplyCollectionNew) {
                if (!vacancyApplyCollectionOld.contains(vacancyApplyCollectionNewVacancyApply)) {
                    Jobseeker oldJobseekerIdOfVacancyApplyCollectionNewVacancyApply = vacancyApplyCollectionNewVacancyApply.getJobseekerId();
                    vacancyApplyCollectionNewVacancyApply.setJobseekerId(jobseeker);
                    vacancyApplyCollectionNewVacancyApply = em.merge(vacancyApplyCollectionNewVacancyApply);
                    if (oldJobseekerIdOfVacancyApplyCollectionNewVacancyApply != null && !oldJobseekerIdOfVacancyApplyCollectionNewVacancyApply.equals(jobseeker)) {
                        oldJobseekerIdOfVacancyApplyCollectionNewVacancyApply.getVacancyApplyCollection().remove(vacancyApplyCollectionNewVacancyApply);
                        oldJobseekerIdOfVacancyApplyCollectionNewVacancyApply = em.merge(oldJobseekerIdOfVacancyApplyCollectionNewVacancyApply);
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
                Integer id = jobseeker.getId();
                if (findJobseeker(id) == null) {
                    throw new NonexistentEntityException("The jobseeker with id " + id + " no longer exists.");
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
            Jobseeker jobseeker;
            try {
                jobseeker = em.getReference(Jobseeker.class, id);
                jobseeker.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The jobseeker with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<JobseekerOl> jobseekerOlCollectionOrphanCheck = jobseeker.getJobseekerOlCollection();
            for (JobseekerOl jobseekerOlCollectionOrphanCheckJobseekerOl : jobseekerOlCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jobseeker (" + jobseeker + ") cannot be destroyed since the JobseekerOl " + jobseekerOlCollectionOrphanCheckJobseekerOl + " in its jobseekerOlCollection field has a non-nullable jobseekerEducation field.");
            }
            Collection<JobseekerEducation> jobseekerEducationCollectionOrphanCheck = jobseeker.getJobseekerEducationCollection();
            for (JobseekerEducation jobseekerEducationCollectionOrphanCheckJobseekerEducation : jobseekerEducationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jobseeker (" + jobseeker + ") cannot be destroyed since the JobseekerEducation " + jobseekerEducationCollectionOrphanCheckJobseekerEducation + " in its jobseekerEducationCollection field has a non-nullable jobseeker field.");
            }
            Collection<JobseekerAl> jobseekerAlCollectionOrphanCheck = jobseeker.getJobseekerAlCollection();
            for (JobseekerAl jobseekerAlCollectionOrphanCheckJobseekerAl : jobseekerAlCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jobseeker (" + jobseeker + ") cannot be destroyed since the JobseekerAl " + jobseekerAlCollectionOrphanCheckJobseekerAl + " in its jobseekerAlCollection field has a non-nullable jobseekerEducation field.");
            }
            Collection<JobseekerWorkedcompany> jobseekerWorkedcompanyCollectionOrphanCheck = jobseeker.getJobseekerWorkedcompanyCollection();
            for (JobseekerWorkedcompany jobseekerWorkedcompanyCollectionOrphanCheckJobseekerWorkedcompany : jobseekerWorkedcompanyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jobseeker (" + jobseeker + ") cannot be destroyed since the JobseekerWorkedcompany " + jobseekerWorkedcompanyCollectionOrphanCheckJobseekerWorkedcompany + " in its jobseekerWorkedcompanyCollection field has a non-nullable jobseeker field.");
            }
            Collection<JobseekerUniversity> jobseekerUniversityCollectionOrphanCheck = jobseeker.getJobseekerUniversityCollection();
            for (JobseekerUniversity jobseekerUniversityCollectionOrphanCheckJobseekerUniversity : jobseekerUniversityCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jobseeker (" + jobseeker + ") cannot be destroyed since the JobseekerUniversity " + jobseekerUniversityCollectionOrphanCheckJobseekerUniversity + " in its jobseekerUniversityCollection field has a non-nullable jobseekerEducation field.");
            }
            Collection<JobseekerOther> jobseekerOtherCollectionOrphanCheck = jobseeker.getJobseekerOtherCollection();
            for (JobseekerOther jobseekerOtherCollectionOrphanCheckJobseekerOther : jobseekerOtherCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jobseeker (" + jobseeker + ") cannot be destroyed since the JobseekerOther " + jobseekerOtherCollectionOrphanCheckJobseekerOther + " in its jobseekerOtherCollection field has a non-nullable jobseeker field.");
            }
            Collection<JobseekerExperience> jobseekerExperienceCollectionOrphanCheck = jobseeker.getJobseekerExperienceCollection();
            for (JobseekerExperience jobseekerExperienceCollectionOrphanCheckJobseekerExperience : jobseekerExperienceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jobseeker (" + jobseeker + ") cannot be destroyed since the JobseekerExperience " + jobseekerExperienceCollectionOrphanCheckJobseekerExperience + " in its jobseekerExperienceCollection field has a non-nullable jobseeker field.");
            }
            Collection<VacancyApply> vacancyApplyCollectionOrphanCheck = jobseeker.getVacancyApplyCollection();
            for (VacancyApply vacancyApplyCollectionOrphanCheckVacancyApply : vacancyApplyCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Jobseeker (" + jobseeker + ") cannot be destroyed since the VacancyApply " + vacancyApplyCollectionOrphanCheckVacancyApply + " in its vacancyApplyCollection field has a non-nullable jobseekerId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(jobseeker);
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

    public List<Jobseeker> findJobseekerEntities() {
        return findJobseekerEntities(true, -1, -1);
    }

    public List<Jobseeker> findJobseekerEntities(int maxResults, int firstResult) {
        return findJobseekerEntities(false, maxResults, firstResult);
    }

    private List<Jobseeker> findJobseekerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Jobseeker.class));
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

    public Jobseeker findJobseeker(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Jobseeker.class, id);
        } finally {
            em.close();
        }
    }

    public int getJobseekerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Jobseeker> rt = cq.from(Jobseeker.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
