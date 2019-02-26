namespace EWMS.Utils
{
    using Microsoft.Win32;
    using System;
    using System.IO;
    using System.Security.Cryptography;
    using System.Text;
    using System.Threading;
    using System.Web;
    using System.Web.UI.WebControls;
    using System.Windows.Forms;

    public class FileUtil
    {
        private const string PATH_SPLIT_CHAR = @"\";

        public static void CopyFiles(string sourceDir, string targetDir, bool overWrite, bool copySubDir)
        {
            foreach (string str in Directory.GetFiles(sourceDir))
            {
                string path = Path.Combine(targetDir, str.Substring(str.LastIndexOf(@"\") + 1));
                if (File.Exists(path))
                {
                    if (overWrite)
                    {
                        File.SetAttributes(path, FileAttributes.Normal);
                        File.Copy(str, path, overWrite);
                    }
                }
                else
                {
                    File.Copy(str, path, overWrite);
                }
            }
        }

        public static void CreateDirectory(string targetDir)
        {
            DirectoryInfo info = new DirectoryInfo(targetDir);
            if (!info.Exists)
            {
                info.Create();
            }
        }

        public static void CreateDirectory(string parentDir, string subDirName)
        {
            CreateDirectory(parentDir + @"\" + subDirName);
        }

        public static void DeleteDirectory(string targetDir)
        {
            DirectoryInfo info = new DirectoryInfo(targetDir);
            if (info.Exists)
            {
                DeleteDirectoryFiles(targetDir, true);
                info.Delete(true);
            }
        }

        public static void DeleteDirectoryFiles(string TargetDir, bool delSubDir)
        {
            foreach (string text1 in Directory.GetFiles(TargetDir))
            {
                File.SetAttributes(text1, FileAttributes.Normal);
                File.Delete(text1);
            }
            if (delSubDir)
            {
                foreach (DirectoryInfo info1 in new DirectoryInfo(TargetDir).GetDirectories())
                {
                    DeleteDirectoryFiles(info1.FullName, true);
                    info1.Delete();
                }
            }
        }

        public static void DeleteFiles(string TargetFileDir)
        {
            File.Delete(TargetFileDir);
        }

        public static void DeleteSubDirectory(string targetDir)
        {
            string[] directories = Directory.GetDirectories(targetDir);
            for (int i = 0; i < directories.Length; i++)
            {
                DeleteDirectory(directories[i]);
            }
        }

        public static bool DirectoryIsExists(string StrPath)
        {
            return new DirectoryInfo(StrPath).Exists;
        }

        public static void DirectoryIsExists(string StrPath, bool Create)
        {
            DirectoryInfo info = new DirectoryInfo(StrPath);
            if (!info.Exists && Create)
            {
                info.Create();
            }
        }

        public static void DownloadFile(HttpContext context, string fileName)
        {
            HttpResponse response = context.Response;
            HttpCookie cookie = context.Request.Cookies["downloadstatus"];
            if (cookie == null)
            {
                cookie = new HttpCookie("downloadstatus", string.Empty);
            }
            HttpCookie cookie2 = new HttpCookie("downloaderror", string.Empty);
            FileInfo info = new FileInfo(fileName);
            if (info.Exists)
            {
                response.Clear();
                cookie.Value = "success";
                response.SetCookie(cookie);
                response.Buffer = true;
                response.AddHeader("content-disposition", "attachment;filename=" + info.Name);
                response.Charset = "";
                response.ContentType = GetContentType(info.Name);
                response.TransmitFile(info.FullName);
                response.Flush();
                response.End();
            }
            else
            {
                cookie.Value = "fail";
                cookie2.Value = "File " + info.Name + " doesn't exists on the server";
                response.SetCookie(cookie);
                response.SetCookie(cookie2);
            }
        }

        public static bool DownloadFile(HttpContext context, string filePath, long speed)
        {
            string fileName = Path.GetFileName(filePath);
            Stream myFile = new FileStream(filePath, FileMode.Open, FileAccess.Read, FileShare.ReadWrite);
            return DownloadFile(context, myFile, fileName, speed);
        }

        public static bool DownloadFile(HttpContext context, Stream myFile, string fileName, long speed)
        {
            bool flag = true;
            try
            {
                long num = 0L;
                int count = 0x2800;
                BinaryReader reader = new BinaryReader(myFile);
                long length = myFile.Length;
                int millisecondsTimeout = (int) Math.Ceiling((double) ((1000.0 * count) / ((double) speed)));
                if (myFile.Length > 0x7fffffffL)
                {
                    context.Response.StatusCode = 0x19d;
                    return false;
                }
                try
                {
                    try
                    {
                        context.Response.Clear();
                        context.Response.Buffer = false;
                        context.Response.AddHeader("Content-MD5", GetHashMD5(myFile));
                        context.Response.AddHeader("Accept-Ranges", "bytes");
                        context.Response.ContentType = "application/octet-stream";
                        context.Response.AddHeader("Content-Disposition", "attachment;filename=" + HttpUtility.UrlEncode(fileName, Encoding.UTF8).Replace("+", "%20"));
                        long num6 = length - num;
                        context.Response.AddHeader("Content-Length", num6.ToString());
                        context.Response.AddHeader("Connection", "Keep-Alive");
                        context.Response.ContentEncoding = Encoding.UTF8;
                        if (context.Request.Headers["Range"] != null)
                        {
                            context.Response.StatusCode = 0xce;
                            char[] separator = new char[] { '=', '-' };
                            num = Convert.ToInt64(context.Request.Headers["Range"].Split(separator)[1]);
                            if ((num < 0L) || (num >= length))
                            {
                                return false;
                            }
                        }
                        if (num > 0L)
                        {
                            context.Response.AddHeader("Content-Range", string.Format(" bytes {0}-{1}/{2}", num, length - 1L, length));
                        }
                        reader.BaseStream.Seek(num, SeekOrigin.Begin);
                        int num5 = (int) Math.Ceiling((double) (((length - num) + 0.0) / ((double) count)));
                        for (int i = 0; (i < num5) && context.Response.IsClientConnected; i++)
                        {
                            context.Response.BinaryWrite(reader.ReadBytes(count));
                            context.Response.Flush();
                            if (millisecondsTimeout > 1)
                            {
                                Thread.Sleep(millisecondsTimeout);
                            }
                        }
                    }
                    catch
                    {
                        flag = false;
                    }
                    return flag;
                }
                finally
                {
                    reader.Close();
                    myFile.Close();
                }
            }
            catch
            {
                flag = false;
            }
            return flag;
        }

        public static void DownLoadFile(string FileFullPath)
        {
            if (!string.IsNullOrEmpty(FileFullPath) && FileExists(FileFullPath))
            {
                FileInfo info = new FileInfo(FileFullPath);
                FileFullPath = HttpUtility.UrlEncode(FileFullPath);
                FileFullPath = FileFullPath.Replace("+", "%20");
                HttpContext.Current.Response.Clear();
                HttpContext.Current.Response.ContentType = "application/octet-stream";
                HttpContext.Current.Response.AppendHeader("Content-Disposition", "attachment; filename=" + FileFullPath);
                HttpContext.Current.Response.AppendHeader("content-length", info.Length.ToString());
                int count = 0x19000;
                byte[] buffer = new byte[count];
                using (FileStream stream = info.Open(FileMode.Open))
                {
                    while ((stream.Position >= 0L) && HttpContext.Current.Response.IsClientConnected)
                    {
                        int num3 = stream.Read(buffer, 0, count);
                        if (num3 <= 0)
                        {
                            return;
                        }
                        HttpContext.Current.Response.OutputStream.Write(buffer, 0, num3);
                        HttpContext.Current.Response.Flush();
                        Thread.Sleep(10);
                    }
                }
            }
        }

        public static bool FileExists(string filename)
        {
            return File.Exists(filename);
        }

        public static string FilesUpload(FileUpload myFileUpload, string[] allowExtensions, int maxLength, string savePath, string saveName)
        {
            bool flag = false;
            if (!myFileUpload.HasFile)
            {
                return "请选择要上传的文件！";
            }
            if (((myFileUpload.PostedFile.ContentLength / 0x400) / 0x400) >= maxLength)
            {
                return string.Format("只能上传小于{0}M的文件！", maxLength);
            }
            string str = Path.GetExtension(myFileUpload.FileName).ToLower();
            string str2 = "";
            for (int i = 0; i < allowExtensions.Length; i++)
            {
                str2 = str2 + ((i == (allowExtensions.Length - 1)) ? allowExtensions[i] : (allowExtensions[i] + ","));
                if (str == allowExtensions[i])
                {
                    flag = true;
                }
            }
            if (allowExtensions.Length == 0)
            {
                flag = true;
            }
            if (flag)
            {
                try
                {
                    DirectoryInfo info = new DirectoryInfo(Path.GetFullPath(savePath));
                    if (!info.Exists)
                    {
                        info.Create();
                    }
                    string filename = savePath + ((saveName == "") ? myFileUpload.FileName : saveName);
                    myFileUpload.SaveAs(filename);
                    return "";
                }
                catch (Exception exception1)
                {
                    throw new Exception(exception1.Message);
                }
            }
            return ("文件格式不符，可以上传的文件格式为：" + str2);
        }

        public static string GetAppCurrentDirectory()
        {
            return Application.StartupPath;
        }

        public static string GetContentType(string fileName)
        {
            string str = "application/octetstream";
            string name = Path.GetExtension(fileName).ToLower();
            RegistryKey key = Registry.ClassesRoot.OpenSubKey(name);
            if ((key != null) && (key.GetValue("Content Type") != null))
            {
                str = key.GetValue("Content Type").ToString();
            }
            return str;
        }

        public string GetFileExtension(string PathFileName)
        {
            return Path.GetExtension(PathFileName);
        }

        public static string GetFileSize(FileInfo File)
        {
            long length = File.Length;
            if (length >= 0x40000000L)
            {
                if (((((length / 0x400L) * 0x400L) * 0x400L) * 0x400L) >= 0x400L)
                {
                    return string.Format("{0:############0.00} TB", (((((double) length) / 1024.0) * 1024.0) * 1024.0) * 1024.0);
                }
                return string.Format("{0:####0.00} GB", ((((double) length) / 1024.0) * 1024.0) * 1024.0);
            }
            if (length >= 0x100000L)
            {
                return string.Format("{0:####0.00} MB", (((double) length) / 1024.0) * 1024.0);
            }
            if (length >= 0x400L)
            {
                return string.Format("{0:####0.00} KB", ((double) length) / 1024.0);
            }
            return string.Format("{0:####0.00} Bytes", length);
        }

        public static string GetFileSize(string FilePath)
        {
            long length = new FileInfo(FilePath).Length;
            if (length >= 0x40000000L)
            {
                if (((((length / 0x400L) * 0x400L) * 0x400L) * 0x400L) >= 0x400L)
                {
                    return string.Format("{0:########0.00} TB", (((((double) length) / 1024.0) * 1024.0) * 1024.0) * 1024.0);
                }
                return string.Format("{0:####0.00} GB", ((((double) length) / 1024.0) * 1024.0) * 1024.0);
            }
            if (length >= 0x100000L)
            {
                return string.Format("{0:####0.00} MB", (((double) length) / 1024.0) * 1024.0);
            }
            if (length >= 0x400L)
            {
                return string.Format("{0:####0.00} KB", ((double) length) / 1024.0);
            }
            return string.Format("{0:####0.00} Bytes", length);
        }

        public DateTime GetFileWriteTime(string FileUrl)
        {
            return File.GetLastWriteTime(FileUrl);
        }

        public static string GetHashMD5(Stream stream)
        {
            MD5CryptoServiceProvider provider1 = new MD5CryptoServiceProvider();
            provider1.ComputeHash(stream);
            StringBuilder builder = new StringBuilder();
            foreach (byte num2 in provider1.Hash)
            {
                builder.Append(string.Format("{0:X2}", num2));
            }
            return builder.ToString();
        }

        public static string GetHashMD5(string file)
        {
            FileStream stream = new FileStream(file, FileMode.Open, FileAccess.Read, FileShare.Read, 0x2000);
            string str = GetHashMD5(stream);
            stream.Close();
            return str;
        }

        public static string[] GetLocalDrives()
        {
            return Directory.GetLogicalDrives();
        }

        public bool IsHiddenFile(string path)
        {
            return (File.GetAttributes(path).ToString().LastIndexOf("Hidden") != -1);
        }

        public static void MoveFiles(string sourceDir, string targetDir, bool overWrite, bool moveSubDir)
        {
            foreach (string str in Directory.GetFiles(sourceDir))
            {
                string path = Path.Combine(targetDir, str.Substring(str.LastIndexOf(@"\") + 1));
                if (File.Exists(path))
                {
                    if (overWrite)
                    {
                        File.SetAttributes(path, FileAttributes.Normal);
                        File.Delete(path);
                        File.Move(str, path);
                    }
                }
                else
                {
                    File.Move(str, path);
                }
            }
            if (moveSubDir)
            {
                foreach (string str3 in Directory.GetDirectories(sourceDir))
                {
                    string str4 = Path.Combine(targetDir, str3.Substring(str3.LastIndexOf(@"\") + 1));
                    if (!Directory.Exists(str4))
                    {
                        Directory.CreateDirectory(str4);
                    }
                    MoveFiles(str3, str4, overWrite, true);
                    Directory.Delete(str3);
                }
            }
        }

        public static string ReadFile(string FilePath)
        {
            string str = string.Empty;
            Encoding encoding = Encoding.GetEncoding("gb2312");
            StreamReader reader = new StreamReader(FilePath, encoding);
            try
            {
                str = reader.ReadToEnd();
                reader.Close();
            }
            catch
            {
            }
            finally
            {
                if (reader != null)
                {
                    reader.Dispose();
                }
            }
            return str;
        }

        public static string ReadTxtFile(string FilePath)
        {
            string str = "";
            using (FileStream stream = new FileStream(FilePath, FileMode.Open, FileAccess.Read, FileShare.Read))
            {
                using (StreamReader reader = new StreamReader(stream, Encoding.UTF8))
                {
                    string str2 = string.Empty;
                    while (!reader.EndOfStream)
                    {
                        str2 = str2 + reader.ReadLine() + "\r\n";
                        str = str2;
                    }
                }
            }
            return str;
        }

        public static bool ReNameFloder(string OldFloderName, string NewFloderName)
        {
            try
            {
                if (Directory.Exists(HttpContext.Current.Server.MapPath("//") + OldFloderName))
                {
                    Directory.Move(HttpContext.Current.Server.MapPath("//") + OldFloderName, HttpContext.Current.Server.MapPath("//") + NewFloderName);
                }
                return true;
            }
            catch
            {
                return false;
            }
        }

        public static void SaveStreamToFile(Stream FromStream, string TargetFile)
        {
            try
            {
                BinaryReader reader = new BinaryReader(FromStream);
                BinaryWriter writer1 = new BinaryWriter(File.Create(TargetFile));
                writer1.Write(reader.ReadBytes((int) FromStream.Length));
                writer1.Flush();
                writer1.Close();
                reader.Close();
            }
            catch
            {
            }
        }

        public static void WriteStrToTxtFile(string FilePath, string WriteStr, FileMode FileModes)
        {
            FileStream stream = new FileStream(FilePath, FileModes);
            StreamWriter writer1 = new StreamWriter(stream, Encoding.GetEncoding("utf-8"));
            writer1.WriteLine(WriteStr);
            writer1.Close();
            stream.Close();
        }
    }
}

