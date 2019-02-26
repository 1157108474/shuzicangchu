namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    using System.Collections.Generic;
    using System.Linq;
    

    public class Base_SparepartsCateService : BaseService<Base_SparepartsCate>
    {
        private static readonly Base_SparepartsCateService _Instance = new Base_SparepartsCateService();

        private List<sparepartscate_treedata> GetTreeData_Children(List<Base_SparepartsCate> listDict, List<Base_SparepartsCate> listDict_Children)
        {
            List<sparepartscate_treedata> first = new List<sparepartscate_treedata>();
            using (List<Base_SparepartsCate>.Enumerator enumerator = listDict_Children.GetEnumerator())
            {
                while (enumerator.MoveNext())
                {
                    Base_SparepartsCate item = enumerator.Current;
                    sparepartscate_treedata _treedata = new sparepartscate_treedata {
                        id = item.ID,
                        text = item.Name,
                        pid = item.ParentID,
                        code = item.Code,
                        sort = item.Sort,
                        memo = item.Memo,
                        isuse = item.Status
                    };
                    first.Add(_treedata);
                    List<sparepartscate_treedata> second = new List<sparepartscate_treedata>();
                    List<Base_SparepartsCate> list3 = listDict.FindAll(p => p.ParentID == item.ID);
                    if (list3.Count > 0)
                    {
                        _treedata.isLeaf = false;
                        _treedata.expanded = false;
                        second = this.GetTreeData_Children(listDict, list3);
                        first = first.Concat<sparepartscate_treedata>(second).ToList<sparepartscate_treedata>();
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

        public List<sparepartscate_treedata> GetTreeData_SparepartsCate()
        {
            List<Base_SparepartsCate> listDict = Instance.GetList_Fish("and 1=1 order by Sort ASC", true);
            List<sparepartscate_treedata> first = new List<sparepartscate_treedata>();
            sparepartscate_treedata item = new sparepartscate_treedata {
                id = 0,
                text = "物料分类",
                pid = -1,
                isLeaf = false,
                expanded = true
            };
            first.Add(item);
            List<sparepartscate_treedata> second = new List<sparepartscate_treedata>();
            List<Base_SparepartsCate> list4 = listDict.FindAll(p => p.ParentID == 0);
            if (list4.Count > 0)
            {
                second = this.GetTreeData_Children(listDict, list4);
                first = first.Concat<sparepartscate_treedata>(second).ToList<sparepartscate_treedata>();
            }
            return first;
        }

        public List<sparepartscate_treedata> GetTreeData_SparepartsCate(int id)
        {
            string sqlOrCondition = " and ParentID=" + id + " and 1=1 order by Sort ASC";
            List<sparepartscate_treedata> list = new List<sparepartscate_treedata>();
            if (id == 0)
            {
                sparepartscate_treedata item = new sparepartscate_treedata {
                    id = 0,
                    text = "物料分类",
                    pid = -1,
                    isLeaf = false,
                    expanded = true
                };
                list.Add(item);
            }
            foreach (Base_SparepartsCate cate in Instance.GetList_Fish(sqlOrCondition, true))
            {
                sparepartscate_treedata _treedata2 = new sparepartscate_treedata {
                    id = cate.ID,
                    text = cate.Name,
                    pid = cate.ParentID,
                    code = cate.Code,
                    sort = cate.Sort,
                    memo = cate.Memo,
                    isuse = cate.Status,
                    expanded = false
                };
                string sql = "SELECT * FROM Base_SparepartsCate where ParentID=" + cate.ID;
                if (Instance.GetDataTable_Fish(sql).Rows.Count > 0)
                {
                    _treedata2.isLeaf = false;
                }
                else
                {
                    _treedata2.isLeaf = true;
                }
                list.Add(_treedata2);
            }
            return list;
        }

        public List<sparepartscate_treedata> GetTreeData_SparepartsCate(string where, string type, string name)
        {
            List<sparepartscate_treedata> list = new List<sparepartscate_treedata>();
            sparepartscate_treedata item = new sparepartscate_treedata {
                id = 0,
                text = "物料分类",
                pid = -1,
                isLeaf = false,
                expanded = true
            };
            list.Add(item);
            foreach (Base_SparepartsCate cate in Instance.GetList_Fish(where, true))
            {
                sparepartscate_treedata _treedata2 = new sparepartscate_treedata {
                    id = cate.ID,
                    text = cate.Name,
                    pid = cate.ParentID,
                    code = cate.Code,
                    sort = cate.Sort,
                    memo = cate.Memo,
                    isuse = cate.Status,
                    isLeaf = true,
                    expanded = false
                };
                list.Add(_treedata2);
            }
            string sqlOrCondition = "";
            if (type.ToLower().Contains("name"))
            {
                sqlOrCondition = " and id in (SELECT distinct ParentID FROM Base_SparepartsCate WHERE name LIKE '%" + name + "%')";
            }
            if (type.ToLower().Contains("code"))
            {
                sqlOrCondition = " and id in (SELECT distinct ParentID FROM Base_SparepartsCate WHERE code LIKE '%" + name + "%')";
            }
            foreach (Base_SparepartsCate cate2 in Instance.GetList_Fish(sqlOrCondition, true))
            {
                sparepartscate_treedata _treedata3 = new sparepartscate_treedata {
                    id = cate2.ID,
                    text = cate2.Name,
                    pid = cate2.ParentID,
                    code = cate2.Code,
                    sort = cate2.Sort,
                    memo = cate2.Memo,
                    isuse = cate2.Status,
                    expanded = false
                };
                list.Add(_treedata3);
            }
            return list;
        }

        public int Insertdata(sparepartscate_treedata data, int creator)
        {
            string code = data.code;
            int id = data.id;
            string text = data.text;
            int sort = data.sort;
            int isuse = data.isuse;
            string memo = data.memo;
            int pid = data.pid;
            string str4 = Guid.NewGuid().ToString();
            string str5 = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            object[] objArray1 = new object[] { 
                "insert into Base_SparepartsCate (Code,Name,ParentID,Sort,Memo,Status,GUID,creator,createdate) values ('", code, "','", text, "',", pid, ",", sort, ",'", memo, "',", isuse, ",'", str4, "',", creator,
                ",to_date('", str5, "','yyyy-mm-dd hh24:mi:ss'))"
            };
            string sql = string.Concat(objArray1);
            return base.ExecuteNonQuery_Fish(sql);
        }

        public int Updatedata(sparepartscate_treedata data, int updater)
        {
            string code = data.code;
            int id = data.id;
            string text = data.text;
            int sort = data.sort;
            int isuse = data.isuse;
            string memo = data.memo;
            string str4 = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            object[] objArray1 = new object[] { "update Base_SparepartsCate set Code='", code, "',Name='", text, "',Sort=", sort, ",Status=", isuse, ",Memo='", memo, "',updater=", updater, ",updatedate=to_date('", str4, "','yyyy-mm-dd hh24:mi:ss') where id=", id };
            string sql = string.Concat(objArray1);
            return base.ExecuteNonQuery_Fish(sql);
        }

        public static Base_SparepartsCateService Instance
        {
            get
            {
                return _Instance;
            }
        }
        
    }
}

