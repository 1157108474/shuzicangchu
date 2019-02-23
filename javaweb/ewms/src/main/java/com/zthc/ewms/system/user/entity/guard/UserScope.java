package com.zthc.ewms.system.user.entity.guard;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: �û�-��Χ��Ӧ
 * @Package
 */
@Entity
@Table(name = "base_person_scope")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserScope {
    /**
     * ----ID---
     **/
    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT 'ID'")
    @SequenceGenerator(name = "userScope", sequenceName = "BASE_PERSONSCOPE_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userScope")
    private Integer id;
    /**
     * ----�û�id---
     **/
    @Column(name = "personId", columnDefinition = "Integer NULL COMMENT '�û�id'")
    private Integer personId;
    /**
     * ----����---
     **/
    @Column(name = "scopeType", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer scopeType;
    /**
     * ----��ΧID---
     **/
    @Column(name = "scopeId", columnDefinition = "Integer NULL COMMENT '��ΧID'")
    private Integer scopeId;
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
     * ----ZTID---
     **/
    @Column(name = "ztId", columnDefinition = "Integer NULL COMMENT 'ZTID'")
    private Integer ztId;
    /**
     * ---GUID---
     */
    @Column(name = "guId", columnDefinition = "nvarchar2(64) NULL COMMENT 'GUID'")
    private String guId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getScopeType() {
        return scopeType;
    }

    public void setScopeType(Integer scopeType) {
        this.scopeType = scopeType;
    }

    public Integer getScopeId() {
        return scopeId;
    }

    public void setScopeId(Integer scopeId) {
        this.scopeId = scopeId;
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

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
    }

    public String getGuId() {
        return guId;
    }

    public void setGuId(String guId) {
        this.guId = guId;
    }

    public UserScope() {
    }

    public UserScope(Integer scopeId) {
        this.scopeId = scopeId;
    }

    public UserScope(Integer id, Integer personId, Integer scopeType, Integer scopeId, Integer creator, Date createDate, Integer ztId, String guId) {
        this.id = id;
        this.personId = personId;
        this.scopeType = scopeType;
        this.scopeId = scopeId;
        this.creator = creator;
        this.createDate = createDate;
        this.ztId = ztId;
        this.guId = guId;
    }
}