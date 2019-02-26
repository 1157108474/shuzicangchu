namespace EWMS.Service
{
    using System;

    internal class Common
    {
        public static decimal GetDecimal(object obj)
        {
            if (obj == null)
            {
                return decimal.Zero;
            }
            return Convert.ToDecimal(obj.ToString().Trim());
        }

        public static int GetInt(object obj)
        {
            if (obj == null)
            {
                return 0;
            }
            return Convert.ToInt32(obj.ToString().Trim());
        }

        public static string GetStr(object obj)
        {
            if (obj == null)
            {
                return null;
            }
            return obj.ToString();
        }
    }
}

