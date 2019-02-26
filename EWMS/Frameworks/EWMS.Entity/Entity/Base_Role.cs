namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Role", PrimaryField="RoleCode", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Role : BaseEntity
    {
        public string CreateDate { get; set; }

        public int Creator { get; set; }

        public int Enabled { get; set; }

        public string Remark { get; set; }

        public string RoleCode { get; set; }

        public string RoleName { get; set; }

        public int RoleType { get; set; }

        public int Sort { get; set; }

        public string UpdateDate { get; set; }

        public int Updator { get; set; }
    }
}

