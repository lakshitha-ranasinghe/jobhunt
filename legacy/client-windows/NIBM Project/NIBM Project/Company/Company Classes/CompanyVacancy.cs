using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class CompanyVacancy
    {
        private int vacancyId;
        private String jobTitle;
        private String vacancyDescription;
        private String prerequesties;
        private String location;
        private int noOfVacancies;
        private double salary;
        private DateTime closingDate;
        private int companyId;

        public void setVacancyId(int vacancyId)
        {
            this.vacancyId = vacancyId;
        }
        public void setJobTitle(String jobTitle)
        {
            this.jobTitle = jobTitle;
        }
        public void setVacancyDescription(String vacancyDescription)
        {
            this.vacancyDescription = vacancyDescription;
        }
        public void setPrerequesties(String prerequesties)
        {
            this.prerequesties = prerequesties;
        }
        public void setLocation(String location)
        {
            this.location = location;
        }
        public void setNoOfVacancies(int noOfVacancies)
        {
            this.noOfVacancies = noOfVacancies;
        }
        public void setSalary(double salary)
        {
            this.salary = salary;
        }
        public void setClosingDate(DateTime closingDate)
        {
            this.closingDate = closingDate;
        }
        public void setCompanyId(int companyId)
        {
            this.companyId = companyId;
        }

        //Get
        public int intGetVacanyID()
        {
            return vacancyId;
        }
        public String getJobTitle()
        {
            return jobTitle;
        }
        public String getVacancyDescription()
        {
            return vacancyDescription;
        }
        public String getPrerequesties()
        {
            return prerequesties;
        }
        public String getLocation()
        {
            return location;
        }
        public int getNoOfVacancies()
        {
            return noOfVacancies;
        }
        public double getSalary()
        {
            return salary;
        }
        public DateTime getClosingDate()
        {
            return closingDate;
        }
        public int getCompanyId()
        {
            return companyId;
        }

    }
}
