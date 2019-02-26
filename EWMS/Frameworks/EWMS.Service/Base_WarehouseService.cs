namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    using System.Collections.Generic;
    using System.Data;
    using System.Linq;
    
    using System.Runtime.InteropServices;

    public class Base_WarehouseService : BaseService<Base_Warehouse>
    {
        private static readonly Base_WarehouseService _Instance = new Base_WarehouseService();

        private List<warehouse_treedata> GetTreeData_Children(List<Base_Warehouse> listDict, List<Base_Warehouse> listDict_Children)
        {
            List<warehouse_treedata> first = new List<warehouse_treedata>();
            using (List<Base_Warehouse>.Enumerator enumerator = listDict_Children.GetEnumerator())
            {
                while (enumerator.MoveNext())
                {
                    Base_Warehouse item = enumerator.Current;
                    warehouse_treedata _treedata = new warehouse_treedata {
                        id = item.ID,
                        text = item.Name,
                        pid = item.ParentID,
                        code = item.Code,
                        order = item.Sort,
                        memo = item.Memo,
                        status = item.Status,
                        level = item.LevelCount,
                        property = item.Property
                    };
                    first.Add(_treedata);
                    List<warehouse_treedata> second = new List<warehouse_treedata>();
                    List<Base_Warehouse> list3 = listDict.FindAll(p => p.ParentID == item.ID);
                    if (list3.Count > 0)
                    {
                        _treedata.isLeaf = false;
                        _treedata.expanded = false;
                        second = this.GetTreeData_Children(listDict, list3);
                        first = first.Concat<warehouse_treedata>(second).ToList<warehouse_treedata>();
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

        public List<warehouse_treedata> GetTreeData_Warehouse()
        {
            List<Base_Warehouse> listDict = Instance.GetList_Fish("and 1=1 and property<>4 order by Sort ASC", true);
            List<warehouse_treedata> first = new List<warehouse_treedata>();
            warehouse_treedata item = new warehouse_treedata {
                id = 0,
                text = "仓库信息",
                code = "ckxx",
                pid = -1,
                isLeaf = false,
                expanded = true
            };
            first.Add(item);
            List<warehouse_treedata> second = new List<warehouse_treedata>();
            List<Base_Warehouse> list4 = listDict.FindAll(p => p.ParentID == 0);
            if (list4.Count > 0)
            {
                second = this.GetTreeData_Children(listDict, list4);
                first = first.Concat<warehouse_treedata>(second).ToList<warehouse_treedata>();
            }
            return first;
        }

        public List<warehouse_treedata> GetTreeData_Warehouse(int id, int ztid = 0, int property = 4)
        {
            string sqlOrCondition = "";
            if (property == -1)
            {
                sqlOrCondition = " and ParentID=" + id + " and 1=1 order by Sort ASC";
            }
            else
            {
                object[] objArray1 = new object[] { " and ParentID=", id, " and 1=1 and property<>", property, " " };
                sqlOrCondition = string.Concat(objArray1);
            }
            if (ztid > 0)
            {
                sqlOrCondition = sqlOrCondition + " and ztid=" + ztid.ToString();
            }
            sqlOrCondition = sqlOrCondition + " order by Sort ASC";
            List<warehouse_treedata> list = new List<warehouse_treedata>();
            if (id == 0)
            {
                warehouse_treedata item = new warehouse_treedata {
                    id = 0,
                    text = "仓库信息",
                    code = "ckxx",
                    pid = -1,
                    isLeaf = false,
                    expanded = true,
                    property = 3
                };
                list.Add(item);
            }
            foreach (Base_Warehouse warehouse in Instance.GetList_Fish(sqlOrCondition, true))
            {
                warehouse_treedata _treedata2 = new warehouse_treedata {
                    id = warehouse.ID,
                    text = warehouse.Name,
                    pid = warehouse.ParentID,
                    code = warehouse.Code,
                    order = warehouse.Sort,
                    memo = warehouse.Memo,
                    status = warehouse.Status,
                    level = warehouse.LevelCount,
                    expanded = false,
                    property = warehouse.Property
                };
                string sql = "SELECT * FROM Base_Warehouse where ParentID=" + warehouse.ID;
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

        public int Insertdata(warehouse_treedata data, int creator)
        {
            int pid = data.pid;
            string code = data.code;
            int id = data.id;
            string text = data.text;
            int level = data.level;
            int order = data.order;
            int status = data.status;
            string memo = data.memo;
            string str4 = "0";
            string str5 = Guid.NewGuid().ToString();
            string str6 = "";
            int property = data.property;
            string str7 = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            string str8 = base.GetDataTable_Fish("select basewarehouse_sequence.nextval from dual").Rows[0][0].ToString();
            if (pid > 0)
            {
                string str10 = "select LevelCode from base_warehouse where ID=" + pid;
                DataTable table = base.GetDataTable_Fish(str10);
                if (table.Rows.Count > 0)
                {
                    str6 = table.Rows[0][0].ToString() + "." + str8;
                }
            }
            else
            {
                str6 = str8;
            }
            string sql = "insert into Base_Warehouse (ID,GUID ,Code ,Name ,ParentID ,Levelcount ,LevelCode ,EndFlag ,Status ,Sort ,Memo ,Creator ,CreateDate,Property) ";
            object[] objArray1 = new object[] { 
                sql, "values (", str8, ",'", str5, "','", code, "','", text, "',", pid, ",", level, ",'", str6, "',",
                str4, ",", status, ",", order, ",'", memo, "',", creator, ",to_date('", str7, "','yyyy-mm-dd hh24:mi:ss'),", property, ")"
            };
            sql = string.Concat(objArray1);
            try
            {
                return base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -3;
            }
        }

        public int Updatedata(warehouse_treedata data, int updater)
        {
            int pid = data.pid;
            string code = data.code;
            int id = data.id;
            string text = data.text;
            int level = data.level;
            int order = data.order;
            int status = data.status;
            string memo = data.memo;
            int property = data.property;
            string str4 = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            object[] objArray1 = new object[] { 
                "update Base_Warehouse set Code='", code, "',Name='", text, "',Sort=", order, ",Status=", status, ",Property=", property, ",Memo='", memo, "',updater=", updater, ",updatedate=to_date('", str4,
                "','yyyy-mm-dd hh24:mi:ss') where id=", id
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

        public static Base_WarehouseService Instance
        {
            get
            {
                return _Instance;
            }
        }
        
    }
}

