namespace EWMS.Core
{
    using System;
    using System.Runtime.CompilerServices;

    [Serializable]
    public class CommandResult : IResult
    {
        private int _ResultID = -1;
        private string _ResultMsg = string.Empty;

        public CommandResult()
        {
            this.Set(-1, "失败");
        }

        public CommandResult Set(bool success, string resultMsg)
        {
            this.ResultID = success ? 0 : -1;
            this.ResultMsg = resultMsg;
            return this;
        }

        public CommandResult Set(int resultID, string resultMsg)
        {
            this.ResultID = resultID;
            this.ResultMsg = resultMsg;
            return this;
        }

        public string emsg
        {
            get
            {
                return this.ResultMsg;
            }
        }

        public static CommandResult Instance_Error
        {
            get
            {
                return new CommandResult { 
                    ResultID = -1,
                    ResultMsg = "失败"
                };
            }
        }

        public static CommandResult Instance_Succeed
        {
            get
            {
                return new CommandResult { 
                    ResultID = 0,
                    ResultMsg = "成功"
                };
            }
        }

        public object ResultData { get; set; }

        public int ResultID
        {
            get
            {
                return this._ResultID;
            }
            set
            {
                this._ResultID = value;
            }
        }

        public string ResultMsg
        {
            get
            {
                return this._ResultMsg;
            }
            set
            {
                this._ResultMsg = value;
            }
        }

        public bool s
        {
            get
            {
                return this.Succeed;
            }
        }

        public bool Succeed
        {
            get
            {
                return (this._ResultID == 0);
            }
        }
    }
}

