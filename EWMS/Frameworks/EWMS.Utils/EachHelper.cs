namespace EWMS.Utils
{
    
    using System;
    using System.Collections;
    using System.Collections.Generic;


    public class EachHelper
    {
        public static void EachListHeader(object list, Action<int, string, Type> handle)
        {
            int num = 0;
            foreach (KeyValuePair<string, Type> pair in GenericUtil.GetListProperties(list))
            {
                handle(num++, pair.Key, pair.Value);
            }
        }

        public static void EachListRow(object list, Action<int, object> handle)
        {
            int num = 0;
            IEnumerator enumerator = list as IEnumerator;
            while (enumerator.MoveNext())
            {
                handle(num++, enumerator.Current);
            }
        }

        public static void EachObjectProperty(object row, Action<int, string, object> handle)
        {
            int num = 0;
            foreach (KeyValuePair<string, object> pair in GenericUtil.GetDictionaryValues(row))
            {
                handle(num++, pair.Key, pair.Value);
            }
        }
        
    }
}

