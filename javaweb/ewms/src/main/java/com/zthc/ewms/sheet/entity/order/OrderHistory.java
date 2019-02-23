package com.zthc.ewms.sheet.entity.order;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "v_jslog")
public class OrderHistory {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;

    /**
     * ----唯一标识---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(36) NULL COMMENT '唯一标识'")
    private String guid;

    /**
     * ----物料编码---
     **/
    @Column(name = "materialCode", columnDefinition = "nvarchar2(50) NULL COMMENT '物料编码'")
    private String materialCode;

    /**
     * ----物料描述---
     **/
    @Column(name = "description", columnDefinition = "nvarchar2(500) NULL COMMENT '物料描述'")
    private String description;

    /**
     * ----单据ID---
     **/
    @Column(name = "orderId", columnDefinition = "Integer NULL COMMENT 'orderId'")
    private Integer orderId;

    /**
     * ----明细唯一标识---
     **/
    @Column(name = "relationGuid", columnDefinition = "nvarchar2(36) NULL COMMENT '明细唯一标识'")
    private String relationGuid;

    /**
     * ----日志信息---
     **/
    @Column(name = "content", columnDefinition = "nvarchar2(200) NULL COMMENT '日志信息'")
    private String content;

    /**
     * ----数量---
     **/
    @Column(name = "count", columnDefinition = "number(18,9) NULL COMMENT '数量'")
    private Double count;

    /**
     * ----创建时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "datetime NULL COMMENT '创建时间'")
    private Date createDate;

    /**
     * ----创建人ID---
     **/
    @Column(name = "creater", columnDefinition = "Integer NULL COMMENT '创建人ID'")
    private Integer creater;

    /**
     * ----制单人姓名---
     **/
    @Column(name = "personname", columnDefinition = "nvarchar2(128) NULL COMMENT '制单人姓名'")
    private String personname;

    /**
     * ----采购订单ID---
     **/
    @Column(name = "extendint1", columnDefinition = "Integer NULL COMMENT '采购订单ID'")
    private Integer extendint1;

    /**
     * ----日志类型---
     **/
    @Column(name = "operationType", columnDefinition = "Integer NULL COMMENT '日志类型'")
    private Integer operationType;

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

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getRelationGuid() {
        return relationGuid;
    }

    public void setRelationGuid(String relationGuid) {
        this.relationGuid = relationGuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public String getPersonname() {
        return personname;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public Integer getExtendint1() {
        return extendint1;
    }

    public void setExtendint1(Integer extendint1) {
        this.extendint1 = extendint1;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public OrderHistory() {
    }
}
