namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using EWMS.Utils;
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Data;
    using System.Linq;
    

    public class Base_OrganizationService : BaseService<Base_Organization>
    {
        private static readonly Base_OrganizationService _Instance = new Base_OrganizationService();

        public Base_Organization GetDep(int depid)
        {
            new Base_Organization();
            string sql = "select levelcode from Base_Organization where id=" + depid;
            return Instance.GetEntity(sql);
        }

        private List<organization_treedata> GetTreeData_Children(List<Base_Organization> listDict, List<Base_Organization> listDict_Children)
        {
            List<organization_treedata> first = new List<organization_treedata>();
            using (List<Base_Organization>.Enumerator enumerator = listDict_Children.GetEnumerator())
            {
                while (enumerator.MoveNext())
                {
                    Base_Organization item = enumerator.Current;
                    organization_treedata _treedata = new organization_treedata {
                        id = item.ID,
                        text = item.Name,
                        pid = item.ParentID,
                        code = item.Code,
                        order = item.Sort,
                        memo = item.Memo,
                        status = item.Status,
                        level = item.LevelCount
                    };
                    first.Add(_treedata);
                    List<organization_treedata> second = new List<organization_treedata>();
                    List<Base_Organization> list3 = listDict.FindAll(p => p.ParentID == item.ID);
                    if (list3.Count > 0)
                    {
                        _treedata.isLeaf = false;
                        _treedata.expanded = false;
                        second = this.GetTreeData_Children(listDict, list3);
                        first = first.Concat<organization_treedata>(second).ToList<organization_treedata>();
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

        public List<organization_treedata> GetTreeData_Organization()
        {
            List<Base_Organization> listDict = Instance.GetList_Fish(" and 1=1 order by Sort", true);
            List<organization_treedata> first = new List<organization_treedata>();
            organization_treedata item = new organization_treedata {
                id = 0,
                text = "组织机构",
                code = "zzjg",
                pid = -1,
                isLeaf = false,
                expanded = true
            };
            first.Add(item);
            List<organization_treedata> second = new List<organization_treedata>();
            List<Base_Organization> list4 = listDict.FindAll(p => p.ParentID == 0);
            if (list4.Count > 0)
            {
                second = this.GetTreeData_Children(listDict, list4);
                first = first.Concat<organization_treedata>(second).ToList<organization_treedata>();
            }
            return first;
        }

        public List<organization_treedata> GetTreeData_Organization(int id)
        {
            string sql = "select * from V_BASE_DEPART where 1=1 and ParentID=" + id + "   order by Sort ASC";
            List<organization_treedata> list = new List<organization_treedata>();
            foreach (Base_Depart depart in Base_DepartService.Instance.GetList_Fish(sql))
            {
                organization_treedata item = new organization_treedata {
                    id = depart.ID,
                    text = depart.Name,
                    pid = depart.ParentID,
                    code = depart.Code,
                    order = depart.Sort,
                    memo = depart.Memo,
                    status = depart.Status,
                    level = depart.LevelCount,
                    expanded = false,
                    type = depart.type,
                    companyid = depart.COMPANYID
                };
                string str2 = "SELECT * FROM V_BASE_DEPART WHERE ParentID=" + depart.ID;
                if (Base_DepartService.Instance.GetDataTable_Fish(str2).Rows.Count > 0)
                {
                    item.isLeaf = false;
                }
                else
                {
                    item.isLeaf = true;
                }
                list.Add(item);
            }
            return list;
        }

        public List<organization_treedata> GetTreeData_Organization(string where, string type, string name)
        {
            List<organization_treedata> first = new List<organization_treedata>();
            List<Base_Depart> list1 = Base_DepartService.Instance.GetList_Fish(where);
            int[] id = new int[list1.Count];
            int index = 0;
            foreach (Base_Depart depart in list1)
            {
                int parentID = depart.ParentID;
                organization_treedata item = new organization_treedata {
                    id = depart.ID,
                    text = depart.Name,
                    pid = parentID,
                    code = depart.Code,
                    order = depart.Sort,
                    memo = depart.Memo,
                    status = depart.Status,
                    expanded = false,
                    type = depart.type,
                    isLeaf = true
                };
                first.Add(item);
                id[index] = depart.ParentID;
                index++;
            }
            List<organization_treedata> second = new List<organization_treedata>();
            second = this.GetTreeData_Parent(id);
            first = first.Concat<organization_treedata>(second).ToList<organization_treedata>();
            Hashtable hashtable = new Hashtable();
            for (int i = first.Count - 1; i >= 0; i--)
            {
                organization_treedata _treedata2 = first[i];
                string str = _treedata2.id.ToString();
                if (hashtable[str] == null)
                {
                    hashtable[str] = _treedata2;
                }
                else
                {
                    first.RemoveAt(i);
                }
            }
            return first;
        }

        private List<organization_treedata> GetTreeData_Parent(int[] id)
        {
            List<organization_treedata> first = new List<organization_treedata>();
            string str = "";
            for (int i = 0; i < id.Length; i++)
            {
                if (i == (id.Length - 1))
                {
                    str = str + id[i];
                }
                else
                {
                    str = str + id[i] + ",";
                }
            }
            if (str == "")
            {
                return first;
            }
            string sql = "select distinct * from v_base_depart where 1=1 and id in (" + str + ")";
            List<Base_Depart> list1 = Base_DepartService.Instance.GetList_Fish(sql);
            int[] numArray = new int[list1.Count];
            int index = 0;
            foreach (Base_Depart depart in list1)
            {
                organization_treedata item = new organization_treedata {
                    id = depart.ID,
                    text = depart.Name,
                    pid = depart.ParentID,
                    code = depart.Code,
                    order = depart.Sort,
                    memo = depart.Memo,
                    status = depart.Status,
                    expanded = true
                };
                numArray[index] = depart.ParentID;
                index++;
                first.Add(item);
            }
            List<organization_treedata> second = new List<organization_treedata>();
            second = this.GetTreeData_Parent(numArray);
            return first.Concat<organization_treedata>(second).ToList<organization_treedata>();
        }

        public int Insertdata(string json, int creator)
        {
            int num = 0;
            foreach (Hashtable hashtable1 in (ArrayList) JSON.Decode(json))
            {
                int num2 = Convert.ToInt32(hashtable1["ParentID"]);
                object obj1 = hashtable1["ParentName"];
                object obj2 = hashtable1["Code"];
                object obj10 = hashtable1["ID"];
                object obj3 = hashtable1["Name"];
                object obj4 = hashtable1["Level"];
                object obj5 = hashtable1["Order"];
                object obj6 = hashtable1["Status"];
                object obj7 = hashtable1["Memo"];
                object obj8 = hashtable1["Type"];
                string str = "0";
                string str2 = Guid.NewGuid().ToString();
                string str3 = "";
                string str4 = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                object obj9 = hashtable1["CompanyID"];
                string sql = "";
                if (obj8.ToString() == "1")
                {
                    string str6 = "SELECT * FROM Base_Organization where code='" + obj2 + "'";
                    if (base.GetDataTable_Fish(str6).Rows.Count > 0)
                    {
                        num = 2;
                    }
                    else
                    {
                        string str7 = base.GetDataTable_Fish("select baseorganization_sequence.nextval from dual").Rows[0][0].ToString();
                        if (num2 > 0)
                        {
                            string str8 = "select LevelCode from Base_Organization where ID=" + num2;
                            DataTable table = base.GetDataTable_Fish(str8);
                            if (table.Rows.Count > 0)
                            {
                                str3 = table.Rows[0][0].ToString() + "." + str7;
                            }
                        }
                        else
                        {
                            string str9 = "select LevelCode from Base_Company where CompanyID=" + obj9;
                            DataTable table2 = base.GetDataTable_Fish(str9);
                            if (table2.Rows.Count > 0)
                            {
                                str3 = table2.Rows[0][0].ToString() + "." + str7;
                            }
                        }
                        if (num2.ToString().IndexOf("1") == 0)
                        {
                            num2 = 0;
                        }
                        sql = "insert into Base_Organization (id,GUID ,Code ,Name ,ParentID ,LevelCount ,LevelCode ,EndFlag ,Status ,Sort ,Memo ,Creator ,CreateDate,companyid) ";
                        object[] objArray1 = new object[] { 
                            sql, "values (", str7, ",'", str2, "','", obj2, "','", obj3, "',", num2, ",", obj4, ",'", str3, "',",
                            str, ",", obj6, ",", obj5, ",'", obj7, "',", creator, ",to_date('", str4, "','yyyy-mm-dd hh24:mi:ss'),", obj9, ")"
                        };
                        sql = string.Concat(objArray1);
                        try
                        {
                            num = base.ExecuteNonQuery_Fish(sql);
                        }
                        catch (Exception)
                        {
                            num = -2;
                        }
                    }
                }
                else if (obj8.ToString() == "0")
                {
                    string str10 = "SELECT * FROM Base_company where companycode='" + obj2 + "'";
                    if (base.GetDataTable_Fish(str10).Rows.Count > 0)
                    {
                        num = 2;
                    }
                    else
                    {
                        string str11 = base.GetDataTable_Fish("select basecompany_sequence.nextval from dual").Rows[0][0].ToString();
                        if (num2 > 0)
                        {
                            string str12 = "select LevelCode from base_company where companyID=" + num2;
                            DataTable table3 = base.GetDataTable_Fish(str12);
                            if (table3.Rows.Count > 0)
                            {
                                str3 = table3.Rows[0][0].ToString() + "." + str11;
                            }
                        }
                        else
                        {
                            str3 = str11;
                        }
                        sql = "insert into Base_Company (CompanyID,CompanyCode ,CompanyName ,ShortName,CompanyType,ParentID ,Enabled ,Sort ,Remark ,Creator ,CreateDate,LevelCount ,LevelCode) ";
                        object[] objArray2 = new object[] { 
                            sql, "values (", str11, ",'", obj2, "','", obj3, "','", obj3, "',", obj8, ",", num2, ",", obj6, ",",
                            obj5, ",'", obj7, "',", creator, ",to_date('", str4, "','yyyy-mm-dd hh24:mi:ss'),", obj4, ",'", str3, "')"
                        };
                        sql = string.Concat(objArray2);
                        try
                        {
                            num = base.ExecuteNonQuery_Fish(sql);
                        }
                        catch (Exception)
                        {
                            num = -2;
                        }
                    }
                }
            }
            return num;
        }

        public int Updatedata(string json)
        {
            int num = 0;
            foreach (Hashtable hashtable1 in (ArrayList) JSON.Decode(json))
            {
                object obj1 = hashtable1["ParentID"];
                object obj9 = hashtable1["ParentName"];
                object obj2 = hashtable1["Code"];
                object obj3 = hashtable1["ID"];
                object obj4 = hashtable1["Name"];
                object obj10 = hashtable1["Level"];
                object obj5 = hashtable1["Order"];
                object obj6 = hashtable1["Status"];
                object obj7 = hashtable1["Memo"];
                object obj8 = hashtable1["Type"];
                string sql = "";
                if (obj8.ToString() == "1")
                {
                    object[] objArray1 = new object[] { "SELECT * FROM Base_Organization where code='", obj2, "' and id <>", obj3 };
                    string str2 = string.Concat(objArray1);
                    if (base.GetDataTable_Fish(str2).Rows.Count > 0)
                    {
                        num = 2;
                    }
                    else
                    {
                        object[] objArray2 = new object[] { "update Base_Organization set Code='", obj2, "',Name='", obj4, "',Sort=", obj5, ",Status=", obj6, ",Memo='", obj7, "' where id=", obj3 };
                        sql = string.Concat(objArray2);
                        try
                        {
                            num = base.ExecuteNonQuery_Fish(sql);
                        }
                        catch (Exception)
                        {
                            num = -2;
                        }
                    }
                }
                else if (obj8.ToString() == "0")
                {
                    object[] objArray3 = new object[] { "SELECT * FROM Base_Company where CompanyCode='", obj2, "' and Companyid <>", obj3 };
                    string str3 = string.Concat(objArray3);
                    if (base.GetDataTable_Fish(str3).Rows.Count > 0)
                    {
                        num = 2;
                    }
                    else
                    {
                        object[] objArray4 = new object[] { "update Base_Company set CompanyCode='", obj2, "',CompanyName='", obj4, "',Sort=", obj5, ",Enabled=", obj6, ",Remark='", obj7, "' where CompanyID=", obj3 };
                        sql = string.Concat(objArray4);
                        try
                        {
                            num = base.ExecuteNonQuery_Fish(sql);
                        }
                        catch (Exception)
                        {
                            num = -2;
                        }
                    }
                }
            }
            return num;
        }

        public static Base_OrganizationService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

