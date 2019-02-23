package com.zthc.ewms.system.warehouse.entity.guard;

import javax.persistence.*;

/**  
 * @Title: 库位标签打印
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="v_location")
public class Location {
	@Id
	@Column(name="id")
	private Integer id;
	/**----编码---**/
	@Column(name="code", columnDefinition="nvarchar2(16) NULL ")
	private String code;
    /**
     * ----名称---
     **/
    @Column(name="name", columnDefinition="nvarchar2(64) NULL ")
	private String name;
	/**
	 * ----库存名称---
	 **/
	@Column(name="parent_name", columnDefinition="nvarchar2(64) NULL ")
	private String parentName;
	/**----库存组织/账套ID---**/
    @Column(name = "ztId", columnDefinition = "Integer NULL ")
    private Integer ztId;
    /**
     * ----名称---
     **/
    @Column(name = "ztIdname", columnDefinition = "nvarchar2(64) NULL COMMENT '名称'")
    private String ztName;


	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getName(){
		return name;
	}

	public void setName(String name) {
        this.name = name;
    }

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
    }

//	public String getSheetId() {
//		return sheetId;
//	}
//
//	public void setSheetId(String sheetId) {
//		this.sheetId = sheetId;
//	}

	public String getZtName() {
		return ztName;
	}

	public void setZtName(String ztName) {
		this.ztName = ztName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Location() {

    }

    public Location(String code, String name, Integer ztId, String ztName) {

        this.code = code;
//		this.sheetId = sheetId;
        this.name = name;
        this.ztId = ztId;
        this.ztName = ztName;
    }


}