namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Log", PrimaryField="ID", IdentityField="", Order="LogTime DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Log : BaseEntity
    {
        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string ExtendString4 { get; set; }

        public string ExtendString5 { get; set; }

        public string GoodCode { get; set; }

        public int ID { get; set; }

        public string LogAction { get; set; }

        public string LogDesc { get; set; }

        public string LogIP { get; set; }

        public string LogObject { get; set; }

        public DateTime? LogTime { get; set; }

        public int LogType { get; set; }

        public int OperateCount { get; set; }

        public int Operator { get; set; }

        public string Remark { get; set; }

        public int SheetDetailID { get; set; }

        public int SheetID { get; set; }

        public int SheetKindID { get; set; }

        public string TrayCode { get; set; }
    }
}

