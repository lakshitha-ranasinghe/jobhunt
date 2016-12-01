using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class StaticJobSeekerData
    {
        private static JobSeeker jobSeekerPersonal;
        private static JobSeekerPreferences jobSeekerPreferences;
        private static JobSeekerEducation jobSeekerEducation;
        private static JobSeekerProfessionalQualification jobSeekerProfessionalQualification;

        public static void setJobSeeker(JobSeeker jobSeeker, JobSeekerPreferences jobSeekerPreferences, JobSeekerEducation jobSeekerEducation, JobSeekerProfessionalQualification jobSeekerProfessionalQualification)
        {
            StaticJobSeekerData.jobSeekerPersonal = jobSeeker;
            StaticJobSeekerData.jobSeekerPreferences = jobSeekerPreferences;
            StaticJobSeekerData.jobSeekerEducation = jobSeekerEducation;
            StaticJobSeekerData.jobSeekerProfessionalQualification = jobSeekerProfessionalQualification;
        }

        public static JobSeeker getJobSeekerPersonalData()
        {
            return jobSeekerPersonal;
        }
        public static JobSeekerPreferences getJobSeekerPreferences()
        {
            return jobSeekerPreferences;
        }
        public static JobSeekerEducation getJobSeekerEducation()
        {
            return jobSeekerEducation;
        }
        public static JobSeekerProfessionalQualification getJobSeekerProfessionalQualification()
        {
            return jobSeekerProfessionalQualification;
        }
        public static void setJobSeekerPersonalData(JobSeeker jobSeeker)
        {
            StaticJobSeekerData.jobSeekerPersonal = jobSeeker;
        }
        public static void setJobSeekerPreferencesData(JobSeekerPreferences jobSeekerPreferences)
        {
            StaticJobSeekerData.jobSeekerPreferences = jobSeekerPreferences;
        }
        public static void setJobSeekerEducationData(JobSeekerEducation jobSeekerEducation)
        {
            StaticJobSeekerData.jobSeekerEducation = jobSeekerEducation;
        }
    }
}
