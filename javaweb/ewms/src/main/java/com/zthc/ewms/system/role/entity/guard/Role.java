package com.zthc.ewms.system.role.entity.guard;

import com.zthc.ewms.system.menu.entity.guard.Button;
import com.zthc.ewms.system.menu.entity.guard.Menu;
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
 * @Title: ��ɫ����
 * @Package
 */
@Entity
@Table(name = "base_role")
public class Role {

    /**
     * ----��ɫ����---
     **/
    @Id
    @Column(name = "roleCode", columnDefinition = "nvarchar2(64) NULL COMMENT '��ɫ����'")
    private String roleCode;
    /**
     * ----��ɫ����---
     **/
    @Column(name = "roleName", columnDefinition = "nvarchar2(64) NULL COMMENT '��ɫ����'")
    private String roleName;
    /**
     * ----��ɫ����---
     **/
    @Column(name = "roleType", columnDefinition = "Integer NULL COMMENT '��ɫ����'")
    private Integer roleType;
    /**
     * ----����---
     **/
    @Column(name = "enabled", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer enabled;
    /**
     * ----����---
     **/
    @Column(name = "sort", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer sort;

    /**
     * ----��ע---
     **/
    @Column(name = "remark", columnDefinition = "nvarchar2(200) NULL COMMENT '��ע'")
    private String remark;
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
    @Column(name = "updator", columnDefinition = "Integer NULL COMMENT '������'")
    private Integer updator;
    /**
     * ----����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "date NULL COMMENT '����ʱ��'")
    private Date updateDate;

    /**
     * ----��ɫ-�˵�������---
     **/
    @ManyToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//ʹ��hibernateע�⼶������͸���
    @JoinTable(name="BASE_ROLEMENU"//�м�����
            ,joinColumns={@JoinColumn(name="roleCode")}//JoinColumns���屾�����м�������ӳ��
            ,inverseJoinColumns={@JoinColumn(name="menuCode")})//JoinColumns���屾�����м�������ӳ��
    private List<Menu> menus;



    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Role() {
    }

    public Role(String roleCode,String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }
    public Role(String roleCode) {
        this.roleCode = roleCode;
    }

    public Role(String roleCode, String roleName, Integer roleType, Integer enabled, Integer sort, String remark,
                Integer creator, Date createDate, Integer updator, Date updateDate) {
        this.roleCode = roleCode;
        this.roleName = roleName;
        this.roleType = roleType;
        this.enabled = enabled;
        this.sort = sort;
        this.remark = remark;
        this.creator = creator;
        this.createDate = createDate;
        this.updator = updator;
        this.updateDate = updateDate;
    }
}