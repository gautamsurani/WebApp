package tech.fraction.webapp.model;

import tech.fraction.webapp.rest.ApiRequestModel.SaveInwardRequestModel;

public class DataDetailInward {

private SaveInwardRequestModel InwardDetails;

    public SaveInwardRequestModel getInwardDetails() {
        return InwardDetails;
    }

    public void setInwardDetails(SaveInwardRequestModel inwardDetails) {
        InwardDetails = inwardDetails;
    }
}
