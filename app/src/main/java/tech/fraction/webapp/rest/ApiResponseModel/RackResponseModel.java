package tech.fraction.webapp.rest.ApiResponseModel;

import java.util.ArrayList;

import tech.fraction.webapp.SqliteDatabase.model.Racks;

public class RackResponseModel {

    ArrayList<Racks> rack;

    public ArrayList<Racks> getRack() {
        return rack;
    }

    public void setRack(ArrayList<Racks> rack) {
        this.rack = rack;
    }
}
