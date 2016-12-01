using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using Jobseeker.Models;
using System.Data;

namespace Jobseeker.Controllers
{
    public class CompanyController : ApiController
    {
        [ActionName("Register")]
        public int RegisterCompany(Company com)
        {
            return new Company().AddCompany(com);
        }

        [ActionName("Update")]
        public int UpdateCompany(Company com)
        {
            return new Company().UpdateCompany(com);
        }

        [ActionName("AddVacancy")]
        public int AddVac(Vacancy vac)
        {
            return new Vacancy().Add(vac);
        }

        [ActionName("UpdateVacancy")]
        public int UpdateVac(Vacancy vac)
        {
            return new Vacancy().Update(vac);
        }

        [ActionName("GetCompany")]
        public DataTable Get(int id)
        {
            return new Company().GetCompany(id);
        }

        [ActionName("GetCompanies")]
        public DataTable GetAll()
        {
            return new Company().GetCompanies();
        }

        [ActionName ("GetVacancies")]
        public DataTable GetVacs()
        {
            return new Company().GetVacs();
        }

        [HttpGet]
        [ActionName ("DeleteVacancy")]
        public int DeleteVac(int id)
        {
            return new Company().DeleteVac(id);
        }

        [ActionName("GetVacancies")]
        public DataTable GetVacs(int id)
        {
            return new Company().GetVacs(id);
        }

        public int Delete(int id)
        {
            return new Company().DeleteCompany(id);
        }
    }
}
