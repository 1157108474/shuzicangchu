namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;

    public class Inf_TaskService : BaseService<Inf_Task>
    {
        private static readonly Inf_TaskService _Instance = new Inf_TaskService();

        public int InsertData(Inf_Task task, int creator)
        {
            try
            {
                string s = Instance.GetDataTable_Fish("select INF_TASK_SEQUENCE.nextval from dual").Rows[0][0].ToString();
                task.ID = int.Parse(s);
                task.GUID = Guid.NewGuid().ToString();
                task.Creator = creator;
                task.CreateDate = DateTime.Now;
                return Instance.Insert(task);
            }
            catch (Exception)
            {
                return -1;
            }
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

        public static Inf_TaskService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

