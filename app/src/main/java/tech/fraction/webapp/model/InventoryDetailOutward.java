package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class InventoryDetailOutward implements Serializable {
    private int OutwardId;

    private String LastName;

    private int TotalRecords;

    private String AccountName;

    private String Mobile1;

    private String OutwardOn;

    private ArrayList<OutwardItems> OutwardItems;

    private OutwardPaidDetail OutwardPaidDetail;

    private String InvoiceGeneratedOn;

    private String PaidStatus;

    private boolean IsModified;

    private String Mobile2;

    private boolean CanDeleteOutWarDetail;

    private String Address;

    private String ReceiptType;

    private int UniqRecordId;

    private String PaidOn;

    private String OutwardNo;

    private int AccountId;

    private String FirstName;

    private int TotalLoadingCharges;

    private Transporter Transporter;

    private String MiddleName;

    private int InvoiceId;

    private int TotalOtherCharges;

    private int PaidAmount;

    public int getOutwardId() {
        return OutwardId;
    }

    public void setOutwardId(int outwardId) {
        OutwardId = outwardId;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        TotalRecords = totalRecords;
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

    public String getOutwardOn() {
        return OutwardOn;
    }

    public void setOutwardOn(String outwardOn) {
        OutwardOn = outwardOn;
    }

    public ArrayList<tech.fraction.webapp.model.OutwardItems> getOutwardItems() {
        return OutwardItems;
    }

    public void setOutwardItems(ArrayList<tech.fraction.webapp.model.OutwardItems> outwardItems) {
        OutwardItems = outwardItems;
    }

    public tech.fraction.webapp.model.OutwardPaidDetail getOutwardPaidDetail() {
        return OutwardPaidDetail;
    }

    public void setOutwardPaidDetail(tech.fraction.webapp.model.OutwardPaidDetail outwardPaidDetail) {
        OutwardPaidDetail = outwardPaidDetail;
    }

    public String getInvoiceGeneratedOn() {
        return InvoiceGeneratedOn;
    }

    public void setInvoiceGeneratedOn(String invoiceGeneratedOn) {
        InvoiceGeneratedOn = invoiceGeneratedOn;
    }

    public String getPaidStatus() {
        return PaidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        PaidStatus = paidStatus;
    }

    public boolean isModified() {
        return IsModified;
    }

    public void setModified(boolean modified) {
        IsModified = modified;
    }

    public String getMobile2() {
        return Mobile2;
    }

    public void setMobile2(String mobile2) {
        Mobile2 = mobile2;
    }

    public boolean isCanDeleteOutWarDetail() {
        return CanDeleteOutWarDetail;
    }

    public void setCanDeleteOutWarDetail(boolean canDeleteOutWarDetail) {
        CanDeleteOutWarDetail = canDeleteOutWarDetail;
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

    public int getUniqRecordId() {
        return UniqRecordId;
    }

    public void setUniqRecordId(int uniqRecordId) {
        UniqRecordId = uniqRecordId;
    }

    public String getPaidOn() {
        return PaidOn;
    }

    public void setPaidOn(String paidOn) {
        PaidOn = paidOn;
    }

    public String getOutwardNo() {
        return OutwardNo;
    }

    public void setOutwardNo(String outwardNo) {
        OutwardNo = outwardNo;
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

    public int getTotalLoadingCharges() {
        return TotalLoadingCharges;
    }

    public void setTotalLoadingCharges(int totalLoadingCharges) {
        TotalLoadingCharges = totalLoadingCharges;
    }

    public tech.fraction.webapp.model.Transporter getTransporter() {
        return Transporter;
    }

    public void setTransporter(tech.fraction.webapp.model.Transporter transporter) {
        Transporter = transporter;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public int getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        InvoiceId = invoiceId;
    }

    public int getTotalOtherCharges() {
        return TotalOtherCharges;
    }

    public void setTotalOtherCharges(int totalOtherCharges) {
        TotalOtherCharges = totalOtherCharges;
    }

    public int getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        PaidAmount = paidAmount;
    }
}
