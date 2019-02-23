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
 * 字符串工具类
 * Created by yyb on 2018/3/5.
 */
public class StringUtils extends org.springframework.util.StringUtils{
    private StringUtils(){

    }
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }



    /**
     * 判断字符串是否为空
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
     * 替换掉下划线并让紧跟它后面的字母大写,例如 ad_code 转成 adCode
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
     * 返回首字母大写的字符串
     *
     * @param str
     * @return
     */
    public static String fristStrToUpperCase(String str) {
        String resultStr = str.substring(0, 1).toUpperCase() + str.substring(1);
        return resultStr;
    }

    /**
     * 返回首字母和第二个字母小写的字符串
     *
     * @param str
     * @return
     */
    public static String fristAndSecondStrToLowerCase(String str) {
        String resultStr = str.substring(0, 1).toLowerCase() + str.substring(1, 2).toLowerCase() + str.substring(2);
        return resultStr;
    }

    /**
     * 返回首字母小写的字符串
     *
     * @param str
     * @return
     */
    public static String fristStrToLowerCase(String str) {
        String resultStr = str.substring(0, 1).toLowerCase() + str.substring(1);
        return resultStr;
    }

    /**
     * 取括号内的数字
     *
     * @param str 例如：char(1)
     * @return
     */
    public static String getKhNum(String str) {
        return str.replaceAll("\\D", "");
    }
    /**
     * 获取UUID
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
          * 将文字转为汉语拼音
        * @param chineseLanguage 要转成拼音的中文
     */
    public static String toHanyuPinyin(String chineseLanguage){
        char[] cl_chars = chineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V) ;
        try {
            for (int i=0; i<cl_chars.length; i++){
                if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")){// 如果字符是中文,则将中文转为汉语拼音
                    hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                } else {// 如果字符不是中文,则不转换
                    hanyupinyin += cl_chars[i];
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
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
     * 对字符串md5加密(小写+字母)
     *
     * @param str 传入要加密的字符串
     * @return  MD5加密后的字符串
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 转换Erp流水号
     *
     * @param id
     */
    public static String getHeaderId(Integer id) {
        //防止数据混乱,900000001开始的流水号
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

        //转换字符串
        Document document = DocumentHelper.parseText(responseData);
        //获取根元素
        Element root = document.getRootElement();
        Element header = root.element("HEADER");
        Element rateOrAmount = header.element("RATE_OR_AMOUNT");
        Double noTaxPrice =Double.valueOf( rateOrAmount.getText());
        Element baseTransactionValue = header.element("BASE_TRANSACTION_VALUE");
        Double noAxSum =Double.valueOf( baseTransactionValue.getText());


      /*  System.out.println("md5生成的密码："+getMD5("123"));
        System.out.println("数据库中密码：202cb962ac59075b964b07152d234b70");*/
    }

}
