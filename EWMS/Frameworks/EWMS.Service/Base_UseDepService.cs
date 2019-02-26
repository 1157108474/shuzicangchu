namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using EWMS.Utils;
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Data;
    using System.Linq;

    public class Base_UseDepService : BaseService<Base_UseDep>
    {
        private static readonly Base_UseDepService _Instance = new Base_UseDepService();

        public DataTable GetDataTable(string patch, ref string errorMessage)
        {
            bool flag = true;
            try
            {
                DataTable table = DbHelperOra.GetExcelTable(patch, true, ref errorMessage);
                if (table != null)
                {
                    if (table.Rows.Count > 0)
                    {
                        DataSet set = DbHelperOra.Query("SELECT ID,ZTID,EXTENDINT1 FROM BASE_ORGANIZATION");
                        table.Columns.Add("ZTID", typeof(int));
                        table.Columns.Add("ORGANIZATIONTYPE", typeof(int));
                        int count = table.Rows.Count;
                        for (int i = 0; i < count; i++)
                        {
                            decimal ID = decimal.Parse(table.Rows[i]["组织ID"].ToString());
                            DataRow row = set.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<decimal>("EXTENDINT1") == ID)).FirstOrDefault<DataRow>();
                            table.Rows[i]["组织ID"] = row.ItemArray[0];
                            table.Rows[i]["ZTID"] = row.ItemArray[1];
                            if (table.Rows[i]["使用单位名称"].ToString().Contains("科") || table.Rows[i]["使用单位名称"].ToString().Contains("室"))
                            {
                                table.Rows[i]["ORGANIZATIONTYPE"] = 2;
                            }
                            else
                            {
                                table.Rows[i]["ORGANIZATIONTYPE"] = 1;
                            }
                        }
                    }
                    else
                    {
                        flag = false;
                        errorMessage = errorMessage + "Excel中没有数据！<br/>";
                    }
                    if (flag)
                    {
                        return table;
                    }
                }
                return null;
            }
            catch (Exception exception)
            {
                errorMessage = errorMessage + exception.Message;
                return null;
            }
        }

        public int Insertdata(string data, int creator)
        {
            int num = 0;
            DataSet set = DbHelperOra.Query("Select ID,PARENTID,EXTENDINT2 from BASE_ORGANIZATION");
            foreach (Hashtable hashtable in (ArrayList) JSON.Decode(data))
            {
                object obj2 = hashtable["Code"];
                int num2 = 0;
                object obj3 = hashtable["Name"];
                object OrganizationID = hashtable["OrganizationID"];
                decimal num3 = new decimal();
                object obj4 = hashtable["OrganizationType"];
                object obj5 = hashtable["Memo"];
                object obj6 = hashtable["Status"];
                string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                DataRow row = set.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<decimal>("ID") == Convert.ToDecimal(OrganizationID))).FirstOrDefault<DataRow>();
                if (row.ItemArray[2].ToString() == "1")
                {
                    num3 = Convert.ToDecimal(OrganizationID);
                }
                else
                {
                    for (int i = 0; i < 5; i++)
                    {
                        decimal parentID = Convert.ToDecimal(row.ItemArray[1]);
                        row = set.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<decimal>("ID") == parentID)).FirstOrDefault<DataRow>();
                        if ((row != null) && (row.ItemArray[2].ToString() == "1"))
                        {
                            num3 = Convert.ToDecimal(row.ItemArray[0]);
                            break;
                        }
                        if (parentID == decimal.Zero)
                        {
                            break;
                        }
                    }
                }
                string sql = "select * from Base_UseDep where code='" + obj2 + "'";
                if (base.GetDataTable_Fish(sql).Rows.Count > 0)
                {
                    num = 2;
                }
                else
                {
                    object obj7 = Instance.GetDataTable_Fish("select Base_UseDep_SEQUENCE.nextval from dual").Rows[0][0];
                    object[] objArray1 = new object[] { 
                        "insert into Base_UseDep (ID,Code,Name,ERPID,OrganizationID,ZTID,OrganizationType,Memo,Status,creator,createdate ) values (", obj7, ",'", obj2, "','", obj3, "',", num2, ",", OrganizationID, ",", num3, ",", obj4, ",'", obj5,
                        "',", obj6, ",", creator, ",to_date('", str, "','yyyy-mm-dd hh24:mi:ss'))"
                    };
                    string str3 = string.Concat(objArray1);
                    try
                    {
                        num = base.ExecuteNonQuery_Fish(str3);
                    }
                    catch (Exception)
                    {
                        num = -2;
                    }
                }
            }
            return num;
        }

        public int InsertData(List<Base_UseDep> data)
        {
            int num = 0;
            if (data.Count > 0)
            {
                foreach (Base_UseDep dep in data)
                {
                    num = Instance.Insert(dep);
                    if (num < 1)
                    {
                        return num;
                    }
                }
            }
            return num;
        }

        public int Updatedata(string data, int updater)
        {
            int num = 0;
            DataSet set = DbHelperOra.Query("Select ID,PARENTID,EXTENDINT2 from BASE_ORGANIZATION");
            foreach (Hashtable hashtable in (ArrayList) JSON.Decode(data))
            { 
                object obj2 = hashtable["Code"];
                object obj3 = hashtable["ID"];
                object obj4 = hashtable["Name"];
                object OrganizationID = hashtable["OrganizationID"];
                decimal num2 = new decimal();
                object obj5 = hashtable["OrganizationType"];
                object obj6 = hashtable["Memo"];
                object obj7 = hashtable["Status"];
                string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                DataRow row = set.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<decimal>("ID") == Convert.ToDecimal(OrganizationID))).FirstOrDefault<DataRow>();
                decimal parentID = Convert.ToDecimal(row.ItemArray[1]);
                if (row.ItemArray[2].ToString() == "1")
                {
                    num2 = Convert.ToDecimal(OrganizationID);
                }
                else if (parentID > decimal.Zero)
                {
                    for (int i = 0; i < 5; i++)
                    {
                        row = set.Tables[0].AsEnumerable().Where<DataRow>(x => x.Field<decimal>("ID") == parentID).FirstOrDefault<DataRow>();
                        if ((row != null) && (row.ItemArray[2].ToString() == "1"))
                        {
                            num2 = Convert.ToDecimal(row.ItemArray[0]);
                            break;
                        }
                    }
                }
                object[] objArray1 = new object[] { "select * from Base_UseDep where code='", obj2, "' and id <>", obj3 };
                string sql = string.Concat(objArray1);
                if (base.GetDataTable_Fish(sql).Rows.Count > 0)
                {
                    num = 2;
                }
                else
                {
                    object[] objArray2 = new object[] { 
                        "update Base_UseDep set Name='", obj4, "',OrganizationID=", OrganizationID, ",ZTID=", num2, ",OrganizationType=", obj5, ", Memo='", obj6, "',Status=", obj7, ",updater=", updater, ",updatedate=to_date('", str,
                        "','yyyy-mm-dd hh24:mi:ss') where id=", obj3
                    };
                    string str3 = string.Concat(objArray2);
                    try
                    {
                        num = base.ExecuteNonQuery_Fish(str3);
                    }
                    catch (Exception)
                    {
                        num = -2;
                    }
                }
            }
            return num;
        }

        public static Base_UseDepService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

