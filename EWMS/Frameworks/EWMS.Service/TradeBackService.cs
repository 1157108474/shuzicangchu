namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class TradeBackService : BaseService<WZ_TrayBack>
    {
        private static readonly TradeBackService _Instance = new TradeBackService();

        public static TradeBackService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

