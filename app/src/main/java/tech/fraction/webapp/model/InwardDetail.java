package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;

public class InwardDetail implements Serializable {
    private String PaidStatus;

    private String Account;

    private int AccountId;

    private String InwardedOn;

    private String LastInvoiceToDate;

    private String LastInvoiceFromDate;

    private Double LastPaidAmount;

    private String Number;

    private String LastInvoiceGeneratedOn;

    private Double TotalPaidAmount;

    private List<InwardVehicleDetail> InwardVehicleDetails;

    private int Id;

    private String LastInvoicePaidOn;

    private String Person;

    private int AddedBy;

    public String getPaidStatus() {
        return PaidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        PaidStatus = paidStatus;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
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

    public Double getTotalPaidAmount() {
        return TotalPaidAmount;
    }

    public void setTotalPaidAmount(Double totalPaidAmount) {
        TotalPaidAmount = totalPaidAmount;
    }

    public List<InwardVehicleDetail> getInwardVehicleDetails() {
        return InwardVehicleDetails;
    }

    public void setInwardVehicleDetails(List<InwardVehicleDetail> inwardVehicleDetails) {
        InwardVehicleDetails = inwardVehicleDetails;
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

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public int getAddedBy() {
        return AddedBy;
    }

    public void setAddedBy(int addedBy) {
        AddedBy = addedBy;
    }
}