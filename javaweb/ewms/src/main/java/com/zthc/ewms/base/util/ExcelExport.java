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
 * excel ������ <br />
 * Ŀǰ֧����ͨbean�ֶΣ������bean�ֶΣ�Date��������ת����yyyy-MM-dd��ʽ���<br/>
 * ClassName: ExcelExport <br/>
 * date: 2016��1��15�� ����3:07:28 <br/>
 *
 * @author wjj
 * @since JDK 1.8
 */
public class ExcelExport {
    protected static final Logger logger = Logger.getLogger(ExcelExport.class);

    /**
     * excel����
     *
     * @param fileName   �ļ���
     * @param resultList �����
     * @param gridTitles ����
     * @param coloumsKey Bean�ֶ�
     * @param request
     * @param response
     */
    public static void doExcelExport(String fileName, List<?> resultList, String[] gridTitles, String[] coloumsKey,
                                     HttpServletRequest request, HttpServletResponse response) {

        if (StringUtils.isEmpty(fileName) || null == request) {
            logger.error("�ļ�������HttpRequestΪ��");
            return;
        }

        if (null == resultList) {
            logger.error("�ļ����󲻴���");
            return;
        }

        if (null == gridTitles) {
            logger.error("����Ϊ��");
            return;
        }

        if (null == coloumsKey) {
            logger.error("�ֶ�Ϊ��");
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
            logger.error("��֧�ֵı�������:" + e.getMessage());
        } catch (IOException e) {
            logger.error("�ļ���������" + e.getMessage());
        }

    }


    /**
     * @param dataList
     * @param gridTitles
     * @param coloumsKey
     * @return Excel��HSSFWorkbook����
     */
    public static HSSFWorkbook getExcelContent(String titleName, List<?> dataList, String[] gridTitles, String[] coloumsKey) {
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFFont font = workBook.createFont();
        font.setFontName("����");
        font.setFontHeightInPoints((short) 16);//���������С
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
     * @param t    ��������<TestBean>
     * @param type com.e.bean.TestBean
     * @param key  field �ֶ�
     * @return value
     */
    private static String getValue(Object t, String type, String key) {
        Object value = null;
        try {
            Class<?> genericSelfClass = Class.forName(type);

            String methodName = "";
            if (!key.contains(".")) {

                // �ο�else�ڵ�ע��
                methodName = "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
                Class<?> fieldClass = genericSelfClass.getDeclaredField(key).getType();

                // ʱ������ ֧��ת����yyyy-MM-dd��ʽ���
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

                // ���������get����
                methodName = "get" + names[0].substring(0, 1).toUpperCase() + names[0].substring(1);
                // ���������ֵ
                value = genericSelfClass.getMethod(methodName).invoke(t);

                // ���������instance
                Class<?> combinationClass = Class.forName(value.getClass().getName());

                // ������������Ӧ�ֶε�get����
                methodName = "get" + names[1].substring(0, 1).toUpperCase() + names[1].substring(1);
                // ������������Ӧ�ֶε�ֵ
                value = combinationClass.getMethod(methodName).invoke(value);

                if (names.length > 2) {
                    // ���������instance
                    Class<?> combinationClass_ = Class.forName(value.getClass().getName());

                    // ������������Ӧ�ֶε�get����
                    methodName = "get" + names[2].substring(0, 1).toUpperCase() + names[2].substring(1);
                    // ������������Ӧ�ֶε�ֵ
                    value = combinationClass_.getMethod(methodName).invoke(value);

                }
            }

        } catch (ClassNotFoundException e) {
            logger.error("δ���ҵ�" + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("����������һ�����Ϸ�����ȷ�Ĳ���" + e.getMessage());
        } catch (SecurityException e) {
            logger.error("���Ͱ�ȫ�쳣!" + e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error("����ʱ������private���εķ���!" + e.getMessage());
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            logger.error("δ�ҵ���Ӧ�ķ���" + e.getMessage());
        } catch (NoSuchFieldException e) {
            logger.error("δ�ҵ���Ӧ���ֶ�" + e.getMessage());
        }
        if (null == value) {
            value = "";
        }
        return String.valueOf(value);
    }

    /**
     * ���ļ���ʹ��UTF8�����ʽ���±��룬�Ա�������ʱ��ʾ���ļ�������
     *
     * @param request
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeFileNameForDownload(HttpServletRequest request, String fileName)
            throws UnsupportedEncodingException {
        if (request == null || StringUtils.isEmpty(fileName)) {
            logger.error("�ļ���UTF8����ʧ�ܣ�httpRequest���ļ�������Ϊ�գ�");
            return fileName;
        }
        String encodedFileName = "";
        // ���
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
     * @return Excel��HSSFWorkbook����
     */
    public static HSSFWorkbook getExcelMapContent(String titleName, List<?> dataList, String[] gridTitles, String[] coloumsKey) {
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFFont font = workBook.createFont();
        font.setFontName("����");
        font.setFontHeightInPoints((short) 16);//���������С
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
     * @param key  field �ֶ�
     * @return value
     */
    private static String getValue2(Map map,String key) {
        String value = map.get(key).toString();
        return String.valueOf(value);
    }
}