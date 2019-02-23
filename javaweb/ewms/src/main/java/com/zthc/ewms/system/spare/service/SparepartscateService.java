package com.zthc.ewms.system.spare.service;

import com.zthc.ewms.system.spare.entity.guard.Sparepartscate;
import org.springframework.stereotype.Service;


import com.zthc.ewms.system.spare.service.guard.SparepartscateServiceGuard;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class SparepartscateService extends SparepartscateServiceGuard {
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Map> getSpareTree(String idStr,String levelStr) {
        List<Map> maps = new ArrayList<>();
        Map map = null;
        Integer parentId =0;
        int level;
        if(idStr == null ){
            map = new HashMap();
            map.put("id", "0");
            map.put("name", "物料分类");
            map.put("pId", "");
            map.put("code", "");
            map.put("level","0");
            map.put("isParent",true);
            map.put("open",true);
            maps.add(map);
            level =1;
        }else {
            parentId = Integer.parseInt(idStr);
            level = Integer.parseInt(levelStr)+1;
        }
        List<Sparepartscate> spares =this.dao.listSpares(parentId,null);

      for(Sparepartscate spare:spares){
          map = new HashMap<>();
          map.put("id",spare.getId());
          map.put("name",spare.getName());
          map.put("pId",parentId);
          map.put("code",spare.getCode());
          map.put("level",level);
          map.put("isParent",level!=3);
          maps.add(map);
      }
      return maps;

    }


    /**
     *使用單位頁面操作物料範圍查詢
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Sparepartscate> getSpareList(Integer[] ids) {
        return this.dao.listSpares(0,ids);
    }
    /**
     * 人员页面物料范围查询
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Sparepartscate> listSpareparts(Integer[] ids) {
        return this.dao.listSpares(0,ids);
    }


    /**
     * 获取已绑定的物料分类
     * @param useId
     * @return
     */
    public List<Sparepartscate> listOfficeScope(Integer useId){
        return this.dao.listOfficeScope(useId);
    }


    //修改
    @Transactional
    public void updateSpare(Sparepartscate spare) {
        if(this.dao.updateSpare(spare) !=1 ){
            throw new RuntimeException("修改"+spare.getName()+"数据异常");
        }

    }

    //查
    @Transactional(propagation=Propagation.NOT_SUPPORTED)
    public Sparepartscate getSpare(Integer id,int level) {
        return this.dao.getSpare(id, level);
    }


    //伪删
    @Transactional
    public void deleteSparepartscate(Integer id,Integer userId){
        if(this.dao.deleteSparepartscate(id,userId)!=1){
            throw new RuntimeException("删除记录异常id="+id);
        }

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean checkNotExit(Sparepartscate obj) {
        return this.dao.checkNotExit(obj);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Sparepartscate getSparepartscate(String code) {
        return this.dao.getSparepartscate(code);
    }


}
