package com.zthc.ewms.system.log.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.persistence.*;


/**
 * @Title: ϵͳ��־
 * @Package
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="BASE_LOG")
public class SystemLog {

	/**----id---**/
	@Id
	@Column(name="id", columnDefinition="Integer NOT NULL ")
	@SequenceGenerator(name="generator",sequenceName="BASELOG_SEQUENCE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	private Integer id;
	/**----��־����---**/
	@Column(name = "logType", columnDefinition = "Integer NULL ")
	private Integer logType;
	/**----��־����---**/
	@Column(name = "logObject", columnDefinition = "Integer NULL ")
	private Integer logObject;
	/**----��������---**/
	@Column(name = "logAction", columnDefinition = "Integer NULL ")
	private Integer logAction;
	/**----����---**/
	@Column(name = "logDesc", columnDefinition = "nvarchar2(2000) NULL ")
	private String logDesc;
	/**----����ID---**/
	@Column(name = "sheetId", columnDefinition = "Integer NULL ")
	private Integer sheetId;
	/**----IP��ַ---**/
	@Column(name = "logIp", columnDefinition = "nvarchar2(64) NULL ")
	private String logIp;
	/**----������ID---**/
	@Column(name="creator", columnDefinition="Integer NULL ")
	private Integer creator;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="create_date", columnDefinition="date NULL ")
	private Date createDate;

	/**----����������---**/
	@Transient
	private String createName;

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

	public Integer getLogType(){
		return logType;
	}

	public void setLogType(Integer logType){
		this.logType = logType;
	}

	public Integer getLogObject(){
		return logObject;
	}

	public void setLogObject(Integer logObject){
		this.logObject = logObject;
	}

	public Integer getLogAction(){
		return logAction;
	}

	public void setLogAction(Integer logAction){
		this.logAction = logAction;
	}

	public String getLogDesc(){
		return logDesc;
	}

	public void setLogDesc(String logDesc){
		this.logDesc = logDesc;
	}

	public Integer getSheetId(){
		return sheetId;
	}

	public void setSheetId(Integer sheetId){
		this.sheetId = sheetId;
	}

	public String getLogIp(){
		return logIp;
	}

	public void setLogIp(String logIp){
		this.logIp = logIp;
	}

	public Integer getCreator(){
		return creator;
	}

	public void setCreator(Integer creator){
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public SystemLog() {

	}

	public SystemLog(Integer id,Integer logType,Integer logObject,Integer logAction,String logDesc,String logIp,
					 String createName,Date createDate){
		this.id = id;
		this.logType = logType;
		this.logObject = logObject;
		this.logAction = logAction;
		this.logDesc = logDesc;
		this.logIp = logIp;
		this.createName = createName;
		this.createDate = createDate;
	}
}