namespace EWMS.Service
{
    using EWMS;
    using EWMS.Core;
    using System;

    public class WZ_AttachfileService : BaseService<WZ_Attachfile>
    {
        private static readonly WZ_AttachfileService _Instance = new WZ_AttachfileService();

        public int AddFiles(WZ_Attachfile files)
        {
            try
            {
                string str = Guid.NewGuid().ToString();
                object[] objArray1 = new object[] { 
                    "INSERT INTO Wz_Attachfile(GUID,FileName,FileAliasName,FileExt,FileType,FilePath,Memo,Status,CreateDate,Creator,attachrelateid)VALUES('", str, "','", files.FileName, "','", files.FileAliAsName, "','", files.FileExt, "','1','", files.FilePath, "','", files.Memo, "','1',to_date('", DateTime.Now, "','yyyy-mm-dd hh24:mi:ss'),'", files.Creator,
                    "','", files.Attachrelateid, "')"
                };
                string sql = string.Concat(objArray1);
                return base.ExecuteNonQuery_Fish(sql);
            }
            catch (Exception)
            {
                return -1;
            }
        }

        public static WZ_AttachfileService Instance
        {
            get
            {
                return _Instance;
            }
        }
    }
}

