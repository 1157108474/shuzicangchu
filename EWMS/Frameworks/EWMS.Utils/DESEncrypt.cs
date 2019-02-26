namespace EWMS.Utils
{
    using System;
    using System.IO;
    using System.Security.Cryptography;
    using System.Text;

    public class DESEncrypt
    {
        private static string encryptKey = "km4250km";

        public static string Decrypt(string text)
        {
            return Decrypt(text, encryptKey);
        }

        public static string Decrypt(string text, string key)
        {
            byte[] bytes = Encoding.UTF8.GetBytes(key.Substring(0, 8));
            byte[] rgbIV = bytes;
            byte[] buffer = Convert.FromBase64String(text);
            DESCryptoServiceProvider provider = new DESCryptoServiceProvider();
            MemoryStream stream = new MemoryStream();
            CryptoStream stream1 = new CryptoStream(stream, provider.CreateDecryptor(bytes, rgbIV), CryptoStreamMode.Write);
            stream1.Write(buffer, 0, buffer.Length);
            stream1.FlushFinalBlock();
            return Encoding.UTF8.GetString(stream.ToArray());
        }

        public static string Encrypt(string text)
        {
            return Encrypt(text, encryptKey);
        }

        public static string Encrypt(string text, string key)
        {
            try
            {
                byte[] bytes = Encoding.UTF8.GetBytes(key.Substring(0, 8));
                byte[] rgbIV = bytes;
                byte[] buffer = Encoding.UTF8.GetBytes(text);
                DESCryptoServiceProvider provider = new DESCryptoServiceProvider();
                MemoryStream stream = new MemoryStream();
                CryptoStream stream2 = new CryptoStream(stream, provider.CreateEncryptor(bytes, rgbIV), CryptoStreamMode.Write);
                stream2.Write(buffer, 0, buffer.Length);
                stream2.FlushFinalBlock();
                return Convert.ToBase64String(stream.ToArray());
            }
            catch
            {
                return text;
            }
        }
    }
}

