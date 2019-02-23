package com.zthc.ewms.sheet.entity.file;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "wz_attachfile")
public class AttachFile {

    @Id
    @Column(name = "id", columnDefinition = "Integer NOT NULL COMMENT 'id'")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "WZFILE_SEQUENCE", allocationSize = 1)
    private Integer id;

    /**
     * ----唯一标识---
     **/
    @Column(name = "guid", columnDefinition = "nvarchar2(36) NULL COMMENT '唯一标识'")
    private String guid;

    /**
     * ----文件名称---
     **/
    @Column(name = "fileName", columnDefinition = "nvarchar2(255) NULL COMMENT '单据名称'")
    private String fileName;

    /**
     * ----文件别名---
     **/
    @Column(name = "fileAliasName", columnDefinition = "nvarchar2(255) NULL COMMENT '单据名称'")
    private String fileAliasName;

    /**
     * ----文件后缀---
     **/
    @Column(name = "fileExt", columnDefinition = "nvarchar2(255) NULL COMMENT '单据名称'")
    private String fileExt;

    /**
     * ----文件类型---
     **/
    @Column(name = "fileType", columnDefinition = "nvarchar2(255) NULL COMMENT '单据名称'")
    private String fileType;

    /**
     * ----文件路径---
     **/
    @Column(name = "filePath", columnDefinition = "nvarchar2(255) NULL COMMENT '单据名称'")
    private String filePath;

    /**
     * ----备注---
     **/
    @Column(name = "memo", columnDefinition = "nvarchar2(255) NULL COMMENT '单据名称'")
    private String memo;

    /**
     * ----状态---
     **/
    @Column(name = "status", columnDefinition = "Integer NULL COMMENT '分类ID'")
    private Integer status;

    /**
     * ----创建人---
     **/
    @Column(name = "creator", columnDefinition = "Integer NULL COMMENT '分类ID'")
    private Integer creator;

    /**
     * ----创建人姓名---
     **/
    @Transient
    private String creatorName;

    /**
     * ----创建时间---
     **/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createDate", columnDefinition = "datetime NULL COMMENT '提交时间'")
    private Date createDate;

    /**
     * ----关联单据ID---
     **/
    @Column(name = "attachRelateId", columnDefinition = "Integer NULL COMMENT '分类ID'")
    private Integer attachRelateId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileAliasName() {
        return fileAliasName;
    }

    public void setFileAliasName(String fileAliasName) {
        this.fileAliasName = fileAliasName;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getAttachRelateId() {
        return attachRelateId;
    }

    public void setAttachRelateId(Integer attachRelateId) {
        this.attachRelateId = attachRelateId;
    }

    public AttachFile(Integer id, String fileName, String fileAliasName, String fileExt, String fileType, String
            filePath, String
            memo, Integer status, String creatorName, Date createDate, Integer attachRelateId) {
        this.id = id;
        this.fileName = fileName;
        this.fileAliasName = fileAliasName;
        this.fileExt = fileExt;
        this.fileType = fileType;
        this.filePath = filePath;
        this.memo = memo;
        this.status = status;
        this.creatorName = creatorName;
        this.createDate = createDate;
        this.attachRelateId = attachRelateId;
    }

    public AttachFile() {
    }
}
