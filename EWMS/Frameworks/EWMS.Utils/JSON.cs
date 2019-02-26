namespace EWMS.Utils
{
    using Newtonsoft.Json;
    using Newtonsoft.Json.Converters;
    using Newtonsoft.Json.Linq;
    using System;
    using System.Collections;
    using System.Collections.Generic;

    public class JSON
    {
        public static string DateTimeFormat = "yyyy'-'MM'-'dd'T'HH':'mm':'ss";

        public static object Decode(string json)
        {
            if (string.IsNullOrEmpty(json))
            {
                return "";
            }
            object o = JsonConvert.DeserializeObject(json);
            if ((o.GetType() == typeof(string)) || (o.GetType() == typeof(string)))
            {
                o = JsonConvert.DeserializeObject(o.ToString());
            }
            return toObject(o);
        }

        public static object Decode(string json, Type type)
        {
            return JsonConvert.DeserializeObject(json, type);
        }

        public static string Encode(object o)
        {
            if ((o == null) || (o.ToString() == "null"))
            {
                return null;
            }
            if ((o != null) && ((o.GetType() == typeof(string)) || (o.GetType() == typeof(string))))
            {
                return o.ToString();
            }
            IsoDateTimeConverter converter = new IsoDateTimeConverter {
                DateTimeFormat = DateTimeFormat
            };
            JsonConverter[] converters = new JsonConverter[] { converter };
            return JsonConvert.SerializeObject(o, converters);
        }

        private static object toObject(object o)
        {
            if (o == null)
            {
                return null;
            }
            if (o.GetType() == typeof(string))
            {
                string str = o.ToString();
                if (((str.Length == 0x13) && (str[10] == 'T')) && ((str[4] == '-') && (str[13] == ':')))
                {
                    o = Convert.ToDateTime(o);
                }
                return o;
            }
            if (o is JObject)
            {
                Hashtable hashtable = new Hashtable();
                foreach (KeyValuePair<string, JToken> pair in o as JObject)
                {
                    hashtable[pair.Key] = toObject(pair.Value);
                }
                o = hashtable;
                return o;
            }
            if (o is IList)
            {
                ArrayList list = new ArrayList();
                list.AddRange(o as IList);
                int num = 0;
                int count = list.Count;
                while (num < count)
                {
                    list[num] = toObject(list[num]);
                    num++;
                }
                o = list;
                return o;
            }
            if (typeof(JValue) == o.GetType())
            {
                o = toObject(((JValue) o).Value);
            }
            return o;
        }
    }
}

