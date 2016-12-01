using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NIBM_Project
{
    class Validate
    {
        public Boolean isEmpty(String value)
        {
            if (value == null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        public Boolean isNumeric(String value)
        {
            try
            {
                int number = Convert.ToInt32(value);
                return true;
            }
            catch (FormatException ex)
            {
                Console.WriteLine(ex.StackTrace);
                return false;
            }
            catch (OverflowException ex)
            {
                Console.WriteLine(ex.StackTrace);
                return false;
            }
        }

        public Boolean isTooShort(String value)
        {
            if (value.Length < 10)
            {
                return false;
            }
            else
            {
                return true;
            }

        }
    }
}
