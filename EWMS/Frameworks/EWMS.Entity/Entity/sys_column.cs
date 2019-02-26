namespace EWMS.Entity
{
    using System;

    public class sys_column
    {
        public static string GetTypeName(string SqlTypeName, bool IsNullable)
        {
            //TODO
            string s = "";
            string str2 = SqlTypeName.ToLower();
            uint num =(uint) str2.GetHashCode();
            switch (num)
            {
                case 0x5bc874be:
                    if (str2 == "nvarchar")
                    {
                        break;
                    }
                    goto Label_0421;

                case 0x60cae230:
                    if (str2 == "bit")
                    {
                        s = "bool";
                        goto Label_0423;
                    }
                    goto Label_0421;

                case 0x68f8a468:
                    if (str2 == "numeric")
                    {
                        goto Label_0411;
                    }
                    goto Label_0421;

                case 0x1f088d4c:
                    if (str2 == "decimal")
                    {
                        goto Label_0411;
                    }
                    goto Label_0421;

                case 0x2cbdc544:
                    if (str2 == "tinyint")
                    {
                        s = "SByte";
                        goto Label_0423;
                    }
                    goto Label_0421;

                case 0x39093a58:
                    if (str2 == "smallmoney")
                    {
                        goto Label_0411;
                    }
                    goto Label_0421;

                case 0x7b98f031:
                    if (str2 == "sql_variant")
                    {
                        s = "object";
                        goto Label_0423;
                    }
                    goto Label_0421;

                case 0x819d3215:
                    if (str2 == "smallint")
                    {
                        s = "short";
                        goto Label_0423;
                    }
                    goto Label_0421;

                case 0x8a67a5ca:
                    if (str2 == "bigint")
                    {
                        s = "long";
                        goto Label_0423;
                    }
                    goto Label_0421;

                case 0xa84c031d:
                    if (str2 == "char")
                    {
                        break;
                    }
                    goto Label_0421;

                case 0xb283d523:
                    if (str2 == "timestamp")
                    {
                        goto Label_03D9;
                    }
                    goto Label_0421;

                case 0x95e97e5e:
                    if (str2 == "int")
                    {
                        s = "int";
                        goto Label_0423;
                    }
                    goto Label_0421;

                case 0xa6c45d85:
                    if (str2 == "float")
                    {
                        s = "double";
                        goto Label_0423;
                    }
                    goto Label_0421;

                case 0xcc898375:
                    if (str2 == "uniqueidentifier")
                    {
                        s = "Guid";
                        goto Label_0423;
                    }
                    goto Label_0421;

                case 0xccea6d90:
                    if (str2 == "datetime")
                    {
                        goto Label_0419;
                    }
                    goto Label_0421;

                case 0xd6dfb05d:
                    if (str2 == "real")
                    {
                        s = "Guid";
                        goto Label_0423;
                    }
                    goto Label_0421;

                case 0xb35135fa:
                    if (str2 == "image")
                    {
                        goto Label_03D9;
                    }
                    goto Label_0421;

                case 0xbde64e3e:
                    if (str2 == "text")
                    {
                        break;
                    }
                    goto Label_0421;

                case 0xc78d68c7:
                    if (str2 == "varbinary")
                    {
                        goto Label_03D9;
                    }
                    goto Label_0421;

                case 0xda2195c1:
                    if (str2 == "smalldatetime")
                    {
                        goto Label_0419;
                    }
                    goto Label_0421;

                case 0xda706eb6:
                    if (str2 == "xml")
                    {
                        break;
                    }
                    goto Label_0421;

                case 0xdd856cfc:
                    if (str2 == "binary")
                    {
                        goto Label_03D9;
                    }
                    goto Label_0421;

                case 0xe98bd702:
                    if (str2 == "ntext")
                    {
                        break;
                    }
                    goto Label_0421;

                case 0xf82db032:
                    if (str2 == "varchar")
                    {
                        break;
                    }
                    goto Label_0421;

                case 0xe0333069:
                    if (str2 == "nchar")
                    {
                        break;
                    }
                    goto Label_0421;

                case 0xe150c94f:
                    if (str2 == "money")
                    {
                        goto Label_0411;
                    }
                    goto Label_0421;

                default:
                    goto Label_0421;
            }
            s = "string";
            goto Label_0423;
        Label_03D9:
            s = "byte[]";
            goto Label_0423;
        Label_0411:
            s = "decimal";
            goto Label_0423;
        Label_0419:
            s = "DateTime";
            goto Label_0423;
        Label_0421:
            s = SqlTypeName;
        Label_0423:
            if (IsNullable)
            {
                num = (uint)s.GetHashCode(); 
                if (num <= 0x9bec7490)
                {
                    if (num <= 0x1f088d4c)
                    {
                        switch (num)
                        {
                            case 0x19402a08:
                                if (s == "SByte")
                                {
                                    goto Label_0535;
                                }
                                return s;

                            case 0x1f088d4c:
                                if (s == "decimal")
                                {
                                    goto Label_0535;
                                }
                                return s;
                        }
                        return s;
                    }
                    switch (num)
                    {
                        case 0x95e97e5e:
                            if (s == "int")
                            {
                                goto Label_0535;
                            }
                            return s;

                        case 0x9bec7490:
                            if (s != "DateTime")
                            {
                                return s;
                            }
                            goto Label_0535;
                    }
                    return s;
                }
                if (num <= 0xacc7cb2c)
                {
                    switch (num)
                    {
                        case 0xa0eb0f08:
                            if (s == "double")
                            {
                                goto Label_0535;
                            }
                            return s;

                        case 0xacc7cb2c:
                            if (s == "Guid")
                            {
                                goto Label_0535;
                            }
                            return s;
                    }
                    return s;
                }
                switch (num)
                {
                    case 0xba226bd5:
                        if (s == "short")
                        {
                            goto Label_0535;
                        }
                        return s;

                    case 0xc2ecdf53:
                        if (s == "long")
                        {
                            goto Label_0535;
                        }
                        return s;

                    case 0xc894953d:
                        if (s == "bool")
                        {
                            goto Label_0535;
                        }
                        return s;
                }
            }
            return s;
        Label_0535:
            return (s + "?");
        }

        public string ColumnCaption { get; set; }

        public string ColumnDescription { get; set; }

        public string ColumnName { get; set; }

        public short ColumnOrder { get; set; }

        public string DecimalNumber { get; set; }

        public string DefaultValue { get; set; }

        public string FlagIdentity { get; set; }

        public string FlagNullable { get; set; }

        public string FlagPrimary { get; set; }

        public bool IsIdentity
        {
            get
            {
                return (this.FlagIdentity == "√");
            }
        }

        public bool IsNullable
        {
            get
            {
                return (this.FlagNullable == "√");
            }
        }

        public bool IsPrimaryKey
        {
            get
            {
                return (this.FlagPrimary == "√");
            }
        }

        public int Length { get; set; }

        public string Size { get; set; }

        public string TableDescription { get; set; }

        public string TableName { get; set; }

        public string Type { get; set; }

        public string TypeName
        {
            get
            {
                return GetTypeName(this.Type, this.IsNullable);
            }
        }
    }
}

