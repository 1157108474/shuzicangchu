namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="", PrimaryField="", IdentityField="", Order=" ", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Query_ZCGD : BaseEntity
    {
        public int count { get; set; }

        public string type { get; set; }
    }
}

