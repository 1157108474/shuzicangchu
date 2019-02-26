using EWMS.Utils;
using Quartz;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace EWMS.WinService
{
    public class SyncJobListener : IJobListener
    {
        public string Name
        {
            get
            {
                return "SyncJobListener";
            }
        }

        public void JobExecutionVetoed(IJobExecutionContext context)
        {

        }

        public void JobToBeExecuted(IJobExecutionContext context)
        {
            LogHelper.Write(string.Format("Job{0}开始执行。", context.JobDetail.Key));
        }

        public void JobWasExecuted(IJobExecutionContext context, JobExecutionException jobException)
        {
            if (jobException == null)
            {
                LogHelper.Write(string.Format("Job{0}执行完成。", context.JobDetail.Key));
            }
            else
            {
                LogHelper.Write(string.Format("Job{0}执行失败：{1}", context.JobDetail.Key, jobException.Message));
            }
        }
    }
}
