package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;

public class InwardData implements Serializable {

    private List<InventoryDetail> PersonInformation;

    private Paging Paging;

    public List<InventoryDetail> getInventoryDetail() {
        return PersonInformation;
    }

    public void setInventoryDetail(List<InventoryDetail> personInformation) {
        PersonInformation = personInformation;
    }

    public Paging getPaging() {
        return Paging;
    }

    public void setPaging(Paging paging) {
        Paging = paging;
    }
}
