package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;

public class DataOutwardItem implements Serializable {

    private List<OutwardDetails> Outwards;

    public List<OutwardDetails> getOutwardDetails() {
        return Outwards;
    }

    public void setOutwardDetails(List<OutwardDetails> outwardDetails) {
        Outwards = outwardDetails;
    }
}
