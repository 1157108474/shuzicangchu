namespace EWMS.WinService
{
    using Quartz;
    using Quartz.Impl;
    using Quartz.Impl.Matchers;
    using System;
    using Topshelf;
    using Utils;

    public sealed class ServiceRunner : ServiceControl, ServiceSuspend
    {
        private readonly IScheduler scheduler = null;

        public ServiceRunner()
        {
            scheduler = StdSchedulerFactory.GetDefaultScheduler();
            SyncJobListener myJobListener = new SyncJobListener();
            scheduler.ListenerManager.AddJobListener(myJobListener, GroupMatcher<JobKey>.GroupEquals("ERP"));
        }
        public bool Continue(HostControl hostControl)
        {
            this.scheduler.ResumeAll();
            return true;
        }

        public bool Pause(HostControl hostControl)
        {
            this.scheduler.PauseAll();
            return true;
        }

        public bool Start(HostControl hostControl)
        {
            LogHelper.Write("服务启动\r\n");
            this.scheduler.Start();
            return true;
        }

        public bool Stop(HostControl hostControl)
        {
            this.scheduler.Shutdown();
            return true;
        }
    }
}

