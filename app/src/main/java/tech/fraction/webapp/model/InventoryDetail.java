package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;

public class InventoryDetail implements Serializable {

    private String PaidStatus;

    private int UniqRecordId;

    private String InwardNo;

    private String CanDeleteInward;

    private String Address;

    private String Color;

    private Transporter VehicleDetailModel;

    private String PaidAmount;

    private String InwardDateinDDMMYYYY;

    private String ReceiptType;

    private List<InwardItems> InwardItems;

    private int TotalRecords;

    private int InwardDetailId;

    private String InvoiceGenerationDuePeriod;

    private String InvoiceMessage;


    private String AccountName;


    private boolean IsInvoicePaid;


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

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
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

    public int getUniqRecordId() {
        return UniqRecordId;
    }

    public void setUniqRecordId(int uniqRecordId) {
        UniqRecordId = uniqRecordId;
    }

    public String getInwardDateinDDMMYYYY() {
        return InwardDateinDDMMYYYY;
    }

    public void setInwardDateinDDMMYYYY(String inwardDateinDDMMYYYY) {
        InwardDateinDDMMYYYY = inwardDateinDDMMYYYY;
    }

    public String getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(String paidAmount) {
        PaidAmount = paidAmount;
    }

    public List<tech.fraction.webapp.model.InwardItems> getInwardItems() {
        return InwardItems;
    }

    public void setInwardItems(List<tech.fraction.webapp.model.InwardItems> inwardItems) {
        InwardItems = inwardItems;
    }

    public String getInvoiceMessage() {
        return InvoiceMessage;
    }

    public void setInvoiceMessage(String invoiceMessage) {
        InvoiceMessage = invoiceMessage;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public Transporter getVehicleDetailModel() {
        return VehicleDetailModel;
    }

    public void setVehicleDetailModel(Transporter vehicleDetailModel) {
        VehicleDetailModel = vehicleDetailModel;
    }
}
