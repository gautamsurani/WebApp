package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Invoices implements Serializable {
    private String AccountId;

    private boolean IsFullyPaid;

    private String Address;

    private String GeneratedOnString;

    private Double TotalLoadingCharges;

    private String CSNo;

    private String ReceiptType;

    private String Number;

    private String AmountDetail;

    private String PANNo;

    private int TotalRecords;

    private int TotalOtherCharges;

    private String Tax2;

    private String PaidStatus;

    private ArrayList<InvoiceDetails> InvoiceDetails;

    private String Tax1;

    private String ToDateString;

    private String TotalUnloadingCharges;

    private String FromDateString;

    private String FirstName;

    private String OutwardInvoiceDetail;

    private String GST;

    private String GeneratedOn;

    private Double TotalAmount;

    private String TransactionOn;

    private Double LastPaidAmount;

    private String MiddleName;

    private Double PaidAmount;

    private String Mobile1;

    private boolean CanDeleteInvoice;

    private String Mobile2;

    private Double TotalRent;

    private int Id;

    private String LastName;

    private String AccountName;

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public boolean isFullyPaid() {
        return IsFullyPaid;
    }

    public void setFullyPaid(boolean fullyPaid) {
        IsFullyPaid = fullyPaid;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGeneratedOnString() {
        return GeneratedOnString;
    }

    public void setGeneratedOnString(String generatedOnString) {
        GeneratedOnString = generatedOnString;
    }

    public Double getTotalLoadingCharges() {
        return TotalLoadingCharges;
    }

    public void setTotalLoadingCharges(Double totalLoadingCharges) {
        TotalLoadingCharges = totalLoadingCharges;
    }

    public String getCSNo() {
        return CSNo;
    }

    public void setCSNo(String CSNo) {
        this.CSNo = CSNo;
    }

    public String getReceiptType() {
        return ReceiptType;
    }

    public void setReceiptType(String receiptType) {
        ReceiptType = receiptType;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getAmountDetail() {
        return AmountDetail;
    }

    public void setAmountDetail(String amountDetail) {
        AmountDetail = amountDetail;
    }

    public String getPANNo() {
        return PANNo;
    }

    public void setPANNo(String PANNo) {
        this.PANNo = PANNo;
    }

    public int getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        TotalRecords = totalRecords;
    }

    public int getTotalOtherCharges() {
        return TotalOtherCharges;
    }

    public void setTotalOtherCharges(int totalOtherCharges) {
        TotalOtherCharges = totalOtherCharges;
    }

    public String getTax2() {
        return Tax2;
    }

    public void setTax2(String tax2) {
        Tax2 = tax2;
    }

    public String getPaidStatus() {
        return PaidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        PaidStatus = paidStatus;
    }

    public ArrayList<InvoiceDetails> getInvoiceDetails() {
        return InvoiceDetails;
    }

    public void setInvoiceDetails(ArrayList<InvoiceDetails> invoiceDetails) {
        InvoiceDetails = invoiceDetails;
    }

    public String getTax1() {
        return Tax1;
    }

    public void setTax1(String tax1) {
        Tax1 = tax1;
    }

    public String getToDateString() {
        return ToDateString;
    }

    public void setToDateString(String toDateString) {
        ToDateString = toDateString;
    }

    public String getTotalUnloadingCharges() {
        return TotalUnloadingCharges;
    }

    public void setTotalUnloadingCharges(String totalUnloadingCharges) {
        TotalUnloadingCharges = totalUnloadingCharges;
    }

    public String getFromDateString() {
        return FromDateString;
    }

    public void setFromDateString(String fromDateString) {
        FromDateString = fromDateString;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getOutwardInvoiceDetail() {
        return OutwardInvoiceDetail;
    }

    public void setOutwardInvoiceDetail(String outwardInvoiceDetail) {
        OutwardInvoiceDetail = outwardInvoiceDetail;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getGeneratedOn() {
        return GeneratedOn;
    }

    public void setGeneratedOn(String generatedOn) {
        GeneratedOn = generatedOn;
    }

    public Double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getTransactionOn() {
        return TransactionOn;
    }

    public void setTransactionOn(String transactionOn) {
        TransactionOn = transactionOn;
    }

    public Double getLastPaidAmount() {
        return LastPaidAmount;
    }

    public void setLastPaidAmount(Double lastPaidAmount) {
        LastPaidAmount = lastPaidAmount;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public Double getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        PaidAmount = paidAmount;
    }

    public String getMobile1() {
        return Mobile1;
    }

    public void setMobile1(String mobile1) {
        Mobile1 = mobile1;
    }

    public boolean isCanDeleteInvoice() {
        return CanDeleteInvoice;
    }

    public void setCanDeleteInvoice(boolean canDeleteInvoice) {
        CanDeleteInvoice = canDeleteInvoice;
    }

    public String getMobile2() {
        return Mobile2;
    }

    public void setMobile2(String mobile2) {
        Mobile2 = mobile2;
    }

    public Double getTotalRent() {
        return TotalRent;
    }

    public void setTotalRent(Double totalRent) {
        TotalRent = totalRent;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }
}
