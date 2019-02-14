package tech.fraction.webapp.rest.ApiRequestModel;

import java.util.List;

import tech.fraction.webapp.model.OutwardsInwardItems;
import tech.fraction.webapp.model.Transporter;

public class SaveOutwardRequestModel {

    private List<OutwardsInwardItems> OutwardsInwardItems;

    private int AccountId;

    private String Broker;

    private String CurrentInvoices;

    private boolean IsModified;

    private int Id;

    private String OutwardNo;

    private Double TotalLoadingCharges;

    private Transporter Transporter;

    private String OutwardOn;

    private Double TotalOtherCharges;

    public List<tech.fraction.webapp.model.OutwardsInwardItems> getOutwardsInwardItems() {
        return OutwardsInwardItems;
    }

    public void setOutwardsInwardItems(List<tech.fraction.webapp.model.OutwardsInwardItems> outwardsInwardItems) {
        OutwardsInwardItems = outwardsInwardItems;
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

    public String getCurrentInvoices() {
        return CurrentInvoices;
    }

    public void setCurrentInvoices(String currentInvoices) {
        CurrentInvoices = currentInvoices;
    }

    public boolean isModified() {
        return IsModified;
    }

    public void setModified(boolean modified) {
        IsModified = modified;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOutwardNo() {
        return OutwardNo;
    }

    public void setOutwardNo(String outwardNo) {
        OutwardNo = outwardNo;
    }

    public Double getTotalLoadingCharges() {
        return TotalLoadingCharges;
    }

    public void setTotalLoadingCharges(Double totalLoadingCharges) {
        TotalLoadingCharges = totalLoadingCharges;
    }

    public tech.fraction.webapp.model.Transporter getTransporter() {
        return Transporter;
    }

    public void setTransporter(tech.fraction.webapp.model.Transporter transporter) {
        Transporter = transporter;
    }

    public String getOutwardOn() {
        return OutwardOn;
    }

    public void setOutwardOn(String outwardOn) {
        OutwardOn = outwardOn;
    }

    public Double getTotalOtherCharges() {
        return TotalOtherCharges;
    }

    public void setTotalOtherCharges(Double totalOtherCharges) {
        TotalOtherCharges = totalOtherCharges;
    }
}