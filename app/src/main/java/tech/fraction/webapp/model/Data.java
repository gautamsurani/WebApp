package tech.fraction.webapp.model;

import java.util.List;

public class Data {
    private UserRights UserRights;

    private PersonInformation PersonInformation;

    private List<Menu> Menu;

    public tech.fraction.webapp.model.UserRights getUserRights() {
        return UserRights;
    }

    public void setUserRights(tech.fraction.webapp.model.UserRights userRights) {
        UserRights = userRights;
    }

    public tech.fraction.webapp.model.PersonInformation getPersonInformation() {
        return PersonInformation;
    }

    public void setPersonInformation(tech.fraction.webapp.model.PersonInformation personInformation) {
        PersonInformation = personInformation;
    }

    public List<tech.fraction.webapp.model.Menu> getMenu() {
        return Menu;
    }

    public void setMenu(List<tech.fraction.webapp.model.Menu> menu) {
        Menu = menu;
    }
}
