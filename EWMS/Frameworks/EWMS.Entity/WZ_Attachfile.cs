namespace EWMS
{
    using EWMS.Core;
    using System;

    [Serializable, Table(TableName="WZ_Attachfile", PrimaryField="ID", IdentityField="ID", Order="CreateDate DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class WZ_Attachfile : BaseEntity
    {
        public int Attachrelateid { get; set; }

        public DateTime CreateDate { get; set; }

        public int Creator { get; set; }

        public string FileAliAsName { get; set; }

        public string FileExt { get; set; }

        public string FileName { get; set; }

        public string FilePath { get; set; }

        public string FileType { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public string Memo { get; set; }

        public int Status { get; set; }
    }
}

