namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    using System.Collections.Generic;
    using System.Linq;
    
    using System.Text;

    public class Base_MenuService : BaseService<Base_Menu>
    {
        private static readonly Base_MenuService _Instance = new Base_MenuService();

        public CommandResult AddMenuButton(string menuCode, string buttonCode, int buttonSort, string buttonText)
        {
            CommandResult result = base.CommandResult.Set(true, "保存成功");
            string[] textArray1 = new string[] { " and MenuCode='", menuCode, "' and ButtonCode='", buttonCode, "'" };
            if (!Base_MenuButtonService.Instance.Exists_Fish(string.Concat(textArray1)))
            {
                object[] objArray1 = new object[] { "insert into Base_MenuButton (MenuCode,ButtonCode,ButtonSort,ButtonText) values('", menuCode, "','", buttonCode, "',", buttonSort, ",'", buttonText, "')" };
                string sql = string.Concat(objArray1);
                if (base.ExecuteNonQuery_Fish(sql) == 0)
                {
                    result = base.CommandResult.Set(true, "插入菜单按钮失败");
                }
            }
            return result;
        }

        public CommandResult DeleteMenu(string menuCode)
        {
            base.CommandResult.Set(true, "删除失败");
            base.db.UseTransaction(true);
            string sql = "delete from Base_RoleMenuButton where MenuCode in (select MenuCode from fn_GetChildrenMenu('" + menuCode + "'))";
            string str2 = "delete from Base_RoleMenu where MenuCode in (select MenuCode from fn_GetChildrenMenu('" + menuCode + "'))";
            string str3 = "delete from Base_MenuButton where MenuCode in (select MenuCode from fn_GetChildrenMenu('" + menuCode + "'))";
            string str4 = "delete from Base_Menu where MenuCode in (select MenuCode from fn_GetChildrenMenu('" + menuCode + "'))";
            base.db.Sql(sql, new object[0]).Execute();
            base.db.Sql(str2, new object[0]).Execute();
            base.db.Sql(str3, new object[0]).Execute();
            base.db.Sql(str4, new object[0]).Execute();
            base.db.Commit();
            return base.CommandResult.Set(true, "删除成功");
        }

        public CommandResult DisableChildrenMenus(string menuCode)
        {
            base.CommandResult.Set(true, "更新失败");
            base.db.UseTransaction(true);
            string sql = "update Base_Menu set Enabled=0 where MenuCode in (select MenuCode from fn_GetChildrenMenu('" + menuCode + "'))";
            base.db.Sql(sql, new object[0]).Execute();
            base.db.Commit();
            return base.CommandResult.Set(true, "更新成功");
        }

        public bool ExistsMenuButton(string buttonCode)
        {
            string sql = "select * from Base_MenuButton where ButtonCode='" + buttonCode + "'";
            return (base.GetDataTable_Fish(sql).Rows.Count > 0);
        }

        public List<Base_Menu> GetChildrenMenus(string MenuCode)
        {
            string str = "select MenuCode from fn_GetChildrenMenu ('" + MenuCode + "')";
            string sql = string.Format("select * from Base_Menu where MenuCode in({0}) order by Sort", str);
            return Instance.GetList_Fish(sql);
        }
         
        public dynamic GetMenuButtons()
        {
            string sql = "select * from V_Base_MenuButton order by MenuCode asc,ButtonSort asc";
            return base.GetDynamicList(sql);
        }

        public List<MenuData> GetMenus(string code)
        {
            string sqlOrCondition = "SELECT * FROM Base_Menu WHERE ParentCode='" + code + "'  order by sort";
            List<MenuData> list = new List<MenuData>();
            foreach (Base_Menu menu in Instance.GetList_Fish(sqlOrCondition, false))
            {
                MenuData item = new MenuData {
                    menucode = menu.MenuCode,
                    menuname = menu.MenuName,
                    menutype = menu.MenuType,
                    parentcode = menu.ParentCode,
                    url = menu.Url,
                    sort = menu.Sort,
                    memo = menu.Remark,
                    status = menu.Enabled,
                    buttonmode = menu.ButtonMode
                };
                string sql = "SELECT * FROM Base_Menu WHERE ParentCode='" + menu.MenuCode + "'";
                if (Instance.GetDataTable_Fish(sql).Rows.Count > 0)
                {
                    item.isLeaf = false;
                    item.expanded = false;
                }
                else
                {
                    item.isLeaf = true;
                    item.expanded = true;
                }
                list.Add(item);
            }
            return list;
        }
         
        public dynamic GetMenusAll()
        {
            string sql = "select * from Base_Menu where enabled=1";
            return base.GetDynamicList(sql);
        }

        private List<MenuData> GetTreeData_Children(List<Base_Menu> listMenu, List<Base_Menu> listMenu_Children)
        {
            List<MenuData> first = new List<MenuData>();
            using (List<Base_Menu>.Enumerator enumerator = listMenu_Children.GetEnumerator())
            {
                while (enumerator.MoveNext())
                {
                    Base_Menu item = enumerator.Current;
                    MenuData data = new MenuData {
                        menucode = item.MenuCode,
                        menuname = item.MenuName,
                        menutype = item.MenuType,
                        parentcode = item.ParentCode
                    };
                    first.Add(data);
                    List<MenuData> second = new List<MenuData>();
                    List<Base_Menu> list3 = listMenu.FindAll(p => p.ParentCode == item.MenuCode);
                    if (list3.Count > 0)
                    {
                        data.isLeaf = false;
                        data.expanded = true;
                        second = this.GetTreeData_Children(listMenu, list3);
                        first = first.Concat<MenuData>(second).ToList<MenuData>();
                    }
                    else
                    {
                        data.isLeaf = true;
                        data.expanded = true;
                    }
                }
            }
            return first;
        }

        public List<MenuData> GetTreeGrid_Menu()
        {
            List<Base_Menu> listMenu = Instance.GetList_Fish("and 1=1 and enabled=1 order by Sort ASC", true);
            List<MenuData> first = new List<MenuData>();
            MenuData item = new MenuData {
                menucode = "0",
                menuname = "所有菜单",
                menutype = 0,
                parentcode = "-3",
                expanded = true
            };
            first.Add(item);
            List<MenuData> second = new List<MenuData>();
            List<Base_Menu> list4 = listMenu.FindAll(p => p.ParentCode == "0");
            if (list4.Count > 0)
            {
                second = this.GetTreeData_Children(listMenu, list4);
                first = first.Concat<MenuData>(second).ToList<MenuData>();
            }
            return first;
        }
         
        public dynamic GetUserMenuButtons(int userId, string menucode)
        {
            StringBuilder builder = new StringBuilder();
            builder.Append(" select a.ButtonCode,(case  b.ButtonText when '' then a.ButtonName else b.ButtonText end) as ButtonName,b.ButtonSort as Sort,a.ButtonType,a.IconClass,a.JsEvent ");
            builder.Append(" from  Base_Button a  join Base_MenuButton b on a.ButtonCode=b.ButtonCode and b.MenuCode='" + menucode + "'");
            builder.Append(" where a.Enabled=1 and a.ButtonCode in (");
            builder.Append(string.Concat(new object[] { " select DISTINCT ButtonCode from Base_RoleMenuButton where MenuCode='", menucode, "' and RoleCode in(select RoleCode from Base_UserRole where UserId=", userId, ") ) order by b.ButtonSort" }));
            return Instance.GetDynamicList(builder.ToString());
        }

        public List<Base_Menu> GetUserMenus(int userId, bool isSuperAdmin)
        {
            new List<Base_Menu>();
            if (isSuperAdmin)
            {
                //string sql = "select MenuCode,MenuName,ParentCode, IconClass as iconCls ,MenuType,Url,Sort,Enabled from Base_Menu where Enabled=1 order by Sort";
                string sql = "select MenuCode,MenuName,ParentCode, IconClass,MenuType,Url,Sort,Enabled from Base_Menu where Enabled=1 order by Sort";
                return Instance.GetList_Fish(sql);
            }
            StringBuilder builder = new StringBuilder();
            builder.Append("select distinct MenuCode,MenuName,ParentCode, IconClass as iconCls ,MenuType,Url,Sort,Enabled  from Base_Menu where Enabled=1 START WITH MenuCode in (");
            builder.Append("select MenuCode from Base_RoleMenu where RoleCode in");
            builder.Append("(select RoleCode from Base_UserRole where UserId=" + userId + ")");
            builder.Append(") CONNECT BY  menucode= PRIOR parentcode  order by Sort");
            return Instance.GetList_Fish(builder.ToString());
        }

        public int Insertdata(MenuData data, int creator)
        {
            string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            string parentcode = data.parentcode;
            if (parentcode == "")
            {
                parentcode = "0";
            }
            string sql = "insert into Base_Menu (MenuCode ,MenuName ,ParentCode ,Url ,Sort ,Enabled ,Remark ,Creator ,CreateDate,Menutype,buttonmode) ";
            object[] objArray1 = new object[] { 
                sql, "values ('", data.menucode, "','", data.menuname, "','", parentcode, "','", data.url, "',", data.sort, ",", data.status, ",'", data.memo, "',",
                creator, ",to_date('", str, "','yyyy-mm-dd hh24:mi:ss'),", data.menutype, ",", data.buttonmode, ")"
            };
            sql = string.Concat(objArray1);
            try
            {
                return base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -2;
            }
        }

        public CommandResult RemoveMenuButton(string menuCode, string buttonCode)
        {
            CommandResult result = base.CommandResult.Set(true, "操作成功");
            string[] textArray1 = new string[] { "delete from Base_MenuButton where MenuCode='", menuCode, "' and ButtonCode='", buttonCode, "'" };
            string sql = string.Concat(textArray1);
            int num = base.ExecuteNonQuery_Fish(sql);
            string[] textArray2 = new string[] { "delete from Base_RoleMenuButton where MenuCode='", menuCode, "' and ButtonCode='", buttonCode, "'" };
            sql = string.Concat(textArray2);
            base.ExecuteNonQuery_Fish(sql);
            if (num == 0)
            {
                result = base.CommandResult.Set(true, "移除菜单按钮失败");
            }
            return result;
        }

        public CommandResult SaveMenuButton(string menuCode, string buttonCode, int buttonSort, string buttonText)
        {
            CommandResult result = base.CommandResult.Set(true, "保存成功");
            string[] textArray1 = new string[] { " and MenuCode='", menuCode, "' and ButtonCode='", buttonCode, "'" };
            if (Base_MenuButtonService.Instance.Exists_Fish(string.Concat(textArray1)))
            {
                string sql = string.Format("update Base_MenuButton set ButtonSort={0},ButtonText='{1}' where MenuCode='{2}' and ButtonCode='{3}'", new object[] { buttonSort, buttonText, menuCode, buttonCode });
                if (base.ExecuteNonQuery_Fish(sql) == 0)
                {
                    result = base.CommandResult.Set(true, "保存失败");
                }
            }
            return result;
        }

        public int Updatedata(MenuData data, int updater)
        {
            string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            object[] objArray1 = new object[] { 
                "update Base_Menu set parentcode='", data.parentcode, "', MenuName='", data.menuname, "',url='", data.url, "',Menutype=", data.menutype, ",buttonmode=", data.buttonmode, ",Sort=", data.sort, ",Enabled=", data.status, ",Remark='", data.memo,
                "',updater=", updater, ",UpdateDate=to_date('", str, "','yyyy-mm-dd hh24:mi:ss') where menucode='", data.menucode, "'"
            };
            string sql = string.Concat(objArray1);
            try
            {
                return base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -2;
            }
        }

        public static Base_MenuService Instance
        {
            get
            {
                return _Instance;
            }
        }
        
    }
}

