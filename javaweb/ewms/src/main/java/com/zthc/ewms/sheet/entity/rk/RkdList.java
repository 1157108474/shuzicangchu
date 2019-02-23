package com.zthc.ewms.sheet.entity.rk;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "v_rkdmore")
public class RkdList {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**
     * ----���ݱ���---
     **/
    @Column(name = "code", columnDefinition = "nvarchar2(50) NULL COMMENT '���ݱ���'")
    private String code;
    /**
     * ----�ɹ��������---
     **/
    @Column(name = "orderNum", columnDefinition = "nvarchar2(200) NULL COMMENT '�ɹ��������'")
    private String orderNum;
    /**
     * ----��Ӧ������---
     **/
    @Column(name = "extendString1", columnDefinition = "nvarchar2(255) NULL COMMENT '��Ӧ������'")
    private String extendString1;
    /**
     * ----��������---
     **/
    @Column(name = "extendString3", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendString3;
    /**
     * ----������ID---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '������ID'")
    private Integer creator;
    /**
     * ----����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "submitTime", columnDefinition = "datetime NULL COMMENT '����ʱ��'")
    private Date submitTime;
    /**
     * ----�����֯����---
     **/
    @Column(name = "extendString2", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendString2;
    /**
     * ----���ź�---
     **/
    @Column(name = "extendString5", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
    private String extendString5;
    /**
     * ----�Ƶ���---
     **/
    @Column(name = "personName", columnDefinition = "nvarchar2(128) NULL COMMENT '�Ƶ���'")
    private String personName;
    /**
     * ----�����֯����---
     **/
    @Column(name = "ztId", columnDefinition = "Integer NULL COMMENT '�����֯����'")
    private Integer ztId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getExtendString1() {
        return extendString1;
    }

    public void setExtendString1(String extendString1) {
        this.extendString1 = extendString1;
    }

    public String getExtendString3() {
        return extendString3;
    }

    public void setExtendString3(String extendString3) {
        this.extendString3 = extendString3;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getExtendString2() {
        return extendString2;
    }

    public void setExtendString2(String extendString2) {
        this.extendString2 = extendString2;
    }

    public String getExtendString5() {
        return extendString5;
    }

    public void setExtendString5(String extendString5) {
        this.extendString5 = extendString5;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
    }

    public RkdList() {
    }
}
