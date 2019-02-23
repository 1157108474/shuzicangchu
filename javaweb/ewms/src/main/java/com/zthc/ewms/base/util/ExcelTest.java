package com.zthc.ewms.base.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel测试
 *
 * @author yyb
 * @create 2018-12-26-17:52
 */
public class ExcelTest {

    public static void main(String[] args) throws IOException {
// 根据后缀名称判断excel的版本
        Workbook wb = FileUtils.readExcel("D:\\远兴订单明细.xlsx");
        List<Excel> excelList = new ArrayList<>();
        // 开始读取数据
        if (wb != null) {
            //获取第一个sheet
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            Row row = sheet.getRow(0);
            Excel excel ;
            Boolean bool=true;
            for (int i = 1; i < rownum; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    excel = new Excel();
                    excel.setCode(ExcelImport.getValue(row.getCell(1)));
                    for (Excel e : excelList){
                        if(e.getCode().equals(excel.getCode())){
                            bool =false;
                            break;
                        }
                    }
                    if (bool){
                        excelList.add(excel);
                    }
                }
                bool=true;
            }
        }
        System.out.println("不重复编码数量："+excelList.size());
        for (Excel  excel :excelList){
            System.out.print(excel.getCode()+",");
        }

    }




}

class Excel{
    private  String code ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}