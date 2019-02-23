package com.zthc.ewms.system.spare.entity.guard;

import com.zthc.ewms.base.util.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: ���Ϸ���
 * @Package
 */
@Entity
@Table(name = "base_sparepartscate")
public class Sparepartscate {

    /**
     * ----����---
     **/
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT '����'")
    @SequenceGenerator(name = "generator", sequenceName = "BASESPAREPARTSCATE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    private Integer id;
    /**
     * ----Ψһ��ʶ---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;
    /**
     * ----����---
     **/
    @Column(name = "code", columnDefinition = "nvarchar2(16) NULL COMMENT '����'")
    private String code;
    /**
     * ----����---
     **/
    @Column(name = "name", columnDefinition = "nvarchar2(64) NULL COMMENT '����'")
    private String name;
    /**
     * ----����ID---
     **/
    @Column(name = "parentId", columnDefinition = "Integer NULL COMMENT '����ID'")
    private Integer parentId;

    @Transient
    private String parentName;

    @Transient
    private String parentCode;

    /**
     * ----�㼶��---
     **/
    @Column(name = "levelCount", columnDefinition = "Integer NULL COMMENT '�㼶��'")
    private Integer levelCount;
    /**
     * ----�㼶����---
     **/
    @Column(name = "levelCode", columnDefinition = "nvarchar2(64) NULL COMMENT '�㼶����'")
    private String levelCode;
    /**
     * ----������ʶ---
     **/
    @Column(name = "endFlag", columnDefinition = "Integer NULL COMMENT '������ʶ'")
    private Integer endFlag;
    /**
     * ----״̬---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '״̬'")
    private Integer status;
    /**
     * ----����---
     **/
    @Column(name = "sort", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer sort;
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
     * ----��������---
     **/
    @Column(name = "addType", columnDefinition = "Integer NULL COMMENT '��������'")
    private Integer addType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdStr(String str) {
        this.id = StringUtils.isEmpty(str) ? null : Integer.parseInt(str);
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {

        if (code != null) {
            code = code.trim();
        }
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

    public void setParentIdStr(String parentId) {
        this.parentId = Integer.parseInt(parentId);
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

    public Integer getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(Integer endFlag) {
        this.endFlag = endFlag;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setStatusStr(String statusStr) {
        this.status = Integer.parseInt(statusStr);
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setSortStr(String sortStr) {
        if (!StringUtils.isEmpty(sortStr)) {
            this.sort = Integer.parseInt(sortStr);
        }
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

    public Integer getAddType() {
        return addType;
    }

    public void setAddType(Integer addType) {
        this.addType = addType;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Sparepartscate() {

    }

    public Sparepartscate(Integer id, String guid, String code, String name, Integer parentId, Integer levelCount, String levelCode, Integer endFlag, Integer status, Integer sort, String memo, Integer creator, Date createDate, Integer updater, Date updateDate, Integer addType) {
        this.id = id;
        this.guid = guid;
        this.code = code;
        this.name = name;
        this.parentId = parentId;
        this.levelCount = levelCount;
        this.levelCode = levelCode;
        this.endFlag = endFlag;
        this.status = status;
        this.sort = sort;
        this.memo = memo;
        this.creator = creator;
        this.createDate = createDate;
        this.updater = updater;
        this.updateDate = updateDate;
        this.addType = addType;
    }

    public Sparepartscate(Integer id, String code, String name) {
        this.id = id;
//		this.guid = guid;
        this.code = code;
        this.name = name;
//		this.parentId = parentId;
//		this.parentCode = parentCode;
//		this.parentName = parentName;

    }

    public Sparepartscate(Integer id, String code, String name, Integer parentId, String parentCode, String parentName, Integer status, Integer sort, String memo) {
        this.id = id;
//		this.guid = guid;
        this.code = code;
        this.name = name;
        this.parentId = parentId;
        this.parentCode = parentCode;
        this.parentName = parentName;
        this.status = status;
        this.sort = sort;
        this.memo = memo;

    }

    public Sparepartscate(Integer id, String code, String name, Integer status, Integer sort, String memo) {
        this.id = id;
//		this.guid = guid;
        this.code = code;
        this.name = name;

        this.status = status;
        this.sort = sort;
        this.memo = memo;

    }
    public Sparepartscate(String code, String name) {
        this.code = code;
        this.name = name;


    }
}