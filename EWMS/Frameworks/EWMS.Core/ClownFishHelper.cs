namespace EWMS.Core
{
    using ClownFish;
    using System;
    using System.Configuration;
    using System.IO;
    using System.Text;
    using System.Web;

    public static class ClownFishHelper
    {
        private static void BuildManager_OnBuildException(Exception ex)
        {
            CompileException exception = ex as CompileException;
            if (exception != null)
            {
                SafeLogException(exception.GetDetailMessages());
            }
            else
            {
                SafeLogException(ex.ToString());
            }
        }

        public static void Init(string conn)
        {
            try
            {
                DbContextDefaultSetting.AutoRetrieveOutputValues = true;
                BuildManager.OnBuildException += new BuildExceptionHandler(ClownFishHelper.BuildManager_OnBuildException);
                ConnectionStringSettings settings = ConfigurationManager.ConnectionStrings[conn];
                string providerName = settings.ProviderName;
                if (providerName == "SqlServer")
                {
                    providerName = "System.Data.SqlClient";
                }
                else if (providerName == "Oracle")
                {
                    providerName = "Oracle.ManagedDataAccess.Client";
                }
                DbContext.RegisterDbConnectionInfo("ORCL", providerName, "", settings.ConnectionString);
                Profiler.ApplicationName = "DigitalWarehouse_ProductWZ";
                Profiler.TryStartClownFishProfiler();
            }
            catch (Exception exception1)
            {
                throw exception1;
            }
        }

        public static void SafeLogException(string message)
        {
            try
            {
                File.AppendAllText(Path.Combine(HttpRuntime.AppDomainAppPath, @"finshlog\ErrorLog.txt"), "=========================================\r\n" + message, Encoding.UTF8);
            }
            catch
            {
            }
        }
    }
}

