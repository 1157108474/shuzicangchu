namespace EWMS.Core
{
    using System;
    using System.Linq;
    using System.Runtime.InteropServices;

    public class ParamQuery
    {
        protected ParamQueryData data = new ParamQueryData();

        public ParamQuery AndWhere(string column, object value, Func<WhereData, string> cp = null, params object[] extend)
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

        public ParamQuery ClearWhere()
        {
            this.data.Where.Clear();
            return this;
        }

        public ParamQuery From(string sql)
        {
            this.data.From = sql;
            return this;
        }

        public ParamQueryData GetData()
        {
            return this.data;
        }

        public ParamQuery GroupBy(string sql)
        {
            this.data.GroupBy = sql;
            return this;
        }

        public ParamQuery Having(string sql)
        {
            this.data.Having = sql;
            return this;
        }

        public static ParamQuery Instance()
        {
            return new ParamQuery();
        }

        public ParamQuery OrderBy(string sql)
        {
            char[] chArray1 = new char[] { ' ' };
            string[] sortOrder = sql.Trim().Split(chArray1);
            if (!string.IsNullOrWhiteSpace(sql))
            {
                string mainTable = null;
                char[] chArray2 = new char[] { ',' };
                this.data.Select.Trim().Split(chArray2).ToList<string>().ForEach(delegate (string x) {
                    if (x.Trim().EndsWith("." + sortOrder[0]))
                    {
                        sortOrder[0] = x;
                    }
                    if (x.Trim().EndsWith(".*"))
                    {
                        char[] separator = new char[] { '.' };
                        mainTable = x.Split(separator)[0];
                    }
                });
                if ((mainTable != null) && mainTable.ToLower().StartsWith("distinct"))
                {
                    mainTable = mainTable.Substring(8);
                }
                if ((sortOrder[0].IndexOf(".") == -1) && !string.IsNullOrEmpty(mainTable))
                {
                    sortOrder[0] = mainTable + "." + sortOrder[0];
                }
            }
            this.data.OrderBy = string.Join(" ", sortOrder);
            return this;
        }

        public ParamQuery OrWhere(string column, object value, Func<WhereData, string> cp = null, params object[] extend)
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

        public ParamQuery Paging(int pageIndex, int pageSize)
        {
            this.data.PageIndex = pageIndex;
            this.data.PageSize = pageSize;
            return this;
        }

        public ParamQuery Select(string sql)
        {
            this.data.Select = sql;
            return this;
        }
    }
}

