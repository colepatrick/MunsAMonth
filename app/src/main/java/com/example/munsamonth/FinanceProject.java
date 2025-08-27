package com.example.munsamonth;

import java.io.Serializable;
import java.util.ArrayList;
public class FinanceProject implements Serializable {
    public String name;
    public ArrayList<MoneyPeriod> expenses;
    public ArrayList<MoneyPeriod> incomes;
    public FinanceProject(String name) {
        this.name = name;
        this.expenses = new ArrayList<>();
        this.incomes = new ArrayList<>();
    }

    public void addExpense(String name, MoneyPeriod.PERIOD period, double amount, int frequency, int uncountedHours, int uncountedDays, int uncountedWeeks, int uncountedMonths) {
        MoneyPeriod newPeriod = new MoneyPeriod(name, period, amount, frequency);
        newPeriod.setUncountedHours(uncountedHours);
        newPeriod.setUncountedDays(uncountedDays);
        newPeriod.setUncountedWeeks(uncountedWeeks);
        newPeriod.setUncountedMonths(uncountedMonths);
        expenses.add(newPeriod);
    }
    public void addIncome(String name, MoneyPeriod.PERIOD period, double amount, int frequency, int uncountedHours, int uncountedDays, int uncountedWeeks, int uncountedMonths) {
        MoneyPeriod newPeriod = new MoneyPeriod(name, period, amount, frequency);
        newPeriod.setUncountedHours(uncountedHours);
        newPeriod.setUncountedDays(uncountedDays);
        newPeriod.setUncountedWeeks(uncountedWeeks);
        newPeriod.setUncountedMonths(uncountedMonths);
        incomes.add(newPeriod);
    }
    public void addExpense(MoneyPeriod newPeriod) {
        expenses.add(newPeriod);
    }
    public void addIncome(MoneyPeriod newPeriod) {
        incomes.add(newPeriod);
    }
    public void removeExpense(String name) {
        expenses.remove(find(true,name));
    }
    public void removeIncome(String name) {
        incomes.remove(find(false,name));
    }
    public MoneyPeriod find(boolean expenses, String name) {
        if(expenses) {
            for(MoneyPeriod expense : this.expenses) {
                if(expense.getName().compareTo(name) == 0) {
                    return expense;
                }
            }
        } else {
            for(MoneyPeriod income : this.incomes) {
                if(income.getName().compareTo(name) == 0) {
                    return income;
                }
            }
        }
        return null;
    }
    public double calculateExpenses() {
        double sum = 0;
        for(MoneyPeriod expense : expenses) {
            sum -= expense.calculateMonthly();
        }
        return sum;
    }

    public double calculateIncome() {
        double sum = 0;
        for(MoneyPeriod income : incomes) {
            sum += income.calculateMonthly();
        }
        return sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}