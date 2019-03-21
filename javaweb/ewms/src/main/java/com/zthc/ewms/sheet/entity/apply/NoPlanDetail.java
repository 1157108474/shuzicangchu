package com.zthc.ewms.sheet.entity.apply;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: 无计划明细
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_SLNOPLANDETAIL")
public class NoPlanDetail {
  	/**----单据ID---**/
	@Id
	@Column(name="RN", columnDefinition="Integer NULL COMMENT '主键'")
	private Integer id;
	/**----物料编码---**/
	@Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '物料编码'")
	private String materialCode;
	/**----物料ID---**/
	@Column(name="materialId", columnDefinition="Integer NULL COMMENT '物料ID'")
	private Integer materiaId;
	/**----物料名称---**/
	@Column(name="materialName", columnDefinition="nvarchar(255) NULL COMMENT '物料名称'")
	private String materialName;
	/**----物料描述---**/
	@Column(name="descripTion", columnDefinition="nvarchar(500) NULL COMMENT '物料描述'")
	private String descripTion;
	/**----单位名称---**/
	@Column(name="detailUnitName", columnDefinition="nvarchar(20) NULL COMMENT '单位名称'")
	private String detailUnitName;

	/**----officesId---**/
	@Column(name="officesId", columnDefinition="Integer NULL COMMENT 'officesId'")
	private Integer officesId;
	/**----库存可用量---**/
	@Column(name="storeuseCount", columnDefinition="Integer NULL COMMENT '库存可用量'")
	private Double storeuseCount;
	/**----库存量---**/
	@Column(name="storeCount", columnDefinition="Integer NULL COMMENT '库存量'")
	private Double storeCount;
	/**----ZTID---**/
	@Column(name="ztId", columnDefinition="Integer NULL COMMENT 'ZTID'")
	private Integer ztId;
    @Transient
    private Integer applyCount;
    @Transient
    private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public Integer getMateriaId() {
		return materiaId;
	}

	public void setMateriaId(Integer materiaId) {
		this.materiaId = materiaId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getDescripTion() {
		return descripTion;
	}

	public void setDescripTion(String descripTion) {
		this.descripTion = descripTion;
	}

	public String getDetailUnitName() {
		return detailUnitName;
	}

	public void setDetailUnitName(String detailUnitName) {
		this.detailUnitName = detailUnitName;
	}

	public Integer getOfficesId() {
		return officesId;
	}

	public void setOfficesId(Integer officesId) {
		this.officesId = officesId;
	}

	public Double getStoreuseCount() {
		return storeuseCount;
	}

	public void setStoreuseCount(Double storeuseCount) {
		this.storeuseCount = storeuseCount;
	}

	public Double getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(Double storeCount) {
		this.storeCount = storeCount;
	}

	public Integer getZtId() {
		return ztId;
	}

	public void setZtId(Integer ztId) {
		this.ztId = ztId;
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

	public NoPlanDetail() {
	}
}