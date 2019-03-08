namespace EWMS.WinService.Job
{
    using EWMS.WinService;
    using EWMS.Entity;
    using EWMS.Service;
    using log4net;
    using Newtonsoft.Json;
    using Quartz;
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Runtime.CompilerServices;
    using System.Text;
    using System.Xml;
    using System.Configuration;

    [DisallowConcurrentExecution]
    public sealed class SynchBusinessData : IJob
    {
        private readonly ILog _logger = LogManager.GetLogger(typeof(SynchBusinessData));
        public string dateT = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
        private static int min = int.Parse(ConfigurationManager.AppSettings["10Min"].ToString());
        public string dateF = DateTime.Now.AddMinutes((double)(0 - min)).ToString("yyyy-MM-dd HH:mm:ss");
        public dynamic service = null;
        public bool isLog = false;
        public void Execute(IJobExecutionContext context)
        {
            if (Common.IsProductWebService)
                service = Common.InitProductWMSClient();
            else
                service = Common.InitWMSClient();
            this._logger.InfoFormat("SynchBusinessData(DateF:{0},DateT:{1})", dateF, dateT);
            this.SynchOrderInfo();
            this.SynchPurchasePlan();
            //this.SynchCKCostUpdate();
           // this.SynchStockCostUpdate();
            //this.SynchOffsetInfo();
            //this.SynchAssetCategory();
            //if (service != null)
            //    service.Close();
        }

        public bool SynchAssetCategory()
        {
            XmlDocument document = new XmlDocument();
            int num = 0;
            string str = string.Empty;
            string str2 = string.Empty;
            string str3 = string.Empty;
            string str4 = "10000";
            string str5 = "21324";
            StringBuilder builder = new StringBuilder();
            builder.Append(string.Format("<WSINTERFACE> <HEADER> <DATE_FR>{0}</DATE_FR> <DATE_TO>{1}</DATE_TO> <CATEGORY_CODE>{2}</CATEGORY_CODE> </HEADER> </WSINTERFACE>", this.dateF, this.dateT, str5));
            string str6 = Base64Encrypt.Encrypt("utf-8", builder.ToString());
            string str7 = "同步资产编码信息接口,接口名称:invokews";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步资产编码信息'").ID;
            string str8 = string.Format("固定值:CUXYXINVASS;batchNum:{0};requestData：{1}", str4, str6);
            try
            {
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str6, 1, "开始调用接口");
                this.service.invokews("CUXYXINVASS", str4, str6, ref str2, ref str, ref str3);
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str3, 1, "接口返回结果");

                if (str.IsNullOrEmpty())
                {
                    str3 = Base64Encrypt.Decrypt("utf-8", str3);
                    document.LoadXml(str3);
                    XmlNodeList childNodes = document.SelectSingleNode("WSINTERFACE").ChildNodes;
                    foreach (System.Xml.XmlNode node2 in childNodes)
                    {
                        string str9 = JsonConvert.SerializeXmlNode(node2);
                        str8 = str9;
                        string str10 = string.Empty;
                        bool flag2 = false;
                        Base_Category_Asset data = new Base_Category_Asset();
                        int num4 = 0;
                        string innerText = node2.SelectSingleNode("CATEGORY_CODE").InnerText;
                        string str12 = node2.SelectSingleNode("ASSET_NUMBER").InnerText;
                        string[] textArray1 = new string[] { " and CategoryCode='", innerText, "' and AssetCode='", str12, "'" };
                        Base_Category_Asset asset2 = Base_Category_AssetService.Instance.GetEntity_Fish(string.Concat(textArray1));
                        if (asset2 != null)
                        {
                            num4 = asset2.ID;
                            flag2 = true;
                        }
                        else
                        {
                            num4 = Convert.ToInt32(Base_Category_AssetService.Instance.GetDataTable_Fish("select BASE_CATEGORY_ASSET_SEQUENCE.nextval from dual").Rows[0][0]);
                            asset2 = new Base_Category_Asset();
                        }
                        data.ID = num4;
                        Base_SparepartsCate cate = Base_SparepartsCateService.Instance.GetEntity_Fish(" and code='" + innerText + "'");
                        if (cate != null)
                        {
                            asset2.CategoryID = data.CategoryID = cate.ID;
                        }
                        else
                        {
                            str10 = str10 + "数据库中不存在此类别编码，编码：" + innerText;
                        }
                        asset2.CategoryCode = data.CategoryCode = innerText;
                        asset2.AssetCode = data.AssetCode = str12;
                        if (str10 == "")
                        {
                            if (flag2)
                            {
                                num = Base_Category_AssetService.Instance.Update(asset2);
                            }
                            else
                            {
                                num = Base_Category_AssetService.Instance.Insert(data);
                            }
                            if (num > 0)
                            {
                                Inf_TaskLogService.WriteLog(str7, num2, iD, str9, 1, "");
                            }
                            else
                            {
                                Inf_TaskLogService.WriteLog(str7, num2, iD, str9, 2, flag2 ? "更新记录失败" : "插入记录失败");
                            }
                        }
                        else
                        {
                            Inf_TaskLogService.WriteLog(str7, num2, iD, str9, 2, str10);
                        }
                    }
                }
                else
                {
                    Inf_TaskLogService.WriteLog(str7, num2, iD, str8, 2, str);
                }
                return true;
            }
            catch (Exception exception)
            {
                this._logger.Error(str7, exception);
                //Inf_TaskLogService.WriteLog(str7, num2, iD, str8, 2, exception.Message);
                return false;
            }
        }

        public bool SynchCKCostUpdate()
        {
            XmlDocument document = new XmlDocument();
            string str = string.Empty;
            string str2 = string.Empty;
            string str3 = string.Empty;
            string str4 = "10000";
            XmlDocument document2 = new XmlDocument();
            XmlDeclaration newChild = document2.CreateXmlDeclaration("1.0", "utf-8", null);
            document2.AppendChild(newChild);
            XmlElement element = document2.CreateElement("WSINTERFACE");
            List<WZ_SHEETCKDetail> list = WZ_SHEETCKDetailService.Instance.GetList_Fish(" and ExtendInt3<>1", true);
            foreach (WZ_SHEETCKDetail detail in list)
            {
                Base_Organization organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ID=" + detail.ZTID);
                if (organization != null)
                {
                    System.Xml.XmlNode node = document2.CreateElement("HEADER");
                    XmlElement element2 = document2.CreateElement("DATE_FR");
                    element2.InnerText = this.dateF;
                    node.AppendChild(element2);
                    XmlElement element3 = document2.CreateElement("DATE_TO");
                    element3.InnerText = this.dateT;
                    node.AppendChild(element3);
                    XmlElement element4 = document2.CreateElement("ORGANIZATION_ID");
                    element4.InnerText = organization.ExtendInt1.ToString();
                    node.AppendChild(element4);
                    XmlElement element5 = document2.CreateElement("ITEM_NO");
                    element5.InnerText = detail.MaterialCode;
                    node.AppendChild(element5);
                    XmlElement element6 = document2.CreateElement("INTERFACE_CODE");
                    element6.InnerText = "";
                    node.AppendChild(element6);
                    XmlElement element7 = document2.CreateElement("HEADER_ID");
                    element7.InnerText = (detail.SheetId).ToString();  //+ 900000000
                    node.AppendChild(element7);
                    XmlElement element8 = document2.CreateElement("LINE_ID");
                    element8.InnerText = (detail.ID).ToString();
                    node.AppendChild(element8);
                    WZ_SHEET_CK wz_sheet_ck = WZ_SHEET_CKService.Instance.GetEntity_Fish(" and ID=" + detail.SheetId);
                    XmlElement element9 = document2.CreateElement("DOC_NUM");
                    element9.InnerText = wz_sheet_ck.Code;
                    node.AppendChild(element9);
                    element.AppendChild(node);
                }
            }
            document2.AppendChild(element);
            string str5 = Base64Encrypt.Encrypt("utf-8", document2.OuterXml);
            string str6 = "同步出库成本更新接口,接口名称:invokews";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步出库成本更新'").ID;
            string str7 = string.Format("固定值:CUXYXINVMMT;batchNum:{0};requestData：{1}", str4, str5);
            string str8 = "";
            try
            {
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str6, 1, "开始调用接口");
                this.service.invokews("CUXYXINVMMT", str4, str5, ref str2, ref str, ref str3);
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str3, 1, "接口返回结果");

                if (str.IsNullOrEmpty())
                {
                    str3 = Base64Encrypt.Decrypt("utf-8", str3);
                    document.LoadXml(str3);
                    XmlNodeList childNodes = document.SelectSingleNode("WSINTERFACE").ChildNodes;
                    foreach (System.Xml.XmlNode node3 in childNodes)
                    {
                        string str9 = JsonConvert.SerializeXmlNode(node3);
                        str7 = str9;
                        string str10 = string.Empty;
                        XmlElement element10 = (XmlElement)node3;
                        XmlNodeList list3 = element10.ChildNodes;
                        int num5 = Convert.ToInt32(WZ_PurchasePlanService.Instance.GetDataTable_Fish("select BASE_COSTUPDATE_SEQUENCE.nextval from dual").Rows[0][0].ToString());
                        Base_CostUpdate data = new Base_CostUpdate
                        {
                            ID = num5
                        };
                        string innerText = node3.SelectSingleNode("ORGANIZATION_ID").InnerText;
                        Base_Organization organization2 = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + innerText);
                        if (organization2 != null)
                        {
                            data.ORGANZATIONID = organization2.ID;
                        }
                        else
                        {
                            str10 = str10 + " 数据库中不存在此库存组织，ERPUD：" + innerText + ";";
                        }
                        string str12 = node3.SelectSingleNode("ITEM_NO").InnerText;
                        Base_Material material = Base_MaterialService.Instance.GetEntity_Fish(" and Code='" + str12 + "'");
                        if (material != null)
                        {
                            data.MATERIALCODE = material.Code;
                        }
                        else
                        {
                            str10 = str10 + " 数据库中不存在此物料，Code：" + str12 + ";";
                        }
                        data.OLDCOST = decimal.Parse(node3.SelectSingleNode("OLD_COST").InnerText);
                        data.NEWCOST = decimal.Parse(node3.SelectSingleNode("NEW_COST").InnerText);
                        data.UOM = node3.SelectSingleNode("UOM").InnerText;
                        data.QUANTITY = decimal.Parse(node3.SelectSingleNode("QUANTITY").InnerText);
                        data.AMOUNT = decimal.Parse(node3.SelectSingleNode("AMOUNT").InnerText);
                        data.UPDATEDATE = DateTime.Parse(node3.SelectSingleNode("LAST_UPDATE_DATE").InnerText);
                        data.TRANSCATIONID = int.Parse(node3.SelectSingleNode("TRANSACTION_ID").InnerText);
                        data.INTERFACECODE = node3.SelectSingleNode("INTERFACE_CODE").InnerText;
                        data.HEADERID = node3.SelectSingleNode("HEADER_ID").InnerText.IsNullOrEmpty() ? 0 : int.Parse(node3.SelectSingleNode("HEADER_ID").InnerText);
                        data.LINEID = node3.SelectSingleNode("LINE_ID").InnerText.IsNullOrEmpty() ? 0 : int.Parse(node3.SelectSingleNode("LINE_ID").InnerText);
                        data.DOCNUM = node3.SelectSingleNode("DOC_NUM").InnerText;
                        data.TRANSDATE = DateTime.Parse(node3.SelectSingleNode("TRANS_DATE").InnerText);
                        if (string.IsNullOrEmpty(str10))
                        {
                            WZ_SHEETCKDetail detail2 = WZ_SHEETCKDetailService.Instance.GetEntity_Fish("and ID=" + data.LINEID);
                            if (detail2 != null)
                            {
                                if (Base_CostUpdateService.Instance.Insert(data) > 0)
                                {
                                    str8 = (str8.Length > 0) ? (str8 + "," + detail2.SheetId.ToString()) : detail2.SheetId.ToString();
                                    detail2.NotaxPrice = data.NEWCOST;
                                    detail2.NoTaxSum = Math.Round((decimal)(data.NEWCOST * detail2.DetailCount), 2);
                                    detail2.ExtendInt3 = 1;
                                    if (WZ_SHEETCKDetailService.Instance.Update(detail2) > 0)
                                    {
                                        Inf_TaskLogService.WriteLog(str6, num2, iD, str9, 1, "");
                                    }
                                    else
                                    {
                                        Inf_TaskLogService.WriteLog(str6, num2, iD, str9, 2, "更新表失败");
                                    }
                                }
                                else
                                {
                                    Inf_TaskLogService.WriteLog(str6, num2, iD, str9, 2, "插入Base_CostUpdate表失败");
                                }
                            }
                            else
                            {
                                Inf_TaskLogService.WriteLog(str6, num2, iD, str9, 2, string.Format("出库单明细中中不存在此数据,ID:{0}", data.LINEID));
                            }
                        }
                        else
                        {
                            Inf_TaskLogService.WriteLog(str6, num2, iD, str9, 2, str10);
                        }
                    }
                    if (!string.IsNullOrEmpty(str8))
                    {
                        WZ_SHEETCKDetailService.Instance.ExecuteNonQuery_Fish(" update  wz_sheet_ck sheet set (ExtendInt3 )=\r\n                                                                            ( select  (case when count(detail.id)>0 then 0 else 1 end ) notPrice \r\n                                                                            from wz_sheetckdetail  detail\r\n                                                                            where  sheet.id=detail.sheetid and detail.ExtendInt3=0  \r\n                                                                            )  where sheet.id in(" + str8 + ")");
                    }
                }
                else
                {
                    Inf_TaskLogService.WriteLog(str6, num2, iD, str7, 2, str);
                }
                return true;
            }
            catch (Exception exception)
            {
                this._logger.Error(str6, exception);
                //Inf_TaskLogService.WriteLog(str6, num2, iD, str7, 2, exception.Message);
                return false;
            }
        }

        public bool SynchOffsetInfo()
        {
            for (int i = 0; i < 1; i++)
            {
                string str = "";
                //if (i == 0)
                //{
                //    str = "21324";
                //}
                //else if (i == 1)
                //{
                //    str = "328";
                //}
                //else
                //{
                //    str = "329";
                //}
                if (i == 0)
                {
                    str = "21324";
                }
                else if (i == 1)
                {
                    str = "21324";
                }
                else
                {
                    str = "21324";
                }
                XmlDocument document = new XmlDocument();
                string str2 = string.Empty;
                string str3 = string.Empty;
                string str4 = string.Empty;
                string str5 = "10000";
                XmlDocument document2 = new XmlDocument();
                XmlDeclaration newChild = document2.CreateXmlDeclaration("1.0", "utf-8", null);
                document2.AppendChild(newChild);
                XmlElement element = document2.CreateElement("WSINTERFACE");
                System.Xml.XmlNode node = document2.CreateElement("HEADER");
                XmlElement element2 = document2.CreateElement("CREATION_DATE_FR");
                element2.InnerText = "";
                node.AppendChild(element2);
                XmlElement element3 = document2.CreateElement("CREATION_DATE_TO");
                element3.InnerText = "";
                node.AppendChild(element3);
                XmlElement element4 = document2.CreateElement("DATE_FR");
                element4.InnerText = this.dateF;
                node.AppendChild(element4);
                XmlElement element5 = document2.CreateElement("DATE_TO");
                element5.InnerText = this.dateT;
                node.AppendChild(element5);
                XmlElement element6 = document2.CreateElement("ORGANIZATION_ID");
                element6.InnerText = str;
                node.AppendChild(element6);
                XmlElement element7 = document2.CreateElement("INTERFACE_CODE");
                element7.InnerText = "";
                node.AppendChild(element7);
                XmlElement element8 = document2.CreateElement("HEADER_ID");
                element8.InnerText = "";
                node.AppendChild(element8);
                XmlElement element9 = document2.CreateElement("LINE_ID");
                element9.InnerText = "";
                node.AppendChild(element9);
                XmlElement element10 = document2.CreateElement("DOC_NUM");
                element10.InnerText = "";
                node.AppendChild(element10);
                element.AppendChild(node);
                document2.AppendChild(element);
                string str6 = Base64Encrypt.Encrypt("utf-8", document2.OuterXml);
                string str7 = "同步冲减通知信息接口,接口名称:invokews";
                int num3 = 0;
                int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步冲减通知信息'").ID;
                string str8 = string.Format("固定值:CUXYXINVCON;batchNum:{0};requestData：{1}", str5, str6);
                try
                {
                    if (isLog) Inf_TaskLogService.WriteLog(str7, 1, iD, str6, 1, "开始调用接口");
                    this.service.invokews("CUXYXINVCON", str5, str6, ref str3, ref str2, ref str4);
                    if (isLog) Inf_TaskLogService.WriteLog(str7, 1, iD, str4, 1, "接口返回结果");


                    if (str2.IsNullOrEmpty())
                    {
                        str4 = Base64Encrypt.Decrypt("utf-8", str4);
                        document.LoadXml(str4);
                        XmlNodeList childNodes = document.SelectSingleNode("WSINTERFACE").ChildNodes;
                        foreach (System.Xml.XmlNode node3 in childNodes)
                        {
                            string str9 = JsonConvert.SerializeXmlNode(node3);
                            str8 = str9;
                            string str10 = string.Empty;
                            XmlElement element11 = (XmlElement)node3;
                            XmlNodeList list2 = element11.ChildNodes;
                            if (!Base_OffsetInfoService.Instance.Exists_Fish(" and DetailID=" + node3.SelectSingleNode("LINE_ID").InnerText))
                            {
                                int num5 = Convert.ToInt32(Base_PersonService.Instance.GetDataTable_Fish("select BASE_OFFSETINFOSEQUENCE.nextval from dual").Rows[0][0]);
                                Base_OffsetInfo data = new Base_OffsetInfo
                                {
                                    ID = num5,
                                    SheetID = int.Parse(node3.SelectSingleNode("HEADER_ID").InnerText),
                                    SheetCode = node3.SelectSingleNode("DOC_NUM").InnerText,
                                    DetailID = int.Parse(node3.SelectSingleNode("LINE_ID").InnerText),
                                    InterfaceType = node3.SelectSingleNode("INTERFACE_CODE").InnerText,
                                    CreateDate = Convert.ToDateTime(node3.SelectSingleNode("CREATE_DATE").InnerText),
                                    UpdateDate = DateTime.Parse(node3.SelectSingleNode("LAST_UPDATE_DATE").InnerText)
                                };
                                string innerText = node3.SelectSingleNode("VENDOR_ID").InnerText;
                                Base_Provider provider = Base_ProviderService.Instance.GetEntity_Fish(" and ExtendInt1=" + innerText);
                                if (provider != null)
                                {
                                    data.ProviderID = provider.ID;
                                    data.ProviderCode = provider.Code;
                                    data.ProviderName = provider.Name;
                                    data.ProviderPlaceID = int.Parse(node3.SelectSingleNode("VENDOR_SITE_ID").InnerText);
                                    data.ProviderPlaceCode = node3.SelectSingleNode("VENDOR_SITE_CODE").InnerText;
                                }
                                else
                                {
                                    str10 = str10 + "数据库中不存在此供应商，ERPID：" + innerText + ";";
                                }
                                data.TransID = int.Parse(node3.SelectSingleNode("TRANSACTION_ID").InnerText);
                                string str12 = node3.SelectSingleNode("ORGANIZATION_ID").InnerText;
                                Base_Organization organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + str12);
                                if (organization != null)
                                {
                                    data.OrganizationID = organization.ID;
                                }
                                else
                                {
                                    str10 = str10 + " 数据库中不存在此库存组织，ERPUD：" + str12 + ";";
                                }
                                if (string.IsNullOrEmpty(str10))
                                {
                                    WZ_SHEETCKDetail detail = WZ_SHEETCKDetailService.Instance.GetEntity_Fish("and ID=" + data.DetailID);
                                    if (detail != null)
                                    {
                                        if (Base_OffsetInfoService.Instance.Insert(data) > 0)
                                        {
                                            detail.ExtendInt6 = 1;
                                            if (WZ_SHEETCKDetailService.Instance.Update(detail) > 0)
                                            {
                                                Inf_TaskLogService.WriteLog(str7, num3, iD, str9, 1, "");
                                            }
                                            else
                                            {
                                                Inf_TaskLogService.WriteLog(str7, num3, iD, str9, 2, "更新表失败");
                                            }
                                        }
                                        else
                                        {
                                            Inf_TaskLogService.WriteLog(str7, num3, iD, str9, 2, "插入表失败");
                                        }
                                    }
                                    else
                                    {
                                        Inf_TaskLogService.WriteLog(str7, num3, iD, str9, 2, "库存中不存在此数据");
                                    }
                                }
                                else
                                {
                                    Inf_TaskLogService.WriteLog(str7, num3, iD, str9, 2, str10);
                                }
                            }
                        }
                    }
                    else
                    {
                        Inf_TaskLogService.WriteLog(str7, num3, iD, str8, 2, str2);
                    }

                }
                catch (Exception exception)
                {
                    this._logger.Error(str7, exception);
                    //Inf_TaskLogService.WriteLog(str7, num3, iD, str8, 2, exception.Message);
                }
            }
            return true;
        }
        public bool SynchOrderInfo()
        {
            bool result = SynchOrderInfo("1");
            result = SynchOrderInfo("2");
            return result;
        }
        public bool SynchOrderInfo(string type)
        {
            XmlDocument document = new XmlDocument();
            int num = 0;
            string str = string.Empty;
            string str2 = string.Empty;
            string str3 = string.Empty;
            string str4 = "10000";
            string str5 = "21324";
            string str6 = "";
            StringBuilder builder = new StringBuilder();
            builder.Append(string.Format("<WSINTERFACE> <PO_INFO> <DATE_FR>{0}</DATE_FR> <DATE_TO>{1}</DATE_TO> <ORG_ID>21324</ORG_ID> <ORGANIZATION_ID>{2}</ORGANIZATION_ID> <PO_NUMBER>{3}</PO_NUMBER> <PO_RELEASE_NUM></PO_RELEASE_NUM><TYPE>{4}</TYPE> </PO_INFO> </WSINTERFACE> ", new object[] { this.dateF, this.dateT, str5, str6, type }));
            string str7 = Base64Encrypt.Encrypt("utf-8", builder.ToString());
            string str8 = "同步采购订单接口,接口名称:invokews";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步采购订单'").ID;
            string str9 = string.Format("固定值:CUXYXPOWSO;batchNum:{0};requestData：{1}", str4, str7);
            try
            {
                if (isLog) Inf_TaskLogService.WriteLog(str8, 1, iD, str7, 1, "开始调用接口");
                this.service.invokews("CUXYXPOWSO", str4, str7, ref str2, ref str, ref str3);
                if (isLog) Inf_TaskLogService.WriteLog(str8, 1, iD, str3, 1, "接口返回结果");

                if (string.IsNullOrEmpty(str))
                {
                    str3 = Base64Encrypt.Decrypt("utf-8", str3);
                    document.LoadXml(str3);
                    XmlNodeList childNodes = document.SelectSingleNode("WSINTERFACE").ChildNodes;
                    List<Base_Dictionary> list = Base_DictionaryService.Instance.GetList("select ID,Name from BASE_DICTIONARY where parentid in (select id from BASE_DICTIONARY where code='JSType')");
                    foreach (System.Xml.XmlNode node2 in childNodes)
                    {
                        string str10 = JsonConvert.SerializeXmlNode(node2);
                        str9 = str10;
                        string str11 = string.Empty;
                        bool flag2 = false;
                        decimal detailCount = new decimal();
                        WZ_OrderInfo data = WZ_OrderInfoService.Instance.GetEntity_Fish(" and  FYID=" + node2.SelectSingleNode("PO_LINE_LOCATION_ID").InnerText);
                        if (data != null)
                        {
                            flag2 = true;
                            detailCount = data.DetailCount;
                        }
                        else
                        {
                            data = new WZ_OrderInfo();
                            int num5 = Convert.ToInt32(WZ_OrderInfoService.Instance.GetDataTable_Fish("select WZ_OrderInfo_SEQUENCE.nextval from dual").Rows[0][0].ToString());
                            data.ID = num5;
                            data.GUID = Guid.NewGuid().ToString();
                        }
                        data.ERPID = node2.SelectSingleNode("ID").InnerText;
                        data.OrderID = node2.SelectSingleNode("PO_HEADER_ID").InnerText.IsNullOrEmpty() ? 0 : int.Parse(node2.SelectSingleNode("PO_HEADER_ID").InnerText);
                        data.OrderNum = node2.SelectSingleNode("PO_NUMBER").InnerText;
                        string innerText = node2.SelectSingleNode("ORG_ID").InnerText;
                        Base_Organization organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + innerText);
                        if (organization != null)
                        {
                            data.BusinessID = organization.ID;
                        }
                        else
                        {
                            str11 = str11 + "数据库中不存在此业务实体;";
                        }
                        data.ERPStockOrgID = node2.SelectSingleNode("ORGANIZATION_ID").InnerText.IsNullOrEmpty() ? 0 : int.Parse(node2.SelectSingleNode("ORGANIZATION_ID").InnerText);
                        organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + data.ERPStockOrgID);
                        if (organization != null)
                        {
                            data.StockOrgID = organization.ID;
                        }
                        else
                        {
                            str11 = str11 + "数据库中不存在此库存组织;";
                        }
                        data.StockOrgCode = node2.SelectSingleNode("ORGANIZATION_CODE").InnerText;
                        data.ERPProviderDepID = node2.SelectSingleNode("VENDOR_ID").InnerText.IsNullOrEmpty() ? 0 : int.Parse(node2.SelectSingleNode("VENDOR_ID").InnerText);
                        Base_Provider provider = Base_ProviderService.Instance.GetEntity_Fish(" and ExtendInt2=" + node2.SelectSingleNode("VENDOR_SITE_ID").InnerText);
                        if (provider != null)
                        {
                            data.ProviderDepID = provider.ID;
                        }
                        else
                        {
                            str11 = str11 + string.Format("数据库中不存在此供应商(Code:{0},Name:{1});", node2.SelectSingleNode("VENDOR_NUMBER").InnerText, node2.SelectSingleNode("VENDOR_NAME").InnerText);
                        }
                        data.ProviderDepCode = node2.SelectSingleNode("VENDOR_NUMBER").InnerText;
                        data.ProviderDepName = node2.SelectSingleNode("VENDOR_NAME").InnerText;
                        data.ProviderPlaceID = node2.SelectSingleNode("VENDOR_SITE_ID").InnerText.IsNullOrEmpty() ? 0 : int.Parse(node2.SelectSingleNode("VENDOR_SITE_ID").InnerText);
                        data.ProviderPlaceCode = node2.SelectSingleNode("VENDOR_SITE_CODE").InnerText;
                        data.IssueCode = node2.SelectSingleNode("PO_RELEASE_NUM").InnerText.IsNullOrEmpty() ? 0 : int.Parse(node2.SelectSingleNode("PO_RELEASE_NUM").InnerText);
                        data.IssueID = node2.SelectSingleNode("PO_RELEASE_ID").InnerText.IsNullOrEmpty() ? 0 : int.Parse(node2.SelectSingleNode("PO_RELEASE_ID").InnerText);
                        string str13 = node2.SelectSingleNode("CREATED_BY").InnerText;
                        Base_Person person = Base_PersonService.Instance.GetEntity_Fish(" and ExtendInt1=" + str13);
                        if (person != null)
                        {
                            data.Creator = person.ID;
                        }
                        else
                        {
                            data.Creator = 0;
                        }
                        data.CreateDate = Convert.ToDateTime(node2.SelectSingleNode("CREATION_DATE").InnerText);
                        str13 = node2.SelectSingleNode("LAST_UPDATED_BY").InnerText;
                        person = Base_PersonService.Instance.GetEntity_Fish(" and ExtendInt1=" + str13);
                        if (person != null)
                        {
                            data.Updator = person.ID;
                        }
                        else
                        {
                            data.Updator = 0;
                        }
                        data.UpdateDate = !node2.SelectSingleNode("LAST_UPDATE_DATE").InnerText.IsNullOrEmpty() ? Convert.ToDateTime(node2.SelectSingleNode("LAST_UPDATE_DATE").InnerText) : DateTime.Now;
                        data.TaxPrice = node2.SelectSingleNode("INCLUDE_TAX_UNIT_PRICE").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("INCLUDE_TAX_UNIT_PRICE").InnerText);
                        data.DetailUnit = node2.SelectSingleNode("UOM").InnerText;
                        data.DetailCount = node2.SelectSingleNode("QUANTITY").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("QUANTITY").InnerText);
                        data.BaseUnit = node2.SelectSingleNode("PRIMARY_UOM").InnerText;
                        data.BaseUnitCount = node2.SelectSingleNode("PRIMARY_QUANTITY").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("PRIMARY_QUANTITY").InnerText);
                        data.BaseUnitTaxPrice = node2.SelectSingleNode("PRIMARY_UNIT_PRICE").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("PRIMARY_UNIT_PRICE").InnerText);
                        data.MaterialCode = node2.SelectSingleNode("ITEM_NUMBER").InnerText;
                        data.Description = node2.SelectSingleNode("ITEM_NAME").InnerText;
                        data.NoTaxPrice = node2.SelectSingleNode("UNIT_PRICE").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("UNIT_PRICE").InnerText);
                        data.NoTaxSum = node2.SelectSingleNode("AMOUNT").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("AMOUNT").InnerText);
                        data.BaseUnitPrice = node2.SelectSingleNode("PRIMARY_NOTAX_UNIT_PRICE").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("PRIMARY_NOTAX_UNIT_PRICE").InnerText);
                        data.BaseUnitSum = node2.SelectSingleNode("PRIMARY_NOTAX_AMOUNT").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("PRIMARY_NOTAX_AMOUNT").InnerText);
                        data.TaxRate = node2.SelectSingleNode("TAX_RATE").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("TAX_RATE").InnerText);
                        data.ExtendFloat2 = node2.SelectSingleNode("QUANTITY_UNRECEIVED").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("QUANTITY_UNRECEIVED").InnerText);
                        if (node2.SelectSingleNode("CONSIGNED_FLAG").InnerText == "Y")
                        {
                            data.Consignment = list.FirstOrDefault<Base_Dictionary>(x => (x.Name == "寄售")).ID;
                            decimal num9 = new decimal();
                            data.NoTaxSum = num9;
                            data.BaseUnitTaxPrice = data.BaseUnitPrice = data.BaseUnitSum = data.NoTaxPrice = num9;
                        }
                        else
                        {
                            data.Consignment = list.FirstOrDefault<Base_Dictionary>(x => (x.Name == "自有")).ID;
                        }
                        data.ERPRowNum = node2.SelectSingleNode("LINE_NUM").InnerText;
                        data.FYID = node2.SelectSingleNode("PO_LINE_LOCATION_ID").InnerText.IsNullOrEmpty() ? 0 : int.Parse(node2.SelectSingleNode("PO_LINE_LOCATION_ID").InnerText);
                        data.OrderRowID = node2.SelectSingleNode("PO_LINE_ID").InnerText.IsNullOrEmpty() ? 0 : int.Parse(node2.SelectSingleNode("PO_LINE_ID").InnerText);
                        data.OrderType = node2.SelectSingleNode("ORDER_TYPE").InnerText;
                        data.ExtendString1 = node2.SelectSingleNode("SUBINV_CODE").InnerText;
                        data.ExtendInt1 = (node2.SelectSingleNode("AUTHORIZATION_STATUS").InnerText == "Y") ? 1 : 0;
                        if (!data.ExtendString1.IsNullOrEmpty())
                        {
                            data.Consignment = list.FirstOrDefault<Base_Dictionary>(x => (x.Name == "赠送品")).ID;
                            data.StockOrgID = (data.StockOrgID == 0x30e45) ? ((data.ExtendString1 == "ZH01") ? 0x30dab : 0x30d41) : data.StockOrgID;
                        }
                        if (detailCount > decimal.Zero)
                        {
                            data.ExtendFloat1 = data.DetailCount - (detailCount - data.ExtendFloat1);
                        }
                        else
                        {
                            data.ExtendFloat1 = data.DetailCount;
                        }
                        if (str11 == "")
                        {
                            if (flag2)
                            {
                                num = WZ_OrderInfoService.Instance.Update(data);
                            }
                            else
                            {
                                num = WZ_OrderInfoService.Instance.Insert(data);
                            }
                            if (num > 0)
                            {
                                Inf_TaskLogService.WriteLog(str8, num2, iD, str10, 1, "");
                            }
                            else
                            {
                                Inf_TaskLogService.WriteLog(str8, num2, iD, str10, 2, flag2 ? "更新记录失败" : "插入记录失败");
                            }
                        }
                        else
                        {
                            Inf_TaskLogService.WriteLog(str8, num2, iD, str10, 2, str11);
                        }
                    }
                }
                else
                {
                    Inf_TaskLogService.WriteLog(str8, num2, iD, str9, 2, str);
                }
                return true;
            }
            catch (Exception exception)
            {
                this._logger.Error(str8, exception);
                //Inf_TaskLogService.WriteLog(str8, num2, iD, str9, 2, exception.Message);
                return false;
            }
        }

        public bool SynchPurchasePlan()
        {
            XmlDocument document = new XmlDocument();
            int num = 0;
            string str = string.Empty;
            string str2 = string.Empty;
            string str3 = string.Empty;
            string str4 = "10000";
            string str5 = "21324";
            string str6 = "";
            StringBuilder builder = new StringBuilder();
            builder.Append(string.Format("<WSINTERFACE> <SCHEDULE_INFO> <DATE_FR>{0}</DATE_FR> <DATE_TO>{1}</DATE_TO> <ORGANIZATION_ID>{2}</ORGANIZATION_ID> <SCHEDULE_NO>{3}</SCHEDULE_NO> </SCHEDULE_INFO> </WSINTERFACE> ", new object[] { this.dateF, this.dateT, str5, str6 }));
            string str7 = Base64Encrypt.Encrypt("utf-8", builder.ToString());
            string str8 = "同步采购计划接口,接口名称:invokews";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步采购计划'").ID;
            string str9 = string.Format("固定值:CUXYXPOSCH;batchNum:{0};requestData：{1}", str4, str7);
            try
            {
                if (isLog) Inf_TaskLogService.WriteLog(str8, 1, iD, str7, 1, "开始调用接口");
                this.service.invokews("CUXYXPOSCH", str4, str7, ref str2, ref str, ref str3);
                if (isLog) Inf_TaskLogService.WriteLog(str8, 1, iD, str3, 1, "接口返回结果");

                if (str.IsNullOrEmpty())
                {
                    str3 = Base64Encrypt.Decrypt("utf-8", str3);
                    document.LoadXml(str3);
                    XmlNodeList childNodes = document.SelectSingleNode("WSINTERFACE").ChildNodes;
                    foreach (System.Xml.XmlNode node2 in childNodes)
                    {
                        string str10 = JsonConvert.SerializeXmlNode(node2);
                        str9 = str10;
                        string str11 = string.Empty;
                        bool flag2 = false;
                        WZ_PurchasePlan data = WZ_PurchasePlanService.Instance.GetEntity_Fish((" and ERPDetailID=" + node2.SelectSingleNode("LINE_ID").InnerText) ?? "");
                        if (data != null)
                        {
                            flag2 = true;
                        }
                        else
                        {
                            int num4 = Convert.ToInt32(WZ_PurchasePlanService.Instance.GetDataTable_Fish("select WZ_PURCHASEPLAN_SEQUENCE.nextval from dual").Rows[0][0].ToString());
                            data = new WZ_PurchasePlan
                            {
                                ID = num4
                            };
                        }
                        List<Base_Organization> list2 = Base_OrganizationService.Instance.GetList_Fish("Select ID,Code,Name,ExtendInt1 FROM base_organization");
                        List<Base_UseDep> list3 = Base_UseDepService.Instance.GetList_Fish("Select ID,Code,Name,ExtendInt1 FROM base_organization");
                        data.ERPID = int.Parse(node2.SelectSingleNode("HEADER_ID").InnerText);
                        data.ERPDetailID = int.Parse(node2.SelectSingleNode("LINE_ID").InnerText);
                        data.PlanCode = node2.SelectSingleNode("SCHEDULE_NO").InnerText;
                        data.CreateDate = Convert.ToDateTime(node2.SelectSingleNode("SCHEDULE_DATE").InnerText);
                        string innerText = node2.SelectSingleNode("ORGANIZATION_ID").InnerText;
                        Base_Organization organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + innerText);
                        if (organization != null)
                        {
                            data.ApplyDepID = data.ZTID = organization.ID;
                        }
                        else
                        {
                            str11 = str11 + string.Format("数据库中不存在此申报单位(ID:{0});", innerText);
                        }
                        string str13 = node2.SelectSingleNode("USE_DEPARTMENT").InnerText;
                        Base_UseDep dep = Base_UseDepService.Instance.GetEntity_Fish(" and Name='" + str13 + "'");
                        if (dep != null)
                        {
                            data.UseDepID = dep.ID;
                        }
                        else
                        {
                            str11 = str11 + string.Format("数据库中不存在此使用单位(Name:{0});", str13);
                        }
                        data.MaterialCode = node2.SelectSingleNode("ITEM_NO").InnerText;
                        Base_Material material = Base_MaterialService.Instance.GetEntity_Fish(" and Code='" + data.MaterialCode + "'");
                        if (material != null)
                        {
                            data.MaterialDes = material.Description;
                        }
                        else
                        {
                            str11 = str11 + string.Format("数据库中不存在此物料(Code:{0})", node2.SelectSingleNode("ITEM_NO").InnerText);
                        }
                        data.PurchaseType = (node2.SelectSingleNode("IS_ON_JC_LIST").InnerText == "Y") ? 1 : 0;
                        data.PlanType = (node2.SelectSingleNode("URGENT_FLAG").InnerText == "Y") ? 1 : 0;
                        data.Count = node2.SelectSingleNode("QUANTITY").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("QUANTITY").InnerText);
                        data.Unit = node2.SelectSingleNode("UOM_CODE").InnerText;
                        data.Price = node2.SelectSingleNode("BUDGET_PRICE").InnerText.IsNullOrEmpty() ? decimal.Zero : decimal.Parse(node2.SelectSingleNode("BUDGET_PRICE").InnerText);
                        data.UpdateDate = node2.SelectSingleNode("LAST_UPDATE_DATE").InnerText.IsNullOrEmpty() ? DateTime.Now : Convert.ToDateTime(node2.SelectSingleNode("LAST_UPDATE_DATE").InnerText);
                        data.NeedDate = node2.SelectSingleNode("NEED_BY_DATE").InnerText.IsNullOrEmpty() ? DateTime.Now : Convert.ToDateTime(node2.SelectSingleNode("NEED_BY_DATE").InnerText);
                        data.ExtendInt1 = (node2.SelectSingleNode("STATUS").InnerText == "N") ? 0 : 1;
                        if (string.IsNullOrEmpty(str11))
                        {
                            if (flag2)
                            {
                                num = WZ_PurchasePlanService.Instance.Update(data);
                            }
                            else
                            {
                                num = WZ_PurchasePlanService.Instance.Insert(data);
                            }
                            if (num > 0)
                            {
                                Inf_TaskLogService.WriteLog(str8, num2, iD, str10, 1, "");
                            }
                            else
                            {
                                Inf_TaskLogService.WriteLog(str8, num2, iD, str10, 2, flag2 ? "更新记录失败" : "插入记录失败");
                            }
                        }
                        else
                        {
                            Inf_TaskLogService.WriteLog(str8, num2, iD, str10, 2, str11);
                        }
                    }
                }
                else
                {
                    Inf_TaskLogService.WriteLog(str8, num2, iD, str9, 2, str);
                }
                return true;
            }
            catch (Exception exception)
            {
                this._logger.Error(str8, exception);
                //Inf_TaskLogService.WriteLog(str8, num2, iD, str9, 2, exception.Message);
                return false;
            }
        }

        public bool SynchStockCostUpdate()
        {
            XmlDocument document = new XmlDocument();
            string str = string.Empty;
            string str2 = string.Empty;
            string str3 = string.Empty;
            string str4 = DateTime.Now.ToString("yyyyMMddHHmmss");
            XmlDocument document2 = new XmlDocument();
            XmlDeclaration newChild = document2.CreateXmlDeclaration("1.0", "utf-8", null);
            document2.AppendChild(newChild);
            XmlElement element = document2.CreateElement("WSINTERFACE");
            List<WZ_Stock> list = WZ_StockService.Instance.GetList_Fish(" and ExtendInt3<>1", true);
            foreach (WZ_Stock stock in list)
            {
                System.Xml.XmlNode node = document2.CreateElement("HEADER");
                Base_Organization organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ID=" + stock.ZTID);
                XmlElement element2 = document2.CreateElement("ORGANIZATION_ID");
                element2.InnerText = organization.ExtendInt1.ToString();
                node.AppendChild(element2);
                XmlElement element3 = document2.CreateElement("ITEM_NO");
                element3.InnerText = stock.MaterialCode;
                node.AppendChild(element3);
                element.AppendChild(node);
            }
            document2.AppendChild(element);
            string str5 = Base64Encrypt.Encrypt("utf-8", document2.OuterXml);
            string str6 = "同步库存成本更新接口,接口名称:invokews";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步库存成本更新'").ID;
            string str7 = string.Format("固定值:CUXYXINVCST;batchNum:{0};requestData：{1}", str4, str5);
            try
            {
                if (isLog) Inf_TaskLogService.WriteLog(str6, 1, iD, str5, 1, "开始调用接口");
                this.service.invokews("CUXYXINVCST", str4, str5, ref str2, ref str, ref str3);
                if (isLog) Inf_TaskLogService.WriteLog(str6, 1, iD, str3, 1, "接口返回结果");

                if (str.IsNullOrEmpty())
                {
                    str3 = Base64Encrypt.Decrypt("utf-8", str3);
                    document.LoadXml(str3);
                    XmlNodeList childNodes = document.SelectSingleNode("WSINTERFACE").ChildNodes;
                    foreach (System.Xml.XmlNode node3 in childNodes)
                    {
                        string str8 = JsonConvert.SerializeXmlNode(node3);
                        str7 = str8;
                        string str9 = string.Empty;
                        XmlElement element4 = (XmlElement)node3;
                        XmlNodeList list3 = element4.ChildNodes;
                        int num5 = Convert.ToInt32(WZ_PurchasePlanService.Instance.GetDataTable_Fish("select BASE_STOCKCOSTUPDATESEQUENCE.nextval from dual").Rows[0][0].ToString());
                        Base_StockCostUpdate data = new Base_StockCostUpdate
                        {
                            ID = num5
                        };
                        string innerText = node3.SelectSingleNode("ORGANIZATION_ID").InnerText;
                        Base_Organization organization2 = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + innerText);
                        if (organization2 != null)
                        {
                            data.OrganizationID = organization2.ID;
                        }
                        else
                        {
                            str9 = str9 + " 数据库中不存在此库存组织，ERPUD：" + innerText + ";";
                        }
                        string str11 = node3.SelectSingleNode("ITEM_NO").InnerText;
                        Base_Material material = Base_MaterialService.Instance.GetEntity_Fish(" and Code='" + str11 + "'");
                        if (material != null)
                        {
                            data.MaterialCode = material.Code;
                        }
                        else
                        {
                            str9 = str9 + " 数据库中不存在此物料，Code：" + str11 + ";";
                        }
                        data.NewCost = decimal.Parse(node3.SelectSingleNode("NEW_COST").InnerText);
                        data.UpdateDate = DateTime.Parse(node3.SelectSingleNode("LAST_UPDATE_DATE").InnerText);
                        data.LastTransID = int.Parse(node3.SelectSingleNode("TRANSACTION_ID").InnerText);
                        if (string.IsNullOrEmpty(str9))
                        {
                            object[] objArray1 = new object[] { "and MaterialCode='", data.MaterialCode, "' and ZTID=", data.OrganizationID };
                            WZ_Stock stock2 = WZ_StockService.Instance.GetEntity_Fish(string.Concat(objArray1));
                            if (stock2 != null)
                            {
                                object[] objArray2 = new object[] { " and ID=", stock2.StoreID, " and ZTID=", stock2.ZTID };
                                switch (Base_WarehouseService.Instance.GetEntity_Fish(string.Concat(objArray2)).Name)
                                {
                                    case "材料库":
                                    case "设备库":
                                        if (Base_StockCostUpdateService.Instance.Insert(data) > 0)
                                        {
                                            stock2.NoTaxPrice = data.NewCost;
                                            stock2.ExtendInt3 = 1;
                                            if (WZ_StockService.Instance.Update(stock2) > 0)
                                            {
                                                Inf_TaskLogService.WriteLog(str6, num2, iD, str8, 1, "");
                                            }
                                            else
                                            {
                                                Inf_TaskLogService.WriteLog(str6, num2, iD, str8, 2, "更新表失败");
                                            }
                                        }
                                        else
                                        {
                                            Inf_TaskLogService.WriteLog(str6, num2, iD, str8, 2, "插入Base_CostUpdate表失败");
                                        }
                                        break;
                                }
                            }
                            else
                            {
                                Inf_TaskLogService.WriteLog(str6, num2, iD, str8, 2, "库存中不存在此数据");
                            }
                        }
                        else
                        {
                            Inf_TaskLogService.WriteLog(str6, num2, iD, str8, 2, str9);
                        }
                    }
                }
                else
                {
                    Inf_TaskLogService.WriteLog(str6, num2, iD, str7, 2, str);
                }
                return true;
            }
            catch (Exception exception)
            {
                this._logger.Error(str6, exception);
                //Inf_TaskLogService.WriteLog(str6, num2, iD, str7, 2, exception.Message);
                return false;
            }
        }

    }
}

