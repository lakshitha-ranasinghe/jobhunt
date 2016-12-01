using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net.Http;
using System.Net;

namespace NIBM_Project
{
    public class FinalJobSeeker
    {
        private int id;
        private JobSeeker jobSeeker;
        private JobSeekerPreferences jobSeekerPreferences;
        private JobSeekerEducation jobSeekerEducation;
        private JobSeekerProfessionalQualification jobSeekerProfessionalQualification;
        private bool oldUser;

        public void receiveData(int id, JobSeeker jobSeeker, JobSeekerPreferences jobSeekerPreferences, JobSeekerEducation jobSeekerEducation, JobSeekerProfessionalQualification jobSeekerProfessionalQualification)
        {
            this.id = id;
            this.jobSeeker = jobSeeker;
            this.jobSeekerPreferences = jobSeekerPreferences;
            this.jobSeekerEducation = jobSeekerEducation;
            this.jobSeekerProfessionalQualification = jobSeekerProfessionalQualification;
        }

        public void excute(bool oldUser)
        {
            this.oldUser = oldUser;
            //Personal
            int personalId = id;
            String name = jobSeeker.getName();
            String address = jobSeeker.getAddress();
            String title = jobSeeker.getTitle();
            String birthDay = jobSeeker.getBirthday().ToString("yyyy-MM-dd HH:mm:ss");
            long mobileTel = jobSeeker.getTelephoneMobile();
            long homeTel = jobSeeker.getTelephoneHome();
            String email = jobSeeker.getEmail();

            //Preference
            String qualifiedField = jobSeekerPreferences.getQualifiedField();
            String interestedPosition = jobSeekerPreferences.getinterestedPosition();
            String lastJob = jobSeekerPreferences.getLastJob();

            //Education
            int OLYear = jobSeekerEducation.getOLYear();
            String OLSchool = jobSeekerEducation.getOLSchool();
            int[] OLIds = jobSeekerEducation.getOrdinaryLevelId();
            String[] OLSubjects = jobSeekerEducation.getOLSubjects();
            String[] OLResults = jobSeekerEducation.getOLResults();

            int ALYear = jobSeekerEducation.getALYear();
            String ALSchool = jobSeekerEducation.getALSchool();
            int[] ALIds = jobSeekerEducation.getAdvanceLevelId();
            String[] ALSubjects = jobSeekerEducation.getALSubjects();
            String[] ALResults = jobSeekerEducation.getALResults();

            String[] institutes = jobSeekerEducation.getInstitute();
            String[] courses = jobSeekerEducation.getCourse();
            int[] years = jobSeekerEducation.getYear();
            double[] GPA = jobSeekerEducation.getGPA();
            int[] higherEducationId = jobSeekerEducation.getHigherEducationId();

            //Professional
            int expYears = jobSeekerProfessionalQualification.getExpYears();

            String firstDesignation = jobSeekerProfessionalQualification.getFirstDesignation();
            String firstCompany = jobSeekerProfessionalQualification.getFirstCompany();
            String firstStartDate = jobSeekerProfessionalQualification.getFirstStartDate().ToString("yyyy-MM-dd HH:mm:ss"); ;
            String firstEndDate = jobSeekerProfessionalQualification.getFirstEndDate().ToString("yyyy-MM-dd HH:mm:ss"); ;
            int firstCompanyId = jobSeekerProfessionalQualification.getFirstCompanyId();

            String secondDesignation = jobSeekerProfessionalQualification.getSecondDesignation();
            String secondCompany = jobSeekerProfessionalQualification.getSecondCompany();
            String secondStartDate = jobSeekerProfessionalQualification.getSecondStartDate().ToString("yyyy-MM-dd HH:mm:ss"); ;
            String secondEndDate = jobSeekerProfessionalQualification.getSecondEndDate().ToString("yyyy-MM-dd HH:mm:ss"); ;
            int secondCompanyId = jobSeekerProfessionalQualification.getSecondCompanyId();

            String otherFirstTitle = jobSeekerProfessionalQualification.getOtherFirstTitle();
            int otherFirstYear = jobSeekerProfessionalQualification.getOtherFirstYear();
            String otherFirstDetails = jobSeekerProfessionalQualification.getOtherFirstDetails();
            int firstOtherId = jobSeekerProfessionalQualification.getFirstOtherId();

            String otherSecondTitle = jobSeekerProfessionalQualification.getOtherSecondTitle();
            int otherSecondYear = jobSeekerProfessionalQualification.getOtherSecondYear();
            String otherSecondDetails = jobSeekerProfessionalQualification.getOtherSecondDetails();
            int secondOtherId = jobSeekerProfessionalQualification.getSecondOtherId();

            //Temp Database Handle
            HandleDatabase handleDatabase = new HandleDatabase();
            if (!oldUser)
            {

                Rest rest = new Rest("Jobseeker/Post");
                rest.SetMethod("POST");
                Dictionary<string, string> postData = new Dictionary<string, string>();

                postData.Add("personalID", personalId.ToString());
                postData.Add("name", name);
                postData.Add("address", address);
                postData.Add("title", title);
                postData.Add("birthDay", birthDay);
                postData.Add("mobileTel", mobileTel.ToString());
                postData.Add("homeTel", homeTel.ToString());
                postData.Add("email", email);
                postData.Add("qualifiedField", qualifiedField);
                postData.Add("interestedPosition", interestedPosition);
                postData.Add("lastJob", lastJob);
                postData.Add("OLYear", OLYear.ToString());
                postData.Add("OLSchool", OLSchool);
                postData.Add("ALYear", ALYear.ToString());
                postData.Add("ALSchool", ALSchool);

                for (int i = 0; i <= OLSubjects.Length - 1; i++)
                {
                    postData.Add("OLSubjects[" + i + "]", OLSubjects[i]);
                    postData.Add("OLResults[" + i + "]", OLResults[i]);
                }

                for (int i = 0; i <= ALSubjects.Length - 1; i++)
                {
                    postData.Add("ALSubjects[" + i + "]", ALSubjects[i]);
                    postData.Add("ALResults[" + i + "]", ALResults[i]);
                }

                for (int i = 0; i <= institutes.Length - 1; i++)
                {
                    postData.Add("institutes[" + i + "]", institutes[i]);
                    postData.Add("courses[" + i + "]", courses[i]);
                    postData.Add("years[" + i + "]", years[i].ToString());
                    postData.Add("GPA[" + i + "]", GPA[i].ToString());
                }

                postData.Add("expYears", expYears.ToString());
                postData.Add("firstDesignation", firstDesignation);
                postData.Add("firstCompany", firstCompany);
                postData.Add("firstStartDate", firstStartDate);
                postData.Add("firstEndDate", firstEndDate);
                postData.Add("secondDesignation", secondDesignation);
                postData.Add("secondCompany", secondCompany);
                postData.Add("secondStartDate", secondStartDate);
                postData.Add("secondEndDate", secondEndDate);
                postData.Add("otherFirstTitle", otherFirstTitle);
                postData.Add("otherFirstYear", otherFirstYear.ToString());
                postData.Add("otherFirstDetails", otherFirstDetails);
                postData.Add("otherSecondTitle", otherSecondTitle);
                postData.Add("otherSecondYear", otherSecondYear.ToString());
                postData.Add("otherSecondDetails", otherSecondDetails);

                try
                {
                    rest.setFormData(postData);

                    rest.Execute();
                }
                catch (WebException ex)
                {
                    throw ex;
                }
                catch (Exception ex)
                {
                    throw ex;
                }
            }
            else
            {
                Rest rest = new Rest("Jobseeker/Put");
                rest.SetMethod("PUT");

                Dictionary<string, string> postData = new Dictionary<string, string>();

                postData.Add("personalID", personalId.ToString());
                postData.Add("name", name);
                postData.Add("address", address);
                postData.Add("title", title);
                postData.Add("birthDay", birthDay);
                postData.Add("mobileTel", mobileTel.ToString());
                postData.Add("homeTel", homeTel.ToString());
                postData.Add("email", email);
                postData.Add("qualifiedField", qualifiedField);
                postData.Add("interestedPosition", interestedPosition);
                postData.Add("lastJob", lastJob);
                postData.Add("OLYear", OLYear.ToString());
                postData.Add("OLSchool", OLSchool);
                postData.Add("ALYear", ALYear.ToString());
                postData.Add("ALSchool", ALSchool);

                for (int i = 0; i <= OLSubjects.Length - 1; i++)
                {
                    postData.Add("OLSubjects[" + i + "]", OLSubjects[i]);
                    postData.Add("OLResults[" + i + "]", OLResults[i]);
                }

                for (int i = 0; i <= ALSubjects.Length - 1; i++)
                {
                    postData.Add("ALSubjects[" + i + "]", ALSubjects[i]);
                    postData.Add("ALResults[" + i + "]", ALResults[i]);
                }

                for (int i = 0; i <= institutes.Length - 1; i++)
                {
                    postData.Add("institutes[" + i + "]", institutes[i]);
                    postData.Add("courses[" + i + "]", courses[i]);
                    postData.Add("years[" + i + "]", years[i].ToString());
                    postData.Add("GPA[" + i + "]", GPA[i].ToString());
                }

                postData.Add("expYears", expYears.ToString());
                postData.Add("firstDesignation", firstDesignation);
                postData.Add("firstCompany", firstCompany);
                postData.Add("firstStartDate", firstStartDate);
                postData.Add("firstEndDate", firstEndDate);
                postData.Add("secondDesignation", secondDesignation);
                postData.Add("secondCompany", secondCompany);
                postData.Add("secondStartDate", secondStartDate);
                postData.Add("secondEndDate", secondEndDate);
                postData.Add("otherFirstTitle", otherFirstTitle);
                postData.Add("otherFirstYear", otherFirstYear.ToString());
                postData.Add("otherFirstDetails", otherFirstDetails);
                postData.Add("otherSecondTitle", otherSecondTitle);
                postData.Add("otherSecondYear", otherSecondYear.ToString());
                postData.Add("otherSecondDetails", otherSecondDetails);


                try
                {
                    rest.setFormData(postData);

                    rest.Execute();
                }
                catch (WebException ex)
                {
                    throw ex;
                }
                catch (Exception ex)
                {
                    throw ex;
                }

            }
        }
    }
}
