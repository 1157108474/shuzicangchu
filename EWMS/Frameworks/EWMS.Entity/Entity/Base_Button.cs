namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Button", PrimaryField="ButtonCode", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Button : BaseEntity
    {
        public string ButtonCode { get; set; }

        public string ButtonName { get; set; }

        public int ButtonType { get; set; }

        public DateTime? CreateDate { get; set; }

        public int Creator { get; set; }

        public int Enabled { get; set; }

        public string IconClass { get; set; }

        public string IconUrl { get; set; }

        public string JsEvent { get; set; }

        public string Remark { get; set; }

        public int Sort { get; set; }

        public int Split { get; set; }

        public DateTime? UpdateDate { get; set; }

        public int Updater { get; set; }
    }
}

