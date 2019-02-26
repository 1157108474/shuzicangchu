package com.zthc.ewms.system.dept.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.service.guard.DepartServiceGuard;

@Service
public class DepartService extends DepartServiceGuard {

	/**
	 * ��ȡ������֯������Ϣ
	 **/
	@Transactional
	public List<Map<String, Object>> listDepart(Depart obj) throws JsonProcessingException {
		List<Depart> departList = this.dao.listDepart(obj);
		List<Map<String, Object>> departs = new ArrayList<>();
		Map<String, Object> map;
		// ����֯����ƴװ��List<Map>��ʽ
		for (Depart depart : departList) {
			map = new HashMap();
			map.put("id", depart.getId());
			map.put("name", depart.getName());
			map.put("type", depart.getType());

			map.put("pId", null == depart.getParent() ? 0 : depart.getParent().getId());
			map.put("code", depart.getCode());
			map.put("levelCount", depart.getLevelCount());
			map.put("ztId", depart.getZtId());
			departs.add(map);
		}
		return departs;
	}

	/**
	 * ��ȡ������֯������Ϣ
	 **/
	@Transactional
	public List<Map<String, Object>> listPublicDepart(Depart obj) throws JsonProcessingException {
		List<Depart> departList = this.dao.listPublicDepart(obj);
		List<Map<String, Object>> departs = new ArrayList<>();
		Map<String, Object> map;
		// ����֯����ƴװ��List<Map>��ʽ
		for (Depart depart : departList) {
			map = new HashMap();
			map.put("id", depart.getId());
			map.put("name", depart.getName());
			map.put("type", depart.getType());

			map.put("pId", null == depart.getParent() ? 0 : depart.getParent().getId());
			map.put("code", depart.getCode());
			map.put("levelCount", depart.getLevelCount());
			map.put("ztId", depart.getZtId());
			departs.add(map);
		}
		return departs;
	}

	/**
	 * ��ȡ���в��ŵ�ID������
	 *
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Depart> listDepartName(int state) {
		return this.dao.listDepartName(state);
	}

	/**
	 * ��ȡ���в���
	 *
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Depart> listDepart(int state) {
		return this.dao.listDepart(state);
	}

	/**
	 * ���ݿ����֯ID��ȡ
	 *
	 * @param ztId
	 * @return
	 */
	@Transactional
	public Depart getDepartByZtId(Integer ztId) {
		return this.dao.getDepartByZtId(ztId);
	}
}
