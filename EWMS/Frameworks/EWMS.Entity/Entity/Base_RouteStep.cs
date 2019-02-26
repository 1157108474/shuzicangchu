namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_RouteStep", PrimaryField="ID", IdentityField="", Order="", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_RouteStep : BaseEntity
    {
        public string Code { get; set; }

        public DateTime CreateDate { get; set; }

        public int Creator { get; set; }

        public int DeviceStatus { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string ExtendString4 { get; set; }

        public string ExtendString5 { get; set; }

        public int Flag1 { get; set; }

        public int Flag2 { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public string InputCondition { get; set; }

        public string InputProcedure { get; set; }

        public string Name { get; set; }

        public int NextID { get; set; }

        public string OperButtons { get; set; }

        public string OperFormFiedls { get; set; }

        public string OperProcedure { get; set; }

        public int OrderNum { get; set; }

        public int PersonID { get; set; }

        public int PrevID { get; set; }

        public string Remark { get; set; }

        public string RoleID { get; set; }

        public int RouteID { get; set; }

        public int RouteType { get; set; }

        public int SheetStatus { get; set; }

        public int Status { get; set; }

        public DateTime UpdateDate { get; set; }

        public int Updator { get; set; }

        public int ZTID { get; set; }
    }
}

