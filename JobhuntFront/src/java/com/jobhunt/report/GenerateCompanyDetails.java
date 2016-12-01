/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.report;

import com.jobhunt.entity.CompanyProfile;
import com.jobhunt.remote.CompanyRemote;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Lakshitha
 */
public class GenerateCompanyDetails extends ActionSupport {
    private final CompanyRemote companyRemote = lookupCompanyManagementRemote();
    private String companyName;
    private InputStream fileStream;

    public InputStream getFileStream() {
        return fileStream;
    }

    public void setFileStream(InputStream fileStream) {
        this.fileStream = fileStream;
    }
    
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public GenerateCompanyDetails() {
    }
    
    public String execute() throws Exception {
        CompanyProfile companyProfile = companyRemote.findCompanyByName(companyName.trim());
        Map companyData = new HashMap();
        companyData.put("name", companyProfile.getName());
        companyData.put("description", companyProfile.getDescription());
        companyData.put("type", companyProfile.getType());
        companyData.put("jobType", companyProfile.getJobType());
        companyData.put("address", companyProfile.getAddress1() + ", " + companyProfile.getAddress2() + ", "+ companyProfile.getAddress3());
        companyData.put("telephone", String.valueOf(companyProfile.getTelephone()));
        companyData.put("mobile", String.valueOf(companyProfile.getMobile()));
        companyData.put("email", companyProfile.getEmail());
        companyData.put("website", companyProfile.getWebsite());
        
        JasperReport jr = JasperCompileManager.compileReport(getClass().getResourceAsStream("reports/CompanyDetails.jrxml"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jr, companyData, new JREmptyDataSource());
        byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
        fileStream = new ByteArrayInputStream(b);
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
