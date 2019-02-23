package com.zthc.ewms.webservice.invoking;

import com.zthc.ewms.sheet.service.SheetDetailService;
import com.zthc.ewms.system.dept.service.OrganizationService;
import com.zthc.ewms.system.taskLog.service.TaskLogService;
import com.zthc.ewms.system.user.service.UserService;
import com.zthc.ewms.system.warehouse.service.WareHouseService;
import drk.system.Log;

import javax.annotation.Resource;

public class ERPWebServiceImpl implements ERPWebService {
    private final static Log log = Log.getLog("com.zthc.ewms.webservice.invoking.ERPWebServiceImpl");

    @Resource(name = "taskLogService")
    private TaskLogService taskLogService;

    @Resource(name = "sheetDetailService")
    public SheetDetailService detailService;
    @Resource(name = "organizationService")
    public OrganizationService orgService;
    @Resource(name = "userService")
    public UserService userService;
    @Resource(name = "wareHouseService")
    public WareHouseService wareHouseService;




    @Override
    public String pushXML(String strXml) {

       return strXml;
    }

    //��ȡ����ֵ
    @Override
    public String getResult(String json) {
        String result = "";
        try {
            // ����ӿ�
            // PortDemoWebServiceImpl portDemoWebServiceImplService = new PortDemoWebServiceImplService().getPortDemoWebServiceImplPort();
            // ����ӿ���ķ���
            // result = portDemoWebServiceImplService.receiveData(json);

        } catch (Exception e) {
            // ���ʧ��
            log.error("����ʧ��");
            log.errorPrintStacktrace(e);
            result = "{code:no,err:" + e + "}";
        }
        // ����ֵ��ʽ��{"code":"no","err":"����Ĵ�����Ϣ"}
        return result;
    }

}
