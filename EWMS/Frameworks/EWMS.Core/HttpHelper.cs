namespace EWMS.Core
{
    using System;
    using System.Collections.Generic;
    using System.IO;
    using System.Linq;
    using System.Net;
    using System.Net.Security;
    using System.Runtime.CompilerServices;
    using System.Runtime.InteropServices;
    using System.Security.Cryptography.X509Certificates;
    using System.Text;

    public class HttpHelper
    {
        public string ContentType = "application/x-www-form-urlencoded";
        public Encoding encoding = Encoding.UTF8;
        public Dictionary<string, string> Headers = new Dictionary<string, string>();
        public string HttpMethod = "GET";
        public bool isLoadCert;

        public X509Certificate2 CreateX509Certificate2()
        {
            X509Certificate2 certificate = null;
            try
            {
                certificate = new X509Certificate2(this.CertificateFilePath, this.CertificateFilePwd);
                ServicePointManager.ServerCertificateValidationCallback = new RemoteCertificateValidationCallback(this.ServerCertificateValidationCallback);
            }
            catch (Exception exception1)
            {
                throw exception1;
            }
            return certificate;
        }

        public string SendHttp(string url, string Content = "")
        {
            string str = "";
            HttpWebRequest request = (HttpWebRequest) WebRequest.Create(url);
            if (this.isLoadCert)
            {
                X509Certificate2 certificate = this.CreateX509Certificate2();
                request.ClientCertificates.Add(certificate);
            }
            request.Method = this.HttpMethod;
            request.KeepAlive = true;
            request.ContentType = this.ContentType;
            request.Timeout = 0x36ee80;
            if ((this.Headers != null) && (this.Headers.Count<KeyValuePair<string, string>>() > 0))
            {
                foreach (KeyValuePair<string, string> pair in this.Headers)
                {
                    request.Headers[pair.Key] = pair.Value;
                }
            }
            try
            {
                byte[] bytes = this.encoding.GetBytes(Content);
                if ((this.HttpMethod.ToLower().Trim() != "get") || !string.IsNullOrWhiteSpace(Content))
                {
                    request.ContentLength = bytes.Length;
                    request.GetRequestStream().Write(bytes, 0, bytes.Length);
                }
                HttpWebResponse response = (HttpWebResponse) request.GetResponse();
                if (response.StatusCode == HttpStatusCode.OK)
                {
                    str = new StreamReader(response.GetResponseStream(), this.encoding).ReadToEnd();
                }
            }
            catch (Exception exception1)
            {
                throw exception1;
            }
            return str;
        }

        public string SendHttp(string url, Encoding encodingMode, string content = "")
        {
            string str = "";
            HttpWebRequest request = (HttpWebRequest) WebRequest.Create(url);
            if (this.isLoadCert)
            {
                X509Certificate2 certificate = this.CreateX509Certificate2();
                request.ClientCertificates.Add(certificate);
            }
            request.Method = this.HttpMethod;
            request.KeepAlive = true;
            request.ContentType = this.ContentType;
            request.Timeout = 0x36ee80;
            try
            {
                if ((this.HttpMethod.ToLower().Trim() != "get") || !string.IsNullOrWhiteSpace(content))
                {
                    byte[] bytes = encodingMode.GetBytes(content);
                    request.ContentLength = bytes.Length;
                    request.GetRequestStream().Write(bytes, 0, bytes.Length);
                }
                HttpWebResponse response = (HttpWebResponse) request.GetResponse();
                if (response.StatusCode == HttpStatusCode.OK)
                {
                    str = new StreamReader(response.GetResponseStream(), encodingMode).ReadToEnd();
                }
            }
            catch (Exception exception1)
            {
                throw exception1;
            }
            return str;
        }

        private bool ServerCertificateValidationCallback(object obj, X509Certificate cer, X509Chain chain, SslPolicyErrors error)
        {
            return true;
        }

        public string CertificateFilePath { get; set; }

        public string CertificateFilePwd { get; set; }
    }
}

