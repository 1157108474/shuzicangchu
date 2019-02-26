namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="WZ_SHEETRKDETAIL", PrimaryField="ID", IdentityField="", Order="CreateDate DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class WZ_SHEETRKDETAIL : BaseEntity
    {
        public int CategoryId { get; set; }

        public DateTime CreateDate { get; set; }

        public int Creator { get; set; }

        public string CurrencyUnit { get; set; }

        public string Description { get; set; }

        public decimal DetailCount { get; set; }

        public int DetailUnit { get; set; }

        public string DetailUnitName { get; set; }

        public int EnableSN { get; set; }

        public DateTime ExpirationTime { get; set; }

        public DateTime ExtendDate1 { get; set; }

        public DateTime ExtendDate2 { get; set; }

        public DateTime ExtendDate3 { get; set; }

        public DateTime ExtendDate4 { get; set; }

        public decimal ExtendFloat1 { get; set; }

        public decimal ExtendFloat2 { get; set; }

        public decimal ExtendFloat3 { get; set; }

        public decimal ExtendFloat4 { get; set; }

        public decimal ExtendFloat5 { get; set; }

        public decimal ExtendFloat6 { get; set; }

        public decimal ExtendFloat7 { get; set; }

        public decimal ExtendFloat8 { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public int ExtendInt5 { get; set; }

        public int ExtendInt6 { get; set; }

        public int ExtendInt7 { get; set; }

        public int ExtendInt8 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString10 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string ExtendString4 { get; set; }

        public string ExtendString5 { get; set; }

        public string ExtendString6 { get; set; }

        public string ExtendString7 { get; set; }

        public string ExtendString8 { get; set; }

        public string ExtendString9 { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public int ISEquipment { get; set; }

        public string MaterialBrand { get; set; }

        public string MaterialCode { get; set; }

        public int MaterialId { get; set; }

        public string MaterialModel { get; set; }

        public string MaterialName { get; set; }

        public string MaterialSpecification { get; set; }

        public string Memo { get; set; }

        public decimal NotaxPrice { get; set; }

        public decimal NoTaxSum { get; set; }

        public int OwnerType { get; set; }

        public int PlanDepartID { get; set; }

        public int ProviderDepId { get; set; }

        public int SheetDetailId { get; set; }

        public int SheetId { get; set; }

        public string SNCode { get; set; }

        public int Status { get; set; }

        public int StoreId { get; set; }

        public string StoreLocationCode { get; set; }

        public int StoreLocationId { get; set; }

        public string StoreLocationName { get; set; }

        public string TagCode { get; set; }

        public decimal TaxPrice { get; set; }

        public decimal TaxRate { get; set; }

        public decimal TaxSum { get; set; }

        public DateTime UpdateDate { get; set; }

        public int Updator { get; set; }

        public int ZTID { get; set; }
    }
}

