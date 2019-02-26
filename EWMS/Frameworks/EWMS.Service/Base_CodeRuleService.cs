namespace EWMS.Service
{
    using EWMS.Core;
    using EWMS.Entity;
    public class Base_CodeRuleService : BaseService<Base_CodeRule>
    {
        private static readonly Base_CodeRuleService _Instance = new Base_CodeRuleService();

        public static Base_CodeRuleService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

