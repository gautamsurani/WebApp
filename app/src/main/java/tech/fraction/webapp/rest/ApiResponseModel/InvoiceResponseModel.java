package tech.fraction.webapp.rest.ApiResponseModel;

import tech.fraction.webapp.model.DataInvoice;

public class InvoiceResponseModel {

    private String Message;

    private DataInvoice Data;

    private boolean IsValid;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public DataInvoice getData() {
        return Data;
    }

    public void setData(DataInvoice data) {
        Data = data;
    }

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }
}
