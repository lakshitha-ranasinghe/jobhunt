using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    class StaticJobVacanciesForJobSeeker
    {
        private static List<CompanyVacancy> vacancyList = new List<CompanyVacancy>();

        public static void setVacancyList(List<CompanyVacancy> paraVacancyList)
        {
            vacancyList = paraVacancyList;
        }
        public static List<CompanyVacancy> getVacancyList()
        {
            return vacancyList;
        }
    }
}
