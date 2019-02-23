package com.zthc.ewms.system.warehouse.entity.guard;

import com.zthc.ewms.base.util.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: 库房库区
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="base_warehouse")
public class WareHouse {
	/**----主键---**/
	@Id
	@Column(name="id", columnDefinition="Integer NULL COMMENT '主键'")
	@SequenceGenerator(name="generator",sequenceName="BASEWAREHOUSE_SEQUENCE",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	private Integer id;
	/**----唯一标识---**/
	@Column(name="guid", columnDefinition="nvarchar2(64) NULL COMMENT '唯一标识'")
	private String guid;
	/**----编码---**/
	@Column(name="code", columnDefinition="nvarchar2(16) NULL COMMENT '编码'")
	private String code;
	/**----名称---**/
	@Column(name="name", columnDefinition="nvarchar2(64) NULL COMMENT '名称'")
	private String name;
	/**----父级ID---**/
	@Column(name="parentId", columnDefinition="Integer NULL COMMENT '父级ID'")
	private Integer parentId;
	@Transient
	private String parentName;
	@Transient
	private  String parentCode;
	/**----层级数---**/
	@Column(name="levelCount", columnDefinition="Integer NULL COMMENT '层级数'")
	private Integer levelCount;
	/**----层级编码---**/
	@Column(name="levelCode", columnDefinition="nvarchar2(64) NULL COMMENT '层级编码'")
	private String levelCode;
	/**----结束标识---**/
	@Column(name="endFlag", columnDefinition="Integer NULL COMMENT '结束标识'")
	private Integer endFlag;
	/**----状态---**/
	@Column(name="status", columnDefinition="Integer NULL COMMENT '状态'")
	private Integer status;
	/**----排序---**/
	@Column(name="sort", columnDefinition="Integer NULL COMMENT '排序'")
	private Integer sort;
	/**----备注---**/
	@Column(name="memo", columnDefinition="nvarchar2(200) NULL COMMENT '备注'")
	private String memo;
	

  	/**----性质---**/
    @Column(name="property", columnDefinition="Integer NULL COMMENT '性质'")
    private Integer property;
  	/**----库存组织/账套ID---**/
    @Column(name = "ztId", columnDefinition = "Integer NULL COMMENT '库存组织/账套ID'")
    private Integer ztId;

    @Transient
	private String ztName;
  	/**----erp唯一标识---**/
    @Column(name="extendint1", columnDefinition="Integer NULL COMMENT 'erp唯一标识'")
    private Integer erpId;
//  	/**----库房编码---**/
//    @Column(name="wareHouseCode", columnDefinition="varchar(200) NULL COMMENT '库房编码'")
//    private String wareHouseCode;
  	/**----新增类型---**/
    @Column(name="addType", columnDefinition="Integer NULL COMMENT '新增类型'")
    private Integer addType;
	/**----创建人---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人'")
	private Integer creator;
	/**----创建时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="createDate", columnDefinition="date NULL COMMENT '创建时间'")
	private Date createDate;
	/**----更新人---**/
    @Column(name = "updater", columnDefinition = "Integer NULL COMMENT '更新人'")
    private Integer updater;
    /**----更新时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="updateDate", columnDefinition="date NULL COMMENT '更新时间'")
	private Date updateDate;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}
	public void setIdStr(String idStr) {
		this.id = StringUtils.isEmpty(idStr) ? null : Integer.parseInt(idStr);
	}

	public String getGuid(){
		return guid;
	}

	public void setGuid(String guid){
		this.guid = guid;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		if(code!=null){
			code = code.toUpperCase();
		}
		this.code = code;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Integer getParentId(){
		return parentId;
	}
	public void setParentIdStr(String parentIdStr) {
		this.parentId = StringUtils.isEmpty(parentIdStr) ? null : Integer.parseInt(parentIdStr);
	}

	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}

	public Integer getLevelCount(){
		return levelCount;
	}

	public void setLevelCount(Integer levelCount){
		this.levelCount = levelCount;
	}
	public void setLevelCountStr(String levelCountStr) {
		this.levelCount = StringUtils.isEmpty(levelCountStr) ? null : Integer.parseInt(levelCountStr);
	}


	public String getLevelCode(){
		return levelCode;
	}

	public void setLevelCode(String levelCode){
		this.levelCode = levelCode;
	}

	public Integer getEndFlag(){
		return endFlag;
	}

	public void setEndFlag(Integer endFlag){
		this.endFlag = endFlag;
	}

	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}
	public void setStatusStr(String statusStr) {
		this.status = StringUtils.isEmpty(statusStr) ? null : Integer.parseInt(statusStr);
	}
	public Integer getSort(){
		return sort;
	}

	public void setSort(Integer sort){
		this.sort = sort;
	}
	public void setSortStr(String sortStr) {
		this.sort = StringUtils.isEmpty(sortStr) ? null : Integer.parseInt(sortStr.trim());
	}

	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}


	 
  	public Integer getProperty(){   
    	return property;   
  	} 
    
  	public void setProperty(Integer property){   
    	this.property = property;   
  	}

    public void setPropertyStr(String propertyStr) {

        this.property = Integer.parseInt(propertyStr);
    }

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
    }

    public void setZtIdStr(String idStr) {

        this.ztId = StringUtils.isEmpty(idStr) ? null : Integer.parseInt(idStr);
    }

	public Integer getErpId(){
    	return erpId;   
  	} 
    
  	public void setErpId(Integer erpId){   
    	this.erpId = erpId;   
  	}  
    
//  	public String getWareHouseCode(){
//    	return wareHouseCode;
//  	}
//
//  	public void setWareHouseCode(String wareHouseCode){
//    	this.wareHouseCode = wareHouseCode;
//  	}
//
  	public Integer getAddType(){   
    	return addType;   
  	} 
    
  	public void setAddType(Integer addType){   
    	this.addType = addType;   
  	}

	
	public Integer getCreator(){
		return creator;
	}

	public void setCreator(Integer creator){
		this.creator = creator;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Integer getUpdater(){
		return updater;
	}

	public void setUpdater(Integer updater){
		this.updater = updater;
	}

	public Date getUpdateDate(){
		return updateDate;
	}

	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}

	public String getZtName() {
		return ztName;
	}

	public void setZtName(String ztName) {
		this.ztName = ztName;
	}

	public WareHouse() {
	
  	}

    public WareHouse(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;

    }

    public WareHouse(Integer id, String code, String name, String ztName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.ztName = ztName;
    }
	public WareHouse(Integer id, String code, String name, Integer parentId) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.parentId = parentId;
	}


    public WareHouse(Integer id, String code, String name, String ztName, Integer parentId, String parentCode) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.ztName = ztName;
        this.parentId = parentId;
        this.parentCode = parentCode;

    }
    public WareHouse(Integer id, String code, String name, Integer parentId, String parentName ,String parentCode) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.parentId = parentId;
		this.parentName = parentName;
		this.parentCode = parentCode;

	}

	public WareHouse(Integer id, String code, String name,Integer status,String memo) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.status =status;
		this.memo = memo;
	}

	public WareHouse(Integer id, String code, String name,  String parentCode, String parentName, Integer levelCount,Integer property,Integer sort, Integer status, String memo, String ztName) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.parentName = parentName;
		this.parentCode = parentCode;
		this.levelCount = levelCount;
		this.status = status;
		this.memo = memo;
		this.property = property;
		this.ztName= ztName;
		this.sort = sort;
	}

    public WareHouse(Integer id, String code, String name, Integer parentId, String parentCode, String parentName,
                     Integer levelCount, Integer property, Integer sort, Integer status, String memo, Integer ztId,
                     Integer erpId,String ztName) {
        this.id = id;
        this.code = code;
		this.name = name;
		this.parentName = parentName;
		this.parentCode = parentCode;
		this.levelCount = levelCount;
		this.status = status;
		this.memo = memo;
		this.property = property;
		this.ztName= ztName;
        this.ztId = ztId;
        this.sort = sort;
        this.parentId = parentId;
        this.erpId = erpId;
	}
}