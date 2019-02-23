package com.zthc.ewms.system.role.entity.guard;


import javax.persistence.*;

/**
 * @author f
 * @version 1.0
 * @Title: ��ɫ-�˵�-��ť
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
     * ----��ɫ����---
     **/
    @Column(name = "roleCode", columnDefinition = "nvarchar2(64) NULL COMMENT '��ɫ����'")
    private String roleCode;
    /**
     * ----�˵�����---
     **/
    @Column(name = "menuCode", columnDefinition = "nvarchar2(64) NULL COMMENT '�˵�����'")
    private String menuCode;
    /**
     * ----��ť����---
     **/
    @Column(name = "buttonCode", columnDefinition = "nvarchar2(64) NULL COMMENT '��ť����'")
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