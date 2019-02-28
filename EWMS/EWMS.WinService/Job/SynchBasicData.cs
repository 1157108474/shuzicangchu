namespace EWMS.WinService.Job
{
    using EWMS.WinService;
    using EWMS.Entity;
    using EWMS.Service;
    using log4net;
    using Newtonsoft.Json;
    using Quartz;
    using System;
    using System.Configuration;
    using System.Text;
    using System.Xml;
    using ItemsServices;
    using CategoryServices;
    using VendorServices;
    using System.ServiceModel;
    using System.ServiceModel.Channels;

    [DisallowConcurrentExecution]
    public sealed class SynchBasicData : IJob
    {
        private readonly ILog _logger = LogManager.GetLogger("myLooger");
        //public string dateF = DateTime.Parse("2017-1-1").ToString("yyyy-MM-dd HH:mm:ss");//DateTime.Now.AddMinutes((double)(0 - min)).ToString("yyyy-MM-dd HH:mm:ss");

        public string dateT = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
        private static int min = int.Parse(ConfigurationManager.AppSettings["1Day"].ToString());
        public string dateF = DateTime.Now.AddMinutes((double)(0 - min)).ToString("yyyy-MM-dd HH:mm:ss");
        public BusinessServices.cuxwmsClient service = null;
        public bool isLog = false;

        public void Execute(IJobExecutionContext context)
        {
            service = Common.InitWMSClient();
            this._logger.InfoFormat("SynchBasicData(DateF:{0},DateT:{1})", dateF, dateT);
            this.SynchOrganization();
            this.SynchPerson();
            this.SynchApplyDep();
            this.SynchUseDep();
            this.SynchStore();
            this.SynchStoreLocation();
            this.SyncVendors();
            this.SyncMaterialType();
            this.SyncMaterials();
      

            if (service != null)
                service.Close();
        }

        public bool SynchApplyDep()
        {
            XmlDocument document = new XmlDocument();
            int num = 0;
            string str = string.Empty;
            string str2 = string.Empty;
            string str3 = string.Empty;
            string str4 = "10000";
            string str5 = "21324";
            StringBuilder builder = new StringBuilder();
            builder.Append(string.Format("<WSINTERFACE> <HEADER> <DATE_FR>{0}</DATE_FR> <DATE_TO>{1}</DATE_TO> <ORGANIZATION_ID>{2}</ORGANIZATION_ID> </HEADER> </WSINTERFACE>", this.dateF, this.dateT, str5));
            string str6 = Base64Encrypt.Encrypt("utf-8", builder.ToString());
            string str7 = "同步申请单位接口,接口名称:invokews";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步申请单位'").ID;
            string str8 = string.Format("固定值:CUXYXINVDIS;batchNum:{0};requestData：{1}", str4, str6);
            try
            {
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str6, 1, "开始调用接口");
                this.service.invokews("CUXYXINVDIS", str4, str6, ref str2, ref str, ref str3);
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str3, 1, "接口返回结果");
                if (string.IsNullOrEmpty(str))
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
                        string str11 = Guid.NewGuid().ToString();
                        int num4 = 0;
                        Base_ApplyDep data = Base_ApplyDepService.Instance.GetEntity_Fish(" and ERPID='" + node2.SelectSingleNode("ALIAS_ID").InnerText + "'");
                        if (data != null)
                        {
                            num4 = data.ID;
                            flag2 = true;
                        }
                        else
                        {
                            num4 = Convert.ToInt32(Base_ApplyDepService.Instance.GetDataTable_Fish("select BASE_APPLYDEP_SEQUENCE.nextval from dual").Rows[0][0]);
                            data = new Base_ApplyDep
                            {
                                ID = num4
                            };
                        }
                        data.ERPID = node2.SelectSingleNode("ALIAS_ID").InnerText;
                        data.CODE = node2.SelectSingleNode("ALIAS_NAME").InnerText;
                        data.NAME = node2.SelectSingleNode("ALIAS_DESC").InnerText;
                        string innerText = node2.SelectSingleNode("ORGANIZATION_ID").InnerText;
                        Base_Organization organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + innerText);
                        if (organization != null)
                        {
                            data.ZTID = organization.ID;
                        }
                        else
                        {
                            str10 = str10 + "数据库中不存在此库存组织，ERPID：" + innerText + "; ";
                        }
                        data.SUBJECTSGROUP = node2.SelectSingleNode("ACCOUNTS").InnerText;
                        data.SUBJECTSGROUPDESCRIPTION = node2.SelectSingleNode("ACT_DES").InnerText;
                        data.STATUS = (node2.SelectSingleNode("STATUS").InnerText == "有效") ? 1 : 0;
                        if (str10 == "")
                        {
                            if (flag2)
                            {
                                num = Base_ApplyDepService.Instance.Update(data);
                            }
                            else
                            {
                                num = Base_ApplyDepService.Instance.Insert(data);
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

        public bool SynchOrganization()
        {
            XmlDocument document = new XmlDocument();
            int num = 0;
            string str = string.Empty;
            string str2 = string.Empty;
            string str3 = string.Empty;
            string str4 = "10000";
            string str5 = "21324";
            StringBuilder builder = new StringBuilder();
            builder.Append(string.Format("<WSINTERFACE> <HEADER> <DATE_FR>{0}</DATE_FR > <DATE_TO>{1}</DATE_TO> <ORG_ID>{2}</ORG_ID> </HEADER> </WSINTERFACE> ", this.dateF, this.dateT, str5));
            string str6 = Base64Encrypt.Encrypt("utf-8", builder.ToString());
            string str7 = "同步组织结构接口,接口名称:invokews";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步组织结构'").ID;
            string str8 = string.Format("固定值:CUXYXINVORG;batchNum:{0};requestData：{1}", str4, str6);
            try
            {
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str6, 1, "开始调用接口");
                this.service.invokews("CUXYXINVORG", str4, str6, ref str2, ref str, ref str3);
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
                        XmlElement element = (XmlElement)node2;
                        XmlNodeList list2 = element.ChildNodes;
                        bool flag2 = false;
                        string str11 = Guid.NewGuid().ToString();
                        int num4 = 0;
                        Base_Organization data = Base_OrganizationService.Instance.GetEntity_Fish(" and  ExtendInt1=" + node2.SelectSingleNode("ORGANIZATION_ID").InnerText);
                        if (data != null)
                        {
                            flag2 = true;
                        }
                        else
                        {
                            num4 = Convert.ToInt32(Base_OrganizationService.Instance.GetDataTable_Fish("select BASEORGANIZATION_SEQUENCE.nextval from dual").Rows[0][0]);
                            data = new Base_Organization
                            {
                                ID = num4,
                                GUID = Guid.NewGuid().ToString(),
                                CreateDate = new DateTime?(Convert.ToDateTime(node2.SelectSingleNode("CREATION_DATE").InnerText))
                            };
                        }
                        data.ExtendInt1 = int.Parse(node2.SelectSingleNode("ORGANIZATION_ID").InnerText);
                        data.Code = node2.SelectSingleNode("ORGANIZATION_NUMBER").InnerText;
                        data.Name = node2.SelectSingleNode("ORGANIZATION_NAME").InnerText;
						data.LevelCode = node2.SelectSingleNode("ORGANIZATION_LEVEL").InnerText;
						data.Sort = node2.SelectSingleNode("ORDER_BY_NUMBER").InnerText;
                        if (data.ExtendInt1 == 21324)//0xb51)
                        {
                            data.ParentID = 0;
                        }
                        else
                        {
                            int num5 = int.Parse(node2.SelectSingleNode("ORGANIZATION_ID_PARENT").InnerText);
                            if (num5 > 0)
                            {
                                Base_Organization organization2 = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + num5);
                                if (organization2 != null)
                                {
                                    data.ParentID = organization2.ID;
                                }
                                else
                                {
                                    object[] objArray1 = new object[] { "数据库中不存在此组织机构，ERPID：", num5, ",请先插入ERPID为", num5, " 的组织机构" };
                                    str10 = string.Concat(objArray1);
                                }
                            }
                            else
                            {
                                data.ParentID = 0;
                                data.ZTID = data.ID;
                            }
                        }
                        data.UpdateDate = new DateTime?(node2.SelectSingleNode("LAST_UPDATE_DATE").InnerText.IsNullOrEmpty() ? new DateTime() : Convert.ToDateTime(node2.SelectSingleNode("LAST_UPDATE_DATE").InnerText));
                        data.Status = node2.SelectSingleNode("DATE_TO").InnerText.IsNullOrEmpty() ? 1 : ((Convert.ToDateTime(node2.SelectSingleNode("DATE_TO").InnerText) < DateTime.Now) ? 0 : 1);
                        data.CompanyID = 0x186a1;
                        data.EndFlag = 1;
                        if ((((data.ExtendInt1 == 21324) || (data.ExtendInt1 == 0x149)) || (data.ExtendInt1 == 0x148)) || (data.ExtendInt1 == 0x265))
                        {
                            data.ExtendInt2 = 1;
                        }
                        else
                        {
                            data.ExtendInt2 = 0;
                        }
                        if (str10 == "")
                        {
                            if (flag2)
                            {
                                num = Base_OrganizationService.Instance.Update(data);
                            }
                            else
                            {
                                num = Base_OrganizationService.Instance.Insert(data);
                            }
                            if (num > 0)
                            {
                                num = Base_OrganizationService.Instance.ExecuteNonQuery_Fish(" update Base_Organization set EndFlag=0 where id=" + data.ParentID);
                                Inf_TaskLogService.WriteLog(str7, num2, iD, str9, 1, "");
                            }
                            else
                            {
                                Inf_TaskLogService.WriteLog(str7, num2, iD, str9, 2, flag2 ? "更新失败" : "插入失败");
                            }
                        }
                    }
                    //EWMS.WinService.Common.UpdateLeve("base_organization");
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

        public bool SynchPerson()
        {
            XmlDocument document = new XmlDocument();
            int num = 0;
            string str = string.Empty;
            string str2 = string.Empty;
            string str3 = string.Empty;
            string str4 = DateTime.Now.ToString("yyyyMMddHHmmss");
            string str5 = "21324";
            StringBuilder builder = new StringBuilder();
            builder.Append(string.Format("<WSINTERFACE> <HEADER><ORG_ID>{0}</ORG_ID> <DATE_FR>{1}</DATE_FR> <DATE_TO>{2}</DATE_TO> <EMPLOYEE_NUM>{3}</EMPLOYEE_NUM> </HEADER> </WSINTERFACE>", str5, this.dateF, this.dateT, ""));
            string str6 = Base64Encrypt.Encrypt("utf-8", builder.ToString());
            string str7 = "同步人员信息接口,接口名称:invokews";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步人员信息'").ID;
            string str8 = string.Format("固定值:CUXYXHRPER;batchNum:{0};requestData：{1}", str4, str6);
            try
            {
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str6, 1, "开始调用接口");
                this.service.invokews("CUXYXHRPER", str4, str6, ref str2, ref str, ref str3);
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str3, 1, "接口返回结果");
                //str3 = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPFdTSU5URVJG QUNFPjwvV1NJTlRFUkZBQ0U+";
                if (str.IsNullOrEmpty())
                {
                    str3 = Base64Encrypt.Decrypt("utf-8", str3);
                    //str3 = new System.IO.StreamReader(@"e:\tmp.txt").ReadToEnd();
                    document.LoadXml(str3);
                    XmlNodeList childNodes = document.SelectSingleNode("WSINTERFACE").ChildNodes;
                    foreach (System.Xml.XmlNode node2 in childNodes)
                    {
                        string str9 = JsonConvert.SerializeXmlNode(node2);
                        str8 = str9;
                        string str10 = string.Empty;
                        bool flag2 = false;
                        string str11 = Guid.NewGuid().ToString();
                        int num4 = 0;
                        Base_Person data = new Base_Person();
                        string innerText = node2.SelectSingleNode("ORGANIZATION_ID").InnerText;
                        Base_Organization organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + innerText);
                        if (organization != null)
                        {
                            object[] objArray1 = new object[] { " and  ExtendInt1=", node2.SelectSingleNode("PERSON_ID").InnerText, " and ZTID=", organization.ID };
                            data = Base_PersonService.Instance.GetEntity_Fish(string.Concat(objArray1));
                        }
                        else
                        {
                            data = null;
                            str10 = str10 + "数据库中不存在此库存组织，ERPID：" + innerText + ";";
                        }
                        if (data != null)
                        {
                            flag2 = true;
                        }
                        else
                        {
                            num4 = Convert.ToInt32(Base_PersonService.Instance.GetDataTable_Fish("select BASEPERSONROLE_SEQUENCE.nextval from dual").Rows[0][0]);
                            data = new Base_Person
                            {
                                ID = num4,
                                GUID = Guid.NewGuid().ToString(),
                                Password = Md5Util.MD5("123"),
                                ZTID = organization.ID,
                                UserType = 3
                            };
                        }
                        data.ExtendInt1 = int.Parse(node2.SelectSingleNode("PERSON_ID").InnerText);
                        data.Code = node2.SelectSingleNode("USER_NAME").InnerText;
                        data.Name = node2.SelectSingleNode("FULL_NAME").InnerText;
                        data.ExtendString1 = node2.SelectSingleNode("DATE_OF_BIRTH").InnerText;
                        data.ExtendString2 = node2.SelectSingleNode("NATIONAL_IDENTIFIER").InnerText;
                        data.Sex = (node2.SelectSingleNode("SEX").InnerText == "M") ? "0" : "1";
                        data.Phone = node2.SelectSingleNode("MOBILE_PHONE").InnerText;
                        data.Email = node2.SelectSingleNode("EMAIL_ADDRESS").InnerText;
                        data.CreateDate = new DateTime?(Convert.ToDateTime(node2.SelectSingleNode("ASS_CREATION_DATE").InnerText));
                        data.UpdateDate = new DateTime?(Convert.ToDateTime(node2.SelectSingleNode("ASS_LAST_UPDATE_DATE").InnerText));
                        data.Status = (node2.SelectSingleNode("STATUS").InnerText == "有效") ? 1 : 0;
                        data.DepartID = data.ZTID;
                        if (str10 == "")
                        {
                            if (flag2)
                            {
                                num = Base_PersonService.Instance.Update(data);
                            }
                            else
                            {
                                num = Base_PersonService.Instance.Insert(data);
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

        public bool SynchStore()
        {
            XmlDocument document = new XmlDocument();
            int num = 0;
            string str = string.Empty;
            string str2 = string.Empty;
            string str3 = string.Empty;
            string str4 = "10000";
            string str5 = "21324";
            StringBuilder builder = new StringBuilder();
            builder.Append(string.Format("<WSINTERFACE> <HEADER> <ORGANIZATION_ID>{0}</ORGANIZATION_ID> </HEADER> </WSINTERFACE>", str5));
            string str6 = Base64Encrypt.Encrypt("utf-8", builder.ToString());
            string str7 = "同步库房信息接口,接口名称:invokews";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步库房信息'").ID;
            string str8 = string.Format("固定值:CUXYXINVSUB;batchNum:{0};requestData：{1}", str4, str6);
            try
            {
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str6, 1, "开始调用接口");
                this.service.invokews("CUXYXINVSUB", str4, str6, ref str2, ref str, ref str3);
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str3, 1, "接口返回结果");

                if (str.IsNullOrEmpty())
                {
                    str3 = Base64Encrypt.Decrypt("utf-8", str3);
                    document.LoadXml(str3);
                    XmlNodeList childNodes = document.SelectSingleNode("WSINTERFACE").ChildNodes;
                    string status = "";
                    foreach (System.Xml.XmlNode node2 in childNodes)
                    {
                        string str9 = JsonConvert.SerializeXmlNode(node2);
                        str8 = str9;
                        string str10 = string.Empty;
                        XmlElement element = (XmlElement)node2;
                        XmlNodeList list2 = element.ChildNodes;
                        string str11 = Guid.NewGuid().ToString();
                        bool flag2 = false;
                        int num4 = 0;
                        Base_Warehouse data = Base_WarehouseService.Instance.GetEntity_Fish(" and ExtendString1 ='" + node2.SelectSingleNode("PK_ID").InnerText + "'");
                        if (data != null)
                        {
                            flag2 = true;
                            data.UpdateDate = new DateTime?(DateTime.Now);
                        }
                        else
                        {
                            num4 = Convert.ToInt32(Base_WarehouseService.Instance.GetDataTable_Fish("select BASEWAREHOUSE_SEQUENCE.nextval from dual").Rows[0][0]);
                            data = new Base_Warehouse
                            {
                                ID = num4,
                                CreateDate = new DateTime?(DateTime.Now),
                                GUID = Guid.NewGuid().ToString()
                            };
                        }
                        data.ExtendString1 = node2.SelectSingleNode("PK_ID").InnerText;
                        data.Code = node2.SelectSingleNode("SUBINV_CODE").InnerText;
                        data.Name = node2.SelectSingleNode("SUBINV_NAME").InnerText;
                        string innerText = node2.SelectSingleNode("ORGANIZATION_ID").InnerText;
                        if ((data.Code == "ZM01") || (data.Code == "ZH01"))
                        {
                            data.ZTID = (data.Code == "ZM01") ? 0x30d41 : 0x30dab;
                        }
                        else
                        {
                            Base_Organization organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + innerText);
                            if (organization != null)
                            {
                                data.ZTID = organization.ID;
                            }
                            else
                            {
                                str10 = str10 + "数据库中不存在此库存组织，ERPID：" + innerText + "; ";
                            }
                        }
                        data.ParentID = 0;
                        status = node2.SelectSingleNode("STATUS").InnerText;
                        data.Status = (status == "Active") ? 1 : (status == "有效" ? 1 : 0);
                        data.EndFlag = 0;
                        data.Property = 1;
                        if (str10 == "")
                        {
                            if (flag2)
                            {
                                num = Base_WarehouseService.Instance.Update(data);
                            }
                            else
                            {
                                num = Base_WarehouseService.Instance.Insert(data);
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
                    EWMS.WinService.Common.UpdateLeve("base_warehouse");
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

        public bool SynchStoreLocation()
        {
            XmlDocument document = new XmlDocument();
            int num = 0;
            string str = string.Empty;
            string str2 = string.Empty;
            string str3 = string.Empty;
            string str4 = "10000";
            string str5 = "21324";
            StringBuilder builder = new StringBuilder();
            builder.Append(string.Format("<WSINTERFACE> <HEADER> <ORGANIZATION_ID>{0}</ORGANIZATION_ID> <DATE_FR>{1}</DATE_FR> <DATE_TO>{2}</DATE_TO> </HEADER> </WSINTERFACE>", str5, this.dateF, this.dateT));
            string str6 = Base64Encrypt.Encrypt("utf-8", builder.ToString());
            string str7 = "同步库位信息接口,接口名称:invokews";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步库位信息'").ID;
            string str8 = string.Format("固定值:CUXYXINVLOC;batchNum:{0};requestData：{1}", str4, str6);
            try
            {
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str6, 1, "开始调用接口");
                this.service.invokews("CUXYXINVLOC", str4, str6, ref str2, ref str, ref str3);
                if (isLog) Inf_TaskLogService.WriteLog(str7, num2, iD, str3, 1, "接口返回结果");

                if (str.IsNullOrEmpty())
                {
                    str3 = Base64Encrypt.Decrypt("utf-8", str3);
                    document.LoadXml(str3);
                    XmlNodeList childNodes = document.SelectSingleNode("WSINTERFACE").ChildNodes;
                    string status = "";
                    foreach (System.Xml.XmlNode node2 in childNodes)
                    {
                        string str9 = JsonConvert.SerializeXmlNode(node2);
                        str8 = str9;
                        string str10 = string.Empty;
                        XmlElement element = (XmlElement)node2;
                        XmlNodeList list2 = element.ChildNodes;
                        bool flag2 = false;
                        string str11 = Guid.NewGuid().ToString();
                        int num4 = 0;
                        Base_Warehouse data = Base_WarehouseService.Instance.GetEntity_Fish(" and ExtendInt1=" + node2.SelectSingleNode("LOCATOR_ID").InnerText);
                        if (data != null)
                        {
                            flag2 = true;
                            data.UpdateDate = new DateTime?(DateTime.Now);
                        }
                        else
                        {
                            num4 = Convert.ToInt32(Base_WarehouseService.Instance.GetDataTable_Fish("select BASEWAREHOUSE_SEQUENCE.nextval from dual").Rows[0][0]);
                            data = new Base_Warehouse
                            {
                                ID = num4,
                                CreateDate = new DateTime?(DateTime.Now),
                                GUID = Guid.NewGuid().ToString()
                            };
                        }
                        data.ExtendInt1 = int.Parse(node2.SelectSingleNode("LOCATOR_ID").InnerText);
                        data.Code = node2.SelectSingleNode("LOCATOR_CODE").InnerText;
                        data.Name = node2.SelectSingleNode("LOCATOR_NAME").InnerText;
                        string innerText = node2.SelectSingleNode("ORGANIZATION_ID").InnerText;
                        string str13 = node2.SelectSingleNode("SUBINV_CODE").InnerText;
                        Base_Organization organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + innerText);
                        if (organization != null)
                        {
                            Base_Warehouse warehouse2 = new Base_Warehouse();
                            if ((str13 != "ZM01") && (str13 != "ZH01"))
                            {
                                object[] objArray1 = new object[] { " and Code='", str13, "' and ztid=", organization.ID };
                                warehouse2 = Base_WarehouseService.Instance.GetEntity_Fish(string.Concat(objArray1));
                            }
                            else
                            {
                                warehouse2 = Base_WarehouseService.Instance.GetEntity_Fish(" and Code='" + str13 + "'");
                            }
                            if (warehouse2 != null)
                            {
                                data.ParentID = warehouse2.ID;
                                data.ZTID = warehouse2.ZTID;
                            }
                            else
                            {
                                object[] objArray2 = new object[] { str10, "数据库中不存在此库房，Code：", str13, ",ZTID:", warehouse2.ID };
                                str10 = string.Concat(objArray2);
                            }
                        }
                        else
                        {
                            str10 = str10 + "数据库中不存在此库存组织，ERPID：" + innerText + "; ";
                        }
                        status = node2.SelectSingleNode("STATUS").InnerText;
                        data.Status = (status == "有效") ? 1 : (status == "Active" ? 1 : 0);
                        data.EndFlag = 1;
                        data.Property = 4;
                        if (str10 == "")
                        {
                            if (flag2)
                            {
                                num = Base_WarehouseService.Instance.Update(data);
                            }
                            else
                            {
                                num = Base_WarehouseService.Instance.Insert(data);
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
                    EWMS.WinService.Common.UpdateLeve("base_warehouse");
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

        public bool SynchUseDep()
        {
            XmlDocument document = new XmlDocument();
            int num = 0;
            string str = string.Empty;
            string str2 = string.Empty;
            string str3 = string.Empty;
            string str4 = "10000";
            string str5 = "21324";
            StringBuilder builder = new StringBuilder();
            builder.Append(string.Format("<WSINTERFACE> <HEADER> <DATE_FR>{0}</DATE_FR> <DATE_TO>{1}</DATE_TO> <ORGANIZATION_ID>{2}</ORGANIZATION_ID> </HEADER> </WSINTERFACE>", this.dateF, this.dateT, str5));
            string str6 = Base64Encrypt.Encrypt("utf-8", builder.ToString());
            string str7 = "同步使用单位接口,接口名称:invokews";
            int num3 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步使用单位'").ID;
            string str8 = string.Format("固定值:CUXYXUSEDPT;batchNum:{0};requestData：{1}", str4, str6);
            try
            {
                if (isLog) Inf_TaskLogService.WriteLog(str7, num3, iD, str6, 1, "开始调用接口");
                this.service.invokews("CUXYXUSEDPT", str4, str6, ref str2, ref str, ref str3);
                if (isLog) Inf_TaskLogService.WriteLog(str7, num3, iD, str3, 1, "接口返回结果");
                //str3 = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPFdTSU5URVJG QUNFPjwvV1NJTlRFUkZBQ0U+";
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
                        string str11 = Guid.NewGuid().ToString();
                        int num5 = 0;
                        Base_UseDep data = Base_UseDepService.Instance.GetEntity_Fish(" and ERPID=" + node2.SelectSingleNode("PK_ID").InnerText);
                        if (data != null)
                        {
                            num5 = data.ID;
                            flag2 = true;
                        }
                        else
                        {
                            num5 = Convert.ToInt32(Base_UseDepService.Instance.GetDataTable_Fish("select BASE_USEDEP_SEQUENCE.nextval from dual").Rows[0][0]);
                            data = new Base_UseDep
                            {
                                ID = num5
                            };
                        }
                        data.ERPID = int.Parse(node2.SelectSingleNode("PK_ID").InnerText);
                        data.Code = node2.SelectSingleNode("USING_DEPART_CODE").InnerText;
                        data.Name = node2.SelectSingleNode("USING_DEPART_NAME").InnerText;
                        if (data.Name.Contains("科") || data.Name.Contains("室"))
                        {
                            data.OrganizationType = 2;
                        }
                        else
                        {
                            data.OrganizationType = 1;
                        }
                        string innerText = node2.SelectSingleNode("ORGANIZATION_ID").InnerText;
                        Base_Organization organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + innerText);
                        if (organization != null)
                        {
                            data.ZTID = organization.ZTID;
                            data.OrganizationID = organization.ID;
                        }
                        else
                        {
                            str10 = str10 + "数据库中不存在此库存组织，ERPID：" + innerText + "; ";
                        }
                        data.Status = (node2.SelectSingleNode("STATUS").InnerText == "有效") ? 1 : 0;
                        if (str10 == "")
                        {
                            if (flag2)
                            {
                                num = Base_UseDepService.Instance.Update(data);
                            }
                            else
                            {
                                num = Base_UseDepService.Instance.Insert(data);
                            }
                            if (num > 0)
                            {
                                Inf_TaskLogService.WriteLog(str7, num3, iD, str9, 1, "");
                            }
                            else
                            {
                                Inf_TaskLogService.WriteLog(str7, num3, iD, str9, 2, flag2 ? "更新记录失败" : "插入记录失败");
                            }
                        }
                        else
                        {
                            Inf_TaskLogService.WriteLog(str7, num3, iD, str9, 2, str10);
                        }
                    }
                }
                else
                {
                    Inf_TaskLogService.WriteLog(str7, num3, iD, str8, 2, str);
                }
                return true;
            }
            catch (Exception exception)
            {
                this._logger.Error(str7, exception);
                //Inf_TaskLogService.WriteLog(str7, num3, iD, str8, 2, exception.Message);
                return false;
            }
        }

        public bool SyncMaterials()
        {
            ItemsServices.TEMSClient client = Common.InitItemClient();
            string str = "内蒙古中煤远兴能源化工有限公司";// "中天合创能源有限责任公司";//;
            string dateF = this.dateF;
            ItemsRInvItemsRecUserArray array = new ItemsRInvItemsRecUserArray();
            string str3 = "";
            int num = 0;
            string str4 = "同步物资接口,接口名称:invItemsWsInt";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步物资接口'").ID;
            string str5 = string.Format("业务实体名称:{0};时间戳:{1}", str, dateF);
            try
            {
                client.invItemsWsInt(str, dateF, ref array, ref str3);
                if (array.Count > 0)
                {
                    foreach (ItemsRInvItemsRecUser user in array)
                    {
                        string str6 = "";
                        bool flag2 = false;
                        string str7 = JsonConvert.SerializeObject(user);
                        str5 = str7;
                        int num4 = 0;
                        string str8 = user.organizationId.ToString();
                        string categoryConcatSegs = user.categoryConcatSegs;
                        Base_Organization organization = Base_OrganizationService.Instance.GetEntity_Fish(" and ExtendInt1=" + str8);
                        Base_Material data = new Base_Material();
                        if (organization != null)
                        {
                            object[] objArray1 = new object[] { " and ExtendInt1=", user.inventoryItemId, " and ZTID=", organization.ID };
                            data = Base_MaterialService.Instance.GetEntity_Fish(string.Concat(objArray1));
                        }
                        else
                        {
                            data = null;
                            str6 = str6 + string.Format("数据库中不存在此账套,ID:{0}", str8);
                        }
                        Base_SparepartsCate cate = Base_SparepartsCateService.Instance.GetEntity_Fish(" and CODE='" + categoryConcatSegs + "'");
                        if (cate == null)
                        {
                            str6 = str6 + string.Format("数据库中不存在此物料类型,CODE:{0}", categoryConcatSegs);
                        }
                        if (!string.IsNullOrEmpty(str6))
                        {
                            Inf_TaskLogService.WriteLog(str4, num2, iD, str7, 2, str6);
                        }
                        else
                        {
                            if (data != null)
                            {
                                flag2 = true;
                                data.UpdateDate = DateTime.Now;
                            }
                            else
                            {
                                num4 = int.Parse(Base_SparepartsCateService.Instance.GetDataTable_Fish("select BASEMATERIAL_SEQUENCE.nextval from dual").Rows[0][0].ToString());
                                data = new Base_Material
                                {
                                    ID = num4,
                                    GUID = Guid.NewGuid().ToString(),
                                    CreateDate = DateTime.Now
                                };
                            }
                            data.ZTID = organization.ID;
                            data.SparescateID = cate.ID;
                            data.ExtendInt1 = Convert.ToInt32(user.inventoryItemId);
                            data.Code = user.itemNumber;
                            data.Name = user.itemDescription.Replace("'", "");
                            data.Description = user.itemDescription.Replace("'", "");
                            data.Model = user.attribute22 == null ? "" : user.attribute22.Replace("'", "");
                            data.Specifications = user.attribute21 == null ? "" : user.attribute21.Replace("'", "");
                            data.Unit = user.uom;

                            data.ConfigMemo = user.attribute24;
                            data.StockUp = Convert.ToDecimal(user.maxMinmaxQuantity);
                            data.StockDown = Convert.ToDecimal(user.minMinmaxQuantity);
                            data.IsUseAlarm = ((data.StockUp == decimal.Zero) && (data.StockDown == decimal.Zero)) ? 0 : 1;
                            data.Status = (user.inventoryItemStatusCode == "Active") ? 1 : 0;

                            if (flag2)
                            {
                                num = Base_MaterialService.Instance.Update(data);
                            }
                            else
                            {
                                num = Base_MaterialService.Instance.Insert(data);
                            }
                            if (num > 0)
                            {
                                Inf_TaskLogService.WriteLog(str4, num2, iD, str7, 1, "");
                            }
                            else
                            {
                                string str10 = flag2 ? "更新数据失败" : "插入数据失败";
                                Inf_TaskLogService.WriteLog(str4, num2, iD, str7, 2, str10);
                            }
                        }
                    }
                }
                else if (str3 != "无数据!")
                {
                    Inf_TaskLogService.WriteLog(str4, num2, iD, str5, 2, str3);
                }
                return true;
            }
            catch (Exception exception)
            {
                this._logger.Error(str4, exception);
                //Inf_TaskLogService.WriteLog(str4, num2, iD, str5, 2, exception.Message);
                return false;
            }
            finally
            {
                if (client != null)
                    client.Close();
            }
        }

        public bool SyncMaterialType()
        {
            CategoryServices.CATEGORIESClient client = Common.InitCategoryClient();
            string str = "内蒙古中煤远兴能源化工有限公司";
            string dateF = this.dateF;
            CategoriesRCategoriesRecUserArray array = new CategoriesRCategoriesRecUserArray();
            string str3 = "";
            int num = 0;
            string str4 = "同步物资分类接口,接口名称:invCategoriesWsInt";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步物资分类接口'").ID;
            string str5 = string.Format("业务实体名称:{0};时间戳:{1}", str, dateF);
            try
            {
                client.invCategoriesWsInt(str, dateF, ref array, ref str3);
                if (array.Count > 0)
                {
                    foreach (CategoriesRCategoriesRecUser user in array)
                    {
                        string str6 = "";
                        bool flag2 = false;
                        string str7 = JsonConvert.SerializeObject(user);
                        str5 = str7;
                        int num4 = 0;
                        Base_SparepartsCate data = Base_SparepartsCateService.Instance.GetEntity_Fish(" and Code='" + user.lbbm + "'");
                        if (data != null)
                        {
                            flag2 = true;
                            data.UpdateDate = new DateTime?(DateTime.Now);
                        }
                        else
                        {
                            num4 = int.Parse(Base_SparepartsCateService.Instance.GetDataTable_Fish("select BASESPAREPARTSCATE_SEQUENCE.nextval from dual").Rows[0][0].ToString());

                            data = new Base_SparepartsCate
                            {
                                ID = num4,
                                GUID = Guid.NewGuid().ToString()
                            };
                        }
                        data.Code = user.lbbm;
                        data.Name = user.categoryDescription;
                        if (!data.Code.IsNullOrEmpty())
                        {
                            if (data.Code.Length > 2)
                            {
                                string str8 = data.Code.Substring(0, data.Code.Length - 2);
                                Base_SparepartsCate cate2 = Base_SparepartsCateService.Instance.GetEntity_Fish(" and CODE='" + str8 + "'");
                                if (cate2 != null)
                                {
                                    data.ParentID = cate2.ID;
                                }
                                else
                                {
                                    str6 = str6 + string.Format("数据库中不存在此备件分类,CODE:{0}", str8);
                                }
                            }
                            else
                            {
                                data.ParentID = 0;
                            }
                        }
                        else
                        {
                            str6 = str6 + "编码为null;";
                        }
                        data.EndFlag = 1;
                        data.Status = (user.validateFlag == "Y") ? 1 : 0;
                        data.CreateDate = user.creationDate.ToString().IsNullOrEmpty() ? new DateTime?(DateTime.Now) : user.creationDate;
                        if (str6.IsNullOrEmpty())
                        {
                            if (flag2)
                            {
                                num = Base_SparepartsCateService.Instance.Update(data);
                            }
                            else
                            {
                                num = Base_SparepartsCateService.Instance.Insert(data);
                            }
                            if (num > 0)
                            {
                                Base_OrganizationService.Instance.ExecuteNonQuery_Fish(" update Base_Organization set EndFlag=0 where id=" + data.ParentID);
                                Inf_TaskLogService.WriteLog(str4, num2, iD, str7, 1, "");
                            }
                            else
                            {
                                string str9 = flag2 ? "更新数据失败" : "插入数据失败";
                                Inf_TaskLogService.WriteLog(str4, num2, iD, str7, 2, str9);
                            }
                        }
                        else
                        {
                            Inf_TaskLogService.WriteLog(str4, num2, iD, str7, 2, str6);
                        }
                    }
                    EWMS.WinService.Common.UpdateLeve("base_sparepartscate");
                }
                else if (str3 != "无数据!")
                {
                    Inf_TaskLogService.WriteLog(str4, num2, iD, str5, 2, str3);
                }
                return true;
            }
            catch (Exception exception)
            {
                this._logger.Error(str4, exception);
                //Inf_TaskLogService.WriteLog(str4, num2, iD, str5, 2, exception.Message);
                return false;
            }
            finally
            {
                if (client != null)
                    client.Close();
            }
        }

        public bool SyncVendors()
        {
            POVENDORSClient client = Common.InitVendorClient();
            int num = 0;
            string str = "内蒙古中煤远兴能源化工有限公司";
            string dateF = this.dateF;
            PovendorsRPoVendoresRecUserArray array = new PovendorsRPoVendoresRecUserArray();
            string str3 = string.Empty;
            string str4 = "同步供应商接口,接口名称:poVendorsWsInt";
            int num2 = 0;
            int iD = Inf_TaskService.Instance.GetEntity_Fish(" and INF_TASKNAME='同步供应商接口'").ID;
            string str5 = string.Format("业务实体名称:{0};时间戳:{1}", str, dateF);
            try
            {
                client.poVendorsWsInt(str, dateF, ref array, ref str3);
                if (array.Count > 0)
                {
                    foreach (PovendorsRPoVendoresRecUser user in array)
                    {
                        DateTime? endDateActive = null;
                        DateTime now = DateTime.Now;
                        string str6 = JsonConvert.SerializeObject(user);
                        str5 = str6;
                        bool flag2 = false;
                        int num4 = 0;
                        Base_Provider data = Base_ProviderService.Instance.GetEntity_Fish(" and ExtendInt2=" + user.vendorSiteId);
                        if (data != null)
                        {
                            num4 = data.ID;
                            flag2 = true;
                        }
                        else
                        {
                            num4 = int.Parse(Base_ProviderService.Instance.GetDataTable_Fish("select BASEPROVIDER_SEQUENCE.nextval from dual").Rows[0][0].ToString());
                            data = new Base_Provider
                            {
                                ID = num4,
                                GUID = Guid.NewGuid().ToString()
                            };
                        }
                        data.ExtendInt1 = Convert.ToInt32(user.vendorId);
                        data.Code = user.vendorNumber;
                        data.Name = user.vendorName;
                        data.ExtendInt2 = Convert.ToInt32(user.vendorSiteId);
                        data.ExtendString1 = user.vendorSiteCode;
                        if (user.endDateActive.HasValue)
                        {
                            endDateActive = user.endDateActive;
                            now = DateTime.Now;
                        }
                        data.Status = (endDateActive.HasValue ? (endDateActive.GetValueOrDefault() <= now) : false) ? 1 : 0;
                        data.CreateDate = user.creationDate;
                        data.UpdateDate = user.lastUpdateDate;
                        if (flag2)
                        {
                            num = Base_ProviderService.Instance.Update(data);
                        }
                        else
                        {
                            num = Base_ProviderService.Instance.Insert(data);
                        }
                        if (num > 0)
                        {
                            Inf_TaskLogService.WriteLog(str4, num2, iD, str6, 1, "");
                        }
                        else
                        {
                            string str7 = flag2 ? "更新数据失败" : "插入数据失败";
                            Inf_TaskLogService.WriteLog(str4, num2, iD, str6, 2, str7);
                        }
                    }
                }
                else if (str3 != "无数据!")
                {
                    Inf_TaskLogService.WriteLog(str4, num2, iD, str5, 2, str3);
                }
                return true;
            }
            catch (Exception exception)
            {
                this._logger.Error(str4, exception);
                //Inf_TaskLogService.WriteLog(str4, num2, iD, str5, 2, exception.Message);
                return false;
            }
            finally
            {
                if (client != null)
                    client.Close();
            }
        }
    }
}

