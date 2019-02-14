package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OutwardDetails implements Serializable {
    private ArrayList<InwardItemLocationPoco> InwardItemLocationPoco;

    private int AccountId;

    private String Label;

    private Double RentPerUnit;

    private int ItemId;

    private String OutwardDetails;

    private int OutwardId;

    private int UnitId;

    private String ItemName;

    private int InwardItemDetailId;

    private String InwardNo;

    private String InwardedOn;

    private int Quantity;

    private int OutwardDetailId;

    private String UnitName;

    private int TotalOutwardQuantity;

    private boolean IsModified;

    private int Weight;

    private Double LoadingCharges;

    private InwardDetail InwardDetail;

    private int RawId;

    private int OutwardQuantity;

    private Double OtherCharges;

    private int InwardDetailId;

    private Double UnloadingCharges;

    private int Stock;

    private int OutwardWeight;

    private String Location;

    private boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public ArrayList<InwardItemLocationPoco> getInwardItemLocationPoco() {
        return InwardItemLocationPoco;
    }

    public void setInwardItemLocationPoco(ArrayList<InwardItemLocationPoco> inwardItemLocationPoco) {
        InwardItemLocationPoco = inwardItemLocationPoco;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public Double getRentPerUnit() {
        return RentPerUnit;
    }

    public void setRentPerUnit(Double rentPerUnit) {
        RentPerUnit = rentPerUnit;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public String getOutwardDetails() {
        return OutwardDetails;
    }

    public void setOutwardDetails(String outwardDetails) {
        OutwardDetails = outwardDetails;
    }

    public int getOutwardId() {
        return OutwardId;
    }

    public void setOutwardId(int outwardId) {
        OutwardId = outwardId;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getInwardItemDetailId() {
        return InwardItemDetailId;
    }

    public void setInwardItemDetailId(int inwardItemDetailId) {
        InwardItemDetailId = inwardItemDetailId;
    }

    public String getInwardNo() {
        return InwardNo;
    }

    public void setInwardNo(String inwardNo) {
        InwardNo = inwardNo;
    }

    public String getInwardedOn() {
        return InwardedOn;
    }

    public void setInwardedOn(String inwardedOn) {
        InwardedOn = inwardedOn;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getOutwardDetailId() {
        return OutwardDetailId;
    }

    public void setOutwardDetailId(int outwardDetailId) {
        OutwardDetailId = outwardDetailId;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public int getTotalOutwardQuantity() {
        return TotalOutwardQuantity;
    }

    public void setTotalOutwardQuantity(int totalOutwardQuantity) {
        TotalOutwardQuantity = totalOutwardQuantity;
    }

    public boolean isModified() {
        return IsModified;
    }

    public void setModified(boolean modified) {
        IsModified = modified;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }


    public tech.fraction.webapp.model.InwardDetail getInwardDetail() {
        return InwardDetail;
    }

    public void setInwardDetail(tech.fraction.webapp.model.InwardDetail inwardDetail) {
        InwardDetail = inwardDetail;
    }

    public int getRawId() {
        return RawId;
    }

    public void setRawId(int rawId) {
        RawId = rawId;
    }

    public int getOutwardQuantity() {
        return OutwardQuantity;
    }

    public void setOutwardQuantity(int outwardQuantity) {
        OutwardQuantity = outwardQuantity;
    }


    public int getInwardDetailId() {
        return InwardDetailId;
    }

    public void setInwardDetailId(int inwardDetailId) {
        InwardDetailId = inwardDetailId;
    }


    public int getStock() {
        return Stock;
    }

    public Double getLoadingCharges() {
        return LoadingCharges;
    }

    public void setLoadingCharges(Double loadingCharges) {
        LoadingCharges = loadingCharges;
    }

    public Double getOtherCharges() {
        return OtherCharges;
    }

    public void setOtherCharges(Double otherCharges) {
        OtherCharges = otherCharges;
    }

    public Double getUnloadingCharges() {
        return UnloadingCharges;
    }

    public void setUnloadingCharges(Double unloadingCharges) {
        UnloadingCharges = unloadingCharges;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getOutwardWeight() {
        return OutwardWeight;
    }

    public void setOutwardWeight(int outwardWeight) {
        OutwardWeight = outwardWeight;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}