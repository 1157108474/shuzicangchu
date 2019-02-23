package com.zthc.ewms.system.role.service;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.menu.dao.ButtonDao;
import com.zthc.ewms.system.menu.dao.MenuDao;
import com.zthc.ewms.system.menu.entity.guard.Button;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleCondition;
import com.zthc.ewms.system.role.entity.guard.RoleMenuButton;
import com.zthc.ewms.system.role.service.guard.RoleServiceGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends RoleServiceGuard {
    @Resource(name = "buttonDao")
    public ButtonDao buttonDao;
    @Resource(name = "menuDao")
    public MenuDao menuDao;
    @Resource(name = "roleMenuButtonService")
    public RoleMenuButtonService roleMenuButtonService;
    /**
     * 获取列表数据
     **/
    @Transactional
    public LayuiPage<Role> listRole(Role obj, RoleCondition condition) throws Exception {
        return this.dao.listRole(obj, condition);
    }
    /**
     * 获取列表数据
     **/
    @Transactional
    public LayuiPage<Role> listRoleName(Role obj, RoleCondition condition) throws Exception {
        return this.dao.listRoleName(obj, condition);
    }
    /**
     * 获取所有角色
     **/
    @Transactional
    public List<Role> listRoleNames(){
        return this.dao.listRoleNames();
    }


    /**
     * 获取列表数据
     **/
    @Transactional
    public List<Map<String, Object>> listMenuButton(Role obj) throws Exception {
        List<Map<String, Object>> menus = new ArrayList<>();
        Map<String, Object> map = null;
       Role role = this.dao.getRoleOne(obj.getRoleCode());
       List<Menu> menuList = this.menuDao.listMenuStatus();
        List<Button> buttonList = this.buttonDao.listButtons();
        List<RoleMenuButton> roleMenuButtons = this.roleMenuButtonService.listRoleMenuButtons(obj);
        //把菜单拼装成List<Map>格式
        for (Menu menu : menuList) {
            map = new HashMap();
            map.put("id", "m-" + menu.getCode());
            map.put("name", menu.getName());
            map.put("pId", "m-" + (null == menu.getMenu() ? 0 : menu.getMenu().getCode()));
            for (Menu roleMenu : role.getMenus()) {
                if (menu.getCode().equals(roleMenu.getCode())) {
                    map.put("checked", "true");
                    break;
                }
            }
            menus.add(map);
        }
        //把菜单拼装成List<Map>格式
        for (Button button : buttonList) {
            map = new HashMap();
            map.put("id", button.getCode());
            map.put("name", button.getName());
            map.put("pId", "m-" + button.getMenuCode());
            for (RoleMenuButton roleMenuButton : roleMenuButtons) {
                if (button.getCode().equals(roleMenuButton.getButtonCode())) {
                    map.put("checked", "true");
                    break;
                }
            }
            menus.add(map);
        }
        return menus;
    }


    /**
     * 获取所有角色
     **/
    @Transactional
    public List<Role> listRoles() throws Exception {
        return this.dao.listRoles();
    }

    /**
     * 删除方法
     **/
    @Transactional
    public int delRoles(RoleCondition condition) {
        return this.dao.delRoles(condition);
    }

    /**
     * 获取所有角色的ID和角色名
     *
     * @return
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Role> listRolesName() {
        return this.dao.listRolesName();
    }

}
