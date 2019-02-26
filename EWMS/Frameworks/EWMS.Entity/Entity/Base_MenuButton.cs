namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_MenuButton", PrimaryField="ButtonCode", IdentityField="", Order="ButtonCode", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_MenuButton : BaseEntity
    {
        public string ButtonCode { get; set; }

        public string ButtonSort { get; set; }

        public string ButtonText { get; set; }

        public string MenuCode { get; set; }
    }
}

