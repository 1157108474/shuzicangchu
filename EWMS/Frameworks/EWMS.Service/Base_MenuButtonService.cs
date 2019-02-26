namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_MenuButtonService : BaseService<Base_MenuButton>
    {
        private static readonly Base_MenuButtonService _Instance = new Base_MenuButtonService();

        public static Base_MenuButtonService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

