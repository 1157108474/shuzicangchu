namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    public class Stat_RKTJ : BaseEntity
    {
        public string BatchNum { get; set; }

        public int DetailCount { get; set; }

        public string gradelevel { get; set; }

        public string gradelevelname { get; set; }

        public DateTime mxcreatedate { get; set; }

        public string ProductType { get; set; }

        public string ProductTypename { get; set; }

        public decimal TotalWeight { get; set; }

        public string TradeMark { get; set; }

        public DateTime wzcreatedate { get; set; }

        public decimal wzTotalWeight { get; set; }

        public DateTime wzupdatedate { get; set; }
    }
}

