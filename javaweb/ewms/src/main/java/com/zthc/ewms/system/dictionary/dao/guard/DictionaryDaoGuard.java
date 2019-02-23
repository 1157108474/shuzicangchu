package com.zthc.ewms.system.dictionary.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryCondition;
import drk.util.TextUtil;

import javax.annotation.Resource;

public class DictionaryDaoGuard {    

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
	public void saveDictionary(Dictionary obj, DictionaryCondition condition) {
//		Date now = new Date();
		//�õ���ǰ�û�ID
//		long currentUserId = getThreadLocal().getCurrentUserId();
		
		//�Զ�������ʱ��ʹ�����
		
		//�Զ���������Ĭ��ֵ�������ֶΣ�
		
		//������������
		baseDao.save(obj);
	}
	 
	 /**ɾ����������ʵɾ����������**/
   	public void delDictionary(Integer id) {
   		baseDao.removeById(Dictionary.class, id);
	}

	/**��ȡ��������Ҫ�����������޸�**/
	public Dictionary getDictionaryOne(Integer id) {

   		if(!TextUtil.isNotNull(id)){
			return new Dictionary();
			//ĳЩ�ֶ�pageĬ��ֵ
			//ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
		}
   		else{
   			return baseDao.getById(Dictionary.class, id);
   		}
	}
	
/*-------------------------------------------------������������-------------------------------------------------*/	
  	
  	//ɾ������
	
	//�޸ġ����·���
	
	//��ѯ����
	
} 