/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.interceptor;

import com.jobhunt.entity.ApplicationUser;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

/**
 *
 * @author Lakshitha
 */
public class LoginCheck extends AbstractInterceptor  implements Interceptor {
    
    public LoginCheck() {
    }
    
    public void destroy() {
        
    }
    
    public void init() {
        
    }
    
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser)session.get("user");
        if(user == null){
            return Action.LOGIN;
        }
        else{
            return actionInvocation.invoke();
        }
    }
    
}
