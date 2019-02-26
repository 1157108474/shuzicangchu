namespace EWMS.Utils
{
    using System;
    using System.IO;
    using System.Net;
    using System.Runtime.InteropServices;
    using System.Text;
    using System.Web;

    public class HttpUtil
    {
        public static string Get(string url)
        {
            HttpWebRequest request1 = WebRequest.Create(new Uri(url)) as HttpWebRequest;
            request1.Method = "GET";
            request1.AllowAutoRedirect = true;
            request1.KeepAlive = true;
            request1.Headers.Add(HttpRequestHeader.AcceptCharset, "GBK,utf-8;q=0.7,*;q=0.3");
            request1.Headers.Add(HttpRequestHeader.AcceptEncoding, "gzip, deflate");
            request1.Headers.Add(HttpRequestHeader.AcceptLanguage, "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
            request1.Accept = "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8";
            request1.ContentType = "application/x-www-form-urlencoded";
            request1.Timeout = 0xea60;
            using (HttpWebResponse response = request1.GetResponse() as HttpWebResponse)
            {
                return new StreamReader(response.GetResponseStream()).ReadToEnd();
            }
        }

        public static string GetServerValue(string name)
        {
            return HttpContext.Current.Request.ServerVariables[name];
        }

        public static string Post(string url, string param, int time = 0xea60)
        {
            HttpWebRequest request = WebRequest.Create(new Uri(url)) as HttpWebRequest;
            request.Method = "POST";
            request.ContentType = "application/json;charset=utf-8";
            request.Timeout = time;
            byte[] bytes = Encoding.UTF8.GetBytes((param == null) ? "" : param);
            request.ContentLength = bytes.Length;
            using (Stream stream = request.GetRequestStream())
            {
                stream.Write(bytes, 0, bytes.Length);
            }
            using (HttpWebResponse response = request.GetResponse() as HttpWebResponse)
            {
                return new StreamReader(response.GetResponseStream()).ReadToEnd();
            }
        }

        public static string ResolveUrl(string url)
        {
            if ((string.IsNullOrEmpty(url) || url.Contains("://")) || !url.StartsWith("~"))
            {
                return url;
            }
            int index = url.IndexOf('?');
            if (index != -1)
            {
                string str = url.Substring(index);
                return (VirtualPathUtility.ToAbsolute(url.Substring(0, index)) + str);
            }
            return VirtualPathUtility.ToAbsolute(url);
        }

        public static string SendGet(string url, string contentType = "application/x-www-form-urlencoded")
        {
            WebRequest request1 = WebRequest.Create(url);
            request1.Method = "GET";
            request1.ContentType = contentType;
            string str = string.Empty;
            using (WebResponse response = request1.GetResponse())
            {
                if (response == null)
                {
                    return str;
                }
                using (Stream stream = response.GetResponseStream())
                {
                    using (StreamReader reader = new StreamReader(stream, Encoding.UTF8))
                    {
                        return reader.ReadToEnd();
                    }
                }
            }
        }

        public static string SendPost(string url, string data)
        {
            return SendPost(url, "application/x-www-form-urlencoded", data);
        }

        public static string SendPost(string url, string contentType, string requestData)
        {
            WebRequest request = WebRequest.Create(url);
            request.Method = "POST";
            byte[] bytes = null;
            request.ContentType = contentType;
            bytes = Encoding.UTF8.GetBytes(requestData);
            request.ContentLength = bytes.Length;
            using (Stream stream = request.GetRequestStream())
            {
                stream.Write(bytes, 0, bytes.Length);
            }
            string str = string.Empty;
            using (WebResponse response = request.GetResponse())
            {
                if (response == null)
                {
                    return str;
                }
                using (Stream stream2 = response.GetResponseStream())
                {
                    using (StreamReader reader = new StreamReader(stream2, Encoding.UTF8))
                    {
                        return reader.ReadToEnd();
                    }
                }
            }
        }

        public static string ServerPathToUrl(string ServerPath)
        {
            string oldValue = HttpContext.Current.Server.MapPath(HttpContext.Current.Request.ApplicationPath.ToString());
            string str2 = ServerPath.Replace(oldValue, "");
            return ("/" + str2.Replace(@"\", "/"));
        }

        public static string Host
        {
            get
            {
                return HttpContext.Current.Request.Url.Host;
            }
        }

        public static string LocalIP
        {
            get
            {
                return HttpContext.Current.Request.ServerVariables["LOCAL_ADDR"].ToString();
            }
        }

        public static string OS
        {
            get
            {
                return Environment.OSVersion.Platform.ToString();
            }
        }

        public static int Port
        {
            get
            {
                return HttpContext.Current.Request.Url.Port;
            }
        }

        public static string RootFullPath
        {
            get
            {
                if (Port == 80)
                {
                    return string.Format("{0}://{1}{2}", HttpContext.Current.Request.Url.Scheme, Host, RootPath);
                }
                return string.Format("{0}://{1}:{2}{3}", new object[] { HttpContext.Current.Request.Url.Scheme, Host, Port, RootPath });
            }
        }

        public static string RootPath
        {
            get
            {
                string applicationPath = HttpContext.Current.Request.ApplicationPath;
                if (applicationPath.EndsWith("/"))
                {
                    return applicationPath;
                }
                return (applicationPath + "/");
            }
        }

        public static string RootPhysicalPath
        {
            get
            {
                return HttpContext.Current.Request.PhysicalApplicationPath;
            }
        }

        public static string ServerDomain
        {
            get
            {
                return HttpContext.Current.Request.ServerVariables["SERVER_NAME"].ToString();
            }
        }
    }
}

