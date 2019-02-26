namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_SysLog", PrimaryField="Id", IdentityField="Id", Order="LogTime DESC", IgnoreInsertFields="LogTime", IgnoreUpdateFields="LogTime")]
    public class Base_SysLog : BaseEntity
    {
        public int Id { get; set; }

        public string LogAction { get; set; }

        public string LogDesc { get; set; }

        public string LogIP { get; set; }

        public string LogIPCity { get; set; }

        public string LogObject { get; set; }

        public DateTime LogTime { get; set; }

        public int LogType { get; set; }
    }
}

