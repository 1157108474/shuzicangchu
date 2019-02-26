namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    public class Base_CostUpdateService : BaseService<Base_CostUpdate>
    {
        private static readonly Base_CostUpdateService _Instance = new Base_CostUpdateService();

        public static Base_CostUpdateService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

