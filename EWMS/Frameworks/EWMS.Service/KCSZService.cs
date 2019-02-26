namespace EWMS.Service
{
    using EWMS.Core;
    using Oracle.ManagedDataAccess.Client;
    using System;
    using System.Data;
    using EWMS.Entity;

    public class KCSZService : BaseService<WZ_Stock>
    {
        private static readonly KCSZService _Instance = new KCSZService();

        public DataSet GetStore(string ProName, string sqlInto, string SqlOther, string sqlwldate, int pageIndex, int pageSize, out int pageCount, out int total)
        {
            OracleParameter[] parameters = new OracleParameter[7];
            OracleParameter parameter = new OracleParameter("Store_SZ", OracleDbType.RefCursor) {
                Direction = ParameterDirection.Output
            };
            OracleParameter parameter2 = new OracleParameter("total", OracleDbType.Int32) {
                Direction = ParameterDirection.Output
            };
            OracleParameter parameter3 = new OracleParameter("sqlInto", OracleDbType.NVarchar2) {
                Direction = ParameterDirection.Input,
                Value = sqlInto
            };
            OracleParameter parameter4 = new OracleParameter("otherwhere", OracleDbType.NVarchar2) {
                Direction = ParameterDirection.Input,
                Value = SqlOther
            };
            OracleParameter parameter5 = new OracleParameter("wldatewhere", OracleDbType.NVarchar2) {
                Direction = ParameterDirection.Input,
                Value = sqlwldate
            };
            OracleParameter parameter6 = new OracleParameter("pageIndex", OracleDbType.Int32) {
                Direction = ParameterDirection.Input,
                Value = pageIndex
            };
            OracleParameter parameter7 = new OracleParameter("pageSize", OracleDbType.Int32) {
                Direction = ParameterDirection.Input,
                Value = pageSize
            };
            parameters[0] = parameter;
            parameters[1] = parameter2;
            parameters[2] = parameter3;
            parameters[3] = parameter4;
            parameters[4] = parameter5;
            parameters[5] = parameter6;
            parameters[6] = parameter7;
            total = int.Parse(parameters[1].Value.ToString());
            pageCount = (int) Math.Ceiling((decimal) (total / pageSize));
            return DbHelperOra.RunProcedure(ProName, parameters, "comTable");
        }

        public static KCSZService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

