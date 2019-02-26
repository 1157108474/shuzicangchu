namespace EWMS.Service
{
    using FluentData;
    using EWMS.Core;
    using EWMS.Entity;
    using EWMS.Utils;
    using Newtonsoft.Json.Linq;
    using System;
    using System.Collections.Generic;

    public class Base_UserService : BaseService<Base_User>
    {
        private static readonly Base_UserService _Instance = new Base_UserService();

        public CommandResult AuditUser(int userId, string op)
        {
            CommandResult result = CommandResult.Instance_Succeed;
            result.ResultMsg = "审核成功";
            ParamUpdate param = ParamUpdate.Instance().Update("Base_User").Column("IsAudit", 1).Column("AuditBy", op).Column("AuditTime", DateTime.Now).AndWhere("UserId", userId, null, new object[0]);
            if (Instance.Update(param) == 0)
            {
                result.Set(false, "审核失败");
            }
            LogHelper.Write(string.Format("用户审核成功。结果：{0}。用户编号：{1}", result.ResultMsg, userId));
            return result;
        }

        public Dictionary<string, object> GetCurrentUserSettings()
        {
            Dictionary<string, object> dictionary = new Dictionary<string, object>();
            int userId = base.CurrentBaseLoginer.UserId;
            foreach (Base_UserSetting setting in Base_UserSettingService.Instance.GetList_Fish(" and UserId =" + userId.ToString(), true))
            {
                dictionary.Add(setting.SettingCode, setting.SettingValue);
            }
            foreach (KeyValuePair<string, object> pair in this.GetDefaultUserSetttins())
            {
                if (!dictionary.ContainsKey(pair.Key))
                {
                    dictionary.Add(pair.Key, pair.Value);
                }
            }
            return dictionary;
        }

        public Dictionary<string, object> GetDefaultUserSetttins()
        {
            return new Dictionary<string, object> { 
                { 
                    "theme",
                    "default"
                },
                { 
                    "navigation",
                    "accordion"
                },
                { 
                    "gridrows",
                    "20"
                }
            };
        }

        public CommandResult Login(string userCode, string password, string ip, string city)
        {
            //TODO
            //new { 
            //    UserCode = userCode,
            //    Password = password,
            //    LoginIP = ip,
            //    LoginCity = city
            //};
            Dictionary<string, string> indict = new Dictionary<string, string> {
                { 
                    "Code",
                    userCode
                },
                { 
                    "Password",
                    password
                },
                { 
                    "LoginIP",
                    ip
                },
                { 
                    "LoginCity",
                    city
                }
            };
            Dictionary<string, string> dict = new Dictionary<string, string> {
                { 
                    "ResultID",
                    ""
                },
                { 
                    "ResultMsg",
                    ""
                }
            };
            return base.SP_Fish("Base_User_Login", indict, dict);
        }

        public CommandResult ModifySelfPassword(string newPassword, int userid)
        {
            CommandResult result = new CommandResult();
            string str = Md5Util.MD5(newPassword);
            object[] objArray1 = new object[] { "update Base_Person set password='", str, "' where ID=", userid };
            string sql = string.Concat(objArray1);
            int num = Base_PersonService.Instance.ExecuteNonQuery_Fish(sql);
            result.ResultID = (num > 0) ? 0 : -1;
            result.ResultMsg = (num > 0) ? "密码修改成功" : "密码修改失败";
            LogHelper.Write("修改个人密码。用户：" + base.CurrentBaseLoginer.UserName + "。结果：" + result.ResultMsg);
            return result;
        }

        public CommandResult ResetUserPassword(int userId, string userName, string userCode)
        {
            CommandResult result = CommandResult.Instance_Succeed;
            result.ResultMsg = "密码重置成功";
            string str = Md5Util.MD5("123456");
            ParamUpdate param = ParamUpdate.Instance().Update("Base_User").Column("Password", str).Column("LastChangePassword", DateTime.Now).AndWhere("UserId", userId, null, new object[0]);
            if (Instance.Update(param) == 0)
            {
                result.Set(false, "密码重置失败");
            }
            LogHelper.Write(string.Format("用户密码重置。结果：{3}。用户：{0}-({1})，登录账号:{2}", new object[] { userName, userId, userCode, result.ResultMsg }));
            return result;
        }

        public void SaveCurrentUserSettings(JObject settings)
        {
            int userId = base.CurrentBaseLoginer.UserId;
            foreach (JProperty property in settings.Children())
            {
                if (base.db.Update("Base_UserSetting").Column("SettingValue", property.Value.ToString(), DataTypes.Object, 0).Where("UserId", userId, DataTypes.Object, 0).Where("SettingCode", property.Name, DataTypes.Object, 0).Execute() <= 0)
                {
                    Base_UserSetting setting = new Base_UserSetting {
                        UserId = userId,
                        SettingCode = property.Name,
                        SettingValue = property.Value.ToString()
                    };
                    base.db.Insert("Base_UserSetting").Column("UserId", setting.UserId, DataTypes.Object, 0).Column("SettingCode", setting.SettingCode, DataTypes.Object, 0).Column("SettingName", setting.SettingCode, DataTypes.Object, 0).Column("SettingValue", setting.SettingValue, DataTypes.Object, 0).ExecuteReturnLastId<int>(null);
                }
            }
        }

        public static Base_UserService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

