package tech.fraction.webapp.rest.ApiResponseModel;

import java.io.Serializable;
import java.util.ArrayList;

import tech.fraction.webapp.SqliteDatabase.model.City;
import tech.fraction.webapp.SqliteDatabase.model.State;

public class CityResponseModel implements Serializable {
    private ArrayList<City> city;



    public ArrayList<City> getCity() {
        return city;
    }

    public void setCity(ArrayList<City> city) {
        this.city = city;
    }
}
