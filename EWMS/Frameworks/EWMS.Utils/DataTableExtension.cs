namespace EWMS.Utils
{
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Data;
    using System.Reflection;

    public static class DataTableExtension
    {
        public static ArrayList DataTableToArrayList(DataTable data)
        {
            ArrayList list = new ArrayList();
            for (int i = 0; i < data.Rows.Count; i++)
            {
                DataRow row = data.Rows[i];
                Hashtable hashtable = new Hashtable();
                for (int j = 0; j < data.Columns.Count; j++)
                {
                    object obj2 = row[j];
                    if (obj2.GetType() == typeof(DBNull))
                    {
                        obj2 = null;
                    }
                    hashtable[data.Columns[j].ColumnName.ToUpper()] = obj2;
                }
                list.Add(hashtable);
            }
            return list;
        }

        public static DataTable ToDataTable<T>(this IEnumerable<T> list)
        {
            List<PropertyInfo> pList = new List<PropertyInfo>();
            DataTable dt = new DataTable();
            Array.ForEach<PropertyInfo>(typeof(T).GetProperties(), delegate (PropertyInfo p)
            {
                if (((p != null) && (p.PropertyType.UnderlyingSystemType.ToString() != "System.Nullable`1[System.Int32]")) && (p.PropertyType.UnderlyingSystemType.ToString() != "System.Nullable`1[System.DateTime]"))
                {
                    pList.Add(p);
                    dt.Columns.Add(p.Name, p.PropertyType);
                }
            });
            using (IEnumerator<T> enumerator = list.GetEnumerator())
            {
                while (enumerator.MoveNext())
                {
                    T item = enumerator.Current;
                    DataRow row = dt.NewRow();
                    pList.ForEach(delegate (PropertyInfo p)
                    {
                        row[p.Name] = p.GetValue(item, null);
                    });
                    dt.Rows.Add(row);
                }
            }
            return dt;
        }

        public static List<T> ToList<T>(this DataTable dt) where T : class, new()
        {
            List<PropertyInfo> prlist = new List<PropertyInfo>();
            Array.ForEach<PropertyInfo>(typeof(T).GetProperties(), delegate (PropertyInfo p)
            {
                if (dt.Columns.IndexOf(p.Name) != -1)
                {
                    prlist.Add(p);
                }
            });
            List<T> list = new List<T>();
            IEnumerator enumerator = dt.Rows.GetEnumerator();
            while (enumerator.MoveNext())
            {
                DataRow row = (DataRow)enumerator.Current;
                T ob = Activator.CreateInstance<T>();
                prlist.ForEach(delegate (PropertyInfo p)
                {
                    if (row[p.Name] != DBNull.Value)
                    {
                        p.SetValue(ob, row[p.Name], null);
                    }
                });
                list.Add(ob);
            }
            return list;
        }
    }
}

