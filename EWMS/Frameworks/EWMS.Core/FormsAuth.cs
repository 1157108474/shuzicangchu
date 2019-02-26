namespace EWMS.Core
{
    using Newtonsoft.Json;
    using System;
    using System.Web;
    using System.Web.Security;

    public static class FormsAuth
    {
        public static BaseLoginer GetBaseLoginerData()
        {
            return GetUserData<BaseLoginer>();
        }

        private static T GetUserData<T>() where T: class, new()
        {
            T local = Activator.CreateInstance<T>();
            try
            {
                local = JsonConvert.DeserializeObject<T>(FormsAuthentication.Decrypt(HttpContext.Current.Request.Cookies[FormsAuthentication.FormsCookieName].Value).UserData);
            }
            catch
            {
            }
            return local;
        }

        public static void SignIn(string loginName, object userData, int expireMin)
        {
            string str = JsonConvert.SerializeObject(userData);
            string str2 = FormsAuthentication.Encrypt(new FormsAuthenticationTicket(2, loginName, DateTime.Now, DateTime.Now.AddDays(1.0), true, str));
            HttpCookie cookie = new HttpCookie(FormsAuthentication.FormsCookieName, str2) {
                HttpOnly =false,
                Secure = false,
                //Domain = FormsAuthentication.CookieDomain,
                Domain = null,
                //Path = FormsAuthentication.FormsCookiePath
                Path = "/"
            };
            if (expireMin > 0)
            {
                cookie.Expires = DateTime.Now.AddMinutes((double) expireMin);
            }
            HttpContext current = HttpContext.Current;
            if (current == null)
            {
                throw new InvalidOperationException();
            }
            current.Response.Cookies.Remove(cookie.Name);
            current.Response.Cookies.Add(cookie);
            bool isAuthenticated = HttpContext.Current.Request.IsAuthenticated;
        }

        public static void SignOut()
        {
            FormsAuthentication.SignOut();
        }

        public static bool IsAuthenticated
        {
            get
            {
                return HttpContext.Current.Request.IsAuthenticated;
            }
        }
    }
}

