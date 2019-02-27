package tech.fraction.webapp.model;

import java.util.ArrayList;

public class InwardDetailsModel {

    private String PaidStatus;

    private boolean CanGenerateInvoice;

    private ArrayList<InwardItems> InwardItems;

    private int AccountId;

    private String AccountName;

    private String InwardedOn;

    private String LastInvoiceToDate;

    private InwardVehicleDetail InwardVehicleDetail;

    private String InwardDateinDDMMYYYY;

    private String Invoices;

    private String IsModified;

    private String LastInvoiceFromDate;

    private Double LastPaidAmount;

    private String Number;

    private String LastInvoiceGeneratedOn;

    private String TotalPaidAmount;

    private int Id;

    private String LastInvoicePaidOn;

    private int AddedBy;

    public String getPaidStatus() {
        return PaidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        PaidStatus = paidStatus;
    }

    public boolean isCanGenerateInvoice() {
        return CanGenerateInvoice;
    }

    public void setCanGenerateInvoice(boolean canGenerateInvoice) {
        CanGenerateInvoice = canGenerateInvoice;
    }

    public ArrayList<InwardItems> getInwardItemDetailPoco() {
        return InwardItems;
    }

    public void setInwardItemDetailPoco(ArrayList<InwardItems> inwardItemDetailPoco) {
        InwardItems = inwardItemDetailPoco;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getInwardedOn() {
        return InwardedOn;
    }

    public void setInwardedOn(String inwardedOn) {
        InwardedOn = inwardedOn;
    }

    public String getLastInvoiceToDate() {
        return LastInvoiceToDate;
    }

    public void setLastInvoiceToDate(String lastInvoiceToDate) {
        LastInvoiceToDate = lastInvoiceToDate;
    }

    public InwardVehicleDetail getInwardVehicleDetail() {
        return InwardVehicleDetail;
    }

    public void setInwardVehicleDetail(InwardVehicleDetail inwardVehicleDetail) {
        InwardVehicleDetail = inwardVehicleDetail;
    }

    public String getInvoices() {
        return Invoices;
    }

    public void setInvoices(String invoices) {
        Invoices = invoices;
    }

    public String getIsModified() {
        return IsModified;
    }

    public void setIsModified(String isModified) {
        IsModified = isModified;
    }

    public String getLastInvoiceFromDate() {
        return LastInvoiceFromDate;
    }

    public void setLastInvoiceFromDate(String lastInvoiceFromDate) {
        LastInvoiceFromDate = lastInvoiceFromDate;
    }

    public Double getLastPaidAmount() {
        return LastPaidAmount;
    }

    public void setLastPaidAmount(Double lastPaidAmount) {
        LastPaidAmount = lastPaidAmount;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getLastInvoiceGeneratedOn() {
        return LastInvoiceGeneratedOn;
    }

    public void setLastInvoiceGeneratedOn(String lastInvoiceGeneratedOn) {
        LastInvoiceGeneratedOn = lastInvoiceGeneratedOn;
    }

    public String getTotalPaidAmount() {
        return TotalPaidAmount;
    }

    public void setTotalPaidAmount(String totalPaidAmount) {
        TotalPaidAmount = totalPaidAmount;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLastInvoicePaidOn() {
        return LastInvoicePaidOn;
    }

    public String getInwardDateinDDMMYYYY() {
        return InwardDateinDDMMYYYY;
    }

    public void setInwardDateinDDMMYYYY(String inwardDateinDDMMYYYY) {
        InwardDateinDDMMYYYY = inwardDateinDDMMYYYY;
    }

    public void setLastInvoicePaidOn(String lastInvoicePaidOn) {
        LastInvoicePaidOn = lastInvoicePaidOn;
    }

    public int getAddedBy() {
        return AddedBy;
    }

    public void setAddedBy(int addedBy) {
        AddedBy = addedBy;
    }
}
