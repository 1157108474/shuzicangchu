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
 * @Title: �˵�����
 * @Package
 */
@Entity
@Table(name = "base_menu")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Menu {
    /**
     * ----�˵�����---
     **/
    @Id
    @Column(name = "code", columnDefinition = "nvarchar2(16) NULL COMMENT '�˵�����'")
    private String code;
    /**
     * ----�˵�����---
     **/
    @Column(name = "name", columnDefinition = "nvarchar2(128) NULL COMMENT '�˵�����'")
    private String name;
    /**
     * ----�ϼ��˵�---
     **/
    @ManyToOne
    @JoinColumn(name = "parentCode")
    @NotFound(action = NotFoundAction.IGNORE)
    private Menu menu;
    @Transient
    private String menuCode;

    /**
     * ----�˵�ͼ��---
     **/
    @Column(name = "icon", columnDefinition = "nvarchar2(128) NULL COMMENT '�˵�ͼ��'")
    private String icon;
    /**
     * ---�˵�Url---
     */
    @Column(name = "url", columnDefinition = "nvarchar2(128) NULL COMMENT '�˵�Url'")
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
     * ----����---
     **/
    @Column(name = "type", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer type;

    /**
     * ----��ťģʽ---
     **/
    @Column(name = "buttonMode", columnDefinition = "Integer NULL COMMENT '��ťģʽ'")
    private Integer buttonMode;
    /**
     * ----��ťģʽ����---
     **/
    @Transient
    private String buttonModeStr;
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
     * �����˵�
     */
    @Transient
    private List<Menu> children;

    @Transient
    private String id;
    /**
     * ----��ɫ-�˵�������---
     **/
    @ManyToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//ʹ��hibernateע�⼶������͸���
    @JoinTable(name="BASE_ROLEMENU"//�м�����
            ,joinColumns={@JoinColumn(name="menuCode")}//JoinColumns���屾�����м�������ӳ��
            ,inverseJoinColumns={@JoinColumn(name="roleCode")})//JoinColumns���屾�����м�������ӳ��
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