namespace EWMS.Utils
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Reflection;


    public class ObjectMapper
    {
        public static void CopyProperties(object source, object target)
        {
            IList<PropertyMapper> mapperProperties = GetMapperProperties(source.GetType(), target.GetType());
            int num = 0;
            int count = mapperProperties.Count;
            while (num < count)
            {
                PropertyMapper local1 = mapperProperties[num];
                object obj2 = local1.SourceProperty.GetValue(source, null);
                local1.TargetProperty.SetValue(target, obj2, null);
                num++;
            }
        }

        public static IList<PropertyMapper> GetMapperProperties(Type sourceType, Type targetType)
        {
            PropertyInfo[] targetProperties = targetType.GetProperties();
            return (from s in sourceType.GetProperties()
                from t in targetProperties
                where (((s.Name == t.Name) && s.CanRead) && t.CanWrite) && (s.PropertyType == t.PropertyType)
                select new PropertyMapper { 
                    SourceProperty = s,
                    TargetProperty = t
                }).ToList<PropertyMapper>();
        }
        
    }
}

