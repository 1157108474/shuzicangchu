namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Utils;
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Data;
    using EWMS.Entity;

    public class Base_PersonService : BaseService<Base_Person>
    {
        private static readonly Base_PersonService _Instance = new Base_PersonService();

        public int Insertdata(string json, int creator)
        {
            int num = 0;
            foreach (Hashtable hashtable1 in (ArrayList) JSON.Decode(json))
            {
                object obj1 = hashtable1["ID"];
                object obj2 = hashtable1["Code"];
                object obj3 = hashtable1["PName"];
                object obj4 = hashtable1["Spell"];
                object obj5 = hashtable1["Sex"];
                object obj6 = hashtable1["Email"];
                object obj7 = hashtable1["Department"];
                object obj8 = hashtable1["Phone"];
                object obj9 = hashtable1["QQ"];
                object obj10 = hashtable1["Sort"];
                object obj11 = hashtable1["Type"];
                object obj12 = hashtable1["Status"];
                object obj13 = hashtable1["Memo"];
                object obj14 = hashtable1["IsSingleLogin"];
                object obj15 = hashtable1["Offices"];
                string str = Guid.NewGuid().ToString();
                string str2 = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                string str3 = Md5Util.MD5("123456");
                DataTable table = base.GetDataTable_Fish("SELECT companyid,ztid FROM Base_Organization where id=" + obj7);
                string str4 = "";
                string str5 = "";
                if ((table != null) && (table.Rows.Count > 0))
                {
                    str4 = table.Rows[0]["companyid"].ToString();
                    str5 = table.Rows[0]["ztid"].ToString();
                }
                object[] objArray1 = new object[] { "SELECT * FROM Base_Person WHERE Code='", obj2, "' and ZTID=", str5 };
                string sql = string.Concat(objArray1);
                if (base.GetDataTable_Fish(sql).Rows.Count > 0)
                {
                    num = 2;
                }
                else
                {
                    string str7 = "insert into Base_Person (GUID ,UserType ,Code ,Spell ,Name ,Sex ,DepartID ,OfficesID,Email ,Phone ,QQ ,Password ,CompanyID ,Status ,Sort ,Memo ,Creator ,CreateDate,issingleLogin,isaudit,ZTID) ";
                    object[] objArray2 = new object[] { 
                        str7, "values ('", str, "',", obj11, ",'", obj2, "','", obj4, "','", obj3, "',", obj5, ",", obj7, ",",
                        obj15, ",'", obj6, "','", obj8, "','", obj9, "','", str3, "',", str4, ",", obj12, ",", obj10, ",'",
                        obj13, "',", creator, ",to_date('", str2, "','yyyy-mm-dd hh24:mi:ss'),", obj14, ",1,", str5, ")"
                    };
                    str7 = string.Concat(objArray2);
                    try
                    {
                        num = base.ExecuteNonQuery_Fish(str7);
                    }
                    catch (Exception)
                    {
                        num = -2;
                    }
                }
            }
            return num;
        }

        public CommandResult Login(string userCode, string password, string ip, string city)
        {
            //TODO
            //new { 
            //    Code = userCode,
            //    Password = password,
            //    LoginIP = ip,
            //    LoginCity = city,
            //    ResultID = 0,
            //    ResultMsg = ""
            //};
            //new Hashtable();
            Dictionary<string, string> indict = new Dictionary<string, string> {
                { 
                    "Code",
                    userCode
                },
                { 
                    "Password",
                    password
                },
                { 
                    "LoginIP",
                    ip
                },
                { 
                    "LoginCity",
                    city
                }
            };
            Dictionary<string, string> dict = new Dictionary<string, string> {
                { 
                    "ResultID",
                    ""
                },
                { 
                    "ResultMsg",
                    ""
                }
            };
            return base.SP_Fish("Base_User_Login", indict, dict);
        }

        public int Updatedata(string json, int updater)
        {
            int num = 0;
            foreach (Hashtable hashtable1 in (ArrayList) JSON.Decode(json))
            {
                object obj2 = hashtable1["ID"];
                object obj3 = hashtable1["Code"];
                object obj4 = hashtable1["PName"];
                object obj5 = hashtable1["Spell"];
                object obj6 = hashtable1["Sex"];
                object obj7 = hashtable1["Email"];
                object obj8 = hashtable1["Department"];
                object obj9 = hashtable1["Phone"];
                object obj10 = hashtable1["QQ"];
                object obj11 = hashtable1["Sort"];
                object obj12 = hashtable1["Type"];
                object obj13 = hashtable1["Status"];
                object obj14 = hashtable1["Memo"];
                object obj15 = hashtable1["Offices"];
                string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                object obj16 = hashtable1["IsSingleLogin"];
                DataTable table = base.GetDataTable_Fish("SELECT companyid,ztid FROM Base_Organization where id=" + obj8);
                string str2 = "";
                string str3 = "";
                if ((table != null) && (table.Rows.Count > 0))
                {
                    str2 = table.Rows[0]["companyid"].ToString();
                    str3 = table.Rows[0]["ztid"].ToString();
                }
                object[] objArray1 = new object[] { "SELECT * FROM Base_Person WHERE Code='", obj3, "' AND ZTID=", str3, " AND ID <>", obj2 };
                string sql = string.Concat(objArray1);
                if (base.GetDataTable_Fish(sql).Rows.Count > 0)
                {
                    num = 2;
                }
                else
                {
                    object[] objArray2 = new object[] { 
                        "update Base_Person set Code='", obj3, "',Name='", obj4, "',Sort=", obj11, ",Status=", obj13, ",Memo='", obj14, "',Departid=", obj8, ",OfficesID=", obj15, ",Email='", obj7,
                        "',Phone='", obj9, "',Spell='", obj5, "',qq='", obj10, "',sex=", obj6, ",usertype=", obj12, ",companyid=", str2, ",IsSingleLogin=", obj16, ",Updater=", updater,
                        ",UpdateDate=to_date('", str, "','yyyy-mm-dd hh24:mi:ss'),isaudit=1 ,ZTID=", str3, "  where id=", obj2
                    };
                    string str5 = string.Concat(objArray2);
                    try
                    {
                        num = base.ExecuteNonQuery_Fish(str5);
                    }
                    catch (Exception)
                    {
                        num = -2;
                    }
                }
            }
            return num;
        }

        public int updatePWD(int id, string pwd)
        {
            pwd = Md5Util.MD5(pwd);
            object[] objArray1 = new object[] { "UPDATE Base_Person SET Password='", pwd, "' where id=", id };
            string sql = string.Concat(objArray1);
            try
            {
                return base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -2;
            }
        }

        public static Base_PersonService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

