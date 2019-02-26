namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;
    using System.Collections.Generic;

    public class Base_RouteStepService : BaseService<Base_RouteStep>
    {
        private static readonly Base_RouteStepService _Instance = new Base_RouteStepService();

        public List<organization_treedata> GetTreeData_Step(int id)
        {
            string sqlOrCondition = " and ParentID=" + id + " and 1=1  order by Sort ASC";
            List<organization_treedata> list = new List<organization_treedata>();
            if (id == 30)
            {
                organization_treedata item = new organization_treedata {
                    id = 30,
                    text = "业务流程",
                    code = "ywlc",
                    pid = -1,
                    isLeaf = false,
                    expanded = true
                };
                list.Add(item);
            }
            foreach (Base_Dictionary dictionary in Base_DictionaryService.Instance.GetList_Fish(sqlOrCondition, true))
            {
                organization_treedata _treedata2 = new organization_treedata {
                    id = dictionary.ID,
                    text = dictionary.Name,
                    pid = dictionary.ParentID,
                    code = dictionary.Code,
                    order = dictionary.Sort,
                    memo = dictionary.Memo,
                    status = dictionary.Status,
                    level = dictionary.Level,
                    expanded = false
                };
                string sql = "SELECT * FROM Base_Dictionary WHERE ParentID=" + dictionary.ID;
                if (Base_OrganizationService.Instance.GetDataTable_Fish(sql).Rows.Count > 0)
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

        public int Insertdata(Base_RouteStepVM step, int curruser, string btns, string forms, string routeid, string btnsName, string formsName)
        {
            string str = Guid.NewGuid().ToString();
            string str2 = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            string[] textArray1 = new string[] { " and RouteID='", routeid, "' AND Code='", step.code, "'" };
            if (base.Exists_Fish(string.Concat(textArray1)))
            {
                return -2;
            }
            try
            {
                string sql = "insert into Base_RouteStep(GUID ,Code ,Name ,RouteID ,PrevID ,NextID ,RoleID ,PersonID ,RouteType,InputCondition,InputProcedure,OperProcedure,SheetStatus,OperButtons,OperFormFiedls,DeviceStatus,Flag1,Flag2,Status,OrderNum,Remark,Creator,CreateDate,ExtendString1,ExtendString2,ZTID) ";
                object[] objArray1 = new object[] { 
                    sql, "values ('", str, "','", step.code, "','", step.name, "','", routeid, "','", step.prev, "','", step.next, "','", step.role, "','",
                    step.person, "','", step.sptype, "','", step.intocondition, "','", step.judgeprocess, "','", step.dealprocess, "','", step.sheetstatus, "','", btns, "','", forms, "','",
                    step.zcstatus, "','", step.isNotice, "','", step.isEmail, "','", step.status, "','", step.order, "','", step.memo, "','", curruser, "',to_date('", str2, "','yyyy-mm-dd hh24:mi:ss'),'",
                    btnsName, "','", formsName, "',1000)"
                };
                sql = string.Concat(objArray1);
                return base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -1;
            }
        }

        public int Updatedata(Base_RouteStepVM step, int curruser, string btns, string forms, string btnsName, string id, string formsname, string routeid)
        {
            try
            {
                string str = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
                string[] textArray1 = new string[] { " and RouteID='", routeid, "' AND Code='", step.code, "' and ID<>'", id, "'" };
                if (base.Exists_Fish(string.Concat(textArray1)))
                {
                    return -2;
                }
                object[] objArray1 = new object[] { 
                    "update Base_RouteStep set \r\n                           Code='", step.code, "',Name='", step.name, "',PrevID='", step.prev, "',NextID='", step.next, "',RoleID='", step.role, "',PersonID='", step.person, "',RouteType='", step.sptype, "',InputCondition='", step.intocondition,
                    "',InputProcedure='", step.judgeprocess, "',OperProcedure='", step.dealprocess, "',SheetStatus='", step.sheetstatus, "',OperButtons='", btns, "',OperFormFiedls='", forms, "',DeviceStatus='", step.zcstatus, "',Flag1='", step.isNotice, "',Flag2='", step.isEmail,
                    "',Status='", step.status, "',OrderNum='", step.order, "',Remark='", step.memo, "' , UpdateDate=to_date('", str, "','yyyy-mm-dd hh24:mi:ss'),Updator='", curruser, "',ExtendString1='", btnsName, "',ExtendString2='", formsname, "' where ID='", id,
                    "' "
                };
                string sql = string.Concat(objArray1);
                return base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -1;
            }
        }

        public static Base_RouteStepService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

