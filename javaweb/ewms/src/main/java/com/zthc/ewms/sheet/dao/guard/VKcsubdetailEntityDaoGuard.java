package com.zthc.ewms.sheet.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;

import javax.annotation.Resource;


public class VKcsubdetailEntityDaoGuard {

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


	/**���浥������Ҫ��������**/


	/**�޸ġ����·���**/


	/**��ѯ����**/


	/**��ѯ����**/

} 