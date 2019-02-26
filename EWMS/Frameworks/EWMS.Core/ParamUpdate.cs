namespace EWMS.Core
{
    using Newtonsoft.Json.Linq;
    using System;
    using System.Collections.Generic;
    using System.Dynamic;
    using System.Reflection;
    using System.Runtime.CompilerServices;
    using System.Runtime.InteropServices;

    public class ParamUpdate
    {
        protected ParamUpdateData data = new ParamUpdateData();

        public ParamUpdate AndWhere(string column, object value, Func<WhereData, string> cp = null, params object[] extend)
        {
            ParamWhere item = new ParamWhere();
            WhereData data1 = new WhereData {
                AndOr = "and",
                Column = column,
                Value = value,
                Extend = extend
            };
            item.Data = data1;
            item.Compare = cp ?? new Func<WhereData, string>(Cp.Equal);
            this.data.Where.Add(item);
            return this;
        }

        public ParamUpdate Column(string columnName, object value)
        {
            if ((value != null) && (value.GetType() == typeof(JValue)))
            {
                value = ((JValue) value).ToString();
            }
            this.data.Columns[columnName] = value;
            return this;
        }

        public ParamUpdateData GetData()
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

        public static ParamUpdate Instance()
        {
            return new ParamUpdate();
        }

        public ParamUpdate OrWhere(string column, object value, Func<WhereData, string> cp = null, params object[] extend)
        {
            ParamWhere item = new ParamWhere();
            WhereData data1 = new WhereData {
                AndOr = "or",
                Column = column,
                Value = value,
                Extend = extend
            };
            item.Data = data1;
            item.Compare = cp ?? new Func<WhereData, string>(Cp.Equal);
            this.data.Where.Add(item);
            return this;
        }

        public ParamUpdate Update(string tableName)
        {
            this.data.Update = tableName;
            return this;
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

