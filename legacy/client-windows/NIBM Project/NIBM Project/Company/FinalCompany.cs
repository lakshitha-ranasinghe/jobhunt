using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net;

namespace NIBM_Project
{
    public class FinalCompany
    {
        private Company companyData;
        private CompanyVacancy companyVacancy;
        private int id;
        bool oldUser;

        public void receiveCompanyData(int id, Company companyData)
        {
            this.id = id;
            this.companyData = companyData;
        }
        public void receiveCompanyVacancyData(CompanyVacancy companyVacancy)
        {
            this.companyVacancy = companyVacancy;
        }
        public void executeCompanyData(bool oldUser)
        {
            this.oldUser = oldUser;

            int companyId = id;

            String name = companyData.getCompanyName();
            String type = companyData.getCompanyType();
            long telehome = companyData.getFirstTelephone();
            long telemobile = companyData.getSecondTelephone();
            String address = companyData.getAddress();
            String website = companyData.getWebsite();
            String offeredjobtypes = companyData.getOfferedJobTypes();
            String description = companyData.getDescription();
            String email = companyData.getEmail();

            HandleDatabase handleDatabase1 = new HandleDatabase();
            if (!oldUser)
            {
                try
                {
                    Rest rest = new Rest("Company/Register");
                    rest.SetMethod("POST");
                    Dictionary<string, string> postData = new Dictionary<string, string>();
                    postData.Add("Id", companyId.ToString());
                    postData.Add("name", name);
                    postData.Add("type", type);
                    postData.Add("telHome", telehome.ToString());
                    postData.Add("telMobile", telemobile.ToString());
                    postData.Add("email", email);
                    postData.Add("address", address);
                    postData.Add("website", website);
                    postData.Add("jobtype", offeredjobtypes);
                    postData.Add("description", description);

                    rest.setFormData(postData);

                    rest.Execute();
                }
                catch (WebException ex)
                {
                    throw ex;
                }
                catch (Exception ex)
                {
                    throw ex;
                }

                // handleDatabase1.execute("insert into company_register(company_id,name,company_type,address,telephone_home,telephone_mobile,email,website,job_type,description) values('" + companyId + "', '" + name + "', '" + type + "', '" + address + "', '" + telehome + "', '" + telemobile + "', '" + email + "', '" + website + "', '" + offeredjobtypes + "', '" + description + "')");
            }
            else
            {
                try
                {
                    Rest rest = new Rest("Company/Update");
                    rest.SetMethod("POST");
                    Dictionary<string, string> postData = new Dictionary<string, string>();
                    postData.Add("Id", companyId.ToString());
                    postData.Add("name", name);
                    postData.Add("type", type);
                    postData.Add("telHome", telehome.ToString());
                    postData.Add("telMobile", telemobile.ToString());
                    postData.Add("email", email);
                    postData.Add("address", address);
                    postData.Add("website", website);
                    postData.Add("jobtype", offeredjobtypes);
                    postData.Add("description", description);

                    rest.setFormData(postData);

                    rest.Execute();
                }
                catch (WebException ex)
                {
                    throw ex;
                }
                catch (Exception ex)
                {
                    throw ex;
                }

                //update query
                //handleDatabase1.execute("update company_register set name='" + name + "' ,company_type='" + type + "' ,address='" + address + "' ,telephone_home='" + telehome + "' ,telephone_mobile='" + telemobile + "' ,email='" + email + "' ,website='" + website + "' ,job_type='" + offeredjobtypes + "' ,description='" + description + "' where company_id='" + companyId + "' ");
            }
        }

        public void executeVacancy()
        {                
            int companyId = StaticCompanyID.getID();

            String title = companyVacancy.getJobTitle();
            String description = companyVacancy.getVacancyDescription();
            String preresquites = companyVacancy.getPrerequesties();
            String location = companyVacancy.getLocation();
            int noOfVacancies = companyVacancy.getNoOfVacancies();
            double salary = companyVacancy.getSalary();
            String closingDate = companyVacancy.getClosingDate().ToString("yyyy-MM-dd HH:mm:ss");

            HandleDatabase handleDatabase2 = new HandleDatabase();
            try
            {
                Rest rest = new Rest("Company/AddVacancy");
                rest.SetMethod("POST");
                Dictionary<string, string> postData = new Dictionary<string, string>();

                postData.Add("JobTitle", title);
                postData.Add("Description", description);
                postData.Add("Preresquities", preresquites);
                postData.Add("Location", location);
                postData.Add("ClosingDate", closingDate);
                postData.Add("Salary", salary.ToString());
                postData.Add("NoOfVacs", noOfVacancies.ToString());
                postData.Add("CompanyID", companyId.ToString());

                rest.setFormData(postData);
                rest.Execute();
            }
            catch (WebException ex)
            {
                throw ex;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}
