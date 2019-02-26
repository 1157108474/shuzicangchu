namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class WZ_SheetCKSubDetailService : BaseService<WZ_SheetCKSubDetail>
    {
        private static readonly WZ_SheetCKSubDetailService _Instance = new WZ_SheetCKSubDetailService();

        public static WZ_SheetCKSubDetailService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

