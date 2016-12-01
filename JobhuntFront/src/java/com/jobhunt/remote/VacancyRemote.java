/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.remote;

import com.jobhunt.entity.UserCache;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.entity.VacancyApply;
import com.jobhunt.entity.VacancyCache;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author Lakshitha
 */
@Remote
public interface VacancyRemote {
    public Integer addVacany(Map<String,String> vacancy, Integer id, Date closingDate);
    
    public List<Vacancy> findAllVacancies();
    
    public List<Vacancy> findRecommendedVacancies(String job, String area, String salary);
    
    public Boolean deleteVacancy(Integer id);
    
    public Vacancy findVacany(Integer id);
    
    public List<VacancyApply> findVacancyByDateAndID(Date today);
    
    public Boolean applyVacancy(Integer seekerId, Integer vacancyId, Integer status);
    
    public Boolean changeApplyVacancyStatus(Integer applicantId, Integer vacancyId, Integer status, Date interviewDate);
    
    public UserCache findUserCache(Integer id);
    
    public void createVacancyCache(Integer id, Date postedDate);
    
    public List<VacancyCache> findAllVacancyCache();
    
    public List<VacancyApply> findAllVacancyApply();
    
    public VacancyApply findVacancyApplyById(Integer id);
    
    public VacancyApply findVacancyApplyByJobseekerAndVacancy(Integer vacancyId, Integer seekerId);
    
    public List<VacancyApply> findVacancyApplyByJobseeker(Integer id);
    
    public List<Vacancy> findVacancyByCompanyId(Integer id);

    public Boolean UpdateVacancyClosingDate(Integer id, Date closingDate);
}
