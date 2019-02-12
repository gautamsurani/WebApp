package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class OutwardItems implements Serializable {
    private int OutwardId;

    private int InwardDetailId;

    private int TotalRecords;

    private int Quantity;

    private String UnitName;

    private int OtherCharges;

    private String InwardNo;

    private int Weight;
    private String MarkoName;

    private String Label;

    private ArrayList<OutwardItemLocations> OutwardItemLocations;

    private String OutwardOn;

    private String OutwardNo;

    private int LoadingCharges;

    private int AccountId;

    private int ItemId;
    int RawId;

    private String ItemName;

    private Double RentPerUnit;

    private int OutwardQuantity;

    private int UnitId;

    private int OutwardDetailId;

    private int InwardItemDetailId;

    private int Stock;

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

    public int getTotalRecords() {
        return TotalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        TotalRecords = totalRecords;
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

    public int getOtherCharges() {
        return OtherCharges;
    }

    public void setOtherCharges(int otherCharges) {
        OtherCharges = otherCharges;
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

    public String getMarkoName() {
        return MarkoName;
    }

    public void setMarkoName(String markoName) {
        MarkoName = markoName;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public ArrayList<tech.fraction.webapp.model.OutwardItemLocations> getOutwardItemLocations() {
        return OutwardItemLocations;
    }

    public void setOutwardItemLocations(ArrayList<tech.fraction.webapp.model.OutwardItemLocations> outwardItemLocations) {
        OutwardItemLocations = outwardItemLocations;
    }

    public String getOutwardOn() {
        return OutwardOn;
    }

    public void setOutwardOn(String outwardOn) {
        OutwardOn = outwardOn;
    }

    public String getOutwardNo() {
        return OutwardNo;
    }

    public void setOutwardNo(String outwardNo) {
        OutwardNo = outwardNo;
    }

    public int getLoadingCharges() {
        return LoadingCharges;
    }

    public void setLoadingCharges(int loadingCharges) {
        LoadingCharges = loadingCharges;
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

    public int getRawId() {
        return RawId;
    }

    public void setRawId(int rawId) {
        RawId = rawId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public Double getRentPerUnit() {
        return RentPerUnit;
    }

    public void setRentPerUnit(Double rentPerUnit) {
        RentPerUnit = rentPerUnit;
    }

    public int getOutwardQuantity() {
        return OutwardQuantity;
    }

    public void setOutwardQuantity(int outwardQuantity) {
        OutwardQuantity = outwardQuantity;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }

    public int getOutwardDetailId() {
        return OutwardDetailId;
    }

    public void setOutwardDetailId(int outwardDetailId) {
        OutwardDetailId = outwardDetailId;
    }

    public int getInwardItemDetailId() {
        return InwardItemDetailId;
    }

    public void setInwardItemDetailId(int inwardItemDetailId) {
        InwardItemDetailId = inwardItemDetailId;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }
}
