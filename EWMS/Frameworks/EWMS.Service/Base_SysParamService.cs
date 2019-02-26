namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_SysParamService : BaseService<Base_SysParam>
    {
        private static readonly Base_SysParamService _Instance = new Base_SysParamService();

        public static Base_SysParamService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

