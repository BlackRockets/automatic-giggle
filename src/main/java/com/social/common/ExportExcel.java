package com.social.common;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class ExportExcel<T> {

    public void export(OutputStream outputStream, List<T> list,String[] henders) throws  Exception{

        HSSFWorkbook workbook = new HSSFWorkbook();//2003版
        //XSSFWorkbook 2007
        HSSFSheet sheet = workbook.createSheet("first");
       //创建第0行数据标签头信息
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i <henders.length;i ++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(henders[i]);
        }
       for (int i = 0; i <list.size(); i ++){
           HSSFRow row1 = sheet.createRow(i+1);
           T t = list.get(i);
           Class cl = t.getClass();//获取类的class对象
           Field[] fields = cl.getDeclaredFields();//获取所有属性
           for (int j = 0; j <fields.length;j ++){
               HSSFCell cell  = row1.createCell(j);
               String name = fields[j].getName();//获取属性名
               String method = "get" + name.substring(0,1).toUpperCase()+ name.substring(1);//
               Method  method1 = cl.getMethod(method,null);//获取方法
               Object obj =  method1.invoke(t,null);
               String value = "";
               if (obj != null){
                   value = obj.toString();
               }
               cell.setCellValue(value);

           }



       }

        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();


    }

    public void export2007(OutputStream outputStream, List<T> list,String[] henders) throws  Exception{

        XSSFWorkbook workbook = new XSSFWorkbook();//2003版
        //XSSFWorkbook 2007
        XSSFSheet sheet = workbook.createSheet("first");
        //创建第0行数据标签头信息
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i <henders.length;i ++){
            XSSFCell cell = row.createCell(i);
            cell.setCellValue(henders[i]);
        }
        for (int i = 0; i <list.size(); i ++){
            XSSFRow row1 = sheet.createRow(i+1);
            T t = list.get(i);
            Class cl = t.getClass();//获取类的class对象
            Field[] fields = cl.getDeclaredFields();//获取所有属性
            for (int j = 0; j <fields.length;j ++){
                XSSFCell cell  = row1.createCell(j);
                String name = fields[j].getName();//获取属性名
                String method = "get" + name.substring(0,1).toUpperCase()+ name.substring(1);//
                Method  method1 = cl.getMethod(method,null);//获取方法
                Object obj =  method1.invoke(t,null);
                String value = "";
                if (obj != null){
                    value = obj.toString();
                }
                cell.setCellValue(value);

            }



        }

        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();


    }

    
}
