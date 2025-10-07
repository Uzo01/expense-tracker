package utils;

import java.util.List;
import java.io.*;
import java.util.ArrayList;

import models.Transaction;

public class FileHandler {

    public static void saveTransactions(List<Transaction> transaction, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Transaction t : transaction){
                writer.write(t.toString());
                writer.newLine();
            }
        } catch (IOException e){
            System.err.println("Error saving file: " + e.getMessage());
        }
    }

    public static List<Transaction> loadTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()){
            return transactions;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(Transaction.fromCSV(line));
            }
        } catch (IOException e){
            System.err.println("Error Loading file: " + e.getMessage());
        }
        return transactions;
    }
    
}
