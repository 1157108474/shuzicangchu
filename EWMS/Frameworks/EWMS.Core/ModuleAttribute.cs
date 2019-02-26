namespace EWMS.Core
{
    using System;
    using System.Runtime.CompilerServices;

    [AttributeUsage(AttributeTargets.Class, AllowMultiple=false)]
    public class ModuleAttribute : Attribute
    {
        public ModuleAttribute(string name)
        {
            this.ModuleName = name;
        }

        public string ModuleName { get; set; }
    }
}

