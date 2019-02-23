package com.zthc.ewms.sheet.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.sheet.entity.apply.ManageApply;
import com.zthc.ewms.sheet.entity.ck.VCKList;
import com.zthc.ewms.sheet.entity.guard.SheetCK;
import com.zthc.ewms.sheet.entity.guard.SheetCKCondition;
import com.zthc.ewms.sheet.entity.guard.SheetCKDETAILCondition;
import drk.util.TextUtil;
import org.hibernate.Query;

import javax.annotation.Resource;

public class SheetCKDaoGuard {

	@Resource
	public BaseDao baseDao;
	
	//�ֲ߳̾����������ڴ�ŵ�ǰ�û���Ϣ������
	private static final ThreadLocal<BaseLocal> threadLocal = new ThreadLocal<BaseLocal>();
	
	/**
	 * �õ���ǰ�̣߳����أ����ݶ���
	 * @return
	 */
	public static BaseLocal getThreadLocal() {
		if(threadLocal.get() == null){
			return new BaseLocal();
		}
		else{
			return threadLocal.get();
		}
	}
	/**
	 * ��ȡ��������
	 *
	 * @param id
	 * @return
	 */
	public VCKList getVCKListOne(Integer id) {
		VCKList ret = null;
		if (!TextUtil.isNotNull(id)) {
			ret = new VCKList();
		} else {
			ret = baseDao.getById(VCKList.class, id);
		}
		return ret;
	}
	/**
	 * ��ȡ��������
	 *
	 * @param id
	 * @return
	 */
	public SheetCK getSheetCKOne(Integer id) {
		SheetCK ret = null;
		if (!TextUtil.isNotNull(id)) {
			ret = new SheetCK();
		} else {
			ret = baseDao.getById(SheetCK.class, id);
		}
		return ret;
	}
	public void delSheetCK(Integer id) {
		baseDao.removeById(SheetCK.class, id);
	}
	/**
	 * �����ݴ��뵱ǰ�̣߳����أ�����
	 * @param local ���úù���ֵ�Ķ���
	 */
	public static void setThreadLocal(BaseLocal local) {
		threadLocal.set(local);
	}
	
	/*-------------------------------------------------��ʼ��������-------------------------------------------------*/
	
/*-------------------------------------------------������������-------------------------------------------------*/	
  	
  	/*
	 * ɾ����ϸ����
     **/
	public int delDetails(SheetCKDETAILCondition condition) {
		String hql = "delete SheetCKDETAIL where id in :ids";
		Query query = baseDao.createQuery(hql);
		query.setParameterList("ids", condition.getIds());
		return query.executeUpdate();
	}
	/*
	 * ɾ����ϸ����
     **/
	public int delSheetDetails(Integer sheetId) {
		String hql = "delete SheetCKDETAIL where sheetId = :sheetId";
		Query query = baseDao.createQuery(hql);
		query.setParameter("sheetId", sheetId);
		return query.executeUpdate();
	}
	//�޸ġ����·���
	
	//��ѯ����
	
} 