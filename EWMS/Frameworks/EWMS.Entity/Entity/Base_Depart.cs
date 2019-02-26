namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="V_BASE_DEPART", PrimaryField="ID", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Depart : BaseEntity
    {
        public string Code { get; set; }

        public int COMPANYID { get; set; }

        public DateTime? CreateDate { get; set; }

        public int Creator { get; set; }

        public int ID { get; set; }

        public string LevelCode { get; set; }

        public int LevelCount { get; set; }

        public string Memo { get; set; }

        public string Name { get; set; }

        public int ParentID { get; set; }

        public int Sort { get; set; }

        public int Status { get; set; }

        public int type { get; set; }

        public DateTime? UpdateDate { get; set; }

        public int Updater { get; set; }

        public int ZTID { get; set; }
    }
}

