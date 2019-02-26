namespace EWMS.Utils
{
    using System;
    using System.Collections;
    using System.Web;
    using System.Web.Caching;

    public class cacheHelper
    {
        public class Base_Page
        {
            public static object GetCache(string CacheKey)
            {
                return HttpRuntime.Cache[CacheKey];
            }

            public static bool IsExistsCache(string CacheKey)
            {
                if (HttpRuntime.Cache[CacheKey] == null)
                {
                    return false;
                }
                return true;
            }

            public static void RemoveAllCache()
            {
                Cache cache = HttpRuntime.Cache;
                IDictionaryEnumerator enumerator = cache.GetEnumerator();
                while (enumerator.MoveNext())
                {
                    cache.Remove(enumerator.Key.ToString());
                }
            }

            public static void RemoveAllCache(string CacheKey)
            {
                HttpRuntime.Cache.Remove(CacheKey);
            }

            public static void SetCache(string CacheKey, object objObject)
            {
                HttpRuntime.Cache.Insert(CacheKey, objObject);
            }

            public static void SetCache(string CacheKey, object objObject, TimeSpan Timeout)
            {
                HttpRuntime.Cache.Insert(CacheKey, objObject, null, DateTime.MaxValue, Timeout, CacheItemPriority.NotRemovable, null);
            }

            public static void SetCache(string CacheKey, object objObject, DateTime absoluteExpiration, TimeSpan slidingExpiration)
            {
                HttpRuntime.Cache.Insert(CacheKey, objObject, null, absoluteExpiration, slidingExpiration);
            }
        }
    }
}

