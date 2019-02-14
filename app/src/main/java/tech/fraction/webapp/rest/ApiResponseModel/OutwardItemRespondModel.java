package tech.fraction.webapp.rest.ApiResponseModel;

import tech.fraction.webapp.model.DataOutwardItem;

public class OutwardItemRespondModel {
    private String Message;

    private DataOutwardItem Data;

    private boolean IsValid;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public DataOutwardItem getData() {
        return Data;
    }

    public void setData(DataOutwardItem data) {
        Data = data;
    }

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }
}
