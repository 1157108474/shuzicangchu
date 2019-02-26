namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class WZ_RouteHistoryService : BaseService<WZ_RouteHistory>
    {
        private static readonly WZ_RouteHistoryService _Instance = new WZ_RouteHistoryService();

        public static WZ_RouteHistoryService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

