/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.rest;

import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.JobseekerAl;
import com.jobhunt.entity.JobseekerExperience;
import com.jobhunt.entity.JobseekerOl;
import com.jobhunt.entity.JobseekerUniversity;
import com.jobhunt.entity.UserSession;
import com.jobhunt.remote.JobseekerRemote;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Githanjana Ishara
 */
public class JobseekerProfile extends ActionSupport implements ServletRequestAware
{
    
    HttpServletRequest request;
    String token;
    UserSession session;
    
    Jobseeker profile;
   

    public Jobseeker getProfile() {
        return profile;
    }

    public void setProfile(Jobseeker profile) {
        this.profile = profile;
    }
    
    @Override
    public String execute() throws Exception
    {
        session = (UserSession)request.getAttribute("session");
        
        if(session != null)
        {
            switch(request.getMethod())
            {
                case "GET" :
                    doGet();
                    break;
                case "POST":
                    doPost();
                    break;
                case "PUT" :
                    doPut();
                    break;
            }
        }
        
        return ActionSupport.SUCCESS;
    }
    
    private void doGet()
    {
        profile = lookupJobseekerManagementRemote().findPersonalProfile(session.getUserid());
    }
    
    private void doPost()
    {
        Map<String,String> jobseekerPersonalData = new HashMap<>();
        jobseekerPersonalData.put("id", String.valueOf(session.getUserid()));
        jobseekerPersonalData.put("firstName", profile.getFirstName());
        jobseekerPersonalData.put("lastName", profile.getLastName());
        jobseekerPersonalData.put("title", profile.getTitle());
        jobseekerPersonalData.put("address1", profile.getAddress1());
        jobseekerPersonalData.put("address2", profile.getAddress2());
        jobseekerPersonalData.put("address3", profile.getAddress3());
        jobseekerPersonalData.put("email", profile.getEmail());
        jobseekerPersonalData.put("telephone", String.valueOf(profile.getTelephone()));
        jobseekerPersonalData.put("mobile", String.valueOf(profile.getMobile()));
        jobseekerPersonalData.put("qualifiedField", profile.getQualifiedField());
        jobseekerPersonalData.put("lastJob", profile.getLastJob());
        jobseekerPersonalData.put("expectedJob", profile.getExpectedJob());
        
        lookupJobseekerManagementRemote().createPersonalProfile(jobseekerPersonalData, profile.getBirthday());
    }
    
    private void doPut()
    {
        //TODO profile creation     
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) 
    {
        this.request = hsr;
    }
    
    private JobseekerRemote lookupJobseekerManagementRemote() 
    {
        try 
        {
            Context c = new InitialContext();
            return (JobseekerRemote) c.lookup("java:global/JobhuntEJB/JobseekerBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
