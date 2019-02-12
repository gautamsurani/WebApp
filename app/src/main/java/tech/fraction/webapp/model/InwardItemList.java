package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;

import tech.fraction.webapp.SqliteDatabase.model.Racks;

public class InwardItemList implements Serializable {

    int itemId;
    String itemName;
    int unitId;
    String unitName;
    double rent;
    int inwardQuantity;
    String markoName;
    int unloadingCharge;
    List<Racks> lstRacks;

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

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public int getInwardQuantity() {
        return inwardQuantity;
    }

    public void setInwardQuantity(int inwardQuantity) {
        this.inwardQuantity = inwardQuantity;
    }


    public String getMarkoName() {
        return markoName;
    }

    public void setMarkoName(String markoName) {
        this.markoName = markoName;
    }

    public int getUnloadingCharge() {
        return unloadingCharge;
    }

    public void setUnloadingCharge(int unloadingCharge) {
        this.unloadingCharge = unloadingCharge;
    }

    public List<Racks> getLstRacks() {
        return lstRacks;
    }

    public void setLstRacks(List<Racks> lstRacks) {
        this.lstRacks = lstRacks;
    }

    public InwardItemList(int itemId, String itemName, int unitId, String unitName, double rent, int inwardQuantity,String markoName, int unloadingCharge, List<Racks> lstRacks) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.unitId = unitId;
        this.unitName = unitName;
        this.rent = rent;
        this.inwardQuantity = inwardQuantity;
        this.markoName = markoName;
        this.unloadingCharge = unloadingCharge;
        this.lstRacks = lstRacks;
    }
}