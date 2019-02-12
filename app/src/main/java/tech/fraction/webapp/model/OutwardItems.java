package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class OutwardItems implements Serializable {
    private int outwardId;

    private int inwardDetailId;

    private int totalRecords;

    private int quantity;

    private String unitName;

    private int otherCharges;

    private String inwardNo;

    private int weight;
    private String markoName;

    private String label;

    private ArrayList<OutwardItemLocations> outwardItemLocations;

    private String outwardOn;

    private String outwardNo;

    private int loadingCharges;

    private int accountId;

    private int itemId;
    int rawId;

    private String itemName;

    private Double rentPerUnit;

    private int outwardQuantity;

    private int unitId;

    private int outwardDetailId;

    private int inwardItemDetailId;

    private int stock;

    public String getMarkoName() {
        return markoName;
    }

    public void setMarkoName(String markoName) {
        this.markoName = markoName;
    }

    public int getRawId() {
        return rawId;
    }

    public void setRawId(int rawId) {
        this.rawId = rawId;
    }

    public int getOutwardId() {
        return outwardId;
    }

    public void setOutwardId(int outwardId) {
        this.outwardId = outwardId;
    }

    public int getInwardDetailId() {
        return inwardDetailId;
    }

    public void setInwardDetailId(int inwardDetailId) {
        this.inwardDetailId = inwardDetailId;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(int otherCharges) {
        this.otherCharges = otherCharges;
    }

    public String getInwardNo() {
        return inwardNo;
    }

    public void setInwardNo(String inwardNo) {
        this.inwardNo = inwardNo;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<OutwardItemLocations> getOutwardItemLocations() {
        return outwardItemLocations;
    }

    public void setOutwardItemLocations(ArrayList<OutwardItemLocations> outwardItemLocations) {
        this.outwardItemLocations = outwardItemLocations;
    }

    public String getOutwardOn() {
        return outwardOn;
    }

    public void setOutwardOn(String outwardOn) {
        this.outwardOn = outwardOn;
    }

    public String getOutwardNo() {
        return outwardNo;
    }

    public void setOutwardNo(String outwardNo) {
        this.outwardNo = outwardNo;
    }

    public int getLoadingCharges() {
        return loadingCharges;
    }

    public void setLoadingCharges(int loadingCharges) {
        this.loadingCharges = loadingCharges;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getRentPerUnit() {
        return rentPerUnit;
    }

    public void setRentPerUnit(Double rentPerUnit) {
        this.rentPerUnit = rentPerUnit;
    }

    public int getOutwardQuantity() {
        return outwardQuantity;
    }

    public void setOutwardQuantity(int outwardQuantity) {
        this.outwardQuantity = outwardQuantity;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getOutwardDetailId() {
        return outwardDetailId;
    }

    public void setOutwardDetailId(int outwardDetailId) {
        this.outwardDetailId = outwardDetailId;
    }

    public int getInwardItemDetailId() {
        return inwardItemDetailId;
    }

    public void setInwardItemDetailId(int inwardItemDetailId) {
        this.inwardItemDetailId = inwardItemDetailId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
