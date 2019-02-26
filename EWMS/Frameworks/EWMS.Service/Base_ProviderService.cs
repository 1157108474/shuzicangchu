namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Utils;
    using System;
    using System.Collections;
    using EWMS.Entity;

    public class Base_ProviderService : BaseService<Base_Provider>
    {
        private static readonly Base_ProviderService _Instance = new Base_ProviderService();

        public int Insertdata(string data, int creator)
        {
            int num = 0;
            foreach (Hashtable hashtable1 in (ArrayList) JSON.Decode(data))
            {
                object obj2 = hashtable1["Code"];
                object obj1 = hashtable1["ID"];
                object obj3 = hashtable1["Name"];
                object obj4 = hashtable1["Address"];
                object obj5 = hashtable1["ZipCode"];
                object obj6 = hashtable1["ContactPerson"];
                object obj7 = hashtable1["ContractPhone"];
                object obj8 = hashtable1["Fax"];
                object obj9 = hashtable1["Email"];
                object obj10 = hashtable1["Memo"];
                string str = Guid.NewGuid().ToString();
                string str2 = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                string sql = "select * from Base_Provider where code='" + obj2 + "'";
                if (base.GetDataTable_Fish(sql).Rows.Count > 0)
                {
                    num = 2;
                }
                else
                {
                    object[] objArray1 = new object[] { 
                        "insert into Base_Provider (GUID,code,name,address,zipcode,ContactPerson,ContractPhone,Fax ,Email ,Memo ,Creator ,CreateDate) values ('", str, "','", obj2, "','", obj3, "','", obj4, "','", obj5, "','", obj6, "','", obj7, "','", obj8,
                        "','", obj9, "','", obj10, "',", creator, ",to_date('", str2, "','yyyy-mm-dd hh24:mi:ss'))"
                    };
                    string str4 = string.Concat(objArray1);
                    try
                    {
                        num = base.ExecuteNonQuery_Fish(str4);
                    }
                    catch (Exception)
                    {
                        num = -2;
                    }
                }
            }
            return num;
        }

        public int Updatedata(string data, int updater)
        {
            int num = 0;
            foreach (Hashtable hashtable1 in (ArrayList) JSON.Decode(data))
            {
                object obj2 = hashtable1["Code"];
                object obj3 = hashtable1["ID"];
                object obj4 = hashtable1["Name"];
                object obj5 = hashtable1["Address"];
                object obj6 = hashtable1["ZipCode"];
                object obj7 = hashtable1["ContactPerson"];
                object obj8 = hashtable1["ContractPhone"];
                object obj9 = hashtable1["Fax"];
                object obj10 = hashtable1["Email"];
                object obj11 = hashtable1["Memo"];
                string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                object[] objArray1 = new object[] { "select * from Base_Provider where code='", obj2, "' and id <>", obj3 };
                string sql = string.Concat(objArray1);
                if (base.GetDataTable_Fish(sql).Rows.Count > 0)
                {
                    num = 2;
                }
                else
                {
                    object[] objArray2 = new object[] { 
                        "update Base_Provider set code='", obj2, "',name='", obj4, "',address='", obj5, "',zipcode='", obj6, "',ContactPerson='", obj7, "', ContractPhone='", obj8, "',fax='", obj9, "',email='", obj10,
                        "',memo='", obj11, "',updater=", updater, ",updatedate=to_date('", str, "','yyyy-mm-dd hh24:mi:ss') where id=", obj3
                    };
                    string str3 = string.Concat(objArray2);
                    try
                    {
                        num = base.ExecuteNonQuery_Fish(str3);
                    }
                    catch (Exception)
                    {
                        num = -2;
                    }
                }
            }
            return num;
        }

        public static Base_ProviderService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

