package com.zthc.ewms.system.print.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.print.entity.guard.Print;
import com.zthc.ewms.system.print.entity.guard.PrintCondition;
import drk.util.TextUtil;

import javax.annotation.Resource;

public class PrintDaoGuard {

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
	public void savePrint(Print obj, PrintCondition condition) {
//		Date now = new Date();
		//�õ���ǰ�û�ID
//		long currentUserId = getThreadLocal().getCurrentUserId();
		
		//�Զ�������ʱ��ʹ�����
		
		//�Զ���������Ĭ��ֵ�������ֶΣ�
		
		//������������


		baseDao.save(obj);
	}
	 
	 /**ɾ����������ʵɾ����������**/
   	public void delPrint(Long id) {
   		baseDao.removeById(Print.class, id);
	}

	/**��ȡ��������Ҫ�����������޸�**/
	public Print getPrintOne(Integer id) {
   		Print ret = null;
   		if(!TextUtil.isNotNull(id)){
			ret = new Print();
			//ĳЩ�ֶ�pageĬ��ֵ
			
			//ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
		}
   		else{
   			ret = baseDao.getById(Print.class, id);
   		}
		return ret;
	}
	
/*-------------------------------------------------������������-------------------------------------------------*/	
  	
  	//ɾ������
	
	//�޸ġ����·���
	
	//��ѯ����
	
} 