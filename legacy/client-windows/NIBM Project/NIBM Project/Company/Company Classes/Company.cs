using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class Company
    {
        private int id;
        private String companyName;
        private String companyType;
        private long firstTelephone;
        private long secondTelephone;
        private String address;
        private String website;
        private String offeredJobTypes;
        private String description;
        private String email;

        public Company()
        {
            Console.WriteLine("Company Created");
        }
        public void setId(int id)
        {
            this.id = id;
        }
        public void setCompanyName(String companyName)
        {
            this.companyName = companyName;
        }
        public void setCompanyType(String companyType)
        {
            this.companyType = companyType;
        }
        public void setFirstTelephone(long firstTelephone)
        {
            this.firstTelephone = firstTelephone;
        }
        public void setSecondTelephone(long secondTelephone)
        {
            this.secondTelephone = secondTelephone;
        }
        public void setEmail(String email)
        {
            this.email = email;
        }

        public void setAddress(String addressLine1,String addressLine2="",String addressLine3="")
        {
            this.address = addressLine1 +" "+ addressLine2+" "+ addressLine3;
        }
        
        public void setWebsite(String website)
        {
            this.website = website;
        }
        public void setOfferedJobTypes(String offeredJobTypes)
        {
            this.offeredJobTypes = offeredJobTypes;
        }
        public void setDescription(String description)
        {
            this.description = description;
        }

        //Get
        public int getId()
        {
            return id;
        }
        public String getCompanyName()
        {
            return companyName;
        }
        public String getCompanyType()
        {
            return companyType;
        }
        public long getFirstTelephone()
        {
            return firstTelephone;
        }
        public long getSecondTelephone()
        {
            return secondTelephone;
        }
        public String getAddress()
        {
            return address;
        }
        public String getWebsite()
        {
            return website;
        }
        public String getOfferedJobTypes()
        {
            return offeredJobTypes;
        }
        public String getDescription()
        {
            return description;
        }
        public String getEmail()
        {
            return email;
        }

    }
}
