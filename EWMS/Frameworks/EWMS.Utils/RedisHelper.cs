namespace EWMS.Utils
{
    using ServiceStack.Redis;
    using ServiceStack.Redis.Generic;
    using ServiceStack.Text;
    using System;
    using System.Collections.Generic;
    using System.Configuration;

    public class RedisHelper
    {
        public static PooledRedisClientManager prcm;
        private static string RedisPath = ConfigurationSettings.AppSettings["RedisPath"];

        static RedisHelper()
        {
            string[] readWriteHosts = new string[] { RedisPath };
            string[] readOnlyHosts = new string[] { RedisPath };
            prcm = CreateManager(readWriteHosts, readOnlyHosts);
        }

        private static PooledRedisClientManager CreateManager(string[] readWriteHosts, string[] readOnlyHosts)
        {
            return new PooledRedisClientManager(readWriteHosts, readOnlyHosts, new RedisClientManagerConfig { 
                MaxWritePoolSize = 5,
                MaxReadPoolSize = 5,
                AutoStart = true
            });
        }

        public static bool Hash_Exist<T>(string key, string dataKey)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return client.HashContainsEntry(key, dataKey);
            }
        }

        public static T Hash_Get<T>(string key, string dataKey)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return JsonSerializer.DeserializeFromString<T>(client.GetValueFromHash(key, dataKey));
            }
        }

        public static List<T> Hash_GetAll<T>(string key)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                List<string> hashValues = client.GetHashValues(key);
                if ((hashValues != null) && (hashValues.Count > 0))
                {
                    List<T> list2 = new List<T>();
                    using (List<string>.Enumerator enumerator = hashValues.GetEnumerator())
                    {
                        while (enumerator.MoveNext())
                        {
                            T item = JsonSerializer.DeserializeFromString<T>(enumerator.Current);
                            list2.Add(item);
                        }
                    }
                    return list2;
                }
                return null;
            }
        }

        public static bool Hash_Remove(string key)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return client.Remove(key);
            }
        }

        public static bool Hash_Remove(string key, string dataKey)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return client.RemoveEntryFromHash(key, dataKey);
            }
        }

        public static bool Hash_Set<T>(string key, string dataKey, T t)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                string str = JsonSerializer.SerializeToString<T>(t);
                return client.SetEntryInHash(key, dataKey, str);
            }
        }

        public static void Hash_SetExpire(string key, DateTime datetime)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                client.ExpireEntryAt(key, datetime);
            }
        }

        public static void List_Add<T>(string key, T t)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                IRedisTypedClient<T> typedClient = client.GetTypedClient<T>();
                typedClient.AddItemToList(typedClient.Lists[key], t);
            }
        }

        public static void List_AddJson<T>(string key, T t)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                string str = JsonSerializer.SerializeToString<T>(t);
                client.AddItemToList(key, str);
            }
        }

        public static int List_Count(string key)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return client.GetListCount(key);
            }
        }

        public static List<T> List_GetList<T>(string key)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                IRedisTypedClient<T> typedClient = client.GetTypedClient<T>();
                return typedClient.Lists[key].GetRange(0, typedClient.Lists[key].Count);
            }
        }

        public static List<T> List_GetList<T>(string key, int pageIndex, int pageSize)
        {
            int start = pageSize * (pageIndex - 1);
            return List_GetRange<T>(key, start, pageSize);
        }

        public static List<T> List_GetRange<T>(string key, int start, int count)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return client.GetTypedClient<T>().Lists[key].GetRange(start, (start + count) - 1);
            }
        }

        public static bool List_Remove<T>(string key, T t)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                IRedisTypedClient<T> typedClient = client.GetTypedClient<T>();
                return (typedClient.RemoveItemFromList(typedClient.Lists[key], t) > 0);
            }
        }

        public static void List_RemoveAll<T>(string key)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                client.GetTypedClient<T>().Lists[key].RemoveAll();
            }
        }

        public static void List_SetExpire(string key, DateTime datetime)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                client.ExpireEntryAt(key, datetime);
            }
        }

        public static void Set_Add<T>(string key, T t)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                client.GetTypedClient<T>().Sets[key].Add(t);
            }
        }

        public static bool Set_Contains<T>(string key, T t)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return client.GetTypedClient<T>().Sets[key].Contains(t);
            }
        }

        public static bool Set_Remove<T>(string key, T t)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return client.GetTypedClient<T>().Sets[key].Remove(t);
            }
        }

        public static T Single_Get_Itme<T>(string key) where T: class
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return client.Get<T>(key);
            }
        }

        public static bool Single_Remove_Itme(string key)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return client.Remove(key);
            }
        }

        public static bool Single_Set_Itme<T>(string key, T t)
        {
            try
            {
                using (IRedisClient client = prcm.GetClient())
                {
                    return client.Set<T>(key, t, new TimeSpan(1, 0, 0));
                }
            }
            catch
            {
            }
            return false;
        }

        public static bool SortedSet_Add<T>(string key, T t, double score)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                string str = JsonSerializer.SerializeToString<T>(t);
                return client.AddItemToSortedSet(key, str, score);
            }
        }

        public static int SortedSet_Count(string key)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return client.GetSortedSetCount(key);
            }
        }

        public static List<T> SortedSet_GetList<T>(string key, int pageIndex, int pageSize)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                List<string> list = client.GetRangeFromSortedSet(key, (pageIndex - 1) * pageSize, (pageIndex * pageSize) - 1);
                if ((list != null) && (list.Count > 0))
                {
                    List<T> list2 = new List<T>();
                    using (List<string>.Enumerator enumerator = list.GetEnumerator())
                    {
                        while (enumerator.MoveNext())
                        {
                            T item = JsonSerializer.DeserializeFromString<T>(enumerator.Current);
                            list2.Add(item);
                        }
                    }
                    return list2;
                }
            }
            return null;
        }

        public static List<T> SortedSet_GetListALL<T>(string key)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                List<string> list = client.GetRangeFromSortedSet(key, 0, 0x98967f);
                if ((list != null) && (list.Count > 0))
                {
                    List<T> list2 = new List<T>();
                    using (List<string>.Enumerator enumerator = list.GetEnumerator())
                    {
                        while (enumerator.MoveNext())
                        {
                            T item = JsonSerializer.DeserializeFromString<T>(enumerator.Current);
                            list2.Add(item);
                        }
                    }
                    return list2;
                }
            }
            return null;
        }

        public static List<T> SortedSet_GetListSearchKeys<T>(string key)
        {
            using (IRedisClient redis = prcm.GetClient())
            {
                List<T> result = new List<T>();
                redis.GetAllKeys().ForEach(delegate (string k) {
                    if (k.Contains(key))
                    {
                        T item = redis.Get<T>(k);
                        result.Add(item);
                    }
                });
                return result;
            }
        }

        public static bool SortedSet_Remove<T>(string key, T t)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                string str = JsonSerializer.SerializeToString<T>(t);
                return client.RemoveItemFromSortedSet(key, str);
            }
        }

        public static void SortedSet_SetExpire(string key, DateTime datetime)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                client.ExpireEntryAt(key, datetime);
            }
        }

        public static int SortedSet_Trim(string key, int size)
        {
            using (IRedisClient client = prcm.GetClient())
            {
                return client.RemoveRangeFromSortedSet(key, size, 0x98967f);
            }
        }
    }
}

