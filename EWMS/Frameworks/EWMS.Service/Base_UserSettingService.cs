namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;

    public class Base_UserSettingService : BaseService<Base_UserSetting>
    {
        private static readonly Base_UserSettingService _Instance = new Base_UserSettingService();

        public static Base_UserSettingService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

