package tech.fraction.webapp.model;

import java.io.Serializable;

public class ProductList implements Serializable {
    String name,count;

    public ProductList() {
    }

    public ProductList(String name, String count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
