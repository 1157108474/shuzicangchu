package com.zthc.ewms.system.user.entity.guard;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.zthc.ewms.system.dept.entity.guard.Company;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.useDep.entity.UseDep;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author f
 * @version 1.0
 * @Title: �û�����
 * @Package
 */
@Entity
@Table(name = "base_person")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements Comparable<User> {

    @Id
    @Column(name = "id", columnDefinition = "Integer NULL COMMENT '����'")
    @SequenceGenerator(name = "user", sequenceName = "BASEPERSON_SEQUENCE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
    private Integer id;
    /**
     * ---GUID---
     */
    @Column(name = "guId", columnDefinition = "nvarchar2(64) NULL COMMENT 'GUID'")
    private String guId;
    /**
     * ----�û�����---
     **/
    @Column(name = "userType", columnDefinition = "Integer NULL COMMENT '�û�����'")
    private Integer userType;
    /**
     * ---Ա�����
     */
    @Column(name = "code", columnDefinition = "nvarchar2(64) NULL COMMENT 'Ա�����'")
    private String code;
    /**
     * ----�û�����---
     **/
    @Column(name = "name", columnDefinition = "nvarchar2(64) NULL COMMENT '�û�����'")
    private String name;
    /**
     * ----����ƴ��---
     **/
    @Column(name = "spell", columnDefinition = "nvarchar2(16) NULL COMMENT '����ƴ��'")
    private String spell;
    /**
     * ---����---
     */
    @Column(name = "email", columnDefinition = "nvarchar2(64) NULL COMMENT '����'")
    private String email;
    /**
     * ---��ϵ��ʽ---
     */
    @Column(name = "phone", columnDefinition = "nvarchar2(16) NULL COMMENT '��ϵ��ʽ'")
    private String phone;
    /**
     * ---QQ---
     */
    @Column(name = "qq", columnDefinition = "nvarchar2(64) NULL COMMENT 'QQ'")
    private String qq;
    /**
     * ---�Ա�---
     */
    @Column(name = "sex", columnDefinition = "char(16) NULL COMMENT '�Ա�'")
    private String sex;

    /**
     * ----��¼����---
     **/
    @Column(name = "passWord", columnDefinition = "nvarchar2(128) NULL COMMENT '��¼����'")
    private String passWord;
    /**
     * ----��Կ---
     **/
    @Column(name = "secretkey", columnDefinition = "varchar(64) NULL COMMENT '��Կ'")
    private String secretkey;
    /**
     * ----����---
     **/
    @ManyToOne
    @JoinColumn(name = "departId")
    @NotFound(action = NotFoundAction.IGNORE)
    private Depart parent;
    /**
     * ----����Id---
     **/
    @Transient
    private Integer parentId;
    /**
     * ----��������---
     **/
    @Transient
    private String parentName;
    /**
     * ----��˾---
     **/
    @ManyToOne
    @JoinColumn(name = "companyId")
    @NotFound(action = NotFoundAction.IGNORE)
    private Company company;
    @Transient
    private Integer companyId;
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
     * ----�Ƿ����---
     **/
    @Column(name = "isAudit", columnDefinition = "Integer NULL COMMENT '����'")
    private Integer isAudit;

    /**
     * ----������Userʵ����---
     **/
    @ManyToOne(targetEntity = User.class)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "auditby")
    private User auditbyUser;
    /**
     * ----������ID---
     **/
    @Transient
    private Integer auditby;
    /**
     * ----������---
     **/
    @Transient
    private String auditbyName;

    /**
     * ----���ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "auditTime", columnDefinition = "date NULL COMMENT '���ʱ��'")
    private Date auditTime;

    /**
     * ----�Ƿ񵥵��¼---
     **/
    @Column(name = "isSingleLogin", columnDefinition = "Integer NULL COMMENT '�Ƿ񵥵��¼'")
    private Integer isSingleLogin;

    /**
     * ----�Ƿ�����---
     **/
    @Column(name = "isOnline", columnDefinition = "Integer NULL COMMENT '�Ƿ�����'")
    private Integer isOnline;
    /**
     * ----��¼����---
     **/
    @Column(name = "loginCount", columnDefinition = "Integer NULL COMMENT '��¼����'")
    private Integer loginCount;
    /**
     * ----��¼ʱ��---
     **/
    @Column(name = "loginTime", columnDefinition = "Date NULL COMMENT '��¼ʱ��'")
    private Date loginTime;
    /**
     * ----��¼IP---
     **/
    @Column(name = "loginIp", columnDefinition = "varchar(64) NULL COMMENT '��¼IP'")
    private String loginIp;
    /**
     * ----��¼����---
     **/
    @Column(name = "loginCity", columnDefinition = "varchar(64) NULL COMMENT '��¼����'")
    private String loginCity;
    /**
     * ----����޸�����ʱ��---
     **/
    @Column(name = "lastChangePassWord", columnDefinition = "Date NULL COMMENT '����޸�����ʱ��'")
    private Date lastChangePassWord;


    /**
     * ----������Userʵ����---
     **/
    @ManyToOne(targetEntity = User.class)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "updater")
    private User updaterUser;
    /**
     * ----������Id---
     **/
    @Transient
    private Integer updater;
    /**
     * ----������---
     **/
    @Transient
    private String updaterName;

    /**
     * ----����ʱ��---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "date NULL COMMENT '����ʱ��'")
    private Date updateDate;

    /**
     * ----������Userʵ����---
     **/
    @ManyToOne(targetEntity = User.class)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "creator")
    private User createUser;
    /**
     * ----������ID---
     **/
    @Transient
    private Integer creator;
    /**
     * ----������---
     **/
    @Transient
    private String createName;

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
     * ----���ҡ�����---
     **/
    @ManyToOne
    @JoinColumn(name = "OfficesId")
    @NotFound(action = NotFoundAction.IGNORE)
