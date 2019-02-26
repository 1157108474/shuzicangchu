namespace EWMS.Core
{
    using FluentData;
    using System;
    using System.Runtime.CompilerServices;

    public class DeleteEventArgs
    {
        public ParamDeleteData data { get; set; }

        public IDbContext db { get; set; }

        public int executeValue { get; set; }
    }
}

