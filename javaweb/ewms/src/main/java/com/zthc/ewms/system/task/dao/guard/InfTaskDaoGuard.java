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
	public void saveInfTask(InfTask obj, InfTaskCondition condition) {
//		Date now = new Date();
		//得到当前用户ID
//		long currentUserId = getThreadLocal().getCurrentUserId();
		
		//自动处理创建时间和创建人
		
		//自动处理特殊默认值（特殊字段）
		
		//进行新增保存

		obj.setStatus(1); //默认为启用
		baseDao.save(obj);
	}
	 
	 /**删除单条，真实删除，不常用**/
   	public void delInfTask(Long id) {
   		baseDao.removeById(InfTask.class, id);
	}

	/**获取单条，主要用于新增和修改**/
	public InfTask getInfTaskOne(Integer id) {
   		InfTask ret = null;
   		if(!TextUtil.isNotNull(id)){
			ret = new InfTask();
			//某些字段page默认值
			
			//ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
		}
   		else{
   			ret = baseDao.getById(InfTask.class, id);
   		}
		return ret;
	}
	
/*-------------------------------------------------结束基础方法-------------------------------------------------*/	
  	
  	//删除方法
	
	//修改、更新方法
	
	//查询方法
	
} 