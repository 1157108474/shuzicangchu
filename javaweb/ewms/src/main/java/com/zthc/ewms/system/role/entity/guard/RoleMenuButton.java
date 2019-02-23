package com.zthc.ewms.system.role.entity.guard;


import javax.persistence.*;

/**
 * @author f
 * @version 1.0
 * @Title: 角色-菜单-按钮
 * @Package
 */
@Entity
@Table(name = "base_roleMenuButton")
public class RoleMenuButton {
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT 'ID'")
    @SequenceGenerator(name = "roleMenuButton", sequenceName = "BASE_ROLEMENUBUTTON_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleMenuButton")
    private Long id;
    /**
     * ----角色编码---
     **/
    @Column(name = "roleCode", columnDefinition = "nvarchar2(64) NULL COMMENT '角色代码'")
    private String roleCode;
    /**
     * ----菜单编码---
     **/
    @Column(name = "menuCode", columnDefinition = "nvarchar2(64) NULL COMMENT '菜单代码'")
    private String menuCode;
    /**
     * ----按钮编码---
     **/
    @Column(name = "buttonCode", columnDefinition = "nvarchar2(64) NULL COMMENT '按钮代码'")
    private String buttonCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    public RoleMenuButton() {
    }
}