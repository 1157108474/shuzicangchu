package com.zthc.ewms.system.warehouse.service;

import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.base.util.StringUtils;
import com.zthc.ewms.system.dept.dao.OrganizationDao;
import com.zthc.ewms.system.warehouse.entity.guard.Location;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouse;
import com.zthc.ewms.system.warehouse.entity.guard.WareHouseCondition;
import com.zthc.ewms.system.warehouse.service.guard.WareHouseServiceGuard;
import drk.system.AppConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WareHouseService extends WareHouseServiceGuard {
    @Resource(name = "organizationDao")
    public OrganizationDao organizationDao;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Map> getWareTree(String idStr, String levelStr) {
        List<Map> maps = new ArrayList<>();
        Map map = null;
        Integer parentId = 0;
        int level;
        if (idStr == null) {
            map = new HashMap();
            map.put("id", "0");
            map.put("name", "仓库信息");
            map.put("pId", "");
            map.put("code", "");
            map.put("level", "0");
            // map.put("isParent",true);
            map.put("open", true);
            maps.add(map);
            level = 1;
        } else {
            parentId = Integer.parseInt(idStr);
            level = Integer.parseInt(levelStr) + 1;
        }
        List<WareHouse> wares = this.dao.treeWares(parentId);

        for (WareHouse ware : wares) {
            map = new HashMap<>();
            map.put("id", ware.getId());
            map.put("name", ware.getName());
            map.put("pId", parentId);
            map.put("code", ware.getCode());
            map.put("level", level);
            // map.put("isParent",level!=3);
            maps.add(map);
        }
        return maps;

    }

    /**
     * 获取列表数据
     **/
    @Transactional
    public LayuiPage<WareHouse> listWareHouse(WareHouse obj, WareHouseCondition condition, Integer orgId) throws Exception {
//        LayuiPage<WareHouse> ret = null;
//        if(obj.getId() == null){
//            ret = new LayuiPage<>();
//            ret.setCount(0L);
//            ret.setData(null);
//        }else{
//            ret = this.dao.listWareHouse(obj, condition,orgId);
//        }

        return this.dao.listWareHouse(obj, condition, orgId);
    }


    /**
     * 人员操作-- 库房库区
     **/
    @Transactional
    public List<WareHouse> listWareHouse(Integer orgId, Integer[] ids) throws Exception {
        return this.dao.listWareHouse(orgId, ids);
    }

    //修改
    @Transactional
    public void updateWareHouse(WareHouse WareHouse) {
        if (this.dao.updateWareHouse(WareHouse) != 1) {
            throw new RuntimeException("修改" + WareHouse.getName() + "数据异常");
        }

    }

    //伪删
    @Transactional
    public void deleteWareHouse(String str, Integer userId) {
        Integer[] ids = StringUtils.parseStringToIntegers(str);

        List<WareHouse> wareHouseList = this.dao.findWareHouseIds(ids);
        String defaultStoreHouse = AppConfig.getProperty("defaultStoreHouse");

        for (WareHouse wareHouse : wareHouseList) {
            if(wareHouse.getName().equals(defaultStoreHouse)){
                throw new RuntimeException("有默认库位不允许删除！");
            }
        }

        int count = ids.length;
        if (this.dao.deleteWareHouse(ids, userId) != count) {
            throw new RuntimeException("删除记录异常,请刷新后重试");
        }

    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean checkNotExit(WareHouse obj) {
        return this.dao.checkNotExit(obj);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public WareHouse getWareHouse(Integer id) {
        return this.dao.getWareHouse(id);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public LayuiPage<Location> listPrintLocation(String name, String begin, String end, WareHouseCondition condition) {
        return this.dao.listPrintLocation(name, begin, end, condition);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public LayuiPage<WareHouse> listLocation(Integer parentId, Integer ztId, String name,String code, String parentCode,
                                             WareHouseCondition condition) {
        return this.dao.listLocation(parentId, ztId, name,code, parentCode, condition);
    }

    /**
     * 获取库房
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<WareHouse> getStores(Integer ztid) {
        return this.dao.getStores(ztid);
    }


    /**
     * 获取库位编码查库位
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public WareHouse getWareHouseByCode(Integer storeId, String locationCode, Integer ztId) {
        return this.dao.getWareHouseByCode(storeId, locationCode, ztId);
    }

    /**
     * 获取库位编码查库位
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public WareHouse getWareHouseById(Integer storeId, Integer id) {
        return this.dao.getWareHouseById(storeId, id);
    }

    /**
     * 获取库位编码、组织及库房 查库位
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public WareHouse getWareHouse(String locationCode, Integer storeId, Integer ztId) {
        return this.dao.getWareHouse(locationCode, storeId, ztId);
    }

    /**
     * 获取库位编码
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public WareHouse getWareHouseCode(String code) {
        return this.dao.getWareHouseCode(code);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public WareHouse getWareHouseByCodes(String locationCode) {
        return this.dao.getWareHouseByCodes(locationCode);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public WareHouse getWareHouseByCode(String code) {
        return this.dao.getWareHouseByCode(code);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public WareHouse getWareHouseByCodeAndStatus(String code,int status) {
        return this.dao.getWareHouseByCodeAndStatus(code,status);
    }
    /**
     * 获取库房下的库位
     *
     * @param storeId
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<WareHouse> findByParentId(Integer storeId) {
        return  this.dao.findByParentId(storeId);
    }
    /**
     * 获取库房下的库位
     *
     * @param code
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<WareHouse> findByParentCode(String code) {
        return  this.dao.findByParentCode(code);
    }
    /**
     * 获取库房下的库位
     *
     * @param code
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Long ifFindByParentCode(String code) {
        return  this.dao.ifFindByParentCode(code);
    }
    /**
     * 判断库房下是否存在库位
     *
     * @param storeId
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Boolean isExistWarehouse(Integer storeId) {
        List<WareHouse> wareHouseList = this.dao.findByParentId(storeId);
        //获取默认库位名称
        String defaultStoreHouse = AppConfig.getProperty("defaultStoreHouse");
        if (wareHouseList.size() > 0) {
            if (wareHouseList.size() == 1 && wareHouseList.get(0).getName().equals(defaultStoreHouse)) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public WareHouse getWareHouseName(String name) {

        return this.dao.getWareHouseName(name);
    }
}
