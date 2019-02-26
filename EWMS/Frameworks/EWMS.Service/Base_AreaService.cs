namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    public class Base_AreaService : BaseService<Base_Area>
    {
        private static readonly Base_AreaService _Instance = new Base_AreaService();

        public static Base_AreaService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

