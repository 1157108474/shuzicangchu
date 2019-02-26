namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;

    public class Base_Offices_ScopeService : BaseService<Base_Offices_Scope>
    {
        private static readonly Base_Offices_ScopeService _Instance = new Base_Offices_ScopeService();

        public int Insertdata(Base_Offices_ScopeVM[] dataspare, int currID, string OfficesID)
        {
            int num = 0;
            string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            string str2 = Guid.NewGuid().ToString();
            try
            {
                foreach (Base_Offices_ScopeVM evm in dataspare)
                {
                    string sql = "insert into Base_Offices_Scope(OfficesID,GUID ,ScopeType ,ScopeID ,Creator ,CreateDate,ZTID) ";
                    object[] objArray1 = new object[] { sql, "values ('", OfficesID.ToString(), "','", str2, "','", 3, "','", evm.id, "','", currID, "',to_date('", str, "','yyyy-mm-dd hh24:mi:ss'),1000)" };
                    sql = string.Concat(objArray1);
                    num = base.ExecuteNonQuery_Fish(sql);
                }
            }
            catch (Exception)
            {
                num = -1;
            }
            return num;
        }

        public static Base_Offices_ScopeService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

