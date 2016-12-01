using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class StaticCompanyVacancyList
    {
        private static Dictionary<Int32, CompanyVacancy> companyVacancyDataList = new Dictionary<Int32, CompanyVacancy>();
        private static List<Int32> companyVacancyList = new List<Int32>();

        public static void setCompanyVacancyDictionary(Dictionary<Int32, CompanyVacancy> paraCompanyVacancyDataList)
        {
            companyVacancyDataList = paraCompanyVacancyDataList;
        }

        public static void setCompanyVacancyList(List<Int32> paraCompanyVacancyList)
        {
            companyVacancyList = paraCompanyVacancyList;
        }

        public static List<Int32> getCompanyVacancyList()
        {
            return companyVacancyList;
        }

        public static Dictionary<Int32, CompanyVacancy> getCompanyVacancyDictionary()
        {
            return companyVacancyDataList;
        }

        public static CompanyVacancy getCompanyVacancyFromList(int vacancyId)
        {
            return companyVacancyDataList[vacancyId];
        }

    }
}
