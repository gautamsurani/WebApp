package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubMenuList implements Serializable {

    String menuName;

    List<SubSubMenuList> subSubMenuLists = new ArrayList<>();

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<SubSubMenuList> getSubSubMenuLists() {
        return subSubMenuLists;
    }

    public void setSubSubMenuLists(List<SubSubMenuList> subSubMenuLists) {
        this.subSubMenuLists = subSubMenuLists;
    }
}
