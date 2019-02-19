package tech.fraction.webapp.model;

import java.io.Serializable;

public class Transporter implements Serializable {
    private String VehicleNo;

    private int InwardDetailId;

    private String DriverContactNumber;

    private String InwardDetail;

    private String TransporterDetail;

    private String DriverName;

    private int Id;

    private int outwardId;
    private  String outward;

    public int getOutwardId() {
        return outwardId;
    }

    public void setOutwardId(int outwardId) {
        this.outwardId = outwardId;
    }

    public String getOutward() {
        return outward;
    }

    public void setOutward(String outward) {
        this.outward = outward;
    }

    private String Remarks;

    public String getVehicleNo() {
        return VehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        VehicleNo = vehicleNo;
    }

    public int getInwardDetailId() {
        return InwardDetailId;
    }

    public void setInwardDetailId(int inwardDetailId) {
        InwardDetailId = inwardDetailId;
    }

    public String getDriverContactNumber() {
        return DriverContactNumber;
    }

    public void setDriverContactNumber(String driverContactNumber) {
        DriverContactNumber = driverContactNumber;
    }

    public String getInwardDetail() {
        return InwardDetail;
    }

    public void setInwardDetail(String inwardDetail) {
        InwardDetail = inwardDetail;
    }

    public String getTransporterDetail() {
        return TransporterDetail;
    }

    public void setTransporterDetail(String transporterDetail) {
        TransporterDetail = transporterDetail;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String driverName) {
        DriverName = driverName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}