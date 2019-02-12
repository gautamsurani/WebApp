package tech.fraction.webapp.rest.ApiResponseModel;

import java.util.ArrayList;

import tech.fraction.webapp.SqliteDatabase.model.ItemRent;

public class RentResponseModel {
    private ArrayList<ItemRent> itemRent;

    public ArrayList<ItemRent> getItemRent() {
        return itemRent;
    }

    public void setItemRent(ArrayList<ItemRent> itemRent) {
        this.itemRent = itemRent;
    }
}
