package com.zthc.ewms.system.activitiManage.controller.guard;

import drk.system.Log;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class ProcessManageControllerGuard {

    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.dictionary.controller.guard.ActivitiManageControllerGuard");
    }

    @InitBinder("process")
    public void initDictionaryBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("process.");
    }


}

