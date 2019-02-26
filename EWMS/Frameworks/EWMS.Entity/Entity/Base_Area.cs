namespace EWMS.Entity
{
    using EWMS.Core;
    using System;
    using System.Runtime.CompilerServices;

    [Serializable, Table(TableName="Base_Area", PrimaryField="Id", IdentityField="", Order="Id", IgnoreInsertFields="", IgnoreUpdateFields="")]
    public class Base_Area : BaseEntity
    {
        public string CityCode { get; set; }

        public string FullName { get; set; }

        public string Id { get; set; }

        public decimal? Lat { get; set; }

        public int? LevelType { get; set; }

        public decimal? Lng { get; set; }

        public string MergerName { get; set; }

        public string ParentId { get; set; }

        public string Pinyin { get; set; }

        public string ShortName { get; set; }

        public string state { get; set; }

        public string ZipCode { get; set; }
    }
}

