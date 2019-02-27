package tech.fraction.webapp.rest.ApiRequestModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tech.fraction.webapp.model.Invoices;
import tech.fraction.webapp.model.InwardItems;
import tech.fraction.webapp.model.InwardVehicleDetail;

public class SaveInwardRequestModel implements Serializable {

    private String Number;
    private int AccountId;
    private InwardVehicleDetail VehicleDetailModel;
    private String inwardDateinDDMMYYYY;
    private int AddedBy;
    private List<InwardItems> InwardItemDetailModel;
    private int Id;



    public SaveInwardRequestModel(String number, int accountId, InwardVehicleDetail vehicleDetailModel, String inwardDateinDDMMYYYY,
                                  int addedBy, List<InwardItems> inwardItemDetailModel, int id) {
        Number = number;
        AccountId = accountId;
        VehicleDetailModel = vehicleDetailModel;
        this.inwardDateinDDMMYYYY = inwardDateinDDMMYYYY;
        AddedBy = addedBy;
        InwardItemDetailModel = inwardItemDetailModel;
        Id = id;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public InwardVehicleDetail getVehicleDetailModel() {
        return VehicleDetailModel;
    }

    public void setVehicleDetailModel(InwardVehicleDetail vehicleDetailModel) {
        VehicleDetailModel = vehicleDetailModel;
    }

    public String getInwardDateinDDMMYYYY() {
        return inwardDateinDDMMYYYY;
    }

    public void setInwardDateinDDMMYYYY(String inwardDateinDDMMYYYY) {
        inwardDateinDDMMYYYY = inwardDateinDDMMYYYY;
    }

    public int getAddedBy() {
        return AddedBy;
    }

    public void setAddedBy(int addedBy) {
        AddedBy = addedBy;
    }

    public List<InwardItems> getInwardItemDetailModel() {
        return InwardItemDetailModel;
    }

    public void setInwardItemDetailModel(List<InwardItems> inwardItemDetailModel) {
        InwardItemDetailModel = inwardItemDetailModel;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
