package com.zthc.ewms.system.print.entity.guard;

import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

/**  
 * @Title: 打印
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="base_formprint")
public class Print {
  
  	/**----主键---**/
	@Id
	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '主键'")
	@SequenceGenerator(name="generator",sequenceName="base_formprint_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
  	private Integer id;
	/**----报表父级类型---**/
	@Column(name="reportparenttype")
	private Integer reportParenType;

  	/**----报表名称---**/
    @Column(name="reportname")
    private String reportName;

  	/**----报表备注---**/
    @Column(name="reportmemo")
    private String reportMemo;

	/**----报表用户---**/
	@Column(name="reportuser")
	private String reportUser;

	/**----报表数据---**/
	@Column(name="reportdata")
	private Blob reportData;

	/**----单据数据集---**/
	@Column(name="sheetdataset")
	private String sheetDataSet;

	/**----明细数据集---**/
	@Column(name="detaildataset")
	private String detailDataSet;


	/**----审核数据集---**/
	@Column(name="aduitdataset")
	private String aduitDataSet;
	/**----报表状态---**/
	@Column(name="reportStatus")
	private Integer reportStatus;
	/**----报表状态---**/
	@Column(name="sort")
	private Integer sort;

	/**----接口种类---**/
    @Column(name="inf_kind", columnDefinition="Integer NULL COMMENT '接口种类'")
    private Integer kind;


	/**----创建人---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '创建人'")
	private Integer creator;
	/**----创建时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="createDate", columnDefinition="date NULL COMMENT '创建时间'")
	private Date createDate;
	/**----更新人---**/
	@Column(name="updater", columnDefinition="Integer NULL COMMENT '更新人'")
	private Integer updater;
	/**----更新时间---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="updateDate", columnDefinition="date NULL COMMENT '更新时间'")
	private Date updateDate;

	/**----code---**/
	@Column(name="reportcode")
	private String reportCode;


	@Column(name = "extendstring1", columnDefinition = "nvarchar(255) NULL ")
	private String extendString1;
	@Column(name = "extendstring2", columnDefinition = "nvarchar(255) NULL ")
	private String extendString2;
	@Column(name = "extendstring3", columnDefinition = "nvarchar(255) NULL ")
	private String extendString3;
	@Column(name = "extendstring4", columnDefinition = "nvarchar(255) NULL ")
	private String extendString4;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReportParenType() {
		return reportParenType;
	}

	public void setReportParenType(Integer reportParenType) {
		this.reportParenType = reportParenType;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportMemo() {
		return reportMemo;
	}

	public void setReportMemo(String reportMemo) {
		this.reportMemo = reportMemo;
	}

	public String getReportUser() {
		return reportUser;
	}

	public void setReportUser(String reportUser) {
		this.reportUser = reportUser;
	}

	public Blob getReportData() {
		return reportData;
	}

	public void setReportData(Blob reportData) {
		this.reportData = reportData;
	}

	public String getSheetDataSet() {
		return sheetDataSet;
	}

	public void setSheetDataSet(String sheetDataSet) {
		this.sheetDataSet = sheetDataSet;
	}

	public String getDetailDataSet() {
		return detailDataSet;
	}

	public void setDetailDataSet(String detailDataSet) {
		this.detailDataSet = detailDataSet;
	}

	public String getAduitDataSet() {
		return aduitDataSet;
	}

	public void setAduitDataSet(String aduitDataSet) {
		this.aduitDataSet = aduitDataSet;
	}

	public Integer getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
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

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
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

	public Print() {
	
  	}

	public Print(Integer id,String reportName, String reportMemo) {
		this.id = id;
		this.reportName = reportName;
		this.reportMemo = reportMemo;
	}
}