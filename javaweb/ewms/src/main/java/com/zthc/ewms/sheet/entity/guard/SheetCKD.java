package com.zthc.ewms.sheet.entity.guard;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Title: ���ʳ��ⵥ
 * @Package
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_CKD")
public class SheetCKD {

	/**----id---**/
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "generator")
	@SequenceGenerator(name= "generator",sequenceName = "WZSHEETCK_SEQUENCE",allocationSize = 1)
	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT 'id'")
	private Integer id;
	/**----Ψһ��ʶ---**/
	@Column(name="guid", columnDefinition="nvarchar2(64) NULL COMMENT 'Ψһ��ʶ'")
	private String guid;

	/**----��������---**/
	@Column(name="name", columnDefinition="nvarchar2(255) NULL COMMENT '��������'")
	private String name;
	/**----���ݱ���---**/
	@Column(name="code", columnDefinition="nvarchar2(50) NULL COMMENT '���ݱ���'")
	private String code;
	/**----����ID---**/
	@Column(name="kindid", columnDefinition="Integer NULL COMMENT '����ID'")
	private Integer kindid;

	/**----����ID---**/
	@Column(name="typeid", columnDefinition="Integer NULL COMMENT '����ID'")
	private Integer typeid;
	/**----DutyId---**/
	@Column(name="dutyid", columnDefinition="Integer NULL COMMENT 'DutyId'")
	private Integer dutyid;

	/**----����id---**/
	@Column(name="departid", columnDefinition="Integer NULL COMMENT '����id'")
	private Integer departid;
	/**----�ֵ��ID---**/
	@Column(name="routeid", columnDefinition="Integer NULL COMMENT '�ֵ��ID'")
	private Integer routeid;
	/**----����ID---**/
	@Column(name="route_stepid", columnDefinition="Integer NULL COMMENT '����ID'")
	private Integer routeStepid;
	/**----��ɫID---**/
	@Column(name="roleid", columnDefinition="Integer NULL COMMENT '��ɫID'")
	private Integer roleid;
	/**----RelateSheet---**/
	@Column(name="relatesheet", columnDefinition="Integer NULL COMMENT 'RelateSheet'")
	private Integer relatesheet;
	/**----�ύ��ID---**/
	@Column(name="submitmanid", columnDefinition="Integer NULL COMMENT '�ύ��ID'")
	private Integer submitmanid;
	/**----�ύʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="submittime", columnDefinition="datetime NULL COMMENT '�ύʱ��'")
	private Date submittime;
	/**----����״̬---**/
	@Column(name="status", columnDefinition="Integer NULL COMMENT '����״̬'")
	private Integer status;
	/**----��ע---**/
	@Column(name="memo", columnDefinition="nvarchar2(255) NULL COMMENT '��ע'")
	private String memo;
	/**----������ID---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '������ID'")
	private Integer creator;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="createdate", columnDefinition="datetime NULL COMMENT '����ʱ��'")
	private Date createdate;
	/**----������ID---**/
	@Column(name="updator", columnDefinition="Integer NULL COMMENT '������ID'")
	private Integer updator;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="updatedate", columnDefinition="datetime NULL COMMENT '����ʱ��'")
	private Date updatedate;
	/**----�����֯����---**/
	@Column(name="ztid", columnDefinition="Integer NULL COMMENT '�����֯����'")
	private Integer ztid;

	/**----�ɹ��������---**/
	@Column(name="ordernum", columnDefinition="nvarchar2(200) NULL COMMENT '�ɹ��������'")
	private String ordernum;
	/**----���յ���---**/
	@Column(name="receivenum", columnDefinition="nvarchar2(200) NULL COMMENT '���յ���'")
	private String receivenum;
	/**----ʹ�õ�λID---**/
	@Column(name="useddepartid", columnDefinition="Integer NULL COMMENT 'ʹ�õ�λID'")
	private Integer useddepartid;
	/**----����ԱID---**/
	@Column(name="storemanid", columnDefinition="Integer NULL COMMENT '����ԱID'")
	private Integer storemanid;
	/**----�����ID---**/
	@Column(name="usedmanid", columnDefinition="Integer NULL COMMENT '�����ID'")
	private Integer usedmanid;
	/**----��Ӧ��ID---**/
	@Column(name="providerdepid", columnDefinition="Integer NULL COMMENT '��Ӧ��ID'")
	private Integer providerdepid;
	/**----�Ĵ浥λID---**/
	@Column(name="ownerdep", columnDefinition="nvarchar2(50) NULL COMMENT '�Ĵ浥λID'")
	private String ownerdep;

	/**----���뵥ID---**/
	@Column(name="extendint1", columnDefinition="Integer NULL ")
	private Integer extendint1;
	/**----���뵥��---**/
	@Column(name="SLCODE", columnDefinition="nvarchar2(128) NULL ")
	private String slcode;
	/**----���뵥λID---**/
	@Column(name="applydepartid", columnDefinition="Integer NULL COMMENT '���뵥λID'")
	private Integer applydepartid;
	@Column(name="STATUSNAME", columnDefinition="nvarchar2(128) NULL ")
	private String statusName;
	@Column(name="USEDEPNAME", columnDefinition="nvarchar2(128) NULL ")
	private String usedepName;
	@Column(name="STORENAME", columnDefinition="nvarchar2(128) NULL ")
	private String storeName;
	@Column(name="PERSONNAME", columnDefinition="nvarchar2(128) NULL ")
	private String personName;

	/**----�ϼ���������---**/
	@Column(name="officesId", columnDefinition="Integer NULL COMMENT '�ϼ���������'")
	private Integer officesId;

	@Column(name="departOfficename", columnDefinition="nvarchar2(128) NULL ")
	private String departOfficename;
	/**----ʹ�õ�λ---**/
	@Column(name="extendint2", columnDefinition="Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendint2;

	@Column(name="requestUnitname", columnDefinition="nvarchar2(128) NULL ")
	private String requestUnitname;
	/**----�ʽ���Դ---**/
	@Column(name="fundssource", columnDefinition="Integer NULL COMMENT '�ʽ���Դ'")
	private Integer fundssource;
	@Column(name="moneyname", columnDefinition="nvarchar2(128) NULL ")
	private String moneyname;
	/**----��;---**/
	@Column(name="extendstring1", columnDefinition="nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendstring1;
	/**----�����֯---**/
	@Column(name="extendstring2", columnDefinition="nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendstring2;
	/**----����int�ֶ�---**/
	@Column(name="extendint3", columnDefinition="Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendint3;

	@Transient
	private String taskId;
	@Transient
	private String createDateStr;

	public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
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

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public Integer getKindid(){
		return kindid;
	}

	public void setKindid(Integer kindid){
		this.kindid = kindid;
	}

	public Integer getTypeid(){
		return typeid;
	}

	public void setTypeid(Integer typeid){
		this.typeid = typeid;
	}

	public Integer getDutyid(){
		return dutyid;
	}

	public void setDutyid(Integer dutyid){
		this.dutyid = dutyid;
	}

	public Integer getDepartid(){
		return departid;
	}

	public void setDepartid(Integer departid){
		this.departid = departid;
	}

	public Integer getRouteid(){
		return routeid;
	}

	public void setRouteid(Integer routeid){
		this.routeid = routeid;
	}

	public Integer getRouteStepid(){
		return routeStepid;
	}

	public void setRouteStepid(Integer routeStepid){
		this.routeStepid = routeStepid;
	}

	public Integer getRoleid(){
		return roleid;
	}

	public void setRoleid(Integer roleid){
		this.roleid = roleid;
	}

	public Integer getRelatesheet(){
		return relatesheet;
	}

	public void setRelatesheet(Integer relatesheet){
		this.relatesheet = relatesheet;
	}

	public Integer getSubmitmanid(){
		return submitmanid;
	}

	public void setSubmitmanid(Integer submitmanid){
		this.submitmanid = submitmanid;
	}

	public Date getSubmittime(){
		return submittime;
	}

	public void setSubmittime(Date submittime){
		this.submittime = submittime;
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

	public Integer getCreator(){
		return creator;
	}

	public void setCreator(Integer creator){
		this.creator = creator;
	}

	public Date getCreatedate(){
		return createdate;
	}

	public void setCreatedate(Date createdate){
		this.createdate = createdate;
	}

	public Integer getUpdator(){
		return updator;
	}

	public void setUpdator(Integer updator){
		this.updator = updator;
	}

	public Date getUpdatedate(){
		return updatedate;
	}

	public void setUpdatedate(Date updatedate){
		this.updatedate = updatedate;
	}

	public Integer getZtid(){
		return ztid;
	}

	public void setZtid(Integer ztid){
		this.ztid = ztid;
	}

	public Integer getExtendint1(){
		return extendint1;
	}

	public void setExtendint1(Integer extendint1){
		this.extendint1 = extendint1;
	}

	public Integer getExtendint2(){
		return extendint2;
	}

	public void setExtendint2(Integer extendint2){
		this.extendint2 = extendint2;
	}

	public Integer getExtendint3(){
		return extendint3;
	}

	public void setExtendint3(Integer extendint3){
		this.extendint3 = extendint3;
	}

	public String getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}

	public String getReceivenum() {
		return receivenum;
	}

	public void setReceivenum(String receivenum) {
		this.receivenum = receivenum;
	}

	public Integer getUseddepartid() {
		return useddepartid;
	}

	public void setUseddepartid(Integer useddepartid) {
		this.useddepartid = useddepartid;
	}

	public Integer getStoremanid() {
		return storemanid;
	}

	public void setStoremanid(Integer storemanid) {
		this.storemanid = storemanid;
	}

	public Integer getUsedmanid() {
		return usedmanid;
	}

	public void setUsedmanid(Integer usedmanid) {
		this.usedmanid = usedmanid;
	}

	public Integer getProviderdepid() {
		return providerdepid;
	}

	public void setProviderdepid(Integer providerdepid) {
		this.providerdepid = providerdepid;
	}

	public String getOwnerdep() {
		return ownerdep;
	}

	public void setOwnerdep(String ownerdep) {
		this.ownerdep = ownerdep;
	}

	public String getSlcode() {
		return slcode;
	}

	public void setSlcode(String slcode) {
		this.slcode = slcode;
	}

	public Integer getApplydepartid() {
		return applydepartid;
	}

	public void setApplydepartid(Integer applydepartid) {
		this.applydepartid = applydepartid;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getUsedepName() {
		return usedepName;
	}

	public void setUsedepName(String usedepName) {
		this.usedepName = usedepName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getOfficesId() {
		return officesId;
	}

	public void setOfficesId(Integer officesId) {
		this.officesId = officesId;
	}

	public String getDepartOfficename() {
		return departOfficename;
	}

	public void setDepartOfficename(String departOfficename) {
		this.departOfficename = departOfficename;
	}

	public String getRequestUnitname() {
		return requestUnitname;
	}

	public void setRequestUnitname(String requestUnitname) {
		this.requestUnitname = requestUnitname;
	}

	public Integer getFundssource() {
		return fundssource;
	}

	public void setFundssource(Integer fundssource) {
		this.fundssource = fundssource;
	}

	public String getMoneyname() {
		return moneyname;
	}

	public void setMoneyname(String moneyname) {
		this.moneyname = moneyname;
	}

	public String getExtendstring1() {
		return extendstring1;
	}

	public void setExtendstring1(String extendstring1) {
		this.extendstring1 = extendstring1;
	}

	public String getExtendstring2() {
		return extendstring2;
	}

	public void setExtendstring2(String extendstring2) {
		this.extendstring2 = extendstring2;
	}


	public SheetCKD() {

	}


} 