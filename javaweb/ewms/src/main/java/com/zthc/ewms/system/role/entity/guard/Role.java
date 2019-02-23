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
 * @Title: 角色管理
 * @Package
 */
@Entity
@Table(name = "base_role")
public class Role {

    /**
     * ----角色代码---
     **/
    @Id
    @Column(name = "roleCode", columnDefinition = "nvarchar2(64) NULL COMMENT '角色代码'")
    private String roleCode;
    /**
     * ----角色名称---
     **/
    @Column(name = "roleName", columnDefinition = "nvarchar2(64) NULL COMMENT '角色名称'")
    private String roleName;
    /**
     * ----角色类型---
     **/
    @Column(name = "roleType", columnDefinition = "Integer NULL COMMENT '角色类型'")
    private Integer roleType;
    /**
     * ----启动---
     **/
    @Column(name = "enabled", columnDefinition = "Integer NULL COMMENT '启动'")
    private Integer enabled;
    /**
     * ----排序---
     **/
    @Column(name = "sort", columnDefinition = "Integer NULL COMMENT '排序'")
    private Integer sort;

    /**
     * ----备注---
     **/
    @Column(name = "remark", columnDefinition = "nvarchar2(200) NULL COMMENT '备注'")
    private String remark;
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
    private Integer updator;
    /**
     * ----更新时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "date NULL COMMENT '更新时间'")
    private Date updateDate;

    /**
     * ----角色-菜单关联表---
     **/
    @ManyToMany
    @NotFound(action = NotFoundAction.IGNORE)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)//使用hibernate注解级联保存和更新
    @JoinTable(name="BASE_ROLEMENU"//中间表表名
            ,joinColumns={@JoinColumn(name="roleCode")}//JoinColumns定义本方在中间表的主键映射
            ,inverseJoinColumns={@JoinColumn(name="menuCode")})//JoinColumns定义本方在中间表的主键映射
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