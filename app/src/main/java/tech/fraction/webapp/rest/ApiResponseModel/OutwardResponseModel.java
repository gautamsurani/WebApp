package tech.fraction.webapp.rest.ApiResponseModel;

import java.io.Serializable;
import java.util.ArrayList;


import tech.fraction.webapp.model.DataOutward;
import tech.fraction.webapp.model.InventoryDetailOutward;
import tech.fraction.webapp.model.Paging;

public class OutwardResponseModel implements Serializable {
    private String Message;

    private DataOutward Data;

    private boolean IsValid;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public DataOutward getData() {
        return Data;
    }

    public void setData(DataOutward data) {
        Data = data;
    }

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }

}
