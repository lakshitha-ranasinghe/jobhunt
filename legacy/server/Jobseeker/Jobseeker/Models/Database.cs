using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Data;
using System.Configuration;
using MySql.Data.MySqlClient;
namespace Jobseeker.Models
{
    public class Database
    {
        string connectionString = "Data Source=mysql2.gear.host;Database=jobhunt; User ID=jobhunt;Password=Qv9k0u!u#d9f; Allow User Variables=True";
        MySqlConnection conn;

        public static string AUTHKEY = "da39a3ee5e6b4b0d3";

        public void Connect()
        {
            conn = new MySqlConnection(connectionString);
        }

        public int ExecuteNQ(string query, List<MySqlParameter> param)
        {
            int result = 0;
            try
            {
                conn.Open();
                MySqlCommand comm = new MySqlCommand(query, conn);
                
                foreach(MySqlParameter p in param)
                {
                    comm.Parameters.Add(p);
                }

                result = comm.ExecuteNonQuery();
                conn.Close();
            }
            catch (MySqlException e)
            {
                string err = e.StackTrace;
            }
            finally
            {
                conn.Close();
            }

            return result;
        }

        public DataTable ExecuteQ(string query)
        {
            DataTable dt = new DataTable();
            try
            {
                conn.Open();
                MySqlDataAdapter da = new MySqlDataAdapter(query, conn);
                da.Fill(dt);

                
                conn.Close();
            }
            catch (MySqlException e)
            {
                string err = e.StackTrace;
            }
            finally
            {
                conn.Close();
            }

            return dt;
        }

        public int ExecuteS(string query)
        {
            conn.Open();
            MySqlCommand comm = new MySqlCommand(query, conn);
            return (int)comm.ExecuteScalar();
        }
    }
}