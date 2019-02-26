namespace EWMS.Utils
{
    using System;
    using System.Drawing;
    using System.Globalization;
    using System.Text;
    using System.Text.RegularExpressions;

    public class StringUtil
    {
        private static Regex RegexBr = new Regex(@"(\r\n)", RegexOptions.IgnoreCase);

        public static string ClearBR(string str)
        {
            Match match = null;
            for (match = RegexBr.Match(str); match.Success; match = match.NextMatch())
            {
                str = str.Replace(match.Groups[0].ToString(), "");
            }
            return str;
        }

        public static string FormatBytesStr(int bytes)
        {
            double num;
            if (bytes > 0x40000000)
            {
                num = bytes / 0x40000000;
                return (num.ToString("0") + "G");
            }
            if (bytes > 0x100000)
            {
                num = bytes / 0x100000;
                return (num.ToString("0") + "M");
            }
            if (bytes > 0x400)
            {
                num = bytes / 0x400;
                return (num.ToString("0") + "K");
            }
            return (bytes.ToString() + "Bytes");
        }

        public static int GetInArrayID(string strSearch, string[] stringArray, bool caseInsensetive)
        {
            for (int i = 0; i < stringArray.Length; i++)
            {
                if (caseInsensetive)
                {
                    if (strSearch.ToLower() == stringArray[i].ToLower())
                    {
                        return i;
                    }
                }
                else if (strSearch == stringArray[i])
                {
                    return i;
                }
            }
            return -1;
        }

        public static int GetStringLength(string str)
        {
            return Encoding.Default.GetBytes(str).Length;
        }

        public static string GetSubString(string p_SrcString, int p_Length, string p_TailString)
        {
            return GetSubString(p_SrcString, 0, p_Length, p_TailString);
        }

        public static string GetSubString(string p_SrcString, int p_StartIndex, int p_Length, string p_TailString)
        {
            string str = p_SrcString;
            if (Regex.IsMatch(p_SrcString, "[ࠀ-一]+") || Regex.IsMatch(p_SrcString, "[가-힣]+"))
            {
                if (p_StartIndex >= p_SrcString.Length)
                {
                    return "";
                }
                return p_SrcString.Substring(p_StartIndex, ((p_Length + p_StartIndex) > p_SrcString.Length) ? (p_SrcString.Length - p_StartIndex) : p_Length);
            }
            if (p_Length < 0)
            {
                return str;
            }
            byte[] bytes = Encoding.Default.GetBytes(p_SrcString);
            if (bytes.Length <= p_StartIndex)
            {
                return str;
            }
            int length = bytes.Length;
            if (bytes.Length > (p_StartIndex + p_Length))
            {
                length = p_Length + p_StartIndex;
            }
            else
            {
                p_Length = bytes.Length - p_StartIndex;
                p_TailString = "";
            }
            int num2 = p_Length;
            int[] numArray = new int[p_Length];
            byte[] destinationArray = null;
            int num3 = 0;
            for (int i = p_StartIndex; i < length; i++)
            {
                if (bytes[i] > 0x7f)
                {
                    num3++;
                    if (num3 == 3)
                    {
                        num3 = 1;
                    }
                }
                else
                {
                    num3 = 0;
                }
                numArray[i] = num3;
            }
            if ((bytes[length - 1] > 0x7f) && (numArray[p_Length - 1] == 1))
            {
                num2 = p_Length + 1;
            }
            destinationArray = new byte[num2];
            Array.Copy(bytes, p_StartIndex, destinationArray, 0, num2);
            return (Encoding.Default.GetString(destinationArray) + p_TailString);
        }

        public static string ReplaceString(string SourceString, string SearchString, string ReplaceString, bool IsCaseInsensetive)
        {
            return Regex.Replace(SourceString, Regex.Escape(SearchString), ReplaceString, IsCaseInsensetive ? RegexOptions.IgnoreCase : RegexOptions.None);
        }

        public static string RTrim(string str)
        {
            for (int i = str.Length; i >= 0; i--)
            {
                char ch = str[i];
                if (!ch.Equals(" "))
                {
                    ch = str[i];
                    if (!ch.Equals("\r"))
                    {
                        ch = str[i];
                        if (!ch.Equals("\n"))
                        {
                            continue;
                        }
                    }
                }
                str.Remove(i, 1);
            }
            return str;
        }

        public static string[] SplitString(string strContent, string strSplit)
        {
            if (strContent.IndexOf(strSplit) < 0)
            {
                return new string[] { strContent };
            }
            return Regex.Split(strContent, Regex.Escape(strSplit), RegexOptions.IgnoreCase);
        }

        public static string[] SplitString(string strContent, string strSplit, int p_3)
        {
            string[] strArray = new string[p_3];
            string[] strArray2 = SplitString(strContent, strSplit);
            for (int i = 0; i < p_3; i++)
            {
                if (i < strArray2.Length)
                {
                    strArray[i] = strArray2[i];
                }
                else
                {
                    strArray[i] = string.Empty;
                }
            }
            return strArray;
        }

        public static Color ToColor(string color)
        {
            int num;
            char[] chArray;
            int blue = 0;
            char[] trimChars = new char[] { '#' };
            color = color.TrimStart(trimChars);
            color = Regex.Replace(color.ToLower(), "[g-zG-Z]", "");
            switch (color.Length)
            {
                case 3:
                    chArray = color.ToCharArray();
                    num = Convert.ToInt32(chArray[1].ToString() + chArray[1].ToString(), 0x10);
                    blue = Convert.ToInt32(chArray[2].ToString() + chArray[2].ToString(), 0x10);
                    return Color.FromArgb(Convert.ToInt32(chArray[0].ToString() + chArray[0].ToString(), 0x10), num, blue);

                case 6:
                    chArray = color.ToCharArray();
                    num = Convert.ToInt32(chArray[2].ToString() + chArray[3].ToString(), 0x10);
                    blue = Convert.ToInt32(chArray[4].ToString() + chArray[5].ToString(), 0x10);
                    return Color.FromArgb(Convert.ToInt32(chArray[0].ToString() + chArray[1].ToString(), 0x10), num, blue);
            }
            return Color.FromName(color);
        }

        public static string ToTitleCase(string str)
        {
            return CultureInfo.CurrentCulture.TextInfo.ToTitleCase(str);
        }
    }
}

