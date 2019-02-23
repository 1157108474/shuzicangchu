package com.zthc.ewms.webservice.axis2;

import java.util.ArrayList;
import java.util.List;
import drk.system.AppConfig;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.context.MessageContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.Header;


/**
 * ERP�ӿڴ�����
 * 
 * @author gpf
 *
 */
public class ESBPortProxy  {
	/** ���нӿ�ƽ̨��ַ */
	
//	private String payURL = "http://172.16.3.169:9099/org.gocom.esb.ws.route/erp/scmapinv";
//
//	/**
//	 * Ӧ���ӿڵ���
//	 * @param OperationCode
//	 * @return
//	 */
//	public APINVStub getPayEntity(Map<String,String> esbInfoMap) {
//		try {
//			return new APINVStub(makeConfigurationContext(esbInfoMap), payURL);
//		} catch (AxisFault e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//	private String url = "http://172.16.3.169:9099/org.gocom.esb.ws.route/cuxws/wms";
	public static CuxwmsStub getPayEntity() {
		try {
			return new CuxwmsStub(makeConfigurationContext(), AppConfig.getProperty("ERPURL"));
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��http Header��ע��ӿ��û���Ϣ
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ConfigurationContext makeConfigurationContext() {
		ConfigurationContext configContext = null;
		try {
			if (MessageContext.getCurrentMessageContext() == null) {
				configContext = ConfigurationContextFactory.createConfigurationContextFromFileSystem(null, null);
			} else {
				configContext = MessageContext.getCurrentMessageContext().getConfigurationContext();
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		List<Header> headers = (List<Header>) configContext.getProperty(HTTPConstants.HTTP_HEADERS);

		if (headers == null) {
			headers = new ArrayList<>();
		}
		headers.add(new Header("ClientId", AppConfig.getProperty("ClientId")));
		headers.add(new Header("OperationCode",AppConfig.getProperty("OperationCode")));
		headers.add(new Header("LoginCode", AppConfig.getProperty("LoginCode")));
		headers.add(new Header("LoginPassword", AppConfig.getProperty("LoginPassword")));

		configContext.setProperty(HTTPConstants.HTTP_HEADERS, headers);
		return configContext;
	}
}
