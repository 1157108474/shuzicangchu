package com.zthc.ewms.system.useDep.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;



/**
 * @Title: ????��????
 * @Package
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="BASE_USEDEP")
public class UseDep {

	/**----id---**/
	@Id
	@Column(name="id", columnDefinition="integer NOT NULL")
	@SequenceGenerator(name="generator",sequenceName="BASE_USEDEP_SEQUENCE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	private Integer id;
	/**----????��????---**/
	@Column(name="code", columnDefinition="nvarchar2(200) NULL ")
	private String code;
	/**----????��????---**/
	@Column(name="name", columnDefinition="nvarchar2(200) NULL ")
	private String name;
	/**----???ID---**/
	@Column(name = "organizationId", columnDefinition = "integer NULL ")
	private Integer organizationId;
	/**----???????---**/
	@Column(name = "organizationType", columnDefinition = "integer NULL ")
	private Integer organizationType;
	/**----??????ID---**/
	@Column(name = "ztId", columnDefinition = "integer NULL ")
	private Integer ztId;
	/**----??---**/
	@Column(name="status", columnDefinition="integer NULL ")
	private Integer status;
	/**----???---**/
	@Column(name="memo", columnDefinition="nvarchar2(200) NULL ")
	private String memo;
	/**----ERP id---**/
	@Column(name = "erpId", columnDefinition = "integer NULL ")
	private Integer erpId;
	/**----????????---**/
	@Column(name="add_type", columnDefinition="integer NULL ")
	private Integer addType;
	/**----??????ID---**/
	@Column(name="creator", columnDefinition="integer NULL ")
	private Integer creator;
	/**----???????---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "createDate", columnDefinition = "date NULL ")
	private Date createDate;
	/**----??????ID---**/
	@Column(name="updater", columnDefinition="integer NULL ")
	private Integer updater;
	/**----???????---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "updateDate", columnDefinition = "date NULL ")
	private Date updateDate;

	/**----??????---**/
	@Column(name="deleted", columnDefinition="integer NULL ")
	private Integer deleted;

	/**----???????---**/
	@Transient
	private String organizationName;

	/**----???????---**/
	@Transient
	private String ztName;

	@Transient
	private String departName;


	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getZtName() {
		return ztName;
	}

	public void setZtName(String ztName) {
		this.ztName = ztName;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
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

	public Integer getOrganizationId(){
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId){
		this.organizationId = organizationId;
	}

	public Integer getOrganizationType(){
		return organizationType;
	}

	public void setOrganizationType(Integer organizationType){
		this.organizationType = organizationType;
	}

	public Integer getZtId(){
		return ztId;
	}

	public void setZtId(Integer ztId){
		this.ztId = ztId;
	}

	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status = status;
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


	public UseDep() {

	}
	public UseDep(Integer id) {
		this.id = id;
	}
	public UseDep(Integer id,String code,String name,String organizationName,Integer organizationType,Integer ztId,
				  String ztName,String departName,Integer status,String memo){
		this.id = id;
		this.code = code;
		this.name = name;
		this.organizationName = organizationName;
		this.organizationType = organizationType;
		this.ztId = ztId;
		this.departName = departName;
		this.ztName = ztName;
		this.status = status;
		this.memo = memo;
	}


} 