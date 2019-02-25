package tech.fraction.webapp.model;

public class PastPaymentDetail {

    private int TransactionSettlementId;

    private String SettlementDate;

    private Double SettlementAmount;

    private boolean isProgressing = false;

    public boolean isProgressing() {
        return isProgressing;
    }

    public void setProgressing(boolean progressing) {
        isProgressing = progressing;
    }

    public int getTransactionSettlementId() {
        return TransactionSettlementId;
    }

    public void setTransactionSettlementId(int transactionSettlementId) {
        TransactionSettlementId = transactionSettlementId;
    }

    public String getSettlementDate() {
        return SettlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        SettlementDate = settlementDate;
    }

    public Double getSettlementAmount() {
        return SettlementAmount;
    }

    public void setSettlementAmount(Double settlementAmount) {
        SettlementAmount = settlementAmount;
    }
}
