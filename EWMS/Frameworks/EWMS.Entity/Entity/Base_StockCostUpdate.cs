namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_StockCostUpdate", PrimaryField="ID", IdentityField="", Order="UpdateDate", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_StockCostUpdate : BaseEntity
    {
        public int ID { get; set; }

        public int LastTransID { get; set; }

        public string MaterialCode { get; set; }

        public decimal NewCost { get; set; }

        public int OrganizationID { get; set; }

        public DateTime UpdateDate { get; set; }
    }
}

