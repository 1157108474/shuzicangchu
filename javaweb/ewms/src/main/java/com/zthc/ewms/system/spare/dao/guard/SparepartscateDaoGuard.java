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
	public void saveSparepartscate(Sparepartscate obj, SparepartscateCondition condition) {
//		Date now = new Date();
		//得到当前用户ID
//		long currentUserId = getThreadLocal().getCurrentUserId();
		
		//自动处理创建时间和创建人
		
		//自动处理特殊默认值（特殊字段）
//		obj.setStatus(1);
			
		//进行新增保存
		baseDao.save(obj);
	}
	 
	 /**删除单条，真实删除，不常用**/
   	public void delSparepartscate(Long id) {
   		baseDao.removeById(Sparepartscate.class, id);
	}

	/**获取单条，主要用于新增和修改**/
	public Sparepartscate getSparepartscateOne(Integer id) {
   		Sparepartscate ret = null;
   		if(!TextUtil.isNotNull(id)){
			ret = new Sparepartscate();
			//某些字段page默认值
			
			//ret.setBirthday(TextUtil.parseDate("1991-01-01", "yyyy-MM-dd"));
		}
   		else{
   			ret = baseDao.getById(Sparepartscate.class, id);
   		}
		return ret;
	}
	
/*-------------------------------------------------结束基础方法-------------------------------------------------*/	
  	
  	//删除方法
	
	//修改、更新方法
	/**
	 * 自定义更新，
	 * @param obj 基本查询参数
   	 * @param condition 特殊查询参数
	 * @return 更新记录数
	 */	 
	 public int updateSpareOne(Sparepartscate obj, SparepartscateCondition condition) {
	 	Date now = new Date();
	 	//得到当前用户ID
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
	
	//查询方法


	
} 