package com.example.basicproject.dto;

public class Pagination<T> {

    private T record;
    private Long total;
    private Integer pageSize;
    private Integer currentPage;

    public static <E> Pagination<E> create(Long total,Integer pageSize,Integer currentPage,E record){
        Pagination<E> pagination = new Pagination<>();
        pagination.setPageSize(pageSize);
        pagination.setTotal(total);
        pagination.setCurrentPage(currentPage);
        pagination.setRecord(record);

        return pagination;
    }
    public T getRecord() {
        return record;
    }

    public void setRecord(T record) {
        this.record = record;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}
