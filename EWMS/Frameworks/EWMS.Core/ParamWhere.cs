namespace EWMS.Core
{
    using System;
    using System.Runtime.CompilerServices;

    public class ParamWhere
    {
        public Func<WhereData, string> Compare { get; set; }

        public WhereData Data { get; set; }
    }
}

