package tech.fraction.webapp.rest.ApiResponseModel;

import java.util.ArrayList;

import tech.fraction.webapp.SqliteDatabase.model.Units;

public class UnitResponseModel {

    private ArrayList<Units> unit;

    public ArrayList<Units> getLstUnit() {
        return unit;
    }

    public void setLstUnit(ArrayList<Units> lstUnit) {
        this.unit = lstUnit;
    }
}
