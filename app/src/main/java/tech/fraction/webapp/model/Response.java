package tech.fraction.webapp.model;

import java.io.Serializable;

public class Response implements Serializable {
    String message;

    boolean isValid;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
