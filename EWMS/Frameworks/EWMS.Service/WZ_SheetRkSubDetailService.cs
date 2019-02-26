namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class WZ_SheetRkSubDetailService : BaseService<WZ_SheetRKSubDetail>
    {
        private static readonly WZ_SheetRkSubDetailService _Instance = new WZ_SheetRkSubDetailService();

        public static WZ_SheetRkSubDetailService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

