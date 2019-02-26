namespace EWMS.Service
{
    using EWMS.Entity;
    using System;
    using System.Collections.Generic;

    public class QueryZCGDService
    {
        private static readonly QueryZCGDService _Instance = new QueryZCGDService();

        public List<Query_ZCGD> SelectList()
        {
            List<Query_ZCGD> list1 = new List<Query_ZCGD>();
            Query_ZCGD item = new Query_ZCGD {
                type = "入库单",
                count = 100
            };
            list1.Add(item);
            Query_ZCGD y_zcgd2 = new Query_ZCGD {
                type = "出库单",
                count = 300
            };
            list1.Add(y_zcgd2);
            Query_ZCGD y_zcgd3 = new Query_ZCGD {
                type = "变动单",
                count = 400
            };
            list1.Add(y_zcgd3);
            Query_ZCGD y_zcgd4 = new Query_ZCGD {
                type = "移库单",
                count = 200
            };
            list1.Add(y_zcgd4);
            Query_ZCGD y_zcgd5 = new Query_ZCGD {
                type = "盘点单",
                count = 340
            };
            list1.Add(y_zcgd5);
            return list1;
        }

        public static QueryZCGDService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

