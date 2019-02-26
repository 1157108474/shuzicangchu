namespace EWMS.Core
{
    using FluentData;
    using EWMS.Utils;
    using System;

    public class APP
    {
        public static string Db_Default_ConnName = "Sys";
        public static string Download_RootPath = "/upload/";
        public static string Field_AddBy = "AddBy";
        public static string Field_AddOn = "AddOn";
        public static string Field_EditBy = "EditBy";
        public static string Field_EditOn = "EditOn";
        public static string Msg_Delete_Success = "删除成功！";
        public static string Msg_File_NotExist = "请求的文件不存在！";
        public static string Msg_Insert_Success = "新增成功！";
        public static string Msg_Miss_Module_Attr = "业务类{0}，缺少特性ModuleAttribute";
        public static string Msg_NotAuth_Code = "NotAuth";
        public static string Msg_NotAuth_Text = "未登录";
        public static string Msg_Save_Success = "保存成功！";
        public static string Msg_Update_Success = "更新成功！";
        public static Action<CommandEventArgs> OnDbExecuting = null;

        public static void DbExecuting(CommandEventArgs args)
        {
            LogHelper.WriteDb("DbExecuting执行sql语句：" + args.Command.CommandText);
        }

        public static void Init()
        {
            ClownFishHelper.Init(Db_Default_ConnName);
            PinYinHelper.Init();
            OnDbExecuting = new Action<CommandEventArgs>(APP.DbExecuting);
        }
    }
}

