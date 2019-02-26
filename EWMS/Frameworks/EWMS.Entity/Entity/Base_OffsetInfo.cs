namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_OffsetInfo", PrimaryField="ID", IdentityField="", Order="", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_OffsetInfo : BaseEntity
    {
        public DateTime CreateDate { get; set; }

        public int DetailID { get; set; }

        public int ID { get; set; }

        public string InterfaceType { get; set; }

        public int OrganizationID { get; set; }

        public string ProviderCode { get; set; }

        public int ProviderID { get; set; }

        public string ProviderName { get; set; }

        public string ProviderPlaceCode { get; set; }

        public int ProviderPlaceID { get; set; }

        public string SheetCode { get; set; }

        public int SheetID { get; set; }

        public int TransID { get; set; }

        public DateTime UpdateDate { get; set; }
    }
}

