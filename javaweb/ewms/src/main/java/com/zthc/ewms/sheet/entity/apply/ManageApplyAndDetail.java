package com.zthc.ewms.sheet.entity.apply;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.util.Date;

/**  
 * @Title: �������쵥����
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_FETCHGOODSHEET_DETAILS")
public class ManageApplyAndDetail {
	

	
  	/**----����ID---**/
    @Column(name="sheetId", columnDefinition="Integer NULL COMMENT '����ID'")
    private Integer sheetId;
	/**----���ϱ���---**/
	@Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '���ϱ���'")
	private String materialCode;
	/**----����---**/
	@Column(name="description", columnDefinition="nvarchar(500) NULL COMMENT '����'")
	private String description;
	/***--��λ--**/
	@Column(name="es1", columnDefinition="nvarchar(255) NULL ")
	private String es1;
	/**----��λ����---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '��λ����'")
	private String detailUnitName;
	/**----������ϸID---**/
	@Column(name="sheetDetailId", columnDefinition="Integer NULL COMMENT '������ϸID'")
	private Integer sheetDetailId;
	/**----��ϸ����---**/
	@Column(name="detailCount", columnDefinition="number(18,9) NULL COMMENT '��ϸ����'")
	private Double detailCount;
	/**----���к�---**/
	@Column(name="snCode", columnDefinition="nvarchar(50) NULL COMMENT '���к�'")
	private String snCode;
	/**----����ID---**/
	@Column(name="materialId", columnDefinition="Integer NULL COMMENT '����ID'")
	private Integer materialId;

	@Column(name="detailUnit", columnDefinition="Integer NULL COMMENT '״̬'")
	private Integer detailUnit;
    @Column(name = "es2", columnDefinition = "nvarchar(255) NULL ")
    private String es2;
    /**----�ѳ�������---**/
	@Column(name="ckCount", columnDefinition="Integer NULL COMMENT '�ѳ�������'")
	private Integer ckCount;

	@Column(name="iztid", columnDefinition="Integer NULL COMMENT '����ID'")
	private Integer iztId;

	@Column(name="extendInt7", columnDefinition="Integer NULL COMMENT 'ERPDetailId'")
	private Integer extendInt7;
    @Transient
    private Integer applyCount;
    @Transient
    private String address;
    //�ⷿid������ͼ��ͬ�����
	@Column(name="storeId", columnDefinition="Integer NULL COMMENT '�ⷿid'")
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
    @Column(name="id", columnDefinition="Integer NOT NULL COMMENT '����'")
    private Integer id;
    /**----GUID---**/
    @Column(name="GUID", columnDefinition="nvarchar(64) NULL COMMENT 'GUID'")
    private String GUID;
    /**----NAME---**/
    @Column(name="NAME", columnDefinition="nvarchar(255) NULL COMMENT 'NAME'")
    private String NAME;
    /**----����---**/
    @Column(name="code", columnDefinition="nvarchar(64) NULL COMMENT '����'")
    private String code;
    /**----ʹ�õ�λID---**/
    @Column(name="usedDepartId", columnDefinition="Integer NULL COMMENT 'ʹ�õ�λID'")
    private Integer usedDepartId;
    /**----���Ϸ�ΧID---**/
    @Column(name="officesId", columnDefinition="Integer NULL COMMENT '���Ϸ�ΧID'")
    private Integer officesId;
    /**----���Ϸ�Χ����---**/
    @Column(name="officeName", columnDefinition="nvarchar(200) NULL COMMENT '���Ϸ�Χ����'")
    private String officeName;

    /**----���뵥λID---**/
    @Column(name="applyDepartId", columnDefinition="Integer NULL COMMENT '���뵥λID'")
    private Integer applyDepartId;
    /**----applyunitName---**/
    @Column(name="applyunitName", columnDefinition="nvarchar(200) NULL COMMENT 'applyunitName'")
    private String applyunitName;
    /**----״̬---**/
    @Column(name="status", columnDefinition="Integer NULL COMMENT '״̬'")
    private Integer status;
    /**----״̬����---**/
    @Column(name="statusName", columnDefinition="nvarchar(64) NULL COMMENT '״̬����'")
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
    /**----������---**/
    @Column(name="creator", columnDefinition="Integer NULL COMMENT '������'")
    private Integer creator;
    /**----����ʱ��---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="createDate", columnDefinition="date NULL COMMENT '����ʱ��'")
    private Date createDate;

    @Transient
    private String createDateStr;
    /**----������---**/
    @Column(name="updator", columnDefinition="Integer NULL COMMENT '������'")
    private Integer updater;
    /**----����ʱ��---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="updateDate", columnDefinition="date NULL COMMENT '����ʱ��'")
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
    /**----ʹ����---**/
    @Column(name="usedManId", columnDefinition="Integer NULL COMMENT 'ʹ����'")
    private Integer usedManId;


    @Column(name="fundsSource", columnDefinition="Integer NULL COMMENT ''")
    private Integer fundsSource;

    /**----���ݷ���---**/
    @Column(name="kindId", columnDefinition="Integer NULL COMMENT '���ݷ���'")
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
    /**----�ύ��ID---**/
    @Column(name="submitManId", columnDefinition="Integer NULL COMMENT '�ύ��ID'")
    private Integer submitManId;
    /**----�ύʱ��---**/
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="submitTime", columnDefinition="date NULL COMMENT '�ύʱ��'")
    private Date submitTime;
    /**----��ע---**/
    @Column(name="memo", columnDefinition="nvarchar(200) NULL COMMENT '��ע'")
    private String memo;
    /**----��������---**/
    @Column(name="orderNum", columnDefinition="NVARCHAR2(200) NULL COMMENT '��������'")
    private String orderNum;
    /**----��Ӧ��ID---**/
    @Column(name="providerDepId", columnDefinition="Integer NULL COMMENT '��Ӧ��ID'")
    private Integer providerDepId;
    @Column(name="depName", columnDefinition="nvarchar(200) NULL COMMENT '��ע'")
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