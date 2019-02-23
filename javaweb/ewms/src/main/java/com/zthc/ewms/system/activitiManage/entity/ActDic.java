package com.zthc.ewms.system.activitiManage.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author gwj
 * @version 1.0
 * @Title: 数据字典关联流程model
 * @Package
 */
@Entity
@Table(name="BASE_DIC_ACT")
public class ActDic implements Serializable {
    /**
     * ----主键---
     **/
    @Id
    @SequenceGenerator(name="generator",sequenceName="actdic_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
    private Integer id;
    /**
     * ----dicID---
     **/
    @Column(name="dicID")
    private String dicID;
    /**
     * ----actModelID---
     **/
    @Column(name = "modelID")
    private String modelID;
    /*
    --depId
    * */
    @Column(name = "DEPID")
    private String depId;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDicID() {
        return dicID;
    }

    public void setDicID(String dicID) {
        this.dicID = dicID;
    }

    public String getModelID() {
        return modelID;
    }

    public void setModelID(String modelID) {
        this.modelID = modelID;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public ActDic(String dicID, String modelID, String depId) {
        this.dicID = dicID;
        this.modelID = modelID;
        this.depId = depId;
    }

    public ActDic() {
    }
}
