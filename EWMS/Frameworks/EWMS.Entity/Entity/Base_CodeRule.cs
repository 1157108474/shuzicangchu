namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_CodeRule", PrimaryField="RuleCode", IdentityField="", Order="Sort", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_CodeRule : BaseEntity
    {
        public string AddBy { get; set; }

        public DateTime? AddOn { get; set; }

        public string EditBy { get; set; }

        public DateTime? EditOn { get; set; }

        public int Enabled { get; set; }

        public string FillChar { get; set; }

        public string Format { get; set; }

        public int Length { get; set; }

        public string Remark { get; set; }

        public string RuleCode { get; set; }

        public string RuleName { get; set; }

        public int Seed { get; set; }

        public int Sort { get; set; }

        public string StartChars { get; set; }
    }
}

