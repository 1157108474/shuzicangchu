namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Category_Asset", PrimaryField="ID", IdentityField="", Order="CategoryCode", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Category_Asset : BaseEntity
    {
        public string AssetCode { get; set; }

        public string CategoryCode { get; set; }

        public int CategoryID { get; set; }

        public decimal ExtendFloat1 { get; set; }

        public decimal ExtendFloat2 { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public int ID { get; set; }
    }
}

