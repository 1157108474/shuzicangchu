package com.zthc.ewms.base.util;

import drk.system.AppConfig;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by barton on 16-2-18.
 */
public class ExcelImport {

    private static final String EXCEL_2007 = ".xlsx";

    /**
     * ��ȡexcel����
     *
     * @param filePath
     * @return
     */
    public static String[] readExcelTitle(String filePath) {
        XSSFWorkbook workbook = null;

        try {
            InputStream is = new FileInputStream(filePath);

            workbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFRow row = sheet.getRow(0);

        // ����������
        int colNum = row.getPhysicalNumberOfCells();

        String[] title = new String[colNum];

        for (int i = 0; i < colNum; i++) {
            title[i] = getCellFormatValue(row.getCell(i));
        }

        return title;
    }

    /**
     * ��ȡExcel��������
     *
     * @param filePath
     * @return
     */
    public static Map<Integer, String> readExcelContent(String filePath) {
        Workbook workbook = getWorkBook(filePath);
        Sheet sheet = workbook.getSheetAt(0);
        return readSheet(sheet, 0, "");//Ĭ�ϵ�һ��Ϊ��ͷ
    }

    public static Map<Integer, String> readExcelContent(String filePath, int headRowNum, String headParent) {
        Workbook workbook = getWorkBook(AppConfig.getProperty("filepath.absolutePath") + filePath);
        Sheet sheet = workbook.getSheetAt(0);
        return readSheet(sheet, headRowNum, headParent);
    }


    private static Map<Integer, String> readSheet(Sheet sheet, int headRowNum, String headParent) {
        Map<Integer, String> content = new HashMap<>(16);
        if (headRowNum != 0) {
            Row row = sheet.getRow(0);

            if (row == null || !headParent.equals(getCellFormatValue(row.getCell(0)).trim())) {
                throw new RuntimeException("������" + headParent + "ģ����е���");
            }
        }

        StringBuffer sb = new StringBuffer("");
        int rowNum = sheet.getLastRowNum();
        Row row = sheet.getRow(headRowNum); //
        int colNum = row.getPhysicalNumberOfCells();
        for (int i = headRowNum + 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < colNum; j++) {
                String colStr = getCellFormatValue(row.getCell(j)).trim();
                sb.append(colStr);
                sb.append("-->");
            }
            // ��ÿһ�е����ݶ�put��map��
            content.put(i, sb.toString());
            // ���³�ʼ��StringBuffer
            sb.setLength(0);
        }

        return content;
    }

    private static Workbook getWorkBook(String filePath) {
        Workbook workbook = null;
        try {
            InputStream is = new FileInputStream(filePath);

            if (filePath.endsWith(EXCEL_2007)) {

                workbook = new XSSFWorkbook(is);
            } else {
                workbook = new HSSFWorkbook(is);
            }
        } catch (IOException e) {
            throw new RuntimeException("��ȡWorkbookʧ��");
        }
        return workbook;
    }

    /**
     * ��ȡ��Ԫ����������Ϊ�ַ������͵�����
     *
     * @param cell
     * @return
     */
    private static String getStringCellValue(XSSFCell cell) {
        String strCell;

        switch (cell.getCellTypeEnum()) {
            case STRING:
                strCell = cell.getStringCellValue();
                break;
            case NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case BLANK:
                strCell = "��";
                break;
            default:
                strCell = "��";
                break;
        }

        if (StringUtils.isEmpty(strCell)) {
            return "��";
        }

        return strCell;
    }

    /**
     * ����XSSFCell������������
     *
     * @param cell
     * @return
     */
    private static String getCellFormatValue(Cell cell) {
        String cellValue;

        if (cell != null) {

            // �жϵ�ǰCell��Type
            switch (cell.getCellTypeEnum()) {

                // �����ǰCell��TypeΪNUMERIC
                case NUMERIC:

                case FORMULA: {

                    // �жϵ�ǰ��cell�Ƿ�ΪDate
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = sdf.format(date);
                    } else { // ����Ǵ�����
                        // ȡ�õ�ǰCell����ֵ
                        cellValue = format(cell.getNumericCellValue());
                    }
                    break;
                }

                // �����ǰCell��TypeΪSTRING
                case STRING:

                    // ȡ�õ�ǰ��Cell�ַ���
                    cellValue = cell.getRichStringCellValue().getString();
                    if (StringUtils.isEmpty(cellValue)) {
                        cellValue = "��";
                    }
                    break;
                case BLANK:

                    // ȡ�õ�ǰ��Cell�ַ���
                    cellValue = "��";
                    break;

                // Ĭ�ϵ�Cellֵ
                default:
                    cellValue = "��";
            }
        } else {
            cellValue = "��";
        }
        return cellValue;

    }


    /**
     * ���excel�������⣬�����ֵ
     *
     * @param cell
     * @return
     */
    public static String getValue(Cell cell) {
        String value = "";
        if (null == cell) {
            return value;
        }
        switch (cell.getCellTypeEnum()) {
            //��ֵ��
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //�����date������ ����ȡ��cell��dateֵ
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = format.format(date);
                    ;
                } else {// ������
                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    //���1234.0  ȥ�������.0
                    if (null != value && !"".equals(value.trim())) {
                        String[] item = value.split("[.]");
                        if (1 < item.length && "0".equals(item[1])) {
                            value = item[0];
                        }
                    }
                }
                break;
            //�ַ�������
            case STRING:
                value = cell.getStringCellValue().toString();
                break;
            // ��ʽ����
            case FORMULA:
                //����ʽ����ֵ
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// �����ȡ������ֵΪ�Ƿ�ֵ,��ת��Ϊ��ȡ�ַ���
                    value = cell.getStringCellValue().toString();
                }
                break;
            // ��������
            case BOOLEAN:
                value = " " + cell.getBooleanCellValue();
                break;
            // ��ֵ
            case BLANK:
                value = "";
                break;
            // ����
            case ERROR:
                value = "";
                break;
            default:
                value = cell.getStringCellValue().toString();
        }
        if ("null".endsWith(value.trim())) {
            value = "";
        }
        return value;
    }

    /**
     * double ת����String
     *
     * @param cellValue
     * @return
     */
    private static String format(double cellValue) {
        NumberFormat numberFormat = new DecimalFormat("#.######");
        return numberFormat.format(cellValue);
    }
}
