namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_FormSet", PrimaryField="FormData_ID", IdentityField="", Order="", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_FormSet : BaseEntity
    {
        public int ExtendInt1 { get; set; }

        public int ExtendInt2 { get; set; }

        public int ExtendInt3 { get; set; }

        public int ExtendInt4 { get; set; }

        public string ExtendString1 { get; set; }

        public string ExtendString2 { get; set; }

        public string ExtendString3 { get; set; }

        public string ExtendString4 { get; set; }

        public string ExtendString5 { get; set; }

        public int FormCardTypeID { get; set; }

        public int FormData_BaseID { get; set; }

        public int FormData_ID { get; set; }

        public int FormDataType { get; set; }

        public int FormFieldControlInit { get; set; }

        public int FormFieldDefault { get; set; }

        public int FormFieldDictionaryID { get; set; }

        public int FormFieldGroup { get; set; }

        public int FormFieldIsHand { get; set; }

        public int FormFieldIsMust { get; set; }

        public int FormFieldIsOnly { get; set; }

        public int FormFieldLeft { get; set; }

        public string FormFieldName { get; set; }

        public int FormFieldPosLeftOrTop { get; set; }

        public int FormFieldStatus { get; set; }

        public int FormFieldTop { get; set; }

        public int FormFieldType { get; set; }

        public int FormFieldWidth { get; set; }

        public string FormLabelCaption { get; set; }

        public string FormLabelName { get; set; }

        public string FormSetName { get; set; }
    }
}

