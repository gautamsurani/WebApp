package tech.fraction.webapp.model;

import java.io.Serializable;

public class InwardItemLocationPoco implements Serializable {

    private String RackName;

    private int InwardItemDetailId;
    private int  RawId;
    private int Id;

    /*Field added for inward Detail Api*/


    private int RackId;
    private String Rack;
    private String InwardItemDetail;
    private String FloorId;
    private String ChamberId;

    public int getRawId() {
        return RawId;
    }

    public void setRawId(int rawId) {
        RawId = rawId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getInwardItemDetail() {
        return InwardItemDetail;
    }

    public void setInwardItemDetail(String inwardItemDetail) {
        InwardItemDetail = inwardItemDetail;
    }

    public String getFloorId() {
        return FloorId;
    }

    public void setFloorId(String floorId) {
        FloorId = floorId;
    }

    public String getChamberId() {
        return ChamberId;
    }

    public void setChamberId(String chamberId) {
        ChamberId = chamberId;
    }

    public String getRackName() {
        return RackName;
    }

    public void setRackName(String rackName) {
        RackName = rackName;
    }

    public int getInwardItemDetailId() {
        return InwardItemDetailId;
    }

    public void setInwardItemDetailId(int inwardItemDetailId) {
        InwardItemDetailId = inwardItemDetailId;
    }
}