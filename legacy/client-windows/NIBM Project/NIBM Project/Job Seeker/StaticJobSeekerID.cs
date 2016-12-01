using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    public class StaticJobSeekerID
    {
        private static int ID;
        private static String name;
        private static bool oldUser;

        public static void setID(int id)
        {
            ID = id;
        }
        public static int getID()
        {
            return ID;
        }
        public static void setName(String Name)
        {
            name = Name;
        }
        public static String getName()
        {
            return name;
        }
        public static void setOldUser(bool state)
        {
            oldUser = state;
        }
        public static bool getOldUser()
        {
            return oldUser;
        }
    }
}
