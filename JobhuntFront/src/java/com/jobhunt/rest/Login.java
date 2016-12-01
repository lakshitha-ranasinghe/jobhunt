/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.rest;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.remote.ApplicationUserRemote;
import com.jobhunt.remote.UserSessionRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Githanjana Ishara
 */
public class Login extends ActionSupport implements ServletRequestAware
{
    
    private HttpServletRequest request;
    
    Map<String,String> result;

    public Map<String, String> getResult() {
        return result;
    }
       

    public String execute() throws Exception 
    {
        result = new HashMap<>();
        if((request.getParameter("username") != null) && (request.getParameter("password") != null))
        {
            ApplicationUser user = lookupApplicationUserManagementRemote().findApplicationUser(request.getParameter("username"), request.getParameter("password"));
            if(user != null)
            {
                String accessToken = DigestUtils.md5Hex(user.getId() + user.getUsername() + new Date());
                result.put("status", "ok");
                result.put("message", "Success");
                result.put("id", user.getId().toString());
                result.put("name", user.getName());
                result.put("type", user.getType());
                result.put("username", user.getUsername());
                result.put("token", accessToken);
                
                lookupUserSessionRemote().createSession(user.getId(), accessToken);

                return ActionSupport.SUCCESS;
            }
            else
            {
                result.put("status", "error");
                result.put("message", "Invalid Credentials");
                return ActionSupport.SUCCESS;
            }
        }
        else if(request.getParameter("token") != null)
        {
            //authenticate using the token and return results
            return ActionSupport.SUCCESS;
        }
        else
        {
            result.put("status", "error");
            result.put("message", "Missing required parameters");
            result.put("method", request.getMethod());
            return ActionSupport.SUCCESS;

        }
    }
    
    
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) 
    {
        this.request = hsr;
    }
    
    private ApplicationUserRemote lookupApplicationUserManagementRemote() 
    {
        try {
            Context c = new InitialContext();
            return (ApplicationUserRemote) c.lookup("java:global/JobhuntEJB/ApplicationUserBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private UserSessionRemote lookupUserSessionRemote() 
    {
        try {
            Context c = new InitialContext();
            return (UserSessionRemote) c.lookup("java:global/JobhuntEJB/UserSessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
