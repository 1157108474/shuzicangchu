package com.zthc.ewms.sheet.entity.apply;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;

/**  
 * @Title: 物资申领单管理
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_FETCHGOODSHEET_DETAILS")
public class ManageApplyAndDetail {
	

	
  	/**----单据ID---**/
    @Column(name="sheetId", columnDefinition="Integer NULL COMMENT '单据ID'")
    private Integer sheetId;
	/**----物料编码---**/
	@Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '物料编码'")
	private String materialCode;
	/**----描述---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '描述'")
	private String description;
	/***--单位--**/
	@Column(name="es1", columnDefinition="nvarchar(255) NULL ")
	private String es1;
	/**----单位名称---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '单位名称'")
	private String detailUnitName;
	/**----单据明细ID---**/
	@Column(name="sheetDetailId", columnDefinition="Integer NULL COMMENT '单据明细ID'")
	private Integer sheetDetailId;
	/**----明细数量---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '明细数量'")
	private Double detailCount;
	/**----序列号---**/
	@Column(name="snCode", columnDefinition="nvarchar(50) NULL COMMENT '序列号'")
	private String snCode;
	/**----物料ID---**/
	@Column(name="materialId", columnDefinition="Integer NULL COMMENT '物料ID'")
	private Integer materialId;

	@Column(name="detailUnit", columnDefinition="Integer NULL COMMENT '状态'")
	private Integer detailUnit;
    @Column(name = "es2", columnDefinition = "nvarchar(255) NULL ")
    private String es2;
    /**----已出库数量---**/
	@Column(name="ckCount", columnDefinition="Integer NULL COMMENT '已出库数量'")
	private Integer ckCount;

	@Column(name="iztid", columnDefinition="Integer NULL COMMENT '账套ID'")
	private Integer iztId;

	@Column(name="extendInt7", columnDefinition="Integer NULL COMMENT 'ERPDetailId'")
	private Integer extendInt7;
    @Transient
    private Integer applyCount;
    @Transient
    private String address;
    //库房id，在视图中同理加上
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT '库房id'")
	private Integer storeId;

    

	public Integer getSheetId() {
		return sheetId;
	}

	public void setSheetId(Integer sheetId) {
		this.sheetId = sheetId;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetailUnitName() {
		return detailUnitName;
	}

	public void setDetailUnitName(String detailUnitName) {
		this.detailUnitName = detailUnitName;
	}

	public Integer getSheetDetailId() {
		return sheetDetailId;
	}

	public void setSheetDetailId(Integer sheetDetailId) {
		this.sheetDetailId = sheetDetailId;
	}

	public Double getDetailCount() {
		return detailCount;
	}

	public void setDetailCount(Double detailCount) {
		this.detailCount = detailCount;
	}

	public String getSnCode() {
		return snCode;
	}

	public void setSnCode(String snCode) {
		this.snCode = snCode;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public Integer getDetailUnit() {
		return detailUnit;
	}

	public void setDetailUnit(Integer detailUnit) {
		this.detailUnit = detailUnit;
	}


    public String getEs1() {
		return es1;
	}

	public void setEs1(String es1) {
		this.es1 = es1;
	}

	public String getEs2() {
		return es2;
	}

	public void setEs2(String es2) {
		this.es2 = es2;
	}

	public Integer getIztId() {
		return iztId;
	}

	public void setIztId(Integer iztId) {
		this.iztId = iztId;
	}

	public Integer getCkCount() {
        return ckCount;
    }

    public void setCkCount(Integer ckCount) {
        this.ckCount = ckCount;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	

	public Integer getExtendInt7() {
		return extendInt7;
	}

	public void setExtendInt7(Integer extendInt7) {
		this.extendInt7 = extendInt7;
	}

	public Integer getStoreId(){
    	return storeId;
	}

	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}


	
	
	
    @Id
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT '主键'")
    private Integer id;
    /**----GUID---**/
    @Column(name="GUID", columnDefinition="nvarchar(64) NULL COMMENT 'GUID'")
    private String GUID;
    /**----NAME---**/
    @Column(name="NAME", columnDefinition="nvarchar(255) NULL COMMENT 'NAME'")
    private String NAME;
    /**----编码---**/
    @Column(name="code", columnDefinition="nvarchar(64) NULL COMMENT '编码'")
    private String code;
    /**----使用单位ID---**/
    @Column(name="usedDepartId", columnDefinition="Integer NULL COMMENT '使用单位ID'")
    private Integer usedDepartId;
    /**----物料范围ID---**/
    @Column(name="officesId", columnDefinition="Integer NULL COMMENT '物料范围ID'")
    private Integer officesId;
    /**----物料范围名称---**/
    @Column(name="officeName", columnDefinition="nvarchar(200) NULL COMMENT '物料范围名称'")
    private String officeName;

    /**----申请单位ID---**/
    @Column(name="applyDepartId", columnDefinition="Integer NULL COMMENT '申请单位ID'")
    private Integer applyDepartId;
    /**----applyunitName---**/
    @Column(name="applyunitName", columnDefinition="nvarchar(200) NULL COMMENT 'applyunitName'")
    private String applyunitName;
    /**----状态---**/
    @Column(name="status", columnDefinition="Integer NULL COMMENT '状态'")
    private Integer status;
    /**----状态名称---**/
    @Column(name="statusName", columnDefinition="nvarchar(64) NULL COMMENT '状态名称'")
    private String statusName;
    /**----useUnitName---**/
    @Column(name="useUnitName", columnDefinition="nvarchar(200) NULL COMMENT 'useUnitName'")
    private String useUnitName;
    /**----EXTENDSTRING2---**/
//    @Column(name="EXTENDSTRING2", columnDefinition="nvarchar(200) NULL COMMENT 'EXTENDSTRING2'")
//    private String EXTENDSTRING2;
    /**----personName---**/
    @Column(name="personName", columnDefinition="nvarchar(64) NULL COMMENT 'personName'")
    private String personName;
    /**----创建人---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人'")
    private Integer creator;
    /**----创建时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createDate", columnDefinition="date NULL COMMENT '创建时间'")
    private Date createDate;

    @Transient
    private String createDateStr;
    /**----更新人---**/
    @Column(name="updator", columnDefinition="Integer NULL COMMENT '更新人'")
    private Integer updater;
    /**----更新时间---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="updateDate", columnDefinition="date NULL COMMENT '更新时间'")
    private Date updateDate;

    @Column(name="extendString1", columnDefinition="nvarchar(255) NULL ")
    private String extendString1;
    @Column(name="extendString2", columnDefinition="nvarchar(255) NULL ")
    private String extendString2;
    @Column(name="EXTENDSTRING3", columnDefinition="nvarchar(255) NULL ")
    private String EXTENDSTRING3;
    @Column(name="EXTENDSTRING4", columnDefinition="nvarchar(255) NULL ")
    private String EXTENDSTRING4;

    /**----ZtId---**/
    @Column(name="ZtId", columnDefinition="Integer NULL COMMENT 'ZtId'")
    private Integer ztId;
    /**----使用人---**/
    @Column(name="usedManId", columnDefinition="Integer NULL COMMENT '使用人'")
    private Integer usedManId;


    @Column(name="fundsSource", columnDefinition="Integer NULL COMMENT ''")
    private Integer fundsSource;

    /**----单据分类---**/
    @Column(name="kindId", columnDefinition="Integer NULL COMMENT '单据分类'")
    private Integer kindId;
    /**----type---**/
    @Column(name="typeId", columnDefinition="Integer NULL COMMENT 'type'")
    private Integer typeId;
    /**----duty---**/
    @Column(name="dutyId", columnDefinition="nvarchar(64) NULL COMMENT 'duty'")
    private String dutyId;
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
    /**----备注---**/
    @Column(name="memo", columnDefinition="nvarchar(200) NULL COMMENT '备注'")
    private String memo;
    /**----订单编码---**/
    @Column(name="orderNum", columnDefinition="NVARCHAR2(200) NULL COMMENT '订单编码'")
    private String orderNum;
    /**----供应商ID---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '供应商ID'")
    private Integer providerDepId;
    @Column(name="depName", columnDefinition="nvarchar(200) NULL COMMENT '备注'")
    private String depName;
    /**
     * ----extendInt1---
     **/
    @Column(name="extendInt1", columnDefinition="Integer NULL COMMENT 'extendInt'")
    private Integer extendInt1;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getApplyunitName() {
        return applyunitName;
    }

    public void setApplyunitName(String applyunitName) {
        this.applyunitName = applyunitName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUseUnitName() {
        return useUnitName;
    }

    public void setUseUnitName(String useUnitName) {
        this.useUnitName = useUnitName;
    }



    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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

    public String getExtendString1() {
        return extendString1;
    }

    public void setExtendString1(String extendString1) {
        this.extendString1 = extendString1;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public Integer getUsedDepartId() {
        return usedDepartId;
    }

    public void setUsedDepartId(Integer usedDepartId) {
        this.usedDepartId = usedDepartId;
    }

    public Integer getOfficesId() {
        return officesId;
    }

    public void setOfficesId(Integer officesId) {
        this.officesId = officesId;
    }

    public Integer getApplyDepartId() {
        return applyDepartId;
    }

    public void setApplyDepartId(Integer applyDepartId) {
        this.applyDepartId = applyDepartId;
    }

    public Integer getZtId() {
        return ztId;
    }

    public void setZtId(Integer ztId) {
        this.ztId = ztId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
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

    public String getExtendString2() {
        return extendString2;
    }

    public void setExtendString2(String extendString2) {
        this.extendString2 = extendString2;
    }

    public String getEXTENDSTRING3() {
        return EXTENDSTRING3;
    }

    public void setEXTENDSTRING3(String EXTENDSTRING3) {
        this.EXTENDSTRING3 = EXTENDSTRING3;
    }

    public String getEXTENDSTRING4() {
        return EXTENDSTRING4;
    }

    public void setEXTENDSTRING4(String EXTENDSTRING4) {
        this.EXTENDSTRING4 = EXTENDSTRING4;
    }

    public Integer getUsedManId() {
        return usedManId;
    }

    public void setUsedManId(Integer usedManId) {
        this.usedManId = usedManId;
    }

    public Integer getFundsSource() {
        return fundsSource;
    }

    public void setFundsSource(Integer fundsSource) {
        this.fundsSource = fundsSource;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getProviderDepId() {
        return providerDepId;
    }

    public void setProviderDepId(Integer providerDepId) {
        this.providerDepId = providerDepId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public Integer getExtendInt1() {
        return extendInt1;
    }

    public void setExtendInt1(Integer extendInt1) {
        this.extendInt1 = extendInt1;
    }

    public ManageApplyAndDetail() {
    }

}