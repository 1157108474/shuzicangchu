namespace EWMS.Core
{
    using FluentData;
    using System;
    using System.Collections.Generic;
    using System.Configuration;

    public class PinYinHelper
    {
        private static Dictionary<string, bool> IsValid = new Dictionary<string, bool>();

        public static void GenaratePinYinFunc()
        {
            try
            {
                ConnectionStringSettingsCollection connectionStrings = ConfigurationManager.ConnectionStrings;
                for (int i = 0; i < connectionStrings.Count; i++)
                {
                    if (connectionStrings[i].ProviderName == "SqlServer")
                    {
                        IsSupportPinYin(connectionStrings[i].Name);
                    }
                }
            }
            catch
            {
            }
        }

        private static string GetFnPinYinSQL()
        {
            return "\r\n--set ANSI_NULLS ON\r\n--set QUOTED_IDENTIFIER ON\r\n \r\n-- =============================================\r\n-- Description:\t提供中文首字母\r\n-- Demo: select * from 表 where fun_getPY(字段) like N'%zgr%'\r\n-- =============================================\r\nCREATE FUNCTION [dbo].[fun_getPY]\r\n(\r\n\t@str NVARCHAR(4000)\r\n)\r\nRETURNS NVARCHAR(4000)\r\nAS\r\nBEGIN\r\n\tDECLARE @word NCHAR(1),@PY NVARCHAR(4000)\r\n\tSET @PY=''\r\n\tWHILE len(@str)>0\r\n\tBEGIN\r\n\t\tSET @word=left(@str,1)\r\n\t\tSET @PY=@PY+(CASE WHEN unicode(@word) BETWEEN 19968 AND 19968+20901\r\n\t\tTHEN (SELECT TOP 1 PY FROM (\r\n\t\tSELECT 'A' AS PY,N'驁' AS word\r\n\t\tUNION ALL SELECT 'B',N'簿'\r\n\t\tUNION ALL SELECT 'C',N'錯'\r\n\t\tUNION ALL SELECT 'D',N'鵽'\r\n\t\tUNION ALL SELECT 'E',N'樲'\r\n\t\tUNION ALL SELECT 'F',N'鰒'\r\n\t\tUNION ALL SELECT 'G',N'腂'\r\n\t\tUNION ALL SELECT 'H',N'夻'\r\n\t\tUNION ALL SELECT 'J',N'攈'\r\n\t\tUNION ALL SELECT 'K',N'穒'\r\n\t\tUNION ALL SELECT 'L',N'鱳'\r\n\t\tUNION ALL SELECT 'M',N'旀'\r\n\t\tUNION ALL SELECT 'N',N'桛'\r\n\t\tUNION ALL SELECT 'O',N'漚'\r\n\t\tUNION ALL SELECT 'P',N'曝'\r\n\t\tUNION ALL SELECT 'Q',N'囕'\r\n\t\tUNION ALL SELECT 'R',N'鶸'\r\n\t\tUNION ALL SELECT 'S',N'蜶'\r\n\t\tUNION ALL SELECT 'T',N'籜'\r\n\t\tUNION ALL SELECT 'W',N'鶩'\r\n\t\tUNION ALL SELECT 'X',N'鑂'\r\n\t\tUNION ALL SELECT 'Y',N'韻'\r\n\t\tUNION ALL SELECT 'Z',N'咗'\r\n\t\t) T \r\n\t\tWHERE word>=@word COLLATE Chinese_PRC_CS_AS_KS_WS \r\n\t\tORDER BY PY ASC) ELSE @word END)\r\n\t\tSET @str=right(@str,len(@str)-1)\r\n\tEND\r\n\tRETURN @PY\r\nEND";
        }

        private static string GetJudgeSQL()
        {
            return "select name from sysobjects where id = object_id(N'[dbo].[fun_getPY]') and xtype in (N'FN', N'IF', N'TF')";
        }

        public static void Init()
        {
            GenaratePinYinFunc();
        }

        public static bool IsSupportPinYin(string module)
        {
            if (!IsValid.ContainsKey(module))
            {
                using (IDbContext context = Db.Context(module))
                {
                    if (context.Sql(GetJudgeSQL(), new object[0]).QueryMany<object>((Action<object, IDataReader>) null).Count == 0)
                    {
                        context.Sql(GetFnPinYinSQL(), new object[0]).Execute();
                    }
                    List<object> list = context.Sql(GetJudgeSQL(), new object[0]).QueryMany<object>((Action<object, IDataReader>) null);
                    IsValid[module] = list.Count > 0;
                }
            }
            return IsValid[module];
        }
    }
}

