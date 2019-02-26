namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_OffsetInfoService : BaseService<Base_OffsetInfo>
    {
        private static readonly Base_OffsetInfoService _Instance = new Base_OffsetInfoService();

        public static Base_OffsetInfoService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

