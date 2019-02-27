package tech.fraction.webapp.model;

import tech.fraction.webapp.rest.ApiRequestModel.SaveInwardRequestModel;

public class DataDetailInward {

    private InwardDetailsModel Inwards;

    public InwardDetailsModel getInwardDetails() {
        return Inwards;
    }

    public void setInwardDetails(InwardDetailsModel inwardDetails) {
        Inwards = inwardDetails;
    }
}
