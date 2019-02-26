namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Warehouse", PrimaryField="ID", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Warehouse : BaseEntity
    {
        public string Code { get; set; }

        public DateTime? CreateDate { get; set; }

        public int Creator { get; set; }

        public int EndFlag { get; set; }

        public decimal ExtendFloat1 { get; set; }

        public decimal ExtendFloat2 { get; set; }

        public decimal ExtendFloat3 { get; set; }

        public decimal ExtendFloat4 { get; set; }

        public decimal ExtendFloat5 { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt10 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public int ExtendInt5 { get; set; }

        public int ExtendInt6 { get; set; }

        public int ExtendInt7 { get; set; }

        public int ExtendInt8 { get; set; }

        public int ExtendInt9 { get; set; }

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

        public string LevelCode { get; set; }

        public int LevelCount { get; set; }

        public string Memo { get; set; }

        public string Name { get; set; }

        public int ParentID { get; set; }

        public int Property { get; set; }

        public int Sort { get; set; }

        public int Status { get; set; }

        public DateTime? UpdateDate { get; set; }

        public int Updater { get; set; }

        public int ZTID { get; set; }
    }
}

