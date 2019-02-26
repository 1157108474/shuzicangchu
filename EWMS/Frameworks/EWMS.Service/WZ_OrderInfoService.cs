namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    using System.Collections.Generic;
    using System.Data;
    using System.Linq;

    public class WZ_OrderInfoService : BaseService<WZ_OrderInfo>
    {
        private static readonly WZ_OrderInfoService _Instance = new WZ_OrderInfoService();

        public DataTable GetDataTable(string patch, ref string errorMessage)
        {
            bool flag = true;
            try
            {
                DataTable dtCGDD = DbHelperOra.GetExcelTable(patch, true, ref errorMessage);
                if (dtCGDD != null)
                {
                    if (dtCGDD.Rows.Count > 0)
                    {
                        int num2;
                        DataSet set = DbHelperOra.Query("SELECT ID,ZTID,EXTENDINT1 FROM BASE_ORGANIZATION");
                        DataSet set2 = DbHelperOra.Query("select ID,Name from BASE_PERSON ");
                        DataSet set3 = DbHelperOra.Query("select  id,name from BASE_PROVIDER ");
                        dtCGDD.Columns.Add("STOCKORGID", typeof(int));
                        dtCGDD.Columns.Add("Creator", typeof(int));
                        dtCGDD.Columns.Add("ProviderID", typeof(int));
                        int count = dtCGDD.Rows.Count;
                        for (int i = 0; i < count; i = num2 + 1)
                        {
                            decimal ERPSTOCKORGID = decimal.Parse(dtCGDD.Rows[i]["库存组织ID"].ToString());
                            DataRow row = set.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<decimal>("EXTENDINT1") == ERPSTOCKORGID)).FirstOrDefault<DataRow>();
                            dtCGDD.Rows[i]["STOCKORGID"] = row.ItemArray[0];
                            DataRow row2 = set2.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<string>("Name") == dtCGDD.Rows[i]["制单人名称"].ToString().Trim())).FirstOrDefault<DataRow>();
                            dtCGDD.Rows[i]["Creator"] = row2.ItemArray[0];
                            DataRow row3 = set3.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<string>("Name") == dtCGDD.Rows[i]["供应商名称"].ToString().Trim())).FirstOrDefault<DataRow>();
                            dtCGDD.Rows[i]["ProviderID"] = row3.ItemArray[0];
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
                        return dtCGDD;
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

        public int InsertData(List<WZ_OrderInfo> data)
        {
            int num = 0;
            if (data.Count > 0)
            {
                foreach (WZ_OrderInfo info in data)
                {
                    num = Instance.Insert(info);
                    if (num < 1)
                    {
                        return num;
                    }
                }
            }
            return num;
        }

        public static WZ_OrderInfoService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

