/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.remote;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.UserCache;
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author Lakshitha
 */
@Remote
public interface ApplicationUserRemote {
    public Boolean createUser(ApplicationUser applicationUser);
    
    public ApplicationUser findApplicationUser(String username, String password);
    
    public Boolean isUsernameTaken(String userName);
    
    public void changeLastLogin(Timestamp loginTime, Integer id);
    
    public void uploadProfilePicture(File picture, Integer id);
    
    public UserCache findUserCache(Integer id);
    
    public void updateUserCache(Integer id, Date loginDateTime);
    
    public void createUserCache(Integer id, Date now);
    
    public Boolean changePassword(Integer id, String password);
}

