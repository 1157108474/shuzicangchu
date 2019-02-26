namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using EWMS.Utils;
    using System;
    using System.Collections;
    using System.Collections.Generic;

    public class WZ_SHEETCKDetailService : BaseService<WZ_SHEETCKDetail>
    {
        private static readonly WZ_SHEETCKDetailService _Instance = new WZ_SHEETCKDetailService();

        public int InsertDetails(string json, int creatorid)
        {
            int num = 0;
            try
            {
                List<WZ_SHEETCKDetail> list = new List<WZ_SHEETCKDetail>();
                foreach (Hashtable hashtable in (ArrayList) JSON.Decode(json))
                {
                    string s = Instance.GetDataTable_Fish("select WZSHEETCKDETAIL_SEQUENCE.nextval from dual").Rows[0][0].ToString();
                    WZ_SHEETCKDetail item = new WZ_SHEETCKDetail {
                        ID = int.Parse(s),
                        GUID = Guid.NewGuid().ToString(),
                        SheetId = int.Parse(hashtable["sheetid"].ToString()),
                        SheetDetailId = int.Parse(hashtable["sheetdetailid"].ToString()),
                        CategoryId = int.Parse(hashtable["categoryid"].ToString()),
                        MaterialId = int.Parse(hashtable["materialid"].ToString()),
                        MaterialCode = hashtable["materialcode"].ToString(),
                        MaterialName = Common.GetStr(hashtable["materialname"]),
                        MaterialModel = Common.GetStr(hashtable["materialmodel"]),
                        MaterialBrand = Common.GetStr(hashtable["materialbrand"]),
                        MaterialSpecification = Common.GetStr(hashtable["materialspecification"]),
                        DetailUnit = Common.GetInt(hashtable["detailunit"]),
                        StoreId = Common.GetInt(hashtable["storeid"]),
                        StoreLocationId = Common.GetInt(hashtable["storelocationid"]),
                        StoreLocationCode = Common.GetStr(hashtable["storelocationcode"]),
                        StoreLocationName = Common.GetStr(hashtable["storelocationname"]),
                        Description = Common.GetStr(hashtable["description"]),
                        TagCode = Common.GetStr(hashtable["tagcode"]),
                        PlanDepartID = Common.GetInt(hashtable["plandepartid"]),
                        NotaxPrice = Common.GetDecimal(hashtable["notaxprice"]),
                        TaxRate = Common.GetDecimal(hashtable["taxrate"]),
                        DetailCount = Common.GetDecimal(hashtable["detailcount"]),
                        ProviderDepId = Common.GetInt(hashtable["providerdepid"]),
                        ExtendString1 = Common.GetStr(hashtable["extendstring1"]),
                        SNCode = Common.GetStr(hashtable["sncode"]),
                        Creator = creatorid,
                        CreateDate = DateTime.Now,
                        ExtendInt1 = Common.GetInt(hashtable["extendint1"])
                    };
                    if (hashtable["sheetdetailid"] != null)
                    {
                        item.SheetDetailId = int.Parse(hashtable["sheetdetailid"].ToString());
                    }
                    if (hashtable["ztid"] != null)
                    {
                        item.ZTID = Convert.ToInt32(hashtable["ztid"]);
                    }
                    if (hashtable["sncode"] != null)
                    {
                        item.SNCode = hashtable["sncode"].ToString();
                    }
                    if (hashtable["detailunitname"] != null)
                    {
                        item.DetailUnitName = hashtable["detailunitname"].ToString();
                    }
                    if (hashtable["materialmodel"] != null)
                    {
                        item.MaterialModel = hashtable["materialmodel"].ToString();
                    }
                    if (hashtable["materialspecification"] != null)
                    {
                        item.MaterialSpecification = hashtable["materialspecification"].ToString();
                    }
                    if (hashtable["providerdepid"] != null)
                    {
                        item.ProviderDepId = Convert.ToInt32(hashtable["providerdepid"]);
                    }
                    if (hashtable["detailcount"] != null)
                    {
                        item.DetailCount = Convert.ToInt32(hashtable["detailcount"]);
                    }
                    if (hashtable["materialbrand"] != null)
                    {
                        item.MaterialBrand = hashtable["materialbrand"].ToString();
                    }
                    if (hashtable["detailunit"] != null)
                    {
                        item.DetailUnit = int.Parse(hashtable["detailunit"].ToString());
                    }
                    if (hashtable["storeid"] != null)
                    {
                        item.StoreId = int.Parse(hashtable["storeid"].ToString());
                    }
                    if (hashtable["storelocationname"] != null)
                    {
                        item.StoreLocationName = hashtable["storelocationname"].ToString();
                    }
                    if (hashtable["tagcode"] != null)
                    {
                        item.TagCode = hashtable["tagcode"].ToString();
                    }
                    if (hashtable["plandepartid"] != null)
                    {
                        item.PlanDepartID = int.Parse(hashtable["plandepartid"].ToString());
                    }
                    if (hashtable["taxprice"] != null)
                    {
                        item.TaxPrice = decimal.Parse(hashtable["taxprice"].ToString());
                    }
                    if (hashtable["extendint2"] != null)
                    {
                        item.ExtendInt2 = Convert.ToInt32(hashtable["extendint2"]);
                    }
                    if (hashtable["extendint5"] != null)
                    {
                        item.ExtendInt5 = Convert.ToInt32(hashtable["extendint5"]);
                    }
                    if (hashtable["ownertype"] != null)
                    {
                        item.OwnerType = Convert.ToInt32(hashtable["ownertype"]);
                    }
                    list.Add(item);
                }
                foreach (WZ_SHEETCKDetail detail2 in list)
                {
                    num = Instance.Insert(detail2);
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

        public static WZ_SHEETCKDetailService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

