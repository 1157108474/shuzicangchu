namespace EWMS.Utils
{
    using Microsoft.Win32;
    using System;
    using System.IO;
    using System.Management;
    using System.Security.Cryptography;
    using System.Text;

    public class RSAEncrypt
    {
        public void CreatePrivateKeyXML(string path, string privatekey)
        {
            try
            {
                FileStream stream = new FileStream(path, FileMode.Create);
                StreamWriter writer1 = new StreamWriter(stream);
                writer1.WriteLine(privatekey);
                writer1.Close();
                stream.Close();
            }
            catch
            {
                throw;
            }
        }

        public void CreatePublicKeyXML(string path, string publickey)
        {
            try
            {
                FileStream stream = new FileStream(path, FileMode.Create);
                StreamWriter writer1 = new StreamWriter(stream);
                writer1.WriteLine(publickey);
                writer1.Close();
                stream.Close();
            }
            catch
            {
                throw;
            }
        }

        public string Decrypt(string xmlPrivateKey, string m_strDecryptString)
        {
            string str;
            try
            {
                RSACryptoServiceProvider provider1 = new RSACryptoServiceProvider();
                provider1.FromXmlString(xmlPrivateKey);
                byte[] bytes = provider1.Decrypt(Convert.FromBase64String(m_strDecryptString), false);
                str = new UnicodeEncoding().GetString(bytes);
            }
            catch (Exception exception1)
            {
                throw exception1;
            }
            return str;
        }

        public string Encrypt(string xmlPublicKey, string m_strEncryptString)
        {
            string str;
            try
            {
                RSACryptoServiceProvider provider1 = new RSACryptoServiceProvider();
                provider1.FromXmlString(xmlPublicKey);
                str = Convert.ToBase64String(provider1.Encrypt(new UnicodeEncoding().GetBytes(m_strEncryptString), false));
            }
            catch (Exception exception1)
            {
                throw exception1;
            }
            return str;
        }

        public string GetHardID()
        {
            string str = "";
            using (ManagementObjectCollection.ManagementObjectEnumerator enumerator = new ManagementClass("Win32_DiskDrive").GetInstances().GetEnumerator())
            {
                while (enumerator.MoveNext())
                {
                    str = (string) ((ManagementObject) enumerator.Current).Properties["Model"].Value;
                }
            }
            return str;
        }

        public string GetHash(string m_strSource)
        {
            return Convert.ToBase64String(HashAlgorithm.Create("MD5").ComputeHash(Encoding.GetEncoding("GB2312").GetBytes(m_strSource)));
        }

        private string ReadReg(string key)
        {
            string str2;
            string str = "";
            try
            {
                RegistryKey key1 = Registry.LocalMachine.OpenSubKey("SOFTWARE/JX/Register");
                str = key1.GetValue(key).ToString();
                key1.Close();
                Registry.LocalMachine.Close();
                str2 = str;
            }
            catch (Exception)
            {
                throw;
            }
            return str2;
        }

        public void RSAKey(string PrivateKeyPath, string PublicKeyPath)
        {
            try
            {
                RSACryptoServiceProvider provider = new RSACryptoServiceProvider();
                this.CreatePrivateKeyXML(PrivateKeyPath, provider.ToXmlString(true));
                this.CreatePublicKeyXML(PublicKeyPath, provider.ToXmlString(false));
            }
            catch (Exception exception1)
            {
                throw exception1;
            }
        }

        public bool SignatureDeformatter(string p_strKeyPublic, string p_strHashbyteDeformatter, string p_strDeformatterData)
        {
            try
            {
                byte[] rgbHash = Convert.FromBase64String(p_strHashbyteDeformatter);
                RSACryptoServiceProvider key = new RSACryptoServiceProvider();
                key.FromXmlString(p_strKeyPublic);
                RSAPKCS1SignatureDeformatter deformatter1 = new RSAPKCS1SignatureDeformatter(key);
                deformatter1.SetHashAlgorithm("MD5");
                byte[] rgbSignature = Convert.FromBase64String(p_strDeformatterData);
                return deformatter1.VerifySignature(rgbHash, rgbSignature);
            }
            catch
            {
                return false;
            }
        }

        public string SignatureFormatter(string p_strKeyPrivate, string m_strHashbyteSignature)
        {
            byte[] rgbHash = Convert.FromBase64String(m_strHashbyteSignature);
            RSACryptoServiceProvider key = new RSACryptoServiceProvider();
            key.FromXmlString(p_strKeyPrivate);
            RSAPKCS1SignatureFormatter formatter1 = new RSAPKCS1SignatureFormatter(key);
            formatter1.SetHashAlgorithm("MD5");
            return Convert.ToBase64String(formatter1.CreateSignature(rgbHash));
        }

        private void WriteReg(string key, string value)
        {
            try
            {
                RegistryKey key1 = Registry.LocalMachine.CreateSubKey("SOFTWARE/JX/Register");
                key1.SetValue(key, value);
                key1.Close();
            }
            catch (Exception)
            {
                throw;
            }
        }
    }
}

