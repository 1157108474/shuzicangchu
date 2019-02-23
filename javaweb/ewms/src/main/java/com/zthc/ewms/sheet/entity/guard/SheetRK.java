package com.zthc.ewms.sheet.entity.guard;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @Title: 物资入库单
 * @Package
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="WZ_SHEET_RK")
public class SheetRK {

	/**----id---**/
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "generator")
	@SequenceGenerator(name= "generator",sequenceName = "WZSHEETRK_SEQUENCE",allocationSize = 1)
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
	@Column(name = "kindId", columnDefinition = "Integer NULL COMMENT '分类ID'")
	private Integer kindId;
	/**----类型ID---**/
	@Column(name = "typeId", columnDefinition = "Integer NULL COMMENT '类型ID'")
	private Integer typeId;
	/**----DutyId---**/
	@Column(name = "dutyId", columnDefinition = "Integer NULL COMMENT 'DutyId'")
	private Integer dutyId;
	/**----部门id---**/
	@Column(name = "departId", columnDefinition = "Integer NULL COMMENT '部门id'")
	private Integer departId;
	/**----字典表ID---**/
	@Column(name = "routeId", columnDefinition = "Integer NULL COMMENT '字典表ID'")
	private Integer routeId;
	/**----流程ID---**/
	@Column(name="route_stepid", columnDefinition="Integer NULL COMMENT '流程ID'")
	private Integer routeStepid;
	/**----角色ID---**/
	@Column(name = "roleId", columnDefinition = "Integer NULL COMMENT '角色ID'")
	private Integer roleId;
	/**----RelateSheet---**/
	@Column(name = "relateSheet", columnDefinition = "Integer NULL COMMENT 'RelateSheet'")
	private Integer relateSheet;
	/**----提交人ID---**/
	@Column(name = "submitManId", columnDefinition = "Integer NULL COMMENT '提交人ID'")
	private Integer submitManId;
	/**----提交时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "submitTime", columnDefinition = "datetime NULL COMMENT '提交时间'")
	private Date submitTime;
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
	@Column(name = "createDate", columnDefinition = "datetime NULL COMMENT '创建时间'")
    @Transient
    private String createDateStr;
    private Date createDate;
	/**----更新人ID---**/
	@Column(name="updator", columnDefinition="Integer NULL COMMENT '更新人ID'")
	private Integer updator;
	/**----更新时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "updateDate", columnDefinition = "datetime NULL COMMENT '更新时间'")
	private Date updateDate;
	/**----库存组织编码---**/
	@Column(name = "ztId", columnDefinition = "Integer NULL COMMENT '库存组织编码'")
	private Integer ztId;
	/**
	 * ----接收单ID---
	 **/
	@Column(name = "extendInt1", columnDefinition = "Integer NULL COMMENT '接收单ID'")
	private Integer extendInt1;
	/**
	 * ----调拨单ID---
	 **/
	@Column(name = "extendInt2", columnDefinition = "Integer NULL COMMENT '调拨单ID'")
	private Integer extendInt2;
	/**
	 * ----备用int字段---
	 **/
	@Column(name = "extendInt3", columnDefinition = "Integer NULL COMMENT '备用int字段'")
	private Integer extendInt3;
	/**
	 * ----备用int字段---
	 **/
	@Column(name = "extendInt4", columnDefinition = "Integer NULL COMMENT '备用int字段'")
	private Integer extendInt4;
	/**
	 * ----备用int字段---
	 **/
	@Column(name = "extendInt5", columnDefinition = "Integer NULL COMMENT '备用int字段'")
	private Integer extendInt5;
	/**
	 * ----备用int字段---
	 **/
	@Column(name = "extendInt6", columnDefinition="Integer NULL COMMENT '备用int字段'")
	private Integer extendInt6;
	/**
	 * ----备用int字段---
	 **/
	@Column(name = "extendInt7", columnDefinition = "Integer NULL COMMENT '备用int字段'")
	private Integer extendInt7;
	/**
	 * ----备用int字段---
	 **/
	@Column(name="extendInt8", columnDefinition = "Integer NULL COMMENT '备用int字段'")
	private Integer extendInt8;
	/**
	 * ----备用float字段---
	 **/
	@Column(name="extendFloat1", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
	private Double extendFloat1;
	/**----备用float字段---**/
	@Column(name="extendFloat2", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
	private Double extendFloat2;
	/**----备用float字段---**/
	@Column(name="extendFloat3", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
	private Double extendFloat3;
	/**----备用float字段---**/
	@Column(name="extendFloat4", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
	private Double extendFloat4;
	/**----备用float字段---**/
	@Column(name="extendFloat5", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
	private Double extendFloat5;
	/**----备用float字段---**/
	@Column(name="extendFloat6", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
	private Double extendFloat6;
	/**----备用float字段---**/
	@Column(name="extendFloat7", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
	private Double extendFloat7;
	/**----备用float字段---**/
	@Column(name="extendFloat8", columnDefinition = "number(18,9) NULL COMMENT '备用float字段'")
	private Double extendFloat8;
	/**----备用string字段---**/
	@Column(name="extendString1", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
	private String extendString1;
	/**----备用string字段---**/
	@Column(name="extendString2", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
	private String extendString2;
	/**----备用string字段---**/
	@Column(name="extendString3", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
	private String extendString3;
	/**----备用string字段---**/
	@Column(name="extendString4", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
	private String extendString4;
	/**----备用string字段---**/
	@Column(name="extendString5", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
	private String extendString5;
	/**----备用string字段---**/
	@Column(name="extendString6", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
	private String extendString6;
	/**----备用string字段---**/
	@Column(name="extendString7", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
	private String extendString7;
	/**----备用string字段---**/
	@Column(name="extendString8", columnDefinition = "nvarchar2(255) NULL COMMENT '备用string字段'")
	private String extendString8;
	/**----采购订单编号---**/
	@Column(name = "orderNum", columnDefinition = "nvarchar2(200) NULL COMMENT '采购订单编号'")
	private String orderNum;
	/**----接收单号---**/
	@Column(name = "receiveNum", columnDefinition = "nvarchar2(200) NULL COMMENT '接收单号'")
	private String receiveNum;
	/**----使用单位ID---**/
	@Column(name = "usedDepartId", columnDefinition = "Integer NULL COMMENT '使用单位ID'")
	private Integer usedDepartId;
	/**----保管员ID---**/
	@Column(name = "storeManId", columnDefinition = "Integer NULL COMMENT '保管员ID'")
	private Integer storeManId;
	/**----入库人ID---**/
	@Column(name = "usedManid", columnDefinition = "Integer NULL COMMENT '入库人ID'")
	private Integer usedManid;
	/**----供应商ID---**/
	@Column(name = "providerDepId", columnDefinition = "Integer NULL COMMENT '供应商ID'")
	private Integer providerDepId;
	/**----寄存单位ID---**/
	@Column(name = "ownerDep", columnDefinition = "nvarchar2(50) NULL COMMENT '寄存单位ID'")
	private String ownerDep;
	/**----订单类型---**/
	@Column(name = "orderType", columnDefinition = "Integer NULL COMMENT '订单类型'")
	private Integer orderType;
	/**----资金来源---**/
	@Column(name = "fundsSource", columnDefinition = "Integer NULL COMMENT '资金来源'")
	private Integer fundsSource;
	/**
	 * ----申请单位ID---
	 **/
	@Column(name = "applyDepartId", columnDefinition = "Integer NULL COMMENT '申请单位ID'")
	private Integer applyDepartId;

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Transient
	private String taskId;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Transient
	private String departName;

	@Transient
	private String creatorName;

	/**
	 * ----????URL---
	 **/
	@Column(name = "url")
	private String url;

	public enum URL {
		YKYW("YKYW", "/sheet/ykyw/"),
		DB("WZDBD", "/sheet/db/"),
		TH("TH", "/sheet/th/"),
		RK("RK", "/sheet/rk/"),
		WZJS("WZJS", "/sheet/wzjs/"),
		KCPD("KCPD", "/sheet/pd/"),
        WZLLD("WZLLD", "/sheet/apply/"),
        ZR("ZR", "/sheet/zr/");
        private String type;
		private String value;

		URL(String type, String value) {
			this.type = type;
			this.value = value;
		}

		public String getType() {
			return type;
		}

		public String getValue() {
			return value;
		}

		public static SheetRK.URL getByType(String type) {
			for (SheetRK.URL url : values()) {
				if (url.getType().equals(type)) {
					return url;
				}
			}
			return null;
		}

	}

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

	public Integer getDutyId() {
		return dutyId;
	}

	public void setDutyId(Integer dutyId) {
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

	public Integer getRouteStepid() {
		return routeStepid;
	}

	public void setRouteStepid(Integer routeStepid) {
		this.routeStepid = routeStepid;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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

	public Integer getUpdator() {
		return updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getZtId() {
		return ztId;
	}

	public void setZtId(Integer ztId) {
		this.ztId = ztId;
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

	public Integer getUsedManid() {
		return usedManid;
	}

	public void setUsedManid(Integer usedManid) {
		this.usedManid = usedManid;
	}

	public Integer getProviderDepId() {
		return providerDepId;
	}

	public void setProviderDepId(Integer providerDepId) {
		this.providerDepId = providerDepId;
	}

	public String getOwnerDep() {
		return ownerDep;
	}

	public void setOwnerDep(String ownerDep) {
		this.ownerDep = ownerDep;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
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

	public SheetRK() {

	}


} 