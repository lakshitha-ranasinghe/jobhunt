/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.bean;

import com.jobhunt.controller.JobseekerAlJpaController;
import com.jobhunt.controller.JobseekerEducationJpaController;
import com.jobhunt.controller.JobseekerExperienceJpaController;
import com.jobhunt.controller.JobseekerJpaController;
import com.jobhunt.controller.JobseekerOlJpaController;
import com.jobhunt.controller.JobseekerOtherJpaController;
import com.jobhunt.controller.JobseekerUniversityJpaController;
import com.jobhunt.controller.JobseekerWorkedcompanyJpaController;
import com.jobhunt.controller.exceptions.NonexistentEntityException;
import com.jobhunt.controller.exceptions.RollbackFailureException;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.JobseekerAl;
import com.jobhunt.entity.JobseekerEducation;
import com.jobhunt.entity.JobseekerExperience;
import com.jobhunt.entity.JobseekerOl;
import com.jobhunt.entity.JobseekerOther;
import com.jobhunt.entity.JobseekerUniversity;
import com.jobhunt.entity.JobseekerWorkedcompany;
import com.jobhunt.entity.VacancyApply;
import com.jobhunt.remote.JobseekerRemote;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class JobseekerBean implements JobseekerRemote {

    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;

    @Override
    public void createPersonalProfile(Map<String, String> jobseekerPersonalData, Date birthday) {
        try {
            Jobseeker jobseeker = new Jobseeker();
            jobseeker.setId(Integer.parseInt(jobseekerPersonalData.get("id")));
            jobseeker.setFirstName(jobseekerPersonalData.get("firstName"));
            jobseeker.setLastName(jobseekerPersonalData.get("lastName"));
            jobseeker.setBirthday(birthday);
            jobseeker.setTitle(jobseekerPersonalData.get("title"));
            jobseeker.setAddress1(jobseekerPersonalData.get("address1"));
            jobseeker.setAddress2(jobseekerPersonalData.get("address2"));
            jobseeker.setAddress3(jobseekerPersonalData.get("address3"));
            jobseeker.setEmail(jobseekerPersonalData.get("email"));
            jobseeker.setTelephone(Integer.parseInt(jobseekerPersonalData.get("telephone")));
            jobseeker.setMobile(Integer.parseInt(jobseekerPersonalData.get("mobile")));
            jobseeker.setLastJob(jobseekerPersonalData.get("lastJob"));
            jobseeker.setExpectedJob(jobseekerPersonalData.get("expectedJob"));
            jobseeker.setQualifiedField(jobseekerPersonalData.get("qualifiedField"));
            
            System.out.println(jobseeker);

            System.out.println("Creating Job Seeker Profile");
            new JobseekerJpaController(utx, emf).create(jobseeker);
            System.out.println("Job Seeker Profile Created");
        } catch (RollbackFailureException ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createEducationalProfile(List<Map<String, String>> alResult, List<Map<String, String>> olResult, List<Map<String, String>> uniResult, Map<String, String> educationProfile, Integer id) {
        Jobseeker jobseeker = new JobseekerJpaController(utx, emf).findJobseeker(id);

        JobseekerEducation education = new JobseekerEducation();
        education.setAlSchool(educationProfile.get("alSchool"));
        education.setAlYear(Integer.parseInt(educationProfile.get("alYear")));
        education.setOlSchool(educationProfile.get("olSchool"));
        education.setOlYear(Integer.parseInt(educationProfile.get("olYear")));
        education.setJobseeker(jobseeker);

        try {
            System.out.println("Creating Job Seeker Education Profile");
            new JobseekerEducationJpaController(utx, emf).create(education);
            System.out.println("Job Seeker Education Profile Created");
        } catch (RollbackFailureException ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Creating Job Seeker AL Profile");
        for (Map<String, String> als : alResult) {
            JobseekerAl jobseekerAl = new JobseekerAl();
            jobseekerAl.setMark(als.get("mark"));
            jobseekerAl.setSubject(als.get("subject"));
            jobseekerAl.setJobseekerEducation(jobseeker);

            try {
                new JobseekerAlJpaController(utx, emf).create(jobseekerAl);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Job Seeker AL Profile Created");

        System.out.println("Creating Job Seeker OL Profile");
        for (Map<String, String> ols : olResult) {
            JobseekerOl jobseekerOl = new JobseekerOl();
            jobseekerOl.setMark(ols.get("mark"));
            jobseekerOl.setSubject(ols.get("subject"));
            jobseekerOl.setJobseekerEducation(jobseeker);

            try {
                new JobseekerOlJpaController(utx, emf).create(jobseekerOl);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Job Seeker OL Profile Created");

        System.out.println("Creating Job Seeker University Profile");
        for (Map<String, String> unis : uniResult) {
            JobseekerUniversity jobseekerUniversity = new JobseekerUniversity();
            jobseekerUniversity.setCompletedYear(Integer.parseInt(unis.get("year")));
            jobseekerUniversity.setCourse(unis.get("course"));
            jobseekerUniversity.setGpa(Double.parseDouble(unis.get("gpa")));
            jobseekerUniversity.setName(unis.get("name"));
            jobseekerUniversity.setJobseekerEducation(jobseeker);
            try {
                new JobseekerUniversityJpaController(utx, emf).create(jobseekerUniversity);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Job Seeker University Profile Created");
    }

    @Override
    public Jobseeker findPersonalProfile(Integer id) {
        try {
            Jobseeker jobseeker = new JobseekerJpaController(utx, emf).findJobseeker(id);
            return jobseeker;
        } catch (Exception ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void createProfessionalProfile(List<Map<String, String>> companiesWorked, List<Map<String, String>> others, List<Map<String, Date>> durations, Integer seekerId, Integer experienceYears) {
        Jobseeker jobseeker = new JobseekerJpaController(utx, emf).findJobseeker(seekerId);

        JobseekerExperience experience = new JobseekerExperience();
        experience.setJobseeker(jobseeker);
        experience.setTotalYears(experienceYears);
        try {
            System.out.println("Creating Job Seeker Experience Profile");
            new JobseekerExperienceJpaController(utx, emf).create(experience);
            System.out.println("Job Seeker Experience Profile Created");
        } catch (RollbackFailureException ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<JobseekerWorkedcompany> workedCompanies = new ArrayList<JobseekerWorkedcompany>();

        for (int i = 0; i < companiesWorked.size(); i++) {
            Map<String, String> companyData = companiesWorked.get(i);
            Map<String, Date> durationData = durations.get(i);

            JobseekerWorkedcompany jobseekerWorkedcompany = new JobseekerWorkedcompany();
            jobseekerWorkedcompany.setDesignation(companyData.get("designation"));
            jobseekerWorkedcompany.setName(companyData.get("name"));
            jobseekerWorkedcompany.setStartDate(durationData.get("start"));
            jobseekerWorkedcompany.setEndDate(durationData.get("end"));
            workedCompanies.add(jobseekerWorkedcompany);
        }

        System.out.println("Creating Job Seeker Worked Company Profile");
        for (JobseekerWorkedcompany workedCompany : workedCompanies) {
            workedCompany.setJobseeker(jobseeker);
            try {
                new JobseekerWorkedcompanyJpaController(utx, emf).create(workedCompany);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Job Seeker Worked Company Profile Created");

        System.out.println("Creating Job Seeker Other Profile");
        for (Map<String, String> otherQualification : others) {
            JobseekerOther other = new JobseekerOther();
            other.setDetails(otherQualification.get("details"));
            other.setPublishedYear(Integer.parseInt(otherQualification.get("publishedYear")));
            other.setTitle(otherQualification.get("title"));
            other.setJobseeker(jobseeker);
            try {
                new JobseekerOtherJpaController(utx, emf).create(other);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Job Seeker Other Profile Created");
    }

    @Override
    public void updatePersonalProfle(Map<String, String> jobseekerPersonalData, Integer id, Date birthday) {
        try {
            Jobseeker jobseeker = new Jobseeker();
            jobseeker.setId(id);
            jobseeker.setFirstName(jobseekerPersonalData.get("firstName"));
            jobseeker.setLastName(jobseekerPersonalData.get("lastName"));
            jobseeker.setTitle(jobseekerPersonalData.get("title"));
            jobseeker.setAddress1(jobseekerPersonalData.get("address1"));
            jobseeker.setAddress2(jobseekerPersonalData.get("address2"));
            jobseeker.setAddress3(jobseekerPersonalData.get("address3"));
            jobseeker.setEmail(jobseekerPersonalData.get("email"));
            jobseeker.setTelephone(Integer.parseInt(jobseekerPersonalData.get("telephone")));
            jobseeker.setMobile(Integer.parseInt(jobseekerPersonalData.get("mobile")));
            jobseeker.setLastJob(jobseekerPersonalData.get("lastJob"));
            jobseeker.setExpectedJob(jobseekerPersonalData.get("expectedJob"));
            jobseeker.setQualifiedField(jobseekerPersonalData.get("qualifiedField"));
            jobseeker.setBirthday(birthday);

            Jobseeker oldJobseeker = new JobseekerJpaController(utx, emf).findJobseeker(id);

            jobseeker.setJobseekerAlCollection(oldJobseeker.getJobseekerAlCollection());
            jobseeker.setJobseekerEducationCollection(oldJobseeker.getJobseekerEducationCollection());
            jobseeker.setJobseekerExperienceCollection(oldJobseeker.getJobseekerExperienceCollection());
            jobseeker.setJobseekerOlCollection(oldJobseeker.getJobseekerOlCollection());
            jobseeker.setJobseekerOtherCollection(oldJobseeker.getJobseekerOtherCollection());
            jobseeker.setJobseekerUniversityCollection(oldJobseeker.getJobseekerUniversityCollection());
            jobseeker.setJobseekerWorkedcompanyCollection(oldJobseeker.getJobseekerWorkedcompanyCollection());
            
            if(oldJobseeker.getVacancyApplyCollection() == null){
                jobseeker.setVacancyApplyCollection(new ArrayList<VacancyApply>());
            }
            else{
                jobseeker.setVacancyApplyCollection(oldJobseeker.getVacancyApplyCollection());
            }

            System.out.println("Updating Job Seeker Profile");
            new JobseekerJpaController(utx, emf).edit(jobseeker);
            System.out.println("Job Seeker Profile Updated");
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateEducationalProfile(List<Map<String, String>> alResult, List<Map<String, String>> olResult, List<Map<String, String>> uniResult, Map<String, String> educationProfile, Integer id) {
        Jobseeker jobseeker = new JobseekerJpaController(utx, emf).findJobseeker(id);

        JobseekerEducation education = new JobseekerEducation();
        education.setId(Integer.parseInt(educationProfile.get("id")));
        education.setAlSchool(educationProfile.get("alSchool"));
        education.setAlYear(Integer.parseInt(educationProfile.get("alYear")));
        education.setOlSchool(educationProfile.get("olSchool"));
        education.setOlYear(Integer.parseInt(educationProfile.get("olYear")));
        education.setJobseeker(jobseeker);

        try {
            System.out.println("Updating Job Seeker Education Profile");
            new JobseekerEducationJpaController(utx, emf).edit(education);
            System.out.println("Job Seeker Education Profile Updated");
        } catch (RollbackFailureException ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Updating Job Seeker AL Profile");
        for (Map<String, String> als : alResult) {
            JobseekerAl jobseekerAl = new JobseekerAl();
            jobseekerAl.setAlId(Integer.parseInt(als.get("id")));
            jobseekerAl.setMark(als.get("mark"));
            jobseekerAl.setSubject(als.get("subject"));
            jobseekerAl.setJobseekerEducation(jobseeker);

            try {
                new JobseekerAlJpaController(utx, emf).edit(jobseekerAl);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Job Seeker AL Profile Updated");

        System.out.println("Updating Job Seeker OL Profile");
        for (Map<String, String> ols : olResult) {
            JobseekerOl jobseekerOl = new JobseekerOl();
            jobseekerOl.setOlId(Integer.parseInt(ols.get("id")));
            jobseekerOl.setMark(ols.get("mark"));
            jobseekerOl.setSubject(ols.get("subject"));
            jobseekerOl.setJobseekerEducation(jobseeker);

            try {
                new JobseekerOlJpaController(utx, emf).edit(jobseekerOl);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Job Seeker OL Profile Updated");

        System.out.println("Updating Job Seeker University Profile");
        for (Map<String, String> unis : uniResult) {
            JobseekerUniversity jobseekerUniversity = new JobseekerUniversity();
            jobseekerUniversity.setUniversityId(Integer.parseInt(unis.get("id")));
            jobseekerUniversity.setCompletedYear(Integer.parseInt(unis.get("year")));
            jobseekerUniversity.setCourse(unis.get("course"));
            jobseekerUniversity.setGpa(Double.parseDouble(unis.get("gpa")));
            jobseekerUniversity.setName(unis.get("name"));
            jobseekerUniversity.setJobseekerEducation(jobseeker);
            try {
                new JobseekerUniversityJpaController(utx, emf).edit(jobseekerUniversity);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Job Seeker University Profile Updated");
    }

    @Override
    public void updateProfessionalProfile(List<Map<String, String>> companiesWorked, List<Map<String, String>> others, List<Map<String, Date>> durations, Integer seekerId, Integer experienceYears, Integer experieceId) {
        Jobseeker jobseeker = new JobseekerJpaController(utx, emf).findJobseeker(seekerId);

        JobseekerExperience experience = new JobseekerExperience();
        experience.setId(experieceId);
        experience.setJobseeker(jobseeker);
        experience.setTotalYears(experienceYears);
        try {
            System.out.println("Updating Job Seeker Experience Profile");
            new JobseekerExperienceJpaController(utx, emf).edit(experience);
            System.out.println("Job Seeker Experience Profile Updated");
        } catch (RollbackFailureException ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<JobseekerWorkedcompany> workedCompanies = new ArrayList<JobseekerWorkedcompany>();

        for (int i = 0; i < companiesWorked.size(); i++) {
            Map<String, String> companyData = companiesWorked.get(i);
            Map<String, Date> durationData = durations.get(i);

            JobseekerWorkedcompany jobseekerWorkedcompany = new JobseekerWorkedcompany();
            jobseekerWorkedcompany.setId(Integer.parseInt(companyData.get("id")));
            jobseekerWorkedcompany.setDesignation(companyData.get("designation"));
            jobseekerWorkedcompany.setName(companyData.get("name"));
            jobseekerWorkedcompany.setStartDate(durationData.get("start"));
            jobseekerWorkedcompany.setEndDate(durationData.get("end"));
            workedCompanies.add(jobseekerWorkedcompany);
        }

        System.out.println("Updating Job Seeker Worked Company Profile");
        for (JobseekerWorkedcompany workedCompany : workedCompanies) {
            workedCompany.setJobseeker(jobseeker);
            try {
                new JobseekerWorkedcompanyJpaController(utx, emf).edit(workedCompany);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Job Seeker Worked Company Profile Updated");

        System.out.println("Updating Job Seeker Other Profile");
        for (Map<String, String> otherQualification : others) {
            JobseekerOther other = new JobseekerOther();
            other.setId(Integer.parseInt(otherQualification.get("id")));
            other.setDetails(otherQualification.get("details"));
            other.setPublishedYear(Integer.parseInt(otherQualification.get("publishedYear")));
            other.setTitle(otherQualification.get("title"));
            other.setJobseeker(jobseeker);
            try {
                new JobseekerOtherJpaController(utx, emf).edit(other);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(JobseekerBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Job Seeker Other Profile Updated");
    }
}
