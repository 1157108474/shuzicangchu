package com.zthc.ewms.sheet.entity.guard;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="st_maxcode")
public class MaxCode {

  	/**----主键---**/
	@Id
	@GeneratedValue(generator="customKey")
  	@GenericGenerator(name="customKey", strategy="com.hckj.base.database.hibernate.CustomKey")
  	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '主键'")
  	private Integer id;
  	/**----唯一标识---**/
    @Column(name="guid", columnDefinition="nvarchar(64) NULL COMMENT '唯一标识'")
    private String guid;
  	/**----名称---**/
    @Column(name="name", columnDefinition="nvarchar(64) NULL COMMENT '名称'")
    private String name;
  	/**----编码---**/
    @Column(name="code", columnDefinition="nvarchar(64) NULL COMMENT '编码'")
    private String code;
  	/**----单据分类---**/
    @Column(name="kindId", columnDefinition="Integer NULL COMMENT '单据分类'")
    private Integer kindId;
  	/**----type---**/
    @Column(name="typeId", columnDefinition="Integer NULL COMMENT 'type'")
    private Integer typeId;
  	/**----duty---**/
    @Column(name="dutyId", columnDefinition="nvarchar(64) NULL COMMENT 'duty'")
    private String dutyId;
  	/**----部门ID---**/
    @Column(name="departId", columnDefinition="Integer NULL COMMENT '部门ID'")
    private Integer departId;
  	/**----routeId---**/
    @Column(name="routeId", columnDefinition="Integer NULL COMMENT 'routeId'")
    private Integer routeId;
  	/**----relateSheet---**/
    @Column(name="relateSheet", columnDefinition="Integer NULL COMMENT 'relateSheet'")
    private Integer relateSheet;
  	/**----提交人ID---**/
    @Column(name="submitManId", columnDefinition="Integer NULL COMMENT '提交人ID'")
    private Integer submitManId;
  	/**----提交时间---**/
  	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  	@Column(name="submitTime", columnDefinition="date NULL COMMENT '提交时间'")
  	private Date submitTime;
  	/**----状态---**/
    @Column(name="status", columnDefinition="Integer NULL COMMENT '状态'")
    private Integer status;
  	/**----备注---**/
    @Column(name="memo", columnDefinition="nvarchar(200) NULL COMMENT '备注'")
    private String memo;
  	/**----创建人---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人'")
    private Integer creator;
  	/**----创建时间---**/
  	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  	@Column(name="createDate", columnDefinition="date NULL COMMENT '创建时间'")
  	private Date createDate;
  	/**----更新人---**/
    @Column(name="updator", columnDefinition="Integer NULL COMMENT '更新人'")
    private Integer updater;
  	/**----更新时间---**/
  	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  	@Column(name="updateDate", columnDefinition="date NULL COMMENT '更新时间'")
  	private Date updateDate;
  	/**----ZTID---**/
    @Column(name="ZTID", columnDefinition="Integer NULL COMMENT 'ZTID'")
    private Integer ZTID;
  	/**----extendInt1---**/
    @Column(name="extendInt1", columnDefinition="Integer NULL COMMENT 'extendInt'")
    private Integer extendInt1;

	/**----extendInt1---**/
	@Column(name="extendInt2", columnDefinition="Integer NULL ")
	private Integer extendInt2;

	/**----extendInt1---**/
	@Column(name="extendInt3", columnDefinition="Integer NULL ")
	private Integer extendInt3;  	/**----extendInt1---**/
	@Column(name="extendInt4", columnDefinition="Integer NULL ")
	private Integer extendInt4;  	/**----extendInt1---**/
	@Column(name="extendInt5", columnDefinition="Integer NULL ")
	private Integer extendInt5;  	/**----extendInt1---**/
	@Column(name="extendInt6", columnDefinition="Integer NULL ")
	private Integer extendInt6;  	/**----extendInt1---**/
	@Column(name="extendInt7", columnDefinition="Integer NULL ")
	private Integer extendInt7;  	/**----extendInt1---**/
	@Column(name="extendInt8", columnDefinition="Integer NULL ")
	private Integer extendInt8;

	@Column(name="extendfloat1", columnDefinition="number(18,9) NULL ")
	private Double extendFloat1;
	@Column(name="extendfloat2", columnDefinition="number(18,9) NULL ")
	private Double extendFloat2;
	@Column(name="extendfloat3", columnDefinition="number(18,9) NULL ")
	private Double extendFloat3;
	@Column(name="extendfloat4", columnDefinition="number(18,9) NULL ")
	private Double extendFloat4;
	@Column(name="extendfloat5", columnDefinition="number(18,9) NULL ")
	private Double extendFloat5;
	@Column(name="extendfloat6", columnDefinition="number(18,9) NULL ")
	private Double extendFloat6;
	@Column(name="extendfloat7", columnDefinition="number(18,9) NULL ")
	private Double extendFloat7;
	@Column(name="extendfloat8", columnDefinition="number(18,9) NULL ")
	private Double extendFloat8;

