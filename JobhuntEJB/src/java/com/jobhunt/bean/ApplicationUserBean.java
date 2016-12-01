/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.bean;

import com.jobhunt.controller.ApplicationUserJpaController;
import com.jobhunt.controller.UserCacheJpaController;
import com.jobhunt.controller.exceptions.RollbackFailureException;
import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.UserCache;
import com.jobhunt.remote.ApplicationUserRemote;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.imageio.ImageIO;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 *
 * @author Lakshitha
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ApplicationUserBean implements ApplicationUserRemote {

    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;

    @Override
    public Boolean createUser(ApplicationUser applicationUser) {
        try {
            new ApplicationUserJpaController(utx, emf).create(applicationUser);
            return true;
        } catch (RollbackFailureException ex) {
            Logger.getLogger(ApplicationUserBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ApplicationUserBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public ApplicationUser findApplicationUser(String username, String password) {
        try {
            ApplicationUser applicationUser = new ApplicationUserJpaController(utx, emf).findApplicationUserByUsernamePassword(username, password);
            return applicationUser;
        } catch (Exception ex) {
            System.out.println("Unknown User");
            Logger.getLogger(ApplicationUserBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Boolean isUsernameTaken(String userName) {
        return new ApplicationUserJpaController(utx, emf).findUsernameExistance(userName);
    }

    @Override
    public void changeLastLogin(Timestamp loginTime, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void uploadProfilePicture(File picture, Integer id) {
        try {
            UserCache userCache = new UserCacheJpaController(utx, emf).findUserCache(id);
            BufferedImage originalImage = null;
            originalImage = ImageIO.read(picture);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            userCache.setProfilePicture(imageInByte);
            new UserCacheJpaController(utx, emf).edit(userCache);
            
        } catch (IOException ex) {
            Logger.getLogger(ApplicationUserBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(ApplicationUserBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public UserCache findUserCache(Integer id) {
        return new UserCacheJpaController(utx, emf).findUserCache(id);
    }

    @Override
    public void updateUserCache(Integer id, Date loginDateTime) {
        UserCache userCache = new UserCacheJpaController(utx, emf).findUserCache(id);
        userCache.setLastLogin(loginDateTime);
        try {
            new UserCacheJpaController(utx, emf).edit(userCache);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(ApplicationUserBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createUserCache(Integer id, Date now) {
        UserCache userCache = new UserCache(id);
        userCache.setLastLogin(now);
        try {
            new UserCacheJpaController(utx, emf).create(userCache);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(ApplicationUserBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ApplicationUserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Boolean changePassword(Integer id, String password) {
        return new ApplicationUserJpaController(utx, emf).changePassword(id, password);
    }

}
