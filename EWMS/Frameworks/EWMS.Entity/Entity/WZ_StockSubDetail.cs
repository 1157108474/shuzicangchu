namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="WZ_StockSubDetail", PrimaryField="ID", IdentityField="", Order="AddTime DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class WZ_StockSubDetail : BaseEntity
    {
        public DateTime AddTime { get; set; }

        public int DetailID { get; set; }

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

        public string GUID { get; set; }

        public int ID { get; set; }

        public int StoreID { get; set; }

        public string StoreLocationCode { get; set; }

        public int StoreLocationId { get; set; }

        public string StoreLocationName { get; set; }

        public decimal SubDetailCount { get; set; }

        public string TagCode { get; set; }

        public int Unit { get; set; }
    }
}

