namespace EWMS.Core
{
    using NPOI.HSSF.UserModel;
    using NPOI.SS.UserModel;
    using NPOI.XSSF.UserModel;
    using Oracle.ManagedDataAccess.Client;
    using System;
    using System.Collections;
    using System.Configuration;
    using System.Data;
    using System.IO;
    using System.Linq;

    public abstract class DbHelperOra
    {
        public static readonly string connectionString = ConfigurationManager.ConnectionStrings["Sys"].ConnectionString;

        private static OracleCommand BuildIntCommand(OracleConnection connection, string storedProcName, IDataParameter[] parameters)
        {
            OracleCommand command1 = BuildQueryCommand(connection, storedProcName, parameters);
            command1.Parameters.Add(new OracleParameter("ReturnValue", OracleDbType.Int32, 4, ParameterDirection.ReturnValue, false, 0, 0, string.Empty, DataRowVersion.Default, null));
            return command1;
        }

        private static OracleCommand BuildQueryCommand(OracleConnection connection, string storedProcName, IDataParameter[] parameters)
        {
            OracleCommand command = new OracleCommand(storedProcName, connection) {
                CommandType = CommandType.StoredProcedure
            };
            foreach (OracleParameter parameter in parameters)
            {
                command.Parameters.Add(parameter);
            }
            return command;
        }

        public static int ExecuteNonQuery(OracleConnection connection, OracleTransaction trans, CommandType cmdType, string cmdText, params OracleParameter[] commandParameters)
        {
            OracleCommand cmd = new OracleCommand();
            PrepareCommand(cmd, connection, trans, cmdType, cmdText, commandParameters);
            int num = cmd.ExecuteNonQuery();
            cmd.Parameters.Clear();
            return num;
        }

        public static OracleDataReader ExecuteReader(string strSQL)
        {
            OracleDataReader reader;
            OracleConnection conn = new OracleConnection(connectionString);
            OracleCommand command = new OracleCommand(strSQL, conn);
            try
            {
                conn.Open();
                reader = command.ExecuteReader(CommandBehavior.CloseConnection);
            }
            catch (Exception exception1)
            {
                throw new Exception(exception1.Message);
            }
            return reader;
        }

        public static OracleDataReader ExecuteReader(string SQLString, params OracleParameter[] cmdParms)
        {
            OracleDataReader reader;
            OracleConnection conn = new OracleConnection(connectionString);
            OracleCommand cmd = new OracleCommand();
            try
            {
                PrepareCommand(cmd, conn, null, SQLString, cmdParms);
                cmd.Parameters.Clear();
                reader = cmd.ExecuteReader(CommandBehavior.CloseConnection);
            }
            catch (Exception exception1)
            {
                throw new Exception(exception1.Message);
            }
            return reader;
        }

        public static int ExecuteSql(string SQLString)
        {
            int num;
            Console.WriteLine(SQLString);
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                using (OracleCommand command = new OracleCommand(SQLString, connection))
                {
                    try
                    {
                        connection.Open();
                        num = command.ExecuteNonQuery();
                    }
                    catch (Exception exception1)
                    {
                        connection.Close();
                        throw new Exception(exception1.Message);
                    }
                    finally
                    {
                        command.Dispose();
                        connection.Close();
                    }
                }
            }
            return num;
        }

