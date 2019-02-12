package tech.fraction.webapp.rest.ApiResponseModel;

import java.util.ArrayList;

import tech.fraction.webapp.SqliteDatabase.model.Country;
import tech.fraction.webapp.SqliteDatabase.model.State;

public class StateResponseModel {
    private ArrayList<State> state;

    public ArrayList<State> getState() {
        return state;
    }

    public void setState(ArrayList<State> state) {
        this.state = state;
    }
}
