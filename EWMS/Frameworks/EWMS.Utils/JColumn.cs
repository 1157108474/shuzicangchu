namespace EWMS.Utils
{
    using System;

    using System.Text;

    public class JColumn
    {
        public string ConvertToJson()
        {
            StringBuilder builder1 = new StringBuilder();
            builder1.Append("{");
            builder1.AppendFormat("\"field\":\"{0}\",", this.field);
            builder1.AppendFormat("\"width\":{0},", this.width);
            builder1.AppendFormat("\"align\":\"{0}\",", this.align);
            builder1.AppendFormat("\"title\":\"{0}\",", this.title);
            builder1.Remove(builder1.Length - 1, 1);
            builder1.Append("}");
            return builder1.ToString().ToLower();
        }

        public string align { get; set; }

        public string field { get; set; }

        public string title { get; set; }

        public int width { get; set; }
    }
}

