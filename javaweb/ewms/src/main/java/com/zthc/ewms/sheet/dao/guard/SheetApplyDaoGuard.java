package com.zthc.ewms.sheet.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;

import com.zthc.ewms.sheet.entity.apply.ManageApply;
import drk.util.TextUtil;

import javax.annotation.Resource;
import java.util.Date;

public class SheetApplyDaoGuard {

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
	 * �����ݴ��뵱ǰ�̣߳����أ�����
	 * @param local ���úù���ֵ�Ķ���
	 */
	public static void setThreadLocal(BaseLocal local) {
		threadLocal.set(local);
	}
	
	/*-------------------------------------------------��ʼ��������-------------------------------------------------*/
	/**
	 * ��ȡ��������
	 *
	 * @param sheetId
	 * @return
	 */
	public ManageApply getApplyOne(Integer sheetId) {
		ManageApply ret = null;
		if (!TextUtil.isNotNull(sheetId)) {
			ret = new ManageApply();
		} else {
			ret = baseDao.getById(ManageApply.class, sheetId);
		}
		return ret;
	}

	
/*-------------------------------------------------������������-------------------------------------------------*/	
  	
  	//ɾ������
	
	//�޸ġ����·���
	
	//��ѯ����
	
} 