using System;
using System.Collections.Generic;
using Newtonsoft.Json.Linq;
using System.Net;


namespace NIBM_Project
{
    public class HandleDatabase
    {
        private static JobSeeker jobSeeker;
        private static JobSeekerPreferences jobSeekerPreferences;
        private static JobSeekerEducation jobSeekerEducation;
        private static JobSeekerProfessionalQualification jobSeekerProfessionalQualification;

        private static Company company;
        private static CompanyVacancy companyVacancy;

        public void getJobSeekerData(int id)
        {
            jobSeeker = new JobSeeker();
            jobSeekerPreferences = new JobSeekerPreferences();
            jobSeekerEducation = new JobSeekerEducation();
            jobSeekerProfessionalQualification = new JobSeekerProfessionalQualification();

            JObject ja;

            try
            {
                Rest rest = new Rest("Jobseeker/Get/" + id);
                string json = rest.Execute();
                ja = JObject.Parse(json);

                jobSeeker.setId(id);
                jobSeeker.setFullName(ja["name"].ToString());
                jobSeeker.setTitle(ja["title"].ToString());
                jobSeeker.setAddress(ja["address"].ToString());
                jobSeeker.setTelMobile(Convert.ToInt32(ja["mobileTel"]));
                jobSeeker.setTelHome(Convert.ToInt32(ja["homeTel"]));
                jobSeeker.setDateOfBirth(Convert.ToDateTime(ja["birthDay"]));
                jobSeeker.setEmail(ja["email"].ToString());


                jobSeekerPreferences.setQualifiedField(ja["qualifiedField"].ToString());
                jobSeekerPreferences.setInterestedPosition(ja["interestedPosition"].ToString());
                jobSeekerPreferences.setLastJob(ja["lastJob"].ToString());



                OrdinaryLevel tmpOL = new OrdinaryLevel();

                tmpOL.setSchool(ja["OLSchool"].ToString());
                tmpOL.setYear(Convert.ToInt32(ja["OLYear"].ToString()));

                JArray ols = (JArray)ja["OLSubjects"];
                JArray olr = (JArray)ja["OLResults"];


                for (int c = 0; c <= ols.Count - 1; c++)
                {
                    tmpOL.setSubjects(c, ols[c].ToString());
                    tmpOL.setResults(c, olr[c].ToString());
                }

                AdvanceLevel tmpAL = new AdvanceLevel();

                tmpAL.setSchool(ja["ALSchool"].ToString());
                tmpAL.setYear(Convert.ToInt32(ja["ALYear"].ToString()));

                JArray als = (JArray)ja["ALSubjects"];
                JArray alr = (JArray)ja["ALResults"];

                for (int c = 0; c <= als.Count - 1; c++)
                {
                    tmpAL.setSubjects(c, als[c].ToString());
                    tmpAL.setResults(c, alr[c].ToString());
                }

                jobSeekerEducation.setOrdinaryLevel(tmpOL);
                jobSeekerEducation.setAdvanceLevel(tmpAL);

                HigherEducation tmpHE = new HigherEducation();

                JArray hei = (JArray)ja["institutes"];
                JArray hec = (JArray)ja["courses"];
                JArray hey = (JArray)ja["years"];
                JArray heGPA = (JArray)ja["GPA"];

                for (int c = 0; c <= hei.Count - 1; c++)
                {
                    tmpHE.setInstitute(c, hei[c].ToString());
                    tmpHE.setCourse(c, hec[c].ToString());
                    tmpHE.setYear(c, (int)hey[c]);
                    tmpHE.setGpa(c, Convert.ToInt64(heGPA[c]));

                }

                jobSeekerEducation.setHigherEducation(tmpHE);


                JobSeekerProfessionalQualification jsProData = new JobSeekerProfessionalQualification();

                jsProData.setFirstCompanyId(0);
                jsProData.setFirstCompany(ja["firstDesignation"].ToString(), ja["firstCompany"].ToString(), Convert.ToDateTime(ja["firstStartDate"]), Convert.ToDateTime(ja["firstEndDate"]));
                jsProData.setSecondCompanyId(0);
                jsProData.setSecondCompany(ja["secondDesignation"].ToString(), ja["secondCompany"].ToString(), Convert.ToDateTime(ja["secondStartDate"]), Convert.ToDateTime(ja["secondEndDate"]));
                jsProData.setExpYears(Convert.ToInt32(ja["expYears"]));
                jsProData.setFirstOtherId(0);
                jsProData.setSecondOtherId(0);
                jsProData.setFirstOther(ja["otherFirstTitle"].ToString(), Convert.ToInt32(ja["otherFirstYear"]), ja["otherFirstDetails"].ToString());
                jsProData.setSecondOther(ja["otherSecondTitle"].ToString(), Convert.ToInt32(ja["otherSecondYear"]), ja["otherSecondDetails"].ToString());

                StaticJobSeekerData.setJobSeeker(jobSeeker, jobSeekerPreferences, jobSeekerEducation, jsProData);
            }

            catch (WebException ex)
            {
                throw ex;
            }
            catch (IndexOutOfRangeException ex)
            {
                throw ex;
            }
            catch (FormatException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }

        }

