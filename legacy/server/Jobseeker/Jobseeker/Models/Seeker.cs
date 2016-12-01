using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using MySql.Data.MySqlClient;
using System.Data;
using System.Reflection;

namespace Jobseeker.Models
{
    public class Seeker
    {
        public int personalID { get; set; }
        public string name { get; set; }
        public string address { get; set; }
        public string title { get; set; }
        public DateTime birthDay { get; set; }
        public long mobileTel { get; set; }
        public long homeTel { get; set; }
        public string email { get; set; }


        public string qualifiedField { get; set; }
        public string interestedPosition { get; set; }
        public string lastJob { get; set; }

        public int OLYear { get; set; }
        public string OLSchool { get; set; }
        public string[] OLSubjects { get; set; }
        public string[] OLResults { get; set; }

        public int ALYear { get; set; }
        public string ALSchool { get; set; }
        public string[] ALSubjects { get; set; }
        public string[] ALResults { get; set; }

        public string[] institutes { get; set; }
        public string[] courses { get; set; }
        public int[] years { get; set; }
        public double[] GPA { get; set; }


        public int expYears { get; set; }
        public string firstDesignation { get; set; }
        public string firstCompany { get; set; }
        public DateTime firstStartDate { get; set; }
        public DateTime firstEndDate { get; set; }

        public string secondDesignation { get; set; }
        public string secondCompany { get; set; }
        public DateTime secondStartDate { get; set; }
        public DateTime secondEndDate { get; set; }

        public string otherFirstTitle { get; set; }
        public int otherFirstYear { get; set; }
        public string otherFirstDetails { get; set; }

        public string otherSecondTitle { get; set; }
        public int otherSecondYear { get; set; }
        public string otherSecondDetails { get; set; }



        public void AddJobseeker(Seeker js)
        {

            PropertyInfo[] properties = js.GetType().GetProperties();

            foreach(PropertyInfo prop in properties)
            {
                object value = prop.GetValue(js);

                if(value == null)
                {
                    value = "";
                }
            }

            Database db = new Database();
            db.Connect();

            string query = "insert into jspersonal_data(ID,title,full_name,address,date_of_birth,home,mobile,email) values(@id,@title,@name,@address,@dob,@tp,@mobile,@email);";

            List<MySqlParameter> personalParam = new List<MySqlParameter>()
            {
                new MySqlParameter("@id", js.personalID),
                new MySqlParameter("@title",js.title),
                new MySqlParameter("@name", js.name),
                new MySqlParameter("@address", js.address),
                new MySqlParameter("@dob", js.birthDay),
                new MySqlParameter("@tp", js.homeTel),
                new MySqlParameter("@mobile", js.mobileTel),
                new MySqlParameter("@email", js.email)
            };
            db.ExecuteNQ(query, personalParam);


            query = "insert into jspreferences(ID,qualified_field,interested_job,last_job) values(@id, @qf,@ij,@lj);";


            List<MySqlParameter> prefParam = new List<MySqlParameter>()
            {
                new MySqlParameter("@id", js.personalID),
                new MySqlParameter("@qf", js.qualifiedField),
                new MySqlParameter("@ij", js.interestedPosition),
                new MySqlParameter("@lj", js.lastJob)
            };

            db.ExecuteNQ(query, prefParam);

            //OLevel 
            for (int i = 0; i <= js.OLSubjects.Count()-1; i++)
            {
                query = "insert into jsordinary_level(subject,result,ID) values(@sub,@res,@id);";


                List<MySqlParameter> OLParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@sub", js.OLSubjects[i]),
                    new MySqlParameter("@res", js.OLResults[i]),
                    new MySqlParameter("@id", js.personalID)
                };

                db.ExecuteNQ(query, OLParam);
            }
            
            //ALevel
            for (int i = 0; i <= js.ALSubjects.Count()-1; i++)
            {
                query = "insert into jsadvanced_level(al_result,al_subject,ID) values(@res,@sub,@id);";


                List<MySqlParameter> ALParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@res", js.ALResults[i]),
                    new MySqlParameter("@sub", js.ALSubjects[i]),
                    new MySqlParameter("@id", js.personalID)
                };

