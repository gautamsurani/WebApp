package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;


public class InwardItems implements Serializable {
    private int InwardItemDetailId;
    private List<InwardItemLocationPoco> InwardLocationModel;
    private String RackName;
    private String Label;
    private String ItemName;
    private int Quantity;
    private String UnitName;
    private int InwardDetailId;
    private int Stock;
    private int RawId;
    private int UnitId;
    private int Id;
    private int UnloadingCharges;
    private int ItemId;
    private Double RentPerUnit;

    /*fields added for inward Detail Api*/
    private String OutwardDetails;
    private int OutwardId;
    private int AccountId;
    private String InwardNo;
    private String InwardedOn;
    private int OutwardDetailId;
    private int TotalOutwardQuantity;
    private boolean IsModified;
    private int Weight;
    private int LoadingCharges;
    private String InwardDetail;
    private String OutwardQuantity;
    private String OtherCharges;
    private String OutwardWeight;
    private String Location;

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

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
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

    public int getOutwardDetailId() {
        return OutwardDetailId;
    }

    public void setOutwardDetailId(int outwardDetailId) {
        OutwardDetailId = outwardDetailId;
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

    public int getLoadingCharges() {
        return LoadingCharges;
    }

    public void setLoadingCharges(int loadingCharges) {
        LoadingCharges = loadingCharges;
    }

    public String getInwardDetail() {
        return InwardDetail;
    }

    public void setInwardDetail(String inwardDetail) {
        InwardDetail = inwardDetail;
    }

    public String getOutwardQuantity() {
        return OutwardQuantity;
    }

    public void setOutwardQuantity(String outwardQuantity) {
        OutwardQuantity = outwardQuantity;
    }

    public String getOtherCharges() {
        return OtherCharges;
    }

    public void setOtherCharges(String otherCharges) {
        OtherCharges = otherCharges;
    }

    public String getOutwardWeight() {
        return OutwardWeight;
    }

    public void setOutwardWeight(String outwardWeight) {
        OutwardWeight = outwardWeight;
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

    public List<InwardItemLocationPoco> getInwardLocationModel() {
        return InwardLocationModel;
    }

    public void setInwardLocationModel(List<InwardItemLocationPoco> inwardLocationModel) {
        InwardLocationModel = inwardLocationModel;
    }

    public String getRackName() {
        return RackName;
    }

    public void setRackName(String rackName) {
        RackName = rackName;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
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

    public int getInwardDetailId() {
        return InwardDetailId;
    }

    public void setInwardDetailId(int inwardDetailId) {
        InwardDetailId = inwardDetailId;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public int getRawId() {
        return RawId;
    }

    public void setRawId(int rawId) {
        RawId = rawId;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUnloadingCharges() {
        return UnloadingCharges;
    }

    public void setUnloadingCharges(int unloadingCharges) {
        UnloadingCharges = unloadingCharges;
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
}