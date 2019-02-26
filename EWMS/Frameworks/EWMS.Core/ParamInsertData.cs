namespace EWMS.Core
{
    using System;
    using System.Collections.Generic;
    using System.Runtime.CompilerServices;

    public class ParamInsertData
    {
        public ParamInsertData()
        {
            this.TableName = "";
            this.Columns = new Dictionary<string, object>();
        }

        public Dictionary<string, object> Columns { get; set; }

        public string TableName { get; set; }
    }
}

