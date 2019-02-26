namespace EWMS.Core
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Runtime.CompilerServices;

    [AttributeUsage(AttributeTargets.Class, Inherited=false)]
    public class TableAttribute : Attribute
    {
        public TableAttribute()
        {
            this.TableName = "";
            this.PrimaryField = "";
            this.IdentityField = "";
            this.Order = this.PrimaryField;
            this.IgnoreInsertFields = "";
            this.IgnoreUpdateFields = "";
        }

        public string IdentityField { get; set; }

        public string IgnoreInsertFields { get; set; }

        public List<string> IgnoreInsertFieldsList
        {
            get
            {
                List<string> list = null;
                if (this.IgnoreInsertFields != "")
                {
                    string[] separator = new string[] { "," };
                    list = this.IgnoreInsertFields.Split(separator, StringSplitOptions.RemoveEmptyEntries).ToList<string>();
                }
                return list;
            }
        }

        public string IgnoreUpdateFields { get; set; }

        public List<string> IgnoreUpdateFieldsList
        {
            get
            {
                List<string> list = null;
                if (this.IgnoreUpdateFields != "")
                {
                    string[] separator = new string[] { "," };
                    list = this.IgnoreUpdateFields.Split(separator, StringSplitOptions.RemoveEmptyEntries).ToList<string>();
                }
                return list;
            }
        }

        public string Order { get; set; }

        public string PrimaryField { get; set; }

        public string TableName { get; set; }
    }
}

