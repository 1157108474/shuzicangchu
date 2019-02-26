namespace EWMS.Utils
{
    using System;
    using System.Security.Cryptography;
    using System.Text;

    public class AESEncrypt
    {
        private static byte[] AESKeys = new byte[] { 0x41, 0x72, 0x65, 0x79, 0x6f, 0x75, 0x6d, 0x79, 0x53, 110, 0x6f, 0x77, 0x6d, 0x61, 110, 0x3f };

        public static string Decrypt(string decryptString, string decryptKey)
        {
            try
            {
                decryptKey = StringUtil.GetSubString(decryptKey, 0x20, "");
                decryptKey = decryptKey.PadRight(0x20, ' ');
                RijndaelManaged managed1 = new RijndaelManaged {
                    Key = Encoding.UTF8.GetBytes(decryptKey),
                    IV = AESKeys
                };
                byte[] inputBuffer = Convert.FromBase64String(decryptString);
                byte[] bytes = managed1.CreateDecryptor().TransformFinalBlock(inputBuffer, 0, inputBuffer.Length);
                return Encoding.UTF8.GetString(bytes);
            }
            catch
            {
                return "";
            }
        }

        public static string Encrypt(string encryptString, string encryptKey)
        {
            encryptKey = StringUtil.GetSubString(encryptKey, 0x20, "");
            encryptKey = encryptKey.PadRight(0x20, ' ');
            RijndaelManaged managed1 = new RijndaelManaged {
                Key = Encoding.UTF8.GetBytes(encryptKey.Substring(0, 0x20)),
                IV = AESKeys
            };
            byte[] bytes = Encoding.UTF8.GetBytes(encryptString);
            return Convert.ToBase64String(managed1.CreateEncryptor().TransformFinalBlock(bytes, 0, bytes.Length));
        }
    }
}

