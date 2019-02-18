package tech.fraction.webapp.rest.ApiResponseModel;

import tech.fraction.webapp.model.DataDetailInward;

public class DetailInwardResponseModel {

    private String Message;

    private DataDetailInward Data;

    private String IsValid;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public DataDetailInward getData() {
        return Data;
    }

    public void setData(DataDetailInward data) {
        Data = data;
    }

    public String getIsValid() {
        return IsValid;
    }

    public void setIsValid(String isValid) {
        IsValid = isValid;
    }
}
