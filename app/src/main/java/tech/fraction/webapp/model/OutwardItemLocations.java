package tech.fraction.webapp.model;

import java.io.Serializable;

public class OutwardItemLocations implements Serializable {
    private int floorId;

    private String rack;

    private int rawId;

    private String rackName;

    private int id;

    private int chamberId;

    private int inwardItemDetailId;

    private String inwardItemDetail;

    private int rackId;

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    public int getRawId() {
        return rawId;
    }

    public void setRawId(int rawId) {
        this.rawId = rawId;
    }

    public String getRackName() {
        return rackName;
    }

    public void setRackName(String rackName) {
        this.rackName = rackName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChamberId() {
        return chamberId;
    }

    public void setChamberId(int chamberId) {
        this.chamberId = chamberId;
    }

    public int getInwardItemDetailId() {
        return inwardItemDetailId;
    }

    public void setInwardItemDetailId(int inwardItemDetailId) {
        this.inwardItemDetailId = inwardItemDetailId;
    }

    public String getInwardItemDetail() {
        return inwardItemDetail;
    }

    public void setInwardItemDetail(String inwardItemDetail) {
        this.inwardItemDetail = inwardItemDetail;
    }

    public int getRackId() {
        return rackId;
    }

    public void setRackId(int rackId) {
        this.rackId = rackId;
    }
}
