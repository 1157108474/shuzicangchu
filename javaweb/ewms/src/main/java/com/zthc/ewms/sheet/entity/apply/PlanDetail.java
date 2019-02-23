package com.zthc.ewms.sheet.entity.apply;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: 计划明细
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_SLPLANDETAIL")
public class PlanDetail {
    /**
     * ----单据ID---
     **/
    @Id
	@Column(name="id", columnDefinition="Integer NULL COMMENT '主键'")
	private Integer id;
	/**----采购计划编号---**/
    @Column(name="planCode", columnDefinition="nvarchar(50) NULL COMMENT '采购计划编号'")
    private String planCode;

	/**----物料编码---**/
	@Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '物料编码'")
	private String materialCode;
	/**----物料描述---**/
	@Column(name="materialDes", columnDefinition="nvarchar(255) NULL COMMENT '物料描述'")
	private String materialDes;
	/**----计量单位---**/
	@Column(name="unIt", columnDefinition="nvarchar(50) NULL COMMENT '计量单位'")
	private String unIt;
	/**----计划数量---**/
	@Column(name="planCount", columnDefinition="number(18,9) NULL COMMENT '采购数量'")
	private double planCount;

	/**----已申领数量---**/
	@Column(name="haveslCount", columnDefinition="Integer NULL COMMENT '已申领数量'")
	private Integer haveslCount;
	/**----库存可用量---**/
	@Column(name="storeuseCount", columnDefinition="Integer NULL COMMENT '库存可用量'")
	private Integer storeuseCount;
	/**----库存量---**/
	@Column(name="storeCount", columnDefinition="Integer NULL COMMENT '库存量'")
	private Integer storeCount;
	/**----计划时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="planDate", columnDefinition="date NULL COMMENT '计划时间'")
	private Date planDate;

	/**----使用单位Id---**/
	@Column(name="useDepId", columnDefinition="Integer NULL COMMENT '使用单位Id'")
	private Integer useDepId;
	/**----使用单位名称---**/
	@Column(name="userDepName", columnDefinition="nvarchar(64) NULL COMMENT '使用单位名称'")
	private String userDepName;
	/**----ZTID---**/
	@Column(name="ztId", columnDefinition="Integer NULL COMMENT 'ZTID'")
	private Integer ztId;
	/**----计划部门---**/
	@Column(name="deptName", columnDefinition="nvarchar(64) NULL COMMENT '计划部门'")
	private String deptName;

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

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialDes() {
		return materialDes;
	}

	public void setMaterialDes(String materialDes) {
		this.materialDes = materialDes;
	}

	public String getUnIt() {
		return unIt;
	}

	public void setUnIt(String unIt) {
		this.unIt = unIt;
	}

	public double getPlanCount() {
		return planCount;
	}

	public void setPlanCount(double planCount) {
		this.planCount = planCount;
	}

	public Integer getHaveslCount() {
		return haveslCount;
	}

	public void setHaveslCount(Integer haveslCount) {
		this.haveslCount = haveslCount;
	}

	public Integer getStoreuseCount() {
		return storeuseCount;
	}

	public void setStoreuseCount(Integer storeuseCount) {
		this.storeuseCount = storeuseCount;
	}

	public Integer getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(Integer storeCount) {
		this.storeCount = storeCount;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public Integer getUseDepId() {
		return useDepId;
	}

	public void setUseDepId(Integer useDepId) {
		this.useDepId = useDepId;
	}

	public String getUserDepName() {
		return userDepName;
	}

	public void setUserDepName(String userDepName) {
		this.userDepName = userDepName;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public PlanDetail() {
    }
}