namespace EWMS.Service
{
    using EWMS.Core;
    using System;
    using EWMS.Entity;

    public class Base_UnitConversionService : BaseService<Base_UnitConversion>
    {
        private static readonly Base_UnitConversionService _Instance = new Base_UnitConversionService();

        public int UpdateData(ParamUpdate param)
        {
            try
            {
                return Instance.Update(param);
            }
            catch (Exception)
            {
                return -1;
            }
        }

        public static Base_UnitConversionService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

