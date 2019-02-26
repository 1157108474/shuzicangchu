namespace System
{
    using System.Collections.Generic;
    using System.Collections.Specialized;
    using System.Linq;


    public static class NameValueCollection_Extentions
    {
        public static IDictionary<string, string> ToDictionary(this NameValueCollection source)
        {
            return source.AllKeys.ToDictionary<string, string, string>(k => k, k => source[k]);
        }

        public static IDictionary<string, string[]> ToDictionaryArray(this NameValueCollection source)
        {
            return source.AllKeys.ToDictionary<string, string, string[]>(k => k, k => source.GetValues(k));
        }
         
    }
}

