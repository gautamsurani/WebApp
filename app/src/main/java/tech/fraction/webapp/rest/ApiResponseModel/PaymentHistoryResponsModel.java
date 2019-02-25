package tech.fraction.webapp.rest.ApiResponseModel;

import tech.fraction.webapp.model.DataPaymentHistory;

public class PaymentHistoryResponsModel {
    private String Message;

    private DataPaymentHistory Data;

    private boolean IsValid;

    public String getMessage() {
        return Message;

    }

    public void setMessage(String message) {
        Message = message;
    }

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
}
