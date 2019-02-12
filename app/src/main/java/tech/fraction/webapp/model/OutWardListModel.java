package tech.fraction.webapp.model;

public class OutWardListModel {
   private String name ,id,date,charge;

    public OutWardListModel(String name, String id, String date, String charge) {
        this.name = name;
        this.id = id;
        this.date = date;
        this.charge = charge;
    }

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
}
