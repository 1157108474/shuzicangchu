namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="WZ_OrderInfo", PrimaryField="ID", IdentityField="", Order="CreateDate DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class WZ_OrderInfo : BaseEntity
    {
        public string BaseUnit { get; set; }

        public decimal BaseUnitCount { get; set; }

        public decimal BaseUnitPrice { get; set; }

        public decimal BaseUnitSum { get; set; }

        public decimal BaseUnitTaxPrice { get; set; }

        public int BusinessID { get; set; }

        public int Consignment { get; set; }

        public DateTime CreateDate { get; set; }

        public int Creator { get; set; }

        public int DepartID { get; set; }

        public string Description { get; set; }

        public decimal DetailCount { get; set; }

        public string DetailUnit { get; set; }

        public string ERPID { get; set; }

        public int ERPProviderDepID { get; set; }

        public string ERPRowNum { get; set; }

        public int ERPStockOrgID { get; set; }

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

        public int FYID { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public int IssueCode { get; set; }

        public int IssueID { get; set; }

        public string MaterialCode { get; set; }

        public decimal NoTaxPrice { get; set; }

        public decimal NoTaxSum { get; set; }

        public int OrderID { get; set; }

        public string OrderNum { get; set; }

        public int OrderRowID { get; set; }

        public string OrderType { get; set; }

        public string ProviderDepCode { get; set; }

        public int ProviderDepID { get; set; }

        public string ProviderDepName { get; set; }

        public string ProviderPlaceCode { get; set; }

        public int ProviderPlaceID { get; set; }

        public string StockOrgCode { get; set; }

        public int StockOrgID { get; set; }

        public decimal TaxPrice { get; set; }

        public decimal TaxRate { get; set; }

        public DateTime UpdateDate { get; set; }

        public int Updator { get; set; }
    }
}

