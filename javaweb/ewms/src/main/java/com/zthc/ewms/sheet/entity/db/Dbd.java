package com.zthc.ewms.sheet.entity.db;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**  
 * @Title: 单据表
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="V_GETDBD")
public class Dbd {

  	/**----主键---**/
	@Id
	@GeneratedValue(generator="customKey")
  	@GenericGenerator(name="customKey", strategy="com.hckj.base.database.hibernate.CustomKey")
  	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '主键'")
  	private Integer id;

  	/**----编码---**/
    @Column(name="code", columnDefinition="nvarchar(64) NULL COMMENT '编码'")
    private String code;

    @Column(name="dcZTID")
    private Integer dcZtid;
	@Column(name="drZTID")
	private Integer drZtid;
	@Column(name="ztId")
	private Integer ztid;

	@Column(name="jhCount")
	private Double jhCount;

	@Column(name="ckCount")
	private Double ckCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Dbd() {
	
  	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDcZtid() {
		return dcZtid;
	}

	public void setDcZtid(Integer dcZtid) {
		this.dcZtid = dcZtid;
	}

	public Integer getDrZtid() {
		return drZtid;
	}

	public void setDrZtid(Integer drZtid) {
		this.drZtid = drZtid;
	}

	public Integer getZtid() {
		return ztid;
	}

	public void setZtid(Integer ztid) {
		this.ztid = ztid;
	}

	public Double getJhCount() {
		return jhCount;
	}

	public void setJhCount(Double jhCount) {
		this.jhCount = jhCount;
	}

	public Double getCkCount() {
		return ckCount;
	}

	public void setCkCount(Double ckCount) {
		this.ckCount = ckCount;
	}
}