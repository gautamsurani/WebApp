package tech.fraction.webapp.model;

import java.io.Serializable;

public class PersonInformation implements Serializable {
    private int AccountId;

    private String FirstName;

    private String LastName;

    private String EmailAddress;

    private int PersonId;

    private String PersonType;


    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        PersonId = personId;
    }

    public String getPersonType() {
        return PersonType;
    }

    public void setPersonType(String personType) {
        PersonType = personType;
    }
}