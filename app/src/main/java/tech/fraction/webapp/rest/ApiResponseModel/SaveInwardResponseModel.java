package tech.fraction.webapp.rest.ApiResponseModel;

import java.io.Serializable;

import tech.fraction.webapp.model.DataSaveInward;

public class SaveInwardResponseModel implements Serializable {
    private String Message;

    private DataSaveInward Data;

    private boolean IsValid;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public DataSaveInward getData() {
        return Data;
    }

    public void setData(DataSaveInward data) {
        Data = data;
    }

    public boolean getIsValid() {
        return IsValid;
    }

    public void setIsValid(boolean isValid) {
        IsValid = isValid;
    }
}
