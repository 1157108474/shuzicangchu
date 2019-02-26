namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_UserSetting", PrimaryField="Id", IdentityField="Id", Order="Id", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_UserSetting : BaseEntity
    {
        public string BDesc { get; set; }

        public int Id { get; set; }

        public string SettingCode { get; set; }

        public string SettingName { get; set; }

        public string SettingValue { get; set; }

        public int UserId { get; set; }
    }
}

