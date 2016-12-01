/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.action;

import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.UserCache;
import com.jobhunt.entity.Vacancy;
import com.jobhunt.entity.VacancyApply;
import com.jobhunt.remote.ApplicationUserRemote;
import com.jobhunt.remote.VacancyRemote;
import com.opensymphony.xwork2.ActionSupport;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
public class OpenApplicants extends ActionSupport implements ServletRequestAware {

    private final VacancyRemote vacancyRemote = lookupVacancyManagementRemote();
    private final ApplicationUserRemote applicationUserRemote = lookupApplicationUserManagementRemote();
    private Integer vacancyId;
    private Vacancy vacancy;
    private Map<Jobseeker, Integer> applicants;
    private HttpServletRequest servletRequest;

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Map<Jobseeker, Integer> getApplicants() {
        return applicants;
    }

    public void setApplicants(Map<Jobseeker, Integer> applicants) {
        this.applicants = applicants;
    }

    public Integer getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(Integer vacancyId) {
        this.vacancyId = vacancyId;
    }

    public OpenApplicants() {
    }

    public String execute() throws Exception {
        vacancy = vacancyRemote.findVacany(vacancyId);
        List<VacancyApply> vacancyApplys = (List) vacancy.getVacancyApplyCollection();
        applicants = new HashMap<>();
        for (VacancyApply vacancyApply : vacancyApplys) {
            downloadProfilePicture(vacancyApply.getJobseekerId().getId());
            applicants.put(vacancyApply.getJobseekerId(), vacancyApply.getStatus());
        }
        return SUCCESS;
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

    private void downloadProfilePicture(Integer id) {
        UserCache userCache = applicationUserRemote.findUserCache(id);
        BufferedImage img = null;
        String imageName = "applicant"+id+".jpg";
        try {
            img = ImageIO.read(new ByteArrayInputStream(userCache.getProfilePicture()));
        } catch (Exception ex) {
            Logger.getLogger(OpenApplicants.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Could Not find image");   
        }
        if (img != null) {
            System.out.println("Image Found");
            String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
            File outputfile = new File(filePath + "external\\image\\"+imageName);
            try {
                ImageIO.write(img, "jpg", outputfile);
            } catch (IOException ex) {
                Logger.getLogger(OpenApplicants.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
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
}
