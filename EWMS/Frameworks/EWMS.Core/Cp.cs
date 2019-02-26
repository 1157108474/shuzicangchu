namespace EWMS.Core
{
    using EWMS.Utils;
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Runtime.InteropServices;

    public class Cp
    {
        public static string Between(WhereData data)
        {
            return SQL(data, "{0} between '{1}'  '{0}'");
        }

        public static string Child(WhereData data)
        {
            return SQL(data, "{0} in (select ID from [dbo].[GetChild]('{0}','{1}'))");
        }

        public static string DateRange(WhereData data)
        {
            return GetDateRangeSql(data, '到', true);
        }

        public static string DtGreaterEqual(WhereData data)
        {
            return SQL(data, "datediff(day,'{1}',{0}) > =0");
        }

        public static string DtLessEqual(WhereData data)
        {
            return SQL(data, "datediff(day,'{1}',{0}) < =0");
        }

        public static string EndWith(WhereData data)
        {
            return SQL(data, "{0} like '%{1}'");
        }

        public static string Equal(WhereData data)
        {
            return SQL(data, "{0} =  '{1}'");
        }

        private static string GetDateRangeSql(WhereData cp, char _separator, bool _ignoreEmpty = true)
        {
            string str = string.Empty;
            string format = "datediff(day,'{1}',{0}) >=0";
            string str3 = "datediff(day,'{1}',{0})<=0";
            char[] separator = new char[] { _separator };
            string[] strArray = ConvertUtil.ToString(cp.Value).Split(separator);
            if (strArray.Length == 1)
            {
                strArray = new string[] { strArray[0], strArray[0] };
            }
            if (!string.IsNullOrWhiteSpace(strArray[0]) || !_ignoreEmpty)
            {
                str = string.Format(format, cp.Column, strArray[0]);
            }
            if (!string.IsNullOrWhiteSpace(strArray[1]) || !_ignoreEmpty)
            {
                str = str + ((str.Length > 0) ? " and " : string.Empty) + string.Format(str3, cp.Column, strArray[1]);
            }
            return str;
        }

        public static string Greater(WhereData data)
        {
            return SQL(data, "{0} >  '{1}'");
        }

        public static string GreaterEqual(WhereData data)
        {
            return SQL(data, "{0} >= '{1}'");
        }

        public static string In(WhereData data)
        {
            return SQL(data, "{0} in ({1})");
        }

        public static string Less(WhereData data)
        {
            return SQL(data, "{0} < '{1}'");
        }

        public static string LessEqual(WhereData data)
        {
            return SQL(data, "{0} <=  '{1}'");
        }

        public static string Like(WhereData data)
        {
            return SQL(data, "{0} like '%{1}%'");
        }

        public static string Map(WhereData data)
        {
            return SQL(data, "{0} in (select {0} from {0} where {0}='{1}')");
        }

        public static string MapChild(WhereData data)
        {
            return SQL(data, "{0} in (select {0} from {2} where {3} in (select ID from [dbo].[GetChild]('{4}','{1}')))");
        }

        public static string NotEqual(WhereData data)
        {
            return SQL(data, "{0} <> '{1}'");
        }

        private static string SQL(WhereData cp, string stringFormat)
        {
            List<object> list = cp.Extend.ToList<object>();
            list.Insert(0, cp.Value);
            list.Insert(0, cp.Column);
            return string.Format(stringFormat, list.ToArray());
        }

        public static string StartWith(WhereData data)
        {
            return SQL(data, "{0} like '{1}%'");
        }

        public static string StartWithPY(WhereData data)
        {
            return SQL(data, "{0} like '{1}%' or [dbo].[fun_getPY]({0}) like N'{1}%'");
        }
    }
}

