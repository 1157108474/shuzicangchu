namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Utils;
    using System;
    using System.Collections;
    using EWMS.Entity;

    public class Base_ReceiptService : BaseService<Base_Receipt>
    {
        private static readonly Base_ReceiptService _Instance = new Base_ReceiptService();

        public int Insertdata(string data, int creator)
        {
            int num = 0;
            foreach (Hashtable hashtable1 in (ArrayList) JSON.Decode(data))
            {
                object obj2 = hashtable1["RECEIPTCODE"];
                object obj1 = hashtable1["ID"];
                object obj3 = hashtable1["RECEIPTNAME"];
                object obj4 = hashtable1["PREFIX"];
                object obj5 = hashtable1["ProcessID"];
                object obj6 = hashtable1["REPORTNAME"];
                object obj7 = hashtable1["RECEIPTSORT"];
                object obj8 = hashtable1["RECEIPTSTATUS"];
                object obj9 = hashtable1["RECEIPTMEMO"];
                object obj10 = hashtable1["RECEIPTTYPEID"];
                object obj11 = hashtable1["MENUCODE"];
                string str = Guid.NewGuid().ToString();
                string str2 = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                string sql = "SELECT * FROM Base_Receipt WHERE ReceiptCode='" + obj2 + "'";
                int count = base.GetDataTable_Fish(sql).Rows.Count;
                string str4 = "SELECT * FROM Base_Receipt WHERE processid=" + obj5;
                if (base.GetDataTable_Fish(str4).Rows.Count > 0)
                {
                    num = 3;
                }
                else if (count > 0)
                {
                    num = 2;
                }
                else
                {
                    string str5 = "insert into Base_Receipt (GUID ,ReceiptCode ,ReceiptType,ReceiptName ,Prefix ,ProcessID ,Reportname ,ReceiptSort ,ReceiptStatus ,ReceiptMemo ,Creator ,CreateDate,menucode) ";
                    object[] objArray1 = new object[] { 
                        str5, "values ('", str, "','", obj2, "',", obj10, ",'", obj3, "','", obj4, "',", obj5, ",'", obj6, "',",
                        obj7, ",", obj8, ",'", obj9, "',", creator, ",to_date('", str2, "','yyyy-mm-dd hh24:mi:ss'),'", obj11, "')"
                    };
                    str5 = string.Concat(objArray1);
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

        public int Updatedata(string data, int updater)
        {
            int num = 0;
            foreach (Hashtable hashtable1 in (ArrayList) JSON.Decode(data))
            {
                object obj2 = hashtable1["RECEIPTCODE"];
                object obj3 = hashtable1["ID"];
                object obj4 = hashtable1["RECEIPTNAME"];
                object obj5 = hashtable1["PREFIX"];
                object obj6 = hashtable1["ProcessID"];
                object obj7 = hashtable1["REPORTNAME"];
                object obj8 = hashtable1["RECEIPTSORT"];
                object obj9 = hashtable1["RECEIPTSTATUS"];
                object obj10 = hashtable1["RECEIPTMEMO"];
                object obj1 = hashtable1["RECEIPTTYPE"];
                object obj11 = hashtable1["MENUCODE"];
                string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                object[] objArray1 = new object[] { "SELECT * FROM Base_Receipt WHERE ReceiptCode='", obj2, "' and id <>", obj3 };
                string sql = string.Concat(objArray1);
                if (base.GetDataTable_Fish(sql).Rows.Count > 0)
                {
                    num = 2;
                }
                else
                {
                    object[] objArray2 = new object[] { 
                        "update Base_Receipt set ReceiptCode='", obj2, "',ReceiptName='", obj4, "',ReceiptSort=", obj8, ",ReceiptStatus=", obj9, ",ReceiptMemo='", obj10, "',Prefix='", obj5, "',Reportname='", obj7, "',ProcessID=", obj6,
                        ",updater=", updater, ",updatedate=to_date('", str, "','yyyy-mm-dd hh24:mi:ss'),menucode='", obj11, "'  where id=", obj3
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

        public static Base_ReceiptService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

