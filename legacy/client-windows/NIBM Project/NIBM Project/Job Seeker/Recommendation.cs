using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class Recommendation
    {
        List<CompanyVacancy> mainList;
        Dictionary<Int32, CompanyVacancy> recommendedJobs;
        Dictionary<String, Int32> recommendedJobList;

        public void automatic()
        {
            recommendedJobs = new Dictionary<Int32, CompanyVacancy>();
            recommendedJobList = new Dictionary<String, Int32>();
            String preferedJob = " ";
            String qualifiedField = " ";

            mainList = StaticJobVacanciesForJobSeeker.getVacancyList();
            try
            {
                qualifiedField = StaticJobSeekerData.getJobSeekerPreferences().getQualifiedField();
                preferedJob = StaticJobSeekerData.getJobSeekerPreferences().getinterestedPosition();
            }
            catch (NullReferenceException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }

            foreach (CompanyVacancy vacancy in mainList)
            {
                if (vacancy.getJobTitle().Equals(preferedJob))
                {
                    foreach (KeyValuePair<String, Company> entry in StaticCompanyList.getCompanyDictionary())
                    {
                        if (entry.Value.getId() == vacancy.getCompanyId())
                        {
                            recommendedJobList.Add(vacancy.intGetVacanyID() + " :    " + entry.Value.getCompanyName() + " - " + vacancy.getJobTitle(), vacancy.intGetVacanyID());
                        }
                    }
                    recommendedJobs.Add(vacancy.intGetVacanyID(), vacancy);
                }
            }
        }
        public void manual(String[] preferenceList)
        {
            bool isTypeNotSpecified = false;
            bool isLocationNotSpecified = false;

            recommendedJobs = new Dictionary<Int32, CompanyVacancy>();
            recommendedJobList = new Dictionary<String, Int32>();

            mainList = StaticJobVacanciesForJobSeeker.getVacancyList();
            String qualifiedField = StaticJobSeekerData.getJobSeekerPreferences().getQualifiedField();
            String preferedJob = StaticJobSeekerData.getJobSeekerPreferences().getinterestedPosition();

            String[] salarySet = new String[2];

            char[] seperators = { '-' };
            if (preferenceList[1].Equals("More than 150000"))
            {
                salarySet[0] = "150000";
                salarySet[1] = Int32.MaxValue.ToString();
                Console.WriteLine("More Than");
            }
            else if (preferenceList[1].Equals("Not Specified"))
            {
                salarySet[0] = "0";
                salarySet[1] = Int32.MaxValue.ToString();
                Console.WriteLine("Not Specified");
            }
            else
            {
                salarySet = preferenceList[1].Split(seperators);
                Console.WriteLine("Other");
            }

            if (preferenceList[0].Equals("Not Specified"))
            {
                isTypeNotSpecified = true;
            }
            if (preferenceList[2].Equals("Not Specified"))
            {
                isLocationNotSpecified = true;
            }

            foreach (CompanyVacancy vacancy in mainList)
            {
                if ((vacancy.getJobTitle().Equals(preferenceList[0]) || isTypeNotSpecified) && vacancy.getSalary() >= Convert.ToDouble(salarySet[0].Trim()) && vacancy.getSalary() <= Convert.ToDouble(salarySet[1].Trim()) && (vacancy.getLocation().Equals(preferenceList[2]) || isLocationNotSpecified))
                {
                    foreach (KeyValuePair<String, Company> entry in StaticCompanyList.getCompanyDictionary())
                    {
                        if (entry.Value.getId() == vacancy.getCompanyId())
                        {
                            recommendedJobList.Add(vacancy.intGetVacanyID() + " :    " + entry.Value.getCompanyName() + " - " + vacancy.getJobTitle(), vacancy.intGetVacanyID());
                        }
                    }
                    recommendedJobs.Add(vacancy.intGetVacanyID(), vacancy);
                }
            }
        }
        public Dictionary<Int32, CompanyVacancy> getData()
        {
            return recommendedJobs;
        }
        public Dictionary<String, Int32> getList()
        {
            return recommendedJobList;
        }
    }
}
