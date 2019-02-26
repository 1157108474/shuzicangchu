namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    using System.Collections.Generic;
    using System.Linq;
    

    public class Base_MaterialService : BaseService<Base_Material>
    {
        private static readonly Base_MaterialService _Instance = new Base_MaterialService();

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

        public List<organization_treedata> GetTreeData_Organization(int id)
        {
            string sqlOrCondition = " and ParentID=" + id + " and 1=1  order by sort ASC";
            List<organization_treedata> list = new List<organization_treedata>();
            if (id == 0)
            {
                organization_treedata item = new organization_treedata {
                    id = 0,
                    text = "供应商",
                    code = "zzjg",
                    pid = -1,
                    isLeaf = false,
                    expanded = true
                };
                list.Add(item);
            }
            foreach (Base_Material material in Instance.GetList_Fish(sqlOrCondition, true))
            {
                organization_treedata _treedata2 = new organization_treedata {
                    id = material.ID,
                    text = material.Name,
                    memo = material.Memo,
                    status = material.Status,
                    expanded = false
                };
                string sql = "SELECT * FROM Base_Material WHERE ParentID=" + material.ID;
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

        public List<sparepartscate_treedata> GetTreeData_SparepartsCate(int personID)
        {
            List<Base_SparepartsCate> listDict = Base_SparepartsCateService.Instance.GetList_Fish("and 1=1 order by Sort ASC", true);
            List<sparepartscate_treedata> first = new List<sparepartscate_treedata>();
            sparepartscate_treedata item = new sparepartscate_treedata {
                id = 0,
                text = "物料分类",
                pid = -1,
                isLeaf = false,
                expanded = false
            };
            first.Add(item);
            List<sparepartscate_treedata> second = new List<sparepartscate_treedata>();
            List<Base_SparepartsCate> list4 = new List<Base_SparepartsCate>();
            if (personID > 0)
            {
                list4 = Base_SparepartsCateService.Instance.GetList_Fish("Select a.* from Base_SparepartsCate a inner join BASE_PERSON_SCOPE b on a.id = b.scopeid where b.scopetype = 3 and b.personid=" + personID);
            }
            else
            {
                list4 = listDict.FindAll(p => p.ParentID == 0);
            }
            if (list4.Count > 0)
            {
                second = this.GetTreeData_Children(listDict, list4);
                first = first.Concat<sparepartscate_treedata>(second).ToList<sparepartscate_treedata>();
            }
            return first;
        }

        public int UpdateData(ParamUpdate param)
        {
            try
            {
                return Instance.Update(param);
            }
            catch (Exception)
            {
                return -1;
            }
        }

        public static Base_MaterialService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

