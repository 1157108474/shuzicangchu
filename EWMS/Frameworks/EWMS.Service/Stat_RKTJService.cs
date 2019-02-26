namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Stat_RKTJService : BaseService<Stat_RKTJ>
    {
        private static readonly Stat_RKTJService _Instance = new Stat_RKTJService();

        public static Stat_RKTJService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

