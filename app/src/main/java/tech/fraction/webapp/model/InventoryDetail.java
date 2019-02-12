package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;

public class InventoryDetail implements Serializable {
    private String LastName;

    private int InwardDetailId;

    private int TotalRecords;

    private String InvoicePaidOn;

    private String AccountName;

    private String Mobile1;

    private String InvoiceGenerationDuePeriod;

    private String PaidStatus;

    private String InwardedOn;

    private String Mobile2;

    private String CanDeleteInward;

    private boolean IsInvoicePaid;

    private int UnloadingCharges;

    private String Address;

    private String ReceiptType;

    private String InwardNo;

    private String TransactionOn;

    private String LastInvoiceGeneratedOn;

    private int UniqRecordId;

    private int AccountId;

    private String FirstName;

    private Transporter Transporter;

    private InwardPaidDetail InwardPaidDetail;

    private String MiddleName;

    private List<InwardItems> InwardItems;

    private String PaidAmount;

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getInwardDetailId() {
        return InwardDetailId;
    }

    public void setInwardDetailId(int inwardDetailId) {
        InwardDetailId = inwardDetailId;
    }

    public int getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        TotalRecords = totalRecords;
    }

    public String getInvoicePaidOn() {
        return InvoicePaidOn;
    }

    public void setInvoicePaidOn(String invoicePaidOn) {
        InvoicePaidOn = invoicePaidOn;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getMobile1() {
        return Mobile1;
    }

    public void setMobile1(String mobile1) {
        Mobile1 = mobile1;
    }

    public String getInvoiceGenerationDuePeriod() {
        return InvoiceGenerationDuePeriod;
    }

    public void setInvoiceGenerationDuePeriod(String invoiceGenerationDuePeriod) {
        InvoiceGenerationDuePeriod = invoiceGenerationDuePeriod;
    }

    public String getPaidStatus() {
        return PaidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        PaidStatus = paidStatus;
    }

    public String getInwardedOn() {
        return InwardedOn;
    }

    public void setInwardedOn(String inwardedOn) {
        InwardedOn = inwardedOn;
    }

    public String getMobile2() {
        return Mobile2;
    }

    public void setMobile2(String mobile2) {
        Mobile2 = mobile2;
    }

    public String getCanDeleteInward() {
        return CanDeleteInward;
    }

    public void setCanDeleteInward(String canDeleteInward) {
        CanDeleteInward = canDeleteInward;
    }

    public boolean isInvoicePaid() {
        return IsInvoicePaid;
    }

    public void setInvoicePaid(boolean invoicePaid) {
        IsInvoicePaid = invoicePaid;
    }

    public int getUnloadingCharges() {
        return UnloadingCharges;
    }

    public void setUnloadingCharges(int unloadingCharges) {
        UnloadingCharges = unloadingCharges;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getReceiptType() {
        return ReceiptType;
    }

    public void setReceiptType(String receiptType) {
        ReceiptType = receiptType;
    }

    public String getInwardNo() {
        return InwardNo;
    }

    public void setInwardNo(String inwardNo) {
        InwardNo = inwardNo;
    }

    public String getTransactionOn() {
        return TransactionOn;
    }

    public void setTransactionOn(String transactionOn) {
        TransactionOn = transactionOn;
    }

    public String getLastInvoiceGeneratedOn() {
        return LastInvoiceGeneratedOn;
    }

    public void setLastInvoiceGeneratedOn(String lastInvoiceGeneratedOn) {
        LastInvoiceGeneratedOn = lastInvoiceGeneratedOn;
    }

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

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public tech.fraction.webapp.model.Transporter getTransporter() {
        return Transporter;
    }

    public void setTransporter(tech.fraction.webapp.model.Transporter transporter) {
        Transporter = transporter;
    }

    public tech.fraction.webapp.model.InwardPaidDetail getInwardPaidDetail() {
        return InwardPaidDetail;
    }

    public void setInwardPaidDetail(tech.fraction.webapp.model.InwardPaidDetail inwardPaidDetail) {
        InwardPaidDetail = inwardPaidDetail;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public List<tech.fraction.webapp.model.InwardItems> getInwardItems() {
        return InwardItems;
    }

    public void setInwardItems(List<tech.fraction.webapp.model.InwardItems> inwardItems) {
        InwardItems = inwardItems;
    }

    public String getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        PaidAmount = paidAmount;
    }
}
