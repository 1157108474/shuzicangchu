namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_RoleMenuButton", PrimaryField="ButtonCode", IdentityField="", Order="ButtonCode", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_RoleMenuButton : BaseEntity
    {
        public string ButtonCode { get; set; }

        public string MenuCode { get; set; }

        public string RoleCode { get; set; }
    }
}

