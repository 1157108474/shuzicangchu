package com.zthc.ewms.sheet.entity.rk;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "v_print_dbrkd")
public class DbrkPrint {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    private Integer id;
    /**
     * ----调拨时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "dbDate", columnDefinition = "datetime NULL COMMENT '调拨时间'")
    private Date dbDate;
    @Column(name = "dc_org", columnDefinition = "nvarchar2(255) NULL COMMENT ''")
    private String dcOrg;
    @Column(name = "dr_org", columnDefinition = "nvarchar2(255) NULL COMMENT ''")
    private String drOrg;
    /**
     * ----调拨单据编码---
     **/
    @Column(name = "db_code", columnDefinition = "nvarchar2(50) NULL COMMENT '调拨单据编码'")
    private String dbCode;
    /**
     * ----入库单据编码---
     **/
    @Column(name = "rk_code", columnDefinition = "nvarchar2(50) NULL COMMENT '入库单据编码'")
    private String rkCode;
    @Column(name = "dbReson", columnDefinition = "nvarchar2(50) NULL COMMENT ''")
    private String dbReson;
    @Column(name = "dbzz_person", columnDefinition = "nvarchar2(50) NULL COMMENT ''")
    private String dbzzPerson;
    @Column(name = "drzz_person", columnDefinition = "nvarchar2(50) NULL COMMENT ''")
    private String drzzPerson;
    @Column(name = "zxzr_person", columnDefinition = "nvarchar2(50) NULL COMMENT ''")
    private String zxzrPerson;
    @Column(name = "rk_person", columnDefinition = "nvarchar2(50) NULL COMMENT ''")
    private String rkPerson;
    @Column(name = "db_person", columnDefinition = "nvarchar2(50) NULL COMMENT ''")
    private String dbPerson;
    @Column(name = "houseCode", columnDefinition = "nvarchar2(50) NULL COMMENT ''")
    private String houseCode;
    /**
     * ----入库时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "rk_date", columnDefinition = "datetime NULL COMMENT '入库时间'")
    private Date rkDate;

    @Transient
    private String createDateStr;


    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public DbrkPrint() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDbDate() {
        return dbDate;
    }

    public void setDbDate(Date dbDate) {
        this.dbDate = dbDate;
    }

    public String getDcOrg() {
        return dcOrg;
    }

    public void setDcOrg(String dcOrg) {
        this.dcOrg = dcOrg;
    }

    public String getDrOrg() {
        return drOrg;
    }

    public void setDrOrg(String drOrg) {
        this.drOrg = drOrg;
    }

    public String getDbCode() {
        return dbCode;
    }

    public void setDbCode(String dbCode) {
        this.dbCode = dbCode;
    }

    public String getRkCode() {
        return rkCode;
    }

    public void setRkCode(String rkCode) {
        this.rkCode = rkCode;
    }

    public String getDbReson() {
        return dbReson;
    }

    public void setDbReson(String dbReson) {
        this.dbReson = dbReson;
    }

    public String getDbzzPerson() {
        return dbzzPerson;
    }

    public void setDbzzPerson(String dbzzPerson) {
        this.dbzzPerson = dbzzPerson;
    }

    public String getDrzzPerson() {
        return drzzPerson;
    }

    public void setDrzzPerson(String drzzPerson) {
        this.drzzPerson = drzzPerson;
    }

    public String getZxzrPerson() {
        return zxzrPerson;
    }

    public void setZxzrPerson(String zxzrPerson) {
        this.zxzrPerson = zxzrPerson;
    }

    public String getRkPerson() {
        return rkPerson;
    }

    public void setRkPerson(String rkPerson) {
        this.rkPerson = rkPerson;
    }

    public String getDbPerson() {
        return dbPerson;
    }

    public void setDbPerson(String dbPerson) {
        this.dbPerson = dbPerson;
    }

    public String getHouseCode() {
        return houseCode;
    }

    public void setHouseCode(String houseCode) {
        this.houseCode = houseCode;
    }

    public Date getRkDate() {
        return rkDate;
    }

    public void setRkDate(Date rkDate) {
        this.rkDate = rkDate;
    }
}
