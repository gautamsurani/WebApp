package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DataInvoice implements Serializable {

    private Paging Paging;

    private ArrayList<PersonInformationInvoice> PersonInformation;

    public tech.fraction.webapp.model.Paging getPaging() {
        return Paging;
    }

    public void setPaging(tech.fraction.webapp.model.Paging paging) {
        Paging = paging;
    }

    public ArrayList<PersonInformationInvoice> getPersonInformation() {
        return PersonInformation;
    }

    public void setPersonInformation(ArrayList<PersonInformationInvoice> personInformation) {
        PersonInformation = personInformation;
    }
}
