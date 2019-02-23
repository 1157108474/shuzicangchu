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
     * 读取excel标题
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

        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();

        String[] title = new String[colNum];

        for (int i = 0; i < colNum; i++) {
            title[i] = getCellFormatValue(row.getCell(i));
        }

        return title;
    }

    /**
     * 读取Excel数据内容
     *
     * @param filePath
     * @return
     */
    public static Map<Integer, String> readExcelContent(String filePath) {
        Workbook workbook = getWorkBook(filePath);
        Sheet sheet = workbook.getSheetAt(0);
        return readSheet(sheet, 0, "");//默认第一行为表头
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
                throw new RuntimeException("请下载" + headParent + "模板进行导入");
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
            // 将每一行的内容都put到map中
            content.put(i, sb.toString());
            // 重新初始化StringBuffer
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
            throw new RuntimeException("获取Workbook失败");
        }
        return workbook;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
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
                strCell = "空";
                break;
            default:
                strCell = "空";
                break;
        }

        if (StringUtils.isEmpty(strCell)) {
            return "空";
        }

        return strCell;
    }

    /**
     * 根据XSSFCell类型设置数据
     *
     * @param cell
     * @return
     */
    private static String getCellFormatValue(Cell cell) {
        String cellValue;

        if (cell != null) {

            // 判断当前Cell的Type
            switch (cell.getCellTypeEnum()) {

                // 如果当前Cell的Type为NUMERIC
                case NUMERIC:

                case FORMULA: {

                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = sdf.format(date);
                    } else { // 如果是纯数字
                        // 取得当前Cell的数值
                        cellValue = format(cell.getNumericCellValue());
                    }
                    break;
                }

                // 如果当前Cell的Type为STRING
                case STRING:

                    // 取得当前的Cell字符串
                    cellValue = cell.getRichStringCellValue().getString();
                    if (StringUtils.isEmpty(cellValue)) {
                        cellValue = "空";
                    }
                    break;
                case BLANK:

                    // 取得当前的Cell字符串
                    cellValue = "空";
                    break;

                // 默认的Cell值
                default:
                    cellValue = "空";
            }
        } else {
            cellValue = "空";
        }
        return cellValue;

    }


    /**
     * 解决excel类型问题，获得数值
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
            //数值型
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    //如果是date类型则 ，获取该cell的date值
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = format.format(date);
                    ;
                } else {// 纯数字
                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toString();
                    //解决1234.0  去掉后面的.0
                    if (null != value && !"".equals(value.trim())) {
                        String[] item = value.split("[.]");
                        if (1 < item.length && "0".equals(item[1])) {
                            value = item[0];
                        }
                    }
                }
                break;
            //字符串类型
            case STRING:
                value = cell.getStringCellValue().toString();
                break;
            // 公式类型
            case FORMULA:
                //读公式计算值
                value = String.valueOf(cell.getNumericCellValue());
                if (value.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串
                    value = cell.getStringCellValue().toString();
                }
                break;
            // 布尔类型
            case BOOLEAN:
                value = " " + cell.getBooleanCellValue();
                break;
            // 空值
            case BLANK:
                value = "";
                break;
            // 故障
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
     * double 转换成String
     *
     * @param cellValue
     * @return
     */
    private static String format(double cellValue) {
        NumberFormat numberFormat = new DecimalFormat("#.######");
        return numberFormat.format(cellValue);
    }
}
