package com.zthc.ewms.sheet.entity.guard;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Title: 物资出库单
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
	/**----唯一标识---**/
	@Column(name="guid", columnDefinition="nvarchar2(64) NULL COMMENT '唯一标识'")
	private String guid;

	/**----单据名称---**/
	@Column(name="name", columnDefinition="nvarchar2(255) NULL COMMENT '单据名称'")
	private String name;
	/**----单据编码---**/
	@Column(name="code", columnDefinition="nvarchar2(50) NULL COMMENT '单据编码'")
	private String code;
	/**----分类ID---**/
	@Column(name="kindid", columnDefinition="Integer NULL COMMENT '分类ID'")
	private Integer kindid;

	/**----类型ID---**/
	@Column(name="typeid", columnDefinition="Integer NULL COMMENT '类型ID'")
	private Integer typeid;
	/**----DutyId---**/
	@Column(name="dutyid", columnDefinition="Integer NULL COMMENT 'DutyId'")
	private Integer dutyid;

	/**----部门id---**/
	@Column(name="departid", columnDefinition="Integer NULL COMMENT '部门id'")
	private Integer departid;
	/**----字典表ID---**/
	@Column(name="routeid", columnDefinition="Integer NULL COMMENT '字典表ID'")
	private Integer routeid;
	/**----流程ID---**/
	@Column(name="route_stepid", columnDefinition="Integer NULL COMMENT '流程ID'")
	private Integer routeStepid;
	/**----角色ID---**/
	@Column(name="roleid", columnDefinition="Integer NULL COMMENT '角色ID'")
	private Integer roleid;
	/**----RelateSheet---**/
	@Column(name="relatesheet", columnDefinition="Integer NULL COMMENT 'RelateSheet'")
	private Integer relatesheet;
	/**----提交人ID---**/
	@Column(name="submitmanid", columnDefinition="Integer NULL COMMENT '提交人ID'")
	private Integer submitmanid;
	/**----提交时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="submittime", columnDefinition="datetime NULL COMMENT '提交时间'")
	private Date submittime;
	/**----单据状态---**/
	@Column(name="status", columnDefinition="Integer NULL COMMENT '单据状态'")
	private Integer status;
	/**----备注---**/
	@Column(name="memo", columnDefinition="nvarchar2(255) NULL COMMENT '备注'")
	private String memo;
	/**----创建人ID---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人ID'")
	private Integer creator;
	/**----创建时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="createdate", columnDefinition="datetime NULL COMMENT '创建时间'")
	private Date createdate;
	/**----更新人ID---**/
	@Column(name="updator", columnDefinition="Integer NULL COMMENT '更新人ID'")
	private Integer updator;
	/**----更新时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="updatedate", columnDefinition="datetime NULL COMMENT '更新时间'")
	private Date updatedate;
	/**----库存组织编码---**/
	@Column(name="ztid", columnDefinition="Integer NULL COMMENT '库存组织编码'")
	private Integer ztid;

	/**----采购订单编号---**/
	@Column(name="ordernum", columnDefinition="nvarchar2(200) NULL COMMENT '采购订单编号'")
	private String ordernum;
	/**----接收单号---**/
	@Column(name="receivenum", columnDefinition="nvarchar2(200) NULL COMMENT '接收单号'")
	private String receivenum;
	/**----使用单位ID---**/
	@Column(name="useddepartid", columnDefinition="Integer NULL COMMENT '使用单位ID'")
	private Integer useddepartid;
	/**----保管员ID---**/
	@Column(name="storemanid", columnDefinition="Integer NULL COMMENT '保管员ID'")
	private Integer storemanid;
	/**----入库人ID---**/
	@Column(name="usedmanid", columnDefinition="Integer NULL COMMENT '入库人ID'")
	private Integer usedmanid;
	/**----供应商ID---**/
	@Column(name="providerdepid", columnDefinition="Integer NULL COMMENT '供应商ID'")
	private Integer providerdepid;
	/**----寄存单位ID---**/
	@Column(name="ownerdep", columnDefinition="nvarchar2(50) NULL COMMENT '寄存单位ID'")
	private String ownerdep;

	/**----申请单ID---**/
	@Column(name="extendint1", columnDefinition="Integer NULL ")
	private Integer extendint1;
	/**----申请单号---**/
	@Column(name="SLCODE", columnDefinition="nvarchar2(128) NULL ")
	private String slcode;
	/**----申请单位ID---**/
	@Column(name="applydepartid", columnDefinition="Integer NULL COMMENT '申请单位ID'")
	private Integer applydepartid;
	@Column(name="STATUSNAME", columnDefinition="nvarchar2(128) NULL ")
	private String statusName;
	@Column(name="USEDEPNAME", columnDefinition="nvarchar2(128) NULL ")
	private String usedepName;
	@Column(name="STORENAME", columnDefinition="nvarchar2(128) NULL ")
	private String storeName;
	@Column(name="PERSONNAME", columnDefinition="nvarchar2(128) NULL ")
	private String personName;

	/**----上级审批部门---**/
	@Column(name="officesId", columnDefinition="Integer NULL COMMENT '上级审批部门'")
	private Integer officesId;

	@Column(name="departOfficename", columnDefinition="nvarchar2(128) NULL ")
	private String departOfficename;
	/**----使用单位---**/
	@Column(name="extendint2", columnDefinition="Integer NULL COMMENT '备用int字段'")
	private Integer extendint2;

	@Column(name="requestUnitname", columnDefinition="nvarchar2(128) NULL ")
	private String requestUnitname;
	/**----资金来源---**/
	@Column(name="fundssource", columnDefinition="Integer NULL COMMENT '资金来源'")
	private Integer fundssource;
	@Column(name="moneyname", columnDefinition="nvarchar2(128) NULL ")
	private String moneyname;
	/**----用途---**/
	@Column(name="extendstring1", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
	private String extendstring1;
	/**----库存组织---**/
	@Column(name="extendstring2", columnDefinition="nvarchar2(255) NULL COMMENT '备用string字段'")
	private String extendstring2;
	/**----备用int字段---**/
	@Column(name="extendint3", columnDefinition="Integer NULL COMMENT '备用int字段'")
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