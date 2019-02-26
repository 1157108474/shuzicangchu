namespace EWMS.Core
{
    using System;

    public interface IResult
    {
        int ResultID { get; set; }

        string ResultMsg { get; set; }

        bool Succeed { get; }
    }
}

