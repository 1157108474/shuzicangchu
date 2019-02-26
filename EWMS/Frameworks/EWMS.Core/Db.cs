namespace EWMS.Core
{
    using FluentData;
    using EWMS.Utils;
    using System;
    using System.Collections.Generic;
    using System.Configuration;
    using System.Data;
    using System.Data.SqlClient;

    public class Db
    {
        public static IDbContext Context()
        {
            return Context(APP.Db_Default_ConnName);
        }

        public static IDbContext Context(string connName)
        {
            ConnectionStringSettings settings = ConfigurationManager.ConnectionStrings[connName];
            IDbContext context1 = new DbContext().ConnectionString(settings.ConnectionString, Provider(settings.ProviderName), (string)null);
            context1.OnExecuting(delegate (CommandEventArgs x)
            {
                if (APP.OnDbExecuting != null)
                {
                    APP.OnDbExecuting(x);
                }
                string commandText = x.Command.CommandText;
                for (int j = x.Command.Parameters.Count - 1; j >= 0; j--)
                {
                    IDataParameter parameter = x.Command.Parameters[j] as IDataParameter;
                    commandText = commandText.Replace(parameter.ParameterName, string.Format("'{0}'", parameter.Value));
                }
                LogHelper.WriteDb("DbExecuting执行sql语句：" + commandText);
            });
            context1.OnError(delegate (ErrorEventArgs e)
            {
                SqlException exception = e.Exception as SqlException;
                if (exception != null)
                {
                    string str = "";
                    switch (exception.Number)
                    {
                        case 0xa43:
                            str = "主键重复，更新失败！";
                            break;

                        case 0xfdc:
                            str = "数据库不可用,请检查系统设置后重试！";
                            break;

                        case 0x4818:
                            str = "登陆数据库失败！";
                            break;

                        case -2:
                            str = "超时时间已到。在操作完成之前超时时间已过或服务器未响应";
                            break;

                        case 0x223:
                            str = "数据已经被引用，更新失败，请先删除引用数据并重试！";
                            break;
                    }
                    if (!string.IsNullOrEmpty(str))
                    {
                        throw new Exception(str);
                    }
                }
            });
            return context1;
        }

        public static IDbProvider Provider(string providerName)
        {
            Dictionary<string, IDbProvider> dictionary = new Dictionary<string, IDbProvider> {
                {
                    "DB2",
                    new DB2Provider()
                },
                {
                    "MySql",
                    new MySqlProvider()
                },
                {
                    "Oracle",
                    new OracleProvider()
                },
                {
                    "PostgreSql",
                    new PostgreSqlProvider()
                },
                {
                    "SqlAzure",
                    new SqlAzureProvider()
                },
                {
                    "Sqlite",
                    new SqliteProvider()
                },
                {
                    "SqlServerCompact",
                    new SqlServerCompactProvider()
                },
                {
                    "SqlServer",
                    new SqlServerProvider()
                },
                {
                    "Oracle.ManagedDataAccess.Client",
                      new OracleProvider()
                }
            };
            if (dictionary[providerName] == null)
            {
                return new SqlServerProvider();
            }
            return dictionary[providerName];
        }

    }
}

