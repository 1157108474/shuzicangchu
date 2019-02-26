namespace EWMS.Utils
{
    using System;
    using System.Runtime.InteropServices;
    using System.Web;

    public class SessionUtil
    {
        public static bool ExistsSession(string sessionName)
        {
            if (HttpContext.Current.Session[sessionName] == null)
            {
                return false;
            }
            return true;
        }

        public static string[] Get(string sessionName)
        {
            return (string[]) HttpContext.Current.Session[sessionName];
        }

        public static T Get<T>(string sessionName)
        {
            T local = default(T);
            if (HttpContext.Current.Session[sessionName] != null)
            {
                return (T) HttpContext.Current.Session[sessionName];
            }
            return local;
        }

        public static void Remove(string sessionName)
        {
            HttpContext.Current.Session.Remove(sessionName);
        }

        public static void RemoveAll()
        {
            HttpContext.Current.Session.RemoveAll();
        }

        public static void Set(string sessionName, string[] value, int timeout = 20)
        {
            HttpContext.Current.Session[sessionName] = value;
            HttpContext.Current.Session.Timeout = timeout;
        }

        public static void Set<T>(string sessionName, T value, int timeout = 20)
        {
            HttpContext.Current.Session[sessionName] = value;
            HttpContext.Current.Session.Timeout = timeout;
        }
    }
}

