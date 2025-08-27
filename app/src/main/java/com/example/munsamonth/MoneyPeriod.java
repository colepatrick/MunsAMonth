package com.example.munsamonth;

import java.io.Serializable;

public class MoneyPeriod implements Serializable {
    public String name;
    public enum PERIOD {
        HOURLY,
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }
    public int[] uncounted;
    public double amountPerPeriod;
    public int frequency; // Frequency is "number of time units to finish the period" like 2 Hourly means every 2 hours the pay is given.
    public PERIOD period;

    public MoneyPeriod(String name, PERIOD period, double amount, int frequency) {
        this.name = name;
        this.period = period;
        this.amountPerPeriod = amount;
        this.frequency = frequency;
        uncounted = new int[4];
    }

    public double calculateHourly() {
        switch (period) {
            case HOURLY:
                return amountPerPeriod / frequency;
            case DAILY:
                return amountPerPeriod / (frequency * 24.0);
            case WEEKLY:
                return amountPerPeriod / (frequency * 7 * 24.0);
            case MONTHLY:
                return amountPerPeriod / (frequency * 30.4375 * 24.0); // average month length
            case YEARLY:
                return amountPerPeriod / (frequency * 365.25 * 24.0); // average year length
            default:
                return -999999999;
        }
    }
    public double calculateDaily() {
        return calculateHourly() * (24 - uncounted[PERIOD.HOURLY.ordinal()]); // 24 hours minus the uncounted hours
    }
    public double calculateWeekly() {
        return calculateDaily() * (7 - uncounted[PERIOD.DAILY.ordinal()]); // 7 days minus the uncounted days
    }
    public double calculateMonthly() {
        return calculateWeekly() * (4.345 - uncounted[PERIOD.WEEKLY.ordinal()]); // 4 weeks minus the uncounted weeks
    }
    public double calculateYearly() {
        return calculateMonthly() * (12 - uncounted[PERIOD.MONTHLY.ordinal()]); // 12 months minus the uncounted months
    }

    public void setUncountedHours(int uncounted) {
        this.uncounted[0] = uncounted;
    }
    public void setUncountedDays(int uncounted) {
        this.uncounted[1] = uncounted;
    }
    public void setUncountedWeeks(int uncounted) {
        this.uncounted[2] = uncounted;
    }
    public void setUncountedMonths(int uncounted) {
        this.uncounted[2] = uncounted;
    }
    public void setAmountPerPeriod(int amountPerPeriod) {
        this.amountPerPeriod = amountPerPeriod;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getUncountedHours() {
        return uncounted[0];
    }
    public int getUncountedDays() {
        return uncounted[1];
    }
    public int getUncountedMonths() {
        return uncounted[2];
    }
    public double getAmountPerPeriod() {
        return amountPerPeriod;
    }
    public String getName() {
        return this.name;
    }
}
