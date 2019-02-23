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

    //获取返回值
    @Override
    public String getResult(String json) {
        String result = "";
        try {
            // 对面接口
            // PortDemoWebServiceImpl portDemoWebServiceImplService = new PortDemoWebServiceImplService().getPortDemoWebServiceImplPort();
            // 对面接口里的方法
            // result = portDemoWebServiceImplService.receiveData(json);

        } catch (Exception e) {
            // 如果失败
            log.error("推送失败");
            log.errorPrintStacktrace(e);
            result = "{code:no,err:" + e + "}";
        }
        // 返回值格式：{"code":"no","err":"具体的错误信息"}
        return result;
    }

}
