using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class JobSeekerProfessionalQualification
    {
        private int expYears;

        private String firstDesignation;
        private String firstCompany;
        private DateTime firstStartDate;
        private DateTime firstEndDate;

        private String secondDesignation;
        private String secondCompany;
        private DateTime secondStartDate;
        private DateTime secondEndDate;

        private int firstCompanyId;
        private int secondCompanyId;

        private String otherFirstTitle;
        private int otherFirstYear;
        private String otherFirstDetails;

        private String otherSecondTitle;
        private int otherSecondYear;
        private String otherSecondDetails;

        private int firstOtherId;
        private int secondOtherId;

        public void setExpYears(int expYears)
        {
            this.expYears = expYears;
        }
        
        public void setFirstCompany(String firstDesignation, String firstCompany, DateTime firstStartDate, DateTime firstEndDate)
        {
            this.firstDesignation = firstDesignation;
            this.firstCompany = firstCompany;
            this.firstStartDate = firstStartDate;
            this.firstEndDate = firstEndDate;
        }
        public void setSecondCompany(String secondDesignation, String secondCompany, DateTime secondStartDate, DateTime secondEndDate)
        {
            this.secondDesignation = secondDesignation;
            this.secondCompany = secondCompany;
            this.secondStartDate = secondStartDate;
            this.secondEndDate = secondEndDate;
        }
        public void setFirstOther(String otherFirstTitle, int otherFirstYear, String otherFirstDetails)
        {
            this.otherFirstTitle = otherFirstTitle;
            this.otherFirstYear = otherFirstYear;
            this.otherFirstDetails = otherFirstDetails;
        }
        public void setSecondOther(String otherSecondTitle, int otherSecondYear, String otherSecondDetails)
        {
            this.otherSecondTitle = otherSecondTitle;
            this.otherSecondYear = otherSecondYear;
            this.otherSecondDetails = otherSecondDetails;
        }
        public void setFirstCompanyId(int firstCompanyId)
        {
            this.firstCompanyId = firstCompanyId;
        }
        public void setSecondCompanyId(int secondCompanyId)
        {
            this.secondCompanyId = secondCompanyId;
        }
        public void setFirstOtherId(int firstOtherId)
        {
            this.firstOtherId = firstOtherId;
        }
        public void setSecondOtherId(int secondOtherId)
        {
            this.secondOtherId = secondOtherId;
        }

        //Get
        //First
        public int getExpYears()
        {
            return expYears;
        }
        public String getFirstDesignation()
        {
            return firstDesignation;
        }
        public DateTime getFirstStartDate()
        {
            return firstStartDate;
        }
        public String getFirstCompany()
        {
            return firstCompany;
        }
        public DateTime getFirstEndDate()
        {
            return firstEndDate;
        }
        public int getFirstCompanyId()
        {
            return firstCompanyId;
        }

        //Second
        public String getSecondDesignation()
        {
            return secondDesignation;
        }
        public DateTime getSecondStartDate()
        {
            return secondStartDate;
        }
        public String getSecondCompany()
        {
            return secondCompany;
        }
        public DateTime getSecondEndDate()
        {
            return secondEndDate;
        }
        public int getSecondCompanyId()
        {
            return secondCompanyId;
        }

        //Other First
        public int getOtherFirstYear()
        {
            return otherFirstYear;
        }
        public String getOtherFirstTitle()
        {
            return otherFirstTitle;
        }
        public String getOtherFirstDetails()
        {
            return otherFirstDetails;
        }
        public int getFirstOtherId()
        {
            return firstOtherId;
        }

        //Other Second
        public int getOtherSecondYear()
        {
            return otherSecondYear;
        }
        public String getOtherSecondTitle()
        {
            return otherSecondTitle;
        }
        public String getOtherSecondDetails()
        {
            return otherSecondDetails;
        }
        public int getSecondOtherId()
        {
            return secondOtherId;
        }
    }
}
