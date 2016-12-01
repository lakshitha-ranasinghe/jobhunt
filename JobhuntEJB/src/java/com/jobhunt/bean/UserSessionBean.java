/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.bean;

import com.jobhunt.controller.UserSessionJpaController;
import com.jobhunt.controller.exceptions.RollbackFailureException;
import com.jobhunt.entity.UserSession;
import com.jobhunt.remote.UserSessionRemote;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 *
 * @author Githanjana Ishara
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserSessionBean implements UserSessionRemote
{
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;

    @Override
    public void createSession(int userid, String token) 
    {
        UserSession session = new UserSession();
        session.setUserid(userid);
        session.setToken(token);
        session.setTimeCreated(new Date());
        try {
            new UserSessionJpaController(utx, emf).create(session);
        } catch (Exception ex) {
            Logger.getLogger(UserSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public UserSession validateSession(String token) 
    {
        UserSessionJpaController controller = new UserSessionJpaController(utx, emf);
        UserSession session = controller.findUserSession(token);
        if(session != null)
        {   
            session.setTimeCreated(new Date());
            try {
                controller.edit(session);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(UserSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(UserSessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return session;
        }
        else
            return new UserSession("invalid", 0, new Date());
    }

    @Override
    public void destorySession(String token) 
    {
        try {
            new UserSessionJpaController(utx, emf).destroy(token);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(UserSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UserSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
