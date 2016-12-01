using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class RecommendedJobSeekers
    {
        private String seekerName, qualifiedField, interestedPosition, eMail;
        private int TeleOne, TeleTwo;

        public void setName(String seekerName)
        {
            this.seekerName = seekerName;
        }
        public void setQualifiedField(String qualifiedField)
        {
            this.qualifiedField = qualifiedField;
        }
        public void setInterestedPosition(String interestedPosition)
        {
            this.interestedPosition = interestedPosition;
        }
        public void setEmail(String eMail)
        {
            this.eMail = eMail;
        }
        public void setTeleOne(int teleOne)
        {
            this.TeleOne = teleOne;
        }
        public void setTeleTwo(int teleTwo)
        {
            this.TeleTwo = teleTwo;
        }

        public String getSeekerName()
        {
            return seekerName;
        }
        public String getQualifiedField()
        {
            return qualifiedField;
        }
        public String getInterestedPosition()
        {
            return interestedPosition;
        }
        public String getEmail()
        {
            return eMail;
        }
        public int getTeleOne()
        {
            return TeleOne;
        }
        public int getTeleTwo()
        {
            return TeleTwo;
        }
    }
}
