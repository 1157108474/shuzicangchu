namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_UserRole", PrimaryField="RoleCode", IdentityField="", Order="RoleCode", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_UserRole : BaseEntity
    {
        public string RoleCode { get; set; }

        public int UserId { get; set; }
    }
}

