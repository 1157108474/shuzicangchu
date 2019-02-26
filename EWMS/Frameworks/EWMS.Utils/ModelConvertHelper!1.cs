namespace EWMS.Utils
{
    using System;
    using System.Collections.Generic;
    using System.Data;
    using System.Reflection;

    public class ModelConvertHelper<T> where T: new()
    {
        public static IList<T> ConvertToModel(DataTable dt)
        {
            IList<T> list = new List<T>();
            string name = "";
            foreach (DataRow row in dt.Rows)
            {
                T local = Activator.CreateInstance<T>();
                foreach (PropertyInfo info in local.GetType().GetProperties())
                {
                    name = info.Name;
                    if (dt.Columns.Contains(name) && info.CanWrite)
                    {
                        object obj2 = row[name];
                        if (obj2 != DBNull.Value)
                        {
                            info.SetValue(local, obj2, null);
                        }
                    }
                }
                list.Add(local);
            }
            return list;
        }
    }
}

