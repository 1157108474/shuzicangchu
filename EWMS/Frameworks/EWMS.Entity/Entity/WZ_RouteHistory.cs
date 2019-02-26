namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="WZ_RouteHistory", PrimaryField="ID", IdentityField="ID", Order="ID DESC", IgnoreInsertFields="ID", IgnoreUpdateFields="ID")]
    public class WZ_RouteHistory : BaseEntity
    {
        public int ID { get; set; }

        public int NextID { get; set; }

        public int PrevID { get; set; }

        public int RouteID { get; set; }

        public int RouteStepID { get; set; }

        public int SheetID { get; set; }
    }
}

