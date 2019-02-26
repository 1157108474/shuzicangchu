namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using Microsoft.CSharp.RuntimeBinder;
    using Oracle.ManagedDataAccess.Client;
    using System;
    using System.Collections.Generic;
    using System.Dynamic;
    

    public class WZ_SHEET_CKService : BaseService<WZ_SHEET_CK>
    {
        private static readonly WZ_SHEET_CKService _Instance = new WZ_SHEET_CKService();

        public List<Base_Dictionary> GetAllDic()
        {
            string sql = "select ID ,GUID,CODE,NAME,PARENTID from Base_Dictionary ";
            return Base_DictionaryService.Instance.GetList(sql);
        }

        public int InsertData(WZ_SHEET_CK sheet, int creator, string kindcode, Base_Menu menu)
        {
            string str = "WZSHEETCK_SEQUENCE";
            try
            {
                dynamic obj2 = new ExpandoObject();
                obj2.Code = "seqName";
                obj2.Type = "varchar2";
                obj2.Size = 0x40;
                dynamic obj3 = new ExpandoObject();
                obj3.Code = "nextID";
                obj3.Type = "varchar2";
                obj3.Size = 64;
                dynamic obj4 = new ExpandoObject();
                obj4.Code = "currentID";
                obj4.Type = "varchar2";
                obj4.Size = 64;
                Dictionary<object, string> dictionary = new Dictionary<object, string>();
                dictionary.Add(obj2, str);
                Dictionary<object, string> dictionary2 = new Dictionary<object, string>();
                dictionary2.Add(obj3, "");
                dictionary2.Add(obj4, "");
                OracleParameter[] parameterArray = base.SP_OtherFish("GetIDs", dictionary, dictionary2);
                sheet.ID = Convert.ToInt32(parameterArray[0].Value.ToString());
                string str2 = parameterArray[1].Value.ToString();
                sheet.GUID = Guid.NewGuid().ToString();
                sheet.SubmitManId = creator;
                sheet.Creator = creator;
                sheet.SubmitTime = DateTime.Now;
                sheet.CreateDate = DateTime.Now;
                if (Instance.Insert(sheet) > 0)
                {
                    dynamic obj5 = new ExpandoObject();
                    obj5.Code = "PSHEETKIND";
                    obj5.Type = "varchar2";
                    obj5.Size = 64;
                    dynamic obj6 = new ExpandoObject();
                    obj6.Code = "PROUTESTEPID";
                    obj6.Type = "int";
                    obj6.Size = 0x20;
                    dynamic obj7 = new ExpandoObject();
                    obj7.Code = "PSHEETID";
                    obj7.Type = "int";
                    obj7.Size = 0x20;
                    dynamic obj8 = new ExpandoObject();
                    obj8.Code = "PMENUID";
                    obj8.Type = "varchar2";
                    obj8.Size = 0x40;
                    dynamic obj9 = new ExpandoObject();
                    obj9.Code = "PPERSONID";
                    obj9.Type = "int";
                    obj9.Size = 0x20;
                    dynamic obj10 = new ExpandoObject();
                    obj10.Code = "PJUMPPATH";
                    obj10.Type = "varchar2";
                    obj10.Size = 0x7d0;
                    dynamic obj11 = new ExpandoObject();
                    obj11.Code = "PZTID";
                    obj11.Type = "int";
                    obj11.Size = 0x20;
                    dynamic obj12 = new ExpandoObject();
                    obj12.Code = "RetInt";
                    obj12.Type = "int";
                    obj12.Size = 0x20;
                    dynamic obj13 = new ExpandoObject();
                    obj13.Code = "RetHintStr";
                    obj13.Type = "varchar2";
                    obj13.Size = 0x3e8;
                    dynamic obj14 = new ExpandoObject();
                    obj14.Code = "RetErrorStr";
                    obj14.Type = "varchar2";
                    obj14.Size = 0x3e8;
                    Dictionary<object, string> dictionary3 = new Dictionary<object, string>();
                    dictionary3.Add(obj5, kindcode);
                    dictionary3.Add(obj6, "0");
                    dictionary3.Add(obj7, str2.ToString());
                    dictionary3.Add(obj8, menu.MenuCode);
                    dictionary3.Add(obj9, creator.ToString());
                    dictionary3.Add(obj10, menu.Url);
                    dictionary3.Add(obj11, "1000");
                    Dictionary<object, string> dictionary4 = new Dictionary<object, string>();
                    dictionary4.Add(obj12, "");
                    dictionary4.Add(obj13, "");
                    dictionary4.Add(obj14, "");
                    base.SP_OtherFish("ADD_FIRST_WAITTASK", dictionary3, dictionary4);
                }
                return Convert.ToInt32(str2);
            }
            catch (Exception)
            {
                return -1;
            }
        }

        public int UpdateData(ParamUpdate param)
        {
            try
            {
                return Instance.Update(param);
            }
            catch (Exception)
            {
                return -1;
            }
        }

        public static WZ_SHEET_CKService Instance
        {
            get
            {
                return _Instance;
            }
        }
        
    }
}

