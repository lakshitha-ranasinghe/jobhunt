/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.UserCache;
import com.jobhunt.remote.ApplicationUserRemote;
import com.jobhunt.remote.VacancyRemote;
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Date;
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
public class Login extends ActionSupport implements ServletRequestAware {

    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    private final ApplicationUserRemote applicationUserRemote = lookupApplicationUserManagementRemote();
    private ApplicationUser applicationUser;
    private String username;
    private String password;
    private final static String SEEKER = "seeker";
    private final static String COMPANY = "company";
    private HttpServletRequest servletRequest;

    public Login() {
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String execute() throws Exception {

        ApplicationUser foundapplicationUser = applicationUserRemote.findApplicationUser(username, password);
        if (foundapplicationUser != null) {
            System.out.println("Valid User Found");
            Map session = ActionContext.getContext().getSession();
            session.put("user", foundapplicationUser);

            UserCache userCache = applicationUserRemote.findUserCache(foundapplicationUser.getId());

            if (userCache != null) {
                if (foundapplicationUser.getType().equals("seeker")) {
                    BufferedImage img;
                    try {
                        img = ImageIO.read(new ByteArrayInputStream(userCache.getProfilePicture()));
                    } catch (Exception ex) {
                        System.out.println("Could Not find image");
                        System.out.println("Adding Default Image");
                        String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                        File file = new File(filePath + "external\\image\\NoProfile.jpg");
                        applicationUserRemote.uploadProfilePicture(file, foundapplicationUser.getId());
                        System.out.println("Default Image Added");
                        userCache = applicationUserRemote.findUserCache(foundapplicationUser.getId());
                        img = ImageIO.read(new ByteArrayInputStream(userCache.getProfilePicture()));
                    }
                    if (img != null) {
                        String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                        File outputfile = new File(filePath + "external\\image\\applicant.jpg");
                        ImageIO.write(img, "jpg", outputfile);
                    }
                    applicationUserRemote.updateUserCache(userCache.getId(), new Date());
                } else {
                    BufferedImage img;
                    try {
                        img = ImageIO.read(new ByteArrayInputStream(userCache.getProfilePicture()));
                    } catch (Exception ex) {
                        System.out.println("Could Not find image");
                        System.out.println("Adding Default Image");
                        String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                        File file = new File(filePath + "external\\image\\NoProfile.jpg");
                        applicationUserRemote.uploadProfilePicture(file, foundapplicationUser.getId());
                        System.out.println("Default Image Added");
                        userCache = applicationUserRemote.findUserCache(foundapplicationUser.getId());
                        img = ImageIO.read(new ByteArrayInputStream(userCache.getProfilePicture()));
                    }
                    if (img != null) {
                        String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
                        File outputfile = new File(filePath + "external\\image\\company.jpg");
                        ImageIO.write(img, "jpg", outputfile);
                    }
                }
            } else {
                applicationUserRemote.createUserCache(foundapplicationUser.getId(), new Date());
            }
            if (foundapplicationUser.getType().equals("seeker")) {

                applicationUser = foundapplicationUser;
                return SEEKER;
            } else {
                applicationUser = foundapplicationUser;
                return COMPANY;
            }
        } else {
            System.out.println("Invalid User");
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

    private VacancyRemote lookupVacancyManagementRemote() {
        try {
            Context c = new InitialContext();
            return (VacancyRemote) c.lookup("java:global/JobhuntEJB/VacancyBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
}
