/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.financetracker;

/**
 *
 * @author onyighichijephthah
 */
public class SavingsGoal {
    private String goalName;
    private double targetAmount;
    private double currentSavings;

    public SavingsGoal(String goalName, double targetAmount) {
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.currentSavings = 0;
    }
    
    public void deposit(double amount) {
        this.currentSavings += amount;
    }

    public boolean isGoalReached() {
        return currentSavings >= targetAmount;
    }

    public double getProgressPercentage() {
        return (currentSavings / targetAmount) * 100;
    }
    
    public String getGoalName() { 
        return goalName; 
    }
    
    public double getTargetAmount() { 
        return targetAmount; 
    }
    
    public double getCurrentSavings() { 
        return currentSavings; 
    }
}
