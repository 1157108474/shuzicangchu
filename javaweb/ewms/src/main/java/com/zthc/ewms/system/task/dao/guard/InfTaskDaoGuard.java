package com.zthc.ewms.system.task.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.task.entity.guard.InfTask;
import com.zthc.ewms.system.task.entity.guard.InfTaskCondition;
import drk.util.TextUtil;

import javax.annotation.Resource;
import java.util.Date;

public class InfTaskDaoGuard {    

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
	public void saveInfTask(InfTask obj, InfTaskCondition condition) {
//		Date now = new Date();
		//�õ���ǰ�û�ID
//		long currentUserId = getThreadLocal().getCurrentUserId();
		
		//�Զ�������ʱ��ʹ�����
		
		//�Զ���������Ĭ��ֵ�������ֶΣ�
		
		//������������

		obj.setStatus(1); //Ĭ��Ϊ����
		baseDao.save(obj);
	}
	 
	 /**ɾ����������ʵɾ����������**/
   	public void delInfTask(Long id) {
   		baseDao.removeById(InfTask.class, id);
	}

	/**��ȡ��������Ҫ�����������޸�**/
	public InfTask getInfTaskOne(Integer id) {
   		InfTask ret = null;
   		if(!TextUtil.isNotNull(id)){
			ret = new InfTask();
			//ĳЩ�ֶ�pageĬ��ֵ
			
			//ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
		}
   		else{
   			ret = baseDao.getById(InfTask.class, id);
   		}
		return ret;
	}
	
/*-------------------------------------------------������������-------------------------------------------------*/	
  	
  	//ɾ������
	
	//�޸ġ����·���
	
	//��ѯ����
	
} 