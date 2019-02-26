namespace EWMS.Core
{
    using ClownFish;
    using FluentData;
    using EWMS.Utils;
    using Microsoft.CSharp.RuntimeBinder;
    using Oracle.ManagedDataAccess.Client;
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Data;
    using System.Dynamic;
    using System.Linq;
    using System.Linq.Expressions;
    using System.Reflection;
    using System.Runtime.CompilerServices;
    using System.Runtime.InteropServices;
    using System.Text;

    public class BaseService<T> where T : BaseEntity, new()
    {
        private IDbContext _db;

        public BaseService()
        {
            this.ModuleName = AttributeHelper.GetModuleName(base.GetType());
            this.CommandResult = new EWMS.Core.CommandResult();
        }

        public BaseService(string moduleName)
        {
            this.ModuleName = moduleName;
            this.CommandResult = new EWMS.Core.CommandResult();
        }

        protected IDeleteBuilder BuilderParse(ParamDelete param)
        {
            ParamDeleteData data = param.GetData();
            IDeleteBuilder deleteBuilder = this.db.Delete((data.From.Length == 0) ? typeof(T).Name : data.From);
            data.Where.ForEach(delegate (ParamWhere item)
            {
                deleteBuilder.Where(item.Data.Column, item.Data.Value, DataTypes.Object, 0);
            });
            return deleteBuilder;
        }

        protected IInsertBuilder BuilderParse(ParamInsert param)
        {
            ParamInsertData data = param.GetData();
            IInsertBuilder builder = this.db.Insert((data.TableName.Length == 0) ? typeof(T).Name : data.TableName);
            Dictionary<string, object> dict = BaseService<T>.GetCommonFieldValueForAdd();
            foreach (KeyValuePair<string, object> pair in data.Columns.Where(column => !dict.ContainsKey(column.Key)))
            {
                builder.Column(pair.Key, pair.Value, DataTypes.Object, 0);
            }
            Dictionary<string, PropertyInfo> properties = ReflectionUtil.GetProperties(typeof(T));
            foreach (KeyValuePair<string, object> pair2 in dict.Where(item => properties.ContainsKey(item.Key.ToLower())))
            {
                builder.Column(pair2.Key, pair2.Value, DataTypes.Object, 0);
            }
            return builder;
        }

        protected ISelectBuilder<T> BuilderParse(ParamQuery param)
        {
            if (param == null)
            {
                param = new ParamQuery();
            }
            ParamQueryData data = param.GetData();
            string sql = (data.From.Length == 0) ? typeof(T).Name : data.From;
            string str2 = string.IsNullOrEmpty(data.Select) ? (sql + ".*") : data.Select;
            ISelectBuilder<T> builder = this.db.Select<T>(str2).From(sql).Where(data.WhereSql).GroupBy(data.GroupBy).Having(data.Having).OrderBy(data.OrderBy).Paging(data.PageIndex, data.PageSize);
            builder.Data.Command.Data.Context.Data.FluentDataProvider.GetSqlForSelectBuilder(builder.Data);
            return builder;
        }

        protected IStoredProcedureBuilder BuilderParse(ParamSP param)
        {
            ParamSPData data = param.GetData();
            IStoredProcedureBuilder builder = this.db.StoredProcedure(data.Name);
            foreach (KeyValuePair<string, object> pair in data.Parameter)
            {
                builder.Parameter(pair.Key, pair.Value, DataTypes.Object, 0);
            }
            foreach (KeyValuePair<string, DataTypes> pair2 in data.ParameterOut)
            {
                builder.ParameterOut(pair2.Key, pair2.Value, 0);
            }
            return builder;
        }

        protected IUpdateBuilder BuilderParse(ParamUpdate param)
        {
            ParamUpdateData data = param.GetData();
            IUpdateBuilder updateBuilder = this.db.Update((data.Update.Length == 0) ? typeof(T).Name : data.Update);
            Dictionary<string, object> dict = BaseService<T>.GetCommonFieldValueForEdit();
            foreach (KeyValuePair<string, object> pair in data.Columns.Where(column => !dict.ContainsKey(column.Key)))
            {
                updateBuilder.Column(pair.Key, pair.Value, DataTypes.Object, 0);
            }
            Dictionary<string, PropertyInfo> properties = ReflectionUtil.GetProperties(typeof(T));
            foreach (KeyValuePair<string, object> pair2 in dict.Where(item => properties.ContainsKey(item.Key.ToLower())))
            {
                updateBuilder.Column(pair2.Key, pair2.Value, DataTypes.Object, 0);
            }
            data.Where.ForEach(delegate (ParamWhere item)
            {
                updateBuilder.Where(item.Data.Column, item.Data.Value, DataTypes.Object, 0);
            });
            return updateBuilder;
        }

        public IList<T> DataSetToList<T>(DataSet dataset, int tableIndex)
        {
            if (((dataset == null) || (dataset.Tables.Count <= 0)) || (tableIndex < 0))
            {
                return null;
            }
            DataTable table = dataset.Tables[tableIndex];
            IList<T> list = new List<T>();
            for (int i = 0; i < table.Rows.Count; i++)
            {
                T local = Activator.CreateInstance<T>();
                PropertyInfo[] properties = local.GetType().GetProperties();
                for (int j = 1; j < table.Columns.Count; j++)
                {
                    foreach (PropertyInfo info in properties)
                    {
                        if (table.Columns[j].ColumnName.ToUpper().Equals(info.Name.ToUpper()))
                        {
                            if (table.Rows[i][j] != DBNull.Value)
                            {
                                if (table.Rows[i][j].GetType() == typeof(decimal))
                                {
                                    info.SetValue(local, int.Parse(table.Rows[i][j].ToString()), null);
                                }
                                else
                                {
                                    info.SetValue(local, table.Rows[i][j], null);
                                }
                            }
                            else
                            {
                                info.SetValue(local, null, null);
                            }
                            break;
                        }
                    }
                }
                list.Add(local);
            }
            return list;
        }

        public int Delete(ParamDelete param)
        {
            int num = 0;
            this.db.UseTransaction(true);
            DeleteEventArgs arg = new DeleteEventArgs
            {
                db = this.db,
                data = param.GetData()
            };
            if (this.OnBeforeDelete(arg))
            {
                num = this.BuilderParse(param).Execute();
                this.CommandResult.Set(true, APP.Msg_Delete_Success);
                DeleteEventArgs args2 = new DeleteEventArgs
                {
                    db = this.db,
                    data = param.GetData(),
                    executeValue = num
                };
                this.OnAfterDelete(args2);
                this.db.Commit();
            }
            return num;
        }

        public int DeleteByPrimaryField(object value)
        {
            TableAttribute customAttribute = AttributeHelper.GetCustomAttribute<TableAttribute>(typeof(T));
            ParamDelete param = ParamDelete.Instance().From(customAttribute.TableName).AndWhere(customAttribute.PrimaryField, value, null, new object[0]);
            return this.Delete(param);
        }

        public int DeleteBySql(string sql)
        {
            return this.db.Sql(sql, null).Execute();
        }

        public int DeleteByWhere(string where)
        {
            where = string.IsNullOrEmpty(where) ? " 1=1 " : (" 1=1 " + where);
            string sql = string.Format("delete from {0} where {1}", typeof(T).Name, where);
            this.db.Commit();
            return this.db.Sql(sql, null).Execute();
        }

        public int ExecuteNonQuery_Fish(string sql)
        {
            return DbHelper.ExecuteNonQuery(sql, null, CommandKind.SqlTextNoParams);
        }

        public string ExecuteTran(ArrayList SQLStringList)
        {
            string message;
            ClownFish.DbContext dbContext = new ClownFish.DbContext(true);
            try
            {
                for (int i = 0; i < SQLStringList.Count; i++)
                {
                    string nameOrSql = SQLStringList[i].ToString();
                    if (nameOrSql.Trim().Length > 1)
                    {
                        DbHelper.ExecuteNonQuery(nameOrSql, "", dbContext, CommandKind.SqlTextNoParams);
                    }
                }
                dbContext.CommitTransaction();
                message = "OK";
            }
            catch (Exception exception1)
            {
                message = exception1.Message;
            }
            finally
            {
                if (dbContext != null)
                {
                    dbContext.Dispose();
                }
            }
            return message;
        }

        public bool Exists_Fish(string condition)
        {
            return (DbHelper.ExecuteScalar(string.Format("select * from {0} where 1=1 {1} ", typeof(T).Name, condition), null, CommandKind.SqlTextNoParams) != null);
        }

        ~BaseService()
        {
            try
            {
                this.db.Dispose();
            }
            catch
            {
            }
        }

        public static Dictionary<string, object> GetCommonFieldName()
        {
            return new Dictionary<string, object> {
                {
                    APP.Field_AddBy,
                    ""
                },
                {
                    APP.Field_AddOn,
                    DateTime.Now
                },
                {
                    APP.Field_EditBy,
                    ""
                },
                {
                    APP.Field_EditOn,
                    DateTime.Now
                },
                {
                    "tempid",
                    0
                }
            };
        }

        public static List<string> GetCommonFieldNameList()
        {
            return new List<string> {
                APP.Field_AddBy,
                APP.Field_AddOn,
                APP.Field_EditBy,
                APP.Field_EditOn,
                "tempid"
            };
        }

        private static Dictionary<string, object> GetCommonFieldValueForAdd()
        {
            BaseLoginer loginer = FormsAuth.GetBaseLoginerData();
            string userName = "sysadmin";
            if (loginer != null)
                userName = loginer.UserName;
            return new Dictionary<string, object> {
                {
                    APP.Field_AddBy,
                    userName
                },
                {
                    APP.Field_AddOn,
                    DateTime.Now
                }
            };
        }

        private static Dictionary<string, object> GetCommonFieldValueForEdit()
        {
            BaseLoginer loginer = FormsAuth.GetBaseLoginerData();
            string userName = "sysadmin";
            if (loginer != null)
                userName = loginer.UserName;
            return new Dictionary<string, object> {
                {
                    APP.Field_EditBy,
                    userName
                },
                {
                    APP.Field_EditOn,
                    DateTime.Now
                }
            };
        }

        public K GetDataItem<K>(string spName, object inputParams) where K : class, new()
        {
            return DbHelper.GetDataItem<K>(spName, inputParams, CommandKind.StoreProcedure);
        }

        public DataSet GetdatasetPageList(string tableName, string columns, string where, string order, int pageIndex, int pageSize, out int pageCount, out int total)
        {
            pageCount = 0;
            total = 0;
            if (string.IsNullOrEmpty(order.Trim()))
            {
                DataTable table = this.GetDataTable_Fish("select a.column_name from user_cons_columns a, user_constraints b where a.constraint_name = b.constraint_name and b.constraint_type = 'P' and a.table_name = '" + tableName.ToUpper() + "'");
                if ((table != null) && (table.Rows.Count > 0))
                {
                    order = table.Rows[0][0].ToString();
                }
            }
            OracleParameter[] parameters = new OracleParameter[8];
            OracleParameter parameter = new OracleParameter("tableName", OracleDbType.Varchar2, 0x3e8)
            {
                Direction = System.Data.ParameterDirection.Input,
                Value = tableName
            };
            OracleParameter parameter2 = new OracleParameter("columns", OracleDbType.Varchar2, 0x3e8)
            {
                Value = columns,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter3 = new OracleParameter("where", OracleDbType.Varchar2, 0x3e8)
            {
                Value = where,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter4 = new OracleParameter("order", OracleDbType.Varchar2, 0x3e8)
            {
                Value = order.Trim(),
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter5 = new OracleParameter("pageIndex", OracleDbType.Int32)
            {
                Value = pageIndex,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter6 = new OracleParameter("pageSize", OracleDbType.Int32)
            {
                Direction = System.Data.ParameterDirection.Input,
                Value = pageSize
            };
            OracleParameter parameter7 = new OracleParameter("total", OracleDbType.Int32)
            {
                Direction = System.Data.ParameterDirection.Output
            };
            OracleParameter parameter8 = new OracleParameter("p_cursor", OracleDbType.RefCursor)
            {
                Direction = System.Data.ParameterDirection.Output
            };
            parameters[0] = parameter;
            parameters[1] = parameter2;
            parameters[2] = parameter3;
            parameters[3] = parameter4;
            parameters[4] = parameter5;
            parameters[5] = parameter6;
            parameters[6] = parameter7;
            parameters[7] = parameter8;
            total = int.Parse(parameters[6].Value.ToString());
            pageCount = (int)Math.Ceiling((decimal)(total / pageSize));
            return DbHelperOra.RunProcedure("com_pagination_fluent", parameters, "comTable");
        }

        public DataTable GetDataTable_Fish(string sql)
        {
            return DbHelper.FillDataTable(sql, null, CommandKind.SqlTextNoParams);
        }

        public DataTable GetDataTable_Fish(string spName, object inputParams)
        {
            return DbHelper.FillDataTable(spName, inputParams, CommandKind.StoreProcedure);
        }

        public dynamic GetDynamic(ParamQuery param)
        {
            //TODO
            new ExpandoObject();
            string sqlForSelectBuilder = this.BuilderParse(param).Data.Command.Data.Context.Data.FluentDataProvider.GetSqlForSelectBuilder(this.BuilderParse(param).Data);
            return this.BuilderParse(param).Data.Command.Sql(sqlForSelectBuilder).QuerySingle<object>((Action<object, FluentData.IDataReader>)null);
        }

        public dynamic GetDynamic(string sql)
        {
            return this.db.Sql(sql, new object[0]).QuerySingle<object>((Action<object, FluentData.IDataReader>)null);
        }

        public dynamic GetDynamicList(ParamQuery param = null)
        {
            new List<object>();
            string sqlForSelectBuilder = this.BuilderParse(param).Data.Command.Data.Context.Data.FluentDataProvider.GetSqlForSelectBuilder(this.BuilderParse(param).Data);
            return this.BuilderParse(param).Data.Command.Sql(sqlForSelectBuilder).QueryMany<object>((Action<object, FluentData.IDataReader>)null);
        }

        public List<dynamic> GetDynamicList(string sql)
        {
            return this.db.Sql(sql, new object[0]).QueryMany<object>((Action<object, FluentData.IDataReader>)null);
        }

        public dynamic GetDynamicPageList(ParamQuery param = null)
        {
            dynamic obj2 = new ExpandoObject();
            obj2.rows = this.GetDynamicList(param);
            obj2.total = QueryRowCount(param, obj2.rows);
            return obj2;
        }
        public dynamic GetDynamicPageList(ParamSP param = null)
        {
            return this.BuilderParse(param).QueryMany<object>((Action<object, FluentData.IDataReader>)null);
        }

        public dynamic GetDynamicPageList(string tableName, string columns, string where, string order, int pageIndex, int pageSize, out int pageCount, out int total)
        {
            pageCount = 0;
            total = 0;
            if (string.IsNullOrEmpty(order.Trim()))
            {
                DataTable table = this.GetDataTable_Fish("select a.column_name from user_cons_columns a, user_constraints b where a.constraint_name = b.constraint_name and b.constraint_type = 'P' and a.table_name = '" + tableName.ToUpper() + "'");
                if ((table != null) && (table.Rows.Count > 0))
                {
                    order = table.Rows[0][0].ToString();
                }
            }
            OracleParameter[] parameters = new OracleParameter[8];
            OracleParameter parameter = new OracleParameter("tableName", OracleDbType.Varchar2, 0x3e8)
            {
                Direction = System.Data.ParameterDirection.Input,
                Value = tableName
            };
            OracleParameter parameter2 = new OracleParameter("columns", OracleDbType.Varchar2, 0x3e8)
            {
                Value = columns,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter3 = new OracleParameter("where", OracleDbType.Varchar2, 0x3e8)
            {
                Value = where,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter4 = new OracleParameter("order", OracleDbType.Varchar2, 0x3e8)
            {
                Value = order.Trim(),
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter5 = new OracleParameter("pageIndex", OracleDbType.Int32)
            {
                Value = pageIndex,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter6 = new OracleParameter("pageSize", OracleDbType.Int32)
            {
                Direction = System.Data.ParameterDirection.Input,
                Value = pageSize
            };
            OracleParameter parameter7 = new OracleParameter("total", OracleDbType.Int32)
            {
                Direction = System.Data.ParameterDirection.Output
            };
            OracleParameter parameter8 = new OracleParameter("p_cursor", OracleDbType.RefCursor)
            {
                Direction = System.Data.ParameterDirection.Output
            };
            parameters[0] = parameter;
            parameters[1] = parameter2;
            parameters[2] = parameter3;
            parameters[3] = parameter4;
            parameters[4] = parameter5;
            parameters[5] = parameter6;
            parameters[6] = parameter7;
            parameters[7] = parameter8;
            DataSet obj2 = DbHelperOra.RunProcedure("com_pagination_fluent", parameters, "comTable");
            total = int.Parse(parameters[6].Value.ToString());
            pageCount = (int)Math.Ceiling((decimal)(total / pageSize));
            dynamic obj3 = new ExpandoObject();
            //obj3.code = "0";
            //obj3.msg = "";
            //obj3.count = total;
            //obj3.data = obj2.Tables["comTable"];
            obj3.rows = obj2;
            obj3.total = total;
            return obj3;
        }

        public dynamic GetDynamicPageList1(string tableName, string columns, string where, string order, int pageIndex, int pageSize, out int pageCount, out int total)
        {
            pageCount = 0;
            total = 0;
            if (string.IsNullOrEmpty(order.Trim()))
            {
                DataTable table = this.GetDataTable_Fish("select a.column_name from user_cons_columns a, user_constraints b where a.constraint_name = b.constraint_name and b.constraint_type = 'P' and a.table_name = '" + tableName.ToUpper() + "'");
                if ((table != null) && (table.Rows.Count > 0))
                {
                    order = table.Rows[0][0].ToString();
                }
            }
            OracleParameter[] parameters = new OracleParameter[8];
            OracleParameter parameter = new OracleParameter("tableName", OracleDbType.Varchar2, 0x3e8)
            {
                Direction = System.Data.ParameterDirection.Input,
                Value = tableName
            };
            OracleParameter parameter2 = new OracleParameter("columns", OracleDbType.Varchar2, 0x3e8)
            {
                Value = columns,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter3 = new OracleParameter("where", OracleDbType.Varchar2, 0x3e8)
            {
                Value = where,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter4 = new OracleParameter("order", OracleDbType.Varchar2, 0x3e8)
            {
                Value = order.Trim(),
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter5 = new OracleParameter("pageIndex", OracleDbType.Int32)
            {
                Value = pageIndex,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter6 = new OracleParameter("pageSize", OracleDbType.Int32)
            {
                Direction = System.Data.ParameterDirection.Input,
                Value = pageSize
            };
            OracleParameter parameter7 = new OracleParameter("total", OracleDbType.Int32)
            {
                Direction = System.Data.ParameterDirection.Output
            };
            OracleParameter parameter8 = new OracleParameter("p_cursor", OracleDbType.RefCursor)
            {
                Direction = System.Data.ParameterDirection.Output
            };
            parameters[0] = parameter;
            parameters[1] = parameter2;
            parameters[2] = parameter3;
            parameters[3] = parameter4;
            parameters[4] = parameter5;
            parameters[5] = parameter6;
            parameters[6] = parameter7;
            parameters[7] = parameter8;
            DataSet obj2 = DbHelperOra.RunProcedure("com_pagination_fluent", parameters, "comTable");
            total = int.Parse(parameters[6].Value.ToString());
            pageCount = (int)Math.Ceiling((decimal)(total / pageSize));
            dynamic obj3 = new ExpandoObject();
            obj3.code = "0";
            obj3.msg = "";
            obj3.count = total;
            obj3.data = obj2.Tables["comTable"];
            return obj3;
        }

        public T GetEntity(ParamQuery param)
        {
            Activator.CreateInstance<T>();
            return this.BuilderParse(param).QuerySingle((Action<T, FluentData.IDataReader>)null);
        }

        public T GetEntity(object value)
        {
            TableAttribute customAttribute = AttributeHelper.GetCustomAttribute<TableAttribute>(typeof(T));
            ParamQuery param = ParamQuery.Instance().From(customAttribute.TableName).ClearWhere().AndWhere(customAttribute.PrimaryField, value, null, new object[0]);
            return this.GetEntity(param);
        }

        public T GetEntity(string sql)
        {
            return this.db.Sql(sql, new object[0]).QuerySingle<T>((Action<T, FluentData.IDataReader>)null);
        }

        public T GetEntity_Fish(string condition)
        {
            condition = string.IsNullOrEmpty(condition) ? " 1=1 " : (" 1=1 " + condition);
            return DbHelper.GetDataItem<T>(string.Format("select * from {0} where {1}", typeof(T).Name, condition), null, CommandKind.SqlTextNoParams);
        }

        public static OracleDbType GetEntityDataType(string PropertiyName)
        {
            foreach (PropertyInfo info in new EWMS.Core.CommandResult().GetType().GetProperties())
            {
                if (info.Name == PropertiyName)
                {
                    if (info.PropertyType == typeof(string))
                    {
                        return OracleDbType.Varchar2;
                    }
                    if (info.PropertyType == typeof(int))
                    {
                        return OracleDbType.Int32;
                    }
                }
            }
            return OracleDbType.Varchar2;
        }

        public static EWMS.Core.CommandResult GetEntityObject(OracleParameter[] parameters)
        {
            PropertyInfo[] properties = new EWMS.Core.CommandResult().GetType().GetProperties();
            object obj2 = Activator.CreateInstance(Type.GetType("EWMS.Core.CommandResult"), (object[])null);
            foreach (OracleParameter parameter in parameters)
            {
                if (System.Data.ParameterDirection.Output == parameter.Direction)
                {
                    foreach (PropertyInfo info in properties)
                    {
                        if (info.Name == parameter.ParameterName)
                        {
                            if (info.PropertyType == typeof(int))
                            {
                                int num3 = int.Parse(parameter.Value.ToString());
                                info.SetValue(obj2, num3, null);
                            }
                            else if (info.PropertyType == typeof(string))
                            {
                                info.SetValue(obj2, parameter.Value.ToString(), null);
                            }
                        }
                    }
                }
            }
            return (EWMS.Core.CommandResult)obj2;
        }

        public ArrayList GetInsertSqlList(List<T> dataList)
        {
            ArrayList list = new ArrayList();
            foreach (T local in dataList)
            {
                StringBuilder builder = new StringBuilder();
                StringBuilder builder2 = new StringBuilder();
                builder.Append("insert into " + typeof(T).Name + "(");
                builder2.Append("  VALUES (");
                TableAttribute customAttribute = AttributeHelper.GetCustomAttribute<TableAttribute>(typeof(T));
                PropertyInfo[] properties = typeof(T).GetProperties();
                for (int i = 0; i < properties.Length; i++)
                {
                    string name = properties[i].Name;
                    bool flag = BaseService<T>.GetCommonFieldName().ContainsKey(name);
                    if (!(((name == "tempid") || (properties[i].Name == customAttribute.IdentityField)) | flag) && ((customAttribute.IgnoreInsertFieldsList == null) || !customAttribute.IgnoreInsertFieldsList.Contains(name)))
                    {
                        if (i != (properties.Length - 2))
                        {
                            builder.Append(name + ",");
                            if (properties[i].PropertyType == typeof(string))
                            {
                                builder2.Append("'" + local.GetValue(name) + "',");
                            }
                            else if (properties[i].PropertyType == typeof(DateTime))
                            {
                                if (local.GetValue(name).ToString().Trim() == "0001/1/1 0:00:00")
                                {
                                    builder2.Append("'',");
                                }
                                else
                                {
                                    builder2.Append("to_date('" + local.GetValue(name) + "', 'yyyy-mm-dd hh24:mi:ss'),");
                                }
                            }
                            else
                            {
                                builder2.Append(local.GetValue(name) + ",");
                            }
                        }
                        else
                        {
                            builder.Append(name + ") ");
                            if (properties[i].PropertyType == typeof(string))
                            {
                                builder2.Append("'" + local.GetValue(name) + "')");
                            }
                            else if (properties[i].PropertyType == typeof(DateTime))
                            {
                                if (local.GetValue(name).ToString().Trim() == "0001/1/1 0:00:00")
                                {
                                    builder2.Append("'')");
                                }
                                else
                                {
                                    builder2.Append("to_date('" + local.GetValue(name) + "', 'yyyy-mm-dd hh24:mi:ss'))");
                                }
                            }
                            else
                            {
                                builder2.Append(local.GetValue(name) + ")");
                            }
                        }
                    }
                }
                list.Add(builder.ToString() + builder2.ToString());
            }
            return list;
        }

        public List<T> GetList(ParamQuery param = null)
        {
            new List<T>();
            return this.BuilderParse(param).QueryMany((Action<T, FluentData.IDataReader>)null);
        }

        public List<T> GetList(string sql)
        {
            new List<T>();
            return this.db.Sql(sql, new object[0]).QueryMany<T>((Action<T, FluentData.IDataReader>)null);
        }

        public List<T> GetList(bool flagSql, string sqlOrconfition)
        {
            new List<T>();
            if (flagSql)
            {
                return this.db.Sql(sqlOrconfition, new object[0]).QueryMany<T>((Action<T, FluentData.IDataReader>)null);
            }
            string str = string.IsNullOrEmpty(sqlOrconfition) ? " 1=1 " : (" 1=1 " + sqlOrconfition);
            string sql = string.Format("select * from {0} where {1}", typeof(T).Name, str);
            return this.db.Sql(sql, new object[0]).QueryMany<T>((Action<T, FluentData.IDataReader>)null);
        }

        public List<T> GetList_Fish(string sql)
        {
            return DbHelper.FillList<T>(sql, null, CommandKind.SqlTextNoParams);
        }

        public List<T> GetList_Fish(string sqlOrCondition, bool flagCondition)
        {
            if (!flagCondition)
            {
                return DbHelper.FillList<T>(sqlOrCondition, null, CommandKind.SqlTextNoParams);
            }
            string str = string.IsNullOrEmpty(sqlOrCondition) ? " 1=1 " : (" 1=1 " + sqlOrCondition);
            return DbHelper.FillList<T>(string.Format("select * from {0} where {1}", typeof(T).Name, str), null, CommandKind.SqlTextNoParams);
        }

        public List<K> GetList_Fish<K>(string spName, object inputParams) where K : class, new()
        {
            return DbHelper.FillList<K>(spName, inputParams, CommandKind.StoreProcedure);
        }

        public static OracleParameter GetMakeParam(string ParamName, OracleDbType otype, int size, string Value)
        {
            return new OracleParameter(ParamName, Value)
            {
                OracleDbType = otype,
                Size = size
            };
        }

        public static void GetoType(string key, DataTable dt, out OracleDbType otype, out int size)
        {
            DataView defaultView = dt.DefaultView;
            defaultView.RowFilter = "column_name='" + key + "'";
            switch (defaultView[0]["data_type"].ToString().ToUpper())
            {
                case "DATE":
                    otype = OracleDbType.Date;
                    size = int.Parse(defaultView[0]["data_length"].ToString());
                    return;

                case "CHAR":
                    otype = OracleDbType.Char;
                    size = int.Parse(defaultView[0]["data_length"].ToString());
                    return;

                case "LONG":
                    otype = OracleDbType.Double;
                    size = int.Parse(defaultView[0]["data_length"].ToString());
                    return;

                case "NVARCHAR2":
                    otype = OracleDbType.NVarchar2;
                    size = int.Parse(defaultView[0]["data_length"].ToString());
                    return;

                case "VARCHAR2":
                    otype = OracleDbType.NVarchar2;
                    size = int.Parse(defaultView[0]["data_length"].ToString());
                    return;
            }
            otype = OracleDbType.NVarchar2;
            size = 100;
        }

        public static void GetoType(string fType, DataView dv, out OracleDbType otype, out int size)
        {
            if (fType != "DATE")
            {
                if (fType == "CHAR")
                {
                    otype = OracleDbType.Char;
                    size = int.Parse(dv[0]["data_length"].ToString());
                }
                else if (fType == "LONG")
                {
                    otype = OracleDbType.Double;
                    size = int.Parse(dv[0]["data_length"].ToString());
                }
                else if (fType == "NVARCHAR2")
                {
                    otype = OracleDbType.NVarchar2;
                    size = int.Parse(dv[0]["data_length"].ToString());
                }
                else if (fType == "VARCHAR2")
                {
                    otype = OracleDbType.NVarchar2;
                    size = int.Parse(dv[0]["data_length"].ToString());
                }
                else
                {
                    otype = OracleDbType.NVarchar2;
                    size = 100;
                }
            }
            else
            {
                otype = OracleDbType.Date;
                size = int.Parse(dv[0]["data_length"].ToString());
            }
        }

        public List<T> GetPageList(string where, int pageIndex, int pageSize, out int pageCount, out int total)
        {
            pageCount = 0;
            total = 0;
            TableAttribute customAttribute = AttributeHelper.GetCustomAttribute<TableAttribute>(typeof(T));
            if (customAttribute == null)
            {
                throw new Exception("实体对象（" + typeof(T).Name + "）不支持TableAttribute特性");
            }
            return this.GetPageList(typeof(T).Name, "*", where, customAttribute.Order, pageIndex, pageSize, out pageCount, out total);
        }

        public List<T> GetPageList(string where, string order, int pageIndex, int pageSize, out int pageCount, out int total)
        {
            pageCount = 0;
            total = 0;
            return this.GetPageList(typeof(T).Name, "*", where, order, pageIndex, pageSize, out pageCount, out total);
        }

        public List<T> GetPageList(string tableName, string columns, string where, string order, int pageIndex, int pageSize, out int pageCount, out int total)
        {
            pageCount = 0;
            total = 0;
            if (string.IsNullOrEmpty(order.Trim()))
            {
                DataTable table = this.GetDataTable_Fish("select a.column_name from user_cons_columns a, user_constraints b where a.constraint_name = b.constraint_name and b.constraint_type = 'P' and a.table_name = '" + tableName.ToUpper() + "'");
                if ((table != null) && (table.Rows.Count > 0))
                {
                    order = table.Rows[0][0].ToString();
                }
            }
            OracleParameter[] parameters = new OracleParameter[8];
            OracleParameter parameter = new OracleParameter("tableName", OracleDbType.Varchar2, 0x3e8)
            {
                Direction = System.Data.ParameterDirection.Input,
                Value = tableName
            };
            OracleParameter parameter2 = new OracleParameter("columns", OracleDbType.Varchar2, 0x3e8)
            {
                Value = columns,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter3 = new OracleParameter("where", OracleDbType.Varchar2, 0x3e8)
            {
                Value = where,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter4 = new OracleParameter("order", OracleDbType.Varchar2, 0x3e8)
            {
                Value = order.Trim(),
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter5 = new OracleParameter("pageIndex", OracleDbType.Int32)
            {
                Value = pageIndex,
                Direction = System.Data.ParameterDirection.Input
            };
            OracleParameter parameter6 = new OracleParameter("pageSize", OracleDbType.Int32)
            {
                Direction = System.Data.ParameterDirection.Input,
                Value = pageSize
            };
            OracleParameter parameter7 = new OracleParameter("total", OracleDbType.Int32)
            {
                Direction = System.Data.ParameterDirection.Output
            };
            OracleParameter parameter8 = new OracleParameter("p_cursor", OracleDbType.RefCursor)
            {
                Direction = System.Data.ParameterDirection.Output
            };
            parameters[0] = parameter;
            parameters[1] = parameter2;
            parameters[2] = parameter3;
            parameters[3] = parameter4;
            parameters[4] = parameter5;
            parameters[5] = parameter6;
            parameters[6] = parameter7;
            parameters[7] = parameter8;
            object obj2 = DbHelperOra.RunProcedure("com_pagination_fluent", parameters, "comTable");
            total = int.Parse(parameters[6].Value.ToString());
            pageCount = (int)Math.Ceiling((decimal)(total / pageSize));
            //TODO
            return (List<T>)obj2;
        }

        public List<T> GetPageList_Fish(string where, int pageIndex, int pageSize, out int pageCount, out int total)
        {
            pageCount = 0;
            total = 0;
            TableAttribute customAttribute = AttributeHelper.GetCustomAttribute<TableAttribute>(typeof(T));
            PagingInfoExtension inputParams = new PagingInfoExtension
            {
                TableName = customAttribute.TableName,
                Columns = "*",
                Order = customAttribute.Order,
                Where = where,
                PageIndex = pageIndex,
                PageSize = pageSize,
                TotalRecords = total
            };
            pageCount = inputParams.CalcPageCount();
            total = inputParams.TotalRecords;
            return DbHelper.FillListPaged<T>("p_com_pagination_clownfish", inputParams, CommandKind.StoreProcedure);
        }

        public List<T> GetPageList_Fish(string where, string order, int pageIndex, int pageSize, out int pageCount, out int total)
        {
            pageCount = 0;
            total = 0;
            new PagingInfoExtension
            {
                Columns = "*",
                Order = order,
                Where = where,
                PageIndex = pageIndex,
                PageSize = pageSize,
                TotalRecords = total
            }.TableName = typeof(T).Name;
            PagingInfoExtension inputParams = new PagingInfoExtension
            {
                Columns = "*",
                Order = order,
                Where = where,
                PageIndex = pageIndex,
                PageSize = pageSize,
                TotalRecords = total
            };
            pageCount = inputParams.CalcPageCount();
            total = inputParams.TotalRecords;
            return DbHelper.FillListPaged<T>("p_com_pagination_clownfish", inputParams, CommandKind.StoreProcedure);
        }

        public List<T> GetPageList_Fish(string tableName, string columns, string where, string order, int pageIndex, int pageSize, out int pageCount, out int total)
        {
            pageCount = 0;
            total = 0;
            PagingInfoExtension inputParams = new PagingInfoExtension
            {
                TableName = tableName,
                Columns = columns,
                Order = order,
                Where = where,
                PageIndex = pageIndex,
                PageSize = pageSize,
                TotalRecords = total
            };
            pageCount = inputParams.CalcPageCount();
            total = inputParams.TotalRecords;
            return DbHelper.FillListPaged<T>("p_com_pagination_clownfish", inputParams, CommandKind.StoreProcedure);
        }

        public ParamInsert GetParamInsert(T data)
        {
            ParamInsert insert = ParamInsert.Instance().Insert(typeof(T).Name);
            TableAttribute customAttribute = AttributeHelper.GetCustomAttribute<TableAttribute>(typeof(T));
            foreach (PropertyInfo info in typeof(T).GetProperties())
            {
                bool flag = BaseService<T>.GetCommonFieldName().ContainsKey(info.Name);
                if (!(((info.Name == "tempid") || (info.Name == customAttribute.IdentityField)) | flag) && ((customAttribute.IgnoreInsertFieldsList == null) || !customAttribute.IgnoreInsertFieldsList.Contains(info.Name)))
                {
                    insert.Column(info.Name, data.GetValue(info.Name));
                }
            }
            return insert;
        }

        public ParamUpdate GetParamUpdate(T data)
        {
            ParamUpdate update = ParamUpdate.Instance().Update(typeof(T).Name);
            TableAttribute customAttribute = AttributeHelper.GetCustomAttribute<TableAttribute>(typeof(T));
            foreach (PropertyInfo info in typeof(T).GetProperties())
            {
                bool flag = BaseService<T>.GetCommonFieldName().ContainsKey(info.Name);
                if (!((((info.Name == "tempid") || (info.Name == customAttribute.PrimaryField)) || (info.Name == customAttribute.IdentityField)) | flag) && ((customAttribute.IgnoreUpdateFieldsList == null) || !customAttribute.IgnoreUpdateFieldsList.Contains(info.Name)))
                {
                    update.Column(info.Name, data.GetValue(info.Name));
                }
            }
            update.AndWhere(customAttribute.PrimaryField, data.GetValue(customAttribute.PrimaryField), null, new object[0]);
            return update;
        }

        public static void GetParaType(string fType, out OracleDbType otype)
        {
            if (fType != "INT")
            {
                if (fType == "DATE")
                {
                    otype = OracleDbType.Date;
                }
                else if (fType == "CHAR")
                {
                    otype = OracleDbType.Char;
                }
                else if (fType == "LONG")
                {
                    otype = OracleDbType.Double;
                }
                else if (fType == "NVARCHAR2")
                {
                    otype = OracleDbType.NVarchar2;
                }
                else if (fType == "VARCHAR2")
                {
                    otype = OracleDbType.NVarchar2;
                }
                else
                {
                    otype = OracleDbType.NVarchar2;
                }
            }
            else
            {
                otype = OracleDbType.Int32;
            }
        }

        public DataTable GetTabType(string tabnale)
        {
            return DbHelperOra.Query("select column_name,data_type,data_length from all_tab_columns where table_name='" + tabnale.ToUpper() + "'").Tables[0];
        }

        public int Insert(T data)
        {
            return this.Insert(this.GetParamInsert(data));
        }

        public int Insert(ParamInsert param)
        {
            int num = 0;
            this.db.UseTransaction(true);
            InsertEventArgs arg = new InsertEventArgs
            {
                db = this.db,
                data = param.GetData()
            };
            if (this.OnBeforeInsert(arg))
            {
                num = (AttributeHelper.GetCustomAttribute<TableAttribute>(typeof(T)).IdentityField != "") ? this.BuilderParse(param).ExecuteReturnLastId<int>(null) : this.BuilderParse(param).Execute();
                this.CommandResult.Set(true, APP.Msg_Insert_Success);
                InsertEventArgs args2 = new InsertEventArgs
                {
                    db = this.db,
                    data = param.GetData(),
                    executeValue = num
                };
                this.OnAfterInsert(args2);
                this.db.Commit();
            }
            return num;
        }

        public int Insert(List<T> dataList)
        {
            int num = 0;
            foreach (T local in dataList)
            {
                num = this.Insert(local);
                if (num < 1)
                {
                    return num;
                }
            }
            return num;
        }

        public static OracleParameter MakeInParam(string ParamName, OracleDbType DbType, int Size, object Value)
        {
            return BaseService<T>.MakeParam(ParamName, DbType, Size, System.Data.ParameterDirection.Input, Value);
        }

        public static OracleParameter MakeOutParam(string ParamName, OracleDbType DbType, int Size)
        {
            return BaseService<T>.MakeParam(ParamName, DbType, Size, System.Data.ParameterDirection.Output, null);
        }

        public static OracleParameter MakeParam(string ParamName, OracleDbType otype, int size, object Value)
        {
            return new OracleParameter(ParamName, Value)
            {
                OracleDbType = otype,
                Size = size
            };
        }

        public static OracleParameter MakeParam(string ParamName, OracleDbType DbType, int Size, System.Data.ParameterDirection Direction, object Value)
        {
            OracleParameter parameter;
            if (Size > 0)
            {
                parameter = new OracleParameter(ParamName, DbType, Size);
            }
            else
            {
                parameter = new OracleParameter(ParamName, DbType);
            }
            parameter.Direction = Direction;
            if ((Direction != System.Data.ParameterDirection.Output) || (Value != null))
            {
                parameter.Value = Value;
            }
            return parameter;
        }

        protected virtual void OnAfterDelete(DeleteEventArgs arg)
        {
        }

        protected virtual void OnAfterInsert(InsertEventArgs arg)
        {
        }

        protected virtual void OnAfterUpdate(UpdateEventArgs arg)
        {
        }

        protected virtual bool OnBeforeDelete(DeleteEventArgs arg)
        {
            return true;
        }

        protected virtual bool OnBeforeInsert(InsertEventArgs arg)
        {
            return true;
        }

        protected virtual bool OnBeforeUpdate(UpdateEventArgs arg)
        {
            return true;
        }

        public EWMS.Core.CommandResult PCmdResult(ParamSP param)
        {
            return this.BuilderParse(param).QuerySingle<EWMS.Core.CommandResult>((Action<EWMS.Core.CommandResult, FluentData.IDataReader>)null);
        }

        protected int QueryRowCount(ParamQuery param, dynamic rows)
        {
            if (rows != null && ((param == null) || (param.GetData().PageSize == 0)))
            {
                return rows.Count;
            }
            ParamQuery query = param.Paging(1, 0).OrderBy(string.Empty);
            string sqlForSelectBuilder = this.BuilderParse(query).Data.Command.Data.Context.Data.FluentDataProvider.GetSqlForSelectBuilder(this.BuilderParse(query).Data);
            return this.db.Sql("select count(*) from ( " + sqlForSelectBuilder + " ) tb_temp", new object[0]).QuerySingle<int>((Action<int, FluentData.IDataReader>)null);
        }

        public int SP(ParamSP param)
        {
            return this.BuilderParse(param).Execute();
        }

        public EWMS.Core.CommandResult SP_Fish(string spName, object inputParams)
        {
            return DbHelper.GetDataItem<EWMS.Core.CommandResult>(spName, inputParams, CommandKind.StoreProcedure);
        }

        public EWMS.Core.CommandResult SP_Fish(string spName, Dictionary<string, string> indict, Dictionary<string, string> dict)
        {
            OracleParameter[] parameters = new OracleParameter[indict.Count + dict.Count];
            DataTable tabType = this.GetTabType("BASE_PERSON");
            int size = 0;
            int index = 0;
            foreach (string str in indict.Keys)
            {
                DataView defaultView = tabType.DefaultView;
                defaultView.RowFilter = "column_name='" + str + "'";
                if (defaultView.ToTable().Rows.Count > 0)
                {
                    OracleDbType type;
                    BaseService<T>.GetoType(defaultView[0]["data_type"].ToString().ToUpper(), defaultView, out type, out size);
                    parameters[index] = BaseService<T>.MakeParam(str, type, size, indict[str]);
                }
                else
                {
                    OracleDbType entityDataType = BaseService<T>.GetEntityDataType(str);
                    parameters[index] = BaseService<T>.MakeOutParam(indict[str], entityDataType, size);
                }
                index++;
            }
            foreach (string local1 in dict.Keys)
            {
                parameters[index] = BaseService<T>.MakeOutParam(local1, BaseService<T>.GetEntityDataType(local1), size);
                index++;
            }
            DbHelperOra.RunProcedure(spName, parameters);
            new EWMS.Core.CommandResult();
            return BaseService<T>.GetEntityObject(parameters);
        }

        public OracleParameter[] SP_OtherFish(string spName, Dictionary<object, string> indict, Dictionary<object, string> dict)
        {
            OracleParameter[] parameters = new OracleParameter[indict.Count + dict.Count];
            int index = 0;
            foreach (dynamic obj2 in indict.Keys)
            {
                OracleDbType type;
                string str = obj2.Code;
                int num3 = int.Parse(obj2.Size.ToString());
                BaseService<T>.GetParaType(obj2.Type.ToString().ToUpper(), out type);
                parameters[index] = GetMakeParam(str, type, num3, indict[obj2]);
                index++;
            }
            foreach (dynamic obj3 in dict.Keys)
            {
                OracleDbType type2;
                string paramName = obj3.Code;
                int size = int.Parse(obj3.Size.ToString());
                BaseService<T>.GetParaType(obj3.Type.ToString().ToUpper(), out type2);
                parameters[index] = BaseService<T>.MakeOutParam(paramName, type2, size);
                index++;
            }
            OracleParameter[] parameterArray2 = new OracleParameter[dict.Count];
            DbHelperOra.RunProcedure(spName, parameters);
            int num2 = 0;
            for (int i = 0; i < parameters.Length; i++)
            {
                if (parameters[i].Direction == System.Data.ParameterDirection.Output)
                {
                    parameterArray2[num2] = parameters[i];
                    num2++;
                }
            }
            return parameterArray2;
        }

        public EWMS.Core.CommandResult SPCmdResult_Fish(string spName, object inputParams)
        {
            return DbHelper.GetDataItem<EWMS.Core.CommandResult>(spName, inputParams, CommandKind.StoreProcedure);
        }

        public int Update(T data)
        {
            return this.Update(this.GetParamUpdate(data));
        }

        public int Update(ParamUpdate param)
        {
            int num = 0;
            this.db.UseTransaction(true);
            UpdateEventArgs arg = new UpdateEventArgs
            {
                db = this.db,
                data = param.GetData()
            };
            if (this.OnBeforeUpdate(arg))
            {
                LogHelper.WriteDb("(ParamUpdate)执行更新记录sql语句：" + this.BuilderParse(param).Data.Command.Data.Context.Data.FluentDataProvider.GetSqlForUpdateBuilder(this.BuilderParse(param).Data));
                num = this.BuilderParse(param).Execute();
                this.CommandResult.Set(true, APP.Msg_Update_Success);
                UpdateEventArgs args2 = new UpdateEventArgs
                {
                    db = this.db,
                    data = param.GetData(),
                    executeValue = num
                };
                this.OnAfterUpdate(args2);
                this.db.Commit();
            }
            return num;
        }

        public EWMS.Core.CommandResult CommandResult { get; set; }

        public BaseLoginer CurrentBaseLoginer
        {
            get
            {
                return FormsAuth.GetBaseLoginerData();
            }
        }

        public TableAttribute CurrentTableAttribute
        {
            get
            {
                return AttributeHelper.GetCustomAttribute<TableAttribute>(typeof(T));
            }
        }

        protected IDbContext db
        {
            get
            {
                if (this._db == null)
                {
                    this._db = Db.Context(this.ModuleName);
                }
                return this._db;
            }
        }

        public string ModuleName { get; set; }

    }
}

