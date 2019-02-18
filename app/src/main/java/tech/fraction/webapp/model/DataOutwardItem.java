package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;

public class DataOutwardItem implements Serializable {

    private List<OutwardDetails> OutwardDetails;

    public List<OutwardDetails> getOutwardDetails() {
        return OutwardDetails;
    }

    public void setOutwardDetails(List<OutwardDetails> outwardDetails) {
        OutwardDetails = outwardDetails;
    }
}
