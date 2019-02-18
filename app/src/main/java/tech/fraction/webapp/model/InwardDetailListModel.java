package tech.fraction.webapp.model;

import java.io.Serializable;

public class InwardDetailListModel implements Serializable {

    private String name;
    private String location;
    private String unloadingCharges;
    private String value;

    public InwardDetailListModel(String name, String location, String unloadingCharges, String value) {
        this.name = name;
        this.location = location;
        this.unloadingCharges = unloadingCharges;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUnloadingCharges() {
        return unloadingCharges;
    }

    public void setUnloadingCharges(String unloadingCharges) {
        this.unloadingCharges = unloadingCharges;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
