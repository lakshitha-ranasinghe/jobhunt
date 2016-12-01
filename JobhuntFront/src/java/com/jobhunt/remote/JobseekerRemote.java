/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.remote;

import com.jobhunt.entity.Jobseeker;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author Lakshitha
 */
@Remote
public interface JobseekerRemote {
    public void createPersonalProfile(Map<String,String> personal, Date birthday);
    
    public void createEducationalProfile(List<Map<String,String>> alResult, List<Map<String,String>> olResult, List<Map<String,String>> uniResult, Map<String,String> educationProfile, Integer id);
    
    public Jobseeker findPersonalProfile(Integer id);
    
    public void createProfessionalProfile(List<Map<String,String>> companiesWorked, List<Map<String,String>> others, List<Map<String,Date>> durations, Integer seekerId, Integer experienceYears); 
    
    public void updatePersonalProfle(Map<String,String> personal, Integer id, Date birthday);
    
    public void updateEducationalProfile(List<Map<String,String>> alResult, List<Map<String,String>> olResult, List<Map<String,String>> uniResult, Map<String,String> educationProfile, Integer id);
    
    public void updateProfessionalProfile(List<Map<String,String>> companiesWorked, List<Map<String,String>> others, List<Map<String,Date>> durations, Integer seekerId, Integer experienceYears, Integer experieceId);
}
