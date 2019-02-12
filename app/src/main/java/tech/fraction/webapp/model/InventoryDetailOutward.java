package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class InventoryDetailOutward implements Serializable {
    private int outwardId;

    private String lastName;

    private int totalRecords;

    private String accountName;

    private String mobile1;

    private String outwardOn;

    private ArrayList<OutwardItems> outwardItems;

    private OutwardPaidDetail outwardPaidDetail;

    private String invoiceGeneratedOn;

    private String paidStatus;

    private boolean isModified;

    private String mobile2;

    private boolean canDeleteOutWarDetail;

    private String address;

    private String receiptType;

    private int uniqRecordId;

    private String paidOn;

    private String outwardNo;

    private int accountId;

    private String firstName;

    private int totalLoadingCharges;

    private Transporter transporter;

    private String middleName;

    private int invoiceId;

    private int totalOtherCharges;

    private int paidAmount;

    public int getOutwardId() {
        return outwardId;
    }

    public void setOutwardId(int outwardId) {
        this.outwardId = outwardId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getOutwardOn() {
        return outwardOn;
    }

    public void setOutwardOn(String outwardOn) {
        this.outwardOn = outwardOn;
    }

    public ArrayList<OutwardItems> getOutwardItems() {
        return outwardItems;
    }

    public void setOutwardItems(ArrayList<OutwardItems> outwardItems) {
        this.outwardItems = outwardItems;
    }

    public OutwardPaidDetail getOutwardPaidDetail() {
        return outwardPaidDetail;
    }

    public void setOutwardPaidDetail(OutwardPaidDetail outwardPaidDetail) {
        this.outwardPaidDetail = outwardPaidDetail;
    }

    public String getInvoiceGeneratedOn() {
        return invoiceGeneratedOn;
    }

    public void setInvoiceGeneratedOn(String invoiceGeneratedOn) {
        this.invoiceGeneratedOn = invoiceGeneratedOn;
    }

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    public boolean getCanDeleteOutWarDetail() {
        return canDeleteOutWarDetail;
    }

    public void setCanDeleteOutWarDetail(boolean canDeleteOutWarDetail) {
        this.canDeleteOutWarDetail = canDeleteOutWarDetail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public int getUniqRecordId() {
        return uniqRecordId;
    }

    public void setUniqRecordId(int uniqRecordId) {
        this.uniqRecordId = uniqRecordId;
    }

    public String getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(String paidOn) {
        this.paidOn = paidOn;
    }

    public String getOutwardNo() {
        return outwardNo;
    }

    public void setOutwardNo(String outwardNo) {
        this.outwardNo = outwardNo;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getTotalLoadingCharges() {
        return totalLoadingCharges;
    }

    public void setTotalLoadingCharges(int totalLoadingCharges) {
        this.totalLoadingCharges = totalLoadingCharges;
    }

    public Transporter getTransporter() {
        return transporter;
    }

    public void setTransporter(Transporter transporter) {
        this.transporter = transporter;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getTotalOtherCharges() {
        return totalOtherCharges;
    }

    public void setTotalOtherCharges(int totalOtherCharges) {
        this.totalOtherCharges = totalOtherCharges;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }
}
