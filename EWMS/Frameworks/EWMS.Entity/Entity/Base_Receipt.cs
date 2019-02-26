namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Receipt", PrimaryField="ID", IdentityField="", Order="", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Receipt : BaseEntity
    {
        public DateTime? CreateDate { get; set; }

        public int Creator { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string ExtendString4 { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public string MenuCode { get; set; }

        public string Prefix { get; set; }

        public int ProcessID { get; set; }

        public string ReceiptCode { get; set; }

        public string ReceiptMemo { get; set; }

        public string ReceiptName { get; set; }

        public int ReceiptSort { get; set; }

        public int ReceiptStatus { get; set; }

        public int ReceiptType { get; set; }

        public string ReportName { get; set; }

        public DateTime? UpdateDate { get; set; }

        public int Updater { get; set; }
    }
}

