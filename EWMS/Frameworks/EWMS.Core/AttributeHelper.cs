namespace EWMS.Core
{
    using System;
    using System.Collections.Concurrent;
    using System.Runtime.CompilerServices;

    public class AttributeHelper
    {
        private static readonly ConcurrentDictionary<Type, string> _cachedModule = new ConcurrentDictionary<Type, string>();
        private static readonly ConcurrentDictionary<Type, Attribute> _cachedPrimaryKeyAttribute = new ConcurrentDictionary<Type, Attribute>();

        public static TAttribute GetCustomAttribute<TAttribute>(Type type) where TAttribute: Attribute
        {
            return (TAttribute) _cachedPrimaryKeyAttribute.GetOrAdd(type, delegate (Type t) {
                object[] customAttributes = t.GetCustomAttributes(typeof(TAttribute), false);
                if (customAttributes.Length == 0)
                {
                    return null;
                }
                return (TAttribute) customAttributes[0];
            });
        }

        public static string GetModuleName(Type type)
        {
            return _cachedModule.GetOrAdd(type, delegate (Type t) {
                object[] customAttributes = t.GetCustomAttributes(typeof(ModuleAttribute), false);
                if (customAttributes.Length == 0)
                {
                    return APP.Db_Default_ConnName;
                }
                return ((ModuleAttribute) customAttributes[0]).ModuleName;
            });
        } 
    }
}

