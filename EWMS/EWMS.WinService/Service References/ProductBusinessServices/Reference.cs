﻿//------------------------------------------------------------------------------
// <auto-generated>
//     此代码由工具生成。
//     运行时版本:4.0.30319.42000
//
//     对此文件的更改可能会导致不正确的行为，并且如果
//     重新生成代码，这些更改将会丢失。
// </auto-generated>
//------------------------------------------------------------------------------

namespace EWMS.WinService.ProductBusinessServices {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://com/oracle/apps/ztwms/Ztwms.wsdl", ConfigurationName="ProductBusinessServices.ztwms")]
    public interface ztwms {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://com/oracle/apps/ztwms/Ztwms.wsdl/invokews", ReplyAction="*")]
        EWMS.WinService.ProductBusinessServices.invokewsResponse invokews(EWMS.WinService.ProductBusinessServices.invokewsRequest request);
        
        // CODEGEN: 正在生成消息协定，应为该操作具有多个返回值。
        [System.ServiceModel.OperationContractAttribute(Action="http://com/oracle/apps/ztwms/Ztwms.wsdl/invokews", ReplyAction="*")]
        System.Threading.Tasks.Task<EWMS.WinService.ProductBusinessServices.invokewsResponse> invokewsAsync(EWMS.WinService.ProductBusinessServices.invokewsRequest request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.MessageContractAttribute(WrapperName="invokews", WrapperNamespace="http://com/oracle/apps/ztwms/Ztwms.wsdl", IsWrapped=true)]
    public partial class invokewsRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public string pIfaceCode;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=1)]
        public string pBatchNumber;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=2)]
        public string pRequestData;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=3)]
        public string xReturnCode_out;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=4)]
        public string xReturnMesg_out;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=5)]
        public string xResponseData_out;
        
        public invokewsRequest() {
        }
        
        public invokewsRequest(string pIfaceCode, string pBatchNumber, string pRequestData, string xReturnCode_out, string xReturnMesg_out, string xResponseData_out) {
            this.pIfaceCode = pIfaceCode;
            this.pBatchNumber = pBatchNumber;
            this.pRequestData = pRequestData;
            this.xReturnCode_out = xReturnCode_out;
            this.xReturnMesg_out = xReturnMesg_out;
            this.xResponseData_out = xResponseData_out;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.MessageContractAttribute(WrapperName="invokewsResponse", WrapperNamespace="http://com/oracle/apps/ztwms/Ztwms.wsdl", IsWrapped=true)]
    public partial class invokewsResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public string xReturnCode_out;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=1)]
        public string xReturnMesg_out;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=2)]
        public string xResponseData_out;
        
        public invokewsResponse() {
        }
        
        public invokewsResponse(string xReturnCode_out, string xReturnMesg_out, string xResponseData_out) {
            this.xReturnCode_out = xReturnCode_out;
            this.xReturnMesg_out = xReturnMesg_out;
            this.xResponseData_out = xResponseData_out;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface ztwmsChannel : EWMS.WinService.ProductBusinessServices.ztwms, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class ztwmsClient : System.ServiceModel.ClientBase<EWMS.WinService.ProductBusinessServices.ztwms>, EWMS.WinService.ProductBusinessServices.ztwms {
        
        public ztwmsClient() {
        }
        
        public ztwmsClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public ztwmsClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ztwmsClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ztwmsClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        EWMS.WinService.ProductBusinessServices.invokewsResponse EWMS.WinService.ProductBusinessServices.ztwms.invokews(EWMS.WinService.ProductBusinessServices.invokewsRequest request) {
            return base.Channel.invokews(request);
        }
        
        public void invokews(string pIfaceCode, string pBatchNumber, string pRequestData, ref string xReturnCode_out, ref string xReturnMesg_out, ref string xResponseData_out) {
            EWMS.WinService.ProductBusinessServices.invokewsRequest inValue = new EWMS.WinService.ProductBusinessServices.invokewsRequest();
            inValue.pIfaceCode = pIfaceCode;
            inValue.pBatchNumber = pBatchNumber;
            inValue.pRequestData = pRequestData;
            inValue.xReturnCode_out = xReturnCode_out;
            inValue.xReturnMesg_out = xReturnMesg_out;
            inValue.xResponseData_out = xResponseData_out;
            EWMS.WinService.ProductBusinessServices.invokewsResponse retVal = ((EWMS.WinService.ProductBusinessServices.ztwms)(this)).invokews(inValue);
            xReturnCode_out = retVal.xReturnCode_out;
            xReturnMesg_out = retVal.xReturnMesg_out;
            xResponseData_out = retVal.xResponseData_out;
        }
        
        public System.Threading.Tasks.Task<EWMS.WinService.ProductBusinessServices.invokewsResponse> invokewsAsync(EWMS.WinService.ProductBusinessServices.invokewsRequest request) {
            return base.Channel.invokewsAsync(request);
        }
    }
}
