﻿//------------------------------------------------------------------------------
// <auto-generated>
//     此代码由工具生成。
//     运行时版本:4.0.30319.42000
//
//     对此文件的更改可能会导致不正确的行为，并且如果
//     重新生成代码，这些更改将会丢失。
// </auto-generated>
//------------------------------------------------------------------------------

namespace EWMS.WinService.ItemsServices {
    using System.Runtime.Serialization;
    using System;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.CollectionDataContractAttribute(Name="ItemsRInvItemsRecUserArray", Namespace="http://com/oracle/apps/cuxmdata/ITEMS.wsdl/types/", ItemName="ItemsRInvItemsRecUser")]
    [System.SerializableAttribute()]
    public class ItemsRInvItemsRecUserArray : System.Collections.Generic.List<EWMS.WinService.ItemsServices.ItemsRInvItemsRecUser> {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="ItemsRInvItemsRecUser", Namespace="http://com/oracle/apps/cuxmdata/ITEMS.wsdl/types/")]
    [System.SerializableAttribute()]
    public partial class ItemsRInvItemsRecUser : EWMS.WinService.ItemsServices.ItemsRInvItemsRecBase {
        
        private string inventoryItemStatusCodeField;
        
        private string trackingQuantityIndField;
        
        private System.Nullable<decimal> organizationIdField;
        
        private System.Nullable<decimal> categorySetIdField;
        
        private string ontPricingQtySourceField;
        
        private System.Nullable<decimal> orgIdField;
        
        private string createdByNameField;
        
        private string uomField;
        
        private string secondaryDefaultIndField;
        
        private System.Nullable<decimal> costOfSalesAccountField;
        
        private string attribute24Field;
        
        private string attribute25Field;
        
        private string attribute22Field;
        
        private string attribute23Field;
        
        private string itemNumberField;
        
        private System.Nullable<decimal> inventoryItemIdField;
        
        private string attribute21Field;
        
        private string categoryConcatSegsField;
        
        private string lastUpdatedByNameField;
        
        private string attribute26Field;
        
        private string categoryDescriptionField;
        
        private string attribute29Field;
        
        private System.Nullable<decimal> categoryIdField;
        
        private string secondaryUomCodeField;
        
        private System.Nullable<decimal> salesAccountField;
        
        private string taxCodeField;
        
        private System.Nullable<System.DateTime> lastUpdateDateField;
        
        private System.Nullable<System.DateTime> creationDateField;
        
        private string itemDescriptionField;
        
        private System.Nullable<decimal> minMinmaxQuantityField;
        
        private string categorySetDescriptionField;
        
        private string attribute1Field;
        
        private string attribute2Field;
        
        private System.Nullable<decimal> maxMinmaxQuantityField;
        
        private string attribute3Field;
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true)]
        public string inventoryItemStatusCode {
            get {
                return this.inventoryItemStatusCodeField;
            }
            set {
                if ((object.ReferenceEquals(this.inventoryItemStatusCodeField, value) != true)) {
                    this.inventoryItemStatusCodeField = value;
                    this.RaisePropertyChanged("inventoryItemStatusCode");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true)]
        public string trackingQuantityInd {
            get {
                return this.trackingQuantityIndField;
            }
            set {
                if ((object.ReferenceEquals(this.trackingQuantityIndField, value) != true)) {
                    this.trackingQuantityIndField = value;
                    this.RaisePropertyChanged("trackingQuantityInd");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=2)]
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
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=3)]
        public System.Nullable<decimal> categorySetId {
            get {
                return this.categorySetIdField;
            }
            set {
                if ((this.categorySetIdField.Equals(value) != true)) {
                    this.categorySetIdField = value;
                    this.RaisePropertyChanged("categorySetId");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=4)]
        public string ontPricingQtySource {
            get {
                return this.ontPricingQtySourceField;
            }
            set {
                if ((object.ReferenceEquals(this.ontPricingQtySourceField, value) != true)) {
                    this.ontPricingQtySourceField = value;
                    this.RaisePropertyChanged("ontPricingQtySource");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=5)]
        public System.Nullable<decimal> orgId {
            get {
                return this.orgIdField;
            }
            set {
                if ((this.orgIdField.Equals(value) != true)) {
                    this.orgIdField = value;
                    this.RaisePropertyChanged("orgId");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=6)]
        public string createdByName {
            get {
                return this.createdByNameField;
            }
            set {
                if ((object.ReferenceEquals(this.createdByNameField, value) != true)) {
                    this.createdByNameField = value;
                    this.RaisePropertyChanged("createdByName");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=7)]
        public string uom {
            get {
                return this.uomField;
            }
            set {
                if ((object.ReferenceEquals(this.uomField, value) != true)) {
                    this.uomField = value;
                    this.RaisePropertyChanged("uom");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=8)]
        public string secondaryDefaultInd {
            get {
                return this.secondaryDefaultIndField;
            }
            set {
                if ((object.ReferenceEquals(this.secondaryDefaultIndField, value) != true)) {
                    this.secondaryDefaultIndField = value;
                    this.RaisePropertyChanged("secondaryDefaultInd");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=9)]
        public System.Nullable<decimal> costOfSalesAccount {
            get {
                return this.costOfSalesAccountField;
            }
            set {
                if ((this.costOfSalesAccountField.Equals(value) != true)) {
                    this.costOfSalesAccountField = value;
                    this.RaisePropertyChanged("costOfSalesAccount");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=10)]
        public string attribute24 {
            get {
                return this.attribute24Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute24Field, value) != true)) {
                    this.attribute24Field = value;
                    this.RaisePropertyChanged("attribute24");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=11)]
        public string attribute25 {
            get {
                return this.attribute25Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute25Field, value) != true)) {
                    this.attribute25Field = value;
                    this.RaisePropertyChanged("attribute25");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=12)]
        public string attribute22 {
            get {
                return this.attribute22Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute22Field, value) != true)) {
                    this.attribute22Field = value;
                    this.RaisePropertyChanged("attribute22");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=13)]
        public string attribute23 {
            get {
                return this.attribute23Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute23Field, value) != true)) {
                    this.attribute23Field = value;
                    this.RaisePropertyChanged("attribute23");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=14)]
        public string itemNumber {
            get {
                return this.itemNumberField;
            }
            set {
                if ((object.ReferenceEquals(this.itemNumberField, value) != true)) {
                    this.itemNumberField = value;
                    this.RaisePropertyChanged("itemNumber");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=15)]
        public System.Nullable<decimal> inventoryItemId {
            get {
                return this.inventoryItemIdField;
            }
            set {
                if ((this.inventoryItemIdField.Equals(value) != true)) {
                    this.inventoryItemIdField = value;
                    this.RaisePropertyChanged("inventoryItemId");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=16)]
        public string attribute21 {
            get {
                return this.attribute21Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute21Field, value) != true)) {
                    this.attribute21Field = value;
                    this.RaisePropertyChanged("attribute21");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=17)]
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
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=18)]
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
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=19)]
        public string attribute26 {
            get {
                return this.attribute26Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute26Field, value) != true)) {
                    this.attribute26Field = value;
                    this.RaisePropertyChanged("attribute26");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=20)]
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
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=21)]
        public string attribute29 {
            get {
                return this.attribute29Field;
            }
            set {
                if ((object.ReferenceEquals(this.attribute29Field, value) != true)) {
                    this.attribute29Field = value;
                    this.RaisePropertyChanged("attribute29");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=22)]
        public System.Nullable<decimal> categoryId {
            get {
                return this.categoryIdField;
            }
            set {
                if ((this.categoryIdField.Equals(value) != true)) {
                    this.categoryIdField = value;
                    this.RaisePropertyChanged("categoryId");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=23)]
        public string secondaryUomCode {
            get {
                return this.secondaryUomCodeField;
            }
            set {
                if ((object.ReferenceEquals(this.secondaryUomCodeField, value) != true)) {
                    this.secondaryUomCodeField = value;
                    this.RaisePropertyChanged("secondaryUomCode");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=24)]
        public System.Nullable<decimal> salesAccount {
            get {
                return this.salesAccountField;
            }
            set {
                if ((this.salesAccountField.Equals(value) != true)) {
                    this.salesAccountField = value;
                    this.RaisePropertyChanged("salesAccount");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=25)]
        public string taxCode {
            get {
                return this.taxCodeField;
            }
            set {
                if ((object.ReferenceEquals(this.taxCodeField, value) != true)) {
                    this.taxCodeField = value;
                    this.RaisePropertyChanged("taxCode");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=26)]
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
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=27)]
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
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=28)]
        public string itemDescription {
            get {
                return this.itemDescriptionField;
            }
            set {
                if ((object.ReferenceEquals(this.itemDescriptionField, value) != true)) {
                    this.itemDescriptionField = value;
                    this.RaisePropertyChanged("itemDescription");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=29)]
        public System.Nullable<decimal> minMinmaxQuantity {
            get {
                return this.minMinmaxQuantityField;
            }
            set {
                if ((this.minMinmaxQuantityField.Equals(value) != true)) {
                    this.minMinmaxQuantityField = value;
                    this.RaisePropertyChanged("minMinmaxQuantity");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=30)]
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
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=31)]
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
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=32)]
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
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=33)]
        public System.Nullable<decimal> maxMinmaxQuantity {
            get {
                return this.maxMinmaxQuantityField;
            }
            set {
                if ((this.maxMinmaxQuantityField.Equals(value) != true)) {
                    this.maxMinmaxQuantityField = value;
                    this.RaisePropertyChanged("maxMinmaxQuantity");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute(IsRequired=true, Order=34)]
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
    [System.Runtime.Serialization.DataContractAttribute(Name="ItemsRInvItemsRecBase", Namespace="http://com/oracle/apps/cuxmdata/ITEMS.wsdl/types/")]
    [System.SerializableAttribute()]
    [System.Runtime.Serialization.KnownTypeAttribute(typeof(EWMS.WinService.ItemsServices.ItemsRInvItemsRecUser))]
    public partial class ItemsRInvItemsRecBase : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
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
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://com/oracle/apps/cuxmdata/ITEMS.wsdl", ConfigurationName="ItemsServices.ITEMS")]
    public interface ITEMS {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://com/oracle/apps/cuxmdata/ITEMS.wsdl/invItemsWsInt", ReplyAction="*")]
        EWMS.WinService.ItemsServices.invItemsWsIntResponse invItemsWsInt(EWMS.WinService.ItemsServices.invItemsWsIntRequest request);
        
        // CODEGEN: 正在生成消息协定，应为该操作具有多个返回值。
        [System.ServiceModel.OperationContractAttribute(Action="http://com/oracle/apps/cuxmdata/ITEMS.wsdl/invItemsWsInt", ReplyAction="*")]
        System.Threading.Tasks.Task<EWMS.WinService.ItemsServices.invItemsWsIntResponse> invItemsWsIntAsync(EWMS.WinService.ItemsServices.invItemsWsIntRequest request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.MessageContractAttribute(WrapperName="invItemsWsInt", WrapperNamespace="http://com/oracle/apps/cuxmdata/ITEMS.wsdl", IsWrapped=true)]
    public partial class invItemsWsIntRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public string pCompany;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=1)]
        public string pRequestDate;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=2)]
        public EWMS.WinService.ItemsServices.ItemsRInvItemsRecUserArray xInvItemsRec_out;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=3)]
        public string xReturnMessage_out;
        
        public invItemsWsIntRequest() {
        }
        
        public invItemsWsIntRequest(string pCompany, string pRequestDate, EWMS.WinService.ItemsServices.ItemsRInvItemsRecUserArray xInvItemsRec_out, string xReturnMessage_out) {
            this.pCompany = pCompany;
            this.pRequestDate = pRequestDate;
            this.xInvItemsRec_out = xInvItemsRec_out;
            this.xReturnMessage_out = xReturnMessage_out;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.MessageContractAttribute(WrapperName="invItemsWsIntResponse", WrapperNamespace="http://com/oracle/apps/cuxmdata/ITEMS.wsdl", IsWrapped=true)]
    public partial class invItemsWsIntResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=0)]
        public EWMS.WinService.ItemsServices.ItemsRInvItemsRecUserArray xInvItemsRec_out;
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="", Order=1)]
        public string xReturnMessage_out;
        
        public invItemsWsIntResponse() {
        }
        
        public invItemsWsIntResponse(EWMS.WinService.ItemsServices.ItemsRInvItemsRecUserArray xInvItemsRec_out, string xReturnMessage_out) {
            this.xInvItemsRec_out = xInvItemsRec_out;
            this.xReturnMessage_out = xReturnMessage_out;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface ITEMSChannel : EWMS.WinService.ItemsServices.ITEMS, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class TEMSClient : System.ServiceModel.ClientBase<EWMS.WinService.ItemsServices.ITEMS>, EWMS.WinService.ItemsServices.ITEMS {
        
        public TEMSClient() {
        }
        
        public TEMSClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public TEMSClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public TEMSClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public TEMSClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        EWMS.WinService.ItemsServices.invItemsWsIntResponse EWMS.WinService.ItemsServices.ITEMS.invItemsWsInt(EWMS.WinService.ItemsServices.invItemsWsIntRequest request) {
            return base.Channel.invItemsWsInt(request);
        }
        
        public void invItemsWsInt(string pCompany, string pRequestDate, ref EWMS.WinService.ItemsServices.ItemsRInvItemsRecUserArray xInvItemsRec_out, ref string xReturnMessage_out) {
            EWMS.WinService.ItemsServices.invItemsWsIntRequest inValue = new EWMS.WinService.ItemsServices.invItemsWsIntRequest();
            inValue.pCompany = pCompany;
            inValue.pRequestDate = pRequestDate;
            inValue.xInvItemsRec_out = xInvItemsRec_out;
            inValue.xReturnMessage_out = xReturnMessage_out;
            EWMS.WinService.ItemsServices.invItemsWsIntResponse retVal = ((EWMS.WinService.ItemsServices.ITEMS)(this)).invItemsWsInt(inValue);
            xInvItemsRec_out = retVal.xInvItemsRec_out;
            xReturnMessage_out = retVal.xReturnMessage_out;
        }
        
        public System.Threading.Tasks.Task<EWMS.WinService.ItemsServices.invItemsWsIntResponse> invItemsWsIntAsync(EWMS.WinService.ItemsServices.invItemsWsIntRequest request) {
            return base.Channel.invItemsWsIntAsync(request);
        }
    }
}
