namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_FormSet_Base", PrimaryField="ID", IdentityField="", Order="", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_FormSet_Base : BaseEntity
    {
        public int FormData_BaseID { get; set; }

        public string FormData_ChineseName { get; set; }

        public string FormDataJumpPath { get; set; }

        public string FormDataName { get; set; }

        public string FormDataType { get; set; }

        public int ID { get; set; }
    }
}

