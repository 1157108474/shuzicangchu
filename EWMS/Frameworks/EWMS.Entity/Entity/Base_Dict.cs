namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Dict", PrimaryField="DictCode", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Dict : BaseEntity
    {
        public string AddBy { get; set; }

        public DateTime? AddOn { get; set; }

        public string DictCode { get; set; }

        public string DictName { get; set; }

        public string EditBy { get; set; }

        public DateTime? EditOn { get; set; }

        public int Enabled { get; set; }

        public string ParentDictCode { get; set; }

        public string Remark { get; set; }

        public int Sort { get; set; }
    }
}

