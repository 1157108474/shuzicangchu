namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using EWMS.Utils;
    using System;
    using System.Collections;
    using System.Collections.Generic;

    public class WZ_SheetDetailService : BaseService<WZ_SheetDetail>
    {
        private static readonly WZ_SheetDetailService _Instance = new WZ_SheetDetailService();

        public int InsertData(List<WZ_SheetDetail> data)
        {
            int num = 0;
            foreach (WZ_SheetDetail detail in data)
            {
                num = Instance.Insert(detail);
                if (num < 1)
                {
                    return num;
                }
            }
            return num;
        }

        public int InsertDetails(string json, int creatorid, int tem)
        {
            int num = 0;
            try
            {
                ArrayList list = (ArrayList) JSON.Decode(json);
                List<WZ_SheetDetail> list2 = new List<WZ_SheetDetail>();
                if (tem == 1)
                {
                    foreach (Hashtable hashtable in list)
                    {
                        string s = Instance.GetDataTable_Fish("select WZSHEETCKDETAIL_SEQUENCE.nextval from dual").Rows[0][0].ToString();
                        WZ_SheetDetail item = new WZ_SheetDetail {
                            ID = int.Parse(s),
                            GUID = Guid.NewGuid().ToString(),
                            SheetId = int.Parse(hashtable["sheetid"].ToString()),
                            SheetDetailId = int.Parse(hashtable["sheetdetailid"].ToString()),
                            SNCode = hashtable["sncode"].ToString(),
                            MaterialCode = hashtable["materialcode"].ToString(),
                            DetailUnitName = hashtable["detailunitname"].ToString(),
                            ExtendDate1 = Convert.ToDateTime(hashtable["extenddate1"]),
                            DetailCount = Convert.ToDecimal(hashtable["DetailCount"]),
                            ExtendString2 = hashtable["extendstring2"].ToString(),
                            Description = hashtable["description"].ToString(),
                            ZTID = Convert.ToInt32(hashtable["ztid"]),
                            Creator = creatorid,
                            CreateDate = DateTime.Now
                        };
                        if (hashtable["extendstring3"] != null)
                        {
                            item.ExtendString3 = hashtable["extendstring3"].ToString();
                        }
                        list2.Add(item);
                    }
                }
                else
                {
                    foreach (Hashtable hashtable2 in list)
                    {
                        string str2 = Instance.GetDataTable_Fish("select WZSHEETCKDETAIL_SEQUENCE.nextval from dual").Rows[0][0].ToString();
                        WZ_SheetDetail detail2 = new WZ_SheetDetail {
                            ID = int.Parse(str2),
                            GUID = Guid.NewGuid().ToString(),
                            SheetId = int.Parse(hashtable2["sheetid"].ToString()),
                            SheetDetailId = 0,
                            MaterialCode = hashtable2["materialcode"].ToString(),
                            MaterialId = Convert.ToInt32(hashtable2["materialid"]),
                            Description = hashtable2["description"].ToString(),
                            DetailUnitName = hashtable2["detailunitname"].ToString(),
                            DetailUnit = Convert.ToInt32(hashtable2["detailunit"]),
                            DetailCount = Convert.ToDecimal(hashtable2["DetailCount"]),
                            ExtendString2 = hashtable2["extendstring2"].ToString(),
                            Creator = creatorid,
                            CreateDate = DateTime.Now,
                            ZTID = Convert.ToInt32(hashtable2["ztid"])
                        };
                        list2.Add(detail2);
                    }
                }
                foreach (WZ_SheetDetail detail3 in list2)
                {
                    num = Instance.Insert(detail3);
                    if (num < 1)
                    {
                        return num;
                    }
                }
                return num;
            }
            catch (Exception)
            {
                num = -2;
            }
            return num;
        }

        public int InsertYWDetails(string json, int creator, int sheetid, string house, string area)
        {
            return 0;
        }

        public int UpdateBGDetail(List<WZ_SheetDetail> data, string ids)
        {
            return 0;
        }

        public int UpdateDetail(ParamUpdate param)
        {
            return Instance.Update(param);
        }

        public static WZ_SheetDetailService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

