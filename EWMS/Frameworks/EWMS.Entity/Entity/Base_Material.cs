namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Material", PrimaryField="ID", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Material : BaseEntity
    {
        public string Brand { get; set; }

        public string Code { get; set; }

        public string ConfigMemo { get; set; }

        public DateTime CreateDate { get; set; }

        public int Creator { get; set; }

        public string Description { get; set; }

        public int EnableSN { get; set; }

        public decimal ExtendFloat1 { get; set; }

        public decimal ExtendFloat2 { get; set; }

        public decimal ExtendFloat3 { get; set; }

        public decimal ExtendFloat4 { get; set; }

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

        public int IsUseAlarm { get; set; }

        public string Memo { get; set; }

        public string Model { get; set; }

        public string Models { get; set; }

        public string Name { get; set; }

        public decimal Price { get; set; }

        public int ProviderID { get; set; }

        public int Sort { get; set; }

        public int SparescateID { get; set; }

        public string Specifications { get; set; }

        public int Status { get; set; }

        public decimal StockDown { get; set; }

        public decimal StockUp { get; set; }

        public string Unit { get; set; }

        public DateTime UpdateDate { get; set; }

        public int Updater { get; set; }

        public int ZTID { get; set; }
    }
}

