namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_StockCostUpdateService : BaseService<Base_StockCostUpdate>
    {
        private static readonly Base_StockCostUpdateService _Instance = new Base_StockCostUpdateService();

        public static Base_StockCostUpdateService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

