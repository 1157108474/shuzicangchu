package com.zthc.ewms.base.util;

import drk.system.AppConfig;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * �ַ���������
 * Created by yyb on 2018/3/5.
 */
public class StringUtils extends org.springframework.util.StringUtils{
    private StringUtils(){

    }
    /**
     * �ж��ַ����Ƿ�Ϊ��
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }



    /**
     * �ж��ַ����Ƿ�Ϊ��
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str) {
        boolean isString = str instanceof String;
        if (isString) {
            if (str == null) {
                return true;
            } else if (String.valueOf(str).trim().equals("")) {
                return true;
            }
        } else {
            if (str == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * �滻���»��߲��ý������������ĸ��д,���� ad_code ת�� adCode
     *
     * @param str
     * @return
     */
    public static String strRelplacetoLowerCase(String str) {
        StringBuffer sb = new StringBuffer();
        sb.append(str.toLowerCase());

        int count = sb.indexOf("_");
        while (count != 0) {
            int num = sb.indexOf("_", count);
            count = num + 1;
            if (num != -1) {
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count, count + 1, ia + "");
            }
        }
        return sb.toString().replaceAll("_", "");
    }

    /**
     * ��������ĸ��д���ַ���
     *
     * @param str
     * @return
     */
    public static String fristStrToUpperCase(String str) {
        String resultStr = str.substring(0, 1).toUpperCase() + str.substring(1);
        return resultStr;
    }

    /**
     * ��������ĸ�͵ڶ�����ĸСд���ַ���
     *
     * @param str
     * @return
     */
    public static String fristAndSecondStrToLowerCase(String str) {
        String resultStr = str.substring(0, 1).toLowerCase() + str.substring(1, 2).toLowerCase() + str.substring(2);
        return resultStr;
    }

    /**
     * ��������ĸСд���ַ���
     *
     * @param str
     * @return
     */
    public static String fristStrToLowerCase(String str) {
        String resultStr = str.substring(0, 1).toLowerCase() + str.substring(1);
        return resultStr;
    }

    /**
     * ȡ�����ڵ�����
     *
     * @param str ���磺char(1)
     * @return
     */
    public static String getKhNum(String str) {
        return str.replaceAll("\\D", "");
    }
    /**
     * ��ȡUUID
     *
     * @return
     */
    public synchronized static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static Long[] parseStringToLong(String idsStr) {
        String[] idsArr = idsStr.split(",");
        Long[] ids = new Long[idsArr.length ];
        int i=0;
        for(String str:idsArr){
            ids[i++] = Long.parseLong(str);
        }
        return ids;
    }
    /**
          * ������תΪ����ƴ��
        * @param chineseLanguage Ҫת��ƴ��������
     */
    public static String toHanyuPinyin(String chineseLanguage){
        char[] cl_chars = chineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// ���ƴ��ȫ��Сд
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// ��������
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V) ;
        try {
            for (int i=0; i<cl_chars.length; i++){
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")){// ����ַ�������,������תΪ����ƴ��
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {// ����ַ���������,��ת��
                    hanyupinyin += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("�ַ�����ת�ɺ���ƴ��");
        }
        return hanyupinyin;
    }

    public static Long[] parseStringToLongs(String ids){

        String[] strs=ids.split(",");
        Long[] ids_Long = new Long[strs.length];
        for(int i=0;i<strs.length;i++){
            ids_Long[i]=Long.parseLong(strs[i]);
        }
        return ids_Long;
    }


    public static Integer[] parseStringToIntegers(String ids){

        String[] strs=ids.split(",");
        Integer[] ids_Integer = new Integer[strs.length];
        for(int i=0;i<strs.length;i++){
            ids_Integer[i]=Integer.parseInt(strs[i]);
        }
        return ids_Integer;
    }
    /**
     * ���ַ���md5����(Сд+��ĸ)
     *
     * @param str ����Ҫ���ܵ��ַ���
     * @return  MD5���ܺ���ַ���
     */
    public static String getMD5(String str) {
        try {
            // ����һ��MD5���ܼ���ժҪ
            MessageDigest md = MessageDigest.getInstance("MD5");
            // ����md5����
            md.update(str.getBytes());
            // digest()���ȷ������md5 hashֵ������ֵΪ8Ϊ�ַ�������Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�
            // BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * ת��Erp��ˮ��
     *
     * @param id
     */
    public static String getHeaderId(Integer id) {
        //��ֹ���ݻ���,900000001��ʼ����ˮ��
        Integer erpKey = Integer.valueOf(AppConfig.getProperty("erp.key"));
        Integer sheetId = id+erpKey;
        return sheetId.toString();
    }

    public static void main(String[] args) throws IOException, DocumentException {
        String encodedText = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPFdTSU5URVJG" +
                "QUNFPjxIRUFERVI+PEJBU0VfVFJBTlNBQ1RJT05fVkFMVUU+LTE8L0JBU0VfVFJB" +
                "TlNBQ1RJT05fVkFMVUU+PFJBVEVfT1JfQU1PVU5UPi0xPC9SQVRFX09SX0FNT1VO" +
                "VD48L0hFQURFUj48L1dTSU5URVJGQUNFPg==";
        final BASE64Decoder decoder = new BASE64Decoder();

        String responseData = new String(decoder.decodeBuffer(encodedText), "UTF-8");
        System.out.println(responseData);

        //ת���ַ���
        Document document = DocumentHelper.parseText(responseData);
        //��ȡ��Ԫ��
        Element root = document.getRootElement();
        Element header = root.element("HEADER");
        Element rateOrAmount = header.element("RATE_OR_AMOUNT");
        Double noTaxPrice =Double.valueOf( rateOrAmount.getText());
        Element baseTransactionValue = header.element("BASE_TRANSACTION_VALUE");
        Double noAxSum =Double.valueOf( baseTransactionValue.getText());


      /*  System.out.println("md5���ɵ����룺"+getMD5("123"));
        System.out.println("���ݿ������룺202cb962ac59075b964b07152d234b70");*/
    }

}
