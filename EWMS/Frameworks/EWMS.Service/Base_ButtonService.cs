namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Utils;
    using System;
    using System.Collections;
    using EWMS.Entity;
    public class Base_ButtonService : BaseService<Base_Button>
    {
        private static readonly Base_ButtonService _Instance = new Base_ButtonService();

        public int Insertdata(string data, int creator)
        {
            int num = 0;
            foreach (Hashtable hashtable1 in (ArrayList) JSON.Decode(data))
            {
                object obj2 = hashtable1["Code"];
                object obj3 = hashtable1["Name"];
                object obj4 = hashtable1["Type"];
                object obj5 = hashtable1["JS"];
                object obj6 = hashtable1["Image"];
                object obj7 = hashtable1["Sort"];
                object obj8 = hashtable1["Status"];
                object obj9 = hashtable1["Memo"];
                string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                string sql = "SELECT * FROM Base_Button WHERE ButtonCode='" + obj2 + "'";
                if (base.GetDataTable_Fish(sql).Rows.Count > 0)
                {
                    num = 2;
                }
                else
                {
                    string str3 = "insert into Base_Button (ButtonCode ,ButtonName ,ButtonType ,IconClass ,jsevent,Sort ,Enabled ,Remark ,Creator ,CreateDate) ";
                    object[] objArray1 = new object[] { 
                        str3, "values ('", obj2, "','", obj3, "',", obj4, ",'", obj6, "','", obj5, "',", obj7, ",", obj8, ",'",
                        obj9, "', ", creator, ",to_date('", str, "','yyyy-mm-dd hh24:mi:ss'))"
                    };
                    str3 = string.Concat(objArray1);
                    num = base.ExecuteNonQuery_Fish(str3);
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
                object obj3 = hashtable1["Name"];
                object obj4 = hashtable1["Type"];
                object obj5 = hashtable1["JS"];
                object obj6 = hashtable1["Image"];
                object obj7 = hashtable1["Sort"];
                object obj8 = hashtable1["Status"];
                object obj9 = hashtable1["Memo"];
                string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                object[] objArray1 = new object[] { 
                    "update Base_Button set ButtonName='", obj3, "',Sort=", obj7, ",Enabled=", obj8, ",Remark='", obj9, "',ButtonType=", obj4, ",jsevent='", obj5, "',IconClass='", obj6, "',updater=", updater,
                    ",updatedate=to_date('", str, "','yyyy-mm-dd hh24:mi:ss')  where buttoncode='", obj2, "'"
                };
                string sql = string.Concat(objArray1);
                num = base.ExecuteNonQuery_Fish(sql);
            }
            return num;
        }

        public static Base_ButtonService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

