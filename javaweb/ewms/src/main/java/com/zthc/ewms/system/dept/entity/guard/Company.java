package com.zthc.ewms.system.dept.entity.guard;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: ��˾����
 * @Package
 */

@Entity
@Table(name = "base_company")
public class Company {
    /**
     * ----ID---
     **/
    @Id
    @Column(name = "COMPANYID", columnDefinition = "Integer NULL COMMENT 'ID'")
    @SequenceGenerator(name = "company", sequenceName = "BASE_COMPANY_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company")
    private Integer id;

    /**
     * ----��˾����---
     **/
    @Column(name = "COMPANYCODE", columnDefinition = "nvarchar2(16) NULL COMMENT '����'")
    private String code;
    /**
     * ----��˾����---
     **/
    @Column(name = "COMPANYNAME", columnDefinition = "nvarchar2(128) NULL COMMENT '����'")
    private String name;
    /**
     * ----��д����---
     **/
    @Column(name = "shortName", columnDefinition = "nvarchar2(128) NULL COMMENT '����'")
    private String shortName;
    /**
     * ----����---
     **/
    @Column(name = "COMPANYTYPE", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer type;
    /**
     * ----����---
     **/
    @Column(name = "sort", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer sort;
    /**
     * ----״̬---
     **/
    @Column(name = "ENABLED", columnDefinition = "Integer NULL COMMENT '״̬'")
    private Integer status;
    /**
     * ----��ע---
     **/
    @Column(name = "REMARK", columnDefinition = "nvarchar2(200) NULL COMMENT '��ע'")
    private String memo;
    /**
     * ----�ϼ�����---
     **/
    @Column(name = "parentId", columnDefinition = "Integer NULL COMMENT '�ϼ�����'")
    private Integer parentId;
    /**
     * ----�������---
     **/
    @Column(name = "levelCount", columnDefinition = "Integer NULL COMMENT '�������'")
    private Integer levelCount;
    /**
     * ----�������---
     **/
    @Column(name = "levelCode", columnDefinition = "nvarchar2(50) NULL COMMENT '�������'")
    private String levelCode;
    /**
     * ----ZTID---
     **/
    @Column(name = "ztId", columnDefinition = "Integer NULL COMMENT 'ZTID'")
    private Integer ztId;
    /**
     * ----������---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '������'")
    private Integer creator;
    /**
     * ----����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "date NULL COMMENT '����ʱ��'")
    private Date createDate;
    /**
     * ----������---
     **/
    @Column(name = "UPDATOR", columnDefinition = "Integer NULL COMMENT '������'")
    private Integer updater;
    /**
     * ----����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "date NULL COMMENT '����ʱ��'")
    private Date updateDate;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(Integer levelCount) {
        this.levelCount = levelCount;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdater() {
        return updater;
    }

    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Company() {
    }

    public Company(String code) {
        this.code = code;
    }

    public Company(Integer id, String code, String name, String shortName, Integer type, Integer sort, Integer status,
                   String memo, Integer parentId, Integer levelCount, String levelCode, Integer ztId, Integer creator,
                   Date createDate, Integer updater, Date updateDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.shortName = shortName;
        this.type = type;
        this.sort = sort;
        this.status = status;
        this.memo = memo;
        this.parentId = parentId;
        this.levelCount = levelCount;
        this.levelCode = levelCode;
        this.ztId = ztId;
        this.creator = creator;
        this.createDate = createDate;
        this.updater = updater;
        this.updateDate = updateDate;
    }
}