namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_UserRoleService : BaseService<Base_UserRole>
    {
        private static readonly Base_UserRoleService _Instance = new Base_UserRoleService();

        public static Base_UserRoleService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

