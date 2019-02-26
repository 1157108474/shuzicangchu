namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_FormSet_BaseService : BaseService<Base_FormSet_Base>
    {
        private static readonly Base_FormSet_BaseService _Instance = new Base_FormSet_BaseService();

        public static Base_FormSet_BaseService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

