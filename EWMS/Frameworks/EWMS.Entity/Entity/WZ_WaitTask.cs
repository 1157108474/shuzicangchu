namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="WZ_WaitTask", PrimaryField="ID", IdentityField="ID", Order="ID DESC", IgnoreInsertFields="ID", IgnoreUpdateFields="ID")]
    public class WZ_WaitTask : BaseEntity
    {
        public DateTime CreateTime { get; set; }

        public int Creator { get; set; }

        public string EncryptCode { get; set; }

        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public int ExtendInt5 { get; set; }

        public int ExtendInt6 { get; set; }

        public int ExtendInt7 { get; set; }

        public int ExtendInt8 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string ExtendString4 { get; set; }

        public string ExtendString5 { get; set; }

        public string GUID { get; set; }

        public int HandingManID { get; set; }

        public DateTime HandingTime { get; set; }

        public int HandResult { get; set; }

        public string HandSuggestion { get; set; }

        public int ID { get; set; }

        public string JumpPath { get; set; }

        public string memo { get; set; }

        public string MenuID { get; set; }

        public int NextHandManID { get; set; }

        public int PrevSubmitManID { get; set; }

        public DateTime PrevSubmitTime { get; set; }

        public int RouteStepID { get; set; }

        public int SheetID { get; set; }

        public int status { get; set; }

        public int ZTID { get; set; }
    }
}

