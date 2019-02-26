namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="WZ_PurchasePlan", PrimaryField="ID", IdentityField="", Order="CreateDate DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class WZ_PurchasePlan : BaseEntity
    {
        public int ApplyDepID { get; set; }

        public decimal BaseCount { get; set; }

        public decimal BasePrice { get; set; }

        public string BaseUnit { get; set; }

        public int Consignment { get; set; }

        public decimal Count { get; set; }

        public DateTime CreateDate { get; set; }

        public int ERPDetailID { get; set; }

        public int ERPID { get; set; }

        public decimal ExtendFloat1 { get; set; }

        public decimal ExtendFloat2 { get; set; }

        public decimal ExtendFloat3 { get; set; }

        public decimal ExtendFloat4 { get; set; }

        public decimal ExtendFloat5 { get; set; }

        public decimal ExtendFloat6 { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public int ExtendInt5 { get; set; }

        public int ExtendInt6 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string ExtendString4 { get; set; }

        public string ExtendString5 { get; set; }

        public string ExtendString6 { get; set; }

        public string ExtendString7 { get; set; }

        public string ExtendString8 { get; set; }

        public int ID { get; set; }

        public string Manufacturer { get; set; }

        public string MaterialCode { get; set; }

        public string MaterialDes { get; set; }

        public DateTime NeedDate { get; set; }

        public string OrderType { get; set; }

        public string PlanCode { get; set; }

        public int PlanType { get; set; }

        public decimal Price { get; set; }

        public string PurchaseModel { get; set; }

        public int PurchaseType { get; set; }

        public string SourceType { get; set; }

        public string Unit { get; set; }

        public DateTime UpdateDate { get; set; }

        public int UseDepID { get; set; }

        public int ZTID { get; set; }
    }
}

