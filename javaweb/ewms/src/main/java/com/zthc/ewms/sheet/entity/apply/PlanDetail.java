package com.zthc.ewms.sheet.entity.apply;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: �ƻ���ϸ
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_SLPLANDETAIL")
public class PlanDetail {
    /**
     * ----����ID---
     **/
    @Id
	@Column(name="id", columnDefinition="Integer NULL COMMENT '����'")
	private Integer id;
	/**----�ɹ��ƻ����---**/
    @Column(name="planCode", columnDefinition="nvarchar(50) NULL COMMENT '�ɹ��ƻ����'")
    private String planCode;

	/**----���ϱ���---**/
	@Column(name="materialCode", columnDefinition="nvarchar(64) NULL COMMENT '���ϱ���'")
	private String materialCode;
	/**----��������---**/
	@Column(name="materialDes", columnDefinition="nvarchar(255) NULL COMMENT '��������'")
	private String materialDes;
	/**----������λ---**/
	@Column(name="unIt", columnDefinition="nvarchar(50) NULL COMMENT '������λ'")
	private String unIt;
	/**----�ƻ�����---**/
	@Column(name="planCount", columnDefinition="number(18,9) NULL COMMENT '�ɹ�����'")
	private double planCount;

	/**----����������---**/
	@Column(name="haveslCount", columnDefinition="Integer NULL COMMENT '����������'")
	private Integer haveslCount;
	/**----��������---**/
	@Column(name="storeuseCount", columnDefinition="Integer NULL COMMENT '��������'")
	private Integer storeuseCount;
	/**----�����---**/
	@Column(name="storeCount", columnDefinition="Integer NULL COMMENT '�����'")
	private Integer storeCount;
	/**----�ƻ�ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="planDate", columnDefinition="date NULL COMMENT '�ƻ�ʱ��'")
	private Date planDate;

	/**----ʹ�õ�λId---**/
	@Column(name="useDepId", columnDefinition="Integer NULL COMMENT 'ʹ�õ�λId'")
	private Integer useDepId;
	/**----ʹ�õ�λ����---**/
	@Column(name="userDepName", columnDefinition="nvarchar(64) NULL COMMENT 'ʹ�õ�λ����'")
	private String userDepName;
	/**----ZTID---**/
	@Column(name="ztId", columnDefinition="Integer NULL COMMENT 'ZTID'")
	private Integer ztId;
	/**----�ƻ�����---**/
	@Column(name="deptName", columnDefinition="nvarchar(64) NULL COMMENT '�ƻ�����'")
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