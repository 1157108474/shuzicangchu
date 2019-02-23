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
 * @Title: ��ť����
 * @Package
 */

@Entity
@Table(name = "base_button")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Button {

    /**
     * ----��ť����---
     **/
    @Id
    @Column(name = "code", columnDefinition = "nvarchar2(16) NULL COMMENT '��ť����'")
    private String code;
    @Transient
    private String id;
    /**
     * ----��ť����---
     **/
    @Column(name = "name", columnDefinition = "nvarchar2(128) NULL COMMENT '��ť����'")
    private String name;
    /**
     * ----�����˵�---
     **/
    @Column(name = "menuCode", columnDefinition = "nvarchar2(128) NULL COMMENT '�����˵�'")
    private String menuCode;

    /**
     * ----��ťͼ��---
     **/
    @Column(name = "icon", columnDefinition = "nvarchar2(128) NULL COMMENT '��ťͼ��'")
    private String icon;
    /**
     * ---��ťUrl---
     */
    @Column(name = "url", columnDefinition = "nvarchar2(128) NULL COMMENT '��ťUrl'")
    private String url;
    /**
     * ---Ȩ�ޱ�ʶ---
     */
    @Column(name = "authIdentity", columnDefinition = "nvarchar2(128) NULL COMMENT 'Ȩ�ޱ�ʶ'")
    private String authIdentity;
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