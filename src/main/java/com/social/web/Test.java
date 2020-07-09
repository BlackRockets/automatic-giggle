package com.social.web;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Test {



    public static void main(String[] arg0) throws IOException, ParseException {
        /*String filePath = "/home/chenmingjian/Downloads/测试.xlsx";
        List<List<String>> data = new ArrayList();
        *//*data.add(Arrays.asList("111","222","333"));
        data.add(Arrays.asList("111","222","333"));
        data.add(Arrays.asList("111","222","333"));*//*
        for (int i = 0; i < 10; i++) {
            List<String> list = new ArrayList();
            list.add(i + "");
            list.add(i+1 + "");
            list.add(i+2 + "");
            data.add(list);
        }
        List<List<String>> head = new ArrayList();
        List<String> head1 = Arrays.asList("表头1", "表头2", "表头3");
        List<String> head11 = Arrays.asList("表头11", "表头12", "表头31");
        List<String> head111 = Arrays.asList("表头111", "表头11", "表头311");
        head.add(head1);
        head.add(head11);
        head.add(head111);
        Sheet sheet = new Sheet(1,1);
        sheet.setSheetName("单元格名称");
        Table table = new Table(1);
        table.setHead(head);
        ExcelWriter excelWriter = new ExcelWriter(new FileOutputStream("D://b.xls"), ExcelTypeEnum.XLS);
        excelWriter.write0(data,sheet,table);
        excelWriter.finish();*/


        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String date = "2013.09.29";
        Date samplingTime = format.parse(date);
        System.out.println(samplingTime);

    }
}
