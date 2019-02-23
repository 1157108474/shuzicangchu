package com.zthc.ewms.sheet.dao.guard;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.hibernate.Query;
import com.hckj.base.database.hibernate.BaseDao;
import com.zthc.ewms.sheet.entity.guard.SheetDetail;
import com.zthc.ewms.sheet.entity.guard.SheetDetailCondition;
import com.hckj.base.mvc.BaseLocal;
import drk.util.TextUtil;

public class SheetDetailDaoGuard {    

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
	public void saveSheetDetail(SheetDetail obj, SheetDetailCondition condition) {
		Date now = new Date();
		//�õ���ǰ�û�ID
		long currentUserId = getThreadLocal().getCurrentUserId();
		
		//�Զ�������ʱ��ʹ�����
		
		//�Զ���������Ĭ��ֵ�������ֶΣ�
		
		//������������
		baseDao.save(obj);
	}
	 
	 /**ɾ����������ʵɾ����������**/
   	public void delSheetDetail(Long id) {
   		baseDao.removeById(SheetDetail.class, id);
	}

	/**��ȡ��������Ҫ�����������޸�**/
	public SheetDetail getSheetDetailOne(Integer id) {
   		SheetDetail ret = null;
   		if(!TextUtil.isNotNull(id)){
			ret = new SheetDetail();
			//ĳЩ�ֶ�pageĬ��ֵ
			
			//ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
		}
   		else{
   			ret = baseDao.getById(SheetDetail.class, id);
   		}
		return ret;
	}
	
/*-------------------------------------------------������������-------------------------------------------------*/	
  	
  	//ɾ������
	
	//�޸ġ����·���
	
	//��ѯ����
	
} 