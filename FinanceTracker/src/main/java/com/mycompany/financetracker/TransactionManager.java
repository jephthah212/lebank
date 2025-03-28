/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.financetracker;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author onyighichijephthah
 */
public class TransactionManager {
    private List<Transaction> transactions = new ArrayList<>();
    private int nextId = 1;

    public void addTransaction(Transaction t) {
        transactions.add(t);
        nextId++;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public int getNextId() {
        return nextId;
    }

    public double getMonthlyExpenses() {
        return transactions.stream()
            .filter(t -> t.getType().equals("Expense"))
            .mapToDouble(Transaction::getAmount)
            .sum();
    }
}
