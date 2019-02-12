package tech.fraction.webapp.model;

import java.util.List;

public class InwardData {

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
