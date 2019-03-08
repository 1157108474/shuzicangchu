package com.zthc.ewms.system.user.service;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.user.entity.guard.User;
import com.zthc.ewms.system.user.entity.guard.UserCondition;
import com.zthc.ewms.system.user.service.guard.UserServiceGuard;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService extends UserServiceGuard {

    @Transactional
    public LayuiPage<User> listUser(User obj, UserCondition condition, String startTime, String endTime) throws Exception {
        return this.dao.listUser(obj, condition, startTime, endTime);
    }

    /**
     * ���ݵ�¼��Ϣ��ѯ�û���Ϣ
     *
     * @param obj
     * @return
     */
    @Transactional
    public User getUserLogin(User obj) {
        return this.dao.getUserLogin(obj);
    }
    @Transactional
    public int updateUserLogin(User obj) {
        return this.dao.updateUserLogin(obj);
    }

    /**
     * �����û���Ż�ȡ�û���Ϣ
     *
     * @return
     */
    @Transactional(readOnly=true)
    public List<User> listUserCodes(String code) {
        return this.dao.listUserCodes(code);
    }
    public List<User> listUserCodesName(String code) {
        return this.dao.listUserCodesName(code);
    }

    /**
     * �����û���ź�Id��ȡ�û���Ϣ
     *
     * @return
     */
    public List<User> listUserCode(User obj) {
        return this.dao.listUserCode(obj);
    }


    /**
     * ��ȡ�����û���Ϣ
     *
     * @return
     */
    public List<User> listUser() {
        return this.dao.listUser();
    }

    /**
     * ��������
     *
     * @param obj
     */
    @Transactional
    public int updateResetPWD(User obj) {
        return this.dao.updateResetPWD(obj);
    }

    @Transactional
    public User getUserByCode(String code) {
        return this.dao.getUserByCode(code);
    }
    /**
     * �����û����ҡ����ӻ���û�Id
     *
     * @return
     */
    public List<User> listUserIdByOfficesId(Integer officesId) {
        return this.dao.listUserIdByOfficesId(officesId);
    }

}
