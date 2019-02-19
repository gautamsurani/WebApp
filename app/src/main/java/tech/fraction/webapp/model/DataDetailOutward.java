package tech.fraction.webapp.model;

import tech.fraction.webapp.rest.ApiRequestModel.SaveOutwardRequestModel;

public class DataDetailOutward {

    private SaveOutwardRequestModel InwardDetails;

    public SaveOutwardRequestModel getInwardDetails() {
        return InwardDetails;
    }

    public void setInwardDetails(SaveOutwardRequestModel inwardDetails) {
        InwardDetails = inwardDetails;
    }
}
