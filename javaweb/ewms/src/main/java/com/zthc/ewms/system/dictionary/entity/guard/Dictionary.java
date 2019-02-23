package com.zthc.ewms.system.dictionary.entity.guard;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zthc.ewms.base.util.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author f
 * @version 1.0
 * @Title: 数据字典
 * @Package
 */
@Entity
@Table(name = "base_dictionary")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Dictionary {

    /**
     * ----主键---
     **/
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT '主键'")
    @SequenceGenerator(name="dictionary",sequenceName="BASEDICTIONARY_SEQUENCE",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="dictionary")
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
//    @Column(name = "parentId", columnDefinition = "Integer default 0 COMMENT '父级ID'")
//    private Integer parentId;

    @ManyToOne
    @JoinColumn(name="parentId")
    @NotFound(action= NotFoundAction.IGNORE)
    //@JsonBackReference
    private Dictionary parent;

    public Dictionary getParent() {
        return parent;
    }

    public void setParent(Dictionary parent) {
        this.parent = parent;
    }
    @Transient
    private String parentIdStr;

//    @Transient
//    private String parentName;
//    @Transient
//    private String parentCode;
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
    @Column(name = "updator", columnDefinition = "Integer NULL COMMENT '更新人'")
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
    @Column(name = "addtype", columnDefinition = "Integer default 0 COMMENT '新增类型'")
    private Integer addType;

    /*
    * 流程按钮的公用方法
    * */
    @Column(name = "btnfun")
    private String btnFun;


    public Integer getAddType() {
        return addType;
    }

    public void setAddType(Integer addType) {
        this.addType = addType;
    }

    public String getParentIdStr() {
        return parentIdStr;
    }

    @Transient
    private List<Dictionary> children = new ArrayList<Dictionary>();

    public List<Dictionary> getChildren() {
        return children;
    }

    public void setChildren(List<Dictionary> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdStr(String idStr) {
        this.id = StringUtils.isEmpty(idStr) ? null : Integer.parseInt(idStr);
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
        if(code!=null){
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

//    public Integer getParentId() {
//        return parentId;
//    }
//
//    public void setParentId(Integer parentId) {
//        this.parentId = parentId;
//    }
//
    public void setParentIdStr(String parentIdStr) {
        this.parentIdStr =parentIdStr;
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
        this.status = StringUtils.isEmpty(statusStr) ? null : Integer.parseInt(statusStr);
    }


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setSortStr(String sortStr) {
        this.sort = StringUtils.isEmpty(sortStr) ? null : Integer.parseInt(sortStr.trim());
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


    public Dictionary(Integer id,  String code, String name, Integer parentId,
                      /*Integer levelCount, String levelCode,Integer endFlag,*/
                       Integer status, Integer sort, String memo) {
        this.id = id;
//        this.guid = guid;
        this.code = code;
        this.name = name;
//        this.parentId = parentId;
        //	this.levelCount = levelCount;
        //	this.levelCode = levelCode;
//        this.endFlag = endFlag;
        this.status = status;
        this.sort = sort;
        this.memo = memo;

    }


    public Dictionary(Integer id,  String code, String name, Dictionary parent /* Integer parentId, String parentName, String parentCode*/,
                      /*Integer levelCount, String levelCode,Integer endFlag,*/
                       Integer status, Integer sort, String memo) {
        this.id = id;
//        this.guid = guid;
        this.code = code;
        this.name = name;
//        this.parentId = parentId;
//        this.parentName = parentName;
//        this.parentCode = parentCode;
         this.parent = parent;
        //this.levelCount = levelCount;
        //this.levelCode = levelCode;
//        this.endFlag = endFlag;
        this.status = status;
        this.sort = sort;
        this.memo = memo;

    }

    public Dictionary(Integer id) {
        this.id = id;

    }
    public Dictionary(Integer id,String code,String name, Dictionary parent) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.parent = parent;

    }

    public Dictionary() {

    }

    public String getBtnFun() {
        return btnFun;
    }

    public void setBtnFun(String btnFun) {
        this.btnFun = btnFun;
    }

    public Dictionary(Integer id, String code, String name, String btnFun) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.btnFun = btnFun;
    }
}
