namespace EWMS.Utils
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Reflection;
    using System.Web;

    public class RequestToModel
    {
        public static List<T> GetListByForm<T>() where T: new()
        {
            return SetList<T>(null, 0);
        }

        public static List<T> GetListByForm<T>(string appstr) where T: new()
        {
            return SetList<T>(appstr, 0);
        }

        private static List<T> GetListByForm<T>(string appstr, int index) where T: new()
        {
            return SetList<T>(appstr, index);
        }

        public static T GetSingleForm<T>() where T: new()
        {
            return SetList<T>(null, 0).Single<T>();
        }

        public static T GetSingleForm<T>(string appstr) where T: new()
        {
            return SetList<T>(appstr, 0).Single<T>();
        }

        private static List<T> SetList<T>(string appendstr, int index) where T: new()
        {
            List<T> list = new List<T>();
            try
            {
                PropertyInfo[] properties = Activator.CreateInstance<T>().GetType().GetProperties();
                char[] separator = new char[] { ',' };
                int length = HttpContext.Current.Request[appendstr + properties[index].Name].Split(separator).Length;
                for (int i = 0; i < length; i++)
                {
                    T local2 = Activator.CreateInstance<T>();
                    foreach (PropertyInfo info in properties)
                    {
                        string str = HttpContext.Current.Request[(appendstr + info.Name) ?? ""];
                        if (!string.IsNullOrEmpty(str))
                        {
                            char[] chArray2 = new char[] { ',' };
                            str = str.Split(chArray2)[i];
                            string name = info.PropertyType.Name;
                            info.SetValue(local2, Convert.ChangeType(str, info.PropertyType), null);
                        }
                    }
                    list.Add(local2);
                }
            }
            catch (Exception exception1)
            {
                throw exception1;
            }
            return list;
        }
    }
}

