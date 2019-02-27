package tech.fraction.webapp.rest.ApiRequestModel;

import java.io.Serializable;

public class InwardRequestModel implements Serializable {

    private int totalRecords;

    private int pageSize;

    private String inwardedOnFilter;

    private String jsFunction;

    private String sortDirection;

    private String sortByField;

    private String paidStatus;

    private String pageType;

    private String sortBy;

    private int invoiceGeneratedPeriod;

    private String personType;

    private String item;

    private String inwardNo;

    private String broker;

    private int paidOn;

    private String marko;

    private int accountId;

    private String unit;

    private String inwardedOnTo;

    private String sortByExpression;

    private int invoiceDue;

    private int pageIndex;

    private int personId;

    private String location;

    private String inwardedOnFrom;

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getInwardedOnFilter() {
        return inwardedOnFilter;
    }

    public void setInwardedOnFilter(String inwardedOnFilter) {
        this.inwardedOnFilter = inwardedOnFilter;
    }

    public String getJsFunction() {
        return jsFunction;
    }

    public void setJsFunction(String jsFunction) {
        this.jsFunction = jsFunction;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSortByField() {
        return sortByField;
    }

    public void setSortByField(String sortByField) {
        this.sortByField = sortByField;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getInvoiceGeneratedPeriod() {
        return invoiceGeneratedPeriod;
    }

    public void setInvoiceGeneratedPeriod(int invoiceGeneratedPeriod) {
        this.invoiceGeneratedPeriod = invoiceGeneratedPeriod;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
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

    public String getMarko() {
        return marko;
    }

    public void setMarko(String marko) {
        this.marko = marko;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getInwardedOnTo() {
        return inwardedOnTo;
    }

    public void setInwardedOnTo(String inwardedOnTo) {
        this.inwardedOnTo = inwardedOnTo;
    }

    public String getSortByExpression() {
        return sortByExpression;
    }

    public void setSortByExpression(String sortByExpression) {
        this.sortByExpression = sortByExpression;
    }

    public int getInvoiceDue() {
        return invoiceDue;
    }

    public void setInvoiceDue(int invoiceDue) {
        this.invoiceDue = invoiceDue;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
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

    public String getInwardedOnFrom() {
        return inwardedOnFrom;
    }

    public void setInwardedOnFrom(String inwardedOnFrom) {
        this.inwardedOnFrom = inwardedOnFrom;
    }

    public InwardRequestModel(int totalRecords, int pageSize, String inwardedOnFilter, String jsFunction, String sortDirection,
                              String sortByField, String paidStatus, String pageType, String sortBy,
                              int invoiceGeneratedPeriod, String personType, String item, String inwardNo,
                              String broker, int paidOn, String marko, int accountId,
                              String unit, String inwardedOnTo, String sortByExpression, int invoiceDue,
                              int pageIndex, int personId, String location, String inwardedOnFrom) {
        this.totalRecords = totalRecords;
        this.pageSize = pageSize;
        this.inwardedOnFilter = inwardedOnFilter;
        this.jsFunction = jsFunction;
        this.sortDirection = sortDirection;
        this.sortByField = sortByField;
        this.paidStatus = paidStatus;
        this.pageType = pageType;
        this.sortBy = sortBy;
        this.invoiceGeneratedPeriod = invoiceGeneratedPeriod;
        this.personType = personType;
        this.item = item;
        this.inwardNo = inwardNo;
        this.broker = broker;
        this.paidOn = paidOn;
        this.marko = marko;
        this.accountId = accountId;
        this.unit = unit;
        this.inwardedOnTo = inwardedOnTo;
        this.sortByExpression = sortByExpression;
        this.invoiceDue = invoiceDue;
        this.pageIndex = pageIndex;
        this.personId = personId;
        this.location = location;
        this.inwardedOnFrom = inwardedOnFrom;
    }

    public InwardRequestModel() {
    }
}