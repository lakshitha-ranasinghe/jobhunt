using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class StaticCompanyVacancy
    {
        private static CompanyVacancy companyVacancy;

        public static void setCompanyVacancy(CompanyVacancy companyVacancy) 
        {
            StaticCompanyVacancy.companyVacancy = companyVacancy;
        }

        public static CompanyVacancy getCompanyVacancy()
        {
            return companyVacancy;
        }

    }
}
