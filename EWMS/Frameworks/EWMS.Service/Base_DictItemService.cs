namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_DictItemService : BaseService<Base_DictItem>
    {
        private static readonly Base_DictItemService _Instance = new Base_DictItemService();

        public static Base_DictItemService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

