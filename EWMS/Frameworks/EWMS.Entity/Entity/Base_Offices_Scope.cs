namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Offices_Scope", PrimaryField="ID", IdentityField="", Order="", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Offices_Scope : BaseEntity
    {
        public DateTime? CreateDate { get; set; }

        public int Creator { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public int OfficesID { get; set; }

        public int ScopeID { get; set; }

        public int ScopeType { get; set; }

        public int ZTID { get; set; }
    }
}

