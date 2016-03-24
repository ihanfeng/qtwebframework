package com.qtong.core.model;

import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by ZML on 2015/8/5.
 */
public class Pager<T> {

    private long totalCount;

    private int startIndex;

    private int currentPage;

    private int pageSize;

    private List<T> list;


    public Pager(int startIndex, long totalCount, int pageSize, List<?> list) {

        Assert.isTrue(pageSize >= 1, "PageSize should bigger than 0");

        this.startIndex=startIndex;

        this.currentPage=startIndex/pageSize+1;

        this.pageSize=pageSize;

    }

    public Pager() {

    }

    public static int getStartOfPage(int pageNo, int pageSize) {


        return (pageNo-1)*pageSize+1;
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
