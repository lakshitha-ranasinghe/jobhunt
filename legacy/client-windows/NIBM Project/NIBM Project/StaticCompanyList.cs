using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class StaticCompanyList
    {
        private static Dictionary<String, Company> companyDataList = new Dictionary<String, Company>();
        private static List<String> companyList = new List<String>();

        public static void setDictionary(Dictionary<String, Company> paraCompanyDataList)
        {
            companyDataList = paraCompanyDataList;
        }

        public static void setList(List<String> paraCompanyList)
        {
            companyList = paraCompanyList;
        }

        public static List<String> getCompanyList()
        {
            return companyList;
        }

        public static Dictionary<String, Company> getCompanyDictionary()
        {
            return companyDataList;
        }

        public static Company getCompanyFromList(String companyName)
        {
            return companyDataList[companyName];
        }

    }
}