        public void getCompanyData(int id)
        {
            try
            {
                company = new Company();

                Rest rest = new Rest("Company/GetCompany/" + id);
                string json = rest.Execute();

                JArray ja = JArray.Parse(json);

                company.setId(Convert.ToInt32(ja[0]["company_id"]));
                company.setCompanyName(ja[0]["name"].ToString());
                company.setAddress(ja[0]["address"].ToString());
                company.setCompanyType(ja[0]["company_type"].ToString());
                company.setFirstTelephone(Convert.ToInt64(ja[0]["telephone_home"]));
                company.setSecondTelephone(Convert.ToInt64(ja[0]["telephone_mobile"]));
                company.setEmail(ja[0]["email"].ToString());
                company.setWebsite(ja[0]["website"].ToString());
                company.setOfferedJobTypes(ja[0]["job_type"].ToString());
                company.setDescription(ja[0]["description"].ToString());

                StaticCompanyData.setCompany(company);
            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (IndexOutOfRangeException ex)
            {
                throw ex;
            }
            catch (FormatException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }

        }

   /*     public void getCompanyVacancyData(int id)
        {
            try
            {
                companyVacancy = new CompanyVacancy();

                Rest rest = new Rest("Company/GetVacancies/" + id);
                JArray ja = JArray.Parse(rest.Execute());

                foreach (JObject o in ja)
                {
                    companyVacancy.setVacancyId(Convert.ToInt32(o["vacancy_Id"].ToString()));
                    companyVacancy.setJobTitle(o["job_title"].ToString());
                    companyVacancy.setVacancyDescription(o["description"].ToString());
                    companyVacancy.setPrerequesties(o["preresquites"].ToString());
                    companyVacancy.setLocation(o["location"].ToString());
                    companyVacancy.setClosingDate(Convert.ToDateTime(o["closing_date"]));
                    companyVacancy.setSalary(Convert.ToInt32(o["salary"]));
                    companyVacancy.setNoOfVacancies(Convert.ToInt32(o["no_of_vacancies"]));
                }


                StaticCompanyVacancy.setCompanyVacancy(companyVacancy);
            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (IndexOutOfRangeException ex)
            {
                throw ex;
            }
            catch (FormatException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }  */

        public void getCompanyData()
        {

            try
            {
                Company tempCompany;
                Dictionary<String, Company> tempDictionary = new Dictionary<string, Company>();
                List<String> tempCompanyList = new List<string>();

                Rest rest = new Rest("Company/GetCompanies");
                JArray ja = JArray.Parse(rest.Execute());

                foreach (JObject o in ja)
                {
                    tempCompany = new Company();
                    tempCompany.setId(Convert.ToInt32(o["company_id"].ToString()));
                    String cName = o["name"].ToString();
                    tempCompany.setCompanyName(cName);
                    tempCompany.setCompanyType(o["company_type"].ToString());
                    tempCompany.setFirstTelephone(Convert.ToInt64(o["telephone_home"]));
                    tempCompany.setSecondTelephone(Convert.ToInt64(o["telephone_mobile"]));
                    tempCompany.setEmail(o["email"].ToString());
                    tempCompany.setAddress(o["address"].ToString());
                    tempCompany.setWebsite(o["website"].ToString());
                    tempCompany.setOfferedJobTypes(o["job_type"].ToString());
                    tempCompany.setDescription(o["description"].ToString());

                    tempDictionary.Add(cName, tempCompany);  //Add Data to Dictionary
                    tempCompanyList.Add(cName); //Add Company Names to List   
                }

                StaticCompanyList.setDictionary(tempDictionary);
                StaticCompanyList.setList(tempCompanyList);
            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (IndexOutOfRangeException ex)
            {
                throw ex;
            }
            catch (FormatException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }


        }

        public void getCompanyVacancyData()
        {
            try
            {

                CompanyVacancy tempCompanyVacancy;
                Dictionary<Int32, CompanyVacancy> tempCompanyVacancyDictionary = new Dictionary<Int32, CompanyVacancy>();
                List<Int32> tempCompanyVacancyList = new List<Int32>();

                Rest rest = new Rest("Company/GetVacancies/" + StaticCompanyID.getID());
                JArray ja = JArray.Parse(rest.Execute());

                foreach (JObject o in ja)
                {
                    tempCompanyVacancy = new CompanyVacancy();

                    int vId = Convert.ToInt32(o["vacancy_Id"]);

                    tempCompanyVacancy.setVacancyId(vId);
                    tempCompanyVacancy.setVacancyId(Convert.ToInt32(o["vacancy_Id"]));
                    tempCompanyVacancy.setJobTitle(o["job_title"].ToString());
                    tempCompanyVacancy.setVacancyDescription(o["description"].ToString());
                    tempCompanyVacancy.setPrerequesties(o["preresquites"].ToString());
                    tempCompanyVacancy.setLocation(o["location"].ToString());
                    tempCompanyVacancy.setClosingDate(Convert.ToDateTime(o["closing_date"].ToString()));
                    tempCompanyVacancy.setSalary(Convert.ToInt32(o["salary"]));
                    tempCompanyVacancy.setNoOfVacancies(Convert.ToInt32(o["no_of_vacancies"]));
                    tempCompanyVacancy.setCompanyId(Convert.ToInt32(o["company_id"].ToString()));

                    tempCompanyVacancyDictionary.Add(vId, tempCompanyVacancy);
                    tempCompanyVacancyList.Add(vId);
                }

                StaticCompanyVacancyList.setCompanyVacancyDictionary(tempCompanyVacancyDictionary);
                StaticCompanyVacancyList.setCompanyVacancyList(tempCompanyVacancyList);
            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (IndexOutOfRangeException ex)
            {
                throw ex;
            }
            catch (FormatException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }

        }
        public void getVacancyData()
        {
            try
            {
                CompanyVacancy tempCompanyVacancy;
                List<CompanyVacancy> tempCompanyVacancyList = new List<CompanyVacancy>();

                Rest rest = new Rest("Company/GetVacancies");
                JArray ja = JArray.Parse(rest.Execute());

                foreach (JObject o in ja)
                {
                    tempCompanyVacancy = new CompanyVacancy();

                    tempCompanyVacancy.setVacancyId(Convert.ToInt32(o["vacancy_Id"]));
                    tempCompanyVacancy.setJobTitle(o["job_title"].ToString());
                    tempCompanyVacancy.setVacancyDescription(o["description"].ToString());
                    tempCompanyVacancy.setPrerequesties(o["preresquites"].ToString());
                    tempCompanyVacancy.setLocation(o["location"].ToString());
                    tempCompanyVacancy.setClosingDate(Convert.ToDateTime(o["closing_date"].ToString()));
                    tempCompanyVacancy.setSalary(Convert.ToInt32(o["salary"]));
                    tempCompanyVacancy.setNoOfVacancies(Convert.ToInt32(o["no_of_vacancies"]));
                    tempCompanyVacancy.setCompanyId(Convert.ToInt32(o["company_id"]));

                    tempCompanyVacancyList.Add(tempCompanyVacancy); // Add vacancies to list

                    StaticJobVacanciesForJobSeeker.setVacancyList(tempCompanyVacancyList);
                }
            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (IndexOutOfRangeException ex)
            {
                throw ex;
            }
            catch (FormatException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }

           
        }

        public void getJobSeekerDetailsForCompany()
        {
            try
            {
                Rest rest = new Rest("Jobseeker/GetForCompany");
                JArray ja = JArray.Parse(rest.Execute());

                RecommendedJobSeekers tempRecJobSk;
                Dictionary<int, RecommendedJobSeekers> tempD = new Dictionary<int, RecommendedJobSeekers>();

                for (int i = 0; i <= ja.Count - 1; i++)
                {
                    tempRecJobSk = new RecommendedJobSeekers();

                    tempRecJobSk.setName(ja[i]["full_name"].ToString());
                    tempRecJobSk.setQualifiedField(ja[i]["qualified_field"].ToString());
                    tempRecJobSk.setInterestedPosition(ja[i]["interested_job"].ToString());
                    tempRecJobSk.setEmail(ja[i]["email"].ToString());
                    tempRecJobSk.setTeleOne(Convert.ToInt32(ja[i]["mobile"]));
                    tempRecJobSk.setTeleTwo(Convert.ToInt32(ja[i]["telephone"]));

                    tempD.Add(i, tempRecJobSk);
                }

                StaticRecommendedJobSeekers.setRecommendedJobSeekers(tempD);
            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (IndexOutOfRangeException ex)
            {
                throw ex;
            }
            catch (FormatException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void getVacancyDataforDatagrid()
        {
            try
            {

            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (IndexOutOfRangeException ex)
            {
                throw ex;
            }
            catch (FormatException ex)
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
