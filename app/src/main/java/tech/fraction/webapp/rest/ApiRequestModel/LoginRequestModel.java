package tech.fraction.webapp.rest.ApiRequestModel;

public class LoginRequestModel {
    private String emailId;
    private String password;
    private String returnUrl;

    public LoginRequestModel(String emailId, String password, String returnUrl) {
        this.emailId = emailId;
        this.password = password;
        this.returnUrl = returnUrl;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
