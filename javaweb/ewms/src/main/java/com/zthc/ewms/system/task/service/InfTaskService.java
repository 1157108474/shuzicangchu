package com.zthc.ewms.system.task.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.task.entity.guard.InfTask;
import com.zthc.ewms.system.task.entity.guard.InfTaskCondition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.zthc.ewms.system.task.service.guard.InfTaskServiceGuard;


@Service
public class InfTaskService extends InfTaskServiceGuard {
    /**
     * ��ȡ�б�����
     **/
    @Transactional
    public LayuiPage<InfTask> listInfTask( InfTaskCondition condition,String name) throws Exception {
        return this.dao.listInfTask( condition,name);
    }

    //�޸�
    @Transactional
    public void updateTask(InfTask task) {
        if(this.dao.updateTask(task) !=1 ){
            throw new RuntimeException("�޸�"+task.getName()+"�����쳣");
        }

    }

    //αɾ
    @Transactional
    public void deleteTask(String str,Integer userId){
        Integer[] ids = StringUtils.parseStringToIntegers(str);
        int count = ids.length;
        if(this.dao.deleteTask(ids,userId)!= count){
            throw new RuntimeException("ɾ����¼�쳣,��ˢ�º�����");
        }

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean checkNotExit(InfTask obj) {
        return this.dao.checkNotExit(obj);
    }
	
}
