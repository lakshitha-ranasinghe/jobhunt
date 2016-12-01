using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    class StaticCompanyID
    {
        private static int ID;
        private static String companyName;
        public static void setID(int id)
        {
            ID = id; 
        }
        public static int getID()
        {
            return ID;
        }
        public static void setCompanyName(String Name)
        {
            companyName = Name;
        }
        public static String getCompanyName()
        {
            return companyName;
        }
    }
}
