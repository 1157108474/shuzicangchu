namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Inf_TaskLog", PrimaryField="ID", IdentityField="", Order="CreateDate", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Inf_TaskLog : BaseEntity
    {
        public DateTime CreateDate { get; set; }

        public int Creator { get; set; }

        public float ExtendFloat1 { get; set; }

        public float ExtendFloat2 { get; set; }

        public float ExtendFloat3 { get; set; }

        public float ExtendFloat4 { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string ExtendString4 { get; set; }

        public string ExtendString5 { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public string Inf_Content { get; set; }

        public string Inf_ErrorContent { get; set; }

        public string Inf_TaskDetailName { get; set; }

        public int Inf_TaskKind { get; set; }

        public string Remark { get; set; }

        public int SyncNum { get; set; }

        public int SyncResult { get; set; }

        public int TaskId { get; set; }

        public DateTime UpdateDate { get; set; }

        public int Updator { get; set; }
    }
}

