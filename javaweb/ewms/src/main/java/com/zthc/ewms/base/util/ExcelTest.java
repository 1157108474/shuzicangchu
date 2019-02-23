package com.zthc.ewms.base.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel����
 *
 * @author yyb
 * @create 2018-12-26-17:52
 */
public class ExcelTest {

    public static void main(String[] args) throws IOException {
// ���ݺ�׺�����ж�excel�İ汾
        Workbook wb = FileUtils.readExcel("D:\\Զ�˶�����ϸ.xlsx");
        List<Excel> excelList = new ArrayList<>();
        // ��ʼ��ȡ����
        if (wb != null) {
            //��ȡ��һ��sheet
            Sheet sheet = wb.getSheetAt(0);
            //��ȡ�������
            int rownum = sheet.getPhysicalNumberOfRows();
            //��ȡ��һ��
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
        System.out.println("���ظ�����������"+excelList.size());
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