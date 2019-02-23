package com.zthc.ewms.system.taskLog.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * @Title: �ӿ���־
 * @Package
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="v_jkrz")
public class TaskLogView {

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
	@Column(name = "INF_TASKDETAILNAME", columnDefinition = "nvarchar2(128) NULL ")
	private String infTaskdetailn;
	@Column(name = "RWNAME", columnDefinition = "nvarchar2(128) NULL ")
	private String rwName;
	/**----����ID---**/
	@Column(name = "INF_TASKKIND", columnDefinition = "Integer NULL ")
	private Integer taskId;
	/**----ͬ�����---**/
	@Column(name = "RESULTNAME", columnDefinition = "nvarchar2(128) NULL ")
	private String syncResult;
	/**----�ӿ�����---**/
	@Column(name="INF_CONTENT", columnDefinition="nvarchar2(2000) NULL ")
	private String infContent;
	/**----������Ϣ---**/
	@Column(name="INF_ERRORCONTENT", columnDefinition="nvarchar2(2000) NULL ")
	private String infErrorContent;
	/**----������ʽ---**/
	@Column(name="TRIGGERNAME", columnDefinition="nvarchar2(128) NULL ")
	private String infMode;
	/**----�ӿ�����---**/
	@Column(name="KINDNAME", columnDefinition="nvarchar2(128) NULL ")
	private String infType;
	/**----�ӿڵ��÷�---**/
	@Column(name="CALLNAME", columnDefinition="nvarchar2(128) NULL ")
	private String infPull;
	/**----�ӿ��ṩ��---**/
	@Column(name="SUPPNAME", columnDefinition="nvarchar2(128) NULL ")
	private String infPush;
	/**----�ӿ�ͬ������---**/
	@Column(name="SYNCNAME", columnDefinition="nvarchar2(128) NULL ")
	private String infMethod;
	/**----������ID---**/
	@Column(name="creator", columnDefinition="integer NULL ")
	private Integer creator;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "createDate", columnDefinition = "date NULL ")
	private Date createDate;

	public String getRwName() {
		return rwName;
	}

	public void setRwName(String rwName) {
		this.rwName = rwName;
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

	public String getSyncResult() {
		return syncResult;
	}

	public void setSyncResult(String syncResult) {
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

	public String getInfMode() {
		return infMode;
	}

	public void setInfMode(String infMode) {
		this.infMode = infMode;
	}

	public String getInfType() {
		return infType;
	}

	public void setInfType(String infType) {
		this.infType = infType;
	}

	public String getInfPull() {
		return infPull;
	}

	public void setInfPull(String infPull) {
		this.infPull = infPull;
	}

	public String getInfPush() {
		return infPush;
	}

	public void setInfPush(String infPush) {
		this.infPush = infPush;
	}

	public String getInfMethod() {
		return infMethod;
	}

	public void setInfMethod(String infMethod) {
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


	public TaskLogView() {

	}


} 