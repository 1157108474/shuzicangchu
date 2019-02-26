namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class WZ_PlanToApplyService : BaseService<WZ_PlanToApply>
    {
        public static readonly WZ_PlanToApplyService _Instance = new WZ_PlanToApplyService();

        public static WZ_PlanToApplyService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

