namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="WZ_TRAYBACK", PrimaryField="ID", IdentityField="", Order="CreateDate DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class WZ_TrayBack : BaseEntity
    {
        public DateTime CREATEDATE { get; set; }

        public string CREATOR { get; set; }

        public string DRIVERNAME { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public string LOADCARNO { get; set; }

        public string REMARK { get; set; }

        public string TELEPHONE { get; set; }

        public int TRAYBACKCOUNT { get; set; }

        public DateTime UPDATEDATE { get; set; }

        public int UPDATOR { get; set; }
    }
}

