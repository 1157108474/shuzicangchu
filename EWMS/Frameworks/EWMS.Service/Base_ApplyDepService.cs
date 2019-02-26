namespace EWMS.Service
{
    using EWMS.Entity;
    using EWMS.Core;

    public class Base_ApplyDepService : BaseService<Base_ApplyDep>
    {
        private static readonly Base_ApplyDepService _Instance = new Base_ApplyDepService();

        public static Base_ApplyDepService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

