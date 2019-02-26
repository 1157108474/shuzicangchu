namespace EWMS.Utils
{
    using log4net;
    using System;

    public class LogHelper
    {
        public static readonly ILog logdb = LogManager.GetLogger("logdb");
        public static readonly ILog logerror = LogManager.GetLogger("logerror");
        public static readonly ILog loginfo = LogManager.GetLogger("loginfo");

        private static string BeautyErrorMsg(Exception ex)
        {
            return string.Format("【异常类型】{0} <br>【异常信息】{1} <br>【堆栈调用】{2}", new object[] { ex.GetType().Name, ex.Message.Replace("\r\n", ""), ex.StackTrace }).Replace("\r\n", "<br>").Replace("位置", "<strong style=\"color:red\">位置</strong>");
        }

        public static void Error(string info, Exception ex)
        {
            if (!string.IsNullOrEmpty(info) && (ex == null))
            {
                object[] args = new object[] { info };
                logerror.ErrorFormat("{0}<br>", args);
            }
            else if (!string.IsNullOrEmpty(info) && (ex != null))
            {
                string str = BeautyErrorMsg(ex);
                object[] objArray2 = new object[] { info, str };
                logerror.ErrorFormat("{0}<br>{1}", objArray2);
            }
            else if (string.IsNullOrEmpty(info) && (ex != null))
            {
                string message = BeautyErrorMsg(ex);
                logerror.Error(message);
            }
        }

        public static void Write(string info)
        {
            if (loginfo.IsInfoEnabled)
            {
                loginfo.Info(info);
            }
        }

        public static void WriteDb(string info)
        {
            if (logdb.IsInfoEnabled)
            {
                logdb.Info(info);
            }
        }
    }
}

