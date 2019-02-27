package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;

public class InwardData implements Serializable {

    private List<InventoryDetail> Inwards;

    private Paging Paging;

    public List<InventoryDetail> getInventoryDetail() {
        return Inwards;
    }

    public void setInventoryDetail(List<InventoryDetail> personInformation) {
        Inwards = personInformation;
    }

    public Paging getPaging() {
        return Paging;
    }

    public void setPaging(Paging paging) {
        Paging = paging;
    }
}
