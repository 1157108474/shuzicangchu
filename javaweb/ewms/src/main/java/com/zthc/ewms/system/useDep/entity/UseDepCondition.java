package com.zthc.ewms.system.useDep.entity;
import com.zthc.ewms.base.util.Condition;

/**
 * ������ݿ��������
 *
 */
public class UseDepCondition extends Condition{

	public Integer ztId;

	public Integer id;

	public Integer[] ids;

	public Integer getZtId() {
		return ztId;
	}

	public void setZtId(Integer ztId) {
		this.ztId = ztId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
}