namespace EWMS.Utils
{
    using System;
    using System.Collections.Specialized;
    using System.Web;

    public class CookiesUtil
    {
        public static string GetCookies(string CookiesName)
        {
            if ((HttpContext.Current.User != null) && (HttpContext.Current.Request.Cookies[CookiesName] != null))
            {
                return DESEncrypt.Decrypt(HttpContext.Current.Request.Cookies[CookiesName].Value);
            }
            return null;
        }

        public static string GetCookies(string CookiesName, string KeyName)
        {
            if (HttpContext.Current.Request.Cookies[CookiesName] == null)
            {
                return null;
            }
            if (DESEncrypt.Decrypt(HttpContext.Current.Request.Cookies[CookiesName].Value).IndexOf(KeyName + "=") == -1)
            {
                return null;
            }
            return DESEncrypt.Decrypt(HttpContext.Current.Request.Cookies[CookiesName][KeyName]);
        }

        public static void RemoveCookie(string CookiesName)
        {
            HttpCookie cookie = new HttpCookie(CookiesName.Trim()) {
                Expires = DateTime.Now.AddYears(-5)
            };
            HttpContext.Current.Response.Cookies.Add(cookie);
        }

        public static void WriteCookies(string CookiesName, int IExpires, NameValueCollection CookiesKeyValueCollection)
        {
            HttpCookie cookie = new HttpCookie(CookiesName.Trim());
            foreach (string str in CookiesKeyValueCollection.AllKeys)
            {
                cookie[str] = DESEncrypt.Encrypt(CookiesKeyValueCollection[str].Trim());
            }
            if (IExpires > 0)
            {
                if (IExpires == 1)
                {
                    cookie.Expires = DateTime.MaxValue;
                }
                else
                {
                    cookie.Expires = DateTime.Now.AddSeconds((double) IExpires);
                }
            }
            HttpContext.Current.Response.Cookies.Add(cookie);
        }

        public static void WriteCookies(string CookiesName, int IExpires, string CookiesValue)
        {
            HttpCookie cookie = new HttpCookie(CookiesName.Trim()) {
                Value = DESEncrypt.Encrypt(CookiesValue.Trim())
            };
            if (IExpires > 0)
            {
                if (IExpires == 1)
                {
                    cookie.Expires = DateTime.MaxValue;
                }
                else
                {
                    cookie.Expires = DateTime.Now.AddMinutes((double) IExpires);
                }
            }
            HttpContext.Current.Response.Cookies.Add(cookie);
        }

        public static void WriteCookies(string CookiesName, int IExpires, NameValueCollection CookiesKeyValueCollection, string CookiesDomain)
        {
            HttpCookie cookie = new HttpCookie(CookiesName.Trim());
            foreach (string str in CookiesKeyValueCollection.AllKeys)
            {
                cookie[str] = DESEncrypt.Encrypt(CookiesKeyValueCollection[str].Trim());
            }
            cookie.Domain = CookiesDomain.Trim();
            if (IExpires > 0)
            {
                if (IExpires == 1)
                {
                    cookie.Expires = DateTime.MaxValue;
                }
                else
                {
                    cookie.Expires = DateTime.Now.AddSeconds((double) IExpires);
                }
            }
            HttpContext.Current.Response.Cookies.Add(cookie);
        }

        public static void WriteCookies(string CookiesName, int IExpires, string CookiesValue, string CookiesDomain)
        {
            HttpCookie cookie = new HttpCookie(CookiesName.Trim()) {
                Value = DESEncrypt.Encrypt(CookiesValue.Trim()),
                Domain = CookiesDomain.Trim()
            };
            if (IExpires > 0)
            {
                if (IExpires == 1)
                {
                    cookie.Expires = DateTime.MaxValue;
                }
                else
                {
                    cookie.Expires = DateTime.Now.AddMinutes((double) IExpires);
                }
            }
            HttpContext.Current.Response.Cookies.Add(cookie);
        }
    }
}

