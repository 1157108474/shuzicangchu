namespace EWMS.Service
{
    using EWMS.Core;
    using System;
    using EWMS.Entity;

    public class WZ_SHEETRKDetailService : BaseService<WZ_SHEETRKDETAIL>
    {
        private static readonly WZ_SHEETRKDetailService _Instance = new WZ_SHEETRKDetailService();

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

        public static WZ_SHEETRKDetailService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

