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
     * ��ȡ�б�����
     **/
    @Transactional
    public LayuiPage<Button> listButton(Button obj, ButtonCondition condition) throws Exception {
        return this.dao.listButton(obj, condition);
    }

    /**
     * ɾ������
     **/
    @Transactional
    public int delButton(ButtonCondition condition) {
        //����Ȩ��ɾ������Ȩ�޷���
//        this.dao.delMenuResourceButtonId(condition.getIds());
        return this.dao.delButton(condition);
    }

    /**
     * ��ȡ���а�ť
     *
     * @return
     */
    public List<Button> listButton() {
        return this.dao.listButton();
    }

    /**
     * ���ݱ�Ż�ȡ��ť
     *
     * @return
     */
    @Transactional
    public List<Button> listButtonCode(Button obj) {
        return this.dao.listButtonCode(obj);
    }

    /**
     * �����û�ID��ѯ��ɫ�����ݽ�ɫ��ȡȨ��
     *
     * @return
     */
    public List<Button> listButton(Long userId) {
        return this.dao.listButton(userId);
    }

    /**
     * �����û�ID��ѯ��ɫ�����ݽ�ɫ�Ͳ˵�ID��ȡȨ��
     *
     * @return
     */
    public List<Button> listButton(Long userId, Long menuId) {
        return this.dao.listButton(userId, menuId);
    }
}
