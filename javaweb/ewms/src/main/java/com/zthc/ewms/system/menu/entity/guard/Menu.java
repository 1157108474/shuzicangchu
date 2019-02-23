package com.zthc.ewms.system.menu.entity.guard;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zthc.ewms.system.role.entity.guard.Role;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author f
 * @version 1.0
 * @Title: 菜单管理
 * @Package
 */
@Entity
@Table(name = "base_menu")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Menu {
    /**
     * ----菜单编码---
     **/
    @Id
    @Column(name = "code", columnDefinition = "nvarchar2(16) NULL COMMENT '菜单编码'")
    private String code;
    /**
     * ----菜单名称---
     **/
    @Column(name = "name", columnDefinition = "nvarchar2(128) NULL COMMENT '菜单名称'")
    private String name;
    /**
     * ----上级菜单---
     **/
    @ManyToOne
    @JoinColumn(name = "parentCode")
    @NotFound(action = NotFoundAction.IGNORE)
    private Menu menu;
    @Transient
    private String menuCode;

    /**
     * ----菜单图标---
     **/
    @Column(name = "icon", columnDefinition = "nvarchar2(128) NULL COMMENT '菜单图标'")
    private String icon;
    /**
     * ---菜单Url---
     */
    @Column(name = "url", columnDefinition = "nvarchar2(128) NULL COMMENT '菜单Url'")
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
     * ----类型---
     **/
    @Column(name = "type", columnDefinition = "Integer NULL COMMENT '类型'")
    private Integer type;

    /**
     * ----按钮模式---
     **/
    @Column(name = "buttonMode", columnDefinition = "Integer NULL COMMENT '按钮模式'")
    private Integer buttonMode;
    /**
     * ----按钮模式名称---
     **/
    @Transient
    private String buttonModeStr;
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
     * 关联菜单
     */
    @Transient
    private List<Menu> children;

    @Transient
    private String id;
    /**
     * ----角色-菜单关联表---
     **/
    @ManyToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//使用hibernate注解级联保存和更新
    @JoinTable(name="BASE_ROLEMENU"//中间表表名
            ,joinColumns={@JoinColumn(name="menuCode")}//JoinColumns定义本方在中间表的主键映射
            ,inverseJoinColumns={@JoinColumn(name="roleCode")})//JoinColumns定义本方在中间表的主键映射
    private List<Role> roles;

    public String getId() {
        return code;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
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

    @JsonIgnore
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
        this.menu = new Menu(menuCode);

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getButtonMode() {
        return buttonMode;
    }

    public void setButtonMode(Integer buttonMode) {
        this.buttonMode = buttonMode;
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

    public String getButtonModeStr() {
        for (MenuButtonEnums.ButtonModeEnum e : MenuButtonEnums.ButtonModeEnum.values()) {
            if (e.getButtonMode() == this.buttonMode) {
                return e.getMeaning();
            }
        }
        return buttonModeStr;
    }

    public void setButtonModeStr(String buttonModeStr) {
        this.buttonModeStr = buttonModeStr;
    }

    public Menu() {
    }

    public Menu(String code) {
        this.code = code;
    }

    public Menu(String code, String name, Menu menu, String icon, String url, String authIdentity, Integer status, Integer sort, Integer type, Integer buttonMode, String memo, Integer creator, Date createDate, Integer updater, Date updateDate) {
        this.code = code;
        this.name = name;
        this.menu = menu;
        this.icon = icon;
        this.url = url;
        this.authIdentity = authIdentity;
        this.status = status;
        this.sort = sort;
        this.type = type;
        this.buttonMode = buttonMode;
        this.memo = memo;
        this.creator = creator;
        this.createDate = createDate;
        this.updater = updater;
        this.updateDate = updateDate;
    }
}