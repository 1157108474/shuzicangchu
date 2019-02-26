namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="WZ_ReceivingLog", PrimaryField="ID", IdentityField="", Order="CreateDate DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class WZ_ReceivingLog : BaseEntity
    {
        public string Content { get; set; }

        public decimal Count { get; set; }

        public DateTime CreateDate { get; set; }

        public int Creater { get; set; }

        public decimal ExtendFloat1 { get; set; }

        public decimal ExtendFloat2 { get; set; }

        public decimal ExtendFloat3 { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public int OperationType { get; set; }

        public int OrderID { get; set; }

        public string RelationGUID { get; set; }
    }
}

