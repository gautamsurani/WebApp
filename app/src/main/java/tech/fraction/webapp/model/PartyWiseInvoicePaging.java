package tech.fraction.webapp.model;

public class PartyWiseInvoicePaging {

    private int accountId;

    private int pageIndex;

    private int pageSize;

    public PartyWiseInvoicePaging(int accountId, int pageIndex, int pageSize) {
        this.accountId = accountId;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
