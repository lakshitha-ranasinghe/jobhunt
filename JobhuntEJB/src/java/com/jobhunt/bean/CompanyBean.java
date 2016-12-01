/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.bean;

import com.jobhunt.controller.CompanyProfileJpaController;
import com.jobhunt.controller.exceptions.RollbackFailureException;
import com.jobhunt.entity.CompanyProfile;
import com.jobhunt.remote.CompanyRemote;
import java.util.List;
import java.util.Map;
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
 * @author Lakshitha
 */
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CompanyBean implements CompanyRemote {

    @PersistenceUnit
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction utx;

    @Override
    public CompanyProfile findCompanyProfile(Integer id) {
        try {
            CompanyProfile companyProfile = new CompanyProfileJpaController(utx, emf).findCompanyProfile(id);
            return companyProfile;
        } catch (Exception ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void createCompanyProfile(Map<String, String> company) {
        CompanyProfile companyProfile = new CompanyProfile();

        companyProfile.setId(Integer.parseInt(company.get("id")));
        companyProfile.setName(company.get("name"));
        companyProfile.setDescription(company.get("description"));
        companyProfile.setAddress1(company.get("address1"));
        companyProfile.setAddress2(company.get("address2"));
        companyProfile.setAddress3(company.get("address3"));
        companyProfile.setTelephone(Integer.parseInt(company.get("telephone")));
        companyProfile.setMobile(Integer.parseInt(company.get("mobile")));
        companyProfile.setType(company.get("type"));
        companyProfile.setJobType(company.get("jobType"));
        companyProfile.setWebsite(company.get("website"));
        companyProfile.setEmail(company.get("email"));

        try {
            System.out.println("Creating Company Profile");
            new CompanyProfileJpaController(utx, emf).create(companyProfile);
            System.out.println("Company Profile Created");
        } catch (RollbackFailureException ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateCompanyProfile(Map<String, String> company, Integer id) {
        try {
            CompanyProfile oldCompanyProfile = new CompanyProfileJpaController(utx, emf).findCompanyProfile(id);

            CompanyProfile companyProfile = new CompanyProfile();

            companyProfile.setId(Integer.parseInt(company.get("id")));
            companyProfile.setName(company.get("name"));
            companyProfile.setDescription(company.get("description"));
            companyProfile.setAddress1(company.get("address1"));
            companyProfile.setAddress2(company.get("address2"));
            companyProfile.setAddress3(company.get("address3"));
            companyProfile.setTelephone(Integer.parseInt(company.get("telephone")));
            companyProfile.setMobile(Integer.parseInt(company.get("mobile")));
            companyProfile.setType(company.get("type"));
            companyProfile.setJobType(company.get("jobType"));
            companyProfile.setWebsite(company.get("website"));
            companyProfile.setEmail(company.get("email"));
            companyProfile.setVacancyCollection(oldCompanyProfile.getVacancyCollection());
            
            System.out.println("Updating Company Profile");
            new CompanyProfileJpaController(utx, emf).edit(companyProfile);
            System.out.println("Company Profile Updated");
        } catch (RollbackFailureException ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CompanyBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<CompanyProfile> findAllCompanies() {
        return new CompanyProfileJpaController(utx, emf).findCompanyProfileEntities();
    }

    @Override
    public CompanyProfile findCompanyByName(String name) {
        return new CompanyProfileJpaController(utx, emf).findCompanyProfileByName(name);
    }

}
