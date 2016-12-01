using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using MySql.Data.MySqlClient;
using System.Data;

namespace Jobseeker.Models
{
    public class Company
    {
        public int Id { get; set; }
        public string name { get; set; }
        public string type { get; set; }
        public int telHome { get; set; }
        public int telMobile { get; set; }
        public string email { get; set; }
        public string address { get; set; }
        public string  website { get; set; }
        public string jobtype { get; set; }
        public string description { get; set; }

        public int AddCompany(Company com)
        {
            Database db = new Database();
            db.Connect();

            string query = "insert into company_register(company_id,name,company_type,telephone_home,telephone_mobile,email,address,website,job_type,description) value(@id,@name,@type,@tph,@tpm,@email,@address,@website,@jtype,@desc);";


            List<MySqlParameter> comParam = new List<MySqlParameter>()
            {
                new MySqlParameter("@id",com.Id),
                new MySqlParameter("@name",com.name),
                new MySqlParameter("@type", com.type),
                new MySqlParameter("@tph", com.telHome),
                new MySqlParameter("@tpm", com.telMobile),
                new MySqlParameter("@email",com.email),
                new MySqlParameter("@address", com.address),
                new MySqlParameter("@website", com.website),
                new MySqlParameter("@jtype", com.jobtype),
                new MySqlParameter("@desc", com.description)
            };

            db.ExecuteNQ(query, comParam);

            return 0;
        }

        public int UpdateCompany(Company com)
        {
            Database db = new Database();
            db.Connect();

            string query = "update company_register set name=@name, company_type=@type, telephone_home=@tph, telephone_mobile=@tpm, email=@email, address=@address, website=@website, job_type=@jtype, description=@desc where company_id=@id;";


            List<MySqlParameter> comParam = new List<MySqlParameter>()
            {
                new MySqlParameter("@id",com.Id),
                new MySqlParameter("@name",com.name),
                new MySqlParameter("@type", com.type),
                new MySqlParameter("@tph", com.telHome),
                new MySqlParameter("@tpm", com.telMobile),
                new MySqlParameter("@email",com.email),
                new MySqlParameter("@address", com.address),
                new MySqlParameter("@website", com.website),
                new MySqlParameter("@jtype", com.jobtype),
                new MySqlParameter("@desc", com.description)
            };

            db.ExecuteNQ(query, comParam);
            return 0;
        }

        public DataTable GetCompany(int id)
        {
            Database db = new Database();
            db.Connect();

            DataTable dt = db.ExecuteQ("select * from company_register where company_id=" + id);

            return dt;
        }

        public DataTable GetCompanies()
        {
            Database db = new Database();
            db.Connect();

            DataTable dt = db.ExecuteQ("select * from company_register");

            return dt;
        }

        public DataTable GetVacs()
        {
            Database db = new Database();
            db.Connect();

            return db.ExecuteQ("select * from company_vacancy");
        }

        public DataTable GetVacs(int id)
        {
            Database db = new Database();
            db.Connect();

            return db.ExecuteQ("select * from company_vacancy where company_id="+id);
        }

        public int DeleteCompany(int id)
        {
            Database db = new Database();
            db.Connect();

            List<MySqlParameter> delParam = new List<MySqlParameter>()
            {
                new MySqlParameter("@id", id)
            };

            new User().Delete(id);

            return db.ExecuteNQ("delete from company_register where company_id = " + id, delParam);
        }


        public int DeleteVac(int id)
        {
            Database db = new Database();
            db.Connect();
            List<MySqlParameter> delParam = new List<MySqlParameter>()
            {
                new MySqlParameter("@id",id)
            };

            return db.ExecuteNQ("delete from company_vacancy where vacancy_Id=" + id, delParam);
        }
    }
}