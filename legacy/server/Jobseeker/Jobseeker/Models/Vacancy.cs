using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MySql.Data.MySqlClient;

namespace Jobseeker.Models
{
    public class Vacancy
    {
        public int Id { get; set; }
        public string JobTitle { get; set; }
        public string Description { get; set; }
        public string Preresquities { get; set; }
        public string Location { get; set; }
        public DateTime ClosingDate { get; set; }
        public int Salary { get; set; }
        public int NoOfVacs { get; set; }
        public int CompanyID { get; set; }

        public int Add(Vacancy vac)
        {
            Database db = new Database();
            db.Connect();

            string query = "insert into company_vacancy(job_title,description,preresquites,location,closing_date,salary,no_of_vacancies,company_id) values(@jt,@des,@pre,@loc,@cd,@sal,@nov,@cid);";
            List<MySqlParameter> vacParams = new List<MySqlParameter>()
            {
                new MySqlParameter("@jt", vac.JobTitle),
                new MySqlParameter("@des", vac.Description),
                new MySqlParameter("@pre", vac.Preresquities),
                new MySqlParameter("@loc", vac.Location),
                new MySqlParameter("@cd", vac.ClosingDate),
                new MySqlParameter("@sal", vac.Salary),
                new MySqlParameter("@nov", vac.NoOfVacs),
                new MySqlParameter("@cid", vac.CompanyID)
            };

            db.ExecuteNQ(query, vacParams);

            return 1;
        }

        public int Update(Vacancy vac)
        {
            Database db = new Database();
            db.Connect();

            string query = "update company_vacancy set job_title=@jt, description=@des, preresquities=@pre,location=@loc,closing_date=@cd,salary=@sal,no_of_vacancies=@nov,company_id=@cid where ";
            List<MySqlParameter> vacParams = new List<MySqlParameter>()
            {
                new MySqlParameter("@id", vac.Id),
                new MySqlParameter("@jt", vac.JobTitle),
                new MySqlParameter("@des", vac.Description),
                new MySqlParameter("pre", vac.Preresquities),
                new MySqlParameter("@pre", vac.Location),
                new MySqlParameter("@cd", vac.ClosingDate),
                new MySqlParameter("@sal", vac.Salary),
                new MySqlParameter("@nov", vac.NoOfVacs),
                new MySqlParameter("@cid", vac.CompanyID)
            };

            db.ExecuteNQ(query, vacParams);

            return 1;
        }
    }
}