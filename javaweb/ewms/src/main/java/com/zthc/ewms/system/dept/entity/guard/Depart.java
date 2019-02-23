package com.zthc.ewms.system.dept.entity.guard;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: ��֯��������
 * @Package
 */

@Entity
@Table(name = "v_base_depart")
public class Depart {
    /**
     * ----ID---
     **/
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT 'ID'")
    private Integer id;
    /**
     * ----����---
     **/
    @Column(name = "type", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer type;
    /**
     * ----����---
     **/
    @Column(name = "code", columnDefinition = "nvarchar2(16) NULL COMMENT '����'")
    private String code;
    /**
     * ----����---
     **/
    @Column(name = "name", columnDefinition = "nvarchar2(128) NULL COMMENT '����'")
    private String name;
    /**
     * ----�ϼ�����---
     **/
    @ManyToOne
    @JoinColumn(name = "parentId")
    @NotFound(action = NotFoundAction.IGNORE)

    private Depart parent;

    /**
     * ----����---
     **/
    @Column(name = "sort", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer sort;
    /**
     * ----״̬---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '״̬'")
    private Integer status;
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
     * ----��˾ID---
     **/
    @Column(name = "companyId", columnDefinition = "Integer NULL COMMENT '��˾ID'")
    private Integer companyId;
    /**
     * ----��ע---
     **/
    @Column(name = "memo", columnDefinition = "nvarchar2(200) NULL COMMENT '��ע'")
    private String memo;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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


    public Depart getParent() {
        return parent;
    }

    public void setParent(Depart parent) {
        this.parent = parent;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

    public Depart() {
    }

    public Depart(Integer id) {
        this.id = id;
    }

    public Depart(Integer id, Integer type, String code, String name, Depart parentId, Integer sort, Integer status, Integer levelCount, String levelCode, Integer ztId, Integer companyId, String memo, Integer creator, Date createDate, Integer updater, Date updateDate) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.name = name;
        this.parent = parent;
        this.sort = sort;
        this.status = status;
        this.levelCount = levelCount;
        this.levelCode = levelCode;
        this.ztId = ztId;
        this.companyId = companyId;
        this.memo = memo;
        this.creator = creator;
        this.createDate = createDate;
        this.updater = updater;
        this.updateDate = updateDate;
    }
}