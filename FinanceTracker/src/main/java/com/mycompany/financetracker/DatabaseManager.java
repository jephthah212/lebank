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

public class DatabaseManager {
    public void saveTransactionsToCSV(List<Transaction> transactions, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction t : transactions) {
                writer.write(String.format("%d,%s,%.2f,%s,%s\n",
                        t.getId(), t.getCategory(), t.getAmount(), t.getDate(), t.getType()));
            }
        }
    }

    public List<Transaction> loadTransactionsFromCSV(String filePath) throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                transactions.add(new Transaction(
                        Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]),
                        LocalDate.parse(parts[3]), parts[4]
                ));
            }
        }
        return transactions;
    }

    public void saveBudget(double limit, double current, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(limit + "," + current);
        }
    }

    public Budget loadBudget(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] parts = reader.readLine().split(",");
            double limit = Double.parseDouble(parts[0]);
            double current = Double.parseDouble(parts[1]);
            Budget b = new Budget(limit);
            b.addExpense(current); // Apply current expenses
            return b;
        }
    }

    public void saveSavingsGoals(List<SavingsGoal> goals, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (SavingsGoal g : goals) {
                writer.write(String.format("%s,%.2f,%.2f\n",
                        g.getGoalName(), g.getTargetAmount(), g.getCurrentSavings()));
            }
        }
    }

    public List<SavingsGoal> loadSavingsGoals(String filePath) throws IOException {
        List<SavingsGoal> goals = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                SavingsGoal goal = new SavingsGoal(parts[0], Double.parseDouble(parts[1]));
                goal.deposit(Double.parseDouble(parts[2]));
                goals.add(goal);
            }
        }
        return goals;
    }
}
