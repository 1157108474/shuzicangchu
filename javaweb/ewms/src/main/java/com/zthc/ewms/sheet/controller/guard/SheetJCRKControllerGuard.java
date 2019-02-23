package com.zthc.ewms.sheet.controller.guard;

import com.zthc.ewms.sheet.service.SheetRKService;
import drk.system.Log;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;

public class SheetJCRKControllerGuard {

	@Resource(name= "sheetRKService")
	public SheetRKService service;

	private final static Log log;

	static{
        log = Log.getLog("com.zthc.ewms.sheet.controller.guard.SheetRKControllerGuard");
    }

	/**
	 * ���ύʱ���ֶ�����ǰ׺��ֻ�ύһ������ʱ�����Բ�д��ǰ׺
	 */
	@InitBinder("rkd")
	public void initSheet_RKBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("rkd.");
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

