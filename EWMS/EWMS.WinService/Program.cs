using EWMS.Core;
using EWMS.Utils;
using log4net.Config;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.ServiceProcess;
using System.Text;
using System.Windows.Forms;
using Topshelf;
using Topshelf.HostConfigurators;

namespace EWMS.WinService
{
    static class Program
    {
        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        static void Main(string[] args)
        {

            Application.ThreadException += Application_ThreadException;
            FrameworkConfig.Register();
            XmlConfigurator.ConfigureAndWatch(new FileInfo(Path.Combine(Application.StartupPath, "log4net.config")));
            if (args != null && args.Length > 0 && args[0] == "ui")
            {
                Application.EnableVisualStyles();
                Application.SetCompatibleTextRenderingDefault(false);
                Application.Run(new MainForm());
                return;
            }
            else
            {
                try
                {
                    HostFactory.Run(delegate (HostConfigurator x)
                    {
                        x.UseLog4Net();
                        x.Service<ServiceRunner>();
                        x.RunAsLocalSystem();
                        x.SetDescription("数字化仓库定时同步数据服务");
                        x.SetDisplayName("WMS Interface Service");
                        x.SetServiceName("WMS Interface Service");
                        x.EnablePauseAndContinue();
                    });
                }
                catch (Exception ex)
                {
                    LogHelper.Error("load", ex);
                }
            }
        }

        private static void Application_ThreadException(object sender, System.Threading.ThreadExceptionEventArgs e)
        {
            LogHelper.Error("ThreadException", e.Exception);
        }
    }
}
