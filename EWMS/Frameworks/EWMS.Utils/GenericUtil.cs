namespace EWMS.Utils
{

    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Dynamic;
    using System.Linq.Expressions;
    using System.Reflection;

    public class GenericUtil
    {
        public static T CreateNew<T>()
        {
            if (IsDynamicType(typeof(T)))
            {
                return default(T);
            }
            return Activator.CreateInstance<T>();
        }

        public static IDictionary<string, object> GetDictionaryValues(object item)
        {
            if (IsDynamicType(item.GetType()))
            {
                return (item as IDictionary<string, object>);
            }
            IDictionary<string, object> dictionary = new ExpandoObject();
            foreach (KeyValuePair<string, PropertyInfo> pair in ReflectionUtil.GetProperties(item.GetType()))
            {
                dictionary.Add(pair.Value.Name, pair.Value.GetValue(item, null));
            }
            return dictionary;
        }

        public static Type GetGenericType(object list)
        {
            return list.GetType().GetGenericArguments()[0];
        }

        public static Dictionary<string, Type> GetListProperties(dynamic list)
        {
            
            Type obj2 = GetGenericType(list);
            Dictionary<string, Type> dictionary = new Dictionary<string, Type>();
            if (IsDynamicType(obj2))
            {
                if (list.Count()>0)
                {
                    foreach (dynamic obj3 in GetDictionaryValues(list[0]))
                    {
                        dictionary.Add(obj3.Key, obj3.Value.GetType());
                    }
                }
                return dictionary;
            }
            foreach (KeyValuePair<string, PropertyInfo> obj4 in ReflectionUtil.GetProperties(obj2))
            {
                dictionary.Add(obj4.Value.Name, obj4.Value.PropertyType);
            }
            return dictionary;
        }

        public static object GetValue(object item, string name)
        {
            if (IsDynamicType(item.GetType()))
            {
                return ReflectionUtil.GetPropertyValueDynamic(item, name);
            }
            return ReflectionUtil.GetPropertyValue(item, name);
        }

        public static bool IsDynamicType(Type type)
        {
            if (!type.Equals(typeof(ExpandoObject)))
            {
                return type.Equals(typeof(object));
            }
            return true;
        }

        public static bool IsNullableType(Type theType)
        {
            return (theType.IsGenericType && theType.GetGenericTypeDefinition().Equals(typeof(Nullable<>)));
        }

        public static bool IsTypeIgoreNullable<T>(object value)
        {
            if (value == null)
            {
                return false;
            }
            Type type = value.GetType();
            if (type.IsGenericType)
            {
                type = type.GetGenericArguments()[0];
            }
            return type.Equals(typeof(T));
        }

        public static void SetValue(object item, string name, object value)
        {
            Type type = item.GetType();
            if (IsDynamicType(type))
            {
                ((IDictionary<string, object>) item).Add(name, value);
            }
            type.GetProperty(name).SetValue(item, value, null);
        }
        
    }
}

