namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Inf_Task", PrimaryField="ID", IdentityField="", Order="CreateDate", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Inf_Task : BaseEntity
    {
        public int CallSystem { get; set; }

        public DateTime CreateDate { get; set; }

        public int Creator { get; set; }

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

        public int Inf_Kind { get; set; }

        public string Inf_TaskName { get; set; }

        public int Inf_TriggerKind { get; set; }

        public string Remark { get; set; }

        public int SupplySystem { get; set; }

        public int SyncKind { get; set; }

        public DateTime UpdateDate { get; set; }

        public int Updator { get; set; }
    }
}

