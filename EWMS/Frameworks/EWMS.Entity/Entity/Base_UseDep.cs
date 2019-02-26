namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_UseDep", PrimaryField="ID", IdentityField="", Order="CreateDate DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_UseDep : BaseEntity
    {
        public string Code { get; set; }

        public DateTime CreateDate { get; set; }

        public int Creator { get; set; }

        public int ERPID { get; set; }

        public decimal ExtendFloat1 { get; set; }

        public decimal ExtendFloat2 { get; set; }

        public decimal ExtendFloat3 { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public int ID { get; set; }

        public string Memo { get; set; }

        public string Name { get; set; }

        public int OrganizationID { get; set; }

        public int OrganizationType { get; set; }

        public int Status { get; set; }

        public DateTime UpdateDate { get; set; }

        public int Updater { get; set; }

        public int ZTID { get; set; }
    }
}

