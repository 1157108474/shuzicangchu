package com.zthc.ewms.system.role.service;


import com.zthc.ewms.system.menu.dao.ButtonDao;
import com.zthc.ewms.system.menu.entity.guard.Button;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.menu.service.MenuService;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.entity.guard.RoleMenuButton;
import com.zthc.ewms.system.role.entity.guard.RoleMenuButtonCondition;
import com.zthc.ewms.system.role.service.guard.RoleMenuButtonServiceGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleMenuButtonService extends RoleMenuButtonServiceGuard {

    @Resource(name = "buttonDao")
    public ButtonDao buttonDao;
    @Resource(name = "roleService")
    public RoleService roleService;
    @Resource(name = "menuService")
    public MenuService menuService;


    /**
     * 获取所有角色
     **/
    @Transactional
    public List<RoleMenuButton> listRoleMenuButtons(Role obj) {
        return this.dao.listRoleMenuButtons(obj);
    }

    /**
     * 获取所有角色
     **/
    @Transactional
    public void saveRoleMenuButton(Role obj, RoleMenuButtonCondition condition) {
        this.dao.delRoleMenuButton(obj);
        List<Button> buttonList = this.buttonDao.listButtons();
        for (String buttonId : condition.getButtonIds()) {
            for (Button button : buttonList) {
                if (buttonId.equals(button.getId())) {
                    RoleMenuButton roleMenuButton = new RoleMenuButton();
                    roleMenuButton.setRoleCode(obj.getRoleCode());
                    roleMenuButton.setButtonCode(button.getCode());
                    roleMenuButton.setMenuCode(button.getMenuCode());
                    this.dao.saveRoleMenuButton(roleMenuButton, null);
                    break;
                }
            }
        }
        Role role = this.roleService.getRoleOne(obj.getRoleCode());
        List<Menu> menus = this.menuService.listMenusCodes(condition.getMenuIds());
        role.setMenus(menus);
        this.roleService.update(role);
    }
}
