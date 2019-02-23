package com.zthc.ewms.system.dept.service;


import com.zthc.ewms.system.dept.entity.guard.Depart;
import com.zthc.ewms.system.dept.entity.guard.Organization;
import com.zthc.ewms.system.dept.service.guard.OrganizationServiceGuard;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrganizationService extends OrganizationServiceGuard {

    /**
     * 获取所有组织机构信息
     **/
    @Transactional
    public String listOrganization(Organization obj) throws IOException {
        List<Organization> organizationList = this.dao.listOrganization(obj);
        List<Map> departs = new ArrayList<>();
        Map map = null;
        //把组织部门拼装成List<Map>格式
        for (Organization organization : organizationList) {
            map = new HashMap();
            map.put("id", organization.getId());
            map.put("name", organization.getName());
            map.put("type", organization.getType());

            map.put("pId", organization.getParentId());
            map.put("code", organization.getCode());
            map.put("levelCount", organization.getLevelCount());
            map.put("ztId", organization.getZtId());
            departs.add(map);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        //转换成json串
        return objectMapper.writeValueAsString(departs);

    }

    /**
     * 根据组织编号获取组织信息
     *
     * @return
     */

    public List<Organization> listOrganizationCode(Organization obj) {
        return this.dao.listOrganizationCode(obj);
    }
    public List<Organization> listOrganization() {
        return this.dao.listOrganization();
    }

    @Transactional
    public List<Organization> getOrganizationByCode(String[] strings) {
        return this.dao.getOrganizationByCode(strings);
    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Organization getOrganizationBy(String extendInt1) {
        return this.dao.getOrganizationBy(extendInt1);
    }
    @Transactional
    public List<Organization> getOrganizationList(Organization obj){
        return this.dao.listOrganization(obj);
    }

}
