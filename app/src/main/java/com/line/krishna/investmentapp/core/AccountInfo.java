package com.line.krishna.investmentapp.core;

import java.util.ArrayList;

/**
 * Created by Krishna on 11/12/2015.
 */
public class AccountInfo {

    private Account account;
    private Customer customer;
    private ArrayList<Period> periods;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(ArrayList<Period> periods) {
        this.periods = periods;
    }

    @Override
    public String
    toString() {
        return "AccountInfo{" +
                "account=" + account +
                ", customer=" + customer +
                ", periods=" + periods +
                '}';
    }
}
