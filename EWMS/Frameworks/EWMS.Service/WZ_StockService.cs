namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    using System.Collections.Generic;
    using System.Data;
    using System.Linq;
    

    public class WZ_StockService : BaseService<WZ_Stock>
    {
        private static readonly WZ_StockService _Instance = new WZ_StockService();
        private List<Base_Dictionary> diclist = WZ_SHEET_RKService.Instance.GetAllDic();

        public List<ViewStock> GetAllStock()
        {
            return new List<ViewStock>();
        }

        public DataTable GetDataTable(string patch, ref string errorMessage)
        {
            bool flag = true;
            try
            {
                DataTable dtStock = DbHelperOra.GetExcelTable(patch, true, ref errorMessage);
                if (dtStock != null)
                {
                    if (dtStock.Rows.Count > 0)
                    {
                        int num7;
                        DataSet set = DbHelperOra.Query("SELECT ID,CODE,NAME,BRAND,SPECIFICATIONS,MODELS,UNIT,DESCRIPTION,SPARESCATEID FROM BASE_MATERIAL");
                        DataSet set2 = DbHelperOra.Query("SELECT ID,CODE,NAME FROM BASE_WAREHOUSE");
                        DataSet set3 = DbHelperOra.Query("SELECT ID,CODE,NAME FROM BASE_ORGANIZATION");
                        DataSet set4 = DbHelperOra.Query("SELECT ID,CODE,NAME FROM BASE_PROVIDER");
                        dtStock.Columns.Add(new DataColumn("物料ID", typeof(int)));
                        dtStock.Columns.Add(new DataColumn("物料品牌", typeof(string)));
                        dtStock.Columns.Add(new DataColumn("物料规格", typeof(string)));
                        dtStock.Columns.Add(new DataColumn("物料型号", typeof(string)));
                        dtStock.Columns.Add(new DataColumn("物料计量单位", typeof(string)));
                        dtStock.Columns.Add(new DataColumn("库房ID", typeof(int)));
                        dtStock.Columns.Add(new DataColumn("供应商ID", typeof(int)));
                        dtStock.Columns.Add(new DataColumn("物料描述", typeof(string)));
                        dtStock.Columns.Add(new DataColumn("材料打印编码", typeof(string)));
                        dtStock.Columns.Add(new DataColumn("库位名称", typeof(string)));
                        dtStock.Columns.Add(new DataColumn("计划部门ID", typeof(int)));
                        dtStock.Columns.Add(new DataColumn("库位ID", typeof(int)));
                        dtStock.Columns.Add(new DataColumn("分类ID", typeof(int)));
                        int count = dtStock.Rows.Count;
                        for (int i = 0; i < count; i = num7 + 1)
                        {
                            bool flag2 = true;
                            int result = 0;
                            decimal num3 = new decimal();
                            DateTime time = new DateTime();
                            if (string.IsNullOrEmpty(dtStock.Rows[i][0].ToString()))
                            {
                                flag = false;
                                object[] objArray1 = new object[] { errorMessage, "第", i + 1, "行:“物料编码”不能为空.<br/>" };
                                errorMessage = string.Concat(objArray1);
                            }
                            else
                            {
                                string materialCode = dtStock.Rows[i][0].ToString();
                                if (set.Tables[0].AsEnumerable().Count<DataRow>(x => (x.Field<string>("CODE") == materialCode)) == 0)
                                {
                                    object[] objArray2 = new object[] { errorMessage, "第", i + 1, "行:数据库中不存在此“物料编码”.<br/>" };
                                    errorMessage = string.Concat(objArray2);
                                }
                                else
                                {
                                    int num4 = 0;
                                    int.TryParse(DbHelperOra.GetSingle("SELECT COUNT(1) FROM WZ_STOCK WHERE MaterialCode='" + materialCode + "'").ToString(), out num4);
                                    int num5 = dtStock.AsEnumerable().Count<DataRow>(x => x.Field<string>("物料编码") == materialCode);
                                    int num6 = dtStock.AsEnumerable().Where<DataRow>(x => (x.Field<string>("物料编码") == materialCode)).Count<DataRow>(x => x.Field<string>("材料打印编码").IsNullOrEmpty());
                                    num4 = ((num4 + 1) + num5) - num6;
                                    DataRow row = set.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<string>("CODE") == materialCode)).FirstOrDefault<DataRow>();
                                    dtStock.Rows[i]["物料ID"] = row.ItemArray[0];
                                    dtStock.Rows[i][1] = row.ItemArray[2];
                                    dtStock.Rows[i]["物料品牌"] = row.ItemArray[3];
                                    dtStock.Rows[i]["物料规格"] = row.ItemArray[4];
                                    dtStock.Rows[i]["物料型号"] = row.ItemArray[5];
                                    dtStock.Rows[i]["物料描述"] = row.ItemArray[7];
                                    dtStock.Rows[i]["材料打印编码"] = materialCode + num4.ToString().PadLeft(6, '0');
                                    dtStock.Rows[i]["分类ID"] = row.ItemArray[8];
                                }
                            }
                            if (!string.IsNullOrEmpty(dtStock.Rows[i][3].ToString()) && !decimal.TryParse(dtStock.Rows[i][3].ToString(), out num3))
                            {
                                flag = false;
                                object[] objArray3 = new object[] { errorMessage, "第", i, 1, "行:“税率”应为数字.<br/>" };
                                errorMessage = string.Concat(objArray3);
                            }
                            if (string.IsNullOrEmpty(dtStock.Rows[i][4].ToString()))
                            {
                                flag = false;
                                object[] objArray4 = new object[] { errorMessage, "第", i + 1, "行:“数量”不能为空.<br/>" };
                                errorMessage = string.Concat(objArray4);
                            }
                            else if (!int.TryParse(dtStock.Rows[i][4].ToString(), out result))
                            {
                                flag = false;
                                object[] objArray5 = new object[] { errorMessage, "第", i + 1, "行:“数量”应为数字.<br/>" };
                                errorMessage = string.Concat(objArray5);
                            }
                            if (string.IsNullOrEmpty(dtStock.Rows[i][5].ToString()))
                            {
                                flag = false;
                                object[] objArray6 = new object[] { errorMessage, "第", i + 1, "行:“单位”不能为空.<br/>" };
                                errorMessage = string.Concat(objArray6);
                            }
                            else
                            {
                                dtStock.Rows[i]["物料计量单位"] = dtStock.Rows[i][5].ToString();
                            }
                            if (!string.IsNullOrEmpty(dtStock.Rows[i][6].ToString()) && !decimal.TryParse(dtStock.Rows[i][6].ToString(), out num3))
                            {
                                flag = false;
                                object[] objArray7 = new object[] { errorMessage, "第", i + 1, "行:“含税单价”应为数字.<br/>" };
                                errorMessage = string.Concat(objArray7);
                            }
                            if (!string.IsNullOrEmpty(dtStock.Rows[i][7].ToString()) && !decimal.TryParse(dtStock.Rows[i][7].ToString(), out num3))
                            {
                                flag = false;
                                object[] objArray8 = new object[] { errorMessage, "第", i + 1, "行:“不含税单价”应为数字.<br/>" };
                                errorMessage = string.Concat(objArray8);
                            }
                            if (string.IsNullOrEmpty(dtStock.Rows[i][8].ToString()))
                            {
                                flag = false;
                                object[] objArray9 = new object[] { errorMessage, "第", i + 1, "行:“库房名称”不能为空.<br/>" };
                                errorMessage = string.Concat(objArray9);
                            }
                            else if (set2.Tables[0].AsEnumerable().Count<DataRow>(x => (x.Field<string>("NAME") == dtStock.Rows[i][8].ToString())) == 0)
                            {
                                flag2 = false;
                                flag = false;
                                object[] objArray10 = new object[] { errorMessage, "第", i + 1, "行:数据库中不存在此“库房名称”.<br/>" };
                                errorMessage = string.Concat(objArray10);
                            }
                            else
                            {
                                DataRow row2 = set2.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<string>("NAME") == dtStock.Rows[i][8].ToString())).FirstOrDefault<DataRow>();
                                dtStock.Rows[i]["库房ID"] = row2.ItemArray[0];
                            }
                            if (flag2 && !string.IsNullOrEmpty(dtStock.Rows[i][9].ToString()))
                            {
                                if (set2.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<string>("NAME") != dtStock.Rows[i][8].ToString())).Where<DataRow>(x => (x.Field<string>("CODE") == dtStock.Rows[i][9].ToString())).Count<DataRow>() == 0)
                                {
                                    flag = false;
                                    object[] objArray11 = new object[] { errorMessage, "第", i + 1, "行:库房中不存在此“库位编号”.<br/>" };
                                    errorMessage = string.Concat(objArray11);
                                }
                                else
                                {
                                    DataRow row3 = set2.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<string>("CODE") == dtStock.Rows[i][9].ToString())).FirstOrDefault<DataRow>();
                                    dtStock.Rows[i]["库位ID"] = row3.ItemArray[0];
                                    dtStock.Rows[i]["库位名称"] = row3.ItemArray[2];
                                }
                            }
                            if (!string.IsNullOrEmpty(dtStock.Rows[i][10].ToString()))
                            {
                                if (set3.Tables[0].AsEnumerable().Count<DataRow>(x => (x.Field<string>("NAME") == dtStock.Rows[i][10].ToString())) == 0)
                                {
                                    flag = false;
                                    object[] objArray12 = new object[] { errorMessage, "第", i + 1, "行:数据库中不存在此“计划部门”.<br/>" };
                                    errorMessage = string.Concat(objArray12);
                                }
                                else
                                {
                                    DataRow row4 = set3.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<string>("NAME") == dtStock.Rows[i][10].ToString())).FirstOrDefault<DataRow>();
                                    dtStock.Rows[i]["计划部门ID"] = row4.ItemArray[0];
                                }
                            }
                            if (!string.IsNullOrEmpty(dtStock.Rows[i][11].ToString()) && !DateTime.TryParse(dtStock.Rows[i][11].ToString(), out time))
                            {
                                flag = false;
                                object[] objArray13 = new object[] { errorMessage, "第", i + 1, "行:“质保到期时间”应为日期.<br/>" };
                                errorMessage = string.Concat(objArray13);
                            }
                            if (!string.IsNullOrEmpty(dtStock.Rows[i][12].ToString()) && !DateTime.TryParse(dtStock.Rows[i][12].ToString(), out time))
                            {
                                flag = false;
                                object[] objArray14 = new object[] { errorMessage, "第", i + 1, "行:“入库时间”应为日期.<br/>" };
                                errorMessage = string.Concat(objArray14);
                            }
                            if (!string.IsNullOrEmpty(dtStock.Rows[i][13].ToString()))
                            {
                                if (set4.Tables[0].AsEnumerable().Count<DataRow>(x => (x.Field<string>("NAME") == dtStock.Rows[i][13].ToString())) == 0)
                                {
                                    flag = false;
                                    object[] objArray15 = new object[] { errorMessage, "第", i + 1, "行:数据库中不存在此“供应商”.<br/>" };
                                    errorMessage = string.Concat(objArray15);
                                }
                                else
                                {
                                    DataRow row5 = set4.Tables[0].AsEnumerable().Where<DataRow>(x => (x.Field<string>("NAME") == dtStock.Rows[i][13].ToString())).FirstOrDefault<DataRow>();
                                    dtStock.Rows[i]["供应商ID"] = row5.ItemArray[0];
                                }
                            }
                            num7 = i;
                        }
                    }
                    else
                    {
                        flag = false;
                        errorMessage = errorMessage + "Excel中没有数据！<br/>";
                    }
                    if (flag)
                    {
                        return dtStock;
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

        public DataTable getSummary(string where)
        {
            string sql = "select count(*) as traycount,sum(storecount) as cpcount,sum(totalweight) as cpweight from wz_stock where STORECOUNT>0 and traycode is not null";
            sql = sql + where;
            return base.GetDataTable_Fish(sql);
        }

        public List<WZ_StockTJ> GetTypeWeight()
        {
            return new List<WZ_StockTJ>();
        }

        public int InsertData(List<WZ_Stock> data)
        {
            int num = 0;
            if (data.Count > 0)
            {
                foreach (WZ_Stock stock in data)
                {
                    num = Instance.Insert(stock);
                    if (num < 1)
                    {
                        return num;
                    }
                }
            }
            return num;
        }

        public string ReviseErrorMessage(string oldMessage, string newMessage)
        {
            string str = string.Empty;
            if (string.IsNullOrEmpty(oldMessage))
            {
                return (newMessage + "</br>");
            }
            return str;
        }

        public int SetCK(string where, int set)
        {
            where = string.IsNullOrEmpty(where) ? " 1=1 " : (" 1=1 " + where);
            string sql = "";
            if (set == 0)
            {
                sql = string.Format("update wz_stock set Iscontrol=0,isoutstore=1 where {0}", where);
            }
            else
            {
                sql = string.Format("update wz_stock set Iscontrol=1,isoutstore=0 where {0}", where);
            }
            return base.db.Sql(sql, null).Execute();
        }

        public static WZ_StockService Instance
        {
            get
            {
                return _Instance;
            }
        }
        
    }
}

