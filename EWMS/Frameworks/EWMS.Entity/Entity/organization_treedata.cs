namespace EWMS.Entity
{
    using System;
    using System.Runtime.CompilerServices;

    public class organization_treedata
    {
        public string code { get; set; }

        public int companyid { get; set; }

        public bool expanded { get; set; }

        public int id { get; set; }

        public bool isLeaf { get; set; }

        public int level { get; set; }

        public string memo { get; set; }

        public int order { get; set; }

        public int pid { get; set; }

        public int status { get; set; }

        public string text { get; set; }

        public int type { get; set; }
    }
}

