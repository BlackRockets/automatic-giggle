package com.social.pojo;

public class Page {
    // 当前页码，默认1
    private int current = 1;
    // 显示上限，本题默认10
    private int limit = 10;
    // 数据总数(用于计算总页数)
    private int rows;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }



    //获取当前页的起始行
    public int getOffset() {
        // current * limit - limit
        return (current - 1) * limit;
    }

    //获取总页数
    public int getTotal() {
        // rows / limit [+1]
        if (rows % limit == 0) {
            return rows / limit;
        } else {
            return rows / limit + 1;
        }
    }


}
