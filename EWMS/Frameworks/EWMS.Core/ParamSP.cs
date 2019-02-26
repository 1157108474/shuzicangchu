namespace EWMS.Core
{
    using FluentData;
    using System;

    public class ParamSP
    {
        protected ParamSPData data = new ParamSPData();

        public ParamSPData GetData()
        {
            return this.data;
        }

        public static ParamSP Instance()
        {
            return new ParamSP();
        }

        public ParamSP Name(string name)
        {
            this.data.Name = name;
            return this;
        }

        public ParamSP Parameter(string name, object value)
        {
            this.data.Parameter.Add(name, value);
            return this;
        }

        public ParamSP ParameterOut(string name, DataTypes type)
        {
            this.data.ParameterOut.Add(name, type);
            return this;
        }
    }
}

