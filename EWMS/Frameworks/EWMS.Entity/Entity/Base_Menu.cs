namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Menu", PrimaryField="MenuCode", IdentityField="", Order="Sort", IgnoreInsertFields="icon,name", IgnoreUpdateFields="icon,name")]
    public class Base_Menu : BaseEntity
    {
        public int ButtonMode { get; set; }

        public DateTime? CreateDate { get; set; }

        public int Creator { get; set; }

        public int Enabled { get; set; }

        public string icon
        {
            get
            {
                return "";
            }
        }

        public string iconclass { get; set; }

        public string iconCls { get; set; }

        public string IconUrl { get; set; }

        public string MenuCode { get; set; }

        public string MenuName { get; set; }

        public int MenuType { get; set; }

        public string name
        {
            get
            {
                return this.MenuName;
            }
        }

        public string ParentCode { get; set; }

        public string Remark { get; set; }

        public int Sort { get; set; }

        public DateTime? UpdateDate { get; set; }

        public int Updater { get; set; }

        public string Url { get; set; }
    }
}

