namespace EWMS.Utils
{
    using System;
    using System.IO;
    using System.Security.Cryptography;
    using System.Text;

    public class SymmetricEncrypt
    {
        private string Key = "Guz(%&hj7x89H$yuBI0456FtmaT5&fvHUFCy76*h%(HilJ$lhj!y6&(*jkP87jH7";
        private SymmetricAlgorithm mobjCryptoService = new RijndaelManaged();

        public string Decrypto(string Source)
        {
            byte[] buffer = Convert.FromBase64String(Source);
            this.mobjCryptoService.Key = this.GetLegalKey();
            this.mobjCryptoService.IV = this.GetLegalIV();
            return new StreamReader(new CryptoStream(new MemoryStream(buffer, 0, buffer.Length), this.mobjCryptoService.CreateDecryptor(), CryptoStreamMode.Read)).ReadToEnd();
        }

        public string Encrypto(string Source)
        {
            byte[] bytes = Encoding.UTF8.GetBytes(Source);
            this.mobjCryptoService.Key = this.GetLegalKey();
            this.mobjCryptoService.IV = this.GetLegalIV();
            ICryptoTransform transform = this.mobjCryptoService.CreateEncryptor();
            MemoryStream stream = new MemoryStream();
            CryptoStream stream2 = new CryptoStream(stream, transform, CryptoStreamMode.Write);
            stream2.Write(bytes, 0, bytes.Length);
            stream2.FlushFinalBlock();
            stream.Close();
            return Convert.ToBase64String(stream.ToArray());
        }

        private byte[] GetLegalIV()
        {
            string s = "E4ghj*Ghg7!rNIfb&95GUY86GfghUb#er57HBh(u%g6HJ($jhWk7&!hg4ui%$hjk";
            this.mobjCryptoService.GenerateIV();
            int length = this.mobjCryptoService.IV.Length;
            if (s.Length > length)
            {
                s = s.Substring(0, length);
            }
            else if (s.Length < length)
            {
                s = s.PadRight(length, ' ');
            }
            return Encoding.ASCII.GetBytes(s);
        }

        private byte[] GetLegalKey()
        {
            string key = this.Key;
            this.mobjCryptoService.GenerateKey();
            int length = this.mobjCryptoService.Key.Length;
            if (key.Length > length)
            {
                key = key.Substring(0, length);
            }
            else if (key.Length < length)
            {
                key = key.PadRight(length, ' ');
            }
            return Encoding.ASCII.GetBytes(key);
        }
    }
}

