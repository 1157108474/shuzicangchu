package com.zthc.ewms.system.menu.controller;


import com.zthc.ewms.base.page.LayuiPage;
import com.zthc.ewms.system.menu.controller.guard.ButtonControllerGuard;
import com.zthc.ewms.system.menu.entity.guard.Button;
import com.zthc.ewms.system.menu.entity.guard.ButtonCondition;
import drk.system.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/system/button")
public class ButtonController extends ButtonControllerGuard {
    private final static Log log = Log.getLog("com.system.menu.controller.ButtonController");

    /**
     * ��ȡ�б�����
     */
    @RequestMapping("/listButton.json")
    @ResponseBody
    public LayuiPage<Button> listDepartment(Button obj, ButtonCondition condition, HttpServletResponse response) throws IOException {
        LayuiPage<Button> ret = null;
        try {
            ret = service.listButton(obj, condition);
        } catch (Exception e) {
            log.error("��ȡ��ť�б����������" + obj.toString());
            log.errorPrintStacktrace(e);

        }
        return ret;
    }

}
