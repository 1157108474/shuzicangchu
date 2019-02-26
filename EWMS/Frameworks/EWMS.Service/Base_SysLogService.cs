namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;

    public class Base_SysLogService : BaseService<Base_SysLog>
    {
        private static readonly Base_SysLogService _Instance = new Base_SysLogService();

        public void AddLoginLog(string userinfo, string ip, string city)
        {
            Base_SysLog data = new Base_SysLog {
                LogType = 1,
                LogObject = "Base_User",
                LogAction = "用户登录",
                LogIP = ip,
                LogIPCity = city,
                LogDesc = userinfo
            };
            base.Insert(data);
        }

        public void AddLogoutLog(string userinfo, string ip, string city)
        {
            Base_SysLog data = new Base_SysLog {
                LogType = 1,
                LogObject = "Base_User",
                LogAction = "用户登出",
                LogIP = ip,
                LogIPCity = city,
                LogDesc = userinfo
            };
            base.Insert(data);
        }

        public static Base_SysLogService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

