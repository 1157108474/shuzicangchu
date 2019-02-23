package com.zthc.ewms.system.dictionary.controller.guard;

import com.hckj.base.mvc.BaseController;
import com.hckj.base.mvc.BaseLocal;
import com.zthc.ewms.system.dictionary.dao.guard.DictionaryDaoGuard;
import com.zthc.ewms.system.dictionary.entity.guard.Dictionary;
import com.zthc.ewms.system.dictionary.entity.guard.DictionaryCondition;
import com.zthc.ewms.system.dictionary.service.DictionaryService;
import com.zthc.ewms.system.log.entity.SystemLog;
import com.zthc.ewms.system.log.service.LogService;
import drk.system.Log;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DictionaryControllerGuard {

    @Resource(name = "dictionaryService")
    public DictionaryService service;



    private final static Log log;

    static {
        log = Log.getLog("com.zthc.ewms.system.dictionary.controller.guard.DictionaryControllerGuard");
    }

    /**
     * 表单提交时，字段所用前缀，只提交一个对象时，可以不写此前缀
     */
    @InitBinder("dic")
    public void initDictionaryBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("dic.");
    }

    /**
     * 管理页面
     */
   // @RequestMapping(value = "/manageDictionary.htm")
    @RequestMapping(method= RequestMethod.GET)
    public String manageDictionary(@ModelAttribute("dic") Dictionary obj, DictionaryCondition condition,
                                   HttpServletRequest request, HttpServletResponse response, Model model) {
        //获取 树型
        List<Dictionary> tree = this.service.getDicTree();
        model.addAttribute("tree", tree);
        return "system/dic/manageDic";
    }

 //   @RequestMapping(value = "/addDictionary.htm")
//    @RequestMapping(method=RequestMethod.GET,value="{id}")
//    public String addDictionary(@ModelAttribute("dic") Dictionary obj, DictionaryCondition condition,
//                                HttpServletRequest request, HttpServletResponse response, Model model) {
//        return editDictionary(obj, condition, request, response, model);
//    }
//
//    /**
//     * 编辑页面
//     */
//    @RequestMapping(value = "/editDictionary.htm")
//    public String editDictionary(@ModelAttribute("dic") Dictionary obj, DictionaryCondition condition,
//                                 HttpServletRequest request, HttpServletResponse response, Model model) {
//        obj = this.service.getDictionaryOne(obj.getId());
//        model.addAttribute("dic", obj);
//        return "ewms/dictionary/view/editDictionary";
//    }
//
//    /**
//     * 保存记录
//     */
//    @RequestMapping(value = "/saveDictionary.htm")
//    public void saveDictionary(@ModelAttribute("dic") Dictionary obj, DictionaryCondition condition,
//                               HttpServletRequest request, HttpServletResponse response, Model model) {
//
//        String ret = "f";
//        try {
//            long userId = 0L;
//            if (request.getSession().getAttribute("userId") != null) {
//                userId = Long.parseLong(request.getSession().getAttribute("userId").toString());
//            }
//            BaseLocal local = DictionaryDaoGuard.getThreadLocal();
//            local.setCurrentUserId(userId); //把当前操作人ID存入当前线程对象中
//            DictionaryDaoGuard.setThreadLocal(local);
//
//            this.service.saveDictionary(obj, condition);
//        } catch (Exception e) {
//            log.error("保存记录出错！");
//            log.errorPrintStacktrace(e);
//        }
//        BaseController.print(response, ret);
//    }


    /****************************添加方法****************************************/
    //select条件列表

    //update
    //delete

    //jump
}

