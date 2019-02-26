namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    public class Trade_TJ : BaseEntity
    {
        public string batchnum { get; set; }

        public DateTime createdate { get; set; }

        public string memo { get; set; }

        public int outcount { get; set; }

        public string person { get; set; }

        public string status { get; set; }

        public int TRAYBACKCOUNT { get; set; }
    }
}

