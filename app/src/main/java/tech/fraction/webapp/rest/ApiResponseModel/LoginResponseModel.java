package tech.fraction.webapp.rest.ApiResponseModel;

import java.util.List;

import tech.fraction.webapp.model.Data;
import tech.fraction.webapp.model.Menu;
import tech.fraction.webapp.model.PersonInformation;
import tech.fraction.webapp.model.Response;
import tech.fraction.webapp.model.UserRights;

public class LoginResponseModel {

    String Message;

    boolean IsValid;

    Data Data;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public boolean isValid() {
        return IsValid;
    }

    public void setValid(boolean valid) {
        IsValid = valid;
    }

    public tech.fraction.webapp.model.Data getData() {
        return Data;
    }

    public void setData(tech.fraction.webapp.model.Data data) {
        Data = data;
    }
}
