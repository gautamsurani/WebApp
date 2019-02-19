package tech.fraction.webapp.rest.ApiResponseModel;


import java.io.Serializable;
import java.util.List;

import tech.fraction.webapp.model.InventoryDetail;
import tech.fraction.webapp.model.InwardData;
import tech.fraction.webapp.model.Paging;

public class InwardResponseModel implements Serializable {


    private String Message;

    private InwardData Data;

    private boolean IsValid;


    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public InwardData getData() {
        return Data;
    }

    public void setData(InwardData data) {
        Data = data;
    }

    public boolean getIsValid() {
        return IsValid;
    }

    public void setIsValid(boolean isValid) {
        IsValid = isValid;
    }
}

