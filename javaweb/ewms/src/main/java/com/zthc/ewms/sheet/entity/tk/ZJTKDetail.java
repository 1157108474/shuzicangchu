package com.zthc.ewms.sheet.entity.tk;

import javax.persistence.*;

/**  
 * @Title: �˿ⵥ����
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_ZJTKDetails")
public class ZJTKDetail {
    @Id
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT '����'")
    private Integer id;

    @Column(name="sheetId")
    private Integer sheetId;




    /**----����ID---**/
    @Column(name="materialId", columnDefinition="Integer NULL COMMENT '����ID'")
    private Integer materialId;
    /**----���ϱ���---**/
    @Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '���ϱ���'")
    private String materialCode;
    /**----��������---**/
    @Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '��������'")
    private String materialName;

    /**----����ģ��---**/
    @Column(name="materialModel", columnDefinition="nvarchar(255) NULL COMMENT '����ģ��'")
    private String materialModel;

    /**----���Ϸ���---**/
    @Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '���Ϸ���'")
    private String materialSpecification;

    /**----storeId---**/
    @Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
    private Integer storeId;

    /**----��λID---**/
    @Column(name="storeLocationId", columnDefinition="Integer NULL COMMENT '��λID'")
    private Integer storeLocationId;
    /**----��λ����---**/
    @Column(name="storeLocationCode", columnDefinition="nvarchar(255) NULL COMMENT '��λ����'")
    private String storeLocationCode;


    /**----��λ����---**/
    @Column(name="storeLocationName", columnDefinition="nvarchar(50) NULL COMMENT '��λ����'")
    private String storeLocationName;

    /**----����---**/
    @Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '����'")
    private String description;

    /**----��λ---**/
    @Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
    private String detailUnitName;


    @Column(name="code", columnDefinition="nvarchar(20) NULL COMMENT '��λ'")
    private String code;
    /**----����˰����---**/
    @Column(name="noTaxPrice", columnDefinition="number(18,9) NULL COMMENT '����˰����'")
    private Double noTaxPrice;

    /**----��Ӧ��Id---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '��Ӧ��Id'")
    private Integer providerDepId;

    @Column(name="providerName")
    private String providerName ;

    @Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
    private Double detailCount;

    @Column(name="haveTkCount", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
    private Double haveTkCount;


    @Column(name="ztId")
    private Integer ztId;

    @Column(name="wareHouseCode")
    private String wareHouseCode ;

    @Column(name="name")
    private String name ;


    @Column(name="ownerType")
    private String ownerType ;

    @Column(name="hasCj")
    private String hasCj ;

    @Transient
    private Double tkCount;


    public Double getTkCount() {
        return tkCount;
    }

    public void setTkCount(Double tkCount) {
        this.tkCount = tkCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSheetId() {
        return sheetId;
    }

    public void setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public String getMaterialSpecification() {
        return materialSpecification;
    }

    public void setMaterialSpecification(String materialSpecification) {
        this.materialSpecification = materialSpecification;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(Integer storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    public String getStoreLocationCode() {
        return storeLocationCode;
    }

    public void setStoreLocationCode(String storeLocationCode) {
        this.storeLocationCode = storeLocationCode;
    }

    public String getStoreLocationName() {
        return storeLocationName;
    }

    public void setStoreLocationName(String storeLocationName) {
        this.storeLocationName = storeLocationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailUnitName() {
        return detailUnitName;
    }

    public void setDetailUnitName(String detailUnitName) {
        this.detailUnitName = detailUnitName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getNoTaxPrice() {
        return noTaxPrice;
    }

    public void setNoTaxPrice(Double noTaxPrice) {
        this.noTaxPrice = noTaxPrice;
    }

    public Integer getProviderDepId() {
        return providerDepId;
    }

    public void setProviderDepId(Integer providerDepId) {
        this.providerDepId = providerDepId;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Double getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Double detailCount) {
        this.detailCount = detailCount;
    }

    public Double getHaveTkCount() {
        return haveTkCount;
    }

    public void setHaveTkCount(Double haveTkCount) {
        this.haveTkCount = haveTkCount;
    }

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
    }

    public String getWareHouseCode() {
        return wareHouseCode;
    }

    public void setWareHouseCode(String wareHouseCode) {
        this.wareHouseCode = wareHouseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getHasCj() {
        return hasCj;
    }

    public void setHasCj(String hasCj) {
        this.hasCj = hasCj;
    }

    public ZJTKDetail() {
    }


}