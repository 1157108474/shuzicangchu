namespace EWMS.Entity
{
    using System;
    using System.Collections.Generic;
    using System.Runtime.CompilerServices;

    public class dict_treedata
    {
        public dict_attributes attributes { get; set; }

        public List<dict_treedata> children { get; set; }

        public string iconCls { get; set; }

        public string id { get; set; }

        public string state { get; set; }

        public string text { get; set; }
    }
}

