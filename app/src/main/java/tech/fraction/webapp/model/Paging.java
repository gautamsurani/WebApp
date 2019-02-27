package tech.fraction.webapp.model;

import java.io.Serializable;

public class Paging implements Serializable {

    private String JsFunction;

    private int TotalRecords;

    private String SortDirection;

    private int PageIndex;

    private String ProductBy;

    private int PageSize;

    private String SortBy;

    private int Id;

    private String Keyword;

    public String getJsFunction() {
        return JsFunction;
    }

    public void setJsFunction(String jsFunction) {
        JsFunction = jsFunction;
    }

    public int getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        TotalRecords = totalRecords;
    }

    public String getSortDirection() {
        return SortDirection;
    }

    public void setSortDirection(String sortDirection) {
        SortDirection = sortDirection;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
    }

    public String getProductBy() {
        return ProductBy;
    }

    public void setProductBy(String productBy) {
        ProductBy = productBy;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public String getSortBy() {
        return SortBy;
    }

    public void setSortBy(String sortBy) {
        SortBy = sortBy;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        Keyword = keyword;
    }
}
