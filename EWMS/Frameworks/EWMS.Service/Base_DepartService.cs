namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_DepartService : BaseService<Base_Depart>
    {
        private static readonly Base_DepartService _Instance = new Base_DepartService();

        public static Base_DepartService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

