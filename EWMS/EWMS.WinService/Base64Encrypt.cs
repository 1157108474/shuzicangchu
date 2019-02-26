namespace EWMS.WinService
{
    using System;
    using System.Text;

    public class Base64Encrypt
    {
        public static string Decrypt(string codeType, string Message)
        {
            byte[] bytes = Convert.FromBase64String(Message);
            return Encoding.GetEncoding(codeType).GetString(bytes);
        }

        public static string Encrypt(string codeType, string Message)
        {
            return Convert.ToBase64String(Encoding.GetEncoding(codeType).GetBytes(Message));
        }
    }
}

