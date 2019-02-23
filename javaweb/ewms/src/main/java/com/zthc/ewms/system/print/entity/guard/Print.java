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
 * @Title: ��ӡ
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="base_formprint")
public class Print {
  
  	/**----����---**/
	@Id
	@Column(name="id", columnDefinition="Integer NOT NULL COMMENT '����'")
	@SequenceGenerator(name="generator",sequenceName="base_formprint_SEQUENCE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
  	private Integer id;
	/**----����������---**/
	@Column(name="reportparenttype")
	private Integer reportParenType;

  	/**----��������---**/
    @Column(name="reportname")
    private String reportName;

  	/**----����ע---**/
    @Column(name="reportmemo")
    private String reportMemo;

	/**----�����û�---**/
	@Column(name="reportuser")
	private String reportUser;

	/**----��������---**/
	@Column(name="reportdata")
	private Blob reportData;

	/**----�������ݼ�---**/
	@Column(name="sheetdataset")
	private String sheetDataSet;

	/**----��ϸ���ݼ�---**/
	@Column(name="detaildataset")
	private String detailDataSet;


	/**----������ݼ�---**/
	@Column(name="aduitdataset")
	private String aduitDataSet;
	/**----����״̬---**/
	@Column(name="reportStatus")
	private Integer reportStatus;
	/**----����״̬---**/
	@Column(name="sort")
	private Integer sort;

	/**----�ӿ�����---**/
    @Column(name="inf_kind", columnDefinition="Integer NULL COMMENT '�ӿ�����'")
    private Integer kind;


	/**----������---**/
	@Column(name="creator", columnDefinition="Integer NULL COMMENT '������'")
	private Integer creator;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="createDate", columnDefinition="date NULL COMMENT '����ʱ��'")
	private Date createDate;
	/**----������---**/
	@Column(name="updater", columnDefinition="Integer NULL COMMENT '������'")
	private Integer updater;
	/**----����ʱ��---**/
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="updateDate", columnDefinition="date NULL COMMENT '����ʱ��'")
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