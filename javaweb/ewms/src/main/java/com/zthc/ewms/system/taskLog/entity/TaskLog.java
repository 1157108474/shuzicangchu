package com.zthc.ewms.system.taskLog.entity;

import java.util.Date;

import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;



/**
 * @Title: �ӿ���־
 * @Package
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="inf_tasklog")
public class TaskLog {

	/**----id---**/
	@Id
	@Column(name="id", columnDefinition="Integer NULL COMMENT 'id'")
	@SequenceGenerator(name="generator",sequenceName="INF_TASKLOG_SEQUENCE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	private Integer id;
	/**----Ψһ��ʶ---**/
	@Column(name="guid", columnDefinition="nvarchar2(64) NULL ")
	private String guid;
	/**----�ӿ�����---**/
	@Column(name = "inf_taskdetailName", columnDefinition = "nvarchar2(128) NULL ")
	private String infTaskdetailn;
	/**----����ID---**/
	@Column(name = "taskId", columnDefinition = "Integer NULL ")
	private Integer taskId;
	/**----ͬ�����---**/
	@Column(name = "syncResult", columnDefinition = "integer NULL ")
	private Integer syncResult;
	/**----�ӿ�����---**/
	@Column(name="inf_Content", columnDefinition="nvarchar2(2000) NULL ")
	private String infContent;
	/**----������Ϣ---**/
	@Column(name="inf_errorContent", columnDefinition="nvarchar2(2000) NULL ")
	private String infErrorContent;
	/**----������ʽ---**/
	@Column(name="inf_mode", columnDefinition="integer NULL ")
	private Integer infMode;
	/**----�ӿ�����---**/
	@Column(name="inf_type", columnDefinition="integer NULL ")
	private Integer infType;
	/**----�ӿڵ��÷�---**/
	@Column(name="inf_pull", columnDefinition="integer NULL ")
	private Integer infPull;
	/**----�ӿ��ṩ��---**/
	@Column(name="inf_push", columnDefinition="integer NULL ")
	private Integer infPush;
	/**----�ӿ�ͬ������---**/
	@Column(name="inf_method", columnDefinition="integer NULL ")
	private Integer infMethod;
	/**----������ID---**/
	@Column(name="creator", columnDefinition="integer NULL ")
	private Integer creator;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "createDate", columnDefinition = "date NULL ")
	private Date createDate;

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

	public String getInfTaskdetailn(){
		return infTaskdetailn;
	}

	public void setInfTaskdetailn(String infTaskdetailn){
		this.infTaskdetailn = infTaskdetailn;
	}

	public Integer getTaskId(){
		return taskId;
	}

	public void setTaskId(Integer taskId){
		this.taskId = taskId;
	}

	public Integer getSyncResult(){
		return syncResult;
	}

	public void setSyncResult(Integer syncResult){
		this.syncResult = syncResult;
	}

	public String getInfContent(){
		return infContent;
	}

	public void setInfContent(String infContent){
		this.infContent = infContent;
	}

	public String getInfErrorContent(){
		return infErrorContent;
	}

	public void setInfErrorContent(String infErrorContent){
		this.infErrorContent = infErrorContent;
	}

	public Integer getInfMode(){
		return infMode;
	}

	public void setInfMode(Integer infMode){
		this.infMode = infMode;
	}

	public Integer getInfType(){
		return infType;
	}

	public void setInfType(Integer infType){
		this.infType = infType;
	}

	public Integer getInfPull(){
		return infPull;
	}

	public void setInfPull(Integer infPull){
		this.infPull = infPull;
	}

	public Integer getInfPush(){
		return infPush;
	}

	public void setInfPush(Integer infPush){
		this.infPush = infPush;
	}

	public Integer getInfMethod(){
		return infMethod;
	}

	public void setInfMethod(Integer infMethod){
		this.infMethod = infMethod;
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


	public TaskLog() {

	}


} 