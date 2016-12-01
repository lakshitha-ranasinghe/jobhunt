/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.UserCache;
import com.jobhunt.remote.ApplicationUserRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Lakshitha
 */
public class OpenOptions extends ActionSupport implements ServletRequestAware {

    private final ApplicationUserRemote applicationUserRemote = lookupApplicationUserManagementRemote();
    private BufferedImage profilePicture;
    private Integer userId;
    private String userName;
    private HttpServletRequest servletRequest;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BufferedImage getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(BufferedImage profilePicture) {
        this.profilePicture = profilePicture;
    }

    public OpenOptions() {
    }

    public String seekerOptions() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();

        userName = user.getUsername();

        UserCache userCache = applicationUserRemote.findUserCache(userId);
        if (userCache.getProfilePicture() == null) {
            return SUCCESS;
        }
        return SUCCESS;
    }

    public String companyOptions() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();

        userName = user.getUsername();
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

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        servletRequest = hsr;
    }
}
