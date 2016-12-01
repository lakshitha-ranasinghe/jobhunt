/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.CompanyProfile;
import com.jobhunt.remote.CompanyRemote;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
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
public class OpenCompanyProfile extends ActionSupport {

    private final CompanyRemote companyRemote = lookupCompanyManagementRemote();
    private CompanyProfile companyProfile;

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }

    public OpenCompanyProfile() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        ApplicationUser user = (ApplicationUser) session.get("user");
        Integer userId = user.getId();
        CompanyProfile company = companyRemote.findCompanyProfile(userId);
        if (company != null) {
            setCompanyProfile(company);
        }
        return SUCCESS;
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
