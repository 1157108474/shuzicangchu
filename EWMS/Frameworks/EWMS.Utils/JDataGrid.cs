namespace EWMS.Utils
{
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Data;
    using System.Reflection;

    using System.Text;

    public class JDataGrid
    {
        public static List<Dictionary<string, object>> ConvertRows(IList list)
        {
            List<Dictionary<string, object>> list2 = new List<Dictionary<string, object>>();
            if (list != null)
            {
                foreach (object obj2 in list)
                {
                    Dictionary<string, object> item = new Dictionary<string, object>();
                    foreach (PropertyInfo info1 in obj2.GetType().GetProperties())
                    {
                        string name = info1.Name;
                        object obj3 = info1.GetValue(obj2, null);
                        item.Add(name, obj3);
                    }
                    list2.Add(item);
                }
            }
            return list2;
        }

        public static List<Dictionary<string, object>> ConvertRows(DataTable dt)
        {
            List<Dictionary<string, object>> list = new List<Dictionary<string, object>>();
            if (dt.Rows.Count > 0)
            {
                for (int i = 0; i < dt.Rows.Count; i++)
                {
                    Dictionary<string, object> item = new Dictionary<string, object>();
                    for (int j = 0; j < dt.Columns.Count; j++)
                    {
                        string columnName = dt.Columns[j].ColumnName;
                        object obj2 = dt.Rows[i][j];
                        if ((obj2 != DBNull.Value) && (obj2.ToString() == "0"))
                        {
                            obj2 = DBNull.Value;
                        }
                        item.Add(columnName, obj2);
                    }
                    list.Add(item);
                }
            }
            return list;
        }

        public string ConvertToJson()
        {
            StringBuilder builder = new StringBuilder();
            builder.AppendFormat("{{\"total\":{0},\"rows\":[", this.total);
            if ((this.rows != null) && (this.rows.Count > 0))
            {
                for (int i = 0; i < this.rows.Count; i++)
                {
                    builder.Append("{");
                    foreach (string str in this.rows[i].Keys)
                    {
                        if (this.rows[i][str] is System.ValueType)
                        {
                            builder.AppendFormat("\"{0}\":{1},", str, this.rows[i][str]);
                        }
                        else
                        {
                            builder.AppendFormat("\"{0}\":\"{1}\",", str, this.rows[i][str]);
                        }
                    }
                    builder.Remove(builder.Length - 1, 1);
                    builder.Append("}");
                    if (i != (this.rows.Count - 1))
                    {
                        builder.Append(",");
                    }
                }
            }
            builder.Append("],");
            builder.Append("\"columns\":[[");
            if ((this.columns != null) && (this.columns.Count > 0))
            {
                for (int j = 0; j < this.columns.Count; j++)
                {
                    builder.Append(this.columns[j].ConvertToJson());
                    if (j != (this.columns.Count - 1))
                    {
                        builder.Append(",");
                    }
                }
            }
            builder.Append("]]}");
            return builder.ToString();
        }

        public List<JColumn> columns { get; set; }

        public List<Dictionary<string, object>> rows { get; set; }

        public int total { get; set; }
    }
}

