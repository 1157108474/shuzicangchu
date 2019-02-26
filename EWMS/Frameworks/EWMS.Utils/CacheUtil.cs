namespace EWMS.Utils
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Runtime.Caching;

    using System.Runtime.InteropServices;

    public static class CacheUtil
    {
        private static readonly object _locker = new object();

        public static void Clear(string key)
        {
            MemoryCache.Default.Remove(key.ToString(), null);
        }

        public static void ClearAll()
        {
            MemoryCache.Default.ToList<KeyValuePair<string, object>>().ForEach(kv => MemoryCache.Default.Remove(kv.Key, null));
        }

        private static CacheItemPolicy CreatePolicy(TimeSpan? slidingExpiration, DateTime? absoluteExpiration)
        {
            CacheItemPolicy policy = new CacheItemPolicy();
            if (absoluteExpiration.HasValue)
            {
                policy.AbsoluteExpiration = absoluteExpiration.Value;
            }
            else if (slidingExpiration.HasValue)
            {
                policy.SlidingExpiration = slidingExpiration.Value;
            }
            policy.Priority = CacheItemPriority.Default;
            return policy;
        }

        public static T GetCacheItem<T>(string key, Func<T> cachePopulate, int ExpireMinutes = 30)
        {
            TimeSpan span = new TimeSpan(0, ExpireMinutes, 0);
            return GetCacheItem<T>(key, cachePopulate, new TimeSpan?(span), null);
        }

        public static object GetCacheItem(string key, object objectData, int ExpireMinutes = 30)
        {
            TimeSpan span = new TimeSpan(0, ExpireMinutes, 0);
            return GetCacheItem(key, objectData, new TimeSpan?(span), null);
        }

        private static T GetCacheItem<T>(string key, Func<T> cachePopulate, TimeSpan? slidingExpiration = new TimeSpan?(), DateTime? absoluteExpiration = new DateTime?())
        {
            if (string.IsNullOrWhiteSpace(key))
            {
                throw new ArgumentException("Invalid cache key");
            }
            if (cachePopulate == null)
            {
                throw new ArgumentNullException("cachePopulate");
            }
            if (!slidingExpiration.HasValue && !absoluteExpiration.HasValue)
            {
                throw new ArgumentException("Either a sliding expiration or absolute must be provided");
            }
            if (MemoryCache.Default[key] == null)
            {
                object obj2 = _locker;
                lock (obj2)
                {
                    if (MemoryCache.Default[key] == null)
                    {
                        CacheItem item = new CacheItem(key, cachePopulate());
                        CacheItemPolicy policy = CreatePolicy(slidingExpiration, absoluteExpiration);
                        MemoryCache.Default.Add(item, policy);
                    }
                }
            }
            return (T) MemoryCache.Default[key];
        }

        private static object GetCacheItem(string key, object objectData, TimeSpan? slidingExpiration = new TimeSpan?(), DateTime? absoluteExpiration = new DateTime?())
        {
            if (string.IsNullOrWhiteSpace(key))
            {
                throw new ArgumentException("Invalid cache key");
            }
            if (objectData == null)
            {
                throw new ArgumentNullException("objectData");
            }
            if (!slidingExpiration.HasValue && !absoluteExpiration.HasValue)
            {
                throw new ArgumentException("Either a sliding expiration or absolute must be provided");
            }
            if (MemoryCache.Default[key] == null)
            {
                object obj2 = _locker;
                lock (obj2)
                {
                    if (MemoryCache.Default[key] == null)
                    {
                        CacheItem item = new CacheItem(key, objectData);
                        CacheItemPolicy policy = CreatePolicy(slidingExpiration, absoluteExpiration);
                        MemoryCache.Default.Add(item, policy);
                    }
                }
            }
            return MemoryCache.Default[key];
        }
        
    }
}

