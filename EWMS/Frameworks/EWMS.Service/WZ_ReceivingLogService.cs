namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    using System.Collections.Generic;

    public class WZ_ReceivingLogService : BaseService<WZ_ReceivingLog>
    {
        private static readonly WZ_ReceivingLogService _Instance = new WZ_ReceivingLogService();

        public int AddLog(List<WZ_ReceivingLog> logList)
        {
            int num = 0;
            WZ_ReceivingLog data = null;
            try
            {
                foreach (WZ_ReceivingLog log2 in logList)
                {
                    data = new WZ_ReceivingLog {
                        GUID = Guid.NewGuid().ToString(),
                        OperationType = 2,
                        OrderID = log2.OrderID,
                        RelationGUID = log2.RelationGUID,
                        Count = log2.Count,
                        CreateDate = DateTime.Now,
                        Creater = base.CurrentBaseLoginer.UserId,
                        Content = log2.Content,
                        ExtendInt1 = log2.ExtendInt1
                    };
                    if (Instance.Insert(data) > 0)
                    {
                        object[] objArray1 = new object[] { "update wz_orderinfo set extendfloat1=extendfloat1+", log2.Count, " where id=", log2.OrderID };
                        string sql = string.Concat(objArray1);
                        num = Instance.ExecuteNonQuery_Fish(sql);
                    }
                }
            }
            catch (Exception)
            {
            }
            return num;
        }

        public static WZ_ReceivingLogService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

