package tech.fraction.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class MenuList {
    String menuName;
    List<SubMenuList> subMenuLists = new ArrayList<>();

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public List<SubMenuList> getSubMenuLists() {
        return subMenuLists;
    }

    public void setSubMenuLists(List<SubMenuList> subMenuLists) {
        this.subMenuLists = subMenuLists;
    }
}
