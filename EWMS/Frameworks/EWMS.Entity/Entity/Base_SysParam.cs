namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_SysParam", PrimaryField="ParamCode", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_SysParam : BaseEntity
    {
        public string AddBy { get; set; }

        public DateTime? AddOn { get; set; }

        public int AllowEdit { get; set; }

        public string EditBy { get; set; }

        public DateTime? EditOn { get; set; }

        public int Enabled { get; set; }

        public string ParamCode { get; set; }

        public string ParamValue { get; set; }

        public string Remark { get; set; }

        public int Sort { get; set; }
    }
}

