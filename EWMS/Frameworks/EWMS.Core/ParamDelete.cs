namespace EWMS.Core
{
    using System;
    using System.Runtime.InteropServices;

    public class ParamDelete
    {
        protected ParamDeleteData data = new ParamDeleteData();

        public ParamDelete AndWhere(string column, object value, Func<WhereData, string> cp = null, params object[] extend)
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

        public ParamDelete From(string sql)
        {
            this.data.From = sql;
            return this;
        }

        public ParamDeleteData GetData()
        {
            return this.data;
        }

        public static ParamDelete Instance()
        {
            return new ParamDelete();
        }

        public ParamDelete OrWhere(string column, object value, Func<WhereData, string> cp = null, params object[] extend)
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
    }
}

