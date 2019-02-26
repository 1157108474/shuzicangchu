namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using Newtonsoft.Json.Linq;
    using System;
    using System.Collections.Generic;

    public class Base_CompanyService : BaseService<Base_Company>
    {
        private static readonly Base_CompanyService _Instance = new Base_CompanyService();

        public CommandResult AddOrg(JObject obj, string addby)
        {
            base.CommandResult.Set(true, "保存成功");
            int num = obj.Value<int>("Select_OP");
            string str = obj.Value<string>("OrgCode");
            string str2 = obj.Value<string>("ParentOrgCode");
            string str3 = obj.Value<string>("OrgCompanyCode");
            string str4 = obj.Value<string>("OrgName");
            int num2 = obj.Value<int>("OrgType");
            int num3 = obj.Value<int>("OrgSort");
            int num4 = obj.Value<int>("OrgEnabled");
            string str5 = obj.Value<string>("OrgRemark");
            string str6 = "INSERT INTO [dbo].[Base_Company] ([CompanyCode],[ParentCode],[CompanyName],[ShortName],[CompanyType],[Sort],[Enabled],[Remark],[AddBy]) ";
            string str7 = "INSERT INTO [dbo].[Base_Department] ([DepartmentCode] ,[ParentCode] ,[DepartmentName] ,[ShortName] ,[CompanyCode] ,[Sort] ,[Enabled] ,[Remark] ,[AddBy]) ";
            string sql = "";
            switch (num)
            {
                case 1:
                case 2:
                    sql = sql + str6 + string.Format("VALUES('{0}','{1}','{2}','{3}',{4},{5},{6},'{7}','{8}')", new object[] { str, str2, str4, str4, num2, num3, num4, str5, addby });
                    break;

                case 3:
                case 4:
                    sql = sql + str7 + string.Format("VALUES('{0}','{1}','{2}','{3}','{4}',{5},{6},'{7}','{8}')", new object[] { str, str2, str4, str4, str3, num3, num4, str5, addby });
                    break;

                default:
                    base.CommandResult.Set(false, "保存模式有误，Select_OP不是1/2/3/4任何一种");
                    break;
            }
            if (base.ExecuteNonQuery_Fish(sql) == 0)
            {
                base.CommandResult.Set(false, "保存失败");
            }
            return base.CommandResult;
        }

        public CommandResult DeleteOrg(JObject obj)
        {
            base.CommandResult.Set(true, "删除成功");
            string str = obj.Value<string>("OrgCode");
            obj.Value<string>("OrgName");
            int num = obj.Value<int>("OrgType");
            string sql = "DELETE FROM Base_Department WHERE CompanyCode IN (select CompanyCode from [dbo].[fn_GetChildrenCompanyCode]('" + str + "'))";
            string str3 = "DELETE FROM Base_Company WHERE CompanyCode IN (select CompanyCode from [dbo].[fn_GetChildrenCompanyCode]('" + str + "'))";
            string str4 = "DELETE FROM Base_Department WHERE DepartmentCode IN (select DepartmentCode from [dbo].[fn_GetChildrenDepartmentCode]('" + str + "'))";
            string[] textArray1 = new string[] { " and CompanyCode='", str, "' or DepartmentCode='", str, "'" };
            if (Base_UserService.Instance.Exists_Fish(string.Concat(textArray1)))
            {
                base.CommandResult.Set(false, "组织机构已经被用户使用，无法删除");
                return base.CommandResult;
            }
            base.db.UseTransaction(true);
            switch (num)
            {
                case 2:
                    base.db.Sql(sql, new object[0]).Execute();
                    base.db.Sql(str3, new object[0]).Execute();
                    break;

                case 9:
                    base.db.Sql(str4, new object[0]).Execute();
                    break;
            }
            base.db.Commit();
            return base.CommandResult;
        }

        public CommandResult EditOrg(JObject obj, string editby)
        {
            base.CommandResult.Set(true, "保存成功");
            string str = obj.Value<string>("OrgCode");
            string str2 = obj.Value<string>("OrgName");
            int num = obj.Value<int>("OrgType");
            int num2 = obj.Value<int>("OrgSort");
            int num3 = obj.Value<int>("OrgEnabled");
            string str3 = obj.Value<string>("OrgRemark");
            string sql = string.Format("UPDATE [dbo].[Base_Company] SET [CompanyName] ='{0}',[ShortName] ='{1}',[Sort] ={2},[Enabled] = {3},[Remark] ='{4}',[EditBy] ='{5}',[EditOn] =GETDATE() WHERE [CompanyCode] ='{6}'", new object[] { str2, str2, num2, num3, str3, editby, str });
            if (num == 9)
            {
                sql = string.Format("UPDATE [dbo].[Base_Department] SET [DepartmentName] ='{0}',[ShortName] ='{1}',[Sort] ={2},[Enabled] = {3},[Remark] ='{4}',[EditBy] ='{5}',[EditOn] =GETDATE() WHERE [DepartmentCode] ='{6}'", new object[] { str2, str2, num2, num3, str3, editby, str });
            }
            if (base.ExecuteNonQuery_Fish(sql) == 0)
            {
                base.CommandResult.Set(false, "保存失败");
            }
            return base.CommandResult;
        }

        private List<org_treedata> GetTreeData_ChildrenComany(List<Base_Company> listComany, List<Base_Company> listComany_Children, List<Base_Department> listDepartment)
        {
            List<org_treedata> list = new List<org_treedata>();
            using (List<Base_Company>.Enumerator enumerator = listComany_Children.GetEnumerator())
            {
                while (enumerator.MoveNext())
                {
                    Base_Company item = enumerator.Current;
                    org_treedata _treedata = new org_treedata();
                    List<org_treedata> list2 = new List<org_treedata>();
                    List<Base_Company> list3 = listComany.FindAll(p => p.ParentID == item.CompanyID);
                    if (list3.Count > 0)
                    {
                        list2 = this.GetTreeData_ChildrenComany(listComany, list3, listDepartment);
                    }
                    List<Base_Department> list4 = listDepartment.FindAll(p => (p.CompanyCode == item.CompanyCode) && (p.ParentCode == "0"));
                    if (list4.Count > 0)
                    {
                        list2.AddRange(this.GetTreeData_Department(listDepartment, list4));
                    }
                    _treedata.children = list2;
                    list.Add(_treedata);
                }
            }
            return list;
        }

        private List<org_treedata> GetTreeData_ChildrenDepartment(List<Base_Department> listDepartment, List<Base_Department> listDepartment_Children)
        {
            List<org_treedata> list = new List<org_treedata>();
            using (List<Base_Department>.Enumerator enumerator = listDepartment_Children.GetEnumerator())
            {
                while (enumerator.MoveNext())
                {
                    Base_Department item = enumerator.Current;
                    org_treedata _treedata1 = new org_treedata {
                        id = item.DepartmentCode,
                        text = item.DepartmentName,
                        iconCls = "icon-standard-note",
                        state = "open",
                        children = new List<org_treedata>()
                    };
                    org_attributes _attributes1 = new org_attributes {
                        org_type = 9,
                        org_code = item.DepartmentCode,
                        org_pcode = item.ParentCode,
                        org_fcode = item.CompanyCode,
                        org_sort = item.Sort,
                        org_enabled = item.Enabled,
                        org_remark = item.Remark
                    };
                    _treedata1.attributes = _attributes1;
                    org_treedata _treedata = _treedata1;
                    List<Base_Department> list2 = listDepartment.FindAll(p => p.ParentCode == item.DepartmentCode);
                    if (list2.Count > 0)
                    {
                        _treedata.children = this.GetTreeData_ChildrenDepartment(listDepartment, list2);
                    }
                    list.Add(_treedata);
                }
            }
            return list;
        }

        public List<org_treedata> GetTreeData_Comany()
        {
            return new List<org_treedata>();
        }

        private List<org_treedata> GetTreeData_Department(List<Base_Department> listDepartment, List<Base_Department> listDepartment_Current)
        {
            List<org_treedata> list = new List<org_treedata>();
            using (List<Base_Department>.Enumerator enumerator = listDepartment_Current.GetEnumerator())
            {
                while (enumerator.MoveNext())
                {
                    Base_Department item = enumerator.Current;
                    org_treedata _treedata1 = new org_treedata {
                        id = item.DepartmentCode,
                        text = item.DepartmentName,
                        iconCls = "icon-standard-note",
                        state = "open",
                        children = new List<org_treedata>()
                    };
                    org_attributes _attributes1 = new org_attributes {
                        org_type = 9,
                        org_code = item.DepartmentCode,
                        org_pcode = item.ParentCode,
                        org_fcode = item.CompanyCode,
                        org_sort = item.Sort,
                        org_enabled = item.Enabled,
                        org_remark = item.Remark
                    };
                    _treedata1.attributes = _attributes1;
                    org_treedata _treedata = _treedata1;
                    List<Base_Department> list2 = listDepartment.FindAll(p => p.ParentCode == item.DepartmentCode);
                    if (list2.Count > 0)
                    {
                        _treedata.children = this.GetTreeData_ChildrenDepartment(listDepartment, list2);
                    }
                    list.Add(_treedata);
                }
            }
            return list;
        }

        public static Base_CompanyService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