        public static int ExecuteSql(string SQLString, string content)
        {
            int num;
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                OracleCommand command = new OracleCommand(SQLString, connection);
                OracleParameter param = new OracleParameter("@content", OracleDbType.NVarchar2) {
                    Value = content
                };
                command.Parameters.Add(param);
                try
                {
                    connection.Open();
                    num = command.ExecuteNonQuery();
                }
                catch (Exception exception1)
                {
                    throw new Exception(exception1.Message);
                }
                finally
                {
                    command.Dispose();
                    connection.Close();
                }
            }
            return num;
        }

        public static int ExecuteSql(string SQLString, params OracleParameter[] cmdParms)
        {
            int num;
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                using (OracleCommand command = new OracleCommand())
                {
                    try
                    {
                        PrepareCommand(command, connection, null, SQLString, cmdParms);
                        command.Parameters.Clear();
                        num = command.ExecuteNonQuery();
                    }
                    catch (Exception exception1)
                    {
                        throw new Exception(exception1.Message);
                    }
                    finally
                    {
                        command.Dispose();
                        connection.Close();
                    }
                }
            }
            return num;
        }

        public static int ExecuteSql(string SQLString, OracleConnection connection, OracleTransaction trans)
        {
            int num;
            OracleCommand command = new OracleCommand(SQLString, connection);
            try
            {
                if (connection.State != ConnectionState.Open)
                {
                    connection.Open();
                }
                command.Transaction = trans;
                num = command.ExecuteNonQuery();
            }
            catch (Exception exception1)
            {
                throw new Exception(exception1.Message);
            }
            finally
            {
                if (command != null)
                {
                    command.Dispose();
                }
            }
            return num;
        }

        public static int ExecuteSqlInsertImg(string strSQL, byte[] fs)
        {
            int num;
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                OracleCommand command = new OracleCommand(strSQL, connection);
                OracleParameter param = new OracleParameter("@fs", OracleDbType.LongRaw) {
                    Value = fs
                };
                command.Parameters.Add(param);
                try
                {
                    connection.Open();
                    num = command.ExecuteNonQuery();
                }
                catch (Exception exception1)
                {
                    throw new Exception(exception1.Message);
                }
                finally
                {
                    command.Dispose();
                    connection.Close();
                }
            }
            return num;
        }

        public static void ExecuteSqlTran(ArrayList SQLStringList)
        {
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                connection.Open();
                OracleCommand command = new OracleCommand {
                    Connection = connection
                };
                OracleTransaction transaction = connection.BeginTransaction();
                command.Transaction = transaction;
                try
                {
                    for (int i = 0; i < SQLStringList.Count; i++)
                    {
                        string str = SQLStringList[i].ToString();
                        if (str.Trim().Length > 1)
                        {
                            command.CommandText = str;
                            command.ExecuteNonQuery();
                        }
                    }
                    transaction.Commit();
                }
                catch (Exception exception1)
                {
                    transaction.Rollback();
                    throw new Exception(exception1.Message);
                }
                finally
                {
                    command.Dispose();
                    connection.Close();
                }
            }
        }

        public static void ExecuteSqlTran(Hashtable SQLStringList)
        {
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                connection.Open();
                using (OracleTransaction transaction = connection.BeginTransaction())
                {
                    OracleCommand cmd = new OracleCommand();
                    try
                    {
                        foreach (DictionaryEntry entry in SQLStringList)
                        {
                            string cmdText = entry.Key.ToString();
                            OracleParameter[] cmdParms = (OracleParameter[]) entry.Value;
                            PrepareCommand(cmd, connection, transaction, cmdText, cmdParms);
                            cmd.ExecuteNonQuery();
                            cmd.Parameters.Clear();
                            transaction.Commit();
                        }
                    }
                    catch
                    {
                        transaction.Rollback();
                        throw;
                    }
                }
            }
        }

        public static string ExecuteSqlTran2(ArrayList SQLStringList)
        {
            string str2;
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                int num = 0;
                connection.Open();
                OracleCommand command = new OracleCommand {
                    Connection = connection
                };
                OracleTransaction transaction = connection.BeginTransaction();
                command.Transaction = transaction;
                try
                {
                    for (int i = 0; i < SQLStringList.Count; i++)
                    {
                        num = i;
                        string str = SQLStringList[i].ToString();
                        if (str.Trim().Length > 1)
                        {
                            command.CommandText = str;
                            command.ExecuteNonQuery();
                        }
                    }
                    transaction.Commit();
                    str2 = "OK";
                }
                catch (Exception exception)
                {
                    transaction.Rollback();
                    str2 = num + ";" + exception.Message;
                }
                finally
                {
                    command.Dispose();
                    connection.Close();
                }
            }
            return str2;
        }

        public static bool Exists(string strSql)
        {
            int num;
            object single = GetSingle(strSql);
            if (object.Equals(single, null) || object.Equals(single, DBNull.Value))
            {
                num = 0;
            }
            else
            {
                num = int.Parse(single.ToString());
            }
            if (num == 0)
            {
                return false;
            }
            return true;
        }

        public static bool Exists(string strSql, params OracleParameter[] cmdParms)
        {
            int num;
            object single = GetSingle(strSql, cmdParms);
            if (object.Equals(single, null) || object.Equals(single, DBNull.Value))
            {
                num = 0;
            }
            else
            {
                num = int.Parse(single.ToString());
            }
            if (num == 0)
            {
                return false;
            }
            return true;
        }

        public static DataTable GetExcelTable(string path, bool isColumnName, ref string errorMessage)
        {
            DataTable table = null;
            FileStream stream = null;
            DataColumn column = null;
            DataRow row = null;
            IWorkbook workbook = null;
            ISheet sheetAt = null;
            IRow source = null;
            ICell cell = null;
            int num = 0;
            try
            {
                using (stream = File.OpenRead(path))
                {
                    if (path.IndexOf(".xlsx") > 0)
                    {
                        workbook = new XSSFWorkbook(stream);
                    }
                    else if (path.IndexOf(".xls") > 0)
                    {
                        workbook = new HSSFWorkbook(stream);
                    }
                    if (workbook != null)
                    {
                        sheetAt = workbook.GetSheetAt(0);
                        table = new DataTable();
                        if (sheetAt != null)
                        {
                            int lastRowNum = sheetAt.LastRowNum;
                            if (lastRowNum > 0)
                            {
                                IRow row3 = sheetAt.GetRow(0);
                                int num3 = row3.LastCellNum - (from x in row3.Cells.AsEnumerable<ICell>()
                                    where string.IsNullOrEmpty(x.ToString())
                                    select x).Count<ICell>();
                                if (isColumnName)
                                {
                                    num = 1;
                                    for (int j = row3.FirstCellNum; j < num3; j++)
                                    {
                                        cell = row3.GetCell(j);
                                        if ((cell != null) && (cell.StringCellValue != null))
                                        {
                                            column = new DataColumn(cell.StringCellValue.Replace("*", ""));
                                            table.Columns.Add(column);
                                        }
                                    }
                                }
                                else
                                {
                                    for (int k = row3.FirstCellNum; k < num3; k++)
                                    {
                                        column = new DataColumn("column" + (k + 1));
                                        table.Columns.Add(column);
                                    }
                                }
                                for (int i = num; i <= lastRowNum; i++)
                                {
                                    source = sheetAt.GetRow(i);
                                    if (source != null)
                                    {
                                        row = table.NewRow();
                                        for (int m = source.FirstCellNum; m < num3; m++)
                                        {
                                            cell = source.GetCell(m);
                                            if (cell == null)
                                            {
                                                row[m] = "";
                                            }
                                            else if ((cell.CellType == CellType.Numeric) && DateUtil.IsCellDateFormatted(cell))
                                            {
                                                row[m] = cell.DateCellValue.ToString("yyyy-MM-dd HH:mm:ss");
                                            }
                                            else
                                            {
                                                row[m] = cell.ToString();
                                            }
                                        }
                                        if (source.Count<ICell>() == num3)
                                        {
                                            table.Rows.Add(row);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return table;
            }
            catch (Exception exception)
            {
                errorMessage = errorMessage + exception.Message + ".<br/>";
                if (stream != null)
                {
                    stream.Close();
                }
                return null;
            }
        }

        public static int GetMaxID(string FieldName, string TableName)
        {
            object single = GetSingle("select max(" + FieldName + ")+1 from " + TableName);
            if (single == null)
            {
                return 1;
            }
            return int.Parse(single.ToString());
        }

        public static object GetSingle(string SQLString)
        {
            object obj3;
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                using (OracleCommand command = new OracleCommand(SQLString, connection))
                {
                    try
                    {
                        connection.Open();
                        object objA = command.ExecuteScalar();
                        if (object.Equals(objA, null) || object.Equals(objA, DBNull.Value))
                        {
                            return null;
                        }
                        obj3 = objA;
                    }
                    catch (Exception exception1)
                    {
                        connection.Close();
                        throw new Exception(exception1.Message);
                    }
                    finally
                    {
                        command.Dispose();
                        connection.Close();
                    }
                }
            }
            return obj3;
        }

        public static object GetSingle(string SQLString, params OracleParameter[] cmdParms)
        {
            object obj3;
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                using (OracleCommand command = new OracleCommand())
                {
                    try
                    {
                        PrepareCommand(command, connection, null, SQLString, cmdParms);
                        object objA = command.ExecuteScalar();
                        command.Parameters.Clear();
                        if (object.Equals(objA, null) || object.Equals(objA, DBNull.Value))
                        {
                            return null;
                        }
                        obj3 = objA;
                    }
                    catch (Exception exception1)
                    {
                        throw new Exception(exception1.Message);
                    }
                    finally
                    {
                        command.Dispose();
                        connection.Close();
                    }
                }
            }
            return obj3;
        }

        private static void PrepareCommand(OracleCommand cmd, OracleConnection conn, OracleTransaction trans, string cmdText, OracleParameter[] cmdParms)
        {
            if (conn.State != ConnectionState.Open)
            {
                conn.Open();
            }
            cmd.Connection = conn;
            cmd.CommandText = cmdText;
            if (trans != null)
            {
                cmd.Transaction = trans;
            }
            cmd.CommandType = CommandType.Text;
            if (cmdParms != null)
            {
                foreach (OracleParameter parameter in cmdParms)
                {
                    if (((parameter.Direction == ParameterDirection.InputOutput) || (parameter.Direction == ParameterDirection.Input)) && (parameter.Value == null))
                    {
                        parameter.Value = DBNull.Value;
                    }
                    cmd.Parameters.Add(parameter);
                }
            }
        }

        private static void PrepareCommand(OracleCommand cmd, OracleConnection conn, OracleTransaction trans, CommandType cmdType, string cmdText, OracleParameter[] cmdParms)
        {
            if (conn.State != ConnectionState.Open)
            {
                conn.Open();
            }
            cmd.Connection = conn;
            cmd.CommandText = cmdText;
            if (trans != null)
            {
                cmd.Transaction = trans;
            }
            cmd.CommandType = cmdType;
            if (cmdParms != null)
            {
                foreach (OracleParameter parameter in cmdParms)
                {
                    if (((parameter.Direction == ParameterDirection.InputOutput) || (parameter.Direction == ParameterDirection.Input)) && (parameter.Value == null))
                    {
                        parameter.Value = DBNull.Value;
                    }
                    cmd.Parameters.Add(parameter);
                }
            }
        }

        public static DataSet Query(string SQLString)
        {
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                DataSet dataSet = new DataSet();
                try
                {
                    connection.Open();
                    new OracleDataAdapter(SQLString, connection).Fill(dataSet, "ds");
                }
                catch (Exception exception1)
                {
                    throw new Exception(exception1.Message);
                }
                finally
                {
                    connection.Close();
                }
                return dataSet;
            }
        }

        public static DataSet Query(string SQLString, params OracleParameter[] cmdParms)
        {
            DataSet set2;
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                OracleCommand cmd = new OracleCommand();
                PrepareCommand(cmd, connection, null, SQLString, cmdParms);
                using (OracleDataAdapter adapter = new OracleDataAdapter(cmd))
                {
                    DataSet dataSet = new DataSet();
                    try
                    {
                        adapter.Fill(dataSet, "ds");
                        cmd.Parameters.Clear();
                    }
                    catch (Exception exception1)
                    {
                        throw new Exception(exception1.Message);
                    }
                    finally
                    {
                        cmd.Dispose();
                        connection.Close();
                    }
                    set2 = dataSet;
                }
            }
            return set2;
        }

        public static OracleDataReader RunProcedure(string storedProcName, IDataParameter[] parameters)
        {
            OracleConnection connection = new OracleConnection(connectionString);
            connection.Open();
            OracleCommand command1 = BuildQueryCommand(connection, storedProcName, parameters);
            command1.CommandType = CommandType.StoredProcedure;
            return command1.ExecuteReader(CommandBehavior.CloseConnection);
        }

        public static DataSet RunProcedure(string storedProcName, IDataParameter[] parameters, string tableName)
        {
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                DataSet dataSet = new DataSet();
                connection.Open();
                new OracleDataAdapter { SelectCommand = BuildQueryCommand(connection, storedProcName, parameters) }.Fill(dataSet, tableName);
                connection.Close();
                return dataSet;
            }
        }

        public static int RunProcedure(string storedProcName, IDataParameter[] parameters, out int rowsAffected)
        {
            using (OracleConnection connection = new OracleConnection(connectionString))
            {
                connection.Open();
                OracleCommand command = BuildIntCommand(connection, storedProcName, parameters);
                rowsAffected = command.ExecuteNonQuery();
                return (int) command.Parameters["ReturnValue"].Value;
            }
        }
         
    }
}

