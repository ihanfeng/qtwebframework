package com.qtong.core.model;

import org.springframework.util.Assert;

import java.util.List;

/**
 * 工具类，用于在数据库分页查询的时候进行参数传递
 */
public class Pager<T> {
    //总条数
    private long totalCount;
    //当前页开始记录
    private int startIndex;
    //当前页码
    private int currentPage;
    //每页大小
    private int pageSize;
    //数据记录
    private List<T> list;

    public Pager(int startIndex, long totalCount, int pageSize, List<?> list) {

        Assert.isTrue(pageSize >= 1, "PageSize should bigger than 0");

        this.startIndex = startIndex;

        this.currentPage = startIndex / pageSize + 1;

        this.pageSize = pageSize;

    }

    public Pager() {

    }

    public static int getStartOfPage(int pageNo, int pageSize) {


        return (pageNo - 1) * pageSize + 1;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
