namespace EWMS.Core
{
    using System;
    using System.Collections.Generic;
    using System.Dynamic;
    using System.Reflection;
    using System.Runtime.CompilerServices;

    public class ParamInsert
    {
        protected ParamInsertData data = new ParamInsertData();

        public ParamInsert Column(string columnName, object value)
        {
            this.data.Columns[columnName] = value;
            return this;
        }

        public ParamInsertData GetData()
        {
            return this.data;
        }

        public dynamic GetDynamicValue()
        {
            IDictionary<string, object> dictionary = new ExpandoObject();
            foreach (KeyValuePair<string, object> pair in this.data.Columns)
            {
                dictionary.Add(pair.Key, pair.Value);
            }
            return (ExpandoObject) dictionary;
        }

        public ParamInsert Insert(string tableName)
        {
            this.data.TableName = tableName;
            return this;
        }

        public static ParamInsert Instance()
        {
            return new ParamInsert();
        }

        public dynamic this[string index]
        {
            
            get
            {
                return this.data.Columns[index];
            }
            set
            {
                this.data.Columns[index] = value;
            }
        }
    }
}

