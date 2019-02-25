package tech.fraction.webapp.model;

public class PaidAmountDetail {

    private String PaidStatus;

    private int AccountId;

    private int ReferenceId;

    private Double Amount;

    private String ReferenceNumber;

    private int ExpanseReasonId;

    private Double PaidAmount;

    private Double RemainingAmount;

    private String AccountName;

    private String TransactionDate;

    public String getPaidStatus() {
        return PaidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        PaidStatus = paidStatus;
    }

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public int getReferenceId() {
        return ReferenceId;
    }

    public void setReferenceId(int referenceId) {
        ReferenceId = referenceId;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getReferenceNumber() {
        return ReferenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        ReferenceNumber = referenceNumber;
    }

    public int getExpanseReasonId() {
        return ExpanseReasonId;
    }

    public void setExpanseReasonId(int expanseReasonId) {
        ExpanseReasonId = expanseReasonId;
    }

    public Double getPaidAmount() {
        return PaidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        PaidAmount = paidAmount;
    }

    public Double getRemainingAmount() {
        return RemainingAmount;
    }

    public void setRemainingAmount(Double remainingAmount) {
        RemainingAmount = remainingAmount;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        TransactionDate = transactionDate;
    }
}