	@Column(name="extendstring1", columnDefinition="nvarchar(255) NULL ")
	private String extendString1;
	@Column(name="extendstring2", columnDefinition="nvarchar(255) NULL ")
	private String extendString2;
	@Column(name="extendstring3", columnDefinition="nvarchar(255) NULL ")
	private String extendString3;
	@Column(name="extendstring4", columnDefinition="nvarchar(255) NULL ")
	private String extendString4;
	@Column(name="extendstring5", columnDefinition="nvarchar(255) NULL ")
	private String extendString5;
	@Column(name="extendstring6", columnDefinition="nvarchar(255) NULL ")
	private String extendString6;
	@Column(name="extendstring7", columnDefinition="nvarchar(255) NULL ")
	private String extendString7;
	@Column(name="extendstring8", columnDefinition="nvarchar(255) NULL ")
	private String extendString8;
	@Column(name="extendstring9", columnDefinition="nvarchar(255) NULL ")
	private String extendString9;

	@Column(name="extendstring10", columnDefinition="nvarchar(255) NULL ")
	private String extendString10;


  	/**----订单编码---**/
    @Column(name="orderNum", columnDefinition="nvarchar(200) NULL COMMENT '订单编码'")
    private String orderNum;

	/**----接收单号---**/
	@Column(name="receiveNum", columnDefinition="nvarchar(200) NULL COMMENT '接收单号'")
	private String receiveNum;

  	/**----使用单位ID---**/
    @Column(name="usedDepartId", columnDefinition="Integer NULL COMMENT '使用单位ID'")
    private Integer usedDepartId;
	/**----storeManId---**/
	@Column(name="storeManId", columnDefinition="Integer NULL COMMENT 'storeManId'")
	private Integer storeManId;
	/**----使用人---**/
	@Column(name="usedManId", columnDefinition="Integer NULL COMMENT '使用人'")
	private Integer usedManId;
  	/**----供应商ID---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '供应商ID'")
    private Integer providerDepId;

	/**----ownerDep---**/
	@Column(name="ownerDep", columnDefinition="Integer NULL COMMENT 'ownerDep'")
	private Integer ownerDep;

	/**----物料范围ID---**/
	@Column(name="officesId", columnDefinition="Integer NULL COMMENT '物料范围ID'")
	private Integer officesId;
  	/**----资金来源---**/
    @Column(name="fundsSource", columnDefinition="Integer NULL COMMENT '资金来源'")
    private Integer fundsSource;
  	/**----申请单位ID---**/
    @Column(name="applyDepartId", columnDefinition="Integer NULL COMMENT '申请单位ID'")
    private Integer applyDepartId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getKindId() {
		return kindId;
	}

