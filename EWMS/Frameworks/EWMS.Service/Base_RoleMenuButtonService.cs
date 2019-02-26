namespace EWMS.Service
{
    using EWMS.Core;
    using System;
    using EWMS.Entity;

    public class Base_RoleMenuButtonService : BaseService<Base_RoleMenuButton>
    {
        private static readonly Base_RoleMenuButtonService _Instance = new Base_RoleMenuButtonService();

        public int Insertdata(bool flag, string rcode, string mcode, string bcode)
        {
            int num = 0;
            string condition = string.Format(" and RoleCode='{0}' and MenuCode='{1}' and ButtonCode='{2}' ", rcode, mcode, bcode);
            bool flag2 = Instance.Exists_Fish(condition);
            if (flag)
            {
                if (!flag2)
                {
                    string sql = string.Format("INSERT INTO Base_RoleMenuButton(RoleCode,MenuCode,ButtonCode)VALUES('{0}','{1}','{2}')", rcode, mcode, bcode);
                    num = base.ExecuteNonQuery_Fish(sql);
                }
                return num;
            }
            if (flag2)
            {
                string str3 = string.Format("DELETE FROM Base_RoleMenuButton WHERE RoleCode='{0}' AND MenuCode='{1}' AND ButtonCode='{2}'", rcode, mcode, bcode);
                num = base.ExecuteNonQuery_Fish(str3);
            }
            return num;
        }

        public bool SaveRoleMenuButton_All(bool flag, string rcode, string mcode)
        {
            bool flag2 = true;
            try
            {
                string sql = string.Format("DELETE FROM Base_RoleMenuButton WHERE RoleCode='{0}' AND MenuCode='{1}' ", rcode, mcode);
                base.ExecuteNonQuery_Fish(sql);
                if (flag)
                {
                    sql = "INSERT INTO Base_RoleMenuButton(RoleCode,MenuCode,ButtonCode) ";
                    sql = sql + string.Format(" SELECT '{0}',MenuCode, ButtonCode FROM Base_MenuButton WHERE MenuCode='{1}'", rcode, mcode);
                    flag2 = base.ExecuteNonQuery_Fish(sql) > 0;
                }
            }
            catch (Exception)
            {
                flag2 = false;
            }
            return flag2;
        }

        public static Base_RoleMenuButtonService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

