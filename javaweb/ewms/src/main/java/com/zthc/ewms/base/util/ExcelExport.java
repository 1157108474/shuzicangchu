package com.zthc.ewms.base.util;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * excel 导出类 <br />
 * 目前支持普通bean字段，组合类bean字段，Date日期类型转换成yyyy-MM-dd格式输出<br/>
 * ClassName: ExcelExport <br/>
 * date: 2016年1月15日 下午3:07:28 <br/>
 *
 * @author wjj
 * @since JDK 1.8
 */
public class ExcelExport {
    protected static final Logger logger = Logger.getLogger(ExcelExport.class);

    /**
     * excel导出
     *
     * @param fileName   文件名
     * @param resultList 结果集
     * @param gridTitles 标题
     * @param coloumsKey Bean字段
     * @param request
     * @param response
     */
    public static void doExcelExport(String fileName, List<?> resultList, String[] gridTitles, String[] coloumsKey,
                                     HttpServletRequest request, HttpServletResponse response) {

        if (StringUtils.isEmpty(fileName) || null == request) {
            logger.error("文件名或者HttpRequest为空");
            return;
        }

        if (null == resultList) {
            logger.error("文件对象不存在");
            return;
        }

        if (null == gridTitles) {
            logger.error("标题为空");
            return;
        }

        if (null == coloumsKey) {
            logger.error("字段为空");
            return;
        }
        String[] str = fileName.split("\\.");
        String titleName =fileName;
        if (str.length>0){
            titleName =str[0];
        }
        String encodedFileName;
        try {
            encodedFileName = encodeFileNameForDownload(request, fileName);
            HSSFWorkbook book = getExcelContent(titleName,resultList, gridTitles, coloumsKey);
            response.setContentType("application/ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);
            book.write(response.getOutputStream());
        } catch (UnsupportedEncodingException e) {
            logger.error("不支持的编码类型:" + e.getMessage());
        } catch (IOException e) {
            logger.error("文件导出错误" + e.getMessage());
        }

    }


    /**
     * @param dataList
     * @param gridTitles
     * @param coloumsKey
     * @return Excel的HSSFWorkbook对象
     */
    public static HSSFWorkbook getExcelContent(String titleName, List<?> dataList, String[] gridTitles, String[] coloumsKey) {
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFFont font = workBook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 16);//设置字体大小
        HSSFSheet sheet = workBook.createSheet();

        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = workBook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        int titleLength = gridTitles.length;
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, titleLength-1));
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue(titleName);
        cell.setCellStyle(style);
        row = sheet.createRow(1);
        for (int i = 0; i < gridTitles.length; i++) {
            row.createCell(i).setCellValue(new HSSFRichTextString(gridTitles[i]));
        }
        if (dataList.size() > 0) {
            String type = getGenType(dataList);
            for (int i = 0; i < dataList.size(); i++) {
                row = sheet.createRow((int) i + 2);
                for (int j = 0; j < coloumsKey.length; j++) {
                    row.createCell(j).setCellValue(new HSSFRichTextString(getValue(dataList.get(i), type, coloumsKey[j])));
                }
            }
        }
        return workBook;
    }

    private static String getGenType(List<?> o) {
        return o.get(0).getClass().getName();
    }

    /**
     * @param t    泛型类型<TestBean>
     * @param type com.e.bean.TestBean
     * @param key  field 字段
     * @return value
     */
    private static String getValue(Object t, String type, String key) {
        Object value = null;
        try {
            Class<?> genericSelfClass = Class.forName(type);

            String methodName = "";
            if (!key.contains(".")) {

                // 参考else内的注释
                methodName = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
                Class<?> fieldClass = genericSelfClass.getDeclaredField(key).getType();

                // 时间类型 支持转换成yyyy-MM-dd格式输出
                if ("java.util.Date".equals(fieldClass.getName())) {
                    Object date = genericSelfClass.getMethod(methodName).invoke(t);
                    if (null != date) {
                        value = DateFormatUtils.format((Date) date, "yyyy-MM-dd", Locale.CHINESE);
                    }
                } else {
                    value = genericSelfClass.getMethod(methodName).invoke(t);
                }
            } else {
                String[] names = StringUtils.split(key, ".");

                // 获得组合类的get方法
                methodName = "get" + names[0].substring(0, 1).toUpperCase() + names[0].substring(1);
                // 获得组合类的值
                value = genericSelfClass.getMethod(methodName).invoke(t);

                // 获得组合类的instance
                Class<?> combinationClass = Class.forName(value.getClass().getName());

                // 获得组合类中相应字段的get方法
                methodName = "get" + names[1].substring(0, 1).toUpperCase() + names[1].substring(1);
                // 获得组合类中相应字段的值
                value = combinationClass.getMethod(methodName).invoke(value);

                if (names.length > 2) {
                    // 获得组合类的instance
                    Class<?> combinationClass_ = Class.forName(value.getClass().getName());

                    // 获得组合类中相应字段的get方法
                    methodName = "get" + names[2].substring(0, 1).toUpperCase() + names[2].substring(1);
                    // 获得组合类中相应字段的值
                    value = combinationClass_.getMethod(methodName).invoke(value);

                }
            }

        } catch (ClassNotFoundException e) {
            logger.error("未能找到" + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("方法传递了一个不合法或不正确的参数" + e.getMessage());
        } catch (SecurityException e) {
            logger.error("类型安全异常!" + e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error("反射时调用了private修饰的方法!" + e.getMessage());
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            logger.error("未找到对应的方法" + e.getMessage());
        } catch (NoSuchFieldException e) {
            logger.error("未找到相应的字段" + e.getMessage());
        }
        if (null == value) {
            value = "";
        }
        return String.valueOf(value);
    }

    /**
     * 将文件名使用UTF8编码格式重新编码，以避免下载时提示的文件名乱码
     *
     * @param request
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeFileNameForDownload(HttpServletRequest request, String fileName)
            throws UnsupportedEncodingException {
        if (request == null || StringUtils.isEmpty(fileName)) {
            logger.error("文件名UTF8编码失败：httpRequest与文件名不能为空！");
            return fileName;
        }
        String encodedFileName = "";
        // 火狐
        if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
            encodedFileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        }
        // IE
        else {
            encodedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        }

        return encodedFileName;
    }

    /**
     * @param dataList
     * @param gridTitles
     * @param coloumsKey
     * @return Excel的HSSFWorkbook对象
     */
    public static HSSFWorkbook getExcelMapContent(String titleName, List<?> dataList, String[] gridTitles, String[] coloumsKey) {
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFFont font = workBook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 16);//设置字体大小
        HSSFSheet sheet = workBook.createSheet();

        HSSFRow row = sheet.createRow(0);
        HSSFCellStyle style = workBook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        int titleLength = gridTitles.length;
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, titleLength-1));
        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue(titleName);
        cell.setCellStyle(style);
        row = sheet.createRow(1);
        for (int i = 0; i < gridTitles.length; i++) {
            row.createCell(i).setCellValue(new HSSFRichTextString(gridTitles[i]));
        }
        if (dataList.size() > 0) {
            Map<String, Object> map;
            for (int i = 0; i < dataList.size(); i++) {
                row = sheet.createRow((int) i + 2);
                for (int j = 0; j < coloumsKey.length; j++) {
                    map = (Map<String, Object>) dataList.get(i);
                    row.createCell(j).setCellValue(new HSSFRichTextString(map.get(coloumsKey[j]) == null ? "" : String.valueOf( map.get(coloumsKey[j]))));
                }
            }
        }
        return workBook;
    }
    /**
     * @param key  field 字段
     * @return value
     */
    private static String getValue2(Map map,String key) {
        String value = map.get(key).toString();
        return String.valueOf(value);
    }
}