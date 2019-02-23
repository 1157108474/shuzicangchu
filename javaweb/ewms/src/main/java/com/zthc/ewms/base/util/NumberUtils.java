package com.zthc.ewms.base.util;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * 数字工具类
 * Created by yyb on 2018/3/5.
 */
public class NumberUtils {

    private NumberUtils() {

    }

    /**
     * 获取数组中的最大数
     *
     * @param args
     * @return
     */
    public static Long getMaxNum(Long args[]) {
        int max = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i] > args[max])
                max = i;
        }
        return args[max];
    }

    /**
     * 获取数组中的最小数
     *
     * @param args
     * @return
     */
    public static Long getMinNum(Long args[]) {
        int min = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i] < args[min])
                min = i;
        }
        return args[min];
    }

    /**
     * 生成随机数
     *
     * @param str    随机数生成范围:例如:0123456789,
     * @param length 随机数长度
     * @return
     */
    public static String getRandomString(String str, int length) {
        Random rd = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = rd.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 产生随机数
     *
     * @param randString 随机数范围，例如：0123456789
     * @param num        随机数个数
     */
    public static void getRandStr(String randString, int num) {
        String randomString = "";
        Random random = new Random();
        // String randString = "0123456789";// 随机产生的字符串
        for (int i = 0; i < num; i++) {
            String randomStr = String.valueOf(randString.charAt(random.nextInt(randString.length())));
            String rand = String.valueOf(randomStr);
            randomString += rand;

        }
        System.out.println(randomString);
    }


    static DecimalFormat df = new DecimalFormat("######0.00");

    /**
     * 保留两位小数
     *
     * @param number 转换数
     */
    public static String getDouble(Double number) {
        if(number==null){
            return "";
        }else {
            if(number<0){
                number = -number;
            }
            return df.format(number);
        }
    }

}
