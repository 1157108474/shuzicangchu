namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(IdentityField="", IgnoreInsertFields="", IgnoreUpdateFields="", Order="CreateDate DESC", PrimaryField="ID", TableName="WZ_PlanToApply")]
    public class WZ_PlanToApply : BaseEntity
    {
        public decimal ApplyCount { get; set; }

        public int ApplyDepartID { get; set; }

        public string ApplyDepCode { get; set; }

        public decimal BaseCount { get; set; }

        public string BaseUnit { get; set; }

        public DateTime CreateDate { get; set; }

        public DateTime DSAddDate { get; set; }

        public int ERPDetailID { get; set; }

        public int ERPID { get; set; }

        public decimal ExtendFloat1 { get; set; }

        public decimal ExtendFloat2 { get; set; }

        public decimal ExtendFloat3 { get; set; }

        public decimal ExtendFloat4 { get; set; }

        public decimal ExtendFloat5 { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public int ExtendInt5 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string ExtendString4 { get; set; }

        public string ExtendString5 { get; set; }

        public int ID { get; set; }

        public string MaterialCode { get; set; }

        public string MaterialDescription { get; set; }

        public string MaterialUnit { get; set; }

        public decimal NoApplyCount { get; set; }

        public int ORGANIZATION_ID { get; set; }

        public string PlanCode { get; set; }

        public int PlanDetailID { get; set; }

        public int PlanID { get; set; }

        public string Remark { get; set; }

        public string SheetCode { get; set; }

        public string USE { get; set; }

        public int usedDepartID { get; set; }

        public string UseDepCode { get; set; }

        public int ZTID { get; set; }
    }
}

