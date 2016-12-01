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
import java.io.File;
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
public class ManageOption extends ActionSupport {
    private final ApplicationUserRemote applicationUserRemote = lookupApplicationUserManagementRemote();
    private File profilePicture;
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
    private Boolean isPasswordChangeError;

    public Boolean getIsPasswordChangeError() {
        return isPasswordChangeError;
    }

    public void setIsPasswordChangeError(Boolean isPasswordChangeError) {
        this.isPasswordChangeError = isPasswordChangeError;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public File getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(File profilePicture) {
        this.profilePicture = profilePicture;
    }
    
    public ManageOption() {
    }
    
    public String manageSeekerOption() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser)session.get("user");
        Integer userId = user.getId();
        applicationUserRemote.uploadProfilePicture(profilePicture, userId);
        
        return SUCCESS;
    }
    
    public String changeUserPassword() throws Exception{
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser)session.get("user");
        Integer userId = user.getId();
        
        if(!(user.getPassword().equals(currentPassword))){
            isPasswordChangeError = true;
        }
        else{
            applicationUserRemote.changePassword(userId, newPassword);
            isPasswordChangeError = false;
        }
        
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
