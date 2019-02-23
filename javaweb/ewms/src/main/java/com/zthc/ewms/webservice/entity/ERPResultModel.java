package com.zthc.ewms.webservice.entity;

public class ERPResultModel {

    private String code = "";
    private String erpID = "";
    private String errorText = "";
    private String name = "";
    private String sheetCode = "";
    private Boolean status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErpID() {
        return erpID;
    }

    public void setErpID(String erpID) {
        this.erpID = erpID;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSheetCode() {
        return sheetCode;
    }

    public void setSheetCode(String sheetCode) {
        this.sheetCode = sheetCode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ERPResultModel() {
    }
    public ERPResultModel(String erpID,  String sheetCode, Boolean status) {
        this.erpID = erpID;
        this.sheetCode = sheetCode;
        this.status = status;
    }
    public ERPResultModel(String code, String erpID, String errorText,  String sheetCode, Boolean status) {
        this.code = code;
        this.erpID = erpID;
        this.errorText = errorText;
        this.sheetCode = sheetCode;
        this.status = status;
    }
}
