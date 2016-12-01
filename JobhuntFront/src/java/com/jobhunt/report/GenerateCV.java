/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jobhunt.report;

import com.jobhunt.entity.ApplicationUser;
import com.jobhunt.entity.Jobseeker;
import com.jobhunt.entity.JobseekerAl;
import com.jobhunt.entity.JobseekerEducation;
import com.jobhunt.entity.JobseekerOl;
import com.jobhunt.entity.JobseekerUniversity;
import com.jobhunt.entity.JobseekerWorkedcompany;
import com.jobhunt.remote.JobseekerRemote;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

/**
 *
 * @author Lakshitha
 */
public class GenerateCV extends ActionSupport {

    private Integer seekerId;
    private InputStream fileStream;
    private final JobseekerRemote jobSeekerManagement = lookupJobseekerManagementRemote();
    private final static int FIRSTRESULT = 0;
    private String objectives;
    private String other;
    private String reference1;
    private String reference2;
    private Boolean isCompanyOneRemoved;
    private Boolean isCompanyTwoRemoved;
    private Boolean isCompanyThreeRemoved;
    private Boolean isALRemoved;
    private Boolean isHigherEducationOneRemoved;
    private Boolean isHigherEducationTwoRemoved;
    private Boolean isHigherEducationThreeRemoved;
    private boolean isThisForJobseeker;
    private String objective;
    private String firstReference;
    private String secondReference;

    public String getFirstReference() {
        return firstReference;
    }

    public void setFirstReference(String firstReference) {
        this.firstReference = firstReference;
    }

    public String getSecondReference() {
        return secondReference;
    }

