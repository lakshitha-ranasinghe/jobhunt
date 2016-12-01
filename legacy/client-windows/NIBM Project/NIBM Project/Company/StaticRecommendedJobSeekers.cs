using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    class StaticRecommendedJobSeekers
    {
        private static Dictionary<Int32, RecommendedJobSeekers> RecommendedSeeker = new Dictionary<int, RecommendedJobSeekers>();

        public static void setRecommendedJobSeekers(Dictionary<Int32, RecommendedJobSeekers> RecommendedSeekersTemp)
        {
            RecommendedSeeker = RecommendedSeekersTemp;
        }
        public static Dictionary<Int32, RecommendedJobSeekers> getRecommendedJobSeekers()
        {
            return RecommendedSeeker;
        }
    }
}
