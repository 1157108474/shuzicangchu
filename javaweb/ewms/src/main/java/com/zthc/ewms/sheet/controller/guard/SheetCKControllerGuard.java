package com.zthc.ewms.sheet.controller.guard;

import drk.system.Log;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class SheetCKControllerGuard {


    private final static Log log = Log.getLog("com.hckj.ewms.sheet.controller.guard.SheetCKControllerGuard");

    @InitBinder("o")
    public void initOrderInfoBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("o.");
    }


}

