using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{    
    public class JobSeekerEducation
    {
        private OrdinaryLevel ordinaryLevel;
        private AdvanceLevel advanceLevel;
        private HigherEducation higherEducation;
        private int id;

        public JobSeekerEducation()
        {
            Console.WriteLine("Job Seeker Education Created");
        }
        public void setId(int id)
        {
            this.id = id;
        }
        public void setOrdinaryLevel(OrdinaryLevel ordinaryLevel)
        {
            this.ordinaryLevel = ordinaryLevel;
        }
        public void setAdvanceLevel(AdvanceLevel advanceLevel)
        {
            this.advanceLevel = advanceLevel;
        }
        public void setHigherEducation(HigherEducation higherEducation)
        {
            this.higherEducation = higherEducation;
        }

        //Get
        public int getOLYear()
        {
            return ordinaryLevel.getYear();
        }
        public String getOLSchool()
        {
            return ordinaryLevel.getSchool();
        }
        public String[] getOLSubjects()
        {
            return ordinaryLevel.getSubjects();
        }
        public String[] getOLResults()
        {
            return ordinaryLevel.getResults();
        }
        public int getALYear()
        {
            return advanceLevel.getYear();
        }
        public String getALSchool()
        {
            return advanceLevel.getSchool();
        }
        public String[] getALSubjects()
        {
            return advanceLevel.getSubjects();
        }
        public String[] getALResults()
        {
            return advanceLevel.getResults();
        }
        public String[] getInstitute()
        {
            return higherEducation.getInstitue();
        }
        public String[] getCourse()
        {
            return higherEducation.getCourse();
        }
        public int[] getYear()
        {
            return higherEducation.getYear();
        }
        public double[] getGPA()
        {
            return higherEducation.getGPA();
        }
        public int[] getOrdinaryLevelId()
        {
            return ordinaryLevel.getOrdinaryLevelId();
        }
        public int[] getAdvanceLevelId()
        {
            return advanceLevel.getAdvanceLevelId();
        }
        public int[] getHigherEducationId()
        {
            return higherEducation.getHigherEducationId();
        }
    }
}
