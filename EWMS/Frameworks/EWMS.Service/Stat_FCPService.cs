namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Stat_FCPService : BaseService<WZ_Secondarystock>
    {
        private static readonly Stat_FCPService _Instance = new Stat_FCPService();

        public static Stat_FCPService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

