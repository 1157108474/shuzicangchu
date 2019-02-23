package com.zthc.ewms.system.formTemplateManage.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author gwj
 * @version 1.0
 * @Title: d单据模板
 * @Package
 */
@Entity
@Table(name="SYS_FORMTEMPLATE")
public class FormTemplate implements Serializable {

    @Id
    @SequenceGenerator(name="generator",sequenceName="sysformtem_Sequence")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
    private int id;

    @Column(name = "processDefinitionKey")
    private String processDefinitionKey;//字典表中各流程的字典表ID

    @Column(name = "formTemCard")
    private String formTemCard;

    @Column(name = "formTemName")
    private String formTemName;

    @Column(name = "formTemPre")
    private String formTemPre;

    @Column(name = "formTemSta")
    private int formTemSta;

    @Column(name = "formTemCom")
    private String formTemCom;

    @Column(name = "formTemMenu")
    private String formTemMenu;

    @Column(name = "formTemDic")
    private int formTemDic;

    @Column(name = "ext1")
    private String ext1;

    @Column(name = "ext2")
    private String ext2;

    @Column(name = "ext3")
    private String ext3;

    @Column(name = "ext4")
    private String ext4;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getFormTemCard() {
        return formTemCard;
    }

    public void setFormTemCard(String formTemCard) {
        this.formTemCard = formTemCard;
    }

    public String getFormTemName() {
        return formTemName;
    }

    public void setFormTemName(String formTemName) {
        this.formTemName = formTemName;
    }

    public String getFormTemPre() {
        return formTemPre;
    }

    public void setFormTemPre(String formTemPre) {
        this.formTemPre = formTemPre;
    }

    public int getFormTemSta() {
        return formTemSta;
    }

    public void setFormTemSta(int formTemSta) {
        this.formTemSta = formTemSta;
    }

    public String getFormTemCom() {
        return formTemCom;
    }

    public void setFormTemCom(String formTemCom) {
        this.formTemCom = formTemCom;
    }

    public String getFormTemMenu() {
        return formTemMenu;
    }

    public void setFormTemMenu(String formTemMenu) {
        this.formTemMenu = formTemMenu;
    }

    public int getFormTemDic() {
        return formTemDic;
    }

    public void setFormTemDic(int formTemDic) {
        this.formTemDic = formTemDic;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public FormTemplate() {
    }

    public FormTemplate(String processDefinitionKey, String formTemCard, String formTemName, String formTemPre, int formTemSta, String formTemCom, String formTemMenu, int formTemDic, String ext1, String ext2, String ext3, String ext4) {
        this.processDefinitionKey = processDefinitionKey;
        this.formTemCard = formTemCard;
        this.formTemName = formTemName;
        this.formTemPre = formTemPre;
        this.formTemSta = formTemSta;
        this.formTemCom = formTemCom;
        this.formTemMenu = formTemMenu;
        this.formTemDic = formTemDic;
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.ext3 = ext3;
        this.ext4 = ext4;
    }


}
