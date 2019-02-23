package com.zthc.ewms.system.activitiManage.service;


import com.zthc.ewms.system.activitiManage.dao.ActdicDao;
import com.zthc.ewms.system.activitiManage.entity.ActDic;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ActdicService {

   @Resource(name = "actdicDao")
    private ActdicDao actdicDao;


    public List<ActDic> findAll(){
        return actdicDao.findAll();
    }

    public ActDic getActDicBydicID(String id){
        return actdicDao.getActDicBydicID(id);
    }
    @Transactional
    public void insertActDic(ActDic actDic){
       actdicDao.insertActDic(actDic);
    }
    @Transactional
    public void updateActDicBymodelId(String modelId,String id) {
        actdicDao.updateActDicBymodelId(modelId,id);
    }

    public ActDic getDic(String id) {
        return actdicDao.getDicBydepid(id);
    }

    public List<ActDic> getActDicByModelID(String modelId) {
        return actdicDao.getActDicByModelID(modelId);
    }
}
