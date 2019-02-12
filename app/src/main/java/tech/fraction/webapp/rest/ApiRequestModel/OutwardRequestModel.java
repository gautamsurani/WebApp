package tech.fraction.webapp.rest.ApiRequestModel;

public class OutwardRequestModel {
    private int totalRecords;

    private String item;

    private String inwardNo;

    private int pageSize;

    private String outwardedOnTo;

    private String broker;

    private int paidOn;

    private String outwardNo;

    private String isInvoiceGenerated;

    private String reportType;

    private String jsFunction;

    private int accountId;

    private String sortDirection;

    private String unit;

    private String paidStatus;

    private int pageIndex;

    private String sortBy;

    private int personId;

    private String location;

    private String outwardedOnFilter;

    private String personType;

    private String outwardedOnFrom;

    public OutwardRequestModel(int totalRecords, String item, String inwardNo, int pageSize, String outwardedOnTo, String broker, int paidOn,
                               String outwardNo, String isInvoiceGenerated, String reportType, String jsFunction, int accountId, String sortDirection,
                               String unit, String paidStatus,
                               int pageIndex, String sortBy, int personId, String location,
                               String outwardedOnFilter, String personType, String outwardedOnFrom) {
        this.totalRecords = totalRecords;
        this.item = item;
        this.inwardNo = inwardNo;
        this.pageSize = pageSize;
        this.outwardedOnTo = outwardedOnTo;
        this.broker = broker;
        this.paidOn = paidOn;
        this.outwardNo = outwardNo;
        this.isInvoiceGenerated = isInvoiceGenerated;
        this.reportType = reportType;
        this.jsFunction = jsFunction;
        this.accountId = accountId;
        this.sortDirection = sortDirection;
        this.unit = unit;
        this.paidStatus = paidStatus;
        this.pageIndex = pageIndex;
        this.sortBy = sortBy;
        this.personId = personId;
        this.location = location;
        this.outwardedOnFilter = outwardedOnFilter;
        this.personType = personType;
        this.outwardedOnFrom = outwardedOnFrom;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getInwardNo() {
        return inwardNo;
    }

    public void setInwardNo(String inwardNo) {
        this.inwardNo = inwardNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOutwardedOnTo() {
        return outwardedOnTo;
    }

    public void setOutwardedOnTo(String outwardedOnTo) {
        this.outwardedOnTo = outwardedOnTo;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public int getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(int paidOn) {
        this.paidOn = paidOn;
    }

    public String getOutwardNo() {
        return outwardNo;
    }

    public void setOutwardNo(String outwardNo) {
        this.outwardNo = outwardNo;
    }

    public String getIsInvoiceGenerated() {
        return isInvoiceGenerated;
    }

    public void setIsInvoiceGenerated(String isInvoiceGenerated) {
        this.isInvoiceGenerated = isInvoiceGenerated;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getJsFunction() {
        return jsFunction;
    }

    public void setJsFunction(String jsFunction) {
        this.jsFunction = jsFunction;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOutwardedOnFilter() {
        return outwardedOnFilter;
    }

    public void setOutwardedOnFilter(String outwardedOnFilter) {
        this.outwardedOnFilter = outwardedOnFilter;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getOutwardedOnFrom() {
        return outwardedOnFrom;
    }

    public void setOutwardedOnFrom(String outwardedOnFrom) {
        this.outwardedOnFrom = outwardedOnFrom;
    }
}