package com.zthc.ewms.system.material.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.material.entity.guard.Material;
import com.zthc.ewms.system.material.entity.guard.MaterialCondition;
import drk.util.TextUtil;

import javax.annotation.Resource;

public class MaterialDaoGuard {    

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
	public void saveMaterial(Material obj, MaterialCondition condition) {
//		Date now = new Date();
		//�õ���ǰ�û�ID
//		long currentUserId = getThreadLocal().getCurrentUserId();
		
		//�Զ�������ʱ��ʹ�����
		
		//�Զ���������Ĭ��ֵ�������ֶΣ�

		//������������
		baseDao.save(obj);
	}
	 
	 /**ɾ����������ʵɾ����������**/
   	public void delMaterial(Long id) {
   		baseDao.removeById(Material.class, id);
	}

	/**��ȡ��������Ҫ�����������޸�**/
	public Material getMaterialOne(Integer id) {
   		Material ret = null;
   		if(!TextUtil.isNotNull(id)){
			ret = new Material();
			//ĳЩ�ֶ�pageĬ��ֵ
			
			//ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
		}
   		else{
   			ret = baseDao.getById(Material.class, id);
   		}
		return ret;
	}

	/**��ȡ��������Ҫ�����������޸�**/
	public Material getMaterialById(Integer id) {
		if(TextUtil.isNotNull(id)){
			return baseDao.getById(Material.class, id);
		}
		return null;
	}
/*-------------------------------------------------������������-------------------------------------------------*/	
  	
  	//ɾ������
	
	//�޸ġ����·���
	
	//��ѯ����
	
} 