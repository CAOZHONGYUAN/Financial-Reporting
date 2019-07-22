package com.fms.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageBeanOne<T> {
    private int pageIndex = 1; // 需要显示的页码
    private int totalPages = 1;  // 总页数
    private int pageSize = 5;  // 每页记录数
    private int totalRecords = 0; // 总记录数
    private boolean isHavePrePage = false; // 是否有上一页
    private boolean isHaveNextPage = false; // 是否有下一页

    private List<Map<String, Object>> pageDatas = (List<Map<String, Object>>) new ArrayList<T>();

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        if(totalRecords < 0){
            throw new RuntimeException("总记录数不能小于0!");
        }
//设置总记录数
        this.totalRecords = totalRecords;
//计算总页数
        this.totalPages = this.totalRecords/this.pageSize;
        if(this.totalRecords%this.pageSize!=0){
            this.totalPages++;
        }
//计算是否有上一页
        if(this.pageIndex>1){
            this.isHavePrePage = true;
        }else{
            this.isHavePrePage = false;
        }
//计算是否有下一页
        if(this.pageIndex<this.totalPages){
            this.isHaveNextPage = true;
        }else{
            this.isHaveNextPage = false;
        }
    }

    public List<Map<String, Object>> getPageDatas() {
        return pageDatas;
    }

    public void setPageDatas(List<Map<String, Object>> pageDatas) {
        this.pageDatas = pageDatas;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean getIsHavePrePage() {
        return isHavePrePage;
    }

    public boolean getIsHaveNextPage() {
        return isHaveNextPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageIndex=" + pageIndex +
                ", totalPages=" + totalPages +
                ", pageSize=" + pageSize +
                ", totalRecords=" + totalRecords +
                ", isHavePrePage=" + isHavePrePage +
                ", isHaveNextPage=" + isHaveNextPage +
                ", pageDatas=" + pageDatas +
                '}';
    }
}
