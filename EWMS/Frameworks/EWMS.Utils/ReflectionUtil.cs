namespace EWMS.Utils
{
    using System;
    using System.Collections;
    using System.Collections.Concurrent;
    using System.Collections.Generic;
    using System.Linq.Expressions;
    using System.Reflection;

    public static class ReflectionUtil
    {
        private static readonly ConcurrentDictionary<Type, Dictionary<string, PropertyInfo>> _cachedProperties = new ConcurrentDictionary<Type, Dictionary<string, PropertyInfo>>();

        private static Dictionary<string, PropertyInfo> BuildPropertyDictionary(Type type)
        {
            Dictionary<string, PropertyInfo> dictionary = new Dictionary<string, PropertyInfo>();
            foreach (PropertyInfo info in type.GetProperties())
            {
                dictionary.Add(info.Name.ToLower(), info);
            }
            return dictionary;
        }

        public static object GetDefault(Type type)
        {
            if (type.IsValueType)
            {
                return Activator.CreateInstance(type);
            }
            return null;
        }

        public static Dictionary<string, PropertyInfo> GetProperties(Type type)
        {
            return _cachedProperties.GetOrAdd(type, new Func<Type, Dictionary<string, PropertyInfo>>(ReflectionUtil.BuildPropertyDictionary));
        }

        public static string GetPropertyNameFromExpression<T>(Expression<Func<T, object>> expression)
        {
            string str = null;
            if (expression.Body is UnaryExpression)
            {
                UnaryExpression body = (UnaryExpression) expression.Body;
                if (body.NodeType == ExpressionType.Convert)
                {
                    str = body.Operand.ToString();
                }
            }
            if (str == null)
            {
                str = expression.Body.ToString();
            }
            return str.Replace(expression.Parameters[0] + ".", string.Empty);
        }

        public static List<string> GetPropertyNamesFromExpressions<T>(Expression<Func<T, object>>[] expressions)
        {
            List<string> list = new List<string>();
            Expression<Func<T, object>>[] expressionArray = expressions;
            for (int i = 0; i < expressionArray.Length; i++)
            {
                string propertyNameFromExpression = GetPropertyNameFromExpression<T>(expressionArray[i]);
                list.Add(propertyNameFromExpression);
            }
            return list;
        }

        public static Type GetPropertyType(PropertyInfo property)
        {
            if (IsNullable(property))
            {
                return property.PropertyType.GetGenericArguments()[0];
            }
            return property.PropertyType;
        }

        public static object GetPropertyValue(object item, PropertyInfo property)
        {
            return property.GetValue(item, null);
        }

        public static object GetPropertyValue(object item, string propertyName)
        {
            char[] separator = new char[] { '.' };
            foreach (string str in propertyName.Split(separator))
            {
                if (item == null)
                {
                    return null;
                }
                PropertyInfo property = item.GetType().GetProperty(str);
                if (property == null)
                {
                    return null;
                }
                item = GetPropertyValue(item, property);
            }
            return item;
        }

        public static object GetPropertyValueDynamic(object item, string name)
        {
            return ((IDictionary<string, object>) item)[name];
        }

        public static bool IsBasicClrType(Type type)
        {
            if ((!type.IsEnum && !type.IsPrimitive) && ((!type.IsValueType && !(type == typeof(string))) && !(type == typeof(DateTime))))
            {
                return false;
            }
            return true;
        }

        public static bool IsList(object item)
        {
            return (item is ICollection);
        }

        public static bool IsNullable(PropertyInfo property)
        {
            return (property.PropertyType.IsGenericType && (property.PropertyType.GetGenericTypeDefinition() == typeof(Nullable<>)));
        }
    }
}

