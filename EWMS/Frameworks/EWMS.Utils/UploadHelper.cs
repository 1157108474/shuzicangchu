namespace EWMS.Utils
{
    using System;
    using System.Drawing;
    using System.Drawing.Drawing2D;
    using System.Drawing.Imaging;
    using System.IO;
    using System.Linq;
    using System.Text;

    public class UploadHelper
    {
        private static string CreateDirectory(string RootPath, DateTime DateTimeNow, bool IsThumbnail)
        {
            StringBuilder builder = new StringBuilder();
            builder.Append(RootPath);
            builder.Append(Path.DirectorySeparatorChar);
            builder.Append(DateTime.Now.Year.ToString().PadLeft(4, '0'));
            builder.Append("-");
            builder.Append(DateTime.Now.Month.ToString().PadLeft(2, '0'));
            builder.Append("-");
            builder.Append(DateTime.Now.Day.ToString().PadLeft(2, '0'));
            if (IsThumbnail)
            {
                builder.Append(Path.DirectorySeparatorChar);
                builder.Append("Thumbnails");
            }
            string path = builder.ToString();
            if (!Directory.Exists(path))
            {
                Directory.CreateDirectory(path);
            }
            return path;
        }

        private static string GenerateFileName(DateTime DateTimeNow, bool IsThumbnail, string SourceShortFileName)
        {
            StringBuilder builder = new StringBuilder();
            builder.Append(DateTime.Now.Year.ToString().PadLeft(4, '0'));
            builder.Append("-");
            builder.Append(DateTime.Now.Month.ToString().PadLeft(2, '0'));
            builder.Append("-");
            builder.Append(DateTime.Now.Day.ToString().PadLeft(2, '0'));
            if (IsThumbnail)
            {
                builder.Append(Path.DirectorySeparatorChar);
                builder.Append("Thumbnails");
            }
            builder.Append(Path.DirectorySeparatorChar);
            builder.Append(DateTimeNow.Hour.ToString().PadLeft(2, '0'));
            builder.Append(DateTimeNow.Minute.ToString().PadLeft(2, '0'));
            builder.Append(DateTimeNow.Second.ToString().PadLeft(2, '0'));
            builder.Append(SourceShortFileName.Remove(SourceShortFileName.LastIndexOf(".") + 1));
            builder.Append("jpeg");
            return builder.ToString();
        }

        public static byte[] GetPicThumbnail(Stream SourceStream, int flag)
        {
            byte[] buffer;
            Image image = Image.FromStream(PictureScale(SourceStream));
            ImageFormat rawFormat = image.RawFormat;
            MemoryStream stream = new MemoryStream();
            EncoderParameters encoderParams = new EncoderParameters();
            long[] numArray = new long[] { (long) flag };
            EncoderParameter parameter = new EncoderParameter(System.Drawing.Imaging.Encoder.Quality, numArray);
            encoderParams.Param[0] = parameter;
            try
            {
                ImageCodecInfo[] imageEncoders = ImageCodecInfo.GetImageEncoders();
                ImageCodecInfo encoder = null;
                for (int i = 0; i < imageEncoders.Length; i++)
                {
                    if (imageEncoders[i].FormatDescription.Equals("JPEG"))
                    {
                        encoder = imageEncoders[i];
                        break;
                    }
                }
                if (encoder != null)
                {
                    image.Save(stream, encoder, encoderParams);
                }
                else
                {
                    image.Save(stream, rawFormat);
                }
                buffer = stream.ToArray();
            }
            catch
            {
                buffer = new byte[1];
            }
            finally
            {
                image.Dispose();
                stream.Dispose();
            }
            return buffer;
        }

        public static bool GetPicThumbnail(string sFile, string dFile, int dHeight, int dWidth, int flag)
        {
            bool flag2;
            Image image = Image.FromFile(sFile);
            ImageFormat rawFormat = image.RawFormat;
            int width = 0;
            int height = 0;
            Size size = new Size(image.Width, image.Height);
            if ((size.Width > dHeight) || (size.Width > dWidth))
            {
                if ((size.Width * dHeight) > (size.Height * dWidth))
                {
                    width = dWidth;
                    height = (dWidth * size.Height) / size.Width;
                }
                else
                {
                    height = dHeight;
                    width = (size.Width * dHeight) / size.Height;
                }
            }
            else
            {
                width = size.Width;
                height = size.Height;
            }
            Bitmap bitmap = new Bitmap(dWidth, dHeight);
            Graphics graphics1 = Graphics.FromImage(bitmap);
            graphics1.Clear(Color.WhiteSmoke);
            graphics1.CompositingQuality = CompositingQuality.HighQuality;
            graphics1.SmoothingMode = SmoothingMode.HighQuality;
            graphics1.InterpolationMode = InterpolationMode.HighQualityBicubic;
            graphics1.DrawImage(image, new Rectangle((dWidth - width) / 2, (dHeight - height) / 2, width, height), 0, 0, image.Width, image.Height, GraphicsUnit.Pixel);
            graphics1.Dispose();
            EncoderParameters encoderParams = new EncoderParameters();
            long[] numArray = new long[] { (long) flag };
            EncoderParameter parameter = new EncoderParameter(System.Drawing.Imaging.Encoder.Quality, numArray);
            encoderParams.Param[0] = parameter;
            try
            {
                ImageCodecInfo[] imageEncoders = ImageCodecInfo.GetImageEncoders();
                ImageCodecInfo encoder = null;
                for (int i = 0; i < imageEncoders.Length; i++)
                {
                    if (imageEncoders[i].FormatDescription.Equals("JPEG"))
                    {
                        encoder = imageEncoders[i];
                        break;
                    }
                }
                if (encoder != null)
                {
                    bitmap.Save(dFile, encoder, encoderParams);
                }
                else
                {
                    bitmap.Save(dFile, rawFormat);
                }
                flag2 = true;
            }
            catch
            {
                flag2 = false;
            }
            finally
            {
                image.Dispose();
                bitmap.Dispose();
            }
            return flag2;
        }

        public static Stream PictureScale(Stream SourceStream)
        {
            Image image = Image.FromStream(SourceStream);
            MemoryStream stream = new MemoryStream();
            int width = image.Width;
            int sourceHeight = 150;
            Rectangle rectangle = PictureScaleSize(width, sourceHeight, 0x400);
            Bitmap bitmap = new Bitmap(rectangle.Width, 150);
            Graphics graphics1 = Graphics.FromImage(bitmap);
            graphics1.SmoothingMode = SmoothingMode.HighQuality;
            graphics1.CompositingQuality = CompositingQuality.HighQuality;
            graphics1.InterpolationMode = InterpolationMode.High;
            Rectangle destRect = new Rectangle(0, 0, rectangle.Width, rectangle.Height);
            graphics1.DrawImage(image, destRect, 0, 0, width, sourceHeight, GraphicsUnit.Pixel);
            bitmap.Save(stream, ImageFormat.Jpeg);
            SourceStream.Dispose();
            image.Dispose();
            bitmap.Dispose();
            graphics1.Dispose();
            return stream;
        }

        public static Rectangle PictureScaleSize(int SourceWidth, int SourceHeight, int DestWidth)
        {
            if (SourceWidth <= 0x400)
            {
                return new Rectangle(0, 0, SourceWidth, SourceHeight);
            }
            return new Rectangle { 
                Width = DestWidth,
                Height = Convert.ToInt32((double) (SourceHeight * (((double) DestWidth) / ((double) SourceWidth))))
            };
        }

        public static byte[] StreamToBytes(Stream stream)
        {
            byte[] buffer = new byte[stream.Length];
            stream.Read(buffer, 0, Convert.ToInt32(stream.Length));
            return buffer;
        }

        public static bool ValidateUploadFormats(string ExtensionFileName)
        {
            string[] separator = new string[] { "," };
            return "bmp,jpg,gif,jpeg,png".Split(separator, StringSplitOptions.RemoveEmptyEntries).Contains<string>(ExtensionFileName.ToLower());
        }
    }
}

