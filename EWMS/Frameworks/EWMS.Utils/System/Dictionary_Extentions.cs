namespace System
{
    using System.Collections.Generic;


    public static class Dictionary_Extentions
    {
        public static void DictionaryAdd<K, V>(this Dictionary<K, V> dict, K id, V val)
        {
            if (val != null)
            {
                Dictionary<K, V> dictionary = dict;
                lock (dictionary)
                {
                    if (!dict.ContainsKey(id))
                    {
                        dict.Add(id, val);
                    }
                }
            }
        }

        public static void DictionaryExchange<K, V>(this Dictionary<K, V> dict, K id, V val)
        {
            if (val != null)
            {
                Dictionary<K, V> dictionary = dict;
                lock (dictionary)
                {
                    if (dict.ContainsKey(id))
                    {
                        dict.Remove(id);
                    }
                    dict.Add(id, val);
                }
            }
        }

        public static T Value<T>(this Dictionary<string, string> dict, string key)
        {
            if (dict == null)
            {
                throw new Exception("Dictionary is NULL.");
            }
            string str = string.Empty;
            if (!dict.TryGetValue(key, out str))
            {
                return default(T);
            }
            return ExtendMethod.FromType<T, string>(str);
        }

        public static V Value<K, V>(this Dictionary<K, V> dict, K key)
        {
            Dictionary<K, V> dictionary = dict;
            lock (dictionary)
            {
                if (dict.ContainsKey(key))
                {
                    return dict[key];
                }
                return default(V);
            }
        }
    }
}

