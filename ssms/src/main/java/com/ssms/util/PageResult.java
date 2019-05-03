package com.ssms.util;

import lombok.Data;

import java.util.List;

/**
 * 分页结果对象,这里以layui框架的table为标准
 */
@Data
public class PageResult<T> {

    private int code; //状态码, 0表示成功

    private String msg;  //提示信息

    private long count; // 总数量, bootstrapTable是total

    private List<T> data; // 当前数据, bootstrapTable是rows

    public PageResult() {
    }

    public PageResult(List<T> rows) {
        this.data = rows;
        this.count = rows.size();
        this.code = 0;
        this.msg = "";
    }

    public PageResult(long total, List<T> rows) {
        this.count = total;
        this.data = rows;
        this.code = 0;
        this.msg = "";
    }

    public void setData(List<T> data) {
        this.data = data;
        this.count = data.size();
    }
}
