package com.zthc.ewms.sheet.entity.guard;

import com.zthc.ewms.base.util.NumberUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2018/6/14.
 */
@Entity
@Table(name = "V_JCRKDetails")
public class SheetJCRKDetails {
    @Id
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**----Ψһ��ʶ---**/
    @Column(name="guid", columnDefinition="nvarchar2(36) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;
    /**----����id---**/
    @Column(name="sheetid", columnDefinition="Integer NULL COMMENT '����id'")
    private String sheetid;
    /**----������ϸid---**/
    @Column(name="SHEETDETAILID", columnDefinition="nvarchar2(36) NULL COMMENT '������ϸid'")
    private String SHEETDETAILID;
    /**----ZTID---**/
    @Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
    private String ZTID;
    /**----���ϴ�ӡ����---**/
    @Column(name = "tagCode", columnDefinition = "nvarchar2(50) NULL COMMENT '���ϴ�ӡ����'")
    private String tagCode;
    /**----����ID---**/
    @Column(name="materialId", columnDefinition="Integer NULL COMMENT '����ID'")
    private Integer materialId;
    /*** ----���Ϸ���ID---**/
    @Column(name = "categoryId", columnDefinition = "Integer NULL COMMENT '���Ϸ���ID'")
    private Integer categoryId;
    /**----���ϱ���---**/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(50) NULL COMMENT '���ϱ���'")
    private String materialCode;
    /**----��������---**/
    @Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '��������'")
    private String materialName;
    /**----��������---**/
    @Column(name="typeName", columnDefinition="nvarchar(255) NULL COMMENT '��������'")
    private String typeName;
    /**----����Ʒ��---**/
    @Column(name="materialBrand", columnDefinition="nvarchar(255) NULL COMMENT '����Ʒ��'")
    private String materialBrand;
    /**----�����ͺ�---**/
    @Column(name="materialModel", columnDefinition="nvarchar(255) NULL COMMENT '�����ͺ�'")
    private String materialModel;
    /**----���Ϲ��---**/
    @Column(name="materialSpecification", columnDefinition="nvarchar(255) NULL COMMENT '���Ϲ��'")
    private String materialSpecification;
    /**----��ϸ����---**/
    @Column(name="detailcount", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
    private Double detailcount;
    /**----��ϸ��λ---**/
    @Column(name="detailUnitName", columnDefinition="nvarchar2(50) NULL COMMENT '��ϸ��λ'")
    private String detailUnitName;
    /**----�ⷿ---**/
    @Column(name="STOREID", columnDefinition="nvarchar2(50) NULL COMMENT '�ⷿ'")
    private Integer STOREID;
    /**----�ⷿ---**/
    @Column(name="houseName", columnDefinition="nvarchar2(50) NULL COMMENT '�ⷿ'")
    private String houseName;
    /**----��λid---**/
    @Column(name="STORELOCATIONID", columnDefinition="nvarchar2(50) NULL COMMENT '��λid'")
    private String STORELOCATIONID;
    /**----��λname---**/
    @Column(name="storeLocationName", columnDefinition="nvarchar2(50) NULL COMMENT '��λname'")
    private String storeLocationName;
    /**----��λcode---**/
    @Column(name="STORELOCATIONCODE", columnDefinition="nvarchar2(50) NULL COMMENT '��λcode'")
    private String STORELOCATIONCODE;
    /**----����˰����---**/
    @Column(name="notaxprice", columnDefinition="number(18,9) NULL COMMENT '����˰����'")
    private Double notaxprice;
    /**----��˰����---**/
    @Column(name="taxprice", columnDefinition="number(18,9) NULL COMMENT '��˰����'")
    private Double taxprice;
    /**----˰��---**/
    @Column(name = "taxRate", columnDefinition = "number(18,9) NULL COMMENT '˰��'")
    private Double taxRate;
    /**----����˰�ܽ��---**/
    @Column(name="notaxsum", columnDefinition="number(18,9) NULL COMMENT '����˰�ܽ��'")
    private Double notaxsum;
    /**----��˰�ܽ��---**/
    @Column(name="taxsum", columnDefinition="number(18,9) NULL COMMENT '����˰�ܽ��'")
    private Double taxsum;
    /**----��������---**/
    @Column(name="description", columnDefinition="nvarchar2(500) NULL COMMENT '��������'")
    private String description;
    /**----������ID---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '������ID'")
    private Integer creator;
    /**----����ʱ��---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createdate", columnDefinition="datetime NULL COMMENT '����ʱ��'")
    private Date createdate;
    /**----״̬---**/
    @Column(name="STATUS", columnDefinition="Integer NULL COMMENT '״̬'")
    private Integer STATUS;
    /**----��ע---**/
    @Column(name="MEMO", columnDefinition="nvarchar2(500) NULL COMMENT '��ע'")
    private String MEMO;
    /**----��λ---**/
    @Column(name="UNITNAME", columnDefinition="nvarchar2(50) NULL COMMENT '��λ'")
    private String UNITNAME;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSheetid() {
        return sheetid;
    }

    public void setSheetid(String sheetid) {
        this.sheetid = sheetid;
    }

    public String getZTID() {
        return ZTID;
    }

    public void setZTID(String ZTID) {
        this.ZTID = ZTID;
    }

    public String getTagCode() {
        return tagCode;
    }

    public void setTagCode(String tagCode) {
        this.tagCode = tagCode;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMaterialBrand() {
        return materialBrand;
    }

    public void setMaterialBrand(String materialBrand) {
        this.materialBrand = materialBrand;
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

    public Double getDetailcount() {
        return detailcount;
    }

    public void setDetailcount(Double detailcount) {
        this.detailcount = detailcount;
    }

    public String getDetailUnitName() {
        return detailUnitName;
    }

    public void setDetailUnitName(String detailUnitName) {
        this.detailUnitName = detailUnitName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStoreLocationName() {
        return storeLocationName;
    }

    public void setStoreLocationName(String storeLocationName) {
        this.storeLocationName = storeLocationName;
    }

    public Double getNotaxprice() {
        return notaxprice;
    }

    public void setNotaxprice(Double notaxprice) {
        this.notaxprice = notaxprice;
    }

    public String getNotaxpriceDouble() {
        return NumberUtils.getDouble(notaxprice);
    }


    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getNotaxsum() {
        return notaxsum;
    }

    public void setNotaxsum(Double notaxsum) {
        this.notaxsum = notaxsum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getSHEETDETAILID() {
        return SHEETDETAILID;
    }

    public void setSHEETDETAILID(String SHEETDETAILID) {
        this.SHEETDETAILID = SHEETDETAILID;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getSTOREID() {
        return STOREID;
    }

    public void setSTOREID(Integer STOREID) {
        this.STOREID = STOREID;
    }

    public String getSTORELOCATIONID() {
        return STORELOCATIONID;
    }

    public void setSTORELOCATIONID(String STORELOCATIONID) {
        this.STORELOCATIONID = STORELOCATIONID;
    }

    public String getSTORELOCATIONCODE() {
        return STORELOCATIONCODE;
    }

    public void setSTORELOCATIONCODE(String STORELOCATIONCODE) {
        this.STORELOCATIONCODE = STORELOCATIONCODE;
    }

    public Double getTaxprice() {
        return taxprice;
    }

    public void setTaxprice(Double taxprice) {
        this.taxprice = taxprice;
    }

    public Double getTaxsum() {
        return taxsum;
    }

    public void setTaxsum(Double taxsum) {
        this.taxsum = taxsum;
    }

    public Integer getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Integer STATUS) {
        this.STATUS = STATUS;
    }

    public String getMEMO() {
        return MEMO;
    }

    public void setMEMO(String MEMO) {
        this.MEMO = MEMO;
    }

    public String getUNITNAME() {
        return UNITNAME;
    }

    public void setUNITNAME(String UNITNAME) {
        this.UNITNAME = UNITNAME;
    }
}
