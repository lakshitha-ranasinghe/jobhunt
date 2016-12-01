/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.interceptor;

import com.jobhunt.entity.UserSession;
import com.jobhunt.remote.UserSessionRemote;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Githanjana Ishara
 */
public class RestAuth extends AbstractInterceptor
{
    
    @Override
    public String intercept(ActionInvocation ai) throws Exception 
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        
        String token = request.getHeader("API-KEY");
        
        if(token != null)
        {
            UserSession session = lookupUserSessionRemote().validateSession(token);
            if(!session.getToken().equals("invalid"))
            {
                request.setAttribute("session", session);
                return ai.invoke();   
            }
            else
                return "error";
            
        }
        else   
            return "error";
        
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
