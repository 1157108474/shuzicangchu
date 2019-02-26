namespace EWMS.Utils
{
    using System;
    using System.Drawing;
    using System.Drawing.Imaging;
    using System.IO;
    using System.Web;

    public static class ImageUtil
    {
        public static bool ChangeImageSize(string OriFileName, string DesiredFilename, int IntWidth, int IntHeight, ImageFormat imageFormat)
        {
            WebPathTran(OriFileName);
            string path = WebPathTran(DesiredFilename);
            FileStream stream = null;
            try
            {
                Image.GetThumbnailImageAbort callback = new Image.GetThumbnailImageAbort(ImageUtil.imageAbort);
                stream = new FileStream(path, FileMode.Create, FileAccess.Write, FileShare.Write);
                Image.FromFile(OriFileName).GetThumbnailImage(IntWidth, IntHeight, callback, IntPtr.Zero).Save(stream, imageFormat);
                stream.Close();
                return true;
            }
            catch
            {
                stream.Close();
                return false;
            }
        }

        public static bool ConvertImage(string OriFilename, string DesiredFilename)
        {
            ImageFormat bmp;
            switch (DesiredFilename.Substring(DesiredFilename.LastIndexOf('.') + 1).ToLower())
            {
                case "bmp":
                    bmp = ImageFormat.Bmp;
                    break;

                case "gif":
                    bmp = ImageFormat.Gif;
                    break;

                case "jpeg":
                    bmp = ImageFormat.Jpeg;
                    break;

                case "ico":
                    bmp = ImageFormat.Icon;
                    break;

                case "png":
                    bmp = ImageFormat.Png;
                    break;

                default:
                    bmp = ImageFormat.Jpeg;
                    break;
            }
            try
            {
                Image.FromFile(WebPathTran(OriFilename)).Save(WebPathTran(DesiredFilename), bmp);
                return true;
            }
            catch
            {
                return false;
            }
        }

        private static bool imageAbort()
        {
            return false;
        }

        public static bool ImageWaterPic(string source, string target, string waterPicSource)
        {
            Image image = Image.FromFile(source);
            Graphics graphics = Graphics.FromImage(image);
            Image image2 = Image.FromFile(waterPicSource);
            try
            {
                graphics.DrawImage(image2, new Rectangle(image.Width - image2.Width, image.Height - image2.Height, image2.Width, image2.Height), 0, 0, image2.Width, image2.Height, GraphicsUnit.Pixel);
                image.Save(target);
            }
            catch (Exception)
            {
                throw;
            }
            finally
            {
                graphics.Dispose();
                image.Dispose();
                image2.Dispose();
            }
            return false;
        }

        public static bool ImageWaterText(string wtext, string source, string target)
        {
            bool flag = false;
            Image image = Image.FromFile(source);
            Graphics graphics = Graphics.FromImage(image);
            try
            {
                graphics.DrawImage(image, 0, 0, image.Width, image.Height);
                Font font = new Font("Verdana", 60f);
                Brush brush = new SolidBrush(Color.Green);
                graphics.DrawString(wtext, font, brush, (float) 35f, (float) 35f);
                image.Save(target);
                flag = true;
            }
            catch (Exception)
            {
                throw;
            }
            finally
            {
                graphics.Dispose();
                image.Dispose();
            }
            return flag;
        }

        private static string WebPathTran(string path)
        {
            try
            {
                return HttpContext.Current.Server.MapPath(path);
            }
            catch
            {
                return path;
            }
        }
    }
}

