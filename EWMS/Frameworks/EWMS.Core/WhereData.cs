namespace EWMS.Core
{
    using System;
    using System.Runtime.CompilerServices;

    public class WhereData
    {
        public string AndOr { get; set; }

        public string Column { get; set; }

        public object[] Extend { get; set; }

        public object Value { get; set; }
    }
}

