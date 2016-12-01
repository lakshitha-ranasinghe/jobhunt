/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.CompanyProfile;
import com.jobhunt.remote.ApplicationUserRemote;
import com.jobhunt.remote.CompanyRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
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
public class ManageCompanyProfile extends ActionSupport {

    private final CompanyRemote companyRemote = lookupCompanyManagementRemote();
    private final static String UPDATE = "update";
    private CompanyProfile companyProfile;

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }

    public ManageCompanyProfile() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();
        companyProfile.setId(userId);

        CompanyProfile companyProfileObject = companyRemote.findCompanyProfile(userId);
        Map<String, String> remoteCompany = new HashMap<>();
        remoteCompany.put("id", String.valueOf(userId));
        remoteCompany.put("name", companyProfile.getName());
        remoteCompany.put("description", companyProfile.getDescription());
        remoteCompany.put("address1", companyProfile.getAddress1());
        remoteCompany.put("address2", companyProfile.getAddress2());
        remoteCompany.put("address3", companyProfile.getAddress3());
        remoteCompany.put("telephone", String.valueOf(companyProfile.getTelephone()));
        remoteCompany.put("mobile", String.valueOf(companyProfile.getMobile()));
        remoteCompany.put("type", companyProfile.getType());
        remoteCompany.put("jobType", companyProfile.getJobType());
        remoteCompany.put("email", companyProfile.getEmail());
        remoteCompany.put("website", companyProfile.getWebsite());
        
        if (companyProfileObject == null) {
            companyRemote.createCompanyProfile(remoteCompany);
            return SUCCESS;
        } else {
            companyRemote.updateCompanyProfile(remoteCompany, userId);
            return UPDATE;
        }
    }

    private CompanyRemote lookupCompanyManagementRemote() {
        try {
            Context c = new InitialContext();
            return (CompanyRemote) c.lookup("java:global/JobhuntEJB/CompanyBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
