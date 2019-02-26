namespace EWMS.Core
{
    using System;
    using FluentData;
    using System.Collections.Generic; 

    public class ParamSPData
    {
        public ParamSPData()
        {
            this.Name = string.Empty;
            this.Parameter = new Dictionary<string, object>();
            this.ParameterOut = new Dictionary<string, DataTypes>();
        }

        public string Name { get; set; }

        public Dictionary<string, object> Parameter { get; set; }

        public Dictionary<string, DataTypes> ParameterOut { get; set; }
    }
}

