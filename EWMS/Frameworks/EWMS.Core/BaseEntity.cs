namespace EWMS.Core
{
    using EWMS.Utils;
    using System;
    using System.Collections.Concurrent;
    using System.Collections.Generic;
    using System.Dynamic;
    using System.Reflection;
    using System.Linq;

    public class BaseEntity : ICloneable, IComparable, IDisposable
    {
        private static readonly ConcurrentDictionary<Type, Dictionary<string, List<string>>> _cachedAtrributes = new ConcurrentDictionary<Type, Dictionary<string, List<string>>>();

        private static Dictionary<string, List<string>> BuildAtrributeDictionary(Type TEntity)
        {
            Dictionary<string, List<string>> dictionary = new Dictionary<string, List<string>>();
            foreach (PropertyInfo info in TEntity.GetProperties())
            {
                Attribute[] customAttributes = info.GetCustomAttributes(typeof(Attribute), true) as Attribute[];
                if (customAttributes != null)
                {
                    foreach (string str in from attr in customAttributes select attr.GetType().Name)
                    {
                        if (!dictionary.ContainsKey(str))
                        {
                            dictionary.Add(str, new List<string>());
                        }
                        dictionary[str].Add(info.Name);
                    }
                }
            }
            return dictionary;
        }

        public object Clone()
        {
            return base.MemberwiseClone();
        }

        public void Dispose()
        {
            this.Dispose();
        }

        public dynamic Extend(object obj)
        {
            IDictionary<string, object> expando = new ExpandoObject();
            EachHelper.EachObjectProperty(this, delegate (int i, string name, object value) {
                expando.Add(name, value);
            });
            EachHelper.EachObjectProperty(obj, delegate (int i, string name, object value) {
                expando[name] = value;
            });
            return expando;
        }

        public static List<string> GetAttributeFields<TEntity, TAttribute>()
        {
            string name = typeof(TAttribute).Name;
            Dictionary<string, List<string>> orAdd = _cachedAtrributes.GetOrAdd(typeof(TEntity), new Func<Type, Dictionary<string, List<string>>>(BaseEntity.BuildAtrributeDictionary));
            if (!orAdd.ContainsKey(name))
            {
                return new List<string>();
            }
            return orAdd[typeof(TAttribute).Name];
        }

        public object GetValue(string propertyName)
        {
            return base.GetType().GetProperty(propertyName).GetValue(this, null);
        }

        public virtual int OnSort(object obj)
        {
            return 0;
        }

        int IComparable.CompareTo(object obj)
        {
            return this.OnSort(obj);
        }

        public int tempid { get; set; }
         
    }
}

