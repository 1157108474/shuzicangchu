namespace EWMS.Utils
{
    using System;
    using System.Security.Cryptography;
    using System.Text;

    public class HashEncode
    {
        public static string GetRandomValue()
        {
            return new Random().Next(1, 0x7fffffff).ToString();
        }

        public static string GetSecurity()
        {
            return HashEncoding(GetRandomValue());
        }

        public static string HashEncoding(string Security)
        {
            byte[] bytes = new UnicodeEncoding().GetBytes(Security);
            Security = "";
            foreach (byte num2 in new SHA512Managed().ComputeHash(bytes))
            {
                Security = Security + ((int) num2) + "O";
            }
            return Security;
        }
    }
}

