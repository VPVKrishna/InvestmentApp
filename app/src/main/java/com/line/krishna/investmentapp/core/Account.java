package com.line.krishna.investmentapp.core;

/**
 * Created by Krishna on 02/12/2015.
 */
public class Account {

    public static final int ACTIVE=1;
    public static final int CLOSED=2;

    private int id;
    private String takenDate;
    private int lineId;
    private int investmentAmount;
    private int actualAmount;
    private int numPeriods;
    private String referredPerson;
    private boolean activeStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(String takenDate) {
        this.takenDate = takenDate;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public int getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(int investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public int getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(int actualAmount) {
        this.actualAmount = actualAmount;
    }

    public int getNumPeriods() {
        return numPeriods;
    }

    public void setNumPeriods(int numPeriods) {
        this.numPeriods = numPeriods;
    }

    public String getReferredPerson() {
        return referredPerson;
    }

    public void setReferredPerson(String referredPerson) {
        this.referredPerson = referredPerson;
    }

    public boolean isActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public int getActiveStatusInt(){
        return isActiveStatus() ? ACTIVE : CLOSED;
    }

    public void setActiveStatusInt(int statusInt){
        this.activeStatus = (statusInt == ACTIVE);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", takenDate='" + takenDate + '\'' +
                ", lineId='" + lineId + '\'' +
                ", investmentAmount='" + investmentAmount + '\'' +
                ", actualAmount='" + actualAmount + '\'' +
                ", numPeriods=" + numPeriods +
                ", referredPerson='" + referredPerson + '\'' +
                ", activeStatus=" + activeStatus +
                '}';
    }
}
