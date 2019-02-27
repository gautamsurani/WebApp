package tech.fraction.webapp.SqliteDatabase.model;

import java.io.Serializable;

public class Racks implements Serializable {

    private int id;

    private int floorId;

    private int chamberId;

    private String name;

    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public int getChamberId() {
        return chamberId;
    }

    public void setChamberId(int chamberId) {
        this.chamberId = chamberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
