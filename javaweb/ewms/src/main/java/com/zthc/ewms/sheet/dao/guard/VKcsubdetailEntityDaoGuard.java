package com.zthc.ewms.sheet.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;

import javax.annotation.Resource;


public class VKcsubdetailEntityDaoGuard {

	@Resource
	public BaseDao baseDao;

	//线程局部变量，用于存放当前用户信息等数据
	private static final ThreadLocal<BaseLocal> threadLocal = new ThreadLocal<BaseLocal>();

	/**
	 * 得到当前线程（本地）数据对象
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
	 * 把数据存入当前线程（本地）对象
	 * @param local 设置好共享值的对象
	 */
	public static void setThreadLocal(BaseLocal local) {
		threadLocal.set(local);
	}

	/*-------------------------------------------------开始基础方法-------------------------------------------------*/
	/**保存单条，主要用于新增**/


	/**保存单条，主要用于新增**/


	/**修改、更新方法**/


	/**查询方法**/


	/**查询方法**/

} 