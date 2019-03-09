namespace EWMS.WinService
{
    using EWMS.Core;
    using Oracle.ManagedDataAccess.Client;
    using System;
    using System.Data;
    using System.ServiceModel;
    using System.ServiceModel.Channels;

    public class Common
    {
        private static bool _IsProductWebService = false;
        static Common()
        {
            _IsProductWebService= Convert.ToBoolean(System.Configuration.ConfigurationManager.AppSettings["IsProductWebService"]);
        }
        public static bool IsProductWebService
        {
            get
            {
                return _IsProductWebService;
            }
        }
        public static void UpdateLeve(string tableName)
        {
            OracleParameter[] parameters = new OracleParameter[1];
            OracleParameter parameter = new OracleParameter
            {
                ParameterName = "p_TableName",
                DbType = DbType.String,
                Value = tableName
            };
            parameters[0] = parameter;
            DbHelperOra.RunProcedure("UpdateLevelCode", parameters);
        }

        public static ItemsServices.TEMSClient InitItemClient()
        {
            ItemsServices.TEMSClient service = new ItemsServices.TEMSClient();
            OperationContextScope scope = new OperationContextScope(service.InnerChannel);
            var httpRequestProperty = new HttpRequestMessageProperty();
            httpRequestProperty.Headers["ClientId"] = "com.primeton.esb.warehouse.yxwms";
            httpRequestProperty.Headers["OperationCode"] = "com.primeton.esb.erp.scm.scmint.items";
            httpRequestProperty.Headers["LoginCode"] = "yxwms";
            httpRequestProperty.Headers["LoginPassword"] = "yxwms";
            OperationContext.Current.OutgoingMessageProperties[HttpRequestMessageProperty.Name] = httpRequestProperty;
            return service;

        }
        public static CategoryServices.CATEGORIESClient InitCategoryClient()
        {
            CategoryServices.CATEGORIESClient service = new CategoryServices.CATEGORIESClient();
            OperationContextScope scope = new OperationContextScope(service.InnerChannel);
            var httpRequestProperty = new HttpRequestMessageProperty();
            httpRequestProperty.Headers["ClientId"] = "com.primeton.esb.warehouse.yxwms";
            httpRequestProperty.Headers["OperationCode"] = "com.primeton.esb.erp.scm.scmint.categories";
            httpRequestProperty.Headers["LoginCode"] = "yxwms";
            httpRequestProperty.Headers["LoginPassword"] = "yxwms";
            OperationContext.Current.OutgoingMessageProperties[HttpRequestMessageProperty.Name] = httpRequestProperty;
            return service;

        }
        public static VendorServices.POVENDORSClient InitVendorClient()
        {
            VendorServices.POVENDORSClient service = new VendorServices.POVENDORSClient();
            OperationContextScope scope = new OperationContextScope(service.InnerChannel);
            var httpRequestProperty = new HttpRequestMessageProperty();
            httpRequestProperty.Headers["ClientId"] = "com.primeton.esb.warehouse.yxwms";
            httpRequestProperty.Headers["OperationCode"] = "com.primeton.esb.erp.scm.scmint.povendors";
            httpRequestProperty.Headers["LoginCode"] = "yxwms";
            httpRequestProperty.Headers["LoginPassword"] = "yxwms";
            OperationContext.Current.OutgoingMessageProperties[HttpRequestMessageProperty.Name] = httpRequestProperty;
            return service;
        }
        public static BusinessServices.cuxwmsClient InitWMSClient()
        {
            BusinessServices.cuxwmsClient service = new BusinessServices.cuxwmsClient();
            service.InnerChannel.OperationTimeout = new TimeSpan(0, 5, 0);
            OperationContextScope scope = new OperationContextScope(service.InnerChannel);
            var httpRequestProperty = new HttpRequestMessageProperty();
            httpRequestProperty.Headers["ClientId"] = "com.primeton.esb.warehouse.yxwms";
            httpRequestProperty.Headers["OperationCode"] = "com.primeton.esb.erp.erpsystem.wms.cuxws";
            httpRequestProperty.Headers["LoginCode"] = "yxwms";
            httpRequestProperty.Headers["LoginPassword"] = "yxwms";
            OperationContext.Current.OutgoingMessageProperties[HttpRequestMessageProperty.Name] = httpRequestProperty;
            return service;
        }

        public static ProductItemsServices.TEMSClient InitProductItemClient()
        {
            ProductItemsServices.TEMSClient service = new ProductItemsServices.TEMSClient();
            OperationContextScope scope = new OperationContextScope(service.InnerChannel);
            var httpRequestProperty = new HttpRequestMessageProperty();
            httpRequestProperty.Headers["ClientId"] = "com.primeton.esb.warehouse.yxwms";
            httpRequestProperty.Headers["OperationCode"] = "com.primeton.esb.erp.scm.items.items";
            httpRequestProperty.Headers["LoginCode"] = "yxwms";
            httpRequestProperty.Headers["LoginPassword"] = "wms3457";
            OperationContext.Current.OutgoingMessageProperties[HttpRequestMessageProperty.Name] = httpRequestProperty;
            return service;

        }
        public static ProductCategoryServices.CATEGORIESClient InitProductCategoryClient()
        {
            ProductCategoryServices.CATEGORIESClient service = new ProductCategoryServices.CATEGORIESClient();
            OperationContextScope scope = new OperationContextScope(service.InnerChannel);
            var httpRequestProperty = new HttpRequestMessageProperty();
            httpRequestProperty.Headers["ClientId"] = "com.primeton.esb.warehouse.yxwms";
            httpRequestProperty.Headers["OperationCode"] = "com.primeton.esb.erp.scm.categories.categories";
            httpRequestProperty.Headers["LoginCode"] = "yxwms";
            httpRequestProperty.Headers["LoginPassword"] = "wms3457";
            OperationContext.Current.OutgoingMessageProperties[HttpRequestMessageProperty.Name] = httpRequestProperty;
            return service;

        }
        public static ProductVendorServices.VENDORSClient InitProductVendorClient()
        {
            ProductVendorServices.VENDORSClient service = new ProductVendorServices.VENDORSClient();
            OperationContextScope scope = new OperationContextScope(service.InnerChannel);
            var httpRequestProperty = new HttpRequestMessageProperty();
            httpRequestProperty.Headers["ClientId"] = "com.primeton.esb.warehouse.yxwms";
            httpRequestProperty.Headers["OperationCode"] = "com.primeton.esb.erp.scm.vendors.vendors";
            httpRequestProperty.Headers["LoginCode"] = "yxwms";
            httpRequestProperty.Headers["LoginPassword"] = "wms3457";
            OperationContext.Current.OutgoingMessageProperties[HttpRequestMessageProperty.Name] = httpRequestProperty;
            return service;
        }
        public static ProductBusinessServices.ztwmsClient InitProductWMSClient()
        {
            ProductBusinessServices.ztwmsClient service = new ProductBusinessServices.ztwmsClient();
            service.InnerChannel.OperationTimeout = new TimeSpan(0, 5, 0);
            OperationContextScope scope = new OperationContextScope(service.InnerChannel);
            var httpRequestProperty = new HttpRequestMessageProperty();
            httpRequestProperty.Headers["ClientId"] = "com.primeton.esb.warehouse.yxwms";
            httpRequestProperty.Headers["OperationCode"] = "com.primeton.esb.erp.erpsystem.wms.cuxws";
            httpRequestProperty.Headers["LoginCode"] = "yxwms";
            httpRequestProperty.Headers["LoginPassword"] = "wms3457";
            OperationContext.Current.OutgoingMessageProperties[HttpRequestMessageProperty.Name] = httpRequestProperty;
            return service;
        }
    }
}

