/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.financetracker;

/**
 *
 * @author onyighichijephthah
 */
public class Budget {
    private double monthlyLimit;
    private double currentExpenses;

    public Budget(double monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
        this.currentExpenses = 0;
    }

    public void addExpense(double amount) {
        this.currentExpenses += amount;
    }

    public boolean isOverBudget() {
        return currentExpenses > monthlyLimit;
    }

    public double getRemainingBudget() {
        return monthlyLimit - currentExpenses;
    }
}
