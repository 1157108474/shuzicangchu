package com.zthc.ewms.sheet.entity.guard;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: ?????
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="wz_sheet")
public class Sheet {
  
  	/**----????---**/
	@Id
	@SequenceGenerator(name="generator",sequenceName="WZSHEET_SEQUENCE",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
  	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '????'")
  	private Integer id;
  	/**----Ψ????---**/
    @Column(name="guid", columnDefinition="nvarchar(64) NULL COMMENT 'Ψ????'")
    private String guid;
  	/**----????---**/
    @Column(name="name", columnDefinition="nvarchar(64) NULL COMMENT '????'")
    private String name;
  	/**----????---**/
    @Column(name="code", columnDefinition="nvarchar(64) NULL COMMENT '????'")
    private String code;
  	/**----???????---**/
    @Column(name="kindId", columnDefinition="Integer NULL COMMENT '???????'")
    private Integer kindId;
  	/**----type---**/
    @Column(name="typeId", columnDefinition="Integer NULL COMMENT 'type'")
    private Integer typeId;
  	/**----duty---**/
    @Column(name="dutyId", columnDefinition="nvarchar(64) NULL COMMENT 'duty'")
    private String dutyId;
  	/**----????ID---**/
    @Column(name="departId", columnDefinition="Integer NULL COMMENT '????ID'")
    private Integer departId;
  	/**----routeId---**/
    @Column(name="routeId", columnDefinition="Integer NULL COMMENT 'routeId'")
    private Integer routeId;
  	/**----relateSheet---**/
    @Column(name="relateSheet", columnDefinition="Integer NULL COMMENT 'relateSheet'")
    private Integer relateSheet;
  	/**----????ID---**/
    @Column(name="submitManId", columnDefinition="Integer NULL COMMENT '????ID'")
    private Integer submitManId;
  	/**----?????---**/
  	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  	@Column(name="submitTime", columnDefinition="date NULL COMMENT '?????'")
  	private Date submitTime;
  	/**----??---**/
    @Column(name="status", columnDefinition="Integer NULL COMMENT '??'")
    private Integer status;
  	/**----???---**/
    @Column(name="memo", columnDefinition="nvarchar(200) NULL COMMENT '???'")
    private String memo;
  	/**----??????---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '??????'")
    private Integer creator;
  	/**----???????---**/
  	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  	@Column(name="createDate", columnDefinition="date NULL COMMENT '???????'")
  	private Date createDate;
    @Transient
    private String createDateStr;
    /**
     * ----??????---
     **/
    @Column(name="updator", columnDefinition="Integer NULL COMMENT '??????'")
    private Integer updater;
  	/**----???????---**/
  	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  	@Column(name="updateDate", columnDefinition="date NULL COMMENT '???????'")
  	private Date updateDate;
  	/**----ZtId---**/
    @Column(name="ZtId", columnDefinition="Integer NULL COMMENT 'ZtId'")
    private Integer ztId;
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


  	/**----????????---**/
    @Column(name="orderNum", columnDefinition="nvarchar(200) NULL COMMENT '????????'")
    private String orderNum;

	/**----???????---**/
	@Column(name="receiveNum", columnDefinition="nvarchar(200) NULL COMMENT '???????'")
	private String receiveNum;

  	/**----????λID---**/
    @Column(name="usedDepartId", columnDefinition="Integer NULL COMMENT '????λID'")
    private Integer usedDepartId;
	/**----storeManId---**/
	@Column(name="storeManId", columnDefinition="Integer NULL COMMENT 'storeManId'")
	private Integer storeManId;
	/**----?????---**/
	@Column(name="usedManId", columnDefinition="Integer NULL COMMENT '?????'")
	private Integer usedManId;
  	/**----?????ID---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '?????ID'")
    private Integer providerDepId;

	/**----ownerDep---**/
	@Column(name="ownerDep", columnDefinition="Integer NULL COMMENT 'ownerDep'")
	private Integer ownerDep;

	/**----?????ΧID---**/
	@Column(name="officesId", columnDefinition="Integer NULL COMMENT '?????ΧID'")
	private Integer officesId;
  	/**----??????---**/
    @Column(name="fundsSource", columnDefinition="Integer NULL COMMENT '??????'")
    private Integer fundsSource;
  	/**----????λID---**/
    @Column(name="applyDepartId", columnDefinition="Integer NULL COMMENT '????λID'")
    private Integer applyDepartId;

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

	/**----????URL---**/
	@Column(name="url" )
	private String url;

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

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ZtId) {
        this.ztId = ZtId;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

	public Sheet() {
	
  	}

	public Sheet(String code, Integer departId, String memo, Integer creator, Date createDate, Integer ztId,
				 Integer extendInt1, Integer extendInt2, Integer extendInt3, Integer extendInt4, Integer extendInt5,
				 Integer extendInt6, Integer extendInt7, Integer extendInt8, Double extendFloat1, Double extendFloat2,
				 Double extendFloat3, Double extendFloat4, Double extendFloat5, Double extendFloat6, Double extendFloat7,
				 Double extendFloat8, String extendString1, String extendString2, String extendString3,
				 String extendString4, String extendString5, String extendString6, String extendString7,
				 String extendString8, String extendString9, String extendString10, String orderNum,
				 String receiveNum, Integer usedDepartId, Integer storeManId, Integer usedManId,
				 Integer providerDepId, Integer ownerDep, Integer officesId, Integer fundsSource,
				 Integer applyDepartId,String departName,String creatorName) {
		this.code = code;
		this.departId = departId;
		this.memo = memo;
		this.creator = creator;
		this.createDate = createDate;
		this.ztId = ztId;
		this.extendInt1 = extendInt1;
		this.extendInt2 = extendInt2;
		this.extendInt3 = extendInt3;
		this.extendInt4 = extendInt4;
		this.extendInt5 = extendInt5;
		this.extendInt6 = extendInt6;
		this.extendInt7 = extendInt7;
		this.extendInt8 = extendInt8;
		this.extendFloat1 = extendFloat1;
		this.extendFloat2 = extendFloat2;
		this.extendFloat3 = extendFloat3;
		this.extendFloat4 = extendFloat4;
		this.extendFloat5 = extendFloat5;
		this.extendFloat6 = extendFloat6;
		this.extendFloat7 = extendFloat7;
		this.extendFloat8 = extendFloat8;
		this.extendString1 = extendString1;
		this.extendString2 = extendString2;
		this.extendString3 = extendString3;
		this.extendString4 = extendString4;
		this.extendString5 = extendString5;
		this.extendString6 = extendString6;
		this.extendString7 = extendString7;
		this.extendString8 = extendString8;
		this.extendString9 = extendString9;
		this.extendString10 = extendString10;
		this.orderNum = orderNum;
		this.receiveNum = receiveNum;
		this.usedDepartId = usedDepartId;
		this.storeManId = storeManId;
		this.usedManId = usedManId;
		this.providerDepId = providerDepId;
		this.ownerDep = ownerDep;
		this.officesId = officesId;
		this.fundsSource = fundsSource;
		this.applyDepartId = applyDepartId;
		this.departName = departName;
		this.creatorName = creatorName;
	}


  

} 