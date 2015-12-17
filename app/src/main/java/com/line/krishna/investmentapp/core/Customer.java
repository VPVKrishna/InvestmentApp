package com.line.krishna.investmentapp.core;

import java.io.Serializable;

/**
 * Created by Krishna on 02/12/2015.
 */
public class Customer implements Serializable{
    private int serialId;
    private String name;
    private String address;
    private int latestAccountId;

    public long getSerialId() {
        return serialId;
    }

    public void setSerialId(int serialId) {
        this.serialId = serialId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getLatestAccountId() {
        return latestAccountId;
    }

    public void setLatestAccountId(int latestAccountId) {
        this.latestAccountId = latestAccountId;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "serialId=" + serialId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latestAccountId=" + latestAccountId +
                '}';
    }
}
