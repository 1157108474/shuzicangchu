namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="WZ_SHEET_CK", PrimaryField="ID", IdentityField="", Order="CreateDate DESC", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class WZ_SHEET_CK : BaseEntity
    {
        public int Applydepartid { get; set; }

        public string Code { get; set; }

        public DateTime CreateDate { get; set; }

        public int Creator { get; set; }

        public int DepartId { get; set; }

        public int DutyId { get; set; }

        public decimal ExtendFloat1 { get; set; }

        public decimal ExtendFloat2 { get; set; }

        public decimal ExtendFloat3 { get; set; }

        public decimal ExtendFloat4 { get; set; }

        public decimal ExtendFloat5 { get; set; }

        public decimal ExtendFloat6 { get; set; }

        public decimal ExtendFloat7 { get; set; }

        public decimal ExtendFloat8 { get; set; }

        public int? ExtendInt1 { get; set; }

        public int? ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public int ExtendInt5 { get; set; }

        public int ExtendInt6 { get; set; }

        public int ExtendInt7 { get; set; }

        public int ExtendInt8 { get; set; }

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

        public int FundsSource { get; set; }

        public string GUID { get; set; }

        public int ID { get; set; }

        public int KindId { get; set; }

        public string Memo { get; set; }

        public string Name { get; set; }

        public int OfficesID { get; set; }

        public string OrderNum { get; set; }

        public int OwnerDep { get; set; }

        public int ProviderDepID { get; set; }

        public string ReceiveNum { get; set; }

        public int RelateSheet { get; set; }

        public int RoleId { get; set; }

        public int Route_StepId { get; set; }

        public int RouteId { get; set; }

        public int Status { get; set; }

        public int StoreManId { get; set; }

        public int SubmitManId { get; set; }

        public DateTime SubmitTime { get; set; }

        public int TypeId { get; set; }

        public DateTime UpdateDate { get; set; }

        public int Updator { get; set; }

        public int UsedDepartId { get; set; }

        public int UsedManID { get; set; }

        public int? ZTID { get; set; }
    }
}

