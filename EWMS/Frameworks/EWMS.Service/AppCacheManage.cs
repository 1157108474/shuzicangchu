namespace EWMS.Service
{
    using EWMS.Utils;
    using System;

    public static class AppCacheManage
    {
        public static void Clear(string key)
        {
            CacheUtil.Clear(key);
        }

        public static void ClearAll()
        {
            CacheUtil.ClearAll();
        }
    }
}

