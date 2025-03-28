/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.financetracker;
import java.time.LocalDate;
/**
 *
 * @author onyighichijephthah
 */
public class Transaction {
    private int id;
    private String category;
    private double amount;
    private LocalDate date;
    private String type; // "Income" or "Expense"

    public Transaction(int id, String category, double amount, LocalDate date, String type) {
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    // Getters and setters
    public int getId() { 
        return id; }
    
    public String getCategory() { 
        return category; }
    
    public double getAmount() { 
        return amount; }
    
    public LocalDate getDate() { 
        return date; }
    
    public String getType() { 
        return type; }
}
