package tech.fraction.webapp.model;

import java.io.Serializable;

public class OutwardPaidDetail implements Serializable {
    private int toatalAmount;

    private int totalPaidAmount;

    public OutwardPaidDetail(int toatalAmount, int totalPaidAmount) {
        this.toatalAmount = toatalAmount;
        this.totalPaidAmount = totalPaidAmount;
    }

    public int getToatalAmount() {
        return toatalAmount;
    }

    public void setToatalAmount(int toatalAmount) {
        this.toatalAmount = toatalAmount;
    }

    public int getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(int totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }
}