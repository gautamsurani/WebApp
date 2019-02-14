package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DataOutward implements Serializable {

    private ArrayList<InventoryDetailOutward> Outwards;

    private Paging Paging;

    public ArrayList<InventoryDetailOutward> getResponse() {
        return Outwards;
    }

    public void setResponse(ArrayList<InventoryDetailOutward> outwards) {
        Outwards = outwards;
    }

    public tech.fraction.webapp.model.Paging getPaging() {
        return Paging;
    }

    public void setPaging(tech.fraction.webapp.model.Paging paging) {
        Paging = paging;
    }
}
