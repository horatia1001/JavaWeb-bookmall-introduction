package com.book.pojo;

import java.util.List;

/*
* 将此类写为泛型类，可以更通用
* */
public class Page<T> {

    // 定义每页显示的记录数为常量
    public static final Integer PAGE_SIZE = 4;

    // 当前页码,从1开始
    private Integer pageNo;
    // 总记录数
    private Integer pageItemTotalCount;
    // 每页显示的记录数
    private Integer pageSize = PAGE_SIZE;
    // 总页码
    private Integer pageTotalCount;

    // 查询页的表项
    private List<T> pageItems;

    // 分页条的请求地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageItemTotalCount() {
        return pageItemTotalCount;
    }

    public void setPageItemTotalCount(Integer pageItemTotalCount) {
        this.pageItemTotalCount = pageItemTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getPageItems() {
        return pageItems;
    }

    public void setPageItems(List<T> pageItems) {
        this.pageItems = pageItems;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageItemTotalCount=" + pageItemTotalCount +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", pageItems=" + pageItems +
                ", url='" + url + '\'' +
                '}';
    }
}
