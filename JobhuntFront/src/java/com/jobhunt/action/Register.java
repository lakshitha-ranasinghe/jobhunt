/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.remote.ApplicationUserRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lakshitha
 */
public class Register extends ActionSupport {
    
    private final ApplicationUserRemote applicationUserRemote = lookupApplicationUserManagementRemote();
    private final static String SEEKER = "seeker";
    private final static String COMPANY = "company";
    private ApplicationUser applicationUser;
    
    public Register() {
    }
    
    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }
    
    public String execute() throws Exception {
        
        Boolean isUserCreated = applicationUserRemote.createUser(applicationUser);
        if(isUserCreated){
            ApplicationUser newApplicationUser = applicationUserRemote.findApplicationUser(applicationUser.getUsername(), applicationUser.getPassword());
            Map session = ActionContext.getContext().getSession();
            session.put("user", applicationUser);
            if(applicationUser.getType().equals("seeker")){
                return SEEKER;
            }
            else{
                return COMPANY;
            }
        }
        else{
            return ERROR;
        }
    }
    
    private ApplicationUserRemote lookupApplicationUserManagementRemote() {
        try {
            Context c = new InitialContext();
            return (ApplicationUserRemote) c.lookup("java:global/JobhuntEJB/ApplicationUserBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
