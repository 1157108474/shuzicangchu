namespace EWMS.Core
{
    using System;
    using System.Collections.Generic;
    using System.Runtime.CompilerServices;

    public class ParamQueryData
    {
        public ParamQueryData()
        {
            this.Having = "";
            this.GroupBy = "";
            this.OrderBy = "";
            this.From = "";
            this.Select = "";
            this.Where = new List<ParamWhere>();
            this.PageIndex = 1;
            this.PageSize = 0;
        }

        public string From { get; set; }

        public string GroupBy { get; set; }

        public string Having { get; set; }

        public string OrderBy { get; set; }

        public int PageIndex { get; set; }

        public int PageSize { get; set; }

        public string Select { get; set; }

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

