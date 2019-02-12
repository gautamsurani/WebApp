package tech.fraction.webapp.model;

import java.io.Serializable;

public class InwardItemLocationPoco implements Serializable {
    private int FloorId;

    private int RawId;

    private String RackName;

    private int Id;

    private int ChamberId;

    private int InwardItemDetailId;

    private String InwardItemDetail;

    private int RackId;

    private String Rack;

    public int getFloorId() {
        return FloorId;
    }

    public void setFloorId(int floorId) {
        FloorId = floorId;
    }

    public int getRawId() {
        return RawId;
    }

    public void setRawId(int rawId) {
        RawId = rawId;
    }

    public String getRackName() {
        return RackName;
    }

    public void setRackName(String rackName) {
        RackName = rackName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getChamberId() {
        return ChamberId;
    }

    public void setChamberId(int chamberId) {
        ChamberId = chamberId;
    }

    public int getInwardItemDetailId() {
        return InwardItemDetailId;
    }

    public void setInwardItemDetailId(int inwardItemDetailId) {
        InwardItemDetailId = inwardItemDetailId;
    }

    public String getInwardItemDetail() {
        return InwardItemDetail;
    }

    public void setInwardItemDetail(String inwardItemDetail) {
        InwardItemDetail = inwardItemDetail;
    }

    public int getRackId() {
        return RackId;
    }

    public void setRackId(int rackId) {
        RackId = rackId;
    }

    public String getRack() {
        return Rack;
    }

    public void setRack(String rack) {
        Rack = rack;
    }
}