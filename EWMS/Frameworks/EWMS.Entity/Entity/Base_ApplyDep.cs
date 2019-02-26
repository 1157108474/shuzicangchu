namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_ApplyDep", PrimaryField="ID", IdentityField="", Order="CreateDate DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_ApplyDep : BaseEntity
    {
        public string CODE { get; set; }

        public string DEMO { get; set; }

        public string ERPID { get; set; }

        public decimal EXTENDFLOAT1 { get; set; }

        public decimal EXTENDFLOAT2 { get; set; }

        public decimal EXTENDFLOAT3 { get; set; }

        public int EXTENDINT1 { get; set; }

        public int EXTENDINT2 { get; set; }

        public int EXTENDINT3 { get; set; }

        public string EXTENDSTRING1 { get; set; }

        public string EXTENDSTRING2 { get; set; }

        public string EXTENDSTRING3 { get; set; }

        public int ID { get; set; }

        public string NAME { get; set; }

        public int STATUS { get; set; }

        public string SUBJECTSGROUP { get; set; }

        public string SUBJECTSGROUPDESCRIPTION { get; set; }

        public int ZTID { get; set; }
    }
}