//	@Transient
    private UseDep offices;
    @Transient
    private Integer officesId;
    @Transient
    private String officesName;
    /**
     * ----���֤�ţ�ԭ��EXTENDSTRING2---
     **/
    @Column(name = "EXTENDSTRING2", columnDefinition = "varchar(200) NULL COMMENT '���֤��'")
    private String identityCode;
    /**----ExtendInt1---**/
    @Column(name="extendint1",columnDefinition=" Integer NULL COMMENT 'ExtendInt1'")
    private Integer extendint1;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuId() {
        return guId;
    }

    public void setGuId(String guId) {
        this.guId = guId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public Depart getParent() {
        return parent;
    }

    public void setParent(Depart parent) {
        this.parent = parent;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    @JsonIgnore
    public User getAuditbyUser() {
        return auditbyUser;
    }

    public void setAuditbyUser(User auditbyUser) {
        this.auditbyUser = auditbyUser;
    }

    public Integer getAuditby() {
        return auditby;
    }

    public void setAuditby(Integer auditby) {
        this.auditby = auditby;
    }

    public String getAuditbyName() {
        if (null != this.auditbyUser) {
            return this.auditbyUser.getName();
        } else {
            return auditbyName;
        }
    }

    public void setAuditbyName(String auditbyName) {
        this.auditbyName = auditbyName;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getIsSingleLogin() {
        return isSingleLogin;
    }

    public void setIsSingleLogin(Integer isSingleLogin) {
        this.isSingleLogin = isSingleLogin;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginCity() {
        return loginCity;
    }

    public void setLoginCity(String loginCity) {
        this.loginCity = loginCity;
    }

    public Date getLastChangePassWord() {
        return lastChangePassWord;
    }

    public void setLastChangePassWord(Date lastChangePassWord) {
        this.lastChangePassWord = lastChangePassWord;
    }

    @JsonIgnore
    public User getUpdaterUser() {
        return updaterUser;
    }

    public void setUpdaterUser(User updaterUser) {
        this.updaterUser = updaterUser;
    }

    public Integer getUpdater() {
        return updater;
    }

    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public String getUpdaterName() {
        if (null != this.updaterUser) {
            return this.updaterUser.getName();
        } else {
            return updaterName;
        }
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @JsonIgnore
    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Integer getCreator() {

        return creator;
    }

    public void setCreator(Integer creator) {
        this.createUser = new User(creator);
        this.creator = creator;
    }

    public String getCreateName() {
        if (null != this.createUser) {
            return this.createUser.getName();
        } else {
            return createName;
        }
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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

    public UseDep getOffices() {
        return offices;
    }

    public void setOffices(UseDep offices) {
        this.offices = offices;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public Integer getParentId() {
        return null == parentId ? 0 : parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCompanyId() {
        return null == companyId ? 0 : companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getOfficesId() {
        return null == officesId ? 0 : officesId;
    }

    public void setOfficesId(Integer officesId) {
        this.officesId = officesId;
    }

    public Integer getExtendint1() {
        return extendint1;
    }

    public void setExtendint1(Integer extendint1) {
        this.extendint1 = extendint1;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getOfficesName() {
        return officesName;
    }

    public void setOfficesName(String officesName) {
        this.officesName = officesName;
    }

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }
    public User(String code) {
        this.code = code;
    }
    public User(Integer id,String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }
    public User(Integer id,Integer ztId,String guId) {
        this.id = id;
        this.ztId = ztId;
        this.guId = guId;
    }

    public User(Integer id, String guId, Integer userType, String code, String name, String spell, String email,
                String phone, String qq, String sex, String passWord, String secretkey, Depart parent, Company company,
                Integer status, Integer sort, String memo, Integer isAudit, Integer auditby, Date auditTime,
                Integer isSingleLogin, Integer isOnline, Integer loginCount, Date loginTime, String loginIp,
                String loginCity, Date lastChangePassWord, Integer creator, Date createDate, Integer updater,
                Date updateDate, Integer ztId, UseDep offices, String identityCode) {
        this.id = id;
        this.guId = guId;
        this.userType = userType;
        this.code = code;
        this.name = name;
        this.spell = spell;
        this.email = email;
        this.phone = phone;
        this.qq = qq;
        this.sex = sex;
        this.passWord = passWord;
        this.secretkey = secretkey;
        this.parent = parent;
        this.company = company;
        this.status = status;
        this.sort = sort;
        this.memo = memo;
        this.isAudit = isAudit;
        this.auditby = auditby;
        this.auditTime = auditTime;
        this.isSingleLogin = isSingleLogin;
        this.isOnline = isOnline;
        this.loginCount = loginCount;
        this.loginTime = loginTime;
        this.loginIp = loginIp;
        this.loginCity = loginCity;
        this.lastChangePassWord = lastChangePassWord;
        this.creator = creator;
        this.createDate = createDate;
        this.updater = updater;
        this.updateDate = updateDate;
        this.ztId = ztId;
        this.offices = offices;
        this.identityCode = identityCode;
    }

    @Override
    public int compareTo(User o) {
        int flag = this.id.compareTo(o.id);
        if (flag == 0) {
            flag = this.userType - o.userType;
        }
        return flag;
    }
}