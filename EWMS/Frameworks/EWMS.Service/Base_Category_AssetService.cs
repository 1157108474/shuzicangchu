namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    public class Base_Category_AssetService : BaseService<Base_Category_Asset>
    {
        public static readonly Base_Category_AssetService _Instance = new Base_Category_AssetService();

        public static Base_Category_AssetService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

