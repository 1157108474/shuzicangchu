package com.zthc.ewms.system.spare.dao.guard;

import java.util.Date;

import javax.annotation.Resource;

import com.zthc.ewms.system.spare.entity.guard.Sparepartscate;
import com.zthc.ewms.system.spare.entity.guard.SparepartscateCondition;
import org.hibernate.Query;
import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import drk.util.TextUtil;

public class SparepartscateDaoGuard {    

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
	public void saveSparepartscate(Sparepartscate obj, SparepartscateCondition condition) {
//		Date now = new Date();
		//�õ���ǰ�û�ID
//		long currentUserId = getThreadLocal().getCurrentUserId();
		
		//�Զ�������ʱ��ʹ�����
		
		//�Զ���������Ĭ��ֵ�������ֶΣ�
//		obj.setStatus(1);
			
		//������������
		baseDao.save(obj);
	}
	 
	 /**ɾ����������ʵɾ����������**/
   	public void delSparepartscate(Long id) {
   		baseDao.removeById(Sparepartscate.class, id);
	}

	/**��ȡ��������Ҫ�����������޸�**/
	public Sparepartscate getSparepartscateOne(Integer id) {
   		Sparepartscate ret = null;
   		if(!TextUtil.isNotNull(id)){
			ret = new Sparepartscate();
			//ĳЩ�ֶ�pageĬ��ֵ
			
			//ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
		}
   		else{
   			ret = baseDao.getById(Sparepartscate.class, id);
   		}
		return ret;
	}
	
/*-------------------------------------------------������������-------------------------------------------------*/	
  	
  	//ɾ������
	
	//�޸ġ����·���
	/**
	 * �Զ�����£�
	 * @param obj ������ѯ����
   	 * @param condition �����ѯ����
	 * @return ���¼�¼��
	 */	 
	 public int updateSpareOne(Sparepartscate obj, SparepartscateCondition condition) {
	 	Date now = new Date();
	 	//�õ���ǰ�û�ID
   		long currentUserId = getThreadLocal().getCurrentUserId();
   		String hql = "update Sparepartscate set guid = :guid,code = :code,name = :name,parentId = :parentid,levelCount = :levelcount,levelCode = :levelcode,endFlag = :endflag,status = :status,sort = :sort,memo = :memo,updater = :updater,updateDate = :updatedate,";
   		Query query = baseDao.createQuery(hql);
   		query.setParameter("guid",obj.getGuid());
		query.setParameter("code",obj.getCode());
		query.setParameter("name",obj.getName());
		query.setParameter("parentid",obj.getParentId());
		query.setParameter("levelcount",obj.getLevelCount());
		query.setParameter("levelcode",obj.getLevelCode());
		query.setParameter("endflag",obj.getEndFlag());
		query.setParameter("status",obj.getStatus());
		query.setParameter("sort",obj.getSort());
		query.setParameter("memo",obj.getMemo());
		query.setParameter("updater",obj.getUpdater());
		query.setParameter("updatedate",obj.getUpdateDate());
		
   		return query.executeUpdate();
	 }
	
	//��ѯ����


	
} 