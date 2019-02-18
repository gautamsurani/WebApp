package tech.fraction.webapp.rest.ApiResponseModel;

import java.io.Serializable;
import java.util.ArrayList;

import tech.fraction.webapp.SqliteDatabase.model.Racks;

public class RackResponseModel implements Serializable {

    ArrayList<Racks> rack;

    public ArrayList<Racks> getRack() {
        return rack;
    }

    public void setRack(ArrayList<Racks> rack) {
        this.rack = rack;
    }
}
