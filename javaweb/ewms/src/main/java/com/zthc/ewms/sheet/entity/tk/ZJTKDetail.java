package com.zthc.ewms.sheet.entity.tk;

import javax.persistence.*;

/**  
 * @Title: 退库单管理
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_ZJTKDetails")
public class ZJTKDetail {
    @Id
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT '主键'")
    private Integer id;

    @Column(name="sheetId")
    private Integer sheetId;




    /**----物料ID---**/
    @Column(name="materialId", columnDefinition="Integer NULL COMMENT '物料ID'")
    private Integer materialId;
    /**----物料编码---**/
    @Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '物料编码'")
    private String materialCode;
    /**----物料名称---**/
    @Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '物料名称'")
    private String materialName;

    /**----物料模型---**/
    @Column(name="materialModel", columnDefinition="nvarchar(255) NULL COMMENT '物料模型'")
    private String materialModel;

    /**----物料分类---**/
    @Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '物料分类'")
    private String materialSpecification;

    /**----storeId---**/
    @Column(name="storeId", columnDefinition="Integer NULL COMMENT 'storeId'")
    private Integer storeId;

    /**----库位ID---**/
    @Column(name="storeLocationId", columnDefinition="Integer NULL COMMENT '库位ID'")
    private Integer storeLocationId;
    /**----库位编码---**/
    @Column(name="storeLocationCode", columnDefinition="nvarchar(255) NULL COMMENT '库位编码'")
    private String storeLocationCode;


    /**----库位名称---**/
    @Column(name="storeLocationName", columnDefinition="nvarchar(50) NULL COMMENT '库位名称'")
    private String storeLocationName;

    /**----描述---**/
    @Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '描述'")
    private String description;

    /**----单位---**/
    @Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '单位'")
    private String detailUnitName;


    @Column(name="code", columnDefinition="nvarchar(20) NULL COMMENT '单位'")
    private String code;
    /**----不含税单价---**/
    @Column(name="noTaxPrice", columnDefinition="number(18,9) NULL COMMENT '不含税单价'")
    private Double noTaxPrice;

    /**----供应商Id---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '供应商Id'")
    private Integer providerDepId;

    @Column(name="providerName")
    private String providerName ;

    @Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '明细数量'")
    private Double detailCount;

    @Column(name="haveTkCount", columnDefinition="number(18,9) NULL COMMENT '明细数量'")
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