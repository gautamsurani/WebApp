package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;


public class InwardItems implements Serializable {
    private int OutwardId;

    private int InwardDetailId;

    private int OtherCharges;

    private int TotalOutwardQuantity;

    private int LoadingCharges;

    private String ItemName;

    private boolean IsModified;

    private int RawId;

    private String InwardedOn;

    private int UnitId;

    private int Stock;

    private int OutwardDetailId;

    private int UnloadingCharges;

    private int Quantity;

    private String UnitName;

    private String InwardDetail;

    private String MarkoName;

    private String InwardNo;

    private int Weight;

    private String Label;

    private int AccountId;

    private int ItemId;

    private Double RentPerUnit;

    private String OutwardDetails;

    private int OutwardQuantity;

    private List<InwardItemLocationPoco> InwardItemLocationPoco;
     private String Location;

    private int InwardItemDetailId;

    private int OutwardWeight;

    public List<InwardItemLocationPoco> getInwardItemLocationPoco() {
        return InwardItemLocationPoco;
    }

    public void setInwardItemLocationPoco(List<InwardItemLocationPoco> inwardItemLocationPoco) {
        InwardItemLocationPoco = inwardItemLocationPoco;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getInwardItemDetailId() {
        return InwardItemDetailId;
    }

    public void setInwardItemDetailId(int inwardItemDetailId) {
        InwardItemDetailId = inwardItemDetailId;
    }

    public int getOutwardWeight() {
        return OutwardWeight;
    }

    public void setOutwardWeight(int outwardWeight) {
        OutwardWeight = outwardWeight;
    }

    public int getOutwardId() {
        return OutwardId;
    }

    public void setOutwardId(int outwardId) {
        OutwardId = outwardId;
    }

    public int getInwardDetailId() {
        return InwardDetailId;
    }

    public void setInwardDetailId(int inwardDetailId) {
        InwardDetailId = inwardDetailId;
    }

    public int getOtherCharges() {
        return OtherCharges;
    }

    public void setOtherCharges(int otherCharges) {
        OtherCharges = otherCharges;
    }

    public int getTotalOutwardQuantity() {
        return TotalOutwardQuantity;
    }

    public void setTotalOutwardQuantity(int totalOutwardQuantity) {
        TotalOutwardQuantity = totalOutwardQuantity;
    }

    public int getLoadingCharges() {
        return LoadingCharges;
    }

    public void setLoadingCharges(int loadingCharges) {
        LoadingCharges = loadingCharges;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public boolean isModified() {
        return IsModified;
    }

    public void setModified(boolean modified) {
        IsModified = modified;
    }

    public int getRawId() {
        return RawId;
    }

    public void setRawId(int rawId) {
        RawId = rawId;
    }

    public String getInwardedOn() {
        return InwardedOn;
    }

    public void setInwardedOn(String inwardedOn) {
        InwardedOn = inwardedOn;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getOutwardDetailId() {
        return OutwardDetailId;
    }

    public void setOutwardDetailId(int outwardDetailId) {
        OutwardDetailId = outwardDetailId;
    }

    public int getUnloadingCharges() {
        return UnloadingCharges;
    }

    public void setUnloadingCharges(int unloadingCharges) {
        UnloadingCharges = unloadingCharges;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getUnitName() {
        return UnitName;
    }

    public void setUnitName(String unitName) {
        UnitName = unitName;
    }

    public String getInwardDetail() {
        return InwardDetail;
    }

    public void setInwardDetail(String inwardDetail) {
        InwardDetail = inwardDetail;
    }

    public String getMarkoName() {
        return MarkoName;
    }

    public void setMarkoName(String markoName) {
        MarkoName = markoName;
    }

    public String getInwardNo() {
        return InwardNo;
    }

    public void setInwardNo(String inwardNo) {
        InwardNo = inwardNo;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public Double getRentPerUnit() {
        return RentPerUnit;
    }

    public void setRentPerUnit(Double rentPerUnit) {
        RentPerUnit = rentPerUnit;
    }

    public String getOutwardDetails() {
        return OutwardDetails;
    }

    public void setOutwardDetails(String outwardDetails) {
        OutwardDetails = outwardDetails;
    }

    public int getOutwardQuantity() {
        return OutwardQuantity;
    }

    public void setOutwardQuantity(int outwardQuantity) {
        OutwardQuantity = outwardQuantity;
    }
}