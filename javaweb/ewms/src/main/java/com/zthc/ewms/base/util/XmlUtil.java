package com.zthc.ewms.base.util;

import com.zthc.ewms.sheet.entity.ck.dto.RenewalCostDTO;
import drk.system.Log;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import sun.misc.BASE64Decoder;

/**
 * @author yyb
 * @create 2019-01-08-17:26
 */
public class XmlUtil {

    private final static Log log = Log.getLog(XmlUtil.class.getName());
    /**
     * ���³ɱ��ӿ�ƴװ
     *
     * @return
     */
    public static String renewalCostXML(Integer detailId) {
        //ƴװͷ��
        Document document = DocumentHelper.createDocument();
        Element element = document.addElement("WSINTERFACE");
        Element node = element.addElement("HEADER");
        //ƴװ����
        node.addElement("ORGANIZATION_ID").addText("21324");
        //��ֹ���ݻ���,900000001��ʼ����ˮ��
        node.addElement("LINE_ID").addText(StringUtils.getHeaderId(detailId));
        return document.getRootElement().asXML();
    }

    /**
     * ��ȡ���³ɱ�
     * @param result
     * @param detailId
     * @return
     * @throws Exception
     */
    public static RenewalCostDTO getRenewalCostDTO(String result,Integer detailId) throws Exception {

        //�������ز���
        final BASE64Decoder decoder = new BASE64Decoder();
        String responseData = new String(decoder.decodeBuffer(result), "UTF-8");
        log.error("�����³ɱ��� ���ܺ�Ĳ���responseData:"+responseData);
        //ת���ַ���
        Document document = DocumentHelper.parseText(responseData);
        //��ȡ��Ԫ��
        Element root = document.getRootElement();
        Element header = root.element("HEADER");
        Element rateOrAmount = header.element("RATE_OR_AMOUNT");
        Double noTaxPrice =Double.valueOf( rateOrAmount.getText());
        Element baseTransactionValue = header.element("BASE_TRANSACTION_VALUE");
        Double noAxSum =Double.valueOf( baseTransactionValue.getText());

        return new RenewalCostDTO(detailId,noTaxPrice,noAxSum);
    }
}
