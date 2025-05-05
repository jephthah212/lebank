/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.financetracker;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *
 * @author onyighichijephthah
 */
public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();
    private int nextId = 1;

    // Adds a transaction to the list and increments the ID tracker
    public void addTransaction(Transaction t) {
        transactions.add(t);
        nextId++;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // Calculates and returns the total monthly expenses
    public double getMonthlyExpenses() {
        return transactions.stream()
            .filter(t -> t.getType().equals("Expense"))  // Only look at expenses
            .mapToDouble(Transaction::getAmount)         // Get the amount of each expense
            .sum();                                      // Sum all expense amounts
    }

    // Finds the next available ID not currently used by any transaction
    public int getNextAvailableId() {
        Set<Integer> usedIds = new HashSet<>();
        for (Transaction t : transactions) {
            usedIds.add(t.getId());  // Track all used IDs
        }

        // Start at 1 and find the first unused ID
        int id = 1;
        while (usedIds.contains(id)) {
            id++;
        }
        return id;
    }
}

