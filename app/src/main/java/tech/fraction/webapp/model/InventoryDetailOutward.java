package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class InventoryDetailOutward implements Serializable {


    private int OutwardId;
    private String OutwardDateinDDMMYYYY;

    private int TotalRecords;

    private String AccountName;


    private String OutwardOn;

    private ArrayList<OutwardItems> OutwardItems;


    private String InvoiceGeneratedOn;

    private String PaidStatus;

    private boolean IsModified;


    private boolean CanDeleteOutWarDetail;

    private String Address;

    private String ReceiptType;

    private int UniqRecordId;

    private String PaidOn;

    private String OutwardNo;

    private int AccountId;


    private int TotalLoadingCharges;

    private Transporter VehicleDetailModel;


    private int InvoiceId;

    private int TotalOtherCharges;

    private int PaidAmount;

    public String getOutwardDateinDDMMYYYY() {
        return OutwardDateinDDMMYYYY;
    }

    public void setOutwardDateinDDMMYYYY(String outwardDateinDDMMYYYY) {
        OutwardDateinDDMMYYYY = outwardDateinDDMMYYYY;
    }

    public int getOutwardId() {
        return OutwardId;
    }

    public void setOutwardId(int outwardId) {
        OutwardId = outwardId;
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


    public String getOutwardOn() {
        return OutwardOn;
    }

    public void setOutwardOn(String outwardOn) {
        OutwardOn = outwardOn;
    }

    public ArrayList<OutwardItems> getOutwardItems() {
        return OutwardItems;
    }

    public void setOutwardItems(ArrayList<OutwardItems> outwardItems) {
        OutwardItems = outwardItems;
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


    public int getTotalLoadingCharges() {
        return TotalLoadingCharges;
    }

    public void setTotalLoadingCharges(int totalLoadingCharges) {
        TotalLoadingCharges = totalLoadingCharges;
    }

    public Transporter getVehicleDetailModel() {
        return VehicleDetailModel;
    }

    public void setVehicleDetailModel(Transporter vehicleDetailModel) {
        VehicleDetailModel = vehicleDetailModel;
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
