package tech.fraction.webapp.rest.ApiResponseModel;

import java.io.Serializable;
import java.util.ArrayList;

import tech.fraction.webapp.SqliteDatabase.model.ItemRent;

public class RentResponseModel implements Serializable {

    private ArrayList<ItemRent> itemRent;

    public ArrayList<ItemRent> getItemRent() {
        return itemRent;
    }

    public void setItemRent(ArrayList<ItemRent> itemRent) {
        this.itemRent = itemRent;
    }
}
