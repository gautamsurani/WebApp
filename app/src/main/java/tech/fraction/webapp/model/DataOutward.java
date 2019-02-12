package tech.fraction.webapp.model;

import java.util.ArrayList;

public class DataOutward {

    private ArrayList<InventoryDetailOutward> Response;

    private Paging Paging;

    public ArrayList<InventoryDetailOutward> getResponse() {
        return Response;
    }

    public void setResponse(ArrayList<InventoryDetailOutward> response) {
        Response = response;
    }

    public tech.fraction.webapp.model.Paging getPaging() {
        return Paging;
    }

    public void setPaging(tech.fraction.webapp.model.Paging paging) {
        Paging = paging;
    }
}
