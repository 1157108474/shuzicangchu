package com.zthc.ewms.system.menu.service;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.menu.entity.guard.Button;
import com.zthc.ewms.system.menu.entity.guard.ButtonCondition;
import com.zthc.ewms.system.menu.service.guard.ButtonServiceGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ButtonService extends ButtonServiceGuard {
    /**
     * 获取列表数据
     **/
    @Transactional
    public LayuiPage<Button> listButton(Button obj, ButtonCondition condition) throws Exception {
        return this.dao.listButton(obj, condition);
    }

    /**
     * 删除方法
     **/
    @Transactional
    public int delButton(ButtonCondition condition) {
        //根据权限删除所有权限分配
//        this.dao.delMenuResourceButtonId(condition.getIds());
        return this.dao.delButton(condition);
    }

    /**
     * 获取所有按钮
     *
     * @return
     */
    public List<Button> listButton() {
        return this.dao.listButton();
    }

    /**
     * 根据编号获取按钮
     *
     * @return
     */
    @Transactional
    public List<Button> listButtonCode(Button obj) {
        return this.dao.listButtonCode(obj);
    }

    /**
     * 根据用户ID查询角色，根据角色获取权限
     *
     * @return
     */
    public List<Button> listButton(Long userId) {
        return this.dao.listButton(userId);
    }

    /**
     * 根据用户ID查询角色，根据角色和菜单ID获取权限
     *
     * @return
     */
    public List<Button> listButton(Long userId, Long menuId) {
        return this.dao.listButton(userId, menuId);
    }
}
