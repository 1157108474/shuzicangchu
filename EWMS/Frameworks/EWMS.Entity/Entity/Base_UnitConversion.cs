namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_UnitConversion", PrimaryField="ID", IdentityField="", Order="", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_UnitConversion : BaseEntity
    {
        public string AfterUnit { get; set; }

        public string BeforeUnit { get; set; }

        public int UnitID { get; set; }

        public decimal UnitRatio { get; set; }

        public string UnitRelation { get; set; }
    }
}

