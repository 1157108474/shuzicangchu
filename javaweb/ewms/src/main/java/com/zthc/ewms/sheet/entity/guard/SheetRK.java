package com.zthc.ewms.sheet.entity.guard;

import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * @Title: ������ⵥ
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
	@Column(name = "kindId", columnDefinition = "Integer NULL COMMENT '����ID'")
	private Integer kindId;
	/**----����ID---**/
	@Column(name = "typeId", columnDefinition = "Integer NULL COMMENT '����ID'")
	private Integer typeId;
	/**----DutyId---**/
	@Column(name = "dutyId", columnDefinition = "Integer NULL COMMENT 'DutyId'")
	private Integer dutyId;
	/**----����id---**/
	@Column(name = "departId", columnDefinition = "Integer NULL COMMENT '����id'")
	private Integer departId;
	/**----�ֵ��ID---**/
	@Column(name = "routeId", columnDefinition = "Integer NULL COMMENT '�ֵ��ID'")
	private Integer routeId;
	/**----����ID---**/
	@Column(name="route_stepid", columnDefinition="Integer NULL COMMENT '����ID'")
	private Integer routeStepid;
	/**----��ɫID---**/
	@Column(name = "roleId", columnDefinition = "Integer NULL COMMENT '��ɫID'")
	private Integer roleId;
	/**----RelateSheet---**/
	@Column(name = "relateSheet", columnDefinition = "Integer NULL COMMENT 'RelateSheet'")
	private Integer relateSheet;
	/**----�ύ��ID---**/
	@Column(name = "submitManId", columnDefinition = "Integer NULL COMMENT '�ύ��ID'")
	private Integer submitManId;
	/**----�ύʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "submitTime", columnDefinition = "datetime NULL COMMENT '�ύʱ��'")
	private Date submitTime;
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
	@Column(name = "createDate", columnDefinition = "datetime NULL COMMENT '����ʱ��'")
    @Transient
    private String createDateStr;
    private Date createDate;
	/**----������ID---**/
	@Column(name="updator", columnDefinition="Integer NULL COMMENT '������ID'")
	private Integer updator;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "updateDate", columnDefinition = "datetime NULL COMMENT '����ʱ��'")
	private Date updateDate;
	/**----�����֯����---**/
	@Column(name = "ztId", columnDefinition = "Integer NULL COMMENT '�����֯����'")
	private Integer ztId;
	/**
	 * ----���յ�ID---
	 **/
	@Column(name = "extendInt1", columnDefinition = "Integer NULL COMMENT '���յ�ID'")
	private Integer extendInt1;
	/**
	 * ----������ID---
	 **/
	@Column(name = "extendInt2", columnDefinition = "Integer NULL COMMENT '������ID'")
	private Integer extendInt2;
	/**
	 * ----����int�ֶ�---
	 **/
	@Column(name = "extendInt3", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendInt3;
	/**
	 * ----����int�ֶ�---
	 **/
	@Column(name = "extendInt4", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendInt4;
	/**
	 * ----����int�ֶ�---
	 **/
	@Column(name = "extendInt5", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendInt5;
	/**
	 * ----����int�ֶ�---
	 **/
	@Column(name = "extendInt6", columnDefinition="Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendInt6;
	/**
	 * ----����int�ֶ�---
	 **/
	@Column(name = "extendInt7", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendInt7;
	/**
	 * ----����int�ֶ�---
	 **/
	@Column(name="extendInt8", columnDefinition = "Integer NULL COMMENT '����int�ֶ�'")
	private Integer extendInt8;
	/**
	 * ----����float�ֶ�---
	 **/
	@Column(name="extendFloat1", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendFloat1;
	/**----����float�ֶ�---**/
	@Column(name="extendFloat2", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendFloat2;
	/**----����float�ֶ�---**/
	@Column(name="extendFloat3", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendFloat3;
	/**----����float�ֶ�---**/
	@Column(name="extendFloat4", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendFloat4;
	/**----����float�ֶ�---**/
	@Column(name="extendFloat5", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendFloat5;
	/**----����float�ֶ�---**/
	@Column(name="extendFloat6", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendFloat6;
	/**----����float�ֶ�---**/
	@Column(name="extendFloat7", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendFloat7;
	/**----����float�ֶ�---**/
	@Column(name="extendFloat8", columnDefinition = "number(18,9) NULL COMMENT '����float�ֶ�'")
	private Double extendFloat8;
	/**----����string�ֶ�---**/
	@Column(name="extendString1", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendString1;
	/**----����string�ֶ�---**/
	@Column(name="extendString2", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendString2;
	/**----����string�ֶ�---**/
	@Column(name="extendString3", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendString3;
	/**----����string�ֶ�---**/
	@Column(name="extendString4", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendString4;
	/**----����string�ֶ�---**/
	@Column(name="extendString5", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendString5;
	/**----����string�ֶ�---**/
	@Column(name="extendString6", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendString6;
	/**----����string�ֶ�---**/
	@Column(name="extendString7", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendString7;
	/**----����string�ֶ�---**/
	@Column(name="extendString8", columnDefinition = "nvarchar2(255) NULL COMMENT '����string�ֶ�'")
	private String extendString8;
	/**----�ɹ��������---**/
	@Column(name = "orderNum", columnDefinition = "nvarchar2(200) NULL COMMENT '�ɹ��������'")
	private String orderNum;
	/**----���յ���---**/
	@Column(name = "receiveNum", columnDefinition = "nvarchar2(200) NULL COMMENT '���յ���'")
	private String receiveNum;
	/**----ʹ�õ�λID---**/
	@Column(name = "usedDepartId", columnDefinition = "Integer NULL COMMENT 'ʹ�õ�λID'")
	private Integer usedDepartId;
	/**----����ԱID---**/
	@Column(name = "storeManId", columnDefinition = "Integer NULL COMMENT '����ԱID'")
	private Integer storeManId;
	/**----�����ID---**/
	@Column(name = "usedManid", columnDefinition = "Integer NULL COMMENT '�����ID'")
	private Integer usedManid;
	/**----��Ӧ��ID---**/
	@Column(name = "providerDepId", columnDefinition = "Integer NULL COMMENT '��Ӧ��ID'")
	private Integer providerDepId;
	/**----�Ĵ浥λID---**/
	@Column(name = "ownerDep", columnDefinition = "nvarchar2(50) NULL COMMENT '�Ĵ浥λID'")
	private String ownerDep;
	/**----��������---**/
	@Column(name = "orderType", columnDefinition = "Integer NULL COMMENT '��������'")
	private Integer orderType;
	/**----�ʽ���Դ---**/
	@Column(name = "fundsSource", columnDefinition = "Integer NULL COMMENT '�ʽ���Դ'")
	private Integer fundsSource;
	/**
	 * ----���뵥λID---
	 **/
	@Column(name = "applyDepartId", columnDefinition = "Integer NULL COMMENT '���뵥λID'")
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