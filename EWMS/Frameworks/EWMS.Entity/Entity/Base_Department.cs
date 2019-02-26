namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Department", PrimaryField="DepartmentCode", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Department : BaseEntity
    {
        public string AddBy { get; set; }

        public DateTime? AddOn { get; set; }

        public string CompanyCode { get; set; }

        public string DepartmentCode { get; set; }

        public string DepartmentName { get; set; }

        public string EditBy { get; set; }

        public DateTime? EditOn { get; set; }

        public string Email { get; set; }

        public int Enabled { get; set; }

        public string Fax { get; set; }

        public string ParentCode { get; set; }

        public string Phone { get; set; }

        public string Remark { get; set; }

        public string ShortName { get; set; }

        public int Sort { get; set; }
    }
}

