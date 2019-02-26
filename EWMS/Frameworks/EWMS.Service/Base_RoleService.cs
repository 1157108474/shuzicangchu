namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    

    public class Base_RoleService : BaseService<Base_Role>
    {
        private static readonly Base_RoleService _Instance = new Base_RoleService();

        public CommandResult AddRoleUser(string roleCode, int userId)
        {
            CommandResult result = base.CommandResult.Set(true, "保存成功");
            object[] objArray1 = new object[] { " and RoleCode='", roleCode, "'  and UserId=", userId, " " };
            if (!Base_UserRoleService.Instance.Exists_Fish(string.Concat(objArray1)))
            {
                object[] objArray2 = new object[] { "insert into Base_UserRole (UserId,RoleCode) values(", userId, ",'", roleCode, "')" };
                string sql = string.Concat(objArray2);
                if (base.ExecuteNonQuery_Fish(sql) == 0)
                {
                    result = base.CommandResult.Set(true, "插入角色用户失败");
                }
            }
            return result;
        }
         
        public dynamic GetRoleMenuButtons(string roleCode)
        {
            string sql = "select * from Base_RoleMenuButton where RoleCode='" + roleCode + "'";
            return base.GetDynamicList(sql);
        }
         
        public dynamic GetRoleMenus(string roleCode)
        {
            string sql = "select a.rolecode,a.menucode from Base_Rolemenu a left join base_menu b on a.menucode=b.menucode where RoleCode='" + roleCode + "' and b.parentcode<>'0'";
            return base.GetDynamicList(sql);
        }
         
        public dynamic GetRoleUsers(string roleCode)
        {
            string sql = "select a.RoleCode,a.UserId,b.RealName,b.UserCode,b.UserType,b.Sort,b.Enabled,b.Remark from Base_UserRole a left join Base_User b on a.UserId=b.UserId where a.RoleCode='" + roleCode + "'";
            return base.GetDynamicList(sql);
        }

        public int Insertdata(Base_Role entity)
        {
            int num = 0;
            if (base.Exists_Fish(" and RoleCode='" + entity.RoleCode + "'"))
            {
                return -2;
            }
            string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            string sql = "insert into Base_Role(RoleCode ,RoleName ,RoleType ,Sort ,Enabled ,Remark ,Creator ,CreateDate) ";
            object[] objArray1 = new object[] { 
                sql, "values ('", entity.RoleCode, "','", entity.RoleName, "',", entity.RoleType, ",", entity.Sort, ",", entity.Enabled, ",'", entity.Remark, "',", entity.Creator, ",to_date('",
                str, "','yyyy-mm-dd hh24:mi:ss'))"
            };
            sql = string.Concat(objArray1);
            try
            {
                num = base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -3;
            }
            return num;
        }

        public CommandResult RemoveRoleUser(string roleCode, int userId)
        {
            CommandResult result = base.CommandResult.Set(true, "操作成功");
            object[] objArray1 = new object[] { "delete from Base_UserRole where RoleCode='", roleCode, "' and UserId=", userId };
            string sql = string.Concat(objArray1);
            if (base.ExecuteNonQuery_Fish(sql) == 0)
            {
                result = base.CommandResult.Set(true, "移除角色用户失败");
            }
            return result;
        }

        public CommandResult SaveRoleMenuButton(bool flagAdd, string roleCode, string menuCode, string buttonCode)
        {
            CommandResult result = base.CommandResult.Set(true, "保存成功");
            string condition = string.Format(" and RoleCode='{0}' and MenuCode='{1}' and ButtonCode='{2}' ", roleCode, menuCode, buttonCode);
            bool flag = Base_RoleMenuButtonService.Instance.Exists_Fish(condition);
            if (flagAdd)
            {
                if (!flag)
                {
                    string sql = string.Format("INSERT INTO Base_RoleMenuButton(RoleCode,MenuCode,ButtonCode)VALUES('{0}','{1}','{2}')", roleCode, menuCode, buttonCode);
                    if (base.ExecuteNonQuery_Fish(sql) == 0)
                    {
                        result = base.CommandResult.Set(true, "保存失败");
                    }
                }
                return result;
            }
            if (flag)
            {
                string str3 = string.Format("DELETE FROM Base_RoleMenuButton WHERE RoleCode='{0}' AND MenuCode='{1}' AND ButtonCode='{2}'", roleCode, menuCode, buttonCode);
                if (base.ExecuteNonQuery_Fish(str3) == 0)
                {
                    result = base.CommandResult.Set(true, "保存失败");
                }
            }
            return result;
        }

        public CommandResult SaveRoleMenuButton_All(bool flagAdd, string roleCode, string menuCode)
        {
            CommandResult result = base.CommandResult.Set(true, "保存成功");
            string sql = string.Format("DELETE FROM Base_RoleMenuButton WHERE RoleCode='{0}' AND MenuCode='{1}' ", roleCode, menuCode);
            base.ExecuteNonQuery_Fish(sql);
            if (flagAdd)
            {
                sql = "INSERT INTO Base_RoleMenuButton(RoleCode,MenuCode,ButtonCode) ";
                sql = sql + string.Format(" SELECT '{0}',MenuCode, ButtonCode FROM Base_MenuButton WHERE MenuCode='{1}'", roleCode, menuCode);
                if (base.ExecuteNonQuery_Fish(sql) == 0)
                {
                    result = base.CommandResult.Set(true, "保存失败");
                }
            }
            return result;
        }

        public int SaveRoleMenus(string roleCode, string menuCode)
        {
            int num = 0;
            string sql = string.Format("DELETE FROM Base_RoleMenu WHERE RoleCode='{0}'", roleCode);
            num = base.ExecuteNonQuery_Fish(sql);
            string[] separator = new string[] { "," };
            foreach (string str2 in menuCode.Split(separator, StringSplitOptions.RemoveEmptyEntries))
            {
                if (str2 != "0")
                {
                    sql = string.Format("INSERT INTO Base_RoleMenu(RoleCode,MenuCode)VALUES('{0}','{1}')", roleCode, str2);
                    num = base.ExecuteNonQuery_Fish(sql);
                }
            }
            return num;
        }

        public int UpdateData(Base_Role entity)
        {
            int num = 0;
            object[] objArray1 = new object[] { "update Base_Role set \r\n                       RoleName='", entity.RoleName, "',RoleType=", entity.RoleType, ",Sort=", entity.Sort, ",Enabled=", entity.Enabled, ",Remark='", entity.Remark, "' where RoleCode='", entity.RoleCode, "'" };
            string sql = string.Concat(objArray1);
            try
            {
                num = base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -2;
            }
            return num;
        }

        public static Base_RoleService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

