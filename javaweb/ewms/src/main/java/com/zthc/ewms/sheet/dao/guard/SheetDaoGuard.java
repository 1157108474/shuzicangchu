package com.zthc.ewms.sheet.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.sheet.entity.guard.Sheet;
import com.zthc.ewms.sheet.entity.guard.SheetCK;
import com.zthc.ewms.sheet.entity.guard.SheetCondition;
import com.zthc.ewms.sheet.entity.guard.SheetStock;
import drk.util.TextUtil;

import javax.annotation.Resource;
import java.util.Date;

public class SheetDaoGuard {    

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
	
	/**���浥������Ҫ��������**/
	public void saveSheet(Sheet obj, SheetCondition condition) {
		Date now = new Date();
		//�õ���ǰ�û�ID
		long currentUserId = getThreadLocal().getCurrentUserId();
		
		//�Զ�������ʱ��ʹ�����
		
		//�Զ���������Ĭ��ֵ�������ֶΣ�
		
		//������������
		baseDao.save(obj);
	}
	public void saveSheetCK(SheetCK obj, SheetCondition condition) {
		Date now = new Date();
		long currentUserId = getThreadLocal().getCurrentUserId();
		baseDao.save(obj);
	}

	/**ɾ����������ʵɾ����������**/
     public void delSheet(Integer id) {
         baseDao.removeById(Sheet.class, id);
     }

	/**��ȡ��������Ҫ�����������޸�**/
	public Sheet getSheetOne(Integer id) {
   		Sheet ret = null;
   		if(!TextUtil.isNotNull(id)){
			ret = new Sheet();

		}
   		else{
   			ret = baseDao.getById(Sheet.class, id);
   		}
		return ret;
	}
	public SheetStock getSheetStockOne(Integer id) {
		SheetStock ret = null;
		if(!TextUtil.isNotNull(id)){
			ret = new SheetStock();

		}
		else{
			ret = baseDao.getById(SheetStock.class, id);
		}
		return ret;
	}

	
	
/*-------------------------------------------------������������-------------------------------------------------*/	
  	
  	//ɾ������
	
	//�޸ġ����·���
	
	//��ѯ����
	
} 