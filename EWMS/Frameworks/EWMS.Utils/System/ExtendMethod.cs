namespace System
{
    using System.Globalization;


    public static class ExtendMethod
    {
        public static T FromType<T, TK>(TK text)
        {
            try
            {
                return (T) Convert.ChangeType(text, typeof(T), CultureInfo.InvariantCulture);
            }
            catch
            {
                return default(T);
            }
        }

        public static bool IsInt(this string s)
        {
            int num;
            return int.TryParse(s, out num);
        }

        public static bool IsNullOrEmpty(this string s)
        {
            return string.IsNullOrEmpty(s);
        }

        public static int ToInt(this string s)
        {
            return int.Parse(s);
        }
    }
}

