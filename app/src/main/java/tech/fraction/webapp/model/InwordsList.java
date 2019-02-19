package tech.fraction.webapp.model;

import java.io.Serializable;
import java.util.List;

public class InwordsList implements Serializable {
    String name,id,date,charge;
    List<ProductList> productLists;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public InwordsList(String name, String id, String date, String charge, List<ProductList> productLists) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.charge = charge;
        this.productLists = productLists;
    }

    public List<ProductList> getProductLists() {
        return productLists;
    }

    public void setProductLists(List<ProductList> productLists) {
        this.productLists = productLists;
    }
}
