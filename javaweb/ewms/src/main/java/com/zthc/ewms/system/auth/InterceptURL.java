package com.zthc.ewms.system.auth;

import java.util.ArrayList;
import java.util.List;

public class InterceptURL {

    /**
     * ��ŵ�¼ǰ����Ҫ���ص�Url
     *
     * @return
     */
    public static List<String> getLoginFrontUrl() {
        List<String> interceptUrls = new ArrayList<>();
        interceptUrls.add("/");//��ʼ����
        interceptUrls.add("/system/auth/login.json");//��¼����
        interceptUrls.add("/system/auth/logout.json");//ע������
        interceptUrls.add("/login.jsp");//��¼ҳ
        interceptUrls.add("/login.html");//��¼ҳ
        interceptUrls.add("/system/auth/ssoLogin");//�����¼����
        interceptUrls.add("/goError.jsp");//����ҳ��

        interceptUrls.add("/sheet/apply/listSqNum.json");//���쵥�ӿ�
        interceptUrls.add("/sheet/ck/saveSheetCK.json");//APP�ӿ�(�������ⵥ�����ⵥ����)
        interceptUrls.add("/sheet/ck/listManageCK.json");//APP�ӿ�(��ѯ�Ƶ��еĳ��ⵥ�����ⵥ����)
        interceptUrls.add("/sheet/ck/listSheetCK.json");//APP�ӿ�(��ѯ���ⵥ��Ϣ������⣩)
        interceptUrls.add("/sheet/ck/listSQCKDetail.json");//APP�ӿ�(��ѯ���ⵥ��Ϣ��δ��⣩)
        interceptUrls.add("/sheet/ck/listCldetails.json");//APP�ӿ�(���ݻ�λ��ǩ�������ȡ�����Ͽ����Ϣ)
        interceptUrls.add("/system/ware/getWareHouseByCode.json");//APP�ӿ�(���ݿ�λ�����ȡ��Ϣ)
        interceptUrls.add("/system/material/getMaterialByCode.json");//APP�ӿ�(�������ϱ����ȡ��Ϣ)

        interceptUrls.add("/system/ware/ifWareHouseByCode.json");//APP�ӿ�(���ݿⷿ�����ѯ�Ƿ��п�λ)

        interceptUrls.add("/sheet/rk/listManageRk.json");//APP�ӿ� (��ѯδ��ɵ���ⵥ)
        interceptUrls.add("/sheet/rk/listRkDetails.json");//APP�ӿ� ��ѯ��ⵥ������Ϣ������⣩
        interceptUrls.add("/sheet/rk/listDetails.json");//APP�ӿ� ��ѯ��ⵥ������Ϣ��δ��⣩
        interceptUrls.add("/sheet/rk/RK");//APP�ӿ� (������ⵥ��)
        interceptUrls.add("/sheet/rkDetail/addRKDetails");//APP�ӿ� (���������ϸ)
        interceptUrls.add("/sheet/rk/listJSDList.json");//APP�ӿ� (��ѯ����ⵥ�����յ���)

        interceptUrls.add("/sheet/wzjs/listManageOrder.json");//APP�ӿ� (��ѯδ��ɵĽ��յ���Ϣ)
        interceptUrls.add("/sheet/wzjs/listDetails.json");//APP�ӿ� ��ѯ���յ���ϸ��Ϣ��δ���գ�
        interceptUrls.add("/sheet/wzjs/listJSDDetails.json");//APP�ӿ� ��ѯ���յ���ϸ��Ϣ���ѽ��գ�
        interceptUrls.add("/sheet/detail/addWZJSDetails");//APP�ӿ� (���������ϸ)
        interceptUrls.add("/sheet/wzjs/WZJS");//APP�ӿ� (������յ���)
        interceptUrls.add("/sheet/wzjs/listOrderGeneral.json");//APP�ӿ� ��ѯ�ɽ��յĲɹ�����δ���յĲɹ�������
        interceptUrls.add("/services/AxisService");//axisWebService�ӿ�
        return interceptUrls;
    }
    /**
     * ��ŵ�¼����Ҫ���ص�Url
     *
     * @return
     */
    public static List<String> getLoginAfterUrl() {
        List<String> interceptUrls = new ArrayList<>();
        return interceptUrls;
    }
}
