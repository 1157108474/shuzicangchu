﻿//------------------------------------------------------------------------------
// <auto-generated>
//     此代码由工具生成。
//     运行时版本:4.0.30319.42000
//
//     对此文件的更改可能会导致不正确的行为，并且如果
//     重新生成代码，这些更改将会丢失。
// </auto-generated>
//------------------------------------------------------------------------------

namespace EWMS.WinService.CategoryServices {
    using System.Runtime.Serialization;
    using System;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.CollectionDataContractAttribute(Name="CategoriesRCategoriesRecUserArray", Namespace="http://com/oracle/apps/cuxmdata/categories/CATEGORIES.wsdl/types/", ItemName="CategoriesRCategoriesRecUser")]
    [System.SerializableAttribute()]
    public class CategoriesRCategoriesRecUserArray : System.Collections.Generic.List<EWMS.WinService.CategoryServices.CategoriesRCategoriesRecUser> {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="CategoriesRCategoriesRecUser", Namespace="http://com/oracle/apps/cuxmdata/categories/CATEGORIES.wsdl/types/")]
    [System.SerializableAttribute()]
    public partial class CategoriesRCategoriesRecUser : EWMS.WinService.CategoryServices.CategoriesRCategoriesRecBase {
        
        private string supplierEnabledFlagField;
        
        private string categoryDescriptionField;
        
        private string attributeCategoryField;
        
        private System.Nullable<decimal> organizationIdField;
        
        private System.Nullable<System.DateTime> lastUpdateDateField;
        
        private string attribute4Field;
        
        private System.Nullable<System.DateTime> creationDateField;
        
        private string attribute5Field;
        
        private string attribute6Field;
        
        private string creationByNameField;
        
        private string lbbmField;
        
        private string validateFlagField;
        
        private string categorySetDescriptionField;
        
        private string attribute1Field;
        
        private string lastUpdatedByNameField;
        
        private string categoryConcatSegsField;
        
        private string attribute2Field;
        
        private string attribute3Field;
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true)]
        public string supplierEnabledFlag {
            get {
                return this.supplierEnabledFlagField;
            }
            set {
                if ((object.ReferenceEquals(this.supplierEnabledFlagField, value) != true)) {
                    this.supplierEnabledFlagField = value;
                    this.RaisePropertyChanged("supplierEnabledFlag");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=1)]
        public string categoryDescription {
            get {
                return this.categoryDescriptionField;
            }
            set {
                if ((object.ReferenceEquals(this.categoryDescriptionField, value) != true)) {
                    this.categoryDescriptionField = value;
                    this.RaisePropertyChanged("categoryDescription");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=2)]
        public string attributeCategory {
            get {
                return this.attributeCategoryField;
            }
            set {
                if ((object.ReferenceEquals(this.attributeCategoryField, value) != true)) {
                    this.attributeCategoryField = value;
                    this.RaisePropertyChanged("attributeCategory");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=3)]
        public System.Nullable<decimal> organizationId {
            get {
                return this.organizationIdField;
            }
            set {
                if ((this.organizationIdField.Equals(value) != true)) {
                    this.organizationIdField = value;
                    this.RaisePropertyChanged("organizationId");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=4)]
        public System.Nullable<System.DateTime> lastUpdateDate {
            get {
                return this.lastUpdateDateField;
            }
            set {
                if ((this.lastUpdateDateField.Equals(value) != true)) {
                    this.lastUpdateDateField = value;
                    this.RaisePropertyChanged("lastUpdateDate");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=5)]
        public string attribute4 {
            get {
                return this.attribute4Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute4Field, value) != true)) {
                    this.attribute4Field = value;
                    this.RaisePropertyChanged("attribute4");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=6)]
        public System.Nullable<System.DateTime> creationDate {
            get {
                return this.creationDateField;
            }
            set {
                if ((this.creationDateField.Equals(value) != true)) {
                    this.creationDateField = value;
                    this.RaisePropertyChanged("creationDate");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=7)]
        public string attribute5 {
            get {
                return this.attribute5Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute5Field, value) != true)) {
                    this.attribute5Field = value;
                    this.RaisePropertyChanged("attribute5");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=8)]
        public string attribute6 {
            get {
                return this.attribute6Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute6Field, value) != true)) {
                    this.attribute6Field = value;
                    this.RaisePropertyChanged("attribute6");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=9)]
        public string creationByName {
            get {
                return this.creationByNameField;
            }
            set {
                if ((object.ReferenceEquals(this.creationByNameField, value) != true)) {
                    this.creationByNameField = value;
                    this.RaisePropertyChanged("creationByName");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=10)]
        public string lbbm {
            get {
                return this.lbbmField;
            }
            set {
                if ((object.ReferenceEquals(this.lbbmField, value) != true)) {
                    this.lbbmField = value;
                    this.RaisePropertyChanged("lbbm");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=11)]
        public string validateFlag {
            get {
                return this.validateFlagField;
            }
            set {
                if ((object.ReferenceEquals(this.validateFlagField, value) != true)) {
                    this.validateFlagField = value;
                    this.RaisePropertyChanged("validateFlag");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=12)]
        public string categorySetDescription {
            get {
                return this.categorySetDescriptionField;
            }
            set {
                if ((object.ReferenceEquals(this.categorySetDescriptionField, value) != true)) {
                    this.categorySetDescriptionField = value;
                    this.RaisePropertyChanged("categorySetDescription");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=13)]
        public string attribute1 {
            get {
                return this.attribute1Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute1Field, value) != true)) {
                    this.attribute1Field = value;
                    this.RaisePropertyChanged("attribute1");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=14)]
        public string lastUpdatedByName {
            get {
                return this.lastUpdatedByNameField;
            }
            set {
                if ((object.ReferenceEquals(this.lastUpdatedByNameField, value) != true)) {
                    this.lastUpdatedByNameField = value;
                    this.RaisePropertyChanged("lastUpdatedByName");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=15)]
        public string categoryConcatSegs {
            get {
                return this.categoryConcatSegsField;
            }
            set {
                if ((object.ReferenceEquals(this.categoryConcatSegsField, value) != true)) {
                    this.categoryConcatSegsField = value;
                    this.RaisePropertyChanged("categoryConcatSegs");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=16)]
        public string attribute2 {
            get {
                return this.attribute2Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute2Field, value) != true)) {
                    this.attribute2Field = value;
                    this.RaisePropertyChanged("attribute2");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=17)]
        public string attribute3 {
            get {
                return this.attribute3Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute3Field, value) != true)) {
                    this.attribute3Field = value;
                    this.RaisePropertyChanged("attribute3");
                }
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="CategoriesRCategoriesRecBase", Namespace="http://com/oracle/apps/cuxmdata/categories/CATEGORIES.wsdl/types/")]
    [System.SerializableAttribute()]
    [System.Runtime.Serialization.KnownTypeAttribute(typeof(EWMS.WinService.CategoryServices.CategoriesRCategoriesRecUser))]
    public partial class CategoriesRCategoriesRecBase : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://com/oracle/apps/cuxmdata/categories/CATEGORIES.wsdl", ConfigurationName="CategoryServices.CATEGORIES")]
    public interface CATEGORIES {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://com/oracle/apps/cuxmdata/categories/CATEGORIES.wsdl/invCategoriesWsInt", ReplyAction="*")]
        EWMS.WinService.CategoryServices.invCategoriesWsIntResponse invCategoriesWsInt(EWMS.WinService.CategoryServices.invCategoriesWsIntRequest request);
        
        // CODEGEN: 正在生成消息协定，应为该操作具有多个返回值。
        [System.ServiceModel.OperationContractAttribute(Action="http://com/oracle/apps/cuxmdata/categories/CATEGORIES.wsdl/invCategoriesWsInt", ReplyAction="*")]
        System.Threading.Tasks.Task<EWMS.WinService.CategoryServices.invCategoriesWsIntResponse> invCategoriesWsIntAsync(EWMS.WinService.CategoryServices.invCategoriesWsIntRequest request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.MessageContractAttribute(WrapperName="invCategoriesWsInt", WrapperNamespace="http://com/oracle/apps/cuxmdata/categories/CATEGORIES.wsdl", IsWrapped=true)]
    public partial class invCategoriesWsIntRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public string pCompany;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=1)]
        public string pRequestDate;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=2)]
        public EWMS.WinService.CategoryServices.CategoriesRCategoriesRecUserArray xCategoriesRec_out;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=3)]
        public string xReturnMessage_out;
        
        public invCategoriesWsIntRequest() {
        }
        
        public invCategoriesWsIntRequest(string pCompany, string pRequestDate, EWMS.WinService.CategoryServices.CategoriesRCategoriesRecUserArray xCategoriesRec_out, string xReturnMessage_out) {
            this.pCompany = pCompany;
            this.pRequestDate = pRequestDate;
            this.xCategoriesRec_out = xCategoriesRec_out;
            this.xReturnMessage_out = xReturnMessage_out;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.MessageContractAttribute(WrapperName="invCategoriesWsIntResponse", WrapperNamespace="http://com/oracle/apps/cuxmdata/categories/CATEGORIES.wsdl", IsWrapped=true)]
    public partial class invCategoriesWsIntResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public EWMS.WinService.CategoryServices.CategoriesRCategoriesRecUserArray xCategoriesRec_out;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=1)]
        public string xReturnMessage_out;
        
        public invCategoriesWsIntResponse() {
        }
        
        public invCategoriesWsIntResponse(EWMS.WinService.CategoryServices.CategoriesRCategoriesRecUserArray xCategoriesRec_out, string xReturnMessage_out) {
            this.xCategoriesRec_out = xCategoriesRec_out;
            this.xReturnMessage_out = xReturnMessage_out;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface CATEGORIESChannel : EWMS.WinService.CategoryServices.CATEGORIES, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class CATEGORIESClient : System.ServiceModel.ClientBase<EWMS.WinService.CategoryServices.CATEGORIES>, EWMS.WinService.CategoryServices.CATEGORIES {
        
        public CATEGORIESClient() {
        }
        
        public CATEGORIESClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public CATEGORIESClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public CATEGORIESClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public CATEGORIESClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        EWMS.WinService.CategoryServices.invCategoriesWsIntResponse EWMS.WinService.CategoryServices.CATEGORIES.invCategoriesWsInt(EWMS.WinService.CategoryServices.invCategoriesWsIntRequest request) {
            return base.Channel.invCategoriesWsInt(request);
        }
        
        public void invCategoriesWsInt(string pCompany, string pRequestDate, ref EWMS.WinService.CategoryServices.CategoriesRCategoriesRecUserArray xCategoriesRec_out, ref string xReturnMessage_out) {
            EWMS.WinService.CategoryServices.invCategoriesWsIntRequest inValue = new EWMS.WinService.CategoryServices.invCategoriesWsIntRequest();
            inValue.pCompany = pCompany;
            inValue.pRequestDate = pRequestDate;
            inValue.xCategoriesRec_out = xCategoriesRec_out;
            inValue.xReturnMessage_out = xReturnMessage_out;
            EWMS.WinService.CategoryServices.invCategoriesWsIntResponse retVal = ((EWMS.WinService.CategoryServices.CATEGORIES)(this)).invCategoriesWsInt(inValue);
            xCategoriesRec_out = retVal.xCategoriesRec_out;
            xReturnMessage_out = retVal.xReturnMessage_out;
        }
        
        public System.Threading.Tasks.Task<EWMS.WinService.CategoryServices.invCategoriesWsIntResponse> invCategoriesWsIntAsync(EWMS.WinService.CategoryServices.invCategoriesWsIntRequest request) {
            return base.Channel.invCategoriesWsIntAsync(request);
        }
    }
}
