package tech.fraction.webapp.model;

import java.io.Serializable;

public class InwardVehicleDetail implements Serializable {

    private String InwardDetail;

    private String VehicleNo;

    private String TransporterName;

    private String Remarks;

    private String DriverContactNumber;

    private String DriverName;

    private int Id;

    private int InwardDetailId;

    public InwardVehicleDetail() {
    }

    public InwardVehicleDetail(String inwardDetail, String vehicleNo, String transporterName, String remarks, String driverContactNumber,
                               String driverName, int id, int inwardDetailId) {
        InwardDetail = inwardDetail;
        VehicleNo = vehicleNo;
        TransporterName = transporterName;
        Remarks = remarks;
        DriverContactNumber = driverContactNumber;
        DriverName = driverName;
        Id = id;
        InwardDetailId = inwardDetailId;
    }

    public String getInwardDetail() {
        return InwardDetail;
    }

    public void setInwardDetail(String inwardDetail) {
        InwardDetail = inwardDetail;
    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        VehicleNo = vehicleNo;
    }

    public String getTransporterName() {
        return TransporterName;
    }

    public void setTransporterName(String transporterName) {
        TransporterName = transporterName;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getDriverContactNumber() {
        return DriverContactNumber;
    }

    public void setDriverContactNumber(String driverContactNumber) {
        DriverContactNumber = driverContactNumber;
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

    public int getInwardDetailId() {
        return InwardDetailId;
    }

    public void setInwardDetailId(int inwardDetailId) {
        InwardDetailId = inwardDetailId;
    }
}