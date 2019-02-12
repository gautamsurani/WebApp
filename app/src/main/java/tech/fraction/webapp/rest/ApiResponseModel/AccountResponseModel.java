package tech.fraction.webapp.rest.ApiResponseModel;

import java.util.ArrayList;

import tech.fraction.webapp.model.Account;

public class AccountResponseModel {

    private ArrayList<Account> account;

    public ArrayList<Account> getAccount() {
        return account;
    }

    public void setAccount(ArrayList<Account> account) {
        this.account = account;
    }
}
