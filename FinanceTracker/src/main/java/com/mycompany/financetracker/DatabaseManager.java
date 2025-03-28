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
}
