namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    using System.Net;

    public class Base_LogService : BaseService<Base_Log>
    {
        private static readonly Base_LogService _Instance = new Base_LogService();

        public int AddFirmLog(Base_Log log)
        {
            IPAddress[] hostAddresses = Dns.GetHostAddresses(Dns.GetHostName());
            log.LogIP = hostAddresses[1].ToString();
            return Instance.Insert(log);
        }

        public void AddLoginLog(string userinfo, string ip)
        {
            object[] objArray1 = new object[] { "INSERT INTO Base_Log(LogType,LogObject,LogAction,LogDesc,LogTime,LogIP)VALUES('", 0, "','Person_User','用户登录','", userinfo, "',to_date('", DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss"), "','yyyy-mm-dd hh24:mi:ss'),'", ip, "')" };
            string sql = string.Concat(objArray1);
            base.ExecuteNonQuery_Fish(sql);
        }

        public void AddLogoutLog(string userinfo, string ip)
        {
            object[] objArray1 = new object[] { "INSERT INTO Base_Log(LogType,LogObject,LogAction,LogDesc,LogTime,LogIP)VALUES('", 0, "','Person_User','用户登出','", userinfo, "',to_date('", DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss"), "','yyyy-mm-dd hh24:mi:ss'),'", ip, "')" };
            string sql = string.Concat(objArray1);
            base.ExecuteNonQuery_Fish(sql);
        }

        public static Base_LogService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

