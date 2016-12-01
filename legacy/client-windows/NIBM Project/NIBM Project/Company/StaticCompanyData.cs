using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class StaticCompanyData
    {
        public static Company companyData;

        public static void setCompany(Company company) 
        {
            StaticCompanyData.companyData = company;
        }

        public static Company getCompany() 
        {
            return companyData;
        }

    }
}
