namespace EWMS.Core
{
    using System;
    using System.Runtime.CompilerServices;

    public class BaseLoginer
    {
        public object Data { get; set; }

        public bool IsAdmin { get; set; }

        public string Password { get; set; }

        public string UserCode { get; set; }

        public int UserId { get; set; }

        public string UserName { get; set; }
    }
}

