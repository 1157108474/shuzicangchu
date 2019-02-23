package com.zthc.ewms.sheet.entity.apply;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_PRINT_LLDETAILS")
public class ApplyDetailsPrint {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**
     * ----????ID---
     **/
    @Column(name = "sheetId", columnDefinition = "Integer NULL COMMENT '????ID'")
    private Integer sheetId;
    /**
     * ----序列号---
     **/
    @Column(name = "snCode", columnDefinition = "nvarchar(50) NULL COMMENT '??????'")
    private String snCode;
    /**
     * ----物料编码---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar(64) NULL COMMENT '???????'")
    private String materialCode;
    /**
     * ----入库数量---
     **/
    @Column(name = "detailCount", columnDefinition = "number(18,9) NULL COMMENT '???????'")
    private Double detailCount;

    @Column(name = "extendstring2", columnDefinition = "nvarchar(255) NULL ")
    private String extendString2;

    /**
     * 单位
     */
    @Column(name = "detailUnitName", columnDefinition = "nvarchar(20) NULL COMMENT '????????'")
    private String detailUnitName;

    /**
     * 物料描述
     */
    @Column(name = "description", columnDefinition = "nvarchar(500) NULL COMMENT '????'")
    private String description;

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

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public Double getDetailCount() {
        return detailCount;
    }

    public void setDetailCount(Double detailCount) {
        this.detailCount = detailCount;
    }

    public String getExtendString2() {
        return extendString2;
    }

    public void setExtendString2(String extendString2) {
        this.extendString2 = extendString2;
    }

    public String getDetailUnitName() {
        return detailUnitName;
    }

    public void setDetailUnitName(String detailUnitName) {
        this.detailUnitName = detailUnitName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ApplyDetailsPrint() {
    }
}
