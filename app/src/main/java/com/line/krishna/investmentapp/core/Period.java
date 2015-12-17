package com.line.krishna.investmentapp.core;

/**
 * Created by Krishna on 02/12/2015.
 */
public class Period {

    private int id;
    private int accountId;
    private String date;
    private int numPeriods;
    private int paidAmount;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumPeriods() {
        return numPeriods;
    }

    public void setNumPeriods(int numPeriods) {
        this.numPeriods = numPeriods;
    }

    public long getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(int paidAmount) {
        this.paidAmount = paidAmount;
    }

    @Override
    public String toString() {
        return "Period{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", date='" + date + '\'' +
                ", numPeriods=" + numPeriods +
                ", paidAmount=" + paidAmount +
                '}';
    }
}
