package com.zthc.ewms.system.provider.entity;

import java.util.Date;

import javax.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;


/**
 * @Title: 供应商管理
 * @Package
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="BASE_PROVIDER")
public class Provider {

	/**----id---**/
	@Id
	@Column(name="id", columnDefinition="Integer NOT NULL ")
	@SequenceGenerator(name="generator",sequenceName="BASEPROVIDER_SEQUENCE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	private Integer id;
	/**----唯一标识---**/
	@Column(name="guid", columnDefinition="nvarchar2(64) NULL ")
	private String guid;
	/**----供应商编码---**/
	@Column(name="code", columnDefinition="nvarchar2(200) NULL ")
	private String code;
	/**----供应商名称---**/
	@Column(name="name", columnDefinition="nvarchar2(200) NULL ")
	private String name;
	/**----供应商地址---**/
	@Column(name="address", columnDefinition="nvarchar2(200) NULL ")
	private String address;
	/**----邮政编码---**/
    @Column(name = "zipCode", columnDefinition = "Integer NULL ")
    private Integer zipCode;
	/**----联系人---**/
    @Column(name = "contactPerson", columnDefinition = "nvarchar2(64) NULL ")
    private String contactPerson;
	/**----联系电话---**/
    @Column(name = "contractPhone", columnDefinition = "nvarchar2(64) NULL ")
    private String contractPhone;
	/**----传真---**/
	@Column(name="fax", columnDefinition="nvarchar2(64) NULL ")
	private String fax;
	/**----邮箱---**/
	@Column(name="email", columnDefinition="nvarchar2(64) NULL ")
	private String email;
	/**----是否启用---**/
	@Column(name="status", columnDefinition="Integer NULL ")
	private Integer status;
	/**----排序---**/
	@Column(name="sort", columnDefinition="Integer NULL ")
	private Integer sort;
	/**----备注---**/
	@Column(name="memo", columnDefinition="nvarchar2(200) NULL ")
	private String memo;
	/**----ERP ID---**/
    @Column(name = "extendInt1", columnDefinition = "Integer NULL ")
    private Integer erpId;
	/**----库房编码（id）---**/
    @Column(name = "extendString2", columnDefinition = "nvarchar2(200) NULL ")
    private String warehouseId;
	/**----新增类型---**/
	@Column(name="add_type", columnDefinition="Integer NULL")
	private Integer addType;
	/**----创建人ID---**/
	@Column(name="creator", columnDefinition="Integer NULL ")
	private Integer creator;
	/**----创建时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "date NULL")
    private Date createDate;
	/**----更新人ID---**/
	@Column(name="updater", columnDefinition="Integer NULL")
	private Integer updater;
	/**----更新时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "updateDate", columnDefinition = "date NULL")
    private Date updateDate;

	/**----是否删除---**/
	@Column(name="deleted", columnDefinition="Integer NULL")
	private Integer deleted;

//	@Column(name="extendInt1", columnDefinition="Integer NULL")
//	private Integer extendInt1;

	@Column(name="extendInt2", columnDefinition="Integer NULL")
	private Integer extendInt2;

	@Column(name="extendString1", columnDefinition="nvarchar2(64) NULL")
	private String extendString1;

//	public Integer getExtendInt1() {
//		return extendInt1;
//	}
//
//	public void setExtendInt1(Integer extendInt1) {
//		this.extendInt1 = extendInt1;
//	}

	public Integer getExtendInt2() {
		return extendInt2;
	}

	public void setExtendInt2(Integer extendInt2) {
		this.extendInt2 = extendInt2;
	}

	public String getExtendString1() {
		return extendString1;
	}

	public void setExtendString1(String extendString1) {
		this.extendString1 = extendString1;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
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
		this.code = code;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public Integer getZipCode(){
		return zipCode;
	}

	public void setZipCode(Integer zipCode){
		this.zipCode = zipCode;
	}

	public String getContactPerson(){
		return contactPerson;
	}

	public void setContactPerson(String contactPerson){
		this.contactPerson = contactPerson;
	}

	public String getContractPhone(){
		return contractPhone;
	}

	public void setContractPhone(String contractPhone){
		this.contractPhone = contractPhone;
	}

	public String getFax(){
		return fax;
	}

	public void setFax(String fax){
		this.fax = fax;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getSort(){
		return sort;
	}

	public void setSort(Integer sort){
		this.sort = sort;
	}

	public String getMemo(){
		return memo;
	}

	public void setMemo(String memo){
		this.memo = memo;
	}

	public Integer getErpId(){
		return erpId;
	}

	public void setErpId(Integer erpId){
		this.erpId = erpId;
	}

	public String getWarehouseId(){
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId){
		this.warehouseId = warehouseId;
	}

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


	public Provider() {

	}

}