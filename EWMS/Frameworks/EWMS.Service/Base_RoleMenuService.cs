namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_RoleMenuService : BaseService<Base_RoleMenu>
    {
        private static readonly Base_RoleMenuService _Instance = new Base_RoleMenuService();

        public static Base_RoleMenuService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

