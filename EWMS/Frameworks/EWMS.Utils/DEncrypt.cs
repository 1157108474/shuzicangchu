namespace EWMS.Utils
{
    using System;
    using System.Security.Cryptography;
    using System.Text;

    public class DEncrypt
    {
        public static string Decrypt(string original)
        {
            return Decrypt(original, "MICHAELJEFF", Encoding.Default);
        }

        public static byte[] Decrypt(byte[] encrypted)
        {
            byte[] bytes = Encoding.Default.GetBytes("MICHAELJEFF");
            return Decrypt(encrypted, bytes);
        }

        public static string Decrypt(string original, string key)
        {
            return Decrypt(original, key, Encoding.Default);
        }

        public static byte[] Decrypt(byte[] encrypted, byte[] key)
        {
            TripleDESCryptoServiceProvider provider1 = new TripleDESCryptoServiceProvider {
                Key = MakeMD5(key),
                Mode = CipherMode.ECB
            };
            return provider1.CreateDecryptor().TransformFinalBlock(encrypted, 0, encrypted.Length);
        }

        public static string Decrypt(string encrypted, string key, Encoding encoding)
        {
            byte[] buffer = Convert.FromBase64String(encrypted);
            byte[] bytes = Encoding.Default.GetBytes(key);
            return encoding.GetString(Decrypt(buffer, bytes));
        }

        public static string Encrypt(string original)
        {
            return Encrypt(original, "MICHAELJEFF");
        }

        public static byte[] Encrypt(byte[] original)
        {
            byte[] bytes = Encoding.Default.GetBytes("MICHAELJEFF");
            return Encrypt(original, bytes);
        }

        public static string Encrypt(string original, string key)
        {
            return Convert.ToBase64String(Encrypt(Encoding.Default.GetBytes(original), Encoding.Default.GetBytes(key)));
        }

        public static byte[] Encrypt(byte[] original, byte[] key)
        {
            TripleDESCryptoServiceProvider provider1 = new TripleDESCryptoServiceProvider {
                Key = MakeMD5(key),
                Mode = CipherMode.ECB
            };
            return provider1.CreateEncryptor().TransformFinalBlock(original, 0, original.Length);
        }

        public static byte[] MakeMD5(byte[] original)
        {
            return new MD5CryptoServiceProvider().ComputeHash(original);
        }
    }
}

