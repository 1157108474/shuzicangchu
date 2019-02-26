namespace EWMS.Core
{
    using System;
    using System.Collections.Generic;
    using System.Runtime.CompilerServices;

    public class ParamDeleteData
    {
        public ParamDeleteData()
        {
            this.From = "";
            this.Where = new List<ParamWhere>();
        }

        public object GetValue(string column)
        {
            ParamWhere where = this.Where.Find(x => x.Data.Column == column);
            if (where != null)
            {
                return where.Data.Value;
            }
            return null;
        }

        public string From { get; set; }

        public List<ParamWhere> Where { get; set; }

        public string WhereSql
        {
            get
            {
                return ParamUtils.GetWhereSql(this.Where);
            }
        }
    }
}

