/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.ajaxaction;

import com.jobhunt.remote.ApplicationUserRemote;
import com.opensymphony.xwork2.ActionSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Lakshitha
 */
public class RegistrationValidation extends ActionSupport {
    private final ApplicationUserRemote applicationUserRemote = lookupApplicationUserManagementRemote();
    private String username;
    private Boolean isUserExist;
    private Boolean isPasswordCorrect;
    private String password;
    private String confirmPassword;

    public Boolean getIsPasswordCorrect() {
        return isPasswordCorrect;
    }

    public void setIsPasswordCorrect(Boolean isPasswordCorrect) {
        this.isPasswordCorrect = isPasswordCorrect;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public Boolean getIsUserExist() {
        return isUserExist;
    }

    public void setIsUserExist(Boolean isUserExist) {
        this.isUserExist = isUserExist;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public RegistrationValidation() {
    }
    
    public String checkUserExistance() throws Exception {
        isUserExist = applicationUserRemote.isUsernameTaken(username);
        return SUCCESS;
    }
    
    public String checkPasswordMatch() throws Exception {
        isPasswordCorrect = password.equals(confirmPassword);
        return SUCCESS;
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
