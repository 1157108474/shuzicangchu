package com.zthc.ewms.system.user.entity.guard;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zthc.ewms.system.role.entity.guard.Role;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

/**
 * @author f
 * @version 1.0
 * @Title: 用户-角色对应
 * @Package
 */
@Entity
@Table(name = "base_userRoles")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class UserRole {
    /**
     * ----ID---
     **/
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT 'ID'")
    @SequenceGenerator(name = "userRole", sequenceName = "BASE_USERROLE_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userRole")
    private Integer id;
    /**
     * ----用户id---
     **/
    @Column(name = "userId", columnDefinition = "Integer NULL COMMENT '用户id'")
    private Integer userId;

    /**
     * ----角色id---
     **/
    @Column(name = "roleCode", columnDefinition = "nvarchar2(64) NULL COMMENT '角色代码'")
    private String roleCode;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public UserRole() {
    }

    public UserRole(Integer userId) {
        this.userId = userId;
    }

    public UserRole(String roleCode) {
        this.roleCode = roleCode;
    }
}