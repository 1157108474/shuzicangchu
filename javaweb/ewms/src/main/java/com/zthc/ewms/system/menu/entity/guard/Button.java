package com.zthc.ewms.system.menu.entity.guard;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: 按钮管理
 * @Package
 */

@Entity
@Table(name = "base_button")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Button {

    /**
     * ----按钮编码---
     **/
    @Id
    @Column(name = "code", columnDefinition = "nvarchar2(16) NULL COMMENT '按钮编码'")
    private String code;
    @Transient
    private String id;
    /**
     * ----按钮名称---
     **/
    @Column(name = "name", columnDefinition = "nvarchar2(128) NULL COMMENT '按钮名称'")
    private String name;
    /**
     * ----关联菜单---
     **/
    @Column(name = "menuCode", columnDefinition = "nvarchar2(128) NULL COMMENT '关联菜单'")
    private String menuCode;

    /**
     * ----按钮图标---
     **/
    @Column(name = "icon", columnDefinition = "nvarchar2(128) NULL COMMENT '按钮图标'")
    private String icon;
    /**
     * ---按钮Url---
     */
    @Column(name = "url", columnDefinition = "nvarchar2(128) NULL COMMENT '按钮Url'")
    private String url;
    /**
     * ---权限标识---
     */
    @Column(name = "authIdentity", columnDefinition = "nvarchar2(128) NULL COMMENT '权限标识'")
    private String authIdentity;
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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return code;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthIdentity() {
        return authIdentity;
    }

    public void setAuthIdentity(String authIdentity) {
        this.authIdentity = authIdentity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Button() {
    }

    public Button(String code) {
        this.code = code;
    }

    public Button(String code, String name, String menuCode, String icon, String url, String authIdentity, Integer status, Integer sort, String memo, Integer creator, Date createDate, Integer updater, Date updateDate) {
        this.code = code;
        this.name = name;
        this.menuCode = menuCode;
        this.icon = icon;
        this.url = url;
        this.authIdentity = authIdentity;
        this.status = status;
        this.sort = sort;
        this.memo = memo;
        this.creator = creator;
        this.createDate = createDate;
        this.updater = updater;
        this.updateDate = updateDate;
    }
}