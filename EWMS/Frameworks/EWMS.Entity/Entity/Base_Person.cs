namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Person", PrimaryField="ID", IdentityField="", Order="", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Person : BaseEntity
    {
        public int AuditBy { get; set; }

        public DateTime? AuditTime { get; set; }

        public string Code { get; set; }

        public int CompanyID { get; set; }

        public DateTime? CreateDate { get; set; }

        public int Creator { get; set; }

        public int DepartID { get; set; }

        public string Email { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string ExtendString4 { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public int IsAudit { get; set; }

        public int IsOnline { get; set; }

        public int IsSingleLogin { get; set; }

        public DateTime? LastChangePassword { get; set; }

        public string LoginCity { get; set; }

        public int LoginCount { get; set; }

        public string LoginIP { get; set; }

        public DateTime? LoginTime { get; set; }

        public string Memo { get; set; }

        public string Name { get; set; }

        public int OfficesID { get; set; }

        public string Password { get; set; }

        public string Phone { get; set; }

        public string QQ { get; set; }

        public string Secretkey { get; set; }

        public string Sex { get; set; }

        public int Sort { get; set; }

        public string Spell { get; set; }

        public int Status { get; set; }

        public DateTime? UpdateDate { get; set; }

        public int Updater { get; set; }

        public int UserType { get; set; }

        public int ZTID { get; set; }
    }
}

