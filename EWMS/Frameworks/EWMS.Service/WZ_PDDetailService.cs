namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using EWMS.Utils;
    using System;
    using System.Collections;
    using System.Collections.Generic;

    public class WZ_PDDetailService : BaseService<WZ_PDDetail>
    {
        private static readonly WZ_PDDetailService _Instance = new WZ_PDDetailService();

        public int BatchUpdatePD(string ids)
        {
            int num = 0;
            object[] objArray1 = new object[] { "update WZ_PDDetail set StockStatus=1,DETAILCOUNT=SYSCOUNT,StockDate=to_date('", DateTime.Now, "','yyyy-mm-dd hh24:mi:ss'),StockResult=0,STOCKMAN=", base.CurrentBaseLoginer.UserId, " where id in (", ids, ")" };
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

        public int InsertData(List<WZ_PDDetail> data)
        {
            int num = 0;
            foreach (WZ_PDDetail detail in data)
            {
                num = Instance.Insert(detail);
                if (num < 1)
                {
                    return num;
                }
            }
            return num;
        }

        public int UpdatePD(string json)
        {
            int num = 0;
            try
            {
                new List<WZ_PDDetail>();
                int num2 = -1;
                int num3 = 0;
                decimal num4 = new decimal();
                decimal num5 = new decimal();
                foreach (Hashtable hashtable1 in (ArrayList) JSON.Decode(json))
                {
                    num2 = Convert.ToInt32(hashtable1["ID"].ToString());
                    num4 = Convert.ToDecimal(hashtable1["DETAILCOUNT"]);
                    num5 = Convert.ToDecimal(hashtable1["KCCOUNT"]);
                }
                if (num4 > num5)
                {
                    num3 = 1;
                }
                else if (num4 < num5)
                {
                    num3 = -1;
                }
                object[] objArray1 = new object[] { "update WZ_PDDetail set StockStatus=1,syscount='", num5, "',DETAILCOUNT='", num4, "',StockDate=to_date('", DateTime.Now, "','yyyy-mm-dd hh24:mi:ss'),STOCKMAN=", base.CurrentBaseLoginer.UserId, ",StockResult=", num3, " where id=", num2 };
                string sql = string.Concat(objArray1);
                num = base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -2;
            }
            return num;
        }

        public static WZ_PDDetailService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

