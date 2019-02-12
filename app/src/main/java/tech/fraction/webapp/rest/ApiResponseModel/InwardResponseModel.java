package tech.fraction.webapp.rest.ApiResponseModel;


import java.util.List;

import tech.fraction.webapp.model.InventoryDetail;
import tech.fraction.webapp.model.InwardData;
import tech.fraction.webapp.model.Paging;

public class InwardResponseModel {


    private String Message;

    private InwardData Data;

    private String IsValid;


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

    public String getIsValid() {
        return IsValid;
    }

    public void setIsValid(String isValid) {
        IsValid = isValid;
    }
}

