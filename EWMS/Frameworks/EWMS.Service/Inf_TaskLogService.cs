namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    using System;

    public class Inf_TaskLogService : BaseService<Inf_TaskLog>
    {
        private static readonly Inf_TaskLogService _Instance = new Inf_TaskLogService();

        public static void WriteLog(string Inf_TaskDetailName, int Inf_TaskKind, int TaskId, string Inf_Content, int SyncResult, string Inf_ErrorContent)
        {
            Inf_TaskLog data = new Inf_TaskLog {
                ID = int.Parse(Instance.GetDataTable_Fish("select INF_TASKLOG_SEQUENCE.nextval from dual").Rows[0][0].ToString()),
                GUID = Guid.NewGuid().ToString(),
                Inf_TaskDetailName = Inf_TaskDetailName,
                Inf_TaskKind = Inf_TaskKind,
                TaskId = TaskId,
                Inf_Content = Inf_Content,
                Creator = 0,
                CreateDate = DateTime.Now,
                Updator = 0,
                UpdateDate = DateTime.Now,
                SyncResult = SyncResult,
                Inf_ErrorContent = Inf_ErrorContent
            };
            Instance.Insert(data);
        }

        public static Inf_TaskLogService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

