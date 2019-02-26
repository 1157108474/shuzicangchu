namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using EWMS.Utils;
    using System;
    using System.Collections.Generic;
    using System.Data;
    using System.Linq;
    

    public class Base_DictionaryService : BaseService<Base_Dictionary>
    {
        private static readonly Base_DictionaryService _Instance = new Base_DictionaryService();

        public List<Base_Dictionary> GetAllDic()
        {
            if (cacheHelper.Base_Page.IsExistsCache("dataDicList"))
            {
                return (List<Base_Dictionary>) cacheHelper.Base_Page.GetCache("dataDicList");
            }
            string sql = "select * from Base_Dictionary ";
            List<Base_Dictionary> objObject = Instance.GetList(sql);
            cacheHelper.Base_Page.SetCache("dataDicList", objObject, DateTime.MaxValue, TimeSpan.FromMinutes(20.0));
            return objObject;
        }

        private List<dictionary_treedata> GetTreeData_Children(List<Base_Dictionary> listDict, List<Base_Dictionary> listDict_Children)
        {
            List<dictionary_treedata> first = new List<dictionary_treedata>();
            using (List<Base_Dictionary>.Enumerator enumerator = listDict_Children.GetEnumerator())
            {
                while (enumerator.MoveNext())
                {
                    Base_Dictionary item = enumerator.Current;
                    dictionary_treedata _treedata = new dictionary_treedata {
                        id = item.ID,
                        text = item.Name,
                        pid = item.ParentID,
                        code = item.Code,
                        sort = item.Sort,
                        memo = item.Memo,
                        isuse = item.Status
                    };
                    first.Add(_treedata);
                    List<dictionary_treedata> second = new List<dictionary_treedata>();
                    List<Base_Dictionary> list3 = listDict.FindAll(p => p.ParentID == item.ID);
                    if (list3.Count > 0)
                    {
                        _treedata.isLeaf = false;
                        _treedata.expanded = false;
                        second = this.GetTreeData_Children(listDict, list3);
                        first = first.Concat<dictionary_treedata>(second).ToList<dictionary_treedata>();
                    }
                    else
                    {
                        _treedata.isLeaf = true;
                        _treedata.expanded = true;
                    }
                }
            }
            return first;
        }

        public List<dictionary_treedata> GetTreeData_SparepartsCate()
        {
            List<Base_Dictionary> listDict = Instance.GetList_Fish("and 1=1 order by Sort ASC", true);
            List<dictionary_treedata> first = new List<dictionary_treedata>();
            dictionary_treedata item = new dictionary_treedata {
                id = 0,
                text = "数据字典分类",
                pid = -1,
                isLeaf = false,
                expanded = true
            };
            first.Add(item);
            List<dictionary_treedata> second = new List<dictionary_treedata>();
            List<Base_Dictionary> list4 = listDict.FindAll(p => p.ParentID == 0);
            if (list4.Count > 0)
            {
                second = this.GetTreeData_Children(listDict, list4);
                first = first.Concat<dictionary_treedata>(second).ToList<dictionary_treedata>();
            }
            return first;
        }

        public List<dictionary_treedata> GetTreeData_SparepartsCate(string where)
        {
            List<Base_Dictionary> source = Instance.GetList_Fish(where, true);
            List<dictionary_treedata> first = new List<dictionary_treedata>();
            List<dictionary_treedata> second = new List<dictionary_treedata>();
            Base_Dictionary dictionary = source.ElementAt<Base_Dictionary>(0);
            dictionary_treedata item = new dictionary_treedata {
                id = dictionary.ID,
                text = dictionary.Name,
                pid = dictionary.ParentID,
                code = dictionary.Code,
                sort = dictionary.Sort,
                memo = dictionary.Memo,
                isuse = dictionary.Status,
                expanded = true,
                isLeaf = false
            };
            first.Add(item);
            int iD = source.ElementAt<Base_Dictionary>(0).ID;
            string sqlOrCondition = " and 1=1 and parentid=" + iD + " order by sort";
            List<Base_Dictionary> list4 = Instance.GetList_Fish(sqlOrCondition, true);
            if (list4.Count > 0)
            {
                second = this.GetTreeData_Children(source, list4);
                first = first.Concat<dictionary_treedata>(second).ToList<dictionary_treedata>();
            }
            return first;
        }

        public int Insertdata(dictionary_treedata data, int creator)
        {
            int pid = data.pid;
            string code = data.code;
            string text = data.text;
            int sort = data.sort;
            int isuse = data.isuse;
            string memo = data.memo;
            string str4 = Guid.NewGuid().ToString();
            string str5 = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            object[] objArray1 = new object[] { 
                "INSERT into Base_Dictionary (Code,Name,ParentID,Status,Sort,Memo,guid,creator,createdate)\r\n\t\t\t\tVALUES('", code, "','", text, "',", pid, ",", isuse, ",", sort, ",'", memo, "','", str4, "',", creator,
                ",to_date('", str5, "','yyyy-mm-dd hh24:mi:ss'))"
            };
            string sql = string.Concat(objArray1);
            try
            {
                return base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -2;
            }
        }

        public DataTable Pidselect(int pid)
        {
            string sql = "SELECT * FROM Base_Dictionary WHERE ID=" + pid;
            return base.GetDataTable_Fish(sql);
        }

        public int Updatedata(dictionary_treedata data, int updater)
        {
            string code = data.code;
            string text = data.text;
            int sort = data.sort;
            int isuse = data.isuse;
            string memo = data.memo;
            int id = data.id;
            string str4 = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            object[] objArray1 = new object[] { "UPDATE Base_Dictionary SET Code='", code, "',Name='", text, "',Sort=", sort, ",Status=", isuse, ",Memo='", memo, "',updator=", updater, ",updatedate=to_date('", str4, "','yyyy-mm-dd hh24:mi:ss') WHERE ID=", id };
            string sql = string.Concat(objArray1);
            try
            {
                return base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -2;
            }
        }

        public static Base_DictionaryService Instance
        {
            get
            {
                return _Instance;
            }
        }
        
    }
}

