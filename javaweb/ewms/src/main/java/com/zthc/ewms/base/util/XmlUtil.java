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
     * 更新成本接口拼装
     *
     * @return
     */
    public static String renewalCostXML(Integer detailId) {
        //拼装头部
        Document document = DocumentHelper.createDocument();
        Element element = document.addElement("WSINTERFACE");
        Element node = element.addElement("HEADER");
        //拼装内容
        node.addElement("ORGANIZATION_ID").addText("21324");
        //防止数据混乱,900000001开始的流水号
        node.addElement("LINE_ID").addText(StringUtils.getHeaderId(detailId));
        return document.getRootElement().asXML();
    }

    /**
     * 获取更新成本
     * @param result
     * @param detailId
     * @return
     * @throws Exception
     */
    public static RenewalCostDTO getRenewalCostDTO(String result,Integer detailId) throws Exception {

        //解析返回参数
        final BASE64Decoder decoder = new BASE64Decoder();
        String responseData = new String(decoder.decodeBuffer(result), "UTF-8");
        log.error("【更新成本】 解密后的参数responseData:"+responseData);
        //转换字符串
        Document document = DocumentHelper.parseText(responseData);
        //获取根元素
        Element root = document.getRootElement();
        Element header = root.element("HEADER");
        Element rateOrAmount = header.element("RATE_OR_AMOUNT");
        Double noTaxPrice =Double.valueOf( rateOrAmount.getText());
        Element baseTransactionValue = header.element("BASE_TRANSACTION_VALUE");
        Double noAxSum =Double.valueOf( baseTransactionValue.getText());

        return new RenewalCostDTO(detailId,noTaxPrice,noAxSum);
    }
}
