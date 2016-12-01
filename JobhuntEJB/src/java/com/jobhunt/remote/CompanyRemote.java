/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.remote;

import com.jobhunt.entity.CompanyProfile;
import java.util.List;
import java.util.Map;
import javax.ejb.Remote;

/**
 *
 * @author Lakshitha
 */
@Remote
public interface CompanyRemote {
    public CompanyProfile findCompanyProfile(Integer id);
    
    public void createCompanyProfile(Map<String,String> company);
    
    public void updateCompanyProfile(Map<String,String> company, Integer id);
    
    public List<CompanyProfile> findAllCompanies();
    
    public CompanyProfile findCompanyByName(String name);
}
