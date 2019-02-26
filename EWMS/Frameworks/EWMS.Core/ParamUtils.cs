namespace EWMS.Core
{
    using System;
    using System.Collections.Concurrent;
    using System.Collections.Generic;
    using System.ComponentModel;

    public class ParamUtils
    {
        private static readonly ConcurrentDictionary<Enum, string> _cachedCpEnum = new ConcurrentDictionary<Enum, string>();

        private static string _GetEnumDesc(Enum enumSubitem)
        {
            object[] customAttributes = enumSubitem.GetType().GetField(enumSubitem.ToString()).GetCustomAttributes(typeof(DescriptionAttribute), false);
            if (customAttributes.Length == 0)
            {
                return enumSubitem.ToString();
            }
            return ((DescriptionAttribute) customAttributes[0]).Description;
        }

        public static string GetEnumDescription(Enum enumSubitem)
        {
            return _cachedCpEnum.GetOrAdd(enumSubitem, new Func<Enum, string>(ParamUtils._GetEnumDesc));
        }

        public static string GetWhereSql(List<ParamWhere> Where)
        {
            string sql = string.Empty;
            Where.ForEach(delegate (ParamWhere x) {
                sql = sql + (string.IsNullOrEmpty(sql) ? string.Empty : x.Data.AndOr) + " " + x.Compare(x.Data);
            });
            return sql;
        }
    }
}

