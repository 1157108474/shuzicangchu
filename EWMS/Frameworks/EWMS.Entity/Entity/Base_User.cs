namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_User", PrimaryField="UserId", IdentityField="UserId", Order="Sort desc", IgnoreInsertFields="IsAudit,AuditBy,AuditTime,IsOnline,LoginCount,LoginTime,LoginIP,LoginCity,LastChangePassword", IgnoreUpdateFields="Password,IsAudit,AuditBy,AuditTime,IsOnline,LoginCount,LoginTime,LoginIP,LoginCity,LastChangePassword")]
    public class Base_User : BaseEntity
    {
        public string AddBy { get; set; }

        public DateTime? AddOn { get; set; }

        public string AuditBy { get; set; }

        public DateTime? AuditTime { get; set; }

        public string CompanyCode { get; set; }

        public string DepartmentCode { get; set; }

        public string EditBy { get; set; }

        public DateTime? EditOn { get; set; }

        public string Email { get; set; }

        public int Enabled { get; set; }

        public int IsAudit { get; set; }

        public int IsOnline { get; set; }

        public int IsSingleLogin { get; set; }

        public int IsSuperAdmin { get; set; }

        public DateTime? LastChangePassword { get; set; }

        public string LoginCity { get; set; }

        public int LoginCount { get; set; }

        public string LoginIP { get; set; }

        public DateTime? LoginTime { get; set; }

        public string Password { get; set; }

        public string Phone { get; set; }

        public string QQ { get; set; }

        public string RealName { get; set; }

        public string Remark { get; set; }

        public string Secretkey { get; set; }

        public string Sex { get; set; }

        public int Sort { get; set; }

        public string Spell { get; set; }

        public string UserCode { get; set; }

        public int UserId { get; set; }

        public int UserType { get; set; }
    }
}

