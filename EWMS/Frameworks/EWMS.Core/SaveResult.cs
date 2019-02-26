namespace EWMS.Core
{
    using System;

    [Serializable]
    public class SaveResult : CommandResult
    {
        private object _CustomObject;

        public static SaveResult CreateDefault()
        {
            return new SaveResult { 
                ResultID = 0,
                ResultMsg = "保存成功！"
            };
        }

        public void SetException(Exception ex)
        {
            base.ResultID = -1;
            base.ResultMsg = base.ResultMsg + "\n\n" + ex.Message;
        }

        public object CustomObject
        {
            get
            {
                return this._CustomObject;
            }
            set
            {
                this._CustomObject = value;
            }
        }
    }
}

