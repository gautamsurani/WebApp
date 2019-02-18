package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;

public class Childrens implements Serializable {

    private List<Childrens> Childrens;

    private String DisplayName;

    private int TotalChilds;

    private int DisplayOrder;

    private int MenuId;

    private int ParentId;


    public List<tech.fraction.webapp.model.Childrens> getChildrens() {
        return Childrens;
    }

    public void setChildrens(List<tech.fraction.webapp.model.Childrens> childrens) {
        Childrens = childrens;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public int getTotalChilds() {
        return TotalChilds;
    }

    public void setTotalChilds(int totalChilds) {
        TotalChilds = totalChilds;
    }

    public int getDisplayOrder() {
        return DisplayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        DisplayOrder = displayOrder;
    }

    public int getMenuId() {
        return MenuId;
    }

    public void setMenuId(int menuId) {
        MenuId = menuId;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }
}