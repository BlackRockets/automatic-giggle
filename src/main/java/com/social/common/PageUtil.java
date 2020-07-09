package com.social.common;

import java.util.List;

public class PageUtil<T> {

    private  int pageNum;//当前页

    private int pageSize = 5;//每页条数

    private int total;//总条数

    private int pages;//总页数

    private List<T> result;//结果集

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
        pages = total%pageSize ==0 ?total/pageSize:total/pageSize +1;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
