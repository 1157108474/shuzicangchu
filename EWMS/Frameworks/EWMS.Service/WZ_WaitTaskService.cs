namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class WZ_WaitTaskService : BaseService<WZ_WaitTask>
    {
        private static readonly WZ_WaitTaskService _Instance = new WZ_WaitTaskService();

        public static WZ_WaitTaskService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

