namespace EWMS.Core
{
    using System;
    using System.Collections.Generic;
    using System.Reflection;
    using System.Runtime.InteropServices;

    public static class CpHelper
    {
        private static Dictionary<string, Func<WhereData, string>> _dictionary = new Dictionary<string, Func<WhereData, string>>();

        static CpHelper()
        {
            foreach (MethodInfo info in typeof(Cp).GetMethods(BindingFlags.Public | BindingFlags.Static))
            {
                MethodInfo temp = info;
                _dictionary.Add(info.Name.ToLower(), data => (string) temp.Invoke(null, new object[] { data }));
            }
        }

        public static Func<WhereData, string> Parse(string str, Func<WhereData, string> defaultCp = null)
        {
            str = (str ?? string.Empty).ToLower();
            if (!_dictionary.ContainsKey(str) && (defaultCp == null))
            {
                return new Func<WhereData, string>(Cp.Equal);
            }
            return _dictionary[str];
        }
    }
}

