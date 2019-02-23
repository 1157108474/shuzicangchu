package com.zthc.ewms.sheet.controller.guard;

import javax.annotation.Resource;

import com.zthc.ewms.sheet.service.SheetRKDETAILService;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import drk.system.Log;

public class SheetRKDETAILControllerGuard {

	@Resource(name= "sheetRKDETAILService")
	public SheetRKDETAILService service;

	private final static Log log;

	static{
        log = Log.getLog("com.zthc.ewms.sheet.controller.guard.SheetRKDETAILControllerGuard");
    }

	/**
	 * ���ύʱ���ֶ�����ǰ׺��ֻ�ύһ������ʱ�����Բ�д��ǰ׺
	 */
	@InitBinder("rkdetail")
	public void initSheet_RKDETAILBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("rkdetail.");
	}

	/**
	 *����ҳ��
	 */

	/**
	 *��תҳ�淽��
	 */

	/**
	 *����
	 */


	/**
	 * �༭ҳ��
	 */


	/**
	 * �����¼
	 */

	/**
	 * �޸ķ���
	 */

	/**
	 * ��ѯ����
	 */

	/**
	 * ɾ������
	 */



}

