package com.line.krishna.investmentapp.core;

/**
 * Created by Krishna on 02/12/2015.
 */
public class Line {

    private int id;
    private String name;
    private String weekDay;
    private String investersAmounts;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getInvestersAmounts() {
        return investersAmounts;
    }

    public void setInvestersAmounts(String investersAmounts) {
        this.investersAmounts = investersAmounts;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weekDay='" + weekDay + '\'' +
                ", investersAmounts='" + investersAmounts + '\'' +
                '}';
    }
}
