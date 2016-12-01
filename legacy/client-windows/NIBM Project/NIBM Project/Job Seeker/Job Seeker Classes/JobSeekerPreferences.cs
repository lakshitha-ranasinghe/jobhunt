using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class JobSeekerPreferences
    {
        private String qualifiedField, interestedPosition, lastJob;
        private int id;

        public JobSeekerPreferences()
        {
            Console.WriteLine("Job Seeker Preference Created");
        }
        public void setId(int id)
        {
            this.id = id;
        }
        public void setQualifiedField(String qualifiedField)
        {
            this.qualifiedField = qualifiedField;
        }
        public void setInterestedPosition(String interestedPosition)
        {
            this.interestedPosition = interestedPosition;
        }
        public void setLastJob(String lastJob)
        {
            this.lastJob = lastJob;
        }
        
        //Get
        public int getId()
        {
            return id;
        }
        public String getQualifiedField()
        {
            return qualifiedField;
        }
        public String getinterestedPosition(){
            return interestedPosition;
        }
        public String getLastJob()
        {
            return lastJob;
        }
    }
}
