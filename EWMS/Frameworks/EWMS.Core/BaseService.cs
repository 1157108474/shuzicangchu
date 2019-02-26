namespace EWMS.Core
{
    using System;

    public class BaseService : BaseService<BaseEntity>
    {
        public BaseService(string moduleName) : base(moduleName)
        {
        }
    }
}

