namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Company", PrimaryField="CompanyID", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Company : BaseEntity
    {
        public string CompanyCode { get; set; }

        public int CompanyID { get; set; }

        public string CompanyName { get; set; }

        public int CompanyType { get; set; }

        public DateTime? CreateDate { get; set; }

        public int Creator { get; set; }

        public string Email { get; set; }

        public int Enabled { get; set; }

        public string Fax { get; set; }

        public string Manager { get; set; }

        public int ParentID { get; set; }

        public string Phone { get; set; }

        public string Remark { get; set; }

        public string ShortName { get; set; }

        public int Sort { get; set; }

        public string Tel { get; set; }

        public DateTime? UpdateDate { get; set; }

        public int Updator { get; set; }

        public string WebSite { get; set; }

        public string ZipCode { get; set; }

        public int ZTID { get; set; }
    }
}

