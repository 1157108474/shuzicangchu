namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;

    public class Base_Person_ScopeService : BaseService<Base_Person_Scope>
    {
        private static readonly Base_Person_ScopeService _Instance = new Base_Person_ScopeService();

        public int Insertdata(Base_Person_ScopeVM[] dataorg, Base_Person_ScopeVM[] datahouse, Base_Person_ScopeVM[] dataspare, Base_Person_ScopeVM[] datadep, int currID, string PersonID)
        {
            int num = 0;
            string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            string str2 = Guid.NewGuid().ToString();
            try
            {
                foreach (Base_Person_ScopeVM evm in dataorg)
                {
                    string sql = "insert into Base_Person_Scope(PersonID,GUID ,ScopeType ,ScopeID ,Creator ,CreateDate,ZTID) ";
                    object[] objArray1 = new object[] { sql, "values ('", PersonID.ToString(), "','", str2, "','", 0, "','", evm.id, "','", currID, "',to_date('", str, "','yyyy-mm-dd hh24:mi:ss'),1000)" };
                    sql = string.Concat(objArray1);
                    num = base.ExecuteNonQuery_Fish(sql);
                }
                foreach (Base_Person_ScopeVM evm2 in datahouse)
                {
                    string str4 = "insert into Base_Person_Scope(PersonID,GUID ,ScopeType ,ScopeID ,Creator ,CreateDate,ZTID) ";
                    object[] objArray2 = new object[] { str4, "values ('", PersonID.ToString(), "','", str2, "','", 2, "','", evm2.id, "','", currID, "',to_date('", str, "','yyyy-mm-dd hh24:mi:ss'),1000)" };
                    str4 = string.Concat(objArray2);
                    num = base.ExecuteNonQuery_Fish(str4);
                }
                foreach (Base_Person_ScopeVM evm3 in dataspare)
                {
                    string str5 = "insert into Base_Person_Scope(PersonID,GUID ,ScopeType ,ScopeID ,Creator ,CreateDate,ZTID) ";
                    object[] objArray3 = new object[] { str5, "values ('", PersonID.ToString(), "','", str2, "','", 3, "','", evm3.id, "','", currID, "',to_date('", str, "','yyyy-mm-dd hh24:mi:ss'),1000)" };
                    str5 = string.Concat(objArray3);
                    num = base.ExecuteNonQuery_Fish(str5);
                }
                foreach (Base_Person_ScopeVM evm4 in datadep)
                {
                    string str6 = "insert into Base_Person_Scope(PersonID,GUID ,ScopeType ,ScopeID ,Creator ,CreateDate,ZTID) ";
                    object[] objArray4 = new object[] { str6, "values ('", PersonID.ToString(), "','", str2, "','", 4, "','", evm4.id, "','", currID, "',to_date('", str, "','yyyy-mm-dd hh24:mi:ss'),1000)" };
                    str6 = string.Concat(objArray4);
                    num = base.ExecuteNonQuery_Fish(str6);
                }
            }
            catch (Exception)
            {
                num = -1;
            }
            return num;
        }

        public static Base_Person_ScopeService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

