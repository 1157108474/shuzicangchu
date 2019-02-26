namespace EWMS.Utils
{
    using System;
    using System.Collections;
    using System.Text;
    using System.Web;

    public class ImportExcel
    {
        public static void ExportExcel(ArrayList columns, ArrayList data, string FileName)
        {
            HttpContext.Current.Response.Clear();
            HttpContext.Current.Response.Buffer = true;
            HttpContext.Current.Response.Charset = "GB2312";
            HttpContext.Current.Response.AppendHeader("Content-Disposition", "attachment;filename=" + HttpUtility.UrlEncode(FileName, Encoding.UTF8) + ".xls");
            HttpContext.Current.Response.ContentEncoding = Encoding.Default;
            HttpContext.Current.Response.ContentType = "application/ms-excel";
            HttpContext.Current.Response.Write(ExportTable(data, columns));
            HttpContext.Current.Response.End();
        }

        public static string ExportTable(ArrayList data, ArrayList columns)
        {
            StringBuilder builder = new StringBuilder();
            int num = 0;
            builder.AppendLine("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">");
            builder.AppendLine("<table cellspacing=\"0\" cellpadding=\"5\" rules=\"all\" border=\"1\">");
            builder.AppendLine("<tr style=\"font-weight: bold; white-space: nowrap;\">");
            foreach (Hashtable hashtable in columns)
            {
                builder.AppendLine("<td>" + hashtable["header"] + "</td>");
            }
            builder.AppendLine("</tr>");
            foreach (Hashtable hashtable2 in data)
            {
                builder.Append("<tr>");
                foreach (Hashtable hashtable3 in columns)
                {
                    if (hashtable3["field"] != null)
                    {
                        builder.AppendLine("<td>" + hashtable2[hashtable3["field"]] + "</td>");
                    }
                }
                builder.AppendLine("</tr>");
                num++;
            }
            builder.AppendLine("</table>");
            return builder.ToString();
        }
    }
}

