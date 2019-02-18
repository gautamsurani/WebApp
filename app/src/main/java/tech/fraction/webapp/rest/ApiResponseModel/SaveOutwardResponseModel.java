package tech.fraction.webapp.rest.ApiResponseModel;

import java.io.Serializable;

import tech.fraction.webapp.model.DataSaveInward;
import tech.fraction.webapp.model.DataSaveOutward;

public class SaveOutwardResponseModel implements Serializable {
    private String Message;

    private DataSaveOutward Data;

    private boolean IsValid;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public DataSaveOutward getData() {
        return Data;
    }

    public void setData(DataSaveOutward data) {
        Data = data;
    }

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }
}
