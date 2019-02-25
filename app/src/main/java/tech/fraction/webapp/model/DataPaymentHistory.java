package tech.fraction.webapp.model;

import java.util.ArrayList;

public class DataPaymentHistory {

    private ArrayList<PastPaymentDetail> PastPaymentDetail;

    private PaidAmountDetail PaidAmountDetail;

    public ArrayList<PastPaymentDetail> getPastPaymentDetail() {
        return PastPaymentDetail;
    }

    public void setPastPaymentDetail(ArrayList<PastPaymentDetail> pastPaymentDetail) {
        PastPaymentDetail = pastPaymentDetail;
    }

    public PaidAmountDetail getPaidAmountDetail() {
        return PaidAmountDetail;
    }

    public void setPaidAmountDetail(PaidAmountDetail paidAmountDetail) {
        PaidAmountDetail = paidAmountDetail;
    }
}
