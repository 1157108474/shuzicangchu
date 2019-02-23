package com.zthc.ewms.system.applyDep.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;



/**
 * @Title: ���뵥λ����
 * @Package
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="BASE_APPLYDEP")
public class ApplyDep {

	/**----id---**/
	@Id
	@Column(name="id", columnDefinition="integer NULL ")
	@SequenceGenerator(name="generator",sequenceName="BASE_APPLYDEP_SEQUENCE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	private Integer id;
	/**----ERP ID---**/
	@Column(name = "erpId", columnDefinition = "nvarchar2(20) NULL ")
	private String erpId;
	/**----����---**/
	@Column(name="code", columnDefinition="nvarchar2(50) NULL ")
	private String code;
	/**----���뵥λ����---**/
	@Column(name="name", columnDefinition="nvarchar2(200) NULL ")
	private String name;
	/**----��ע---**/
	@Column(name="demo", columnDefinition="nvarchar2(255) NULL ")
	private String demo;
	/**----�����֯ID---**/
	@Column(name = "ztId", columnDefinition = "integer NULL ")
	private Integer ztId;
	/**----��Ŀ���---**/
	@Column(name = "subjectsGroup", columnDefinition = "nvarchar2(200) NULL ")
	private String subjectsGroup;
	/**----��Ŀ˵��---**/
	@Column(name = "subjectsGroupDescription", columnDefinition = "nvarchar2(200) NULL ")
	private String subjectsGroupDescription;
	/**----״̬---**/
	@Column(name="status", columnDefinition="integer NULL ")
	private Integer status;
	/**----��������---**/
	@Column(name="add_type", columnDefinition="integer NULL ")
	private Integer addType;
	/**----������ID---**/
	@Column(name="creator", columnDefinition="integer NULL ")
	private Integer creator;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="create_date", columnDefinition="date NULL ")
	private Date createDate;

	/**----ɾ��״̬---**/
	@Column(name="deleted",columnDefinition = "integer NULL")
	private Integer deleted;

	@Transient
	private String createName;
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

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getErpId(){
		return erpId;
	}

	public void setErpId(String erpId){
		this.erpId = erpId;
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

	public String getDemo(){
		return demo;
	}

	public void setDemo(String demo){
		this.demo = demo;
	}

	public Integer getZtId(){
		return ztId;
	}

	public void setZtId(Integer ztId){
		this.ztId = ztId;
	}

	public String getSubjectsGroup(){
		return subjectsGroup;
	}

	public void setSubjectsGroup(String subjectsGroup){
		this.subjectsGroup = subjectsGroup;
	}

	public String getSubjectsGroupDescription(){
		return subjectsGroupDescription;
	}

	public void setSubjectsGroupDescription(String subjectsGroupDescription){
		this.subjectsGroupDescription = subjectsGroupDescription;
	}

	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status){
		this.status = status;
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


	public ApplyDep() {

	}

	public ApplyDep(Integer id,String code,String name,String subjectsGroup,String subjectsGroupDescription,Integer ztId,
					String departName,String ztName,Integer status,String demo){
		this.id = id;
		this.code = code;
		this.name = name;
		this.subjectsGroup = subjectsGroup;
		this.subjectsGroupDescription = subjectsGroupDescription;
		this.ztId = ztId;
		this.departName = departName;
		this.ztName = ztName;
		this.status = status;
		this.demo = demo;
	}
} 