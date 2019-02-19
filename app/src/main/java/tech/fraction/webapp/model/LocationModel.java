package tech.fraction.webapp.model;

import java.io.Serializable;

public class LocationModel implements Serializable {

    String id;
    String name;

    public LocationModel(String id,String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
