namespace EWMS.Core
{
    using ClownFish;
    using System;
    using System.Runtime.CompilerServices;

    public class PagingInfoExtension : PagingInfo
    {
        public string Columns { get; set; }

        public string Order { get; set; }

        public string OrderColumn { get; set; }

        public string TableName { get; set; }

        public string Where { get; set; }
    }
}

