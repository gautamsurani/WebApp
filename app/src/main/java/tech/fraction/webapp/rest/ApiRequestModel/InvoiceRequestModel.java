package tech.fraction.webapp.rest.ApiRequestModel;

import java.io.Serializable;
import java.util.ArrayList;

import tech.fraction.webapp.model.PartyWiseInvoicePaging;

public class InvoiceRequestModel implements Serializable {

    private int totalRecords;

    private String year;

    private ArrayList<PartyWiseInvoicePaging> partyWiseInvoicePaging;

    private int pageSize;

    private int partyWisePagingAccountId;

    private String jsFunction;

    private int partywisePageIndex;

    private String sortDirection;

    private String paidStatus;

    private String outwardNumber;

    private String sortBy;

    private int invoiceGeneratedPeriod;

    private String invoiceNo;

    private String personType;

    private boolean isPartyWiseInvoice;

    private String receiptType;

    private String inwardNumber;

    private String broker;

    private int paidOn;

    private int accountId;

    private int partywisePageSize;

    private String month;

    private int pageIndex;

    private String reporType;

    private int personId;

    public InvoiceRequestModel(int totalRecords, String year, ArrayList<PartyWiseInvoicePaging> partyWiseInvoicePaging, int pageSize, int partyWisePagingAccountId,
                               String jsFunction, int partywisePageIndex, String sortDirection, String paidStatus, String outwardNumber, String sortBy,
                               int invoiceGeneratedPeriod, String invoiceNo, String personType, boolean isPartyWiseInvoice, String receiptType,
                               String inwardNumber, String broker, int paidOn, int accountId, int partywisePageSize, String month, int pageIndex,
                               String reporType, int personId) {
        this.totalRecords = totalRecords;
        this.year = year;
        this.partyWiseInvoicePaging = partyWiseInvoicePaging;
        this.pageSize = pageSize;
        this.partyWisePagingAccountId = partyWisePagingAccountId;
        this.jsFunction = jsFunction;
        this.partywisePageIndex = partywisePageIndex;
        this.sortDirection = sortDirection;
        this.paidStatus = paidStatus;
        this.outwardNumber = outwardNumber;
        this.sortBy = sortBy;
        this.invoiceGeneratedPeriod = invoiceGeneratedPeriod;
        this.invoiceNo = invoiceNo;
        this.personType = personType;
        this.isPartyWiseInvoice = isPartyWiseInvoice;
        this.receiptType = receiptType;
        this.inwardNumber = inwardNumber;
        this.broker = broker;
        this.paidOn = paidOn;
        this.accountId = accountId;
        this.partywisePageSize = partywisePageSize;
        this.month = month;
        this.pageIndex = pageIndex;
        this.reporType = reporType;
        this.personId = personId;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ArrayList<PartyWiseInvoicePaging> getPartyWiseInvoicePaging() {
        return partyWiseInvoicePaging;
    }

    public void setPartyWiseInvoicePaging(ArrayList<PartyWiseInvoicePaging> partyWiseInvoicePaging) {
        this.partyWiseInvoicePaging = partyWiseInvoicePaging;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPartyWisePagingAccountId() {
        return partyWisePagingAccountId;
    }

    public void setPartyWisePagingAccountId(int partyWisePagingAccountId) {
        this.partyWisePagingAccountId = partyWisePagingAccountId;
    }

    public String getJsFunction() {
        return jsFunction;
    }

    public void setJsFunction(String jsFunction) {
        this.jsFunction = jsFunction;
    }

    public int getPartywisePageIndex() {
        return partywisePageIndex;
    }

    public void setPartywisePageIndex(int partywisePageIndex) {
        this.partywisePageIndex = partywisePageIndex;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public String getOutwardNumber() {
        return outwardNumber;
    }

    public void setOutwardNumber(String outwardNumber) {
        this.outwardNumber = outwardNumber;
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

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public boolean isPartyWiseInvoice() {
        return isPartyWiseInvoice;
    }

    public void setPartyWiseInvoice(boolean partyWiseInvoice) {
        isPartyWiseInvoice = partyWiseInvoice;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getInwardNumber() {
        return inwardNumber;
    }

    public void setInwardNumber(String inwardNumber) {
        this.inwardNumber = inwardNumber;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPartywisePageSize() {
        return partywisePageSize;
    }

    public void setPartywisePageSize(int partywisePageSize) {
        this.partywisePageSize = partywisePageSize;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getReporType() {
        return reporType;
    }

    public void setReporType(String reporType) {
        this.reporType = reporType;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
