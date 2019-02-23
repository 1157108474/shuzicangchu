package com.zthc.ewms.system.menu.service;


import com.zthc.ewms.system.menu.dao.ButtonDao;
import com.zthc.ewms.system.menu.entity.guard.Menu;
import com.zthc.ewms.system.menu.entity.guard.MenuButtonEnums;
import com.zthc.ewms.system.menu.entity.guard.MenuCondition;
import com.zthc.ewms.system.menu.service.guard.MenuServiceGuard;
import com.zthc.ewms.system.role.entity.guard.Role;
import com.zthc.ewms.system.role.service.RoleMenuButtonService;
import com.zthc.ewms.system.user.service.UserRoleService;
import drk.system.AppConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService extends MenuServiceGuard {

    @Resource(name = "buttonDao")
    public ButtonDao buttonDao;
    @Resource(name = "userRoleService")
    public UserRoleService userRoleService;


    /**
     * ��ȡ�б�����
     **/
    @Transactional
    public List<Menu> listMenus() {
        List<Menu> menus = this.dao.listMenus();
        List<Menu> mainMenu = getListMenus(menus);
        return mainMenu;
    }

    /**
     * ��ȡ�б�����
     **/
    @Transactional
    public List<Menu> listMenuStatus() {
        List<Menu> menus = this.dao.listMenuStatus();
        List<Menu> mainMenu = getListMenus(menus);
        return mainMenu;
    }

    /**
     * ��ȡ���˵���
     **/
    @Transactional
    public String appMenus(Integer userId) {
        List<Menu> menus = this.dao.listMenus(userId);
        String codeStr = AppConfig.getProperty("app.menu.code");
        String[] codes = codeStr.split(",");
        String ret = "";
        for (String code : codes) {
            for (Menu menu : menus) {
                if (code.equals(menu.getCode())) {
                    ret += code + ",";
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * ��ȡ���˵���
     **/
    @Transactional
    public List<Menu> leftMenus(Integer userId) {
        List<Menu> menus = this.dao.listMenus(userId);
        List<Menu> mainMenu = getListMenus(menus);
        return mainMenu;
    }

    public List<Menu> getListMenus(List<Menu> menus) {
        List<Menu> mainMenu = new ArrayList<>();
        for (Menu menu : menus) {
            if (null == menu.getMenu() && null != menu.getType() && menu.getType() == MenuButtonEnums.TypeEnum.CATALOG.getType()) {
                List<Menu> submenu = new ArrayList<>();
                for (Menu me : menus) {
                    if (null != me.getMenu() && menu.getCode().equals(me.getMenu().getCode())) {
                        List<Menu> smenu = new ArrayList<>();
                        for (Menu m : menus) {
                            if (null != m.getMenu() && me.getCode().equals(m.getMenu().getCode())) {
                                smenu.add(m);
                            }
                        }
                        me.setChildren(smenu);
                        submenu.add(me);
                    }
                }
                menu.setChildren(submenu);
                mainMenu.add(menu);
            }
        }
        return mainMenu;
    }

    /**
     * ɾ������
     **/
    @Transactional
    public int delMenus(MenuCondition condition) {
        //ɾ���˵��µ�Ȩ��
        //this.permissionsDao.delPermissionsMenuId(condition.getIds());
        //���ݲ˵�ɾ������Ȩ�޷���
        //this.dao.delMenuResourceMenuId(condition);
        return this.dao.delMenu(condition);
    }

    /**
     * ���ݱ�Ż�ȡ�˵�
     *
     * @return
     */
    @Transactional
    public List<Menu> listMenuCode(Menu obj) {
        return this.dao.listMenuCode(obj);
    }


    /**
     * �������ͻ�ȡ�˵�
     *
     * @return
     */
    @Transactional
    public List<Menu> listFatherMenus(int type) {
        return this.dao.listFatherMenus(type);
    }

    @Transactional
    public List<Menu> listMenusCodes(String[] codes) {
        return this.dao.listMenusCodes(codes);
    }

    /*
     *��ȡ��������
     */
    @Transactional
    public Menu getMenuCode(String code) {
        return this.dao.getMenuCode(code);
    }

}