    public void setSecondReference(String secondReference) {
        this.secondReference = secondReference;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public Boolean getIsCompanyOneRemoved() {
        return isCompanyOneRemoved;
    }

    public void setIsCompanyOneRemoved(Boolean isCompanyOneRemoved) {
        this.isCompanyOneRemoved = isCompanyOneRemoved;
    }

    public Boolean getIsCompanyTwoRemoved() {
        return isCompanyTwoRemoved;
    }

    public void setIsCompanyTwoRemoved(Boolean isCompanyTwoRemoved) {
        this.isCompanyTwoRemoved = isCompanyTwoRemoved;
    }

    public Boolean getIsCompanyThreeRemoved() {
        return isCompanyThreeRemoved;
    }

    public void setIsCompanyThreeRemoved(Boolean isCompanyThreeRemoved) {
        this.isCompanyThreeRemoved = isCompanyThreeRemoved;
    }

    public Boolean getIsALRemoved() {
        return isALRemoved;
    }

    public void setIsALRemoved(Boolean isALRemoved) {
        this.isALRemoved = isALRemoved;
    }

    public Boolean getIsHigherEducationOneRemoved() {
        return isHigherEducationOneRemoved;
    }

    public void setIsHigherEducationOneRemoved(Boolean isHigherEducationOneRemoved) {
        this.isHigherEducationOneRemoved = isHigherEducationOneRemoved;
    }

    public Boolean getIsHigherEducationTwoRemoved() {
        return isHigherEducationTwoRemoved;
    }

    public void setIsHigherEducationTwoRemoved(Boolean isHigherEducationTwoRemoved) {
        this.isHigherEducationTwoRemoved = isHigherEducationTwoRemoved;
    }

    public Boolean getIsHigherEducationThreeRemoved() {
        return isHigherEducationThreeRemoved;
    }

    public void setIsHigherEducationThreeRemoved(Boolean isHigherEducationThreeRemoved) {
        this.isHigherEducationThreeRemoved = isHigherEducationThreeRemoved;
    }

    public boolean isIsThisForJobseeker() {
        return isThisForJobseeker;
    }

    public void setIsThisForJobseeker(boolean isThisForJobseeker) {
        this.isThisForJobseeker = isThisForJobseeker;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getReference1() {
        return reference1;
    }

    public void setReference1(String reference1) {
        this.reference1 = reference1;
    }

    public String getReference2() {
        return reference2;
    }

    public void setReference2(String reference2) {
        this.reference2 = reference2;
    }

    public Integer getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(Integer seekerId) {
        this.seekerId = seekerId;
    }

    public InputStream getFileStream() {
        return fileStream;
    }

    public void setFileStream(InputStream fileStream) {
        this.fileStream = fileStream;
    }

    public GenerateCV() {
    }

    public String execute() throws Exception {
        Jobseeker jobseeker;
        if (seekerId != null) {
            jobseeker = jobSeekerManagement.findPersonalProfile(seekerId);
            System.out.println(jobseeker);
        } else {
            Map session = ActionContext.getContext().getSession();
            ApplicationUser user = (ApplicationUser) session.get("user");
            Integer userId = user.getId();
            jobseeker = jobSeekerManagement.findPersonalProfile(userId);
        }

        Map jobseekerData = new HashMap();
        String firstName = jobseeker.getFirstName();
        String lastName = jobseeker.getLastName();
        String nameWithInitials = firstName.charAt(0) + ". " + lastName;
        String address = jobseeker.getAddress1() + ", " + jobseeker.getAddress2() + ", " + jobseeker.getAddress3();
        String title = jobseeker.getTitle();
        Date birthday = jobseeker.getBirthday();
        String telephone = String.valueOf(jobseeker.getTelephone());
        String mobile = String.valueOf(jobseeker.getMobile());
        String email = jobseeker.getEmail();
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);

        jobseekerData.put("fullName", firstName + " " + lastName);
        jobseekerData.put("nameWithInitial", nameWithInitials);
        jobseekerData.put("address", address);
        jobseekerData.put("title", title);
        jobseekerData.put("birthday", df.format(birthday));
        jobseekerData.put("telephone", "0" + telephone);
        jobseekerData.put("mobile", "0" + mobile);
        jobseekerData.put("email", email);
        jobseekerData.put("alName", "Adavanced Level");

//        if(!isThisForJobseeker){
//            objective = "I wish to work in a challenging environment in a dynamic organization with a team working culture where I can complete my internship";
//        }
        jobseekerData.put("objective", objective);

        List<JobseekerOl> jobseekerOlList = (List) jobseeker.getJobseekerOlCollection();
        List<JobseekerAl> jobseekerAlList = (List) jobseeker.getJobseekerAlCollection();
        List<JobseekerUniversity> jobseekerUniversities = (List) jobseeker.getJobseekerUniversityCollection();

        List educationalResults = new ArrayList();
        for (JobseekerOl jobseekerOl : jobseekerOlList) {
            Map<String, String> result = new HashMap<>();
            result.put("olSubject", jobseekerOl.getSubject());
            result.put("olMark", jobseekerOl.getMark());
            educationalResults.add(result);
        }

        int educationalResultIterator = 0;
        for (JobseekerAl jobseekerAl : jobseekerAlList) {
            if (isThisForJobseeker) {
                if (!isALRemoved) {
                    ((Map<String, String>) (educationalResults.get(educationalResultIterator))).put("alSubject", jobseekerAl.getSubject());
                    ((Map<String, String>) (educationalResults.get(educationalResultIterator))).put("alMark", jobseekerAl.getMark());
                } else {
                    jobseekerData.remove("alName");
                }
            } else {
                ((Map<String, String>) (educationalResults.get(educationalResultIterator))).put("alSubject", jobseekerAl.getSubject());
                ((Map<String, String>) (educationalResults.get(educationalResultIterator))).put("alMark", jobseekerAl.getMark());
            }
            educationalResultIterator++;
        }

        educationalResultIterator = 0;
        for (JobseekerUniversity jobseekerUniversity : jobseekerUniversities) {
            jobseekerData.put("uniName" + educationalResultIterator, jobseekerUniversity.getName());
            jobseekerData.put("uniCourse" + educationalResultIterator, jobseekerUniversity.getCourse());
            jobseekerData.put("completedYear" + educationalResultIterator, String.valueOf(jobseekerUniversity.getCompletedYear()));
            jobseekerData.put("gpa" + educationalResultIterator, String.valueOf(jobseekerUniversity.getGpa()));
            educationalResultIterator++;
        }
        
        jobseekerData.put("higherEducation", "Higher Education");
        
        int numberOfHigherEducationRemovals = 0;
        if (isThisForJobseeker) {
            if (isHigherEducationOneRemoved) {
                jobseekerData.remove("uniName0");
                jobseekerData.remove("uniCourse0");
                jobseekerData.remove("completedYear0");
                jobseekerData.remove("gpa0");
                numberOfHigherEducationRemovals++;
            }
            if (isHigherEducationTwoRemoved) {
                jobseekerData.remove("uniName1");
                jobseekerData.remove("uniCourse1");
                jobseekerData.remove("completedYear1");
                jobseekerData.remove("gpa1");
                numberOfHigherEducationRemovals++;
            }
            if (isHigherEducationThreeRemoved) {
                jobseekerData.remove("uniName2");
                jobseekerData.remove("uniCourse2");
                jobseekerData.remove("completedYear2");
                jobseekerData.remove("gpa3");
                numberOfHigherEducationRemovals++;
            }
            if(numberOfHigherEducationRemovals==3){
                jobseekerData.remove("higherEducation");
            }
        }

        
        JobseekerEducation jobseekerEducation = ((List<JobseekerEducation>) jobseeker.getJobseekerEducationCollection()).get(FIRSTRESULT);

        jobseekerData.put("alSchool", jobseekerEducation.getAlSchool());
        jobseekerData.put("olSchool", jobseekerEducation.getOlSchool());
        jobseekerData.put("olYear", String.valueOf(jobseekerEducation.getOlYear()));
        jobseekerData.put("alYear", String.valueOf(jobseekerEducation.getAlYear()));

        if (isThisForJobseeker) {
            if (isALRemoved) {
                jobseekerData.remove("alSchool");
                jobseekerData.remove("alYear");
            }
        }
        DateFormat mediumdf = DateFormat.getDateInstance(DateFormat.MEDIUM);
        jobseekerData.put("professional", "Professional Qualification");
        
        List <JobseekerWorkedcompany> jobseekerCompanies = ((List<JobseekerWorkedcompany>)jobseeker.getJobseekerWorkedcompanyCollection());
        educationalResultIterator = 1;
        for(JobseekerWorkedcompany jobseekerWorkedCompanies : jobseekerCompanies){
            String duration = mediumdf.format(jobseekerWorkedCompanies.getStartDate()) + " - " + (mediumdf.format(jobseekerWorkedCompanies.getEndDate()));
            jobseekerData.put("company"+educationalResultIterator, jobseekerWorkedCompanies.getName());
            jobseekerData.put("duration"+educationalResultIterator, duration);
            jobseekerData.put("position"+educationalResultIterator, jobseekerWorkedCompanies.getDesignation());
            educationalResultIterator++;
        }
        
        jobseekerData.put("firstReference", firstReference);
        jobseekerData.put("secondReference", secondReference);

        JasperReport jr = JasperCompileManager.compileReport(getClass().getResourceAsStream("reports/JobseekerCV.jrxml"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jr, jobseekerData, new JRMapCollectionDataSource(educationalResults));
        byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
        fileStream = new ByteArrayInputStream(b);
        return SUCCESS;
    }

    private JobseekerRemote lookupJobseekerManagementRemote() {
        try {
            Context c = new InitialContext();
            return (JobseekerRemote) c.lookup("java:global/JobhuntEJB/JobseekerBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
