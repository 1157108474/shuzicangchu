package com.zthc.ewms.system.dept.entity.guard;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: ��֯����
 * @Package
 */
@Entity
@Table(name = "base_organization")
public class Organization {
    /**
     * ----ID---
     **/
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT 'ID'")
    @SequenceGenerator(name = "organization", sequenceName = "BASE_ORGANIZATION_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization")
    private Integer id;
    /**
     * ----����---
     **/
    @Column(name = "EXTENDINT2", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer type;

    /**
     * ----����---
     **/
    @Column(name = "code", columnDefinition = "nvarchar2(32) NULL COMMENT '����'")
    private String code;
    /**
     * ----����---
     **/
    @Column(name = "name", columnDefinition = "nvarchar2(128) NULL COMMENT '����'")
    private String name;
    /**
     * ----�ϼ�����---
     **/
    @Column(name = "parentId", columnDefinition = "Integer NULL COMMENT '�ϼ�����'")
    private Integer parentId;
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
    @Column(name = "updater", columnDefinition = "Integer NULL COMMENT '������'")
    private Integer updater;
    /**
     * ----����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "date NULL COMMENT '����ʱ��'")
    private Date updateDate;
    /**
     * ----GUID---
     **/
    @Column(name = "guId", columnDefinition = "nvarchar2(64) NULL COMMENT 'GUID'")
    private String guId;
    /**
     * ---������---
     **/
    @Column(name = "endFlag", columnDefinition = "Integer NULL COMMENT '������'")
    private Integer endFlag;


    /**----EXTENDINT1---**/
    @Column(name="extendint1",columnDefinition=" Integer NULL COMMENT 'EXTENDINT1'")
    private Integer extendint1;

    /**----EXTENDINT3---**/
    @Column(name="extendint3",columnDefinition=" Integer NULL COMMENT 'EXTENDINT3'")
    private Integer extendint3;





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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public String getGuId() {
        return guId;
    }

    public void setGuId(String guId) {
        this.guId = guId;
    }

    public Integer getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(Integer endFlag) {
        this.endFlag = endFlag;
    }

    public Integer getExtendint1() {
        return extendint1;
    }

    public void setExtendint1(Integer extendint1) {
        this.extendint1 = extendint1;
    }

    public Integer getExtendint3() {
        return extendint3;
    }

    public void setExtendint3(Integer extendint3) {
        this.extendint3 = extendint3;
    }

    public Organization() {
    }

    public Organization(String code) {
        this.code = code;
    }
    public Organization(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public Organization(Integer id, Integer type, String code, String name, Integer parentId, Integer sort,
                        Integer status, Integer levelCount, String levelCode, Integer ztId, Integer companyId,
                        String memo, Integer creator, Date createDate, Integer updater, Date updateDate, String guId,
                        Integer endFlag) {
        this.id = id;
        this.type = type;
        this.code = code;
        this.name = name;
        this.parentId = parentId;
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
        this.guId = guId;
        this.endFlag = endFlag;
    }
}