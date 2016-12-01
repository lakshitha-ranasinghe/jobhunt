using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Net.Http;
using System.IO;
using System.Windows;

namespace NIBM_Project
{
    class Rest
    {
        private string serviceUrl = "http://jobhunt.ml/api";
        private string ApiController;
        private string method = "GET";
        private Dictionary<string, string> data;

        public Rest(string url, string controller)
        {
            this.serviceUrl = url;
            this.ApiController = controller;
        }

        public Rest(string controller)
        {
            this.ApiController = controller;

        }

        public void SetMethod(string m)
        {
            method = m;
        }

        public void setFormData(Dictionary<string, string> d)
        {
            this.data = d;
        }

        public string Execute()
        {
            string responseStr = "";
            try
            {
                HttpWebRequest request = (HttpWebRequest)HttpWebRequest.Create(serviceUrl + "/" + ApiController);
                request.Method = method;
                request.ContentType = "application/x-www-form-urlencoded";


                if ((method != "GET") && (method != "DELETE"))
                {
                    StringBuilder poststring = new StringBuilder("");

                    foreach (KeyValuePair<string, string> keyvalue in data)
                    {
                        poststring.Append(keyvalue.Key + "=" + keyvalue.Value);
                        poststring.Append("&");
                    }

                    using (StreamWriter sw = new StreamWriter(request.GetRequestStream()))
                    {
                        sw.Write(poststring);
                    }

                }

                HttpWebResponse httpWebResponse = request.GetResponse() as HttpWebResponse;
                using (StreamReader sr = new StreamReader(httpWebResponse.GetResponseStream()))
                {
                    responseStr = sr.ReadToEnd();
                }
            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }
            return responseStr;
        }

    }
}