	public void setKindId(Integer kindId) {
		this.kindId = kindId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getDutyId() {
		return dutyId;
	}

	public void setDutyId(String dutyId) {
		this.dutyId = dutyId;
	}

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public Integer getRelateSheet() {
		return relateSheet;
	}

	public void setRelateSheet(Integer relateSheet) {
		this.relateSheet = relateSheet;
	}

	public Integer getSubmitManId() {
		return submitManId;
	}

	public void setSubmitManId(Integer submitManId) {
		this.submitManId = submitManId;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getZTID() {
		return ZTID;
	}

	public void setZTID(Integer ZTID) {
		this.ZTID = ZTID;
	}

	public Integer getExtendInt1() {
		return extendInt1;
	}

	public void setExtendInt1(Integer extendInt1) {
		this.extendInt1 = extendInt1;
	}

	public Integer getExtendInt2() {
		return extendInt2;
	}

	public void setExtendInt2(Integer extendInt2) {
		this.extendInt2 = extendInt2;
	}

	public Integer getExtendInt3() {
		return extendInt3;
	}

	public void setExtendInt3(Integer extendInt3) {
		this.extendInt3 = extendInt3;
	}

	public Integer getExtendInt4() {
		return extendInt4;
	}

	public void setExtendInt4(Integer extendInt4) {
		this.extendInt4 = extendInt4;
	}

	public Integer getExtendInt5() {
		return extendInt5;
	}

	public void setExtendInt5(Integer extendInt5) {
		this.extendInt5 = extendInt5;
	}

	public Integer getExtendInt6() {
		return extendInt6;
	}

	public void setExtendInt6(Integer extendInt6) {
		this.extendInt6 = extendInt6;
	}

	public Integer getExtendInt7() {
		return extendInt7;
	}

	public void setExtendInt7(Integer extendInt7) {
		this.extendInt7 = extendInt7;
	}

	public Integer getExtendInt8() {
		return extendInt8;
	}

	public void setExtendInt8(Integer extendInt8) {
		this.extendInt8 = extendInt8;
	}

	public Double getExtendFloat1() {
		return extendFloat1;
	}

	public void setExtendFloat1(Double extendFloat1) {
		this.extendFloat1 = extendFloat1;
	}

	public Double getExtendFloat2() {
		return extendFloat2;
	}

	public void setExtendFloat2(Double extendFloat2) {
		this.extendFloat2 = extendFloat2;
	}

	public Double getExtendFloat3() {
		return extendFloat3;
	}

	public void setExtendFloat3(Double extendFloat3) {
		this.extendFloat3 = extendFloat3;
	}

	public Double getExtendFloat4() {
		return extendFloat4;
	}

	public void setExtendFloat4(Double extendFloat4) {
		this.extendFloat4 = extendFloat4;
	}

	public Double getExtendFloat5() {
		return extendFloat5;
	}

	public void setExtendFloat5(Double extendFloat5) {
		this.extendFloat5 = extendFloat5;
	}

	public Double getExtendFloat6() {
		return extendFloat6;
	}

	public void setExtendFloat6(Double extendFloat6) {
		this.extendFloat6 = extendFloat6;
	}

	public Double getExtendFloat7() {
		return extendFloat7;
	}

	public void setExtendFloat7(Double extendFloat7) {
		this.extendFloat7 = extendFloat7;
	}

	public Double getExtendFloat8() {
		return extendFloat8;
	}

	public void setExtendFloat8(Double extendFloat8) {
		this.extendFloat8 = extendFloat8;
	}

	public String getExtendString1() {
		return extendString1;
	}

	public void setExtendString1(String extendString1) {
		this.extendString1 = extendString1;
	}

	public String getExtendString2() {
		return extendString2;
	}

	public void setExtendString2(String extendString2) {
		this.extendString2 = extendString2;
	}

	public String getExtendString3() {
		return extendString3;
	}

	public void setExtendString3(String extendString3) {
		this.extendString3 = extendString3;
	}

	public String getExtendString4() {
		return extendString4;
	}

	public void setExtendString4(String extendString4) {
		this.extendString4 = extendString4;
	}

	public String getExtendString5() {
		return extendString5;
	}

	public void setExtendString5(String extendString5) {
		this.extendString5 = extendString5;
	}

	public String getExtendString6() {
		return extendString6;
	}

	public void setExtendString6(String extendString6) {
		this.extendString6 = extendString6;
	}

	public String getExtendString7() {
		return extendString7;
	}

	public void setExtendString7(String extendString7) {
		this.extendString7 = extendString7;
	}

	public String getExtendString8() {
		return extendString8;
	}

	public void setExtendString8(String extendString8) {
		this.extendString8 = extendString8;
	}

	public String getExtendString9() {
		return extendString9;
	}

	public void setExtendString9(String extendString9) {
		this.extendString9 = extendString9;
	}

	public String getExtendString10() {
		return extendString10;
	}

	public void setExtendString10(String extendString10) {
		this.extendString10 = extendString10;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(String receiveNum) {
		this.receiveNum = receiveNum;
	}

	public Integer getUsedDepartId() {
		return usedDepartId;
	}

	public void setUsedDepartId(Integer usedDepartId) {
		this.usedDepartId = usedDepartId;
	}

	public Integer getStoreManId() {
		return storeManId;
	}

	public void setStoreManId(Integer storeManId) {
		this.storeManId = storeManId;
	}

	public Integer getUsedManId() {
		return usedManId;
	}

	public void setUsedManId(Integer usedManId) {
		this.usedManId = usedManId;
	}

	public Integer getProviderDepId() {
		return providerDepId;
	}

	public void setProviderDepId(Integer providerDepId) {
		this.providerDepId = providerDepId;
	}

	public Integer getOwnerDep() {
		return ownerDep;
	}

	public void setOwnerDep(Integer ownerDep) {
		this.ownerDep = ownerDep;
	}

	public Integer getOfficesId() {
		return officesId;
	}

	public void setOfficesId(Integer officesId) {
		this.officesId = officesId;
	}

	public Integer getFundsSource() {
		return fundsSource;
	}

	public void setFundsSource(Integer fundsSource) {
		this.fundsSource = fundsSource;
	}

	public Integer getApplyDepartId() {
		return applyDepartId;
	}

	public void setApplyDepartId(Integer applyDepartId) {
		this.applyDepartId = applyDepartId;
	}

	public MaxCode() {
	
  	}
  

} 