package com.zthc.ewms.system.useDep.entity;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;


/**  
 * @Title: ���Ϸ�Χ
 * @Package 
 * @author f
 * @version 1.0
 */
@Entity
@Table(name="BASE_OFFICES_SCOPE")
public class OfficesScope {
  
  	/**----id---**/
  	@Id
    @Column(name="id", columnDefinition="integer NOT NULL ")
	@SequenceGenerator(name="generator",sequenceName="BASEOFFICESSCOPE_SEQUENCE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
    private Integer id;
  	/**----Ψһ��ʶ---**/
    @Column(name="guid", columnDefinition="nvarchar2(36) NULL ")
    private String guid;
  	/**----����ID---**/
	@Column(name = "officesId", columnDefinition = "integer NULL ")
	private Integer officesId;
  	/**----��Χ����---**/
	@Column(name = "scopeType", columnDefinition = "integer NULL ")
	private Integer scopeType;
  	/**----��ΧID---**/
	@Column(name = "scopeId", columnDefinition = "integer NULL ")
	private Integer scopeId;
  	/**----�����֯ID---**/
	@Column(name = "ztId", columnDefinition = "integer NULL ")
	private Integer ztId;
  	/**----��������---**/
    @Column(name="add_type", columnDefinition="integer NULL ")
    private Integer addType;
  	/**----������ID---**/
    @Column(name="creator", columnDefinition="integer NULL ")
    private Integer creator;
  	/**----����ʱ��---**/
  	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "createDate", columnDefinition = "date NULL ")
  	private Date createDate;

	@Transient
  	private String scopeName;

	@Transient
	private String scopeCode;

	public String getScopeCode() {
		return scopeCode;
	}

	public void setScopeCode(String scopeCode) {
		this.scopeCode = scopeCode;
	}

	public String getScopeName() {
		return scopeName;
	}

	public void setScopeName(String scopeName) {
		this.scopeName = scopeName;
	}

	public Integer getId(){
    	return id;   
  	} 
    
  	public void setId(Integer id){   
    	this.id = id;   
  	}  
    
  	public String getGuid(){   
    	return guid;   
  	} 
    
  	public void setGuid(String guid){   
    	this.guid = guid;   
  	}  
    
  	public Integer getOfficesId(){   
    	return officesId;   
  	} 
    
  	public void setOfficesId(Integer officesId){   
    	this.officesId = officesId;   
  	}  
    
  	public Integer getScopeType(){   
    	return scopeType;   
  	} 
    
  	public void setScopeType(Integer scopeType){   
    	this.scopeType = scopeType;   
  	}  
    
  	public Integer getScopeId(){   
    	return scopeId;   
  	} 
    
  	public void setScopeId(Integer scopeId){   
    	this.scopeId = scopeId;   
  	}  
    
  	public Integer getZtId(){   
    	return ztId;   
  	} 
    
  	public void setZtId(Integer ztId){   
    	this.ztId = ztId;   
  	}  
    
  	public Integer getAddType(){   
    	return addType;   
  	} 
    
  	public void setAddType(Integer addType){   
    	this.addType = addType;   
  	}  
    
  	public Integer getCreator(){   
    	return creator;   
  	} 
    
  	public void setCreator(Integer creator){   
    	this.creator = creator;   
  	}  
    
  	public Date getCreateDate(){   
    	return createDate;   
  	} 
    
  	public void setCreateDate(Date createDate){   
    	this.createDate = createDate;   
  	}  
    
  
  	public OfficesScope() {
	
  	}

	public OfficesScope(Integer id,Integer scopeId, String scopeName, String scopeCode) {
  		this.id = id;
		this.scopeId = scopeId;
		this.scopeName = scopeName;
		this.scopeCode = scopeCode;
	}
}