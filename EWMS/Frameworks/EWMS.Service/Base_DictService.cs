namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    using System.Collections.Generic;
    

    public class Base_DictService : BaseService<Base_Dict>
    {
        private static readonly Base_DictService _Instance = new Base_DictService();

        private List<dict_treedata> GetTreeData_Children(List<Base_Dict> listDict, List<Base_Dict> listDict_Children)
        {
            List<dict_treedata> list = new List<dict_treedata>();
            using (List<Base_Dict>.Enumerator enumerator = listDict_Children.GetEnumerator())
            {
                while (enumerator.MoveNext())
                {
                    Base_Dict item = enumerator.Current;
                    dict_treedata _treedata1 = new dict_treedata {
                        id = item.DictCode,
                        text = item.DictName,
                        iconCls = "icon-standard-image",
                        state = "open",
                        children = new List<dict_treedata>()
                    };
                    dict_attributes _attributes1 = new dict_attributes {
                        dict_code = item.DictCode,
                        dict_pcode = item.ParentDictCode,
                        dict_sort = item.Sort,
                        dict_enabled = item.Enabled,
                        dict_remark = item.Remark
                    };
                    _treedata1.attributes = _attributes1;
                    dict_treedata _treedata = _treedata1;
                    List<dict_treedata> list2 = new List<dict_treedata>();
                    List<Base_Dict> list3 = listDict.FindAll(p => p.ParentDictCode == item.DictCode);
                    if (list3.Count > 0)
                    {
                        list2 = this.GetTreeData_Children(listDict, list3);
                        _treedata.children = list2;
                    }
                    list.Add(_treedata);
                }
            }
            return list;
        }

        public List<dict_treedata> GetTreeData_Dict()
        {
            List<Base_Dict> listDict = Instance.GetList_Fish("and 1=1 order by Sort ASC", true);
            dict_treedata _treedata1 = new dict_treedata {
                id = "all",
                text = "所有数据字典分类",
                iconCls = "icon-standard-house",
                state = "open",
                children = new List<dict_treedata>()
            };
            dict_attributes _attributes1 = new dict_attributes {
                dict_code = "0",
                dict_pcode = "0",
                dict_sort = 0,
                dict_enabled = 1,
                dict_remark = "所有数据字典分类"
            };
            _treedata1.attributes = _attributes1;
            dict_treedata _treedata = _treedata1;
            List<Base_Dict> list2 = listDict.FindAll(p => p.ParentDictCode == "0");
            if (list2.Count > 0)
            {
                _treedata.children = this.GetTreeData_Children(listDict, list2);
            }
            return new List<dict_treedata> { _treedata };
        }

        public static Base_DictService Instance
        {
            get
            {
                return _Instance;
            }
        }
        
    }
}

