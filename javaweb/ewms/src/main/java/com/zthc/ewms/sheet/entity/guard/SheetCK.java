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
@Table(name="WZ_SHEET_CK")
public class SheetCK {

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
	/**----����int�ֶ�---**/
	@Column(name="extendint1", columnDefinition="Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendint1;
	/**----����int�ֶ�---**/
	@Column(name="extendint2", columnDefinition="Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendint2;
	/**----����int�ֶ�---**/
	@Column(name="extendint3", columnDefinition="Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendint3;
	/**----����int�ֶ�---**/
	@Column(name="extendint4", columnDefinition="Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendint4;
	/**----����int�ֶ�---**/
	@Column(name="extendint5", columnDefinition="Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendint5;
	/**----����int�ֶ�---**/
	@Column(name="extendint6", columnDefinition="Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendint6;
	/**----����int�ֶ�---**/
	@Column(name="extendint7", columnDefinition="Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendint7;
	/**----���³ɱ�---**/
	@Column(name="extendint8", columnDefinition="Integer NULL COMMENT '���³ɱ�'")
	private Integer extendint8;

	/**----����float�ֶ�---**/
	@Column(name="extendfloat1", columnDefinition="number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendfloat1;
	/**----����float�ֶ�---**/
	@Column(name="extendfloat2", columnDefinition="number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendfloat2;
	/**----����float�ֶ�---**/
	@Column(name="extendfloat3", columnDefinition="number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendfloat3;
	/**----����float�ֶ�---**/
	@Column(name="extendfloat4", columnDefinition="number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendfloat4;
	/**----����float�ֶ�---**/
	@Column(name="extendfloat5", columnDefinition="number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendfloat5;
	/**----����float�ֶ�---**/
	@Column(name="extendfloat6", columnDefinition="number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendfloat6;
	/**----����float�ֶ�---**/
	@Column(name="extendfloat7", columnDefinition="number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendfloat7;
	/**----����float�ֶ�---**/
	@Column(name="extendfloat8", columnDefinition="number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendfloat8;
	/**----����string�ֶ�---**/
	@Column(name="extendstring1", columnDefinition="nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendstring1;
	/**----����string�ֶ�---**/
	@Column(name="extendstring2", columnDefinition="nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendstring2;
	/**----����string�ֶ�---**/
	@Column(name="extendstring3", columnDefinition="nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendstring3;
	/**----����string�ֶ�---**/
	@Column(name="extendstring4", columnDefinition="nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendstring4;
	/**----����string�ֶ�---**/
	@Column(name="extendstring5", columnDefinition="nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendstring5;
	/**----����string�ֶ�---**/
	@Column(name="extendstring6", columnDefinition="nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendstring6;
	/**----����string�ֶ�---**/
	@Column(name="extendstring7", columnDefinition="nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendstring7;
	/**----����string�ֶ�---**/
	@Column(name="extendstring8", columnDefinition="nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendstring8;
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
	/**----��������---**/
	@Column(name="ordertype", columnDefinition="Integer NULL COMMENT '��������'")
	private Integer ordertype;
	/**----�ʽ���Դ---**/
	@Column(name="fundssource", columnDefinition="Integer NULL COMMENT '�ʽ���Դ'")
	private Integer fundssource;
	/**----���뵥λID---**/
	@Column(name="applydepartid", columnDefinition="Integer NULL COMMENT '���뵥λID'")
	private Integer applydepartid;
	/**----�ϼ���������---**/
	@Column(name="officesId", columnDefinition="Integer NULL COMMENT '�ϼ���������'")
	private Integer officesId;
	@Column(name="url" )
	private String url;
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

	public Integer getExtendint4(){
		return extendint4;
	}

	public void setExtendint4(Integer extendint4){
		this.extendint4 = extendint4;
	}

	public Integer getExtendint5(){
		return extendint5;
	}

	public void setExtendint5(Integer extendint5){
		this.extendint5 = extendint5;
	}

	public Integer getExtendint6(){
		return extendint6;
	}

	public void setExtendint6(Integer extendint6){
		this.extendint6 = extendint6;
	}

	public Integer getExtendint7(){
		return extendint7;
	}

	public void setExtendint7(Integer extendint7){
		this.extendint7 = extendint7;
	}

	public Integer getExtendint8(){
		return extendint8;
	}

	public void setExtendint8(Integer extendint8){
		this.extendint8 = extendint8;
	}

	public Double getExtendfloat1() {
		return extendfloat1;
	}

	public void setExtendfloat1(Double extendfloat1) {
		this.extendfloat1 = extendfloat1;
	}

	public Double getExtendfloat2() {
		return extendfloat2;
	}

	public void setExtendfloat2(Double extendfloat2) {
		this.extendfloat2 = extendfloat2;
	}

	public Double getExtendfloat3() {
		return extendfloat3;
	}

	public void setExtendfloat3(Double extendfloat3) {
		this.extendfloat3 = extendfloat3;
	}

	public Double getExtendfloat4() {
		return extendfloat4;
	}

	public void setExtendfloat4(Double extendfloat4) {
		this.extendfloat4 = extendfloat4;
	}

	public Double getExtendfloat5() {
		return extendfloat5;
	}

	public void setExtendfloat5(Double extendfloat5) {
		this.extendfloat5 = extendfloat5;
	}

	public Double getExtendfloat6() {
		return extendfloat6;
	}

	public void setExtendfloat6(Double extendfloat6) {
		this.extendfloat6 = extendfloat6;
	}

	public Double getExtendfloat7() {
		return extendfloat7;
	}

	public void setExtendfloat7(Double extendfloat7) {
		this.extendfloat7 = extendfloat7;
	}

	public Double getExtendfloat8() {
		return extendfloat8;
	}

	public void setExtendfloat8(Double extendfloat8) {
		this.extendfloat8 = extendfloat8;
	}

	public String getExtendstring1(){
		return extendstring1;
	}

	public void setExtendstring1(String extendstring1){
		this.extendstring1 = extendstring1;
	}

	public String getExtendstring2(){
		return extendstring2;
	}

	public void setExtendstring2(String extendstring2){
		this.extendstring2 = extendstring2;
	}

	public String getExtendstring3(){
		return extendstring3;
	}

	public void setExtendstring3(String extendstring3){
		this.extendstring3 = extendstring3;
	}

	public String getExtendstring4(){
		return extendstring4;
	}

	public void setExtendstring4(String extendstring4){
		this.extendstring4 = extendstring4;
	}

	public String getExtendstring5(){
		return extendstring5;
	}

	public void setExtendstring5(String extendstring5){
		this.extendstring5 = extendstring5;
	}

	public String getExtendstring6(){
		return extendstring6;
	}

	public void setExtendstring6(String extendstring6){
		this.extendstring6 = extendstring6;
	}

	public String getExtendstring7(){
		return extendstring7;
	}

	public void setExtendstring7(String extendstring7){
		this.extendstring7 = extendstring7;
	}

	public String getExtendstring8(){
		return extendstring8;
	}

	public void setExtendstring8(String extendstring8){
		this.extendstring8 = extendstring8;
	}

	public String getOrdernum(){
		return ordernum;
	}

	public void setOrdernum(String ordernum){
		this.ordernum = ordernum;
	}

	public String getReceivenum(){
		return receivenum;
	}

	public void setReceivenum(String receivenum){
		this.receivenum = receivenum;
	}

	public Integer getUseddepartid(){
		return useddepartid;
	}

	public void setUseddepartid(Integer useddepartid){
		this.useddepartid = useddepartid;
	}

	public Integer getStoremanid(){
		return storemanid;
	}

	public void setStoremanid(Integer storemanid){
		this.storemanid = storemanid;
	}

	public Integer getUsedmanid(){
		return usedmanid;
	}

	public void setUsedmanid(Integer usedmanid){
		this.usedmanid = usedmanid;
	}

	public Integer getProviderdepid(){
		return providerdepid;
	}

	public void setProviderdepid(Integer providerdepid){
		this.providerdepid = providerdepid;
	}

	public String getOwnerdep(){
		return ownerdep;
	}

	public void setOwnerdep(String ownerdep){
		this.ownerdep = ownerdep;
	}

	public Integer getOrdertype(){
		return ordertype;
	}

	public void setOrdertype(Integer ordertype){
		this.ordertype = ordertype;
	}

	public Integer getFundssource(){
		return fundssource;
	}

	public void setFundssource(Integer fundssource){
		this.fundssource = fundssource;
	}

	public Integer getApplydepartid(){
		return applydepartid;
	}

	public void setApplydepartid(Integer applydepartid){
		this.applydepartid = applydepartid;
	}

	public Integer getOfficesId() {
		return officesId;
	}

	public void setOfficesId(Integer officesId) {
		this.officesId = officesId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public SheetCK() {

	}


} 