                db.ExecuteNQ(query, ALParam);
            }

            //HEdu
            for (int i = 0; i <= js.institutes.Count()-1; i++)
            {

                query = "insert into jshigher_education(ID,institute,course,completion_year,grade) values(@id,@ins,@course,@cy,@grade);";

                List<MySqlParameter> HEParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@id", js.personalID),
                    new MySqlParameter("@ins", js.institutes[i]),
                    new MySqlParameter("@course", js.courses[i]),
                    new MySqlParameter("@cy", js.years[i]),
                    new MySqlParameter("@grade", js.GPA[i])
                };

                db.ExecuteNQ(query, HEParam);
                
            }

            //school education

            query = "insert into school_education(OLSchool,ALSchool,OLYear,ALYear,ID) values(@ols,@als,@oly,@aly,@id);";

            List<MySqlParameter> SchParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@ols", js.OLSchool),
                    new MySqlParameter("@als", js.ALSchool),
                    new MySqlParameter("@oly", js.OLYear),
                    new MySqlParameter("@aly", js.OLYear),
                    new MySqlParameter("@id",  js.personalID)
                };

            db.ExecuteNQ(query, SchParam);

            //First designation
            query = "insert into jsprofessional(ID,companyName,designation,duration_from,duration_to) values(@id,@cname,@des,@from,@to)";


            List<MySqlParameter> FirstQParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@id", js.personalID),
                    new MySqlParameter("@cname", js.firstCompany),
                    new MySqlParameter("@des", js.firstDesignation),
                    new MySqlParameter("@from", js.firstStartDate),
                    new MySqlParameter("@to", js.firstEndDate)
                };

            db.ExecuteNQ(query, FirstQParam);

            //Second designation
            query = "insert into jsprofessional(ID,companyName,designation,duration_from,duration_to) values(@id,@cname,@des,@from,@to)";


            List<MySqlParameter> SecondQParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@id", js.personalID),
                    new MySqlParameter("@cname", js.secondCompany),
                    new MySqlParameter("@des", js.secondDesignation),
                    new MySqlParameter("@from", js.secondStartDate),
                    new MySqlParameter("@to", js.secondEndDate)
                };

            db.ExecuteNQ(query, SecondQParam);

            //Experiance Years
            query = "insert into jsprofessional_experience(ID,years) values(@id, @years);";

            List<MySqlParameter> expYearsParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@id", js.personalID),
                    new MySqlParameter("@years", js.expYears)
                };

            db.ExecuteNQ(query, expYearsParam);


            //Other first Qaulifications
            query = "insert into jsprofessional_other(ID,title,other_year,Other_details) values(@id,@title,@oy,@od)";


            List<MySqlParameter> otherFQParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@id", js.personalID),
                    new MySqlParameter("@title", js.otherFirstTitle),
                    new MySqlParameter("@oy", js.otherFirstYear),
                    new MySqlParameter("@od", js.otherFirstDetails)
                };

            db.ExecuteNQ(query, otherFQParam);

        //Other second Qaulifications
        query = "insert into jsprofessional_other(ID,title,other_year,Other_details) values(@id,@title,@oy,@od)";


            List<MySqlParameter> otherSQParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@id", js.personalID),
                    new MySqlParameter("@title", js.otherSecondTitle),
                    new MySqlParameter("@oy", js.otherSecondYear),
                    new MySqlParameter("@od", js.otherSecondDetails)
                };

        db.ExecuteNQ(query, otherSQParam);

        }

        public DataTable GetAll()
        {
            Database db = new Database();
            db.Connect();

            DataTable dt = db.ExecuteQ("Select * from singleRowdata");

            return dt;
        }

        public Seeker GetJobSeeker(int id)
        {
            Seeker seeker = new Seeker();

            Database db = new Database();
            db.Connect();

            DataTable dt = db.ExecuteQ("Select * from singlerowdata where ID=" + id);

            DataRow row = dt.Rows[0];

            seeker.personalID   = (int)row["ID"];
            seeker.name = row["full_name"].ToString();
            seeker.title = row["title"].ToString();
            seeker.address = row["address"].ToString();
            seeker.birthDay = Convert.ToDateTime(row["date_of_birth"]);
            seeker.homeTel = Convert.ToInt64(row["telephone"]);
            seeker.mobileTel = Convert.ToInt64(row["mobile"]);
            seeker.email = row["email"].ToString();
            seeker.qualifiedField = row["qualified_field"].ToString();
            seeker.lastJob = row["last_job"].ToString();
            seeker.interestedPosition = row["interested_job"].ToString();
            seeker.expYears = (int)row["years"];
            seeker.OLSchool = row["OLSchool"].ToString();
            seeker.ALSchool = row["ALSchool"].ToString();
            seeker.OLYear = (int)row["OLYear"];
            seeker.ALYear = (int)row["ALYear"];
            //seeker.OLSubjects = new string[] {"Sinhala" , "English" , "Mathematics"};

            dt.Clear();
            //get OL results
            dt = db.ExecuteQ("select * from jsordinary_level where ID=" + id);

            seeker.OLSubjects = new string[dt.Rows.Count];
            seeker.OLResults = new string[dt.Rows.Count];

            for (int i = 0; i <= dt.Rows.Count-1; i++ )
            {
                seeker.OLSubjects[i] = dt.Rows[i]["subject"].ToString();
                seeker.OLResults[i] = dt.Rows[i]["result"].ToString();
            }

            dt.Clear();
            //get AL results

            dt = db.ExecuteQ("select * from jsadvanced_level where ID=" + id);

            seeker.ALSubjects = new string[dt.Rows.Count];
            seeker.ALResults = new string[dt.Rows.Count];

            for (int i = 0; i <= dt.Rows.Count - 1; i++)
            {
                seeker.ALSubjects[i] = dt.Rows[i]["al_subject"].ToString();
                seeker.ALResults[i] = dt.Rows[i]["al_result"].ToString();
            }

            dt.Clear();

            //get institutes

            dt = db.ExecuteQ("select * from jshigher_education where ID=" + id);

            seeker.institutes = new string[dt.Rows.Count];
            seeker.courses = new string[dt.Rows.Count];
            seeker.years = new int[dt.Rows.Count];
            seeker.GPA = new double[dt.Rows.Count];

            for (int i = 0; i <= dt.Rows.Count - 1; i++)
            {
                seeker.institutes[i] = dt.Rows[i]["institute"].ToString();
                seeker.courses[i] = dt.Rows[i]["course"].ToString();
                seeker.years[i] = (int)dt.Rows[i]["completion_year"];
                seeker.GPA[i] = Convert.ToDouble(dt.Rows[i]["grade"]);
            }

            dt.Clear();

            //get designations

            dt = db.ExecuteQ("select * from jsprofessional where ID=" + id);

            seeker.firstDesignation = dt.Rows[0]["designation"].ToString();
            seeker.firstCompany = dt.Rows[0]["companyName"].ToString();
            seeker.firstStartDate = Convert.ToDateTime(dt.Rows[0]["duration_from"]);
            seeker.firstEndDate = Convert.ToDateTime(dt.Rows[0]["duration_to"]);

            seeker.secondDesignation= dt.Rows[1]["designation"].ToString();
            seeker.secondCompany= dt.Rows[1]["companyName"].ToString();
            seeker.secondStartDate = Convert.ToDateTime(dt.Rows[1]["duration_from"]);
            seeker.secondEndDate = Convert.ToDateTime(dt.Rows[1]["duration_to"]);

            dt.Clear();

            //get other designations

            dt = db.ExecuteQ("select * from jsprofessional_other where ID=" + id);

            seeker.otherFirstTitle = dt.Rows[0]["title"].ToString();
            seeker.otherFirstDetails = dt.Rows[0]["Other_details"].ToString();
            seeker.otherFirstYear = (int)dt.Rows[0]["other_year"];

            seeker.otherSecondTitle = dt.Rows[1]["title"].ToString();
            seeker.otherSecondDetails = dt.Rows[1]["Other_details"].ToString();
            seeker.otherSecondYear = (int)dt.Rows[1]["other_year"];

            return seeker;
        }

        public List<Seeker> UpdateJobSeeker(Seeker js)
        {
            List<Seeker> seekerList = new List<Seeker>();

            PropertyInfo[] properties = js.GetType().GetProperties();

            foreach (PropertyInfo prop in properties)
            {
                object value = prop.GetValue(js);

                if (value == null)
                {
                    value = "";
                }
            }

            Database db = new Database();
            db.Connect();

            string query = "update jspersonal_data set title=@title,full_name=@name,address=@address,date_of_birth=@dob,home=@tp,mobile=@mobile,email=@email where ID=@id";

            List<MySqlParameter> personalParam = new List<MySqlParameter>()
            {
                new MySqlParameter("@id", js.personalID),
                new MySqlParameter("@title",js.title),
                new MySqlParameter("@name", js.name),
                new MySqlParameter("@address", js.address),
                new MySqlParameter("@dob", js.birthDay),
                new MySqlParameter("@tp", js.homeTel),
                new MySqlParameter("@mobile", js.mobileTel),
                new MySqlParameter("@email", js.email)
            };
            db.ExecuteNQ(query, personalParam);


            query = "update jspreferences set qualified_field=@qf,interested_job=@ij,last_job=@lj where ID=@id";


            List<MySqlParameter> prefParam = new List<MySqlParameter>()
            {
                new MySqlParameter("@id", js.personalID),
                new MySqlParameter("@qf", js.qualifiedField),
                new MySqlParameter("@ij", js.interestedPosition),
                new MySqlParameter("@lj", js.lastJob)
            };

            db.ExecuteNQ(query, prefParam);

            //OLevel 
            for (int i = 0; i <= js.OLSubjects.Count() - 1; i++)
            {
                query = "set @oid = (select OL_ID from jsordinary_level where ID=@id limit "+i+",1);update jsordinary_level set subject=@sub, result=@res where OL_ID = @oid;";


                List<MySqlParameter> OLParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@sub", js.OLSubjects[i]),
                    new MySqlParameter("@res", js.OLResults[i]),
                    new MySqlParameter("@id", js.personalID)
                };

                db.ExecuteNQ(query, OLParam);
            }

            //ALevel
            for (int i = 0; i <= js.ALSubjects.Count() - 1; i++)
            {
                query = "set @aid = (select AL_ID from jsadvanced_level where ID=@id limit " + i + ",1);update jsadvanced_level set al_subject=@sub, al_result=@res where AL_ID = @aid;";


                List<MySqlParameter> ALParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@res", js.ALResults[i]),
                    new MySqlParameter("@sub", js.ALSubjects[i]),
                    new MySqlParameter("@id", js.personalID)
                };

                db.ExecuteNQ(query, ALParam);
            }

            //HEdu
            for (int i = 0; i <= js.institutes.Count() - 1; i++)
            {

                if (js.institutes[i] != null && js.courses[i] != null)
                {
                    query = "set @hid = (select HID from jshigher_education where ID=@id limit " + i + ",1); update jshigher_education set institute=@ins, course=@course, completion_year=@cy,grade=@grade where HID=@hid";

                    List<MySqlParameter> HEParam = new List<MySqlParameter>()
                    {
                        new MySqlParameter("@id", js.personalID),
                        new MySqlParameter("@ins", js.institutes[i]),
                        new MySqlParameter("@course", js.courses[i]),
                        new MySqlParameter("@cy", js.years[i]),
                        new MySqlParameter("@grade", js.GPA[i])
                    };

                    db.ExecuteNQ(query, HEParam);
                }
            }

            //school education

            query = "update school_education set OLSchool=@ols,ALSchool=@als,OLYear=@oly,ALYear=@aly where ID=@id;";

            List<MySqlParameter> SchParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@ols", js.OLSchool),
                    new MySqlParameter("@als", js.ALSchool),
                    new MySqlParameter("@oly", js.OLYear),
                    new MySqlParameter("@aly", js.ALYear),
                    new MySqlParameter("@id",  js.personalID)
                };

            db.ExecuteNQ(query, SchParam);

            //First designation
            query = "update jsprofessional set companyName=@cname,designation=@des,duration_from=@from,duration_to=@to where ID=@id";


            List<MySqlParameter> FirstQParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@id", js.personalID),
                    new MySqlParameter("@cname", js.firstCompany),
                    new MySqlParameter("@des", js.firstDesignation),
                    new MySqlParameter("@from", js.firstStartDate),
                    new MySqlParameter("@to", js.firstEndDate)
                };

            db.ExecuteNQ(query, FirstQParam);

            //Second designation
            query = "update jsprofessional set companyName=@cname,designation=@des,duration_from=@from,duration_to=@to where ID=@id;";


            List<MySqlParameter> SecondQParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@id", js.personalID),
                    new MySqlParameter("@cname", js.secondCompany),
                    new MySqlParameter("@des", js.secondDesignation),
                    new MySqlParameter("@from", js.secondStartDate),
                    new MySqlParameter("@to", js.secondEndDate)
                };

            db.ExecuteNQ(query, SecondQParam);

            //Experiance Years
            query = "update jsprofessional_experience set years=@years where ID=@id";

            List<MySqlParameter> expYearsParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@id", js.personalID),
                    new MySqlParameter("@years", js.expYears)
                };

            db.ExecuteNQ(query, expYearsParam);


            //Other first Qaulifications
            query = "set @otherid = (select OtherID from jsprofessional_other where ID=@id limit " + 0 + ",1); update jsprofessional_other set title=@title,other_year=@oy,Other_details=@od where OtherID=@otherid;";


            List<MySqlParameter> otherFQParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@id", js.personalID),
                    new MySqlParameter("@title", js.otherFirstTitle),
                    new MySqlParameter("@oy", js.otherFirstYear),
                    new MySqlParameter("@od", js.otherFirstDetails)
                };

            db.ExecuteNQ(query, otherFQParam);

            //Other first Qaulifications
            query = "set @otherid = (select OtherID from jsprofessional_other where ID=@id limit " + 1 + ",1);update jsprofessional_other set title=@title,other_year=@oy,Other_details=@od where OtherID=@otherid;";


            List<MySqlParameter> otherSQParam = new List<MySqlParameter>()
                {
                    new MySqlParameter("@id", js.personalID),
                    new MySqlParameter("@title", js.otherSecondTitle),
                    new MySqlParameter("@oy", js.otherSecondYear),
                    new MySqlParameter("@od", js.otherSecondDetails)
                };

            db.ExecuteNQ(query, otherSQParam);
            return seekerList;
        }


        public List<Seeker> Delete(int id)
        {
            List<Seeker> seekerList = new List<Seeker>();

            Database db = new Database();
            db.Connect();


            List<MySqlParameter> delparam = new List<MySqlParameter>();
            delparam.Add(new MySqlParameter("@id", id));

            db.ExecuteNQ("delete from jspersonal_data where ID=@id;delete from jsadvanced_level where ID=@id;delete from jshigher_education where ID=@id;delete from jsordinary_level where ID=@id;delete from jspreferences where ID=@id;delete from jsprofessional where ID=@id;delete from jsprofessional_experience where ID=@id;delete from jsprofessional_other where ID=@id;delete from school_education where ID=@id;",delparam);

            new User().Delete(id);

            return seekerList;
        }

        public DataTable GetForCompany()
        {
            Database db = new Database();
            db.Connect();

            return db.ExecuteQ("select full_name, qualified_field,interested_job,email,telephone,mobile from singlerowdata");
        }

    }
}