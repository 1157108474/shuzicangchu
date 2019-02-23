package com.zthc.ewms.system.task.entity.guard;

import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**  
 * @Title: �ӿ�����
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="inf_task")
public class InfTask {
  
  	/**----����---**/
	@Id
	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '����'")
	@SequenceGenerator(name="generator",sequenceName="INF_TASK_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
  	private Integer id;
  	/**----Ψһ��ʶ---**/
    @Column(name="guid", columnDefinition="varchar2(64) NULL COMMENT 'Ψһ��ʶ'")
    private String guid;
  	/**----����---**/
    @Column(name="inf_taskname", columnDefinition="varchar2(128) NULL COMMENT '����'")
    private String name;
  	/**----������ʽ---**/
    @Column(name="inf_triggerKind", columnDefinition="Integer NULL COMMENT '������ʽ'")
    private Integer triggerKind;


  	/**----�ӿ�����---**/
    @Column(name="inf_kind", columnDefinition="Integer NULL COMMENT '�ӿ�����'")
    private Integer kind;

  	/**----�ӿ��ṩ��---**/
//	@Column(name="supplySystem", columnDefinition="Integer NULL COMMENT '�ӿ��ṩ��'")
//	private Integer supplySystem;
	@ManyToOne
	@JoinColumn(name="supplySystem")
	@NotFound(action= NotFoundAction.IGNORE)
//	@Transient
	private Dictionary supply;

//	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Dictionary.class )
//	@JoinColumn(name="supplySystem")
//	private Dictionary supplySystemDic;
//  	/**----�ӿڵ��÷�---**/
//    @Column(name="callSystem", columnDefinition="Integer NULL COMMENT '�ӿڵ��÷�'")
//    private Integer callSystem;
//	@Transient
  	@ManyToOne
	@JoinColumn(name="callSystem")
	@NotFound(action= NotFoundAction.IGNORE)
	private Dictionary call;
  	/**----ͬ����ʽ---**/
    @Column(name="syncKind", columnDefinition="Integer NULL COMMENT 'ͬ����ʽ'")
    private Integer syncKind;
	/**----״̬---**/
	@Column(name="status", columnDefinition="Integer default 1 COMMENT '״̬'")
	private Integer status;
  	/**----��ע---**/
    @Column(name="remark", columnDefinition="varchar2(200) NULL COMMENT '��ע'")
    private String remark;

	/**----������---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '������'")
	private Integer creator;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="createDate", columnDefinition="date NULL COMMENT '����ʱ��'")
	private Date createDate;
	/**----������---**/
	@Column(name="updator", columnDefinition="Integer NULL COMMENT '������'")
	private Integer updater;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="updateDate", columnDefinition="date NULL COMMENT '����ʱ��'")
	private Date updateDate;

	/**
	 * ----��������---
	 **/
	@Column(name = "addtype", columnDefinition = "Integer default 0 COMMENT '��������'")
	private Integer addType;


	public Integer getAddType() {
		return addType;
	}

	public void setAddType(Integer addType) {
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
  
  	public Integer getId(){   
    	return id;   
  	} 
    
  	public void setId(Integer id){   
    	this.id = id;   
  	}
	public void setIdStr(String str){
		this.id = StringUtils.isEmpty(str)? null : Integer.parseInt(str);
	}

	public String getGuid(){
    	return guid;   
  	}

  	public void setGuid(String guid){
    	this.guid = guid;   
  	}  
    
  	public String getName(){
    	return name;
  	} 
    
  	public void setName(String name){
    	this.name = name;
  	}  
    
  	public Integer getTriggerKind(){
    	return triggerKind;
  	} 
    

	public void setTriggerKind(Integer triggerKind){
		this.triggerKind = triggerKind;
	}
	public void setTriggerKindStr(String triggerKindStr){
		this.triggerKind = Integer.parseInt(triggerKindStr);
	}
	public Integer getKind(){
    	return kind;
  	} 
    
  	public void setKind(Integer kind){
    	this.kind = kind;
  	}
	public void setKindStr(String kindStr){
		this.kind = Integer.parseInt(kindStr);
	}
//  	public Integer getSupplySystem(){
//    	return supplySystem;
//  	}
//
//  	public void setSupplySystem(Integer supplySystem){
//    	this.supplySystem = supplySystem;
//  	}
//	public void setSupplySystemStr(String supplySystemStr){
//		this.supplySystem = Integer.parseInt(supplySystemStr);
//	}

//	public Dictionary getSupplySystemDic() {
//		return supplySystemDic;
//	}
//
//	public void setSupplySystemDic(Dictionary supplySystemDic) {
//		this.supplySystemDic = supplySystemDic;
//	}

//	public Integer getCallSystem(){
//    	return callSystem;
//  	}
//
//  	public void setCallSystem(Integer callSystem){
//    	this.callSystem = callSystem;
//  	}
//	public void setCallSystemStr(String callSystemStr){
//		this.callSystem = Integer.parseInt(callSystemStr);
//	}
    
  	public Integer getSyncKind(){   
    	return syncKind;   
  	} 
    
  	public void setSyncKind(Integer syncKind){   
    	this.syncKind = syncKind;   
  	}
	public void setSyncKindStr(String syncKindStr){
		this.syncKind = Integer.parseInt(syncKindStr);
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getRemark(){
    	return remark;   
  	} 
    
  	public void setRemark(String remark){   
    	this.remark = remark;   
  	}

  	@Transient
	private String supplyName;
	@Transient
	private String callName;

	public String getSupplyName() {
		return supplyName;
	}

	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}

	public String getCallName() {
		return callName;
	}

	public void setCallName(String callName) {
		this.callName = callName;
	}

	public Dictionary getSupply() {
		return supply;
	}

	public void setSupply(Dictionary supply) {
		this.supply = supply;
	}
	public void setSupplyStr(String supply) {
		this.supply = new Dictionary(Integer.parseInt(supply));
	}

	public Dictionary getCall() {
		return call;
	}

	public void setCall(Dictionary call) {
		this.call = call;
	}
	public void setCallStr(String call) {
		this.call = new Dictionary(Integer.parseInt(call));
	}

	public InfTask() {
	
  	}
	public InfTask(int id) {
           this.id = id;
	}

	public InfTask(Integer id, String name, Integer triggerKind, Integer kind,
				   Dictionary supply, Dictionary call,
				   Integer syncKind, String remark) {
		this.id = id;
//  		this.guid = guid;
		this.name = name;
		this.triggerKind = triggerKind;
		this.kind = kind;
//		this.supply = supply;
		this.supplyName = supply.getName();
		this.callName = call.getName();
		this.syncKind = syncKind;
		this.remark = remark;
	}





}