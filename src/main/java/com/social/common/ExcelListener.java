package com.social.common;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExcelListener extends AnalysisEventListener {
    //自定义用于暂时存储data
    private List<Object> datas = Collections.synchronizedList(new ArrayList());

    /**
     * 通过 AnalysisContext 对象还可以获取当前 sheet，当前行等数据
     */
    public void invoke(Object object, AnalysisContext context) {
        System.out.println("当前行："+context.getCurrentRowNum());
        System.out.println(object);
        datas.add(object);//数据存储到list，供批量处理，或后续自己业务逻辑处理。
        doSomething(object);//根据自己业务做处理
    }

    /**
     * 读取完之后的操作
     */
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    private void doSomething(Object object) {
        //1、入库调用接口
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}
