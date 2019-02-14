package tech.fraction.webapp.model;

import java.util.ArrayList;

public class PersonInformationInvoice {

    private int UniqRecordId;

    private int AccountId;

    private Paging Paging;

    private int TotalRecords;

    private ArrayList<Invoices> Invoices;

    private Double TotalAmount;

    private int LastPaidAmount;

    private int PaidAmount;

    private int RemainingAmount;

    private int TotalInvoices;

    private String AccountName;

    public int getUniqRecordId() {
        return UniqRecordId;
    }

    public void setUniqRecordId(int uniqRecordId) {
        UniqRecordId = uniqRecordId;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public tech.fraction.webapp.model.Paging getPaging() {
        return Paging;
    }

    public void setPaging(tech.fraction.webapp.model.Paging paging) {
        Paging = paging;
    }

    public int getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        TotalRecords = totalRecords;
    }

    public ArrayList<Invoices> getInvoices() {
        return Invoices;
    }

    public void setInvoices(ArrayList<Invoices> invoices) {
        Invoices = invoices;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }

    public int getLastPaidAmount() {
        return LastPaidAmount;
    }

    public void setLastPaidAmount(int lastPaidAmount) {
        LastPaidAmount = lastPaidAmount;
    }

    public int getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        PaidAmount = paidAmount;
    }

    public int getRemainingAmount() {
        return RemainingAmount;
    }

    public void setRemainingAmount(int remainingAmount) {
        RemainingAmount = remainingAmount;
    }

    public int getTotalInvoices() {
        return TotalInvoices;
    }

    public void setTotalInvoices(int totalInvoices) {
        TotalInvoices = totalInvoices;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }
}
