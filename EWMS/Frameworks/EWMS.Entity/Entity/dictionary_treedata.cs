﻿namespace EWMS.Entity
{
    using System;
    using System.Runtime.CompilerServices;

    public class dictionary_treedata
    {
        public string code { get; set; }

        public bool expanded { get; set; }

        public string fjcode { get; set; }

        public string fjname { get; set; }

        public int id { get; set; }

        public bool isLeaf { get; set; }

        public int isuse { get; set; }

        public string memo { get; set; }

        public int pid { get; set; }

        public int sort { get; set; }

        public string text { get; set; }
    }
}

