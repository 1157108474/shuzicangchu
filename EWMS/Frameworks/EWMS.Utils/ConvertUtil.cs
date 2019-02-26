namespace EWMS.Utils
{
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Collections.Specialized;
    using System.Data;
    using System.Reflection;

    public class ConvertUtil
    {
        private static T DefaultValue<T>()
        {
            return default(T);
        }

        private static object GetDefaultValue(Type type)
        {
            if (type.IsValueType)
            {
                return Activator.CreateInstance(type);
            }
            return null;
        }

        private static object GetNullInternal(Type type)
        {
            if (type.IsValueType)
            {
                if (type.IsEnum)
                {
                    return GetNullInternal(Enum.GetUnderlyingType(type));
                }
                if (type.IsPrimitive)
                {
                    if (type == typeof(int))
                    {
                        return 0;
                    }
                    if (type == typeof(double))
                    {
                        return 0.0;
                    }
                    if (type == typeof(short))
                    {
                        return (short) 0;
                    }
                    if (type == typeof(sbyte))
                    {
                        return (sbyte) 0;
                    }
                    if (type == typeof(long))
                    {
                        return 0L;
                    }
                    if (type == typeof(byte))
                    {
                        return (byte) 0;
                    }
                    if (type == typeof(ushort))
                    {
                        return (ushort) 0;
                    }
                    if (type == typeof(uint))
                    {
                        return 0;
                    }
                    if (type == typeof(ulong))
                    {
                        return (ulong) 0L;
                    }
                    if (type == typeof(ulong))
                    {
                        return (ulong) 0L;
                    }
                    if (type == typeof(float))
                    {
                        return 0f;
                    }
                    if (type == typeof(bool))
                    {
                        return false;
                    }
                    if (type == typeof(char))
                    {
                        return '\0';
                    }
                }
                else
                {
                    if (type == typeof(DateTime))
                    {
                        return DateTime.MinValue;
                    }
                    if (type == typeof(decimal))
                    {
                        return decimal.Zero;
                    }
                    if (type == typeof(Guid))
                    {
                        return Guid.Empty;
                    }
                    if (type == typeof(TimeSpan))
                    {
                        return new TimeSpan(0, 0, 0);
                    }
                }
            }
            return null;
        }

        public static DataTable ListToDataTable(object datas, string tableName)
        {
            Type genericType = GenericUtil.GetGenericType(datas);
            if (string.IsNullOrEmpty(tableName))
            {
                tableName = genericType.Name;
            }
            DataTable table = new DataTable(tableName);
            table.BeginLoadData();
            Dictionary<string, PropertyInfo> properties = ReflectionUtil.GetProperties(genericType);
            foreach (KeyValuePair<string, PropertyInfo> pair in properties)
            {
                Type propertyType = pair.Value.PropertyType;
                string typeName = propertyType.ToString();
                if (propertyType.IsGenericType)
                {
                    typeName = propertyType.GetGenericArguments()[0].ToString();
                }
                Type type = Type.GetType(typeName, false);
                if (type != null)
                {
                    table.Columns.Add(pair.Value.Name, type);
                }
            }
            IEnumerator enumerator = datas as IEnumerator;
            while (enumerator.MoveNext())
            {
                DataRow row = table.NewRow();
                foreach (KeyValuePair<string, PropertyInfo> pair2 in properties)
                {
                    object obj2 = GenericUtil.GetValue(enumerator.Current, pair2.Value.Name);
                    if ((Type.GetType(pair2.Value.PropertyType.ToString(), false) != null) && (obj2 != null))
                    {
                        row[pair2.Value.Name] = obj2;
                    }
                }
                table.Rows.Add(row);
            }
            table.EndLoadData();
            table.AcceptChanges();
            return table;
        }

        //[return: Dynamic(new bool[] { false, true })]
        public static List<object> ListToTreeData<T>(List<T> source, string ID, string PID) where T: class, new()
        {
            Action<List<object>, T, object> AddItem =  (List<object> parent, T item,  dynamic Recursive)=> {
                List<object> childrens = new List<object>();
                IDictionary<string, object> dictionaryValues = GenericUtil.GetDictionaryValues(item);
                source.FindAll(o => GenericUtil.GetValue(item, ID).Equals(GenericUtil.GetValue(o, PID))).ForEach(delegate (T subitem) {
                    Recursive(childrens, subitem, Recursive);
                });
                dictionaryValues.Add("children", childrens);
                parent.Add(dictionaryValues);
            };
            List<object> target = new List<object>();
            source.FindAll(m => !source.Exists(n => GenericUtil.GetValue(n, ID).Equals(GenericUtil.GetValue(m, PID)))).ForEach(delegate (T item) {
                AddItem(target, item, AddItem);
            });
            return target;
        }

        public static T To<T>(NameValueCollection datas) where T: class, new()
        {
            Type type = typeof(T);
            char[] separator = new char[] { '.' };
            string[] textArray1 = type.FullName.Split(separator);
            string str = textArray1[textArray1.Length - 1];
            PropertyInfo[] properties = type.GetProperties(BindingFlags.Public | BindingFlags.Instance);
            T local = Activator.CreateInstance<T>();
            foreach (string str2 in datas.AllKeys)
            {
                string str3 = datas[str2];
                if (!string.IsNullOrEmpty(str3))
                {
                    str3 = str3.TrimEnd(new char[0]);
                }
                foreach (PropertyInfo info in properties)
                {
                    string str4 = string.Format("{0}.{1}", str, info.Name);
                    if (str2.Equals(info.Name, StringComparison.CurrentCultureIgnoreCase) || str2.Equals(str4, StringComparison.CurrentCultureIgnoreCase))
                    {
                        string typeName = info.PropertyType.ToString();
                        if (info.PropertyType.IsGenericType)
                        {
                            typeName = info.PropertyType.GetGenericArguments()[0].ToString();
                        }
                        object nullInternal = GetNullInternal(info.PropertyType);
                        Type conversionType = Type.GetType(typeName, false);
                        if (!string.IsNullOrEmpty(str3))
                        {
                            nullInternal = Convert.ChangeType(str3, conversionType);
                        }
                        info.SetValue(local, nullInternal, null);
                    }
                }
            }
            return local;
        }

        public static T To<T>(object obj)
        {
            return To<T>(obj, default(T));
        }

        public static T To<T>(object obj, T defaultValue)
        {
            if (obj == null)
            {
                return defaultValue;
            }
            if (obj is T)
            {
                return (T) obj;
            }
            try
            {
                Type conversionType = typeof(T);
                object obj2 = null;
                if (conversionType.Equals(typeof(Guid)))
                {
                    obj2 = new Guid(Convert.ToString(obj));
                }
                else
                {
                    obj2 = Convert.ChangeType(obj, conversionType);
                }
                return (T) obj2;
            }
            catch (Exception)
            {
                return defaultValue;
            }
        }

        public static T[] ToArray<T>(DataTable datas) where T: class, new()
        {
            return (ToList<T>(datas) as List<T>).ToArray();
        }

        public static DataTable ToDataTable<T>(IList<T> datas)
        {
            return ToDataTable<T>(datas, null);
        }

        public static DataTable ToDataTable<T>(T[] datas)
        {
            return ToDataTable<T>(datas, null);
        }

        public static DataTable ToDataTable<T>(IList<T> datas, string tableName)
        {
            Type type = typeof(T);
            if (string.IsNullOrEmpty(tableName))
            {
                tableName = type.Name;
            }
            DataTable table = new DataTable(tableName);
            table.BeginLoadData();
            PropertyInfo[] properties = type.GetProperties(BindingFlags.Public | BindingFlags.Instance);
            foreach (PropertyInfo info in properties)
            {
                string typeName = info.PropertyType.ToString();
                if (info.PropertyType.IsGenericType)
                {
                    typeName = info.PropertyType.GetGenericArguments()[0].ToString();
                }
                Type type2 = Type.GetType(typeName, false);
                if (type2 != null)
                {
                    table.Columns.Add(info.Name, type2);
                }
            }
            if ((datas != null) && (datas.Count > 0))
            {
                foreach (object obj2 in datas)
                {
                    DataRow row = table.NewRow();
                    foreach (PropertyInfo info2 in properties)
                    {
                        if ((Type.GetType(info2.PropertyType.ToString(), false) != null) && (info2.GetValue(obj2, null) != null))
                        {
                            row[info2.Name] = info2.GetValue(obj2, null);
                        }
                    }
                    table.Rows.Add(row);
                }
            }
            table.EndLoadData();
            table.AcceptChanges();
            return table;
        }

        public static DataTable ToDataTable<T>(T[] datas, string tableName)
        {
            IList<T> list;
            if ((datas == null) || (datas.Length == 0))
            {
                list = new List<T>();
            }
            else
            {
                list = new List<T>(datas);
            }
            return ToDataTable<T>(list, tableName);
        }

        public static T ToEnum<T>(object obj, T defaultEnum)
        {
            int num;
            string str = To<string>(obj);
            if (Enum.IsDefined(typeof(T), str))
            {
                return (T) Enum.Parse(typeof(T), str);
            }
            if (int.TryParse(str, out num) && Enum.IsDefined(typeof(T), num))
            {
                return (T) Enum.ToObject(typeof(T), num);
            }
            return defaultEnum;
        }

        public static IList<T> ToList<T>(DataTable datas) where T: class, new()
        {
            IList<T> list = new List<T>();
            if ((datas != null) && (datas.Rows.Count != 0))
            {
                PropertyInfo[] properties = typeof(T).GetProperties(BindingFlags.Public | BindingFlags.Instance);
                foreach (DataRow row in datas.Rows)
                {
                    T local = Activator.CreateInstance<T>();
                    foreach (DataColumn column in datas.Columns)
                    {
                        object obj2 = null;
                        if (row.RowState == DataRowState.Deleted)
                        {
                            obj2 = row[column, DataRowVersion.Original];
                        }
                        else
                        {
                            obj2 = row[column];
                        }
                        if (obj2 != DBNull.Value)
                        {
                            foreach (PropertyInfo info in properties)
                            {
                                if (column.ColumnName.Equals(info.Name, StringComparison.CurrentCultureIgnoreCase))
                                {
                                    info.SetValue(local, obj2, null);
                                }
                            }
                        }
                    }
                    list.Add(local);
                }
            }
            return list;
        }

        public static string ToString(object obj)
        {
            return To<string>(obj, string.Empty);
        }

        public static List<T> TreeDataToList<T>( List<object> source)
        {
            Action<List<object>, List<T>, object> action = (List<object> mysource, List<T> mytarget,dynamic Recursive)=> {
                foreach (object obj2 in mysource)
                {
                    T item = GenericUtil.CreateNew<T>();
                    IDictionary<string, object> dictionary = GenericUtil.GetDictionaryValues(obj2);
                    if ((dictionary["childern"] as List<object>).Count > 0)
                    {
                      Recursive( mysource, mytarget, Recursive);
                    }
                    foreach (KeyValuePair<string, object> pair in dictionary)
                    {
                        if (pair.Key != "children")
                        {
                            GenericUtil.SetValue(item, pair.Key, pair.Value);
                        }
                    }
                    mytarget.Add(item);
                }
            };
            List<T> list = new List<T>();
            action(source, list, action);
            return list;
        }

    }
}

