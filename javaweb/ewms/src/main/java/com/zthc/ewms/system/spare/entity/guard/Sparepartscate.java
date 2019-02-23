package com.zthc.ewms.system.spare.entity.guard;

import com.zthc.ewms.base.util.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: 物料分类
 * @Package
 */
@Entity
@Table(name = "base_sparepartscate")
public class Sparepartscate {

    /**
     * ----主键---
     **/
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT '主键'")
    @SequenceGenerator(name = "generator", sequenceName = "BASESPAREPARTSCATE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    private Integer id;
    /**
     * ----唯一标识---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(64) NULL COMMENT '唯一标识'")
    private String guid;
    /**
     * ----编码---
     **/
    @Column(name = "code", columnDefinition = "nvarchar2(16) NULL COMMENT '编码'")
    private String code;
    /**
     * ----名称---
     **/
    @Column(name = "name", columnDefinition = "nvarchar2(64) NULL COMMENT '名称'")
    private String name;
    /**
     * ----父级ID---
     **/
    @Column(name = "parentId", columnDefinition = "Integer NULL COMMENT '父级ID'")
    private Integer parentId;

    @Transient
    private String parentName;

    @Transient
    private String parentCode;

    /**
     * ----层级数---
     **/
    @Column(name = "levelCount", columnDefinition = "Integer NULL COMMENT '层级数'")
    private Integer levelCount;
    /**
     * ----层级编码---
     **/
    @Column(name = "levelCode", columnDefinition = "nvarchar2(64) NULL COMMENT '层级编码'")
    private String levelCode;
    /**
     * ----结束标识---
     **/
    @Column(name = "endFlag", columnDefinition = "Integer NULL COMMENT '结束标识'")
    private Integer endFlag;
    /**
     * ----状态---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '状态'")
    private Integer status;
    /**
     * ----排序---
     **/
    @Column(name = "sort", columnDefinition = "Integer NULL COMMENT '排序'")
    private Integer sort;
    /**
     * ----备注---
     **/
    @Column(name = "memo", columnDefinition = "nvarchar2(200) NULL COMMENT '备注'")
    private String memo;
    /**
     * ----创建人---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '创建人'")
    private Integer creator;
    /**
     * ----创建时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "date NULL COMMENT '创建时间'")
    private Date createDate;
    /**
     * ----更新人---
     **/
    @Column(name = "updater", columnDefinition = "Integer NULL COMMENT '更新人'")
    private Integer updater;
    /**
     * ----更新时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "date NULL COMMENT '更新时间'")
    private Date updateDate;

    /**
     * ----新增类型---
     **/
    @Column(name = "addType", columnDefinition = "Integer NULL COMMENT '新增类型'")
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