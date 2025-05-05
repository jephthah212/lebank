/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.financetracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author onyighichijephthah
 */



 /**
 * Handles saving and loading of transactions, budgets, and savings goals
 * using CSV and plain text files.
 */
public class DatabaseManager {

    // Save all transactions to a CSV file
    public void saveTransactionsToCSV(List<Transaction> transactions, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction t : transactions) {
                // Write transaction fields as comma-separated values
                writer.write(String.format("%d,%s,%.2f,%s,%s\n",
                        t.getId(), t.getCategory(), t.getAmount(), t.getDate(), t.getType()));
            }
        }
    }

    // Load transactions from a CSV file and return them as a list
    public List<Transaction> loadTransactionsFromCSV(String filePath) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split line into parts and build a Transaction object
                String[] parts = line.split(",");
                transactions.add(new Transaction(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Double.parseDouble(parts[2]),
                        LocalDate.parse(parts[3]),
                        parts[4]
                ));
            }
        }
        return transactions;
    }

    // Save budget data (limit and current expenses) to a file
    public void saveBudget(double limit, double current, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(limit + "," + current);
        }
    }

    // Load budget data from file and return a Budget object
    public Budget loadBudget(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] parts = reader.readLine().split(",");
            double limit = Double.parseDouble(parts[0]);
            double current = Double.parseDouble(parts[1]);

            // Create budget and apply the saved expense value
            Budget b = new Budget(limit);
            b.addExpense(current);
            return b;
        }
    }

    // Save all savings goals to a CSV file
    public void saveSavingsGoals(List<SavingsGoal> goals, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (SavingsGoal g : goals) {
                // Save goal name, target amount, and current saved amount
                writer.write(String.format("%s,%.2f,%.2f\n",
                        g.getGoalName(), g.getTargetAmount(), g.getCurrentSavings()));
            }
        }
    }

    // Load savings goals from a CSV file and return them as a list
    public List<SavingsGoal> loadSavingsGoals(String filePath) throws IOException {
        List<SavingsGoal> goals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split line and recreate the goal with progress
                String[] parts = line.split(",");
                SavingsGoal goal = new SavingsGoal(parts[0], Double.parseDouble(parts[1]));
                goal.deposit(Double.parseDouble(parts[2]));
                goals.add(goal);
            }
        }
        return goals;
    }
}