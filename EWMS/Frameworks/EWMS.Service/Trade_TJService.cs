namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Trade_TJService : BaseService<Trade_TJ>
    {
        private static readonly Trade_TJService _Instance = new Trade_TJService();

        public static Trade_TJService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

