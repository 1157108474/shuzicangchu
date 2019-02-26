namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_SparepartsCate", PrimaryField="ID", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_SparepartsCate : BaseEntity
    {
        public string Code { get; set; }

        public DateTime? CreateDate { get; set; }

        public int Creator { get; set; }

        public int EndFlag { get; set; }

        public int ExtendInt1 { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public string LevelCode { get; set; }

        public int LevelCount { get; set; }

        public string Memo { get; set; }

        public string Name { get; set; }

        public int ParentID { get; set; }

        public int Sort { get; set; }

        public int Status { get; set; }

        public DateTime? UpdateDate { get; set; }

        public int Updater { get; set; }
    }
}

