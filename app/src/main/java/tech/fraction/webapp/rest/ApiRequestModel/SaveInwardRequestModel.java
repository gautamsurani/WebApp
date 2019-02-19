package tech.fraction.webapp.rest.ApiRequestModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.model.Invoices;
import tech.fraction.webapp.model.InwardItems;
import tech.fraction.webapp.model.InwardVehicleDetail;

public class SaveInwardRequestModel implements Serializable {
    private String PaidStatus;

    private boolean CanGenerateInvoice;

    private List<InwardItems> InwardItemDetailPoco;

    private int AccountId;

    private String Broker;

    private String InwardedOn;

    private String LastInvoiceToDate;

    private InwardVehicleDetail InwardVehicleDetail;

    private ArrayList<Invoices> Invoices;

    private boolean IsModified;

    private String LastInvoiceFromDate;

    private String LastPaidAmount;

    private String Number;

    private String LastInvoiceGeneratedOn;

    private String TotalPaidAmount;

    private int Id;

    private String LastInvoicePaidOn;

    private int AddedBy;

    public SaveInwardRequestModel() {
    }

    public SaveInwardRequestModel(String paidStatus, boolean canGenerateInvoice, List<InwardItems> inwardItemDetailPoco, int accountId, String broker,
                                  String inwardedOn, String lastInvoiceToDate, tech.fraction.webapp.model.InwardVehicleDetail inwardVehicleDetail,
                                  ArrayList<Invoices> invoices, boolean isModified, String lastInvoiceFromDate, String lastPaidAmount, String number,
                                  String lastInvoiceGeneratedOn, String totalPaidAmount, int id, String lastInvoicePaidOn, int addedBy) {
        PaidStatus = paidStatus;
        CanGenerateInvoice = canGenerateInvoice;
        InwardItemDetailPoco = inwardItemDetailPoco;
        AccountId = accountId;
        Broker = broker;
        InwardedOn = inwardedOn;
        LastInvoiceToDate = lastInvoiceToDate;
        InwardVehicleDetail = inwardVehicleDetail;
        Invoices = invoices;
        IsModified = isModified;
        LastInvoiceFromDate = lastInvoiceFromDate;
        LastPaidAmount = lastPaidAmount;
        Number = number;
        LastInvoiceGeneratedOn = lastInvoiceGeneratedOn;
        TotalPaidAmount = totalPaidAmount;
        Id = id;
        LastInvoicePaidOn = lastInvoicePaidOn;
        AddedBy = addedBy;
    }

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

    public List<InwardItems> getInwardItemDetailPoco() {
        return InwardItemDetailPoco;
    }

    public void setInwardItemDetailPoco(List<InwardItems> inwardItemDetailPoco) {
        InwardItemDetailPoco = inwardItemDetailPoco;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public String getBroker() {
        return Broker;
    }

    public void setBroker(String broker) {
        Broker = broker;
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

    public ArrayList<Invoices> getInvoices() {
        return Invoices;
    }

    public void setInvoices(ArrayList<Invoices> invoices) {
        Invoices = invoices;
    }

    public boolean isModified() {
        return IsModified;
    }

    public void setModified(boolean modified) {
        IsModified = modified;
    }

    public String getLastInvoiceFromDate() {
        return LastInvoiceFromDate;
    }

    public void setLastInvoiceFromDate(String lastInvoiceFromDate) {
        LastInvoiceFromDate = lastInvoiceFromDate;
    }

    public String getLastPaidAmount() {
        return LastPaidAmount;
    }

    public void setLastPaidAmount(String lastPaidAmount) {
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
