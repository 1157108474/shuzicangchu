namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_DepartmentService : BaseService<Base_Department>
    {
        private static readonly Base_DepartmentService _Instance = new Base_DepartmentService();

        public static Base_DepartmentService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

