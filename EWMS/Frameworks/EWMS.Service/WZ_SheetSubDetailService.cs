namespace EWMS.Service
{
    using EWMS.Core;
    using System;
    using EWMS.Entity;

    public class WZ_SheetSubDetailService : BaseService<WZ_SheetSubDetail>
    {
        private static readonly WZ_SheetSubDetailService _Instance = new WZ_SheetSubDetailService();

        public int UpdateDetails(ParamUpdate param)
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

        public static WZ_SheetSubDetailService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

