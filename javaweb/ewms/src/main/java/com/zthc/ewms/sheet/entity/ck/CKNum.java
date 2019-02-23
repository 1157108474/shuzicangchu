package com.zthc.ewms.sheet.entity.ck;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**  
 * @Title: 申领单号
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_CKNUM")
public class CKNum {
	@Id
	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '主键'")
	private Integer id;
	/**----出库Code---**/
	@Column(name="CKSHEETCODE", columnDefinition="nvarchar(255) NULL COMMENT '出库Code'")
	private String cksheetCode;
	/**----使用单位ID---**/
	@Column(name="usedDepartId", columnDefinition="Integer NULL COMMENT '使用单位ID'")
	private Integer usedDepartId;
	/**----使用单位---**/
	@Column(name="useddepartname", columnDefinition="nvarchar(200) NULL COMMENT '使用单位'")
	private String useddepartname;

	/**----物料范围ID---**/
	@Column(name="officesId", columnDefinition="Integer NULL COMMENT '物料范围ID'")
	private Integer officesId;
	/**----物料范围名称---**/
    @Column(name = "officesName", columnDefinition = "nvarchar(200) NULL COMMENT '物料范围名称'")
    private String officesName;
    /**
     * ----资金来源---
     **/
    @Column(name="fundsSource", columnDefinition="Integer NULL COMMENT ''")
	private Integer fundsSource;
	/**----资金来源名称---**/
	@Column(name="fundsSourceName", columnDefinition="nvarchar(255) NULL ")
	private String fundsSourceName;

	/**----用途---**/
	@Column(name="extendString1", columnDefinition="nvarchar(255) NULL ")
	private String extendString1;

	/**----创建时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="createDate", columnDefinition="date NULL COMMENT '创建时间'")
	private Date createDate;

	/**----领料单Id---**/
	@Column(name="LLSHEETCODE", columnDefinition="nvarchar(64) NULL COMMENT '领料单Id'")
	private String llSheetCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCksheetCode() {
		return cksheetCode;
	}

	public void setCksheetCode(String cksheetCode) {
		this.cksheetCode = cksheetCode;
	}

	public Integer getUsedDepartId() {
		return usedDepartId;
	}

	public void setUsedDepartId(Integer usedDepartId) {
		this.usedDepartId = usedDepartId;
	}

	public String getUseddepartname() {
		return useddepartname;
	}

	public void setUseddepartname(String useddepartname) {
		this.useddepartname = useddepartname;
	}

	public Integer getOfficesId() {
		return officesId;
	}

	public void setOfficesId(Integer officesId) {
		this.officesId = officesId;
    }

    public String getOfficesName() {
        return officesName;
    }

    public void setOfficesName(String officesName) {
        this.officesName = officesName;
    }

	public Integer getFundsSource() {
		return fundsSource;
	}

	public void setFundsSource(Integer fundsSource) {
		this.fundsSource = fundsSource;
	}

	public String getFundsSourceName() {
		return fundsSourceName;
	}

	public void setFundsSourceName(String fundsSourceName) {
		this.fundsSourceName = fundsSourceName;
	}

	public String getExtendString1() {
		return extendString1;
	}

	public void setExtendString1(String extendString1) {
		this.extendString1 = extendString1;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLlSheetCode() {
		return llSheetCode;
	}

	public void setLlSheetCode(String llSheetCode) {
		this.llSheetCode = llSheetCode;
	}

	public CKNum() {
	}
}