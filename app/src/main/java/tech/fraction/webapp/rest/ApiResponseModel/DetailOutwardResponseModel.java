package tech.fraction.webapp.rest.ApiResponseModel;

import tech.fraction.webapp.model.DataDetailOutward;

public class DetailOutwardResponseModel {
    private String Message;

    private DataDetailOutward Data;

    private boolean IsValid;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public DataDetailOutward getData() {
        return Data;
    }

    public void setData(DataDetailOutward data) {
        Data = data;
    }

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }
}