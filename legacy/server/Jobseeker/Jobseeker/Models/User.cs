using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data;
using System.Data.SqlClient;
using System.Web.Http;
using System.Net;
using MySql.Data.MySqlClient;

namespace Jobseeker.Models
{

    public class User
    {
        public int Id { get; set; }
        public string fName { get; set; }
        public string type { get; set; }
        public string username { get; set; }
        public string password { get; set; }


        //Returns all the users
        public DataTable GetAll()
        {
            Database db = new Database();
            db.Connect();

            DataTable dt = db.ExecuteQ("Select signin_ID,name,user_name,type from jssign_up");

            return dt;
        }

        //Returns the user with the given ID
        public DataTable GetUser(int id)
        {
            Database db = new Database();
            db.Connect();

            DataTable dt = db.ExecuteQ("Select signin_ID,name,user_name,type from jssign_up where signin_ID=" + id);

            return dt;
        }

        //Adds a new User
        public DataTable AddUser(User user)
        {

            Database db = new Database();
            db.Connect();

            int lastID;

            try {

                 lastID = (int)db.ExecuteQ("select signin_ID from jssign_up order by signin_ID desc limit 1;").Rows[0]["signin_ID"];
            }
            catch(IndexOutOfRangeException e)
            {
                lastID = 0;
            }

            string query = "insert into jssign_up(signin_ID,name, type,user_name,password) values(@id,@name,@type,@uname,@pw);";

            List<MySqlParameter> paramList = new List<MySqlParameter>()
            {
                new MySqlParameter("@id", lastID + 1),
                new MySqlParameter("@name", user.fName),
                new MySqlParameter("@type", user.type),
                new MySqlParameter("@uname", user.username),
                new MySqlParameter("@pw", user.password)
            };

            db.ExecuteNQ(query, paramList);
        
            return GetUser(lastID+1);
        }

        public DataTable UpdateUser(User user)
        {

            Database db = new Database();
            db.Connect();

            string query = "update jssign_up SET name=@fn, type=@type, user_name=@un, password=@pw where signin_ID=@id;";

            List<MySqlParameter> paramList = new List<MySqlParameter>()
            {
                new MySqlParameter("@id", user.Id),
                new MySqlParameter("@type", user.type),
                new MySqlParameter("@fn", user.fName),
                new MySqlParameter("@un", user.username),
                new MySqlParameter("@pw", user.password)
            };

            db.ExecuteNQ(query, paramList);

            return GetUser(user.Id);
        }

        public DataTable Delete(int id)
        {
            string query = "delete from jssign_up where signin_ID=@id;";
            Database db = new Database();
            db.Connect();
            List<MySqlParameter> paramList = new List<MySqlParameter>()
            {
                new MySqlParameter("@id", id)
            };

            db.ExecuteNQ(query, paramList);

            return GetAll();
        }

        public DataTable Login(Login login)
        {
            Database db = new Database();
            db.Connect();
            return db.ExecuteQ("select signin_ID,name,user_name,type from jssign_up where user_name='" + login.username + "' and password='" + login.password+"'");
        }

        public bool isProfiled(int id)
        {
            Database db = new Database();
            db.Connect();
            DataTable dt = db.ExecuteQ("select * from jspersonal_data where ID=" + id);

            bool value = false;

            bool js = false;
            bool cmp = false;

            if (dt.Rows.Count == 1)
                js = true;
            else
                js = false;

            dt.Clear();
            dt = db.ExecuteQ("select * from company_register where company_id=" + id);

            if (dt.Rows.Count == 1)
                cmp = true;
            else
                cmp = false;

            value = js || cmp;

            return value;
            
        }

    }
}