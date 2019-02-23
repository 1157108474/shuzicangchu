package com.zthc.ewms.sheet.dao.guard;

import com.hckj.base.database.hibernate.BaseDao;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.sheet.entity.apply.ManageApply;
import com.zthc.ewms.sheet.entity.ck.VCKList;
import com.zthc.ewms.sheet.entity.guard.SheetCK;
import com.zthc.ewms.sheet.entity.guard.SheetCKCondition;
import com.zthc.ewms.sheet.entity.guard.SheetCKDETAILCondition;
import drk.util.TextUtil;
import org.hibernate.Query;

import javax.annotation.Resource;

public class SheetCKDaoGuard {

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
	 * 获取单条数据
	 *
	 * @param id
	 * @return
	 */
	public VCKList getVCKListOne(Integer id) {
		VCKList ret = null;
		if (!TextUtil.isNotNull(id)) {
			ret = new VCKList();
		} else {
			ret = baseDao.getById(VCKList.class, id);
		}
		return ret;
	}
	/**
	 * 获取单条数据
	 *
	 * @param id
	 * @return
	 */
	public SheetCK getSheetCKOne(Integer id) {
		SheetCK ret = null;
		if (!TextUtil.isNotNull(id)) {
			ret = new SheetCK();
		} else {
			ret = baseDao.getById(SheetCK.class, id);
		}
		return ret;
	}
	public void delSheetCK(Integer id) {
		baseDao.removeById(SheetCK.class, id);
	}
	/**
	 * 把数据存入当前线程（本地）对象
	 * @param local 设置好共享值的对象
	 */
	public static void setThreadLocal(BaseLocal local) {
		threadLocal.set(local);
	}
	
	/*-------------------------------------------------开始基础方法-------------------------------------------------*/
	
/*-------------------------------------------------结束基础方法-------------------------------------------------*/	
  	
  	/*
	 * 删除明细方法
     **/
	public int delDetails(SheetCKDETAILCondition condition) {
		String hql = "delete SheetCKDETAIL where id in :ids";
		Query query = baseDao.createQuery(hql);
		query.setParameterList("ids", condition.getIds());
		return query.executeUpdate();
	}
	/*
	 * 删除明细方法
     **/
	public int delSheetDetails(Integer sheetId) {
		String hql = "delete SheetCKDETAIL where sheetId = :sheetId";
		Query query = baseDao.createQuery(hql);
		query.setParameter("sheetId", sheetId);
		return query.executeUpdate();
	}
	//修改、更新方法
	
	//查询方法
	
} 