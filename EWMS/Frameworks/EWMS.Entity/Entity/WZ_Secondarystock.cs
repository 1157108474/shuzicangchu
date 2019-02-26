namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="wz_secondarystock", PrimaryField="ID", IdentityField="", Order="CreateDate DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class WZ_Secondarystock : BaseEntity
    {
        public DateTime CREATEDATE { get; set; }

        public string CREATER { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public int PRODUCTID { get; set; }

        public string PRODUCTNAME { get; set; }

        public string PROPORTION { get; set; }

        public string REMARK { get; set; }

        public string TANKNAME { get; set; }

        public decimal TLLHEIGHT { get; set; }

        public DateTime UPDATEDATE { get; set; }

        public string UPDATER { get; set; }

        public decimal WEIGHT { get; set; }
    }
}

