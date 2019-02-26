namespace EWMS.Service
{
    using FluentData;
    using EWMS.Core;
    using System;
    using System.Collections.Generic;
    using EWMS.Entity;

    public class Base_DataBaseService : BaseService<Base_DataBase>
    {
        private static readonly Base_DataBaseService _Instance = new Base_DataBaseService();

        public List<sys_column> GetTableColumnList(string tableName)
        {
            return base.db.Sql("select * from sys_column where TableName='" + tableName + "'  order by ColumnOrder asc", new object[0]).QueryMany<sys_column>((Action<sys_column, IDataReader>) null);
        }

        public List<sys_table> GetTableList()
        {
            return base.db.Sql("select * from sys_table order by TableName asc", new object[0]).QueryMany<sys_table>((Action<sys_table, IDataReader>) null);
        }

        public CommandResult SaveTableColumnCaption(string tableName, string columnName, string columnCaption)
        {
            return base.db.StoredProcedure("sys_addtableculumncaption").Parameter("TableName", tableName, DataTypes.Object, 0).Parameter("ColumnName", columnName, DataTypes.Object, 0).Parameter("ColumnCaption", columnCaption, DataTypes.Object, 0).QuerySingle<CommandResult>((Action<CommandResult, IDataReader>) null);
        }

        public CommandResult SaveTableColumnDescription(string tableName, string columnName, string columnDescription)
        {
            return base.db.StoredProcedure("sys_addtableculumndescription").Parameter("TableName", tableName, DataTypes.Object, 0).Parameter("ColumnName", columnName, DataTypes.Object, 0).Parameter("ColumnDescription", columnDescription, DataTypes.Object, 0).QuerySingle<CommandResult>((Action<CommandResult, IDataReader>) null);
        }

        public CommandResult SaveTableDescription(string tableName, string tableDescription)
        {
            return base.db.StoredProcedure("sys_addtabledescription").Parameter("TableName", tableName, DataTypes.Object, 0).Parameter("TableDescription", tableDescription, DataTypes.Object, 0).QuerySingle<CommandResult>((Action<CommandResult, IDataReader>) null);
        }

        public static Base_DataBaseService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

