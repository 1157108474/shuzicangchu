namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Provider", PrimaryField="ID", IdentityField="", Order="", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Provider : BaseEntity
    {
        public string Address { get; set; }

        public string Code { get; set; }

        public string ContactPerson { get; set; }

        public string ContractPhone { get; set; }

        public DateTime? CreateDate { get; set; }

        public int Creator { get; set; }

        public string Email { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt10 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public int ExtendInt5 { get; set; }

        public int ExtendInt6 { get; set; }

        public int ExtendInt7 { get; set; }

        public int ExtendInt8 { get; set; }

        public int ExtendInt9 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString10 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string ExtendString4 { get; set; }

        public string ExtendString5 { get; set; }

        public string ExtendString6 { get; set; }

        public string ExtendString7 { get; set; }

        public string ExtendString8 { get; set; }

        public string ExtendString9 { get; set; }

        public string Fax { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public string Memo { get; set; }

        public string Name { get; set; }

        public int Sort { get; set; }

        public int Status { get; set; }

        public DateTime? UpdateDate { get; set; }

        public int Updater { get; set; }

        public int ZipCode { get; set; }

        public int ZTID { get; set; }
    }
}

