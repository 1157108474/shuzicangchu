namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_CostUpdate", PrimaryField="ID", IdentityField="", Order="TRANSDATE", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_CostUpdate : BaseEntity
    {
        public decimal AMOUNT { get; set; }

        public string DOCNUM { get; set; }

        public int HEADERID { get; set; }

        public int ID { get; set; }

        public string INTERFACECODE { get; set; }

        public int LINEID { get; set; }

        public string MATERIALCODE { get; set; }

        public decimal NEWCOST { get; set; }

        public decimal OLDCOST { get; set; }

        public int ORGANZATIONID { get; set; }

        public decimal QUANTITY { get; set; }

        public int TRANSCATIONID { get; set; }

        public DateTime TRANSDATE { get; set; }

        public string UOM { get; set; }

        public DateTime UPDATEDATE { get; set; }
    }
}

