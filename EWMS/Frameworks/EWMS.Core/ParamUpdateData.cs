namespace EWMS.Core
{
    using System;
    using System.Collections.Generic;
    using System.Runtime.CompilerServices;

    public class ParamUpdateData
    {
        public ParamUpdateData()
        {
            this.Update = "";
            this.Columns = new Dictionary<string, object>();
            this.Where = new List<ParamWhere>();
        }

        public Dictionary<string, object> Columns { get; set; }

        public string Update { get; set; }

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

