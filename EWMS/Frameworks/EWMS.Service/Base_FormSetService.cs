namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_FormSetService : BaseService<Base_FormSet>
    {
        private static readonly Base_FormSetService _Instance = new Base_FormSetService();

        public static Base_FormSetService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

