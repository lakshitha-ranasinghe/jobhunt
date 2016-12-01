/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.remote;

import com.jobhunt.entity.UserSession;
import javax.ejb.Remote;

/**
 *
 * @author Githanjana Ishara
 */
@Remote
public interface UserSessionRemote 
{
    public void createSession(int userid, String token);
    
    public UserSession validateSession(String token);
    
    public void destorySession(String token);
}
