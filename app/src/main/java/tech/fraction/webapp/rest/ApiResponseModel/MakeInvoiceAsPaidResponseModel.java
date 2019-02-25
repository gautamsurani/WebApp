package tech.fraction.webapp.rest.ApiResponseModel;

import tech.fraction.webapp.model.DataPaymentHistory;

public class MakeInvoiceAsPaidResponseModel {


    private DataPaymentHistory Data;
    private boolean IsValid;
    private String Message;

    public DataPaymentHistory getData() {
        return Data;
    }

    public void setData(DataPaymentHistory data) {
        Data = data;
    }

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
