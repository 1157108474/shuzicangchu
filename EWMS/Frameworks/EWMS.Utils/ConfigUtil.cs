namespace EWMS.Utils
{
    using System;
    using System.Configuration;

    public class ConfigUtil
    {
        public static bool GetConfigBool(string key)
        {
            bool flag = false;
            string configString = GetConfigString(key);
            if ((configString != null) && (string.Empty != configString))
            {
                try
                {
                    flag = bool.Parse(configString);
                }
                catch (FormatException)
                {
                }
            }
            return flag;
        }

        public static decimal GetConfigDecimal(string key)
        {
            decimal num = new decimal();
            string configString = GetConfigString(key);
            if ((configString != null) && (string.Empty != configString))
            {
                try
                {
                    num = decimal.Parse(configString);
                }
                catch (FormatException)
                {
                }
            }
            return num;
        }

        public static int GetConfigInt(string key)
        {
            int num = 0;
            string configString = GetConfigString(key);
            if ((configString != null) && (string.Empty != configString))
            {
                try
                {
                    num = int.Parse(configString);
                }
                catch (FormatException)
                {
                }
            }
            return num;
        }

        public static string GetConfigString(string key) =>
            ConfigurationManager.AppSettings[key].ToString();

        public static string GetConnectionString(string key) =>
            ConfigurationManager.ConnectionStrings[key].ToString();
    }
}
