package com.social.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

public class ExportExcel2 {

    public void exportExcel(String title, String[] header, List<T> list, String path, String pattern) throws Exception {
        OutputStream out=null;
        //声明工作簿
        Workbook book = null;
        if(path.endsWith(".xls")){
            out = new FileOutputStream("path");
            book =  new HSSFWorkbook();
        }else if(path.endsWith(".xlsx")){
            out = new FileOutputStream("path");
            book =  new XSSFWorkbook();
        }else{
            out = new FileOutputStream("导出文件.xls");
            book =  new XSSFWorkbook();
        }

        Sheet sheet = book.createSheet();
        Row row = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }

        for (int i = 0; i < list.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            T t = list.get(i);
            Class c = t.getClass();
            Field[] files = c.getDeclaredFields();
            for (int j = 0; j < files.length; j++) {
                Cell cell = row.createCell(j);
                String name = files[j].getName();
                String  method="get"+ name.substring(0,1).toUpperCase()+name.substring(1);
                Method method1 = c.getMethod(method, null);
                Object object = method1.invoke(t, null);
                String value="";
                if(object != null){
                    value = object.toString();
                }
                cell.setCellValue(value);
            }
        }
    }
}
