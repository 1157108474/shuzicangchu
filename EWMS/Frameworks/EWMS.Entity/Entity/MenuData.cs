namespace EWMS.Entity
{
    using System;
    using System.Runtime.CompilerServices;

    public class MenuData
    {
        public string btns { get; set; }

        public int buttonmode { get; set; }

        public bool expanded { get; set; }

        public bool isLeaf { get; set; }

        public string memo { get; set; }

        public string menucode { get; set; }

        public string menucodeold { get; set; }

        public string menuname { get; set; }

        public int menutype { get; set; }

        public string parentcode { get; set; }

        public int sort { get; set; }

        public int status { get; set; }

        public string url { get; set; }
    }
}

