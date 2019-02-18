package tech.fraction.webapp.rest.ApiResponseModel;

import java.io.Serializable;
import java.util.ArrayList;

import tech.fraction.webapp.SqliteDatabase.model.Country;

public class CountryResponseModel implements Serializable {
    private ArrayList<Country> country;

    public ArrayList<Country> getCountry() {
        return country;
    }

    public void setCountry(ArrayList<Country> country) {
        this.country = country;
    }
}
