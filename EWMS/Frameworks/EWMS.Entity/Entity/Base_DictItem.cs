namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_DictItem", PrimaryField="DictItemCode", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="DictCode")]
    public class Base_DictItem : BaseEntity
    {
        public string AddBy { get; set; }

        public DateTime? AddOn { get; set; }

        public string DictCode { get; set; }

        public string DictItemCode { get; set; }

        public string DictItemName { get; set; }

        public string EditBy { get; set; }

        public DateTime? EditOn { get; set; }

        public int Enabled { get; set; }

        public string Remark { get; set; }

        public int Sort { get; set; }
    }
}

