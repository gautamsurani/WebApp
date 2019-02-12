package tech.fraction.webapp.model;

import java.io.Serializable;

public class InwardPaidDetail implements Serializable {
    private int ToatalAmount;

    private int TotalPaidAmount;

    public int getToatalAmount() {
        return ToatalAmount;
    }

    public void setToatalAmount(int toatalAmount) {
        ToatalAmount = toatalAmount;
    }

    public int getTotalPaidAmount() {
        return TotalPaidAmount;
    }

    public void setTotalPaidAmount(int totalPaidAmount) {
        TotalPaidAmount = totalPaidAmount;
    }
}