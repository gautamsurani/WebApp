package tech.fraction.webapp.model;

import java.io.Serializable;

public class SearchTextViewModel implements Serializable
{
    int id;
    String name ;

    public SearchTextViewModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
