package com.zthc.ewms.base.util;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Excel工具类
 *
 * @author yyb
 */
public class FileUtils {

    private static String EXCELXLS = ".xls";
    private static String EXCELXLSX = ".xlsx";

    /**
     * 根据后缀名称判断excel的版本
     *
     * @param fileName    文件名称
     * @param inputStream 文件流
     * @return
     * @throws IOException
     */
    public static Workbook readExcel(String fileName, InputStream inputStream) throws IOException {
        String extString = fileName.substring(fileName.lastIndexOf("."));
        if (extString.equals(EXCELXLS)) {
            return new HSSFWorkbook(inputStream);
        } else if (extString.equals(EXCELXLSX)) {
            return new XSSFWorkbook(inputStream);
        }
        return null;
    }
    /**
     * 根据后缀名称判断excel的版本
     *
     * @param filePath 文件路径
     * @return
     * @throws IOException
     */
    public static Workbook readExcel(String filePath) throws IOException {
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = new FileInputStream(filePath);
        if (".xls".equals(extString)) {
            return new HSSFWorkbook(is);
        } else if (".xlsx".equals(extString)) {
            return new XSSFWorkbook(is);
        } else {
            return null;
        }
    }
    /**
     * 转换文件流
     *
     * @param file 文件流
     * @return
     * @throws IOException
     */
    public static InputStream convertStream(MultipartFile file) throws IOException {
        //MultipartFile转换为文件流
        CommonsMultipartFile cFile = (CommonsMultipartFile) file;
        DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
        return fileItem.getInputStream();
    }

}
