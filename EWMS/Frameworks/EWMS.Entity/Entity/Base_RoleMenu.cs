namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_RoleMenu", PrimaryField="MenuCode", IdentityField="", Order="MenuCode", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_RoleMenu : BaseEntity
    {
        public string MenuCode { get; set; }

        public string RoleCode { get; set; }
    }
}

