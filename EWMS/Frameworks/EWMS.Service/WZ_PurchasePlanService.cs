namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    using System.Collections.Generic;
    using System.Data;
    using System.Linq;

    public class WZ_PurchasePlanService : BaseService<WZ_PurchasePlan>
    {
        private static readonly WZ_PurchasePlanService _Instance = new WZ_PurchasePlanService();

        public DataTable GetDataTable(string patch, ref string errorMessage)
        {
            bool flag = true;
            try
            {
                DataTable dtCGJH = DbHelperOra.GetExcelTable(patch, true, ref errorMessage);
                if (dtCGJH != null)
                {
                    if (dtCGJH.Rows.Count > 0)
                    {
                        int num2;
                        DataSet set = DbHelperOra.Query("select  ID,Code,Name from BASE_UseDep ");
                        DataSet set2 = DbHelperOra.Query("select ID,Code,Name from BASE_ApplyDep ");
                        dtCGJH.Columns.Add("UseDepID", typeof(int));
                        dtCGJH.Columns.Add("ApplyDepID", typeof(int));
                        int count = dtCGJH.Rows.Count;
                        for (int i = 0; i < count; i = num2 + 1)
                        {
                            DataRow row = set.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<string>("Name") == dtCGJH.Rows[i]["使用单位编码"].ToString().Trim())).FirstOrDefault<DataRow>();
                            if (row != null)
                            {
                                dtCGJH.Rows[i]["UseDepID"] = row.ItemArray[0];
                            }
                            DataRow row2 = set2.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<string>("Name") == dtCGJH.Rows[i]["申报单位编码"].ToString().Trim())).FirstOrDefault<DataRow>();
                            if (row2 != null)
                            {
                                dtCGJH.Rows[i]["ApplyDepID"] = row2.ItemArray[0];
                            }
                            num2 = i;
                        }
                    }
                    else
                    {
                        flag = false;
                        errorMessage = errorMessage + "Excel中没有数据！<br/>";
                    }
                    if (flag)
                    {
                        return dtCGJH;
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

        public int InsertData(List<WZ_PurchasePlan> data)
        {
            int num = 0;
            if (data.Count > 0)
            {
                foreach (WZ_PurchasePlan plan in data)
                {
                    num = Instance.Insert(plan);
                    if (num < 1)
                    {
                        return num;
                    }
                }
            }
            return num;
        }

        public static WZ_PurchasePlanService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

