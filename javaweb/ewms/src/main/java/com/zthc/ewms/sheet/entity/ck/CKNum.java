package com.zthc.ewms.sheet.entity.ck;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**  
 * @Title: ���쵥��
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_CKNUM")
public class CKNum {
	@Id
	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '����'")
	private Integer id;
	/**----����Code---**/
	@Column(name="CKSHEETCODE", columnDefinition="nvarchar(255) NULL COMMENT '����Code'")
	private String cksheetCode;
	/**----ʹ�õ�λID---**/
	@Column(name="usedDepartId", columnDefinition="Integer NULL COMMENT 'ʹ�õ�λID'")
	private Integer usedDepartId;
	/**----ʹ�õ�λ---**/
	@Column(name="useddepartname", columnDefinition="nvarchar(200) NULL COMMENT 'ʹ�õ�λ'")
	private String useddepartname;

	/**----���Ϸ�ΧID---**/
	@Column(name="officesId", columnDefinition="Integer NULL COMMENT '���Ϸ�ΧID'")
	private Integer officesId;
	/**----���Ϸ�Χ����---**/
    @Column(name = "officesName", columnDefinition = "nvarchar(200) NULL COMMENT '���Ϸ�Χ����'")
    private String officesName;
    /**
     * ----�ʽ���Դ---
     **/
    @Column(name="fundsSource", columnDefinition="Integer NULL COMMENT ''")
	private Integer fundsSource;
	/**----�ʽ���Դ����---**/
	@Column(name="fundsSourceName", columnDefinition="nvarchar(255) NULL ")
	private String fundsSourceName;

	/**----��;---**/
	@Column(name="extendString1", columnDefinition="nvarchar(255) NULL ")
	private String extendString1;

	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="createDate", columnDefinition="date NULL COMMENT '����ʱ��'")
	private Date createDate;

	/**----���ϵ�Id---**/
	@Column(name="LLSHEETCODE", columnDefinition="nvarchar(64) NULL COMMENT '���ϵ�Id'")
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