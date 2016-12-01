using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class JobSeeker
    {
        private String title, fullName, address, email;  
        private long telMobile, telHome;   
        private DateTime dateOfBirth;
        private int id;

        public JobSeeker()
        {
            Console.WriteLine("Job Seeker Created");
        }
        public void setId(int id)
        {
            this.id = id;
        }
        public void setTitle(String title)  
        {
            this.title = title;
        }
        public void setFullName(String firstName, String lastName="") 
        {
            this.fullName = firstName + " " + lastName;
        }
        public void setAddress(String addressLine1, String addressLine2="", String addressLine3="") 
        {
            this.address = addressLine1 + " " + addressLine2 + " " + addressLine3;          
        }
        public void setTelMobile(int telMobile)   
        {
            this.telMobile = telMobile;
        }
        public void setTelHome(int telHome)   
        {
            this.telHome = telHome;
        }
        public void setEmail(String email)
        {
            this.email = email;
        }
        public void setDateOfBirth(DateTime dateOfBirth)
        {
            this.dateOfBirth = dateOfBirth;
        }

        //Get
        public int getId()
        {
            return id;
        }
        public String getTitle()
        {
            return title;
        }
        public String getName()
        {
            return fullName;
        }
        public String getAddress()
        {
            return address;
        }
        public String getEmail()
        {
            return email;
        }
        public long getTelephoneMobile()
        {
            return telMobile;
        }
        public long getTelephoneHome()
        {
            return telHome;
        }
        public DateTime getBirthday()
        {
            return dateOfBirth;
        }
    }
}
