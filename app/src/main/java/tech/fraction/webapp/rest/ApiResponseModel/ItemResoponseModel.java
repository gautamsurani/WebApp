package tech.fraction.webapp.rest.ApiResponseModel;



import java.util.ArrayList;

import tech.fraction.webapp.SqliteDatabase.model.Items;

public class ItemResoponseModel {
    private ArrayList<Items> item;

    public ArrayList<Items> getLstItem() {
        return item;
    }

    public void setLstItem(ArrayList<Items> item) {
        this.item = item;
    }
}